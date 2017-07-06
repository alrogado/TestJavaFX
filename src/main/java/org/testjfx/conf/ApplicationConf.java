package org.testjfx.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.typesafe.config.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testjfx.GuiApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by alrogado on 6/20/17.
 */
public class ApplicationConf {

    private static Logger logger = LoggerFactory.getLogger(ApplicationConf.class);

    static final String FILE_NAME_USER_CONFIG = "userconfig.properties";
    static final String FILE_NAME_CONFIGURATION = "/config.properties";

    // Version and copyright information
    static final String APPLICATION_NAME = "Test JavaFX LS";
    static final String COPYRIGHT = "@Test JavaFX LS 2014";
    static final String VERSION = "V3.0";

    // Encryption/decryption o fconfiguration files
    static final String ENCRYPTER_ALGORITHM = "PBEWithMD5AndDES";
    static final byte[] KEY_GENERATION_SALT = new byte[]{(byte) 0x1A, (byte) 0xAF, (byte) 0x13, (byte) 0xF5, (byte) 0x12, (byte) 0x01, (byte) 0xAA, (byte) 0xDC};
    static final int KEY_GENERATION_ITERATIONS = 16;
    static final char[] SECRET_KEY = "Test JavaFx ApplicationConf Files".toCharArray();

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
    public static String fileName = System.getProperty("user.dir")+"\\application.conf";
    public static Config config;
    private static ApplicationConf instance = new ApplicationConf();


    private double pulseVolume = 50;
    private double screenVolume = 50;
    private double depositMinValue = 15;
    private double depositMaxValue = 36;
    private double tipMinValue = 15;
    private double tipMaxValue = 40;
    private String password = "111";
    private boolean depositFillEnabled = false;
    private boolean pedalEnabled = true;
    private boolean triggerEnabled = false;
    private long aliveInterval = 300;

    public static String getBundleString(String rscBundle) {
        String res = rscBundle;
        try {
            res = APPBUNDLE.getString(rscBundle);
        } catch (Exception e) {
            //logger.e
        }
        return res;
    }

    public static ApplicationConf getInstance() {
        return instance;
    }

    public double getPulseVolume() {
        return pulseVolume;
    }

    public void setPulseVolume(double pulseVolume) {
        this.pulseVolume = pulseVolume;
    }

    public double getScreenVolume() {
        return screenVolume;
    }

    public void setScreenVolume(double screenVolume) {
        this.screenVolume = screenVolume;
    }

    public double getDepositMinValue() {
        return depositMinValue;
    }

    public void setDepositMinValue(double depositMinValue) {
        this.depositMinValue = depositMinValue;
    }

    public double getDepositMaxValue() {
        return depositMaxValue;
    }

    public void setDepositMaxValue(double depositMaxValue) {
        this.depositMaxValue = depositMaxValue;
    }

    public double getTipMaxValue() {
        return tipMaxValue;
    }

    public void setTipMaxValue(double tipMaxValue) {
        this.tipMaxValue = tipMaxValue;
    }

    public double getTipMinValue() {
        return tipMinValue;
    }

    public void setTipMinValue(double tipMinValue) {
        this.tipMinValue = tipMinValue;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getDepositFillEnabled() {
        return depositFillEnabled;
    }

    public boolean isDepositFillEnabled() {
        return depositFillEnabled;
    }

    public void setDepositFillEnabled(boolean depositFillEnabled) {
        this.depositFillEnabled = depositFillEnabled;
    }

    public boolean getPedalEnabled() {
        return pedalEnabled;
    }

    public boolean isPedalEnabled() {
        return pedalEnabled;
    }

    public void setPedalEnabled(boolean pedalEnabled) {
        this.pedalEnabled = pedalEnabled;
    }

    public boolean getTriggerEnabled() {
        return triggerEnabled;
    }

    public boolean isTriggerEnabled() {
        return triggerEnabled;
    }

    public void setTriggerEnabled(boolean triggerEnabled) {
        this.triggerEnabled = triggerEnabled;
    }

    public long getAliveInterval() {
        return aliveInterval;
    }

    public void setAliveInterval(long aliveInterval) {
        this.aliveInterval = aliveInterval;
    }

    public String getConfigValue(String key) {
        String result = "";
        if (config != null){
            result = config.getString(key);
        }else {
            logger.error("No se ha podido cargar el fichero application.conf");
            System.exit(0);
        }
        return result;
    }

    public void setConfigValue(String key, Object value) {
        ConfigRenderOptions renderOptions = ConfigRenderOptions.defaults()
                .setComments(true)
                .setFormatted(true)
                .setOriginComments(false)
                .setJson(false);
        config = config.withValue(key, ConfigValueFactory.fromAnyRef(value));
        config.root().render(renderOptions);

        String configData = config.root().render(renderOptions);
        try {

            FileWriter writer = new FileWriter(fileName);
            writer.write(configData);
            writer.close();
        } catch (IOException e) {
            logger.warn("failed to write file, message={}", e.getMessage());
        }
    }

    static {
        File file = new File(fileName);
        if(file.exists()) {
            config = ConfigFactory.parseFile(file);
        }else{
            //se carga con las propiedades por defecto del fichero de configuración del aplicativo si no se cambian
            // así que no se escribe si q no hay cambios
            ConfigParseOptions configParseOptions = ConfigParseOptions.defaults();
            //.appendIncluder(ConfigIncluder)
            config = ConfigFactory.defaultApplication();
        }
    }

    public static void configStaticValues() {
        if (config != null) {
            String localeStr = config.getString("application.locale");
            if (localeStr != null) {
                URL resource = GuiApp.class.getResource("/testjfx_" + localeStr + ".properties");
                if (resource == null) {
                    logger.warn("el valor del parametro de pais/idioma '" + localeStr + "' no esta dado de alta como fichero. Se toma el valor por defecto del lenguaje de la aplicacion 'es'.");
                } else {
                    LOCALE = new Locale(localeStr);
                    APPBUNDLE = ResourceBundle.getBundle("testjfx", LOCALE);
                }
            }
        }
    }

    public Double getConfigValueAsDouble(String key) {
        return config.getDouble(key);
    }

    public static ApplicationConf loadConf() {
        final InputStream file = PredefinedWorkModes.class.getResourceAsStream(fileName);
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory()); // jackson databind
        /*
        Now this is not needed
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addKeyDeserializer(FreqFluPair.class, new FreqFluPairKeyDeserializer());
        simpleModule.addKeyDeserializer(Pulse.class, new PulseKeyDeserializer());
        mapper.registerModule(simpleModule);*/
        try {
            instance = mapper.readValue(file, ApplicationConf.class);
        } catch (IOException e) {
            logger.error("Problem loading predefined workmodes", e);
        }
        return instance;
    }




}
