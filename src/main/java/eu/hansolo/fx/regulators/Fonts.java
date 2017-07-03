/*
 * Copyright (c) 2016 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hansolo.fx.regulators;

import javafx.scene.text.Font;


/**
 * Created by hansolo on 01.03.16.
 */
public class Fonts {
    private static final String ROBOTO_LIGHT_NAME;
    private static final String ROBOTO_MEDIUM_NAME;
    private static final String ROBOTO_BOLD_NAME;
    private static final String ROBOTO_BLACK_NAME;

    private static String robotoLightName;
    private static String robotoMediumName;
    private static String robotoBoldName;
    private static String robotoBlackName;

    static {
        try {
            robotoLightName  = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/fx/regulators/Roboto-Light.ttf"), 10).getName();
            robotoMediumName = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/fx/regulators/Roboto-Medium.ttf"), 10).getName();
            robotoBoldName = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/fx/regulators/Roboto-Bold.ttf"), 10).getName();
            robotoBlackName = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/fx/regulators/Roboto-Black.ttf"), 10).getName();
        } catch (Exception exception) { }
        ROBOTO_LIGHT_NAME  = robotoLightName;
        ROBOTO_MEDIUM_NAME = robotoMediumName;
        ROBOTO_BOLD_NAME = robotoBoldName;
        ROBOTO_BLACK_NAME = robotoBlackName;
    }


    // ******************** Methods *******************************************
    public static Font robotoLight(final double SIZE) { return new Font(ROBOTO_LIGHT_NAME, SIZE); }
    public static Font robotoMedium(final double SIZE) { return new Font(ROBOTO_MEDIUM_NAME, SIZE); }
    public static Font robotoBold(final double SIZE) { return new Font(ROBOTO_BOLD_NAME, SIZE); }
    public static Font robotoBlack(final double SIZE) { return new Font(ROBOTO_BLACK_NAME, SIZE); }
}
