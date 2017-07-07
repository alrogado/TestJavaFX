package org.testjfx.conf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.typesafe.config.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testjfx.GuiApp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by alrogado on 6/20/17.
 */
public class ApplicationSettings {

    private static Logger logger = LoggerFactory.getLogger(ApplicationSettings.class);

    // Version and copyright information
    static final String APPLICATION_NAME = "Test JavaFX LS";
    static final String COPYRIGHT = "@Test JavaFX LS 2017";
    static final String VERSION = "V1.0";

    // Encryption/decryption o fconfiguration files
    static final String ENCRYPTER_ALGORITHM = "PBEWithMD5AndDES";
    static final byte[] KEY_GENERATION_SALT = new byte[]{(byte) 0x1A, (byte) 0xAF, (byte) 0x13, (byte) 0xF5, (byte) 0x12, (byte) 0x01, (byte) 0xAA, (byte) 0xDC};
    static final int KEY_GENERATION_ITERATIONS = 16;
    static final char[] SECRET_KEY = "Test JavaFx ApplicationSettings Files".toCharArray();

    public static Locale LOCALE = new Locale("es");
    public static ResourceBundle APPBUNDLE = ResourceBundle.getBundle("testjfx", LOCALE);
    public static int WIDTH = 1100;
    public static int HEIGHT = 800;

    public static String fileConfName = "/applicationSettings.conf";

    private static ApplicationSettings instance = new ApplicationSettings().loadConf();

    @JsonProperty private long startupDelay;
    @JsonProperty private String serialNumber;
    @JsonProperty private String maintenancePassword;
    @JsonProperty private String mainMaintenancePassword;

    @JsonProperty private String localeAvailable;
    @JsonProperty private String datePattern;

    @JsonProperty private int setpointMinFullTemperature;
    @JsonProperty private int setpointMinTemperature;
    @JsonProperty private int setpointMaxTemperature;
    @JsonProperty private int setpointMaxFullTemperature;

    @JsonProperty private String workModeName;

    @JsonProperty private boolean fulScreenEnabled;
    @JsonProperty private int widthScreen;
    @JsonProperty private int heightScreen;

    @JsonProperty private int commRetryInterval;
    @JsonProperty private String commDriver;
    @JsonProperty private String commDriverArguments;
    @JsonProperty private int commMessageWaitingTimeout;
    @JsonProperty private int commAcknowledgeWaitingTimeout;
    @JsonProperty private int commAliveSignalInterval;

    @JsonProperty private int toolsAccessClicks;
    @JsonProperty private int toolsAccessTime;

    @JsonProperty private int shutdownMessageTime;
    @JsonProperty private int errorShutdownMessageTime;
    @JsonProperty private int workIdleTimeout;

    @JsonProperty private int activationSignalTrigger;
    @JsonProperty private int activationSignalPedal;
    @JsonProperty private int activationSignalInterlock;
    @JsonProperty private int activationSignalKey;
    @JsonProperty private int activationSignalFlow;
    @JsonProperty private int activationSignalTankLowLevel;
    @JsonProperty private int activationSignalFlowError;
    @JsonProperty private int activationSignalHighCompression;
    @JsonProperty private int activationSignalLowCompression;

    @JsonProperty private double pulseVolume = 50;
    @JsonProperty private double screenVolume = 50;
    @JsonProperty private boolean depositFillEnabled = false;
    @JsonProperty private boolean pedalEnabled = true;
    @JsonProperty private boolean triggerEnabled = false;

    public static String getBundleString(String rscBundle) {
        String res = rscBundle;
        try {
            res = APPBUNDLE.getString(rscBundle);
        } catch (Exception e) {
            logger.warn("Error loading resources ", e);
        }
        return res;
    }

    public static ApplicationSettings getInstance() {
        return instance;
    }

    public int getSetpointMinFullTemperature() {
        return setpointMinFullTemperature;
    }

    public void setSetpointMinFullTemperature(int setpointMinFullTemperature) {
        this.setpointMinFullTemperature = setpointMinFullTemperature;
    }

    public int getSetpointMaxFullTemperature() {
        return setpointMaxFullTemperature;
    }

    public void setSetpointMaxFullTemperature(int setpointMaxFullTemperature) {
        this.setpointMaxFullTemperature = setpointMaxFullTemperature;
    }

    public String getWorkModeName() {
        return workModeName;
    }

    public void setWorkModeName(String workModeName) {
        this.workModeName = workModeName;
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

    public long getStartupDelay() {
        return startupDelay;
    }

    public void setStartupDelay(long startupDelay) {
        this.startupDelay = startupDelay;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMaintenancePassword() {
        return maintenancePassword;
    }

    public void setMaintenancePassword(String maintenancePassword) {
        this.maintenancePassword = maintenancePassword;
    }

    public String getMainMaintenancePassword() {
        return mainMaintenancePassword;
    }

    public void setMainMaintenancePassword(String mainMaintenancePassword) {
        this.mainMaintenancePassword = mainMaintenancePassword;
    }

    public String getLocaleAvailable() {
        return localeAvailable;
    }

    public void setLocaleAvailable(String localeAvailable) {
        this.localeAvailable = localeAvailable;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    public int getSetpointMaxTemperature() {
        return setpointMaxTemperature;
    }

    public void setSetpointMaxTemperature(int setpointMaxTemperature) {
        this.setpointMaxTemperature = setpointMaxTemperature;
    }

    public int getSetpointMinTemperature() {
        return setpointMinTemperature;
    }

    public void setSetpointMinTemperature(int setpointMinTemperature) {
        this.setpointMinTemperature = setpointMinTemperature;
    }

    public boolean isFulScreenEnabled() {
        return fulScreenEnabled;
    }

    public void setFulScreenEnabled(boolean fulScreenEnabled) {
        this.fulScreenEnabled = fulScreenEnabled;
    }

    public int getWidthScreen() {
        return widthScreen;
    }

    public void setWidthScreen(int widthScreen) {
        this.widthScreen = widthScreen;
    }

    public int getHeightScreen() {
        return heightScreen;
    }

    public void setHeightScreen(int heightScreen) {
        this.heightScreen = heightScreen;
    }

    public int getCommRetryInterval() {
        return commRetryInterval;
    }

    public void setCommRetryInterval(int commRetryInterval) {
        this.commRetryInterval = commRetryInterval;
    }

    public String getCommDriver() {
        return commDriver;
    }

    public void setCommDriver(String commDriver) {
        this.commDriver = commDriver;
    }

    public String getCommDriverArguments() {
        return commDriverArguments;
    }

    public void setCommDriverArguments(String commDriverArguments) {
        this.commDriverArguments = commDriverArguments;
    }

    public int getCommMessageWaitingTimeout() {
        return commMessageWaitingTimeout;
    }

    public void setCommMessageWaitingTimeout(int commMessageWaitingTimeout) {
        this.commMessageWaitingTimeout = commMessageWaitingTimeout;
    }

    public int getCommAcknowledgeWaitingTimeout() {
        return commAcknowledgeWaitingTimeout;
    }

    public void setCommAcknowledgeWaitingTimeout(int commAcknowledgeWaitingTimeout) {
        this.commAcknowledgeWaitingTimeout = commAcknowledgeWaitingTimeout;
    }

    public int getCommAliveSignalInterval() {
        return commAliveSignalInterval;
    }

    public void setCommAliveSignalInterval(int commAliveSignalInterval) {
        this.commAliveSignalInterval = commAliveSignalInterval;
    }

    public int getToolsAccessClicks() {
        return toolsAccessClicks;
    }

    public void setToolsAccessClicks(int toolsAccessClicks) {
        this.toolsAccessClicks = toolsAccessClicks;
    }

    public int getToolsAccessTime() {
        return toolsAccessTime;
    }

    public void setToolsAccessTime(int toolsAccessTime) {
        this.toolsAccessTime = toolsAccessTime;
    }

    public int getShutdownMessageTime() {
        return shutdownMessageTime;
    }

    public void setShutdownMessageTime(int shutdownMessageTime) {
        this.shutdownMessageTime = shutdownMessageTime;
    }

    public int getErrorShutdownMessageTime() {
        return errorShutdownMessageTime;
    }

    public void setErrorShutdownMessageTime(int errorShutdownMessageTime) {
        this.errorShutdownMessageTime = errorShutdownMessageTime;
    }

    public int getWorkIdleTimeout() {
        return workIdleTimeout;
    }

    public void setWorkIdleTimeout(int workIdleTimeout) {
        this.workIdleTimeout = workIdleTimeout;
    }

    public int getActivationSignalTrigger() {
        return activationSignalTrigger;
    }

    public void setActivationSignalTrigger(int activationSignalTrigger) {
        this.activationSignalTrigger = activationSignalTrigger;
    }

    public int getActivationSignalPedal() {
        return activationSignalPedal;
    }

    public void setActivationSignalPedal(int activationSignalPedal) {
        this.activationSignalPedal = activationSignalPedal;
    }

    public int getActivationSignalInterlock() {
        return activationSignalInterlock;
    }

    public void setActivationSignalInterlock(int activationSignalInterlock) {
        this.activationSignalInterlock = activationSignalInterlock;
    }

    public int getActivationSignalKey() {
        return activationSignalKey;
    }

    public void setActivationSignalKey(int activationSignalKey) {
        this.activationSignalKey = activationSignalKey;
    }

    public int getActivationSignalFlow() {
        return activationSignalFlow;
    }

    public void setActivationSignalFlow(int activationSignalFlow) {
        this.activationSignalFlow = activationSignalFlow;
    }

    public int getActivationSignalTankLowLevel() {
        return activationSignalTankLowLevel;
    }

    public void setActivationSignalTankLowLevel(int activationSignalTankLowLevel) {
        this.activationSignalTankLowLevel = activationSignalTankLowLevel;
    }

    public int getActivationSignalFlowError() {
        return activationSignalFlowError;
    }

    public void setActivationSignalFlowError(int activationSignalFlowError) {
        this.activationSignalFlowError = activationSignalFlowError;
    }

    public int getActivationSignalHighCompression() {
        return activationSignalHighCompression;
    }

    public void setActivationSignalHighCompression(int activationSignalHighCompression) {
        this.activationSignalHighCompression = activationSignalHighCompression;
    }

    public int getActivationSignalLowCompression() {
        return activationSignalLowCompression;
    }

    public void setActivationSignalLowCompression(int activationSignalLowCompression) {
        this.activationSignalLowCompression = activationSignalLowCompression;
    }


    public static ApplicationSettings loadConf() {
        final InputStream file = ApplicationSettings.class.getResourceAsStream(fileConfName);
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory()); // jackson databind
        try {
            instance = mapper.readValue(file, ApplicationSettings.class);
        } catch (IOException e) {
            logger.error("Problem loading Application Conf Values ", e);
        }
        return instance;
    }


    public static void main(String args[]) throws IOException {
        System.out.println("DatePattern:" + ApplicationSettings.getInstance().getDatePattern());
        System.out.println("LocaleAvailable:" + ApplicationSettings.getInstance().getLocaleAvailable());
    }

}
