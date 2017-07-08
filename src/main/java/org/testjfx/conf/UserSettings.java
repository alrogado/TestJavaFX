package org.testjfx.conf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
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

    /**
     * Total laser shots
     */
    private long totalPulsesA;

    /**
     * Main total laser shots
     */
    private long totalPulsesB;

    String fileName = "userSettings.conf";

    static final String FILE_NAME_PULSES_A1 = "slspa";
    static final String FILE_NAME_PULSES_A2 = "slspb";
    static final String FILE_NAME_PULSES_B1 = "slspba";
    static final String FILE_NAME_PULSES_B2 = "slspbb";


    public long getTotalPulsesA() {
        return totalPulsesA;
    }

    public void setTotalPulsesA(long totalPulsesA) {
        this.totalPulsesA = totalPulsesA;
    }

    public long getTotalPulsesB() {
        return totalPulsesB;
    }

    public void setTotalPulsesB(long totalPulsesB) {
        this.totalPulsesB = totalPulsesB;
    }

    static UserSettings instance = new UserSettings().loadConf(UserSettings.class);

    public static UserSettings getInstance(){
        return instance;
    }

    public static void main(String args[]) throws IOException {
        System.out.println("TotalPulsesA:" + UserSettings.getInstance().getTotalPulsesA());
    }

    @Override
    public String getFileName() {
        return fileName;
    }

}
