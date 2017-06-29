package jidefx;

import com.sun.javafx.scene.control.skin.LabeledText;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.ListBinding;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jidefx.scene.control.decoration.DecorationUtils;
import jidefx.scene.control.decoration.Decorator;
import jidefx.scene.control.field.BoundingBoxField;
import jidefx.scene.control.field.PopupField;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by alvaro.lopez on 28/06/2017.
 */
public class TestCombo extends Application{

    public static final String PREFIX_COMBO_BOX_FORM = "ComboBoxForm";


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tabs");

        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList("uno", "dos"));

        VBox pane = new VBox();
        pane.getChildren().add(choiceBox);
        /*

        addDecoratorForPopupField(boundingBoxLabel, boundingBoxField);*/

        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    private static void addDecoratorForPopupField(Label label, PopupField<?> field) {
        field.setPopupButtonVisible(true);

       /* ImageView tip = new ImageView(new Image("/images/logo.png"));
        Tooltip tooltip = new Tooltip("Pattern:" + field.getPattern());
        Tooltip.install(tip, tooltip);
        DecorationUtils.install(label, new Decorator<Node>(tip, Pos.CENTER_RIGHT, new Point2D(80, 0)));*/

        field.installAdjustmentMouseHandler(label, 1);
    }


}
