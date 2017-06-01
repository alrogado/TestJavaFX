package gauge;

import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.scene.control.gauge.linear.BasicRoundDailGauge;
import jfxtras.scene.control.gauge.linear.SimpleMetroArcGauge;
import org.reactfx.EventStreams;

import java.net.URL;
import java.time.Duration;
import java.util.Random;

/**
 * Created by alrogado on 5/25/17.
 */
public class GaugeTest extends Application {

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
        // adding data to one chart which changes the scaling would adjust the other charts
        // axes as well

        SimpleMetroArcGauge simpleMetroArcGauge = new SimpleMetroArcGauge();
        BasicRoundDailGauge basicRoundDailGauge = new BasicRoundDailGauge();

        VBox charts = new VBox();
        charts.getChildren().add(simpleMetroArcGauge);
        charts.getChildren().add(basicRoundDailGauge);
        Scene scene  = new Scene(charts);

        /*URL styleSheet = getClass().getResource("chartExample.css");
        scene.getStylesheets().add(styleSheet.toExternalForm());*/

        stage.setScene(scene);
        stage.show();
        Random random = new Random(100);
        EventStreams.ticks(Duration.ofMillis(2500))
                .subscribe(tick -> {
                    simpleMetroArcGauge.setValue(random.nextInt(100));
                    basicRoundDailGauge.setValue(random.nextInt(100));
                });
    }
}
