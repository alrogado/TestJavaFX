package org.lazer.controllers.effects;

import com.chrisnewland.demofx.DemoFX;

/**
 * Created by alrogado on 6/2/17.
 */
public class AbstractEffectsController implements EffectRunnable {
    DemoFX demoFX;

    @Override
    public void stopEffects() {
        demoFX.stopDemo();
    }
}
