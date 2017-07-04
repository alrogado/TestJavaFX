package org.testjfx.components;

/**
 * Created by alvaro.lopez on 04/07/2017.
 */
public class RegulatorsInnerControlsPane extends RegulatorsPane {


    protected double getXForComp() {
        return getPadding().getLeft()-150;
    }

    protected double getXForStartButton(double x, double depositTempWidth) {
        return(getWidth()/2)-startButton.getWidth()/2 -20;
    }

    protected double getRepositionFactorXForLeftButtons() {
        return (getWidth()/2)-getWorkModebuttonsPaneLeft().getWidth()-20;
    }


    protected double getRespositionMinusFactorXForRightButtons() {
        return (getWidth()/2)+5;
    }


    int factorXForSecondTiles = 280;

    protected double getRepositionFactorXForFluencyTile() {
        return factorXForSecondTiles;
    }

    protected double getRepositionFactorXForTipTile() {
        return factorXForSecondTiles;
    }

    public static RegulatorsPane getInstance() {
        return instance==null?instance=new RegulatorsInnerControlsPane():instance;
    }

    protected void setSpacingWorkModeButtons() {
        //reposition the distance for workmode buttons
        int factorForSpacingButtons = 0;
        getWorkModebuttonsPaneLeft().setSpacing((getHeight() / 7 / getWorkModebuttonsPaneLeft().getChildren().size()) - factorForSpacingButtons);
        getWorkModebuttonsPaneRight().setSpacing((getHeight() / 7 / getWorkModebuttonsPaneRight().getChildren().size()) - factorForSpacingButtons);
    }

    protected int getFactorToResizeStartButton() {
        return 10;
    }

}
