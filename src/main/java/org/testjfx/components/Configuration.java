package org.testjfx.components;

/**
 * Created by alrogado on 6/20/17.
 */
public class Configuration {
    private static double pulseVolume;
    private static double screenVolume = 50;

    public static double getPulseVolume() {
        return pulseVolume;
    }

    public static void setPulseVolume(double pulseVolume) {
        Configuration.pulseVolume = pulseVolume;
    }

    public static double getScreenVolume() {
        return screenVolume;
    }

    public static void setScreenVolume(double screenVolume) {
        Configuration.screenVolume = screenVolume;
    }
}
