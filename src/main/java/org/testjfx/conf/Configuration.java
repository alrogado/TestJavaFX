package org.testjfx.conf;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by alrogado on 6/20/17.
 */
public class Configuration {

    static final String FILE_NAME_USER_CONFIG = "userconfig.properties";
    static final String FILE_NAME_CONFIGURATION = "/config.properties";

    // Version and copyright information
    static final String APPLICATION_NAME = "Sapphire LS";
    static final String COPYRIGHT = "@Sapphire LS 2014";
    static final String VERSION = "V3.0";

    // Encryption/decryption o fconfiguration files
    static final String ENCRYPTER_ALGORITHM = "PBEWithMD5AndDES";
    static final byte[] KEY_GENERATION_SALT = new byte[]{(byte) 0x1A, (byte) 0xAF, (byte) 0x13, (byte) 0xF5, (byte) 0x12, (byte) 0x01, (byte) 0xAA, (byte) 0xDC};
    static final int KEY_GENERATION_ITERATIONS = 16;
    static final char[] SECRET_KEY = "ShappireLS Configuration Files".toCharArray();

    // Default values
    static final String DEFAULT_LOG_INFO = "true";
    static final String DEFAULT_LOG_DEBUG = "false";
    static final String DEFAULT_LOG_CONSOLE = "false";

    static final String DEFAULT_STARTUP_DELAY = "10000";

    static final String DEFAULT_MAINTENANCE_PASSWORD = "12345";
    static final String DEFAULT_MAIN_MAINTENANCE_PASSWORD = "56789";

    static final String DEFAULT_LOCALE_AVAILABLE = "en,es";
    static final String DEFAULT_SKIN_RESOURCES_LOCATION = "";
    static final String DEFAULT_DATE_PATTERN = "EEEEE  dd/MM/yyyy  HH:mm:ss";

    static final String DEFAULT_TIMEZONE = "Europe/Madrid";
    static final String DEFAULT_LOCALE = "es";
    static final String DEFAULT_VOLUME = "50";
    static final String DEFAULT_SELECTED_MODE = "0";
    static final String DEFAULT_SELECTED_FREQUENCY = "0";
    static final String DEFAULT_SELECTED_FLUENCE = "0";
    static final String DEFAULT_MACHINE_CODE = "";

    static final String DEFAULT_COMM_ALIVE_INTERVAL = "1000";
    static final String DEFAULT_TOOLS_ACCESS_CLICKS = "5";
    static final String DEFAULT_TOOLS_ACCESS_TIME = "3000";
    static final String DEFAULT_SHUTDOWN_MESSAGE_TIME = "3000";
    static final String DEFAULT_ERROR_SHUTDOWN_MESSAGE_TIME = "3000";
    static final String DEFAULT_WORK_IDLE_TIMEOUT = "900";

    static final String KEY_STARTUP_DELAY = "startupDelay";

    static final String KEY_SERIAL_NUMBER = "serialNumber";
    static final String KEY_MAINTENANCE_PASSWORD = "maintenancePassword";
    static final String KEY_MAIN_MAINTENANCE_PASSWORD = "mainMaintenancePassword";

    static final String KEY_LOCALE_AVAILABLE = "locale.available";
    static final String KEY_SKIN_RESOURCES_LOCATION = "skin.resources.location";

    static final String KEY_DATE_PATTERN = "date.pattern";

    static final String KEY_SETPOINT_MAX_TEMPERATURE = "setpoint.maxTemperature";
    static final String KEY_SETPOINT_MIN_TEMPERATURE = "setpoint.minTemperature";

    static final String KEY_SHORTPULSE_MINFREQUENCY = "mode.shortPulse.minFrequency";
    static final String KEY_SHORTPULSE_MAXFREQUENCY = "mode.shortPulse.maxFrequency";
    static final String KEY_SHORTPULSE_MINFLUENCE = "mode.shortPulse.minFluence";
    static final String KEY_SHORTPULSE_MAXFLUENCE = "mode.shortPulse.maxFluence";

    static final String KEY_SWEEP_MINFREQUENCY = "mode.sweep.minFrequency";
    static final String KEY_SWEEP_MAXFREQUENCY = "mode.sweep.maxFrequency";
    static final String KEY_SWEEP_MINFLUENCE = "mode.sweep.minFluence";
    static final String KEY_SWEEP_MAXFLUENCE = "mode.sweep.maxFluence";

    static final String KEY_COMM_RETRY_INTERVAL = "comm.retry.interval";

    static final String KEY_FULLSCREEN_ENABLED = "ui.fullscreen";
    static final String KEY_NONFULLSCREEN_SIZE = "ui.nonFullscreenSize";
    static final String KEY_HIDE_CURSOR = "ui.hideCursor";

    static final String KEY_COMM_DRIVER = "comm.driver";
    static final String KEY_COMM_DRIVER_ARGUMENTS = "comm.driver.arguments";
    static final String KEY_COMM_ACKNOWNLEDGE_WAITING_TIMEOUT = "comm.acknowledgeWaitingTimeout";
    static final String KEY_COMM_MESSAGE_WAITING_TIMEOUT = "comm.messageWaitingTimeout";

    // User configuration keys
    static final String KEY_TIMEZONE = "timezone";
    static final String KEY_LOCALE = "locale";
    static final String KEY_PULSE_VOLUME = "volume.pulse";
    static final String KEY_SCREEN_VOLUME = "volume.screen";
    static final String KEY_MACHINE_CODE = "machineCode";

    // Screen behaviour keys
    static final String KEY_COMM_ALIVE_INTERVAL = "comm.aliveSignalInterval";
    static final String KEY_TOOLS_ACCESS_CLICKS = "tools.accessClicks";
    static final String KEY_TOOLS_ACCESS_TIME = "tools.accessTime";
    static final String KEY_SHUTDOWN_MESSAGE_TIME = "shutdownMessageTime";
    static final String KEY_ERROR_SHUTDOWN_MESSAGE_TIME = "errorShutdownMessageTime";
    static final String KEY_WORK_IDLE_TIMEOUT = "workIdleTimeout";

    // Signal activation keys
    static final String KEY_SIGNAL_ACTIVATION_TRIGGER = "probe.trigger.activation";
    static final String KEY_SIGNAL_ACTIVATION_PEDAL = "probe.pedal.activation";
    static final String KEY_SIGNAL_ACTIVATION_INTERLOCK = "probe.interlock.activation";
    static final String KEY_SIGNAL_ACTIVATION_KEY = "probe.key.activation";
    static final String KEY_SIGNAL_ACTIVATION_FLOW = "probe.flow.activation";
    static final String KEY_SIGNAL_ACTIVATION_TANK_LOW_LEVEL = "error.tankLowLevel.activation";
    static final String KEY_SIGNAL_ACTIVATION_FLOW_ERROR = "error.flow.activation";
    static final String KEY_SIGNAL_ACTIVATION_HIGH_COMPRESSION = "error.highCompression.activation";
    static final String KEY_SIGNAL_ACTIVATION_LOW_COMPRESSION = "error.lowCompression.activation";


    public static Locale LOCALE = new Locale("es");
    public static ResourceBundle APPBUNDLE = ResourceBundle.getBundle("testjfx", LOCALE);
    public static int WIDTH = 1100;
    public static int HEIGHT = 800;


    private static double pulseVolume = 50;
    private static double screenVolume = 50;
    private static double depositMinValue = 15;
    private static double depositMaxValue = 36;
    private static double tipMinValue = 15;
    private static double tipMaxValue = 40;

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

    public static double getDepositMinValue() {
        return depositMinValue;
    }

    public static void setDepositMinValue(double depositMinValue) {
        Configuration.depositMinValue = depositMinValue;
    }

    public static double getDepositMaxValue() {
        return depositMaxValue;
    }

    public static void setDepositMaxValue(double depositMaxValue) {
        Configuration.depositMaxValue = depositMaxValue;
    }

    public static double getTipMaxValue() {
        return tipMaxValue;
    }

    public static void setTipMaxValue(double tipMaxValue) {
        Configuration.tipMaxValue = tipMaxValue;
    }

    public static double getTipMinValue() {
        return tipMinValue;
    }

    public static void setTipMinValue(double tipMinValue) {
        Configuration.tipMinValue = tipMinValue;
    }
}
