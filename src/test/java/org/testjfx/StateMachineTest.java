package org.testjfx;

import javafx.beans.binding.Binding;
import org.junit.Test;
import org.reactfx.*;
import org.reactfx.util.Tuple2;

import java.time.Duration;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.reactfx.util.Tuples.t;

/**
 * Created by alvaro.lopez on 06/07/2017.
 */
public class StateMachineTest {

    private static class Counter {
        private int count = 0;
        public void inc() { ++count; }
        public int get() { return count; }
        public int getAndReset() { int res = count; count = 0; return res; }
    }

    @Test
    public void countDownTest() {
        EventSource<Void> src1 = new EventSource<Void>();
        EventSource<Void> src2 = new EventSource<Void>();
        EventSource<Void> reset = new EventSource<Void>();

        BiFunction<Integer, Void, Tuple2<Integer, Optional<String>>> countdown =
                (s, i) -> s == 1
                        ? t(3, Optional.of("COUNTDOWN REACHED"))
                        : t(s-1, Optional.empty());

        EventStream<String> countdowns = StateMachine.init(3)
                .on(src1).transmit(countdown)
                .on(src2).transmit(countdown)
                .on(reset).transition((s, i) -> 3)
                .toEventStream();

        Counter counter = new Counter();
        Subscription sub = countdowns.hook(x -> counter.inc()).pin();

        src1.push(null);
        src2.push(null);
        assertEquals(0, counter.get());

        src1.push(null);
        assertEquals(1, counter.getAndReset());

        src2.push(null);
        src2.push(null);
        reset.push(null);
        assertEquals(0, counter.get());
        src2.push(null);
        assertEquals(0, counter.get());
        src1.push(null);
        assertEquals(0, counter.get());
        src2.push(null);
        assertEquals(1, counter.getAndReset());

        sub.unsubscribe();
        src1.push(null);
        src1.push(null);
        src1.push(null);
        src1.push(null);
        src1.push(null);
        src1.push(null);
        assertEquals(0, counter.get());
    }

    @Test
    public void testEventStream() {
        EventSource<Void> src1 = new EventSource<Void>();
    }

    private static EventStream<Boolean> booleanPulse(javafx.util.Duration javafxDuration, EventStream<?> restartImpulse) {
        Duration duration = Duration.ofMillis(Math.round(javafxDuration.toMillis()));
        EventStream<?> ticks = EventStreams.restartableTicks(duration, restartImpulse);
        return StateMachine.init(false)
                .on(restartImpulse.withDefaultEvent(null)).transition((state, impulse) -> true)
                .on(ticks).transition((state, tick) -> !state)
                .toStateStream();
    }


    private <T> void manageSubscription(EventStream<T> stream, Consumer<T> subscriber) {
        manageSubscription(stream.subscribe(subscriber));
    }

    private Subscription subscriptions = () -> {};

    private void manageBinding(Binding<?> binding) {
        manageSubscription(binding::dispose);
    }

    private void manageSubscription(Subscription s) {
        subscriptions = subscriptions.and(s);
    }
    public void dispose() {
        subscriptions.unsubscribe();
    }

}
