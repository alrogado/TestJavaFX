package org.testjavafx.components.regulators;

/**
 * Created by alvaro.lopez on 04/07/2017.
 */
public class MainPaneInnerControls extends MainPane {


    int factorXForSecondTiles = 280;

    public static MainPane getInstance() {
        return instance == null ? instance = new MainPaneInnerControls() : instance;
    }

    protected double getXForComp() {
        return getPadding().getLeft() - 150;
    }

    protected double getXForStartButton(double x, double depositTempWidth) {
        return (getWidth() / 2) - startButton.getWidth() / 2 - 20;
    }

    protected double getRepositionXForLeftButtons() {
        return (getWidth() / 2) - getWorkModebuttonsPaneLeft().getWidth() - 20;
    }

    protected double getXForMessagePane(double x, double depositTempWidth) {
        return x + depositTempWidth - depositTempWidth / 10;
    }

    protected double getRespositionMinusXForRightButtons() {
        return (getWidth() / 2) + 5;
    }

    protected double getRepositionXForFluencyTile() {
        return factorXForSecondTiles;
    }

    protected double getRepositionXForTipTile() {
        return factorXForSecondTiles;
    }

    protected void setSpacingWorkModeButtons() {
        //reposition the distance for workmode buttons
        int factorForSpacingButtons = 0;
        getWorkModebuttonsPaneLeft().setSpacing((getHeight() / 7 / getWorkModebuttonsPaneLeft().getChildren().size()) - factorForSpacingButtons);
        getWorkModebuttonsPaneRight().setSpacing((getHeight() / 7 / getWorkModebuttonsPaneRight().getChildren().size()) - factorForSpacingButtons);
    }


    protected double getRepositionXForFreqTile() {
        return 0;
    }

    protected double getWidthForMessagePanel() {
        return 280 + (depositTempTile.getWidth() * 0.2);
    }

}
