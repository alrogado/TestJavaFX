package org.testjfx.conf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.reactfx.StateMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by alvaro.lopez on 05/07/2017.
 */
public class WorkSettings extends Settings{

    private static Logger logger = LoggerFactory.getLogger(WorkSettings.class);

    @JsonProperty float tipWorkTempSetpoint;
    @JsonProperty float tipMaxTempSetpoint;
    @JsonProperty float tipTempHysteresis;
    @JsonProperty float tipTempHysteresisWarn;
    @JsonProperty float tipTempHysteresisMax;
    @JsonProperty float tankWorkTempSetpoint;
    @JsonProperty float tankMaxTempSetpoint;
    @JsonProperty float tankTempHysteresisUp;
    @JsonProperty float tankTempHysteresisDown;
    @JsonProperty float machineMinTempSetpoint;
    @JsonProperty float machineMaxTempSetpoint;
    @JsonProperty float machineTempHysteresis;
    @JsonProperty float diodeWorkTempSetpoint;
    @JsonProperty float diodeMaxTempSetpoint;
    @JsonProperty float diodeTempHysteresis;
    @JsonProperty float shotFrequency;
    @JsonProperty float shotFluence;
    @JsonProperty boolean pedalEnabled;
    @JsonProperty boolean triggerEnabled;

    @JsonIgnore FloatProperty tipWorkTempSetpointProperty = new SimpleFloatProperty(tipWorkTempSetpoint);
    @JsonIgnore FloatProperty tipMaxTempSetpointProperty = new SimpleFloatProperty (tipMaxTempSetpoint);
    @JsonIgnore FloatProperty tipTempHysteresisProperty = new SimpleFloatProperty (tipTempHysteresis);
    @JsonIgnore FloatProperty tipTempHysteresisWarnProperty = new SimpleFloatProperty (tipTempHysteresisWarn);
    @JsonIgnore FloatProperty tipTempHysteresisMaxProperty = new SimpleFloatProperty (tipTempHysteresisMax);
    @JsonIgnore FloatProperty tankWorkTempSetpointProperty = new SimpleFloatProperty (tankWorkTempSetpoint);
    @JsonIgnore FloatProperty tankMaxTempSetpointProperty = new SimpleFloatProperty (tankMaxTempSetpoint);
    @JsonIgnore FloatProperty tankTempHysteresisUpProperty = new SimpleFloatProperty (tankTempHysteresisUp);
    @JsonIgnore FloatProperty tankTempHysteresisDownProperty = new SimpleFloatProperty (tankTempHysteresisDown);
    @JsonIgnore FloatProperty machineMinTempSetpointProperty = new SimpleFloatProperty (machineMinTempSetpoint);
    @JsonIgnore FloatProperty machineMaxTempSetpointProperty = new SimpleFloatProperty (machineMaxTempSetpoint);
    @JsonIgnore FloatProperty machineTempHysteresisProperty = new SimpleFloatProperty (machineTempHysteresis);
    @JsonIgnore FloatProperty diodeWorkTempSetpointProperty = new SimpleFloatProperty (diodeWorkTempSetpoint);
    @JsonIgnore FloatProperty diodeMaxTempSetpointProperty = new SimpleFloatProperty (diodeMaxTempSetpoint);
    @JsonIgnore FloatProperty diodeTempHysteresisProperty = new SimpleFloatProperty (diodeTempHysteresis);
    @JsonIgnore FloatProperty shotFrequencyProperty = new SimpleFloatProperty (shotFrequency);
    @JsonIgnore FloatProperty shotFluenceProperty = new SimpleFloatProperty (shotFluence);
    @JsonIgnore BooleanProperty pedalEnabledProperty = new SimpleBooleanProperty(pedalEnabled);
    @JsonIgnore BooleanProperty triggerEnabledProperty = new SimpleBooleanProperty (triggerEnabled);

    {
        triggerEnabledProperty.addListener(changeListener);
        tipWorkTempSetpointProperty.addListener(changeListener);
        tipMaxTempSetpointProperty.addListener(changeListener);
        tipTempHysteresisProperty.addListener(changeListener);
        tipTempHysteresisWarnProperty.addListener(changeListener);
        tipTempHysteresisMaxProperty.addListener(changeListener);
        tankWorkTempSetpointProperty.addListener(changeListener);
        tankMaxTempSetpointProperty.addListener(changeListener);
        tankTempHysteresisUpProperty.addListener(changeListener);
        tankTempHysteresisDownProperty.addListener(changeListener);
        machineMinTempSetpointProperty.addListener(changeListener);
        machineMaxTempSetpointProperty.addListener(changeListener);
        machineTempHysteresisProperty.addListener(changeListener);
        diodeWorkTempSetpointProperty.addListener(changeListener);
        diodeMaxTempSetpointProperty.addListener(changeListener);
        diodeTempHysteresisProperty.addListener(changeListener);
        shotFrequencyProperty.addListener(changeListener);
        shotFluenceProperty.addListener(changeListener);
        pedalEnabledProperty.addListener(changeListener);
        triggerEnabledProperty.addListener(changeListener);
    }

    /**
     * Total laser shots
     */
    public long totalPulsesA;

    /**
     * Main total laser shots
     */
    public long totalPulsesB;

    static String fileName = "workSettings.conf";



    static final String FILE_NAME_PULSES_A1 = "slspa";
    static final String FILE_NAME_PULSES_A2 = "slspb";
    static final String FILE_NAME_PULSES_B1 = "slspba";
    static final String FILE_NAME_PULSES_B2 = "slspbb";


    public float getTipWorkTempSetpoint() {
        return tipWorkTempSetpointProperty.get();
    }

    public void setTipWorkTempSetpoint(float tipWorkTempSetpoint) {
        this.tipWorkTempSetpointProperty.set(tipWorkTempSetpoint);
    }

    public float getTipMaxTempSetpoint() {
        return tipMaxTempSetpointProperty.get();
    }

    public void setTipMaxTempSetpoint(float tipMaxTempSetpoint) {
        this.tipMaxTempSetpointProperty.set(tipMaxTempSetpoint);
    }

    public float getTipTempHysteresis() {
        return tipTempHysteresisProperty.get();
    }

    public void setTipTempHysteresis(float tipTempHysteresis) {
        this.tipTempHysteresisProperty.set(tipTempHysteresis);
    }

    public float getTipTempHysteresisWarn() {
        return tipTempHysteresisWarnProperty.get();
    }

    public void setTipTempHysteresisWarn(float tipTempHysteresisWarn) {
        this.tipTempHysteresisWarnProperty.set(tipTempHysteresisWarn);
    }

    public float getTipTempHysteresisMax() {
        return tipTempHysteresisMaxProperty.get();
    }

    public void setTipTempHysteresisMax(float tipTempHysteresisMax) {
        this.tipTempHysteresisMaxProperty.set(tipTempHysteresisMax);
    }

    public float getTankWorkTempSetpoint() {
        return tankWorkTempSetpointProperty.get();
    }

    public void setTankWorkTempSetpoint(float tankWorkTempSetpoint) {
        this.tankWorkTempSetpointProperty.set(tankWorkTempSetpoint);
    }

    public float getTankMaxTempSetpoint() {
        return tankMaxTempSetpointProperty.get();
    }

    public void setTankMaxTempSetpoint(float tankMaxTempSetpoint) {
        this.tankMaxTempSetpointProperty.set(tankMaxTempSetpoint);
    }

    public float getTankTempHysteresisUp() {
        return tankTempHysteresisUpProperty.get();
    }

    public void setTankTempHysteresisUp(float tankTempHysteresisUp) {
        this.tankTempHysteresisUpProperty.set(tankTempHysteresisUp);
    }

    public float getTankTempHysteresisDown() {
        return tankTempHysteresisDownProperty.get();
    }

    public void setTankTempHysteresisDown(float tankTempHysteresisDown) {
        this.tankTempHysteresisDownProperty.set(tankTempHysteresisDown);
    }

    public float getMachineMinTempSetpoint() {
        return machineMinTempSetpointProperty.get();
    }

    public void setMachineMinTempSetpoint(float machineMinTempSetpoint) {
        this.machineMinTempSetpointProperty.set(machineMinTempSetpoint);
    }

    public float getMachineMaxTempSetpoint() {
        return machineMaxTempSetpointProperty.get();
    }

    public void setMachineMaxTempSetpoint(float machineMaxTempSetpoint) {
        this.machineMaxTempSetpointProperty.set(machineMaxTempSetpoint);
    }

    public float getMachineTempHysteresis() {
        return machineTempHysteresisProperty.get();
    }

    public void setMachineTempHysteresis(float machineTempHysteresis) {
        this.machineTempHysteresisProperty.set(machineTempHysteresis);
    }

    public float getDiodeWorkTempSetpoint() {
        return diodeWorkTempSetpointProperty.get();
    }

    public void setDiodeWorkTempSetpoint(float diodeWorkTempSetpoint) {
        this.diodeWorkTempSetpointProperty.set(diodeWorkTempSetpoint);
    }


    public float getDiodeMaxTempSetpoint() {
        return diodeMaxTempSetpointProperty.get();
    }

    public void setDiodeMaxTempSetpoint(float diodeMaxTempSetpoint) {
        this.diodeMaxTempSetpointProperty.set(diodeMaxTempSetpoint);
    }

    public float getDiodeTempHysteresis() {
        return diodeTempHysteresisProperty.get();
    }

    public void setDiodeTempHysteresis(float diodeTempHysteresis) {
        this.diodeTempHysteresisProperty.set(diodeTempHysteresis);
    }

    public float getShotFrequency() {
        return shotFrequencyProperty.get();
    }

    public void setShotFrequency(float shotFrequency) {
        this.shotFrequencyProperty.set(shotFrequency);
    }

    public float getShotFluence() {
        return shotFluenceProperty.get();
    }

    public void setShotFluence(float shotFluence) {
        this.shotFluenceProperty.set(shotFluence);
    }

    public boolean isPedalEnabled() {
        return pedalEnabledProperty.get();
    }

    public void setPedalEnabled(boolean pedalEnabled) {
        this.pedalEnabledProperty.set(pedalEnabled);
    }

    public boolean isTriggerEnabled() {
        return triggerEnabledProperty.get();
    }

    public void setTriggerEnabled(boolean triggerEnabled) {
        this.triggerEnabledProperty.set(triggerEnabled);
    }

    static WorkSettings instance = new WorkSettings().loadConf(WorkSettings.class);

    public static WorkSettings getInstance(){
        return instance;
    }

    public static void main(String args[]) throws IOException {
        System.out.println("diodeMaxTempSetpoint:" + WorkSettings.getInstance().getDiodeMaxTempSetpoint());
        System.out.println("tipWorkTempSetpoint:" + WorkSettings.getInstance().getTipWorkTempSetpoint());
        WorkSettings.getInstance().setTipWorkTempSetpoint(26);
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
