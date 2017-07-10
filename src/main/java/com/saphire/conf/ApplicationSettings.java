package com.saphire.conf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
    @JsonProperty private double frequencySelected;
    @JsonProperty private double fluencySelected;
    @JsonProperty private boolean depositFillEnabled;
    @JsonProperty private boolean pedalEnabled;
    @JsonProperty private boolean triggerEnabled;

    public static String getBundleString(String rscBundle) {
        String res = rscBundle;
        try {
            res = APPBUNDLE.getString(rscBundle);
        } catch (Exception e) {
            logger.warn("Error loading resources ", e);
        }
        return res;
    }

    @JsonIgnore private LongProperty startupDelayProperty = new SimpleLongProperty(startupDelay);
    @JsonIgnore private StringProperty serialNumberProperty = new SimpleStringProperty(serialNumber);
    @JsonIgnore private StringProperty maintenancePasswordProperty = new SimpleStringProperty(maintenancePassword);
    @JsonIgnore private StringProperty mainMaintenancePasswordProperty = new SimpleStringProperty(mainMaintenancePassword);
    @JsonIgnore private StringProperty localeAvailableProperty = new SimpleStringProperty(localeAvailable);
    @JsonIgnore private StringProperty datePatternProperty = new SimpleStringProperty(datePattern);
    @JsonIgnore private IntegerProperty setpointMinFullTemperatureProperty = new SimpleIntegerProperty(setpointMinFullTemperature);
    @JsonIgnore private IntegerProperty setpointMinTemperatureProperty = new SimpleIntegerProperty(setpointMinTemperature);
    @JsonIgnore private IntegerProperty setpointMaxTemperatureProperty = new SimpleIntegerProperty(setpointMaxTemperature);
    @JsonIgnore private IntegerProperty setpointMaxFullTemperatureProperty = new SimpleIntegerProperty(setpointMaxFullTemperature);
    @JsonIgnore private StringProperty workModeNameProperty = new SimpleStringProperty(workModeName);
    @JsonIgnore private BooleanProperty fulScreenEnabledProperty = new SimpleBooleanProperty(fulScreenEnabled);
    @JsonIgnore private IntegerProperty widthScreenProperty = new SimpleIntegerProperty(widthScreen);
    @JsonIgnore private IntegerProperty heightScreenProperty = new SimpleIntegerProperty(heightScreen);
    @JsonIgnore private IntegerProperty commRetryIntervalProperty = new SimpleIntegerProperty(commRetryInterval);
    @JsonIgnore private StringProperty commDriverProperty = new SimpleStringProperty(commDriver);
    @JsonIgnore private StringProperty commDriverArgumentsProperty = new SimpleStringProperty(commDriverArguments);
    @JsonIgnore private IntegerProperty commMessageWaitingTimeoutProperty = new SimpleIntegerProperty(commMessageWaitingTimeout);
    @JsonIgnore private IntegerProperty commAcknowledgeWaitingTimeoutProperty = new SimpleIntegerProperty(commAcknowledgeWaitingTimeout);
    @JsonIgnore private IntegerProperty commAliveSignalIntervalProperty = new SimpleIntegerProperty(commAliveSignalInterval);
    @JsonIgnore private IntegerProperty toolsAccessClicksProperty = new SimpleIntegerProperty(toolsAccessClicks);
    @JsonIgnore private IntegerProperty toolsAccessTimeProperty = new SimpleIntegerProperty(toolsAccessTime);
    @JsonIgnore private IntegerProperty shutdownMessageTimeProperty = new SimpleIntegerProperty(shutdownMessageTime);
    @JsonIgnore private IntegerProperty errorShutdownMessageTimeProperty = new SimpleIntegerProperty(errorShutdownMessageTime);
    @JsonIgnore private IntegerProperty workIdleTimeoutProperty = new SimpleIntegerProperty(workIdleTimeout);
    @JsonIgnore private IntegerProperty activationSignalTriggerProperty = new SimpleIntegerProperty(activationSignalTrigger);
    @JsonIgnore private IntegerProperty activationSignalPedalProperty = new SimpleIntegerProperty(activationSignalPedal);
    @JsonIgnore private IntegerProperty activationSignalInterlockProperty = new SimpleIntegerProperty(activationSignalInterlock);
    @JsonIgnore private IntegerProperty activationSignalKeyProperty = new SimpleIntegerProperty(activationSignalKey);
    @JsonIgnore private IntegerProperty activationSignalFlowProperty = new SimpleIntegerProperty(activationSignalFlow);
    @JsonIgnore private IntegerProperty activationSignalTankLowLevelProperty = new SimpleIntegerProperty(activationSignalTankLowLevel);
    @JsonIgnore private IntegerProperty activationSignalFlowErrorProperty = new SimpleIntegerProperty(activationSignalFlowError);
    @JsonIgnore private IntegerProperty activationSignalHighCompressionProperty = new SimpleIntegerProperty(activationSignalHighCompression);
    @JsonIgnore private IntegerProperty activationSignalLowCompressionProperty = new SimpleIntegerProperty(activationSignalLowCompression);
    @JsonIgnore private DoubleProperty pulseVolumeProperty = new SimpleDoubleProperty(pulseVolume);
    @JsonIgnore private DoubleProperty screenVolumeProperty = new SimpleDoubleProperty(screenVolume);
    @JsonIgnore private DoubleProperty frequencySelectedProperty = new SimpleDoubleProperty(frequencySelected);
    @JsonIgnore private DoubleProperty fluencySelectedProperty = new SimpleDoubleProperty(fluencySelected);
    @JsonIgnore private BooleanProperty depositFillEnabledProperty = new SimpleBooleanProperty(depositFillEnabled);
    @JsonIgnore private BooleanProperty pedalEnabledProperty = new SimpleBooleanProperty(pedalEnabled);
    @JsonIgnore private BooleanProperty triggerEnabledProperty = new SimpleBooleanProperty(triggerEnabled);

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
        frequencySelectedProperty.addListener(changeListener);
        fluencySelectedProperty.addListener(changeListener);
    }

    public static ApplicationSettings getInstance() {
        return instance;
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

    public double getFrequencySelected() {
        return frequencySelectedProperty.get();
    }

    public void setFrequencySelected(double frequencySelected) {
        this.frequencySelectedProperty.set(frequencySelected);
    }

    public double getFluencySelected() {
        return fluencySelectedProperty.get();
    }

    public void setFluencySelected(double fluencySelected) {
        this.fluencySelectedProperty.set(fluencySelected);
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

    @Override
    public String getFileName() {
        return fileName;
    }
    
    
    
    public static void main(String args[]) throws IOException {
        System.out.println("DatePattern:" + ApplicationSettings.getInstance().getDatePattern());
        System.out.println("LocaleAvailable:" + ApplicationSettings.getInstance().getLocaleAvailable());
        ApplicationSettings.getInstance().setFrequencySelected(58);
    }

    public long getStartupDelayProperty() {
        return startupDelayProperty.get();
    }

    public LongProperty startupDelayProperty() {
        return startupDelayProperty;
    }

    public String getSerialNumberProperty() {
        return serialNumberProperty.get();
    }

    public StringProperty serialNumberProperty() {
        return serialNumberProperty;
    }

    public String getMaintenancePasswordProperty() {
        return maintenancePasswordProperty.get();
    }

    public StringProperty maintenancePasswordProperty() {
        return maintenancePasswordProperty;
    }

    public String getMainMaintenancePasswordProperty() {
        return mainMaintenancePasswordProperty.get();
    }

    public StringProperty mainMaintenancePasswordProperty() {
        return mainMaintenancePasswordProperty;
    }

    public String getLocaleAvailableProperty() {
        return localeAvailableProperty.get();
    }

    public StringProperty localeAvailableProperty() {
        return localeAvailableProperty;
    }

    public String getDatePatternProperty() {
        return datePatternProperty.get();
    }

    public StringProperty datePatternProperty() {
        return datePatternProperty;
    }

    public int getSetpointMinFullTemperatureProperty() {
        return setpointMinFullTemperatureProperty.get();
    }

    public IntegerProperty setpointMinFullTemperatureProperty() {
        return setpointMinFullTemperatureProperty;
    }

    public int getSetpointMinTemperatureProperty() {
        return setpointMinTemperatureProperty.get();
    }

    public IntegerProperty setpointMinTemperatureProperty() {
        return setpointMinTemperatureProperty;
    }

    public int getSetpointMaxTemperatureProperty() {
        return setpointMaxTemperatureProperty.get();
    }

    public IntegerProperty setpointMaxTemperatureProperty() {
        return setpointMaxTemperatureProperty;
    }

    public int getSetpointMaxFullTemperatureProperty() {
        return setpointMaxFullTemperatureProperty.get();
    }

    public IntegerProperty setpointMaxFullTemperatureProperty() {
        return setpointMaxFullTemperatureProperty;
    }

    public String getWorkModeNameProperty() {
        return workModeNameProperty.get();
    }

    public StringProperty workModeNameProperty() {
        return workModeNameProperty;
    }

    public boolean isFulScreenEnabledProperty() {
        return fulScreenEnabledProperty.get();
    }

    public BooleanProperty fulScreenEnabledProperty() {
        return fulScreenEnabledProperty;
    }

    public int getWidthScreenProperty() {
        return widthScreenProperty.get();
    }

    public IntegerProperty widthScreenProperty() {
        return widthScreenProperty;
    }

    public int getHeightScreenProperty() {
        return heightScreenProperty.get();
    }

    public IntegerProperty heightScreenProperty() {
        return heightScreenProperty;
    }

    public int getCommRetryIntervalProperty() {
        return commRetryIntervalProperty.get();
    }

    public IntegerProperty commRetryIntervalProperty() {
        return commRetryIntervalProperty;
    }

    public String getCommDriverProperty() {
        return commDriverProperty.get();
    }

    public StringProperty commDriverProperty() {
        return commDriverProperty;
    }

    public String getCommDriverArgumentsProperty() {
        return commDriverArgumentsProperty.get();
    }

    public StringProperty commDriverArgumentsProperty() {
        return commDriverArgumentsProperty;
    }

    public int getCommMessageWaitingTimeoutProperty() {
        return commMessageWaitingTimeoutProperty.get();
    }

    public IntegerProperty commMessageWaitingTimeoutProperty() {
        return commMessageWaitingTimeoutProperty;
    }

    public int getCommAcknowledgeWaitingTimeoutProperty() {
        return commAcknowledgeWaitingTimeoutProperty.get();
    }

    public IntegerProperty commAcknowledgeWaitingTimeoutProperty() {
        return commAcknowledgeWaitingTimeoutProperty;
    }

    public int getCommAliveSignalIntervalProperty() {
        return commAliveSignalIntervalProperty.get();
    }

    public IntegerProperty commAliveSignalIntervalProperty() {
        return commAliveSignalIntervalProperty;
    }

    public int getToolsAccessClicksProperty() {
        return toolsAccessClicksProperty.get();
    }

    public IntegerProperty toolsAccessClicksProperty() {
        return toolsAccessClicksProperty;
    }

    public int getToolsAccessTimeProperty() {
        return toolsAccessTimeProperty.get();
    }

    public IntegerProperty toolsAccessTimeProperty() {
        return toolsAccessTimeProperty;
    }

    public int getShutdownMessageTimeProperty() {
        return shutdownMessageTimeProperty.get();
    }

    public IntegerProperty shutdownMessageTimeProperty() {
        return shutdownMessageTimeProperty;
    }

    public int getErrorShutdownMessageTimeProperty() {
        return errorShutdownMessageTimeProperty.get();
    }

    public IntegerProperty errorShutdownMessageTimeProperty() {
        return errorShutdownMessageTimeProperty;
    }

    public int getWorkIdleTimeoutProperty() {
        return workIdleTimeoutProperty.get();
    }

    public IntegerProperty workIdleTimeoutProperty() {
        return workIdleTimeoutProperty;
    }

    public int getActivationSignalTriggerProperty() {
        return activationSignalTriggerProperty.get();
    }

    public IntegerProperty activationSignalTriggerProperty() {
        return activationSignalTriggerProperty;
    }

    public int getActivationSignalPedalProperty() {
        return activationSignalPedalProperty.get();
    }

    public IntegerProperty activationSignalPedalProperty() {
        return activationSignalPedalProperty;
    }

    public int getActivationSignalInterlockProperty() {
        return activationSignalInterlockProperty.get();
    }

    public IntegerProperty activationSignalInterlockProperty() {
        return activationSignalInterlockProperty;
    }

    public int getActivationSignalKeyProperty() {
        return activationSignalKeyProperty.get();
    }

    public IntegerProperty activationSignalKeyProperty() {
        return activationSignalKeyProperty;
    }

    public int getActivationSignalFlowProperty() {
        return activationSignalFlowProperty.get();
    }

    public IntegerProperty activationSignalFlowProperty() {
        return activationSignalFlowProperty;
    }

    public int getActivationSignalTankLowLevelProperty() {
        return activationSignalTankLowLevelProperty.get();
    }

    public IntegerProperty activationSignalTankLowLevelProperty() {
        return activationSignalTankLowLevelProperty;
    }

    public int getActivationSignalFlowErrorProperty() {
        return activationSignalFlowErrorProperty.get();
    }

    public IntegerProperty activationSignalFlowErrorProperty() {
        return activationSignalFlowErrorProperty;
    }

    public int getActivationSignalHighCompressionProperty() {
        return activationSignalHighCompressionProperty.get();
    }

    public IntegerProperty activationSignalHighCompressionProperty() {
        return activationSignalHighCompressionProperty;
    }

    public int getActivationSignalLowCompressionProperty() {
        return activationSignalLowCompressionProperty.get();
    }

    public IntegerProperty activationSignalLowCompressionProperty() {
        return activationSignalLowCompressionProperty;
    }

    public double getPulseVolumeProperty() {
        return pulseVolumeProperty.get();
    }

    public DoubleProperty pulseVolumeProperty() {
        return pulseVolumeProperty;
    }

    public double getScreenVolumeProperty() {
        return screenVolumeProperty.get();
    }

    public DoubleProperty screenVolumeProperty() {
        return screenVolumeProperty;
    }

    public double getFrequencySelectedProperty() {
        return frequencySelectedProperty.get();
    }

    public DoubleProperty frequencySelectedProperty() {
        return frequencySelectedProperty;
    }

    public double getFluencySelectedProperty() {
        return fluencySelectedProperty.get();
    }

    public DoubleProperty fluencySelectedProperty() {
        return fluencySelectedProperty;
    }

    public boolean isDepositFillEnabledProperty() {
        return depositFillEnabledProperty.get();
    }

    public BooleanProperty depositFillEnabledProperty() {
        return depositFillEnabledProperty;
    }

    public boolean isPedalEnabledProperty() {
        return pedalEnabledProperty.get();
    }

    public BooleanProperty pedalEnabledProperty() {
        return pedalEnabledProperty;
    }

    public boolean isTriggerEnabledProperty() {
        return triggerEnabledProperty.get();
    }

    public BooleanProperty triggerEnabledProperty() {
        return triggerEnabledProperty;
    }
}
