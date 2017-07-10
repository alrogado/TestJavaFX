package org.testjfx.conf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ChangeListener;
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
public class UserSettings extends Settings{

    private static Logger logger = LoggerFactory.getLogger(UserSettings.class);

    private long totalPulsesA;
    private long totalPulsesB;
    
    LongProperty totalPulsesAProperty = new SimpleLongProperty();
    LongProperty totalPulsesBProperty = new SimpleLongProperty();
    

    String fileName = "userSettings.conf";

   
    public long getTotalPulsesA() {
        return totalPulsesAProperty.get();
    }

    public void setTotalPulsesA(long totalPulsesA) {
        this.totalPulsesAProperty.set(totalPulsesA);
    }

    public long getTotalPulsesB() {
        return totalPulsesBProperty.get();
    }

    public void setTotalPulsesB(long totalPulsesB) {
        this.totalPulsesBProperty.set(totalPulsesB);
    }

    static UserSettings instance = new UserSettings().loadConf(UserSettings.class);

    public static UserSettings getInstance(){
        return instance;
    }

    public static void main(String args[]) throws IOException {
        System.out.println("TotalPulsesA:" + UserSettings.getInstance().getTotalPulsesA());
    }

    {
        totalPulsesAProperty.addListener(changeListener);
        totalPulsesBProperty.addListener(changeListener);
    }

    public long getTotalPulsesAProperty() {
        return totalPulsesAProperty.get();
    }

    public LongProperty totalPulsesAProperty() {
        return totalPulsesAProperty;
    }

    public long getTotalPulsesBProperty() {
        return totalPulsesBProperty.get();
    }

    public LongProperty totalPulsesBProperty() {
        return totalPulsesBProperty;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

}
