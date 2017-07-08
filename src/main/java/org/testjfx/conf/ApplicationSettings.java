package org.testjfx.conf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import javafx.beans.property.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by alrogado on 6/20/17.
 */
public class ApplicationSettings extends Settings{

    private static Logger logger = LoggerFactory.getLogger(ApplicationSettings.class);

    // Version and copyright information
    static final String APPLICATION_NAME = "Test JavaFX LS";
    static final String COPYRIGHT = "@Test JavaFX LS 2017";
    static final String VERSION = "V1.0";

    public static Locale LOCALE = new Locale("es");
    public static ResourceBundle APPBUNDLE = ResourceBundle.getBundle("testjfx", LOCALE);

    public static int WIDTH = 1100;
    public static int HEIGHT = 800;

    public String fileName = "applicationSettings.conf";

    static ApplicationSettings instance = new ApplicationSettings().loadConf(ApplicationSettings.class);

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
    @JsonProperty private double pulseVolume;
    @JsonProperty private double screenVolume;
    @JsonProperty private boolean depositFillEnabled;
    @JsonProperty private boolean pedalEnabled;
    @JsonProperty private boolean triggerEnabled;

    LongProperty startupDelayProperty = new SimpleLongProperty(startupDelay);
    StringProperty serialNumberProperty = new SimpleStringProperty(serialNumber);
    StringProperty maintenancePasswordProperty = new SimpleStringProperty(maintenancePassword);
    StringProperty mainMaintenancePasswordProperty = new SimpleStringProperty(mainMaintenancePassword);
    StringProperty localeAvailableProperty = new SimpleStringProperty(localeAvailable);
    StringProperty datePatternProperty = new SimpleStringProperty(datePattern);
    IntegerProperty setpointMinFullTemperatureProperty = new SimpleIntegerProperty(setpointMinFullTemperature);
    IntegerProperty setpointMinTemperatureProperty = new SimpleIntegerProperty(setpointMinTemperature);
    IntegerProperty setpointMaxTemperatureProperty = new SimpleIntegerProperty(setpointMaxTemperature);
    IntegerProperty setpointMaxFullTemperatureProperty = new SimpleIntegerProperty(setpointMaxFullTemperature);
    StringProperty workModeNameProperty = new SimpleStringProperty(workModeName);
    BooleanProperty fulScreenEnabledProperty = new SimpleBooleanProperty(fulScreenEnabled);
    IntegerProperty widthScreenProperty = new SimpleIntegerProperty(widthScreen);
    IntegerProperty heightScreenProperty = new SimpleIntegerProperty(heightScreen);
    IntegerProperty commRetryIntervalProperty = new SimpleIntegerProperty(commRetryInterval);
    StringProperty commDriverProperty = new SimpleStringProperty(commDriver);
    StringProperty commDriverArgumentsProperty = new SimpleStringProperty(commDriverArguments);
    IntegerProperty commMessageWaitingTimeoutProperty = new SimpleIntegerProperty(commMessageWaitingTimeout);
    IntegerProperty commAcknowledgeWaitingTimeoutProperty = new SimpleIntegerProperty(commAcknowledgeWaitingTimeout);
    IntegerProperty commAliveSignalIntervalProperty = new SimpleIntegerProperty(commAliveSignalInterval);
    IntegerProperty toolsAccessClicksProperty = new SimpleIntegerProperty(toolsAccessClicks);
    IntegerProperty toolsAccessTimeProperty = new SimpleIntegerProperty(toolsAccessTime);
    IntegerProperty shutdownMessageTimeProperty = new SimpleIntegerProperty(shutdownMessageTime);
    IntegerProperty errorShutdownMessageTimeProperty = new SimpleIntegerProperty(errorShutdownMessageTime);
    IntegerProperty workIdleTimeoutProperty = new SimpleIntegerProperty(workIdleTimeout);
    IntegerProperty activationSignalTriggerProperty = new SimpleIntegerProperty(activationSignalTrigger);
    IntegerProperty activationSignalPedalProperty = new SimpleIntegerProperty(activationSignalPedal);
    IntegerProperty activationSignalInterlockProperty = new SimpleIntegerProperty(activationSignalInterlock);
    IntegerProperty activationSignalKeyProperty = new SimpleIntegerProperty(activationSignalKey);
    IntegerProperty activationSignalFlowProperty = new SimpleIntegerProperty(activationSignalFlow);
    IntegerProperty activationSignalTankLowLevelProperty = new SimpleIntegerProperty(activationSignalTankLowLevel);
    IntegerProperty activationSignalFlowErrorProperty = new SimpleIntegerProperty(activationSignalFlowError);
    IntegerProperty activationSignalHighCompressionProperty = new SimpleIntegerProperty(activationSignalHighCompression);
    IntegerProperty activationSignalLowCompressionProperty = new SimpleIntegerProperty(activationSignalLowCompression);
    DoubleProperty pulseVolumeProperty = new SimpleDoubleProperty(pulseVolume);
    DoubleProperty screenVolumeProperty = new SimpleDoubleProperty(screenVolume);
    BooleanProperty depositFillEnabledProperty = new SimpleBooleanProperty(depositFillEnabled);
    BooleanProperty pedalEnabledProperty = new SimpleBooleanProperty(pedalEnabled);
    BooleanProperty triggerEnabledProperty = new SimpleBooleanProperty(triggerEnabled);
    {
        startupDelayProperty.addListener(changeListener);
        serialNumberProperty.addListener(changeListener);
        maintenancePasswordProperty.addListener(changeListener);
        mainMaintenancePasswordProperty.addListener(changeListener);
        localeAvailableProperty.addListener(changeListener);
        datePatternProperty.addListener(changeListener);
        setpointMinFullTemperatureProperty.addListener(changeListener);
        setpointMinTemperatureProperty.addListener(changeListener);
        setpointMaxTemperatureProperty.addListener(changeListener);
        setpointMaxFullTemperatureProperty.addListener(changeListener);
        workModeNameProperty.addListener(changeListener);
        fulScreenEnabledProperty.addListener(changeListener);
        widthScreenProperty.addListener(changeListener);
        heightScreenProperty.addListener(changeListener);
        commRetryIntervalProperty.addListener(changeListener);
        commDriverProperty.addListener(changeListener);
        commDriverArgumentsProperty.addListener(changeListener);
        commMessageWaitingTimeoutProperty.addListener(changeListener);
        commAcknowledgeWaitingTimeoutProperty.addListener(changeListener);
        commAliveSignalIntervalProperty.addListener(changeListener);
        toolsAccessClicksProperty.addListener(changeListener);
        toolsAccessTimeProperty.addListener(changeListener);
        shutdownMessageTimeProperty.addListener(changeListener);
        errorShutdownMessageTimeProperty.addListener(changeListener);
        workIdleTimeoutProperty.addListener(changeListener);
        activationSignalTriggerProperty.addListener(changeListener);
        activationSignalPedalProperty.addListener(changeListener);
        activationSignalInterlockProperty.addListener(changeListener);
        activationSignalKeyProperty.addListener(changeListener);
        activationSignalFlowProperty.addListener(changeListener);
        activationSignalTankLowLevelProperty.addListener(changeListener);
        activationSignalFlowErrorProperty.addListener(changeListener);
        activationSignalHighCompressionProperty.addListener(changeListener);
        activationSignalLowCompressionProperty.addListener(changeListener);
        pulseVolumeProperty.addListener(changeListener);
        screenVolumeProperty.addListener(changeListener);
        depositFillEnabledProperty.addListener(changeListener);
        pedalEnabledProperty.addListener(changeListener);
        triggerEnabledProperty.addListener(changeListener);
    }

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
        return (ApplicationSettings)instance;
    }

    public int getSetpointMinFullTemperature() {
        return setpointMinFullTemperatureProperty.get();
    }

    public void setSetpointMinFullTemperature(int setpointMinFullTemperature) {
        this.setpointMinFullTemperatureProperty.set(setpointMinFullTemperature);
    }

    public int getSetpointMaxFullTemperature() {
        return setpointMaxFullTemperatureProperty.get();
    }

    public void setSetpointMaxFullTemperature(int setpointMaxFullTemperature) {
        this.setpointMaxFullTemperatureProperty.set(setpointMaxFullTemperature);
    }

    public String getWorkModeName() {
        return workModeNameProperty.get();
    }

    public void setWorkModeName(String workModeName) {
        this.workModeNameProperty.set(workModeName);
    }

    public double getPulseVolume() {
        return pulseVolumeProperty.get();
    }

    public void setPulseVolume(double pulseVolume) {
        this.pulseVolumeProperty.set(pulseVolume);
    }

    public double getScreenVolume() {
        return screenVolumeProperty.get();
    }

    public void setScreenVolume(double screenVolume) {
        this.screenVolumeProperty.set(screenVolume);
    }


    public boolean getDepositFillEnabled() {
        return depositFillEnabledProperty.get();
    }

    public boolean isDepositFillEnabled() {
        return depositFillEnabledProperty.get();
    }

    public void setDepositFillEnabled(boolean depositFillEnabled) {
        this.depositFillEnabledProperty.set(depositFillEnabled);
    }

    public boolean getPedalEnabled() {
        return pedalEnabledProperty.get();
    }

    public boolean isPedalEnabled() {
        return pedalEnabledProperty.get();
    }

    public void setPedalEnabled(boolean pedalEnabled) {
        this.pedalEnabledProperty.set(pedalEnabled);
    }

    public boolean getTriggerEnabled() {
        return triggerEnabledProperty.get();
    }

    public boolean isTriggerEnabled() {
        return triggerEnabledProperty.get();
    }

    public void setTriggerEnabled(boolean triggerEnabled) {
        this.triggerEnabledProperty.set(triggerEnabled);
    }

    public long getStartupDelay() {
        return startupDelayProperty.get();
    }

    public void setStartupDelay(long startupDelay) {
        this.startupDelayProperty.set(startupDelay);
    }

    public String getSerialNumber() {
        return serialNumberProperty.get();
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumberProperty.set(serialNumber);
    }

    public String getMaintenancePassword() {
        return maintenancePasswordProperty.get();
    }

    public void setMaintenancePassword(String maintenancePassword) {
        this.maintenancePasswordProperty.set(maintenancePassword);
    }

    public String getMainMaintenancePassword() {
        return mainMaintenancePasswordProperty.get();
    }

    public void setMainMaintenancePassword(String mainMaintenancePassword) {
        this.mainMaintenancePasswordProperty.set(mainMaintenancePassword);
    }

    public String getLocaleAvailable() {
        return localeAvailableProperty.get();
    }

    public void setLocaleAvailable(String localeAvailable) {
        this.localeAvailableProperty.set(localeAvailable);
    }

    public String getDatePattern() {
        return datePatternProperty.get();
    }

    public void setDatePattern(String datePattern) {
        this.datePatternProperty.set(datePattern);
    }

    public int getSetpointMaxTemperature() {
        return setpointMaxTemperatureProperty.get();
    }

    public void setSetpointMaxTemperature(int setpointMaxTemperature) {
        this.setpointMaxTemperatureProperty.set(setpointMaxTemperature);
    }

    public int getSetpointMinTemperature() {
        return setpointMinTemperatureProperty.get();
    }

    public void setSetpointMinTemperature(int setpointMinTemperature) {
        this.setpointMinTemperatureProperty.set(setpointMinTemperature);
    }

    public boolean isFulScreenEnabled() {
        return fulScreenEnabledProperty.get();
    }

    public void setFulScreenEnabled(boolean fulScreenEnabled) {
        this.fulScreenEnabledProperty.set(fulScreenEnabled);
    }

    public int getWidthScreen() {
        return widthScreenProperty.get();
    }

    public void setWidthScreen(int widthScreen) {
        this.widthScreenProperty.set(widthScreen);
    }

    public int getHeightScreen() {
        return heightScreenProperty.get();
    }

    public void setHeightScreen(int heightScreen) {
        this.heightScreenProperty.set(heightScreen);
    }

    public int getCommRetryInterval() {
        return commRetryIntervalProperty.get();
    }

    public void setCommRetryInterval(int commRetryInterval) {
        this.commRetryIntervalProperty.set(commRetryInterval);
    }

    public String getCommDriver() {
        return commDriverProperty.get();
    }

    public void setCommDriver(String commDriver) {
        this.commDriverProperty.set(commDriver);
    }

    public String getCommDriverArguments() {
        return commDriverArgumentsProperty.get();
    }

    public void setCommDriverArguments(String commDriverArguments) {
        this.commDriverArgumentsProperty.set(commDriverArguments);
    }

    public int getCommMessageWaitingTimeout() {
        return commMessageWaitingTimeoutProperty.get();
    }

    public void setCommMessageWaitingTimeout(int commMessageWaitingTimeout) {
        this.commMessageWaitingTimeoutProperty.set(commMessageWaitingTimeout);
    }

    public int getCommAcknowledgeWaitingTimeout() {
        return commAcknowledgeWaitingTimeoutProperty.get();
    }

    public void setCommAcknowledgeWaitingTimeout(int commAcknowledgeWaitingTimeout) {
        this.commAcknowledgeWaitingTimeoutProperty.set(commAcknowledgeWaitingTimeout);
    }

    public int getCommAliveSignalInterval() {
        return commAliveSignalIntervalProperty.get();
    }

    public void setCommAliveSignalInterval(int commAliveSignalInterval) {
        this.commAliveSignalIntervalProperty.set(commAliveSignalInterval);
    }

    public int getToolsAccessClicks() {
        return toolsAccessClicksProperty.get();
    }

    public void setToolsAccessClicks(int toolsAccessClicks) {
        this.toolsAccessClicksProperty.set(toolsAccessClicks);
    }

    public int getToolsAccessTime() {
        return toolsAccessTimeProperty.get();
    }

    public void setToolsAccessTime(int toolsAccessTime) {
        this.toolsAccessTimeProperty.set(toolsAccessTime);
    }

    public int getShutdownMessageTime() {
        return shutdownMessageTimeProperty.get();
    }

    public void setShutdownMessageTime(int shutdownMessageTime) {
        this.shutdownMessageTimeProperty.set(shutdownMessageTime);
    }

    public int getErrorShutdownMessageTime() {
        return errorShutdownMessageTimeProperty.get();
    }

    public void setErrorShutdownMessageTime(int errorShutdownMessageTime) {
        this.errorShutdownMessageTimeProperty.set(errorShutdownMessageTime);
    }

    public int getWorkIdleTimeout() {
        return workIdleTimeoutProperty.get();
    }

    public void setWorkIdleTimeout(int workIdleTimeout) {
        this.workIdleTimeoutProperty.set(workIdleTimeout);
    }

    public int getActivationSignalTrigger() {
        return activationSignalTriggerProperty.get();
    }

    public void setActivationSignalTrigger(int activationSignalTrigger) {
        this.activationSignalTriggerProperty.set(activationSignalTrigger);
    }

    public int getActivationSignalPedal() {
        return activationSignalPedalProperty.get();
    }

    public void setActivationSignalPedal(int activationSignalPedal) {
        this.activationSignalPedalProperty.set(activationSignalPedal);
    }

    public int getActivationSignalInterlock() {
        return activationSignalInterlockProperty.get();
    }

    public void setActivationSignalInterlock(int activationSignalInterlock) {
        this.activationSignalInterlockProperty.set(activationSignalInterlock);
    }

    public int getActivationSignalKey() {
        return activationSignalKeyProperty.get();
    }

    public void setActivationSignalKey(int activationSignalKey) {
        this.activationSignalKeyProperty.set(activationSignalKey);
    }

    public int getActivationSignalFlow() {
        return activationSignalFlowProperty.get();
    }

    public void setActivationSignalFlow(int activationSignalFlow) {
        this.activationSignalFlowProperty.set(activationSignalFlow);
    }

    public int getActivationSignalTankLowLevel() {
        return activationSignalTankLowLevelProperty.get();
    }

    public void setActivationSignalTankLowLevel(int activationSignalTankLowLevel) {
        this.activationSignalTankLowLevelProperty.set(activationSignalTankLowLevel);
    }

    public int getActivationSignalFlowError() {
        return activationSignalFlowErrorProperty.get();
    }

    public void setActivationSignalFlowError(int activationSignalFlowError) {
        this.activationSignalFlowErrorProperty.set(activationSignalFlowError);
    }

    public int getActivationSignalHighCompression() {
        return activationSignalHighCompressionProperty.get();
    }

    public void setActivationSignalHighCompression(int activationSignalHighCompression) {
        this.activationSignalHighCompressionProperty.set(activationSignalHighCompression);
    }

    public int getActivationSignalLowCompression() {
        return activationSignalLowCompressionProperty.get();
    }

    public void setActivationSignalLowCompression(int activationSignalLowCompression) {
        this.activationSignalLowCompressionProperty.set(activationSignalLowCompression);
    }


    public static void main(String args[]) throws IOException {
        System.out.println("DatePattern:" + ApplicationSettings.getInstance().getDatePattern());
        System.out.println("LocaleAvailable:" + ApplicationSettings.getInstance().getLocaleAvailable());
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
