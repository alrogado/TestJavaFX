package charts;

import com.jfoenix.controls.JFXSlider;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.beans.binding.Binding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.fxmisc.easybind.EasyBind;
import org.reactfx.EventStreams;

import java.net.URL;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * Created by alrogado on 5/25/17.
 */
public class ChartsTest extends Application {

    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        /*Flow flow = new Flow(MainController.class);
        DefaultFlowContainer container = new DefaultFlowContainer();
        context = new ViewFlowContext();
        context.register("Stage", stage);
        flow.createHandler(context).start(container);

        JFXDecorator decorator = new JFXDecorator(stage, container.getView());
        decorator.setCustomMaximize(true);*/

        // defining the axes (NOTE: It is possible to use the same axis
        // for more than one chart - in that case, the axises are synchronized,
        // adding data to one chart which changes the scaling would adjust the other chartsVBox
        // axes as well
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Number of Month");

        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setId("chart1");
        lineChart.setTitle("Chart 1");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        AtomicInteger ai = new AtomicInteger(1);
        series.getData().add(new XYChart.Data<>(ai.getAndIncrement(), 23));
        series.getData().add(new XYChart.Data<>(ai.getAndIncrement(), 14));
        series.getData().add(new XYChart.Data<>(ai.getAndIncrement(), 15));

        final NumberAxis xAxis2 = new NumberAxis();
        final NumberAxis yAxis2 = new NumberAxis();

        final LineChart<Number,Number> lineChart2 =
                new LineChart<Number,Number>(xAxis2,yAxis2);
        lineChart2.setId("chart2");
        lineChart2.setTitle("Chart 2");
        lineChart2.setCreateSymbols(false); // do not show the dots at each data point
        lineChart2.setAnimated(false);      // do not animate the diagram
        lineChart2.setLegendVisible(false); // do not show the legend

        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        for (double x = -4.9;  x < 4.9;  x += 0.1) {
            series2.getData().add(new XYChart.Data<>(x, Math.sin(x) * 10));
        }
        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
        for (double x = -4.9;  x < 4.9;  x += 0.1) {
            series3.getData().add(new XYChart.Data<>(x, Math.signum(Math.sin(x + 1.2)) * 5 ));
        }

        VBox chartsVBox = new VBox();
        chartsVBox.getChildren().addAll(lineChart, lineChart2);

        Scene scene  = new Scene(chartsVBox);
        lineChart.getData().add(series);
        lineChart2.getData().add(series2);
        lineChart2.getData().add(series3);

        URL styleSheet = getClass().getResource("chartExample.css");
        scene.getStylesheets().add(styleSheet.toExternalForm());

        stage.setScene(scene);
        stage.show();

        Binding<Boolean> bb = EasyBind.select(stage.sceneProperty())
                .select(s -> s.windowProperty())
                .selectObject(w -> w.focusedProperty());


        DoubleAccumulator ad = new DoubleAccumulator((double left, double right)->left+right,5);
        //AtomicInteger pot = new AtomicInteger(20);
        Random random = new Random(100);
        IntegerProperty ip = new SimpleIntegerProperty(random.nextInt(100));
        JFXSlider sizeSlider = new JFXSlider(0, 100, ip.getValue());
        sizeSlider.valueProperty().bind(ip);
        chartsVBox.getChildren().add(sizeSlider);
        EventStreams.ticks(Duration.ofMillis(500))
                .subscribe(tick -> {
                    ip.setValue(random.nextInt(100));
                    series.getData().add(new XYChart.Data<>(ai.getAndIncrement(), ip.getValue()));
                    for (double x = ad.get();  x < ad.get()+4.9;  x += 0.1) {
                        series2.getData().add(new XYChart.Data<>(x, Math.sin(x) * 10));
                    }
                    for (double x = ad.get();  x < ad.get()+4.9;  x += 0.1) {
                        series3.getData().add(new XYChart.Data<>(x, Math.signum(Math.sin(x + 1.2)) * 5 ));
                    }
                    //pot.getAndAdd(10);
                    ad.accumulate(5);
                    //basicRoundDailGauge.setValue(random.nextInt(100));
                    lineChart2.setTitle(""+bb.getValue());
                });

        Property<Integer> a = new SimpleObjectProperty<>(5);
        Property<Integer> b = new SimpleObjectProperty<>(10);
        ObservableList<Property<Integer>> list = FXCollections.observableArrayList();

        Binding<Integer> sum = EasyBind.combine(
                list,
                stream -> stream.reduce((c, d) -> c + d).orElse(0));

        assert sum.getValue() == 0;

        // sum responds to element additions
        list.add(a);
        list.add(b);
        assert sum.getValue() == 15;

        // sum responds to element value changes
        a.setValue(20);
        assert sum.getValue() == 30;

    }
}
