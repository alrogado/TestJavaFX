package org.lazer.util.testbuttons;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.SVGPathBuilder;
import javafx.stage.Stage;
import org.lazer.GuiApp;
import org.lazer.util.GuiColors;
import org.lazer.util.ImageLogoUtils;

/**
 * Created by alvaro.lopez on 09/06/2017.
 */
public class LogoButtonResizeGrad extends Application {

    private final int MIN_BUTTON_SIZE = 10;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        /*
        Esto no crea el path bien
        SVGPath svg = new SVGPath();
        svg.setContent("m 136.55471,144.21642 c 0,-1.81495 0.0212,-2.20853 0.12329,-2.29329 0.15039,-0.12482 0.30004,-0.0464 0.30004,0.15714 0,0.25629 0.0789,0.25383 0.60785,-0.019 0.46515,-0.2399 0.58429,-0.26425 1.2859,-0.26288 0.94342,0.002 1.5749,0.24607 1.96764,0.76098 0.22388,0.29352 0.24495,0.38054 0.24495,1.01166 0,0.63111 -0.0211,0.71814 -0.24495,1.01165 -0.39274,0.51491 -1.02422,0.75914 -1.96764,0.76098 -0.70161,0.001 -0.82075,-0.023 -1.2859,-0.26288 -0.28196,-0.14541 -0.53407,-0.24297 -0.56025,-0.21679 -0.0262,0.0262 -0.0476,0.38414 -0.0476,0.79548 0,0.74131 -0.002,0.74789 -0.21167,0.74789 h -0.21166 z m 3.44832,0.56849 c 1.0371,-0.53371 1.0121,-1.93867 -0.0437,-2.45548 -0.5417,-0.26517 -1.58994,-0.26965 -2.0923,-0.009 -0.57434,0.29805 -0.89142,0.75456 -0.88714,1.27724 0.003,0.34225 0.29347,0.91469 0.54973,1.0826 0.62771,0.41129 1.78252,0.46012 2.4734,0.10458 z m 1.87862,1.58735 c -0.0194,-0.0193 -0.0162,-1.03536 0.007,-2.25787 0.0322,-1.69194 0.068,-2.2313 0.14991,-2.25852 0.0613,-0.0204 0.14667,0.0764 0.19841,0.22481 l 0.0908,0.26057 0.35193,-0.20284 c 0.9362,-0.53962 2.40173,-0.47105 3.15209,0.14746 0.81718,0.67358 0.82098,1.85991 0.008,2.57351 -0.69836,0.61317 -2.35393,0.68378 -3.16277,0.13488 -0.39629,-0.26893 -0.44699,-0.18817 -0.44867,0.71462 -0.001,0.62042 -0.0187,0.6985 -0.15652,0.6985 -0.0854,0 -0.17109,-0.0158 -0.1905,-0.0351 z m 3.41042,-1.59004 c 0.54365,-0.30376 0.70297,-0.57826 0.70297,-1.21117 0,-0.63759 -0.15911,-0.90736 -0.71985,-1.22049 -0.4761,-0.26586 -1.44951,-0.29652 -2.05057,-0.0646 -1.3336,0.51458 -1.22698,2.25947 0.162,2.65125 0.57773,0.16296 1.46568,0.0907 1.90545,-0.15499 z m -12.97069,0.3944 c -0.47705,-0.14813 -1.00096,-0.57522 -1.15352,-0.94035 -0.28564,-0.68362 -0.0359,-1.60695 0.54266,-2.00654 0.34818,-0.24046 1.15837,-0.47901 1.62686,-0.47901 0.43131,0 1.24935,0.22776 1.52628,0.42496 0.30306,0.2158 0.31996,0.21267 0.35193,-0.0651 0.0193,-0.16746 0.0808,-0.2405 0.2191,-0.26013 l 0.19231,-0.0273 -0.023,1.67719 c -0.0202,1.47346 -0.0398,1.68043 -0.16136,1.70379 -0.0909,0.0175 -0.17016,-0.0646 -0.23106,-0.23928 l -0.0927,-0.26586 -0.22508,0.17705 c -0.51351,0.40393 -1.76771,0.55048 -2.57247,0.3006 z m 1.78653,-0.30493 c 1.30563,-0.43714 1.4932,-1.88129 0.32789,-2.52459 -0.51802,-0.28597 -1.64407,-0.30175 -2.15676,-0.0302 -0.45154,0.23914 -0.68443,0.50671 -0.7945,0.91284 -0.18052,0.66608 0.24529,1.41022 0.93267,1.62991 0.38642,0.1235 1.3376,0.13029 1.6907,0.0121 z m 23.31713,0.36149 c -0.81408,-0.21371 -1.2969,-0.70565 -1.37531,-1.40129 -0.0646,-0.57299 0.0419,-0.95675 0.3648,-1.31411 0.99911,-1.10588 3.55518,-0.93698 4.1036,0.27116 0.19208,0.42314 0.20938,0.68352 0.054,0.81248 -0.0732,0.0607 -0.80641,0.1014 -2.0955,0.1162 l -1.98291,0.0228 0.0179,0.254 c 0.0227,0.32203 0.48129,0.7587 0.92657,0.88234 0.4174,0.1159 1.33321,0.11619 1.74965,5.3e-4 0.17792,-0.0494 0.46829,-0.22065 0.64526,-0.38053 0.50047,-0.45214 0.81587,-0.33704 0.42792,0.15616 -0.31349,0.39853 -0.86789,0.59334 -1.76873,0.62152 -0.4473,0.014 -0.92757,-0.005 -1.06727,-0.0412 z m 2.71812,-1.99316 c 0.0252,-0.0657 -0.0132,-0.23341 -0.0853,-0.37282 -0.40096,-0.77537 -2.13589,-1.01209 -3.02521,-0.41278 -0.30805,0.20759 -0.62301,0.68432 -0.54041,0.81797 0.034,0.0551 0.70532,0.087 1.82943,0.087 1.44973,0 1.78407,-0.0219 1.82146,-0.11936 z m 11.09846,1.97972 c -0.6636,-0.10462 -1.44787,-0.46906 -1.75004,-0.81321 -0.46912,-0.53429 -0.54097,-1.25881 -0.12484,-1.25881 0.11402,0 0.18207,0.1078 0.2594,0.41091 0.0576,0.226 0.18771,0.49915 0.28903,0.60699 0.45744,0.48693 1.71138,0.76788 3.08854,0.69201 1.00694,-0.0555 1.64427,-0.23364 2.01241,-0.56257 0.2223,-0.19863 0.26974,-0.30368 0.26974,-0.59733 0,-0.28305 -0.0475,-0.39719 -0.23104,-0.55506 -0.29736,-0.25578 -0.55583,-0.32565 -2.34745,-0.63458 -0.81491,-0.14053 -1.68169,-0.30766 -1.92619,-0.37141 -0.6384,-0.16646 -1.15992,-0.49787 -1.32245,-0.84037 -0.59646,-1.25695 0.93109,-2.12739 3.44382,-1.96238 1.09486,0.0719 1.82698,0.30143 2.25087,0.70569 0.38941,0.37138 0.62707,0.99708 0.42685,1.12379 -0.2284,0.14454 -0.31289,0.0768 -0.58689,-0.47048 -0.28982,-0.57886 -0.68706,-0.78758 -1.72298,-0.90525 -0.98905,-0.11235 -2.21316,-0.002 -2.78438,0.25092 -0.76338,0.33804 -0.84625,1.05785 -0.16551,1.43747 0.41853,0.23339 0.77469,0.32029 2.1752,0.53069 1.41346,0.21234 2.30692,0.42841 2.68527,0.64938 0.39185,0.22885 0.65907,0.65965 0.65907,1.06253 0,0.63391 -0.41892,1.04636 -1.3704,1.34928 -0.61689,0.1964 -2.41026,0.28073 -3.22803,0.15179 z m -44.74276,-0.12422 c -0.6301,-0.17633 -0.89733,-0.33153 -1.1972,-0.69533 -0.24528,-0.29757 -0.35116,-0.75371 -0.20815,-0.89672 0.10893,-0.10893 0.34918,0.11229 0.40047,0.36876 0.066,0.32985 0.44091,0.68862 0.87025,0.8327 0.4748,0.15934 2.09721,0.2184 2.67941,0.0975 0.78698,-0.16337 1.19451,-0.61488 0.99334,-1.10054 -0.14338,-0.34616 -0.5336,-0.48288 -2.1912,-0.76771 -2.05846,-0.35372 -2.57483,-0.62523 -2.57483,-1.35389 0,-0.43026 0.2997,-0.77485 0.86299,-0.99227 0.55551,-0.21441 2.57375,-0.21416 3.18817,4.2e-4 0.55227,0.19286 0.97797,0.62231 0.99611,1.00489 0.0154,0.32529 -0.16338,0.39044 -0.31574,0.11505 -0.44001,-0.79537 -0.83922,-0.9737 -2.17964,-0.9737 -1.4709,0 -2.10546,0.26276 -2.10546,0.87184 0,0.53215 0.27806,0.65985 2.06547,0.94857 1.9344,0.31247 2.37729,0.47808 2.64072,0.9875 0.17244,0.33346 0.11068,0.78377 -0.14479,1.0557 -0.10264,0.10926 -0.39943,0.28131 -0.65952,0.38231 -0.61985,0.24073 -2.43224,0.30746 -3.1204,0.11488 z m 20.67979,0.0256 c -0.024,-0.0624 -0.0334,-1.10346 -0.0209,-2.31343 0.022,-2.13595 0.0276,-2.19994 0.19197,-2.19994 0.15538,0 0.17134,0.0646 0.19368,0.78317 0.0134,0.43074 0.0382,0.78316 0.0551,0.78316 0.0169,0 0.23456,-0.0949 0.48365,-0.21084 0.36127,-0.16817 0.58798,-0.211 1.12042,-0.21166 0.82795,-10e-4 1.26373,0.12477 1.63048,0.47066 l 0.28434,0.26817 v 1.36584 c 0,1.28315 -0.01,1.36583 -0.1601,1.36583 -0.1485,0 -0.16234,-0.0916 -0.19093,-1.26363 -0.0291,-1.19482 -0.0419,-1.27537 -0.23334,-1.47921 -0.11139,-0.11856 -0.33345,-0.25878 -0.49347,-0.3116 -0.4013,-0.13244 -1.43547,-0.0624 -1.81703,0.12311 -0.53935,0.26217 -0.6134,0.45763 -0.66113,1.74506 -0.0371,1.00141 -0.0606,1.14556 -0.19075,1.17043 -0.0816,0.0156 -0.16802,-0.0227 -0.19197,-0.0851 z m 4.92943,0.0474 c -0.0326,-0.0327 -0.0594,-0.79862 -0.0594,-1.70215 v -1.64278 l 0.1905,0.0271 c 0.19011,0.027 0.1905,0.0304 0.1905,1.6769 0,1.42229 -0.0181,1.65332 -0.13113,1.67509 -0.0721,0.0139 -0.15784,-0.002 -0.1905,-0.0341 z m 1.27,0 c -0.0869,-0.0869 -0.0701,-3.10044 0.018,-3.23966 0.0997,-0.15752 0.34592,-0.0552 0.34592,0.14374 0,0.26195 0.0905,0.27439 0.45564,0.0627 0.34991,-0.20288 1.27669,-0.42278 1.41683,-0.33618 0.18959,0.11718 0.0512,0.27923 -0.28497,0.33359 -0.62247,0.10068 -1.04856,0.3369 -1.30406,0.72297 -0.21578,0.32608 -0.24081,0.44529 -0.28131,1.34009 -0.036,0.79603 -0.0693,0.98692 -0.17559,1.00717 -0.0721,0.0137 -0.15785,-0.002 -0.1905,-0.0344 z m 10.05446,-0.053 c -0.0225,-0.0586 -0.0308,-1.36349 -0.0185,-2.89984 l 0.0224,-2.79335 h 0.21166 0.21167 l 0.0454,2.667 c 0.025,1.46685 0.0535,2.67594 0.0635,2.68686 0.01,0.0109 1.11351,0.03 2.4523,0.0423 l 2.43417,0.0225 0.0271,0.1905 0.0271,0.1905 h -2.71788 c -2.10497,0 -2.7271,-0.024 -2.75874,-0.10647 z m -11.61667,-6.6676 c -0.15134,-0.32547 -0.27516,-0.62545 -0.27516,-0.66663 0,-0.0808 -0.28631,-0.17627 -0.53416,-0.17814 -0.0842,-6.8e-4 -0.12926,0.0375 -0.10004,0.0848 0.0591,0.0957 -0.94722,1.18404 -1.09485,1.18404 -0.14682,0 -0.86818,-0.50429 -2.99754,-2.09553 -1.08622,-0.81171 -1.99006,-1.46071 -2.00854,-1.44222 -0.0185,0.0185 0.38445,0.55715 0.89543,1.19704 0.51097,0.63989 0.92903,1.20049 0.92903,1.24579 0,0.0453 -0.16192,1.7e-4 -0.35983,-0.10027 -0.81685,-0.41461 -10.09485,-5.65002 -11.176,-6.30642 -0.22119,-0.13429 -0.40217,-0.30684 -0.40217,-0.38343 0,-0.14308 1.11834,-1.47219 3.72219,-4.42373 0.81665,-0.9257 1.90378,-2.16396 2.41584,-2.75169 0.51207,-0.58773 1.14085,-1.28967 1.3973,-1.55987 l 0.46627,-0.49127 0.80404,0.44425 c 0.44222,0.24434 1.43268,0.77022 2.20103,1.16861 0.76835,0.39839 1.397,0.75814 1.397,0.79943 0,0.0413 -0.40005,0.2917 -0.889,0.55645 -0.48895,0.26475 -1.1938,0.65447 -1.56633,0.86605 -0.37254,0.21159 -1.03929,0.58531 -1.48167,0.8305 -0.44238,0.24519 -1.50918,0.83672 -2.37067,1.31451 -0.86148,0.47779 -2.29023,1.25692 -3.175,1.73141 -1.60141,0.85881 -1.9847,1.09759 -1.56633,0.97578 0.11642,-0.0339 1.44992,-0.49892 2.96333,-1.03339 1.51342,-0.53448 3.28507,-1.15565 3.937,-1.38039 0.65194,-0.22475 2.04126,-0.71593 3.08738,-1.09153 1.04613,-0.37559 1.93094,-0.654 1.96625,-0.61869 0.0353,0.0353 -0.39442,1.07297 -0.95495,2.3059 -1.32109,2.90582 -2.19368,4.87306 -2.19368,4.94562 0,0.17785 0.36876,-0.34515 2.30065,-3.263 1.17041,-1.76773 2.16792,-3.20075 2.21668,-3.1845 0.0488,0.0163 0.26908,0.88426 0.48958,1.9289 1.19368,5.65511 1.51719,7.12934 1.59299,7.25932 0.11693,0.20048 0.11329,-0.0135 -0.0325,-1.91532 -0.0667,-0.87009 -0.16436,-2.34398 -0.217,-3.27532 -0.0526,-0.93134 -0.13195,-2.14683 -0.17623,-2.7011 -0.0443,-0.55427 -0.0623,-1.02601 -0.04,-1.04831 0.0223,-0.0223 0.45146,0.22527 0.95369,0.55015 3.7577,2.43083 5.464,3.50264 5.53726,3.47822 0.0849,-0.0283 -2.79607,-2.85126 -4.86615,-4.76809 -0.59878,-0.55446 -1.05115,-1.02062 -1.00526,-1.03592 0.0459,-0.0153 0.70725,0.12909 1.4697,0.32086 0.76244,0.19177 2.20541,0.53421 3.20659,0.76098 1.00118,0.22677 2.4087,0.54674 3.12782,0.71104 0.71912,0.16431 1.32458,0.28163 1.34548,0.26074 0.0595,-0.0595 -0.63888,-0.33935 -2.65297,-1.06301 -1.02446,-0.3681 -2.60561,-0.94083 -3.51366,-1.27273 -0.90805,-0.33191 -2.01295,-0.73522 -2.45534,-0.89625 -0.44238,-0.16102 -0.83232,-0.32015 -0.86652,-0.35362 -0.0342,-0.0335 0.36584,-0.36709 0.889,-0.74137 2.05556,-1.47063 2.5634,-1.84709 2.65254,-1.96635 0.0946,-0.12651 0.53082,0.24644 3.89091,3.32624 0.56113,0.51434 1.61841,1.48294 2.3495,2.15246 0.73108,0.66951 1.32924,1.27456 1.32924,1.34455 0,0.15797 -2.25887,3.17086 -2.92512,3.90154 l -0.48937,0.5367 -0.38826,-0.0582 c -0.21354,-0.032 -0.41158,-0.0958 -0.44009,-0.14184 -0.0707,-0.11408 -0.49419,0.42881 -1.43217,1.83595 -0.71795,1.07705 -0.95697,1.34957 -2.6164,2.98314 -1.00444,0.98879 -1.86436,1.79804 -1.91092,1.79833 -0.0466,2.6e-4 -0.20849,-0.26576 -0.35984,-0.59122 z m -2.51719,-16.53049 c -0.038,-0.16504 -0.43047,-2.91648 -0.73358,-5.14353 -0.18756,-1.37804 -0.22897,-1.27963 -0.4988,1.18534 -0.22548,2.05987 -0.4759,3.97856 -0.52577,4.02843 -0.0344,0.0344 -1.61593,-0.10059 -3.57749,-0.30529 -0.4191,-0.0437 -0.80962,-0.0802 -0.86783,-0.081 -0.0582,-7.9e-4 -0.10583,-0.0338 -0.10583,-0.0734 0,-0.11253 4.66069,-5.77445 5.23012,-6.35368 0.0987,-0.10042 0.17459,-0.0626 0.51206,0.25553 0.21788,0.20538 0.7581,0.71568 1.20048,1.134 0.44239,0.41832 1.71032,1.61477 2.81764,2.65879 1.10732,1.04402 1.98362,1.9237 1.94733,1.95485 -0.0363,0.0311 -0.58032,0.1419 -1.20897,0.24609 -0.62865,0.1042 -1.73619,0.3008 -2.4612,0.4369 -1.60274,0.30086 -1.6714,0.30312 -1.72816,0.0569 z" +
                "c3.958,0,6.245,2.551,7.124,4.486L56.615,56.844z");*/


        ImageLogoUtils imageLogoUtils = new ImageLogoUtils();
        Button buttonWithSVG = imageLogoUtils.createButtonGrad();
        imageLogoUtils.startAnimation();

        root.getChildren().addAll(buttonWithSVG);
        root.layoutBoundsProperty().addListener((observableValue, oldBounds, newBounds) -> {
                    double size = Math.max(MIN_BUTTON_SIZE, Math.min(newBounds.getWidth(), newBounds.getHeight()));
                    buttonWithSVG.setPrefSize(size, size);
                }
        );
        root.setPrefSize(800,600);
        Scene scene = new Scene(root);

        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(GuiApp.class.getResource("/org/lazer/css/custom-path-button.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
