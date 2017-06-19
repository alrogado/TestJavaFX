package org.testjfx.controllers.components;

import eu.hansolo.fx.regulators.ColorRegulator;
import eu.hansolo.fx.regulators.FeedbackRegulator;
import eu.hansolo.fx.regulators.Regulator;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.testjfx.components.RegulatorBuilder;
import org.tbee.javafx.scene.layout.MigPane;

import javax.annotation.PostConstruct;
import java.util.Objects;

import static org.testjfx.util.EffectUtils.fadeIn;

@ViewController(value = "/org/testjfx/fxml/ui/main_content_regulators.fxml", title = "Material Design Example")
public class RegulatorsController {


    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    StackPane root;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        Objects.requireNonNull(context, "context");
        MigPane rootMP = new MigPane(new LC().fillX().fillY().pack(), new AC(), new AC());
        //use Reactfx to manipulate bindings and values from communications
        //frequency.minValueProperty()

        rootMP.add(createTempGauage(), "alignx center, wrap");
        //rootMP.add(createTempRegulator(), "alignx center, wrap");
        rootMP.add(createFreqFluBox(), "alignx center, wrap");

        root.getChildren().addAll(rootMP);
        fadeIn(root);
    }

    private HBox createFreqFluBox() {
        Regulator frequency = RegulatorBuilder.createRegulator("Frecuencia", "Hz", null, 50d, 30d, 200d);
        Regulator fluency = RegulatorBuilder.createRegulator("Fluencia", "J/cm", null, 96d, 20d, 130d);
        HBox freqFluPane = new HBox(frequency, fluency);
        freqFluPane.setSpacing(20);
        freqFluPane.setPadding(new Insets(10));
        return freqFluPane;
    }

    private HBox createTempRegulator() {
        FeedbackRegulator depositTemp = RegulatorBuilder.createFeedbackRegulator("Deposit", "Deposit", MaterialDesign.MDI_TEMPERATURE_CELSIUS, 50d, 30d, 200d);
        ColorRegulator tipTemp = RegulatorBuilder.createColorRegulator("Punta", "Punta", MaterialDesign.MDI_OIL_TEMPERATURE, 96d, 20d, 130d);

        HBox tempPane = new HBox(depositTemp, tipTemp);
        tempPane.setSpacing(20);
        tempPane.setPadding(new Insets(10));
        return tempPane;
    }
    private MigPane createTempGauage() {
        Tile depositTempTile = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                //.prefSize(WIDTH, TILE_HEIGHT)
                .title("Deposit Temperature")
                .unit("ºC")
                .threshold(75)
                .build();

        Tile tipTempTile = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                //.prefSize(WIDTH, TILE_HEIGHT)
                .title("Tip Temperature")
                .unit("ºC")
                .threshold(75)
                .build();


        MigPane tempPane = new MigPane(new LC().fillX().fillY().pack(), new AC(), new AC());
        //MigPane tempPane = new MigPane("debug", "[grow,fill]", "");
        tempPane.add(depositTempTile, "w 45sp,h 45sp");
        tempPane.add(tipTempTile, "w 45sp,h 45sp");
        /*HBox tempPane = new HBox(depositTempTile, tipTempTile);
        tempPane.setSpacing(20);
        tempPane.setPadding(new Insets(10));*/
        return tempPane;
    }
}
