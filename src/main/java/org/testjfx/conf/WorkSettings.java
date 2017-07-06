package org.testjfx.conf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
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
public class WorkSettings {

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

    /**
     * Total laser shots
     */
    public long totalPulsesA;

    /**
     * Main total laser shots
     */
    public long totalPulsesB;

    static String fileName = "/workSettings.conf";

    // Encryption/decryption o fconfiguration files
    static final String ENCRYPTER_ALGORITHM = "PBEWithMD5AndDES";
    static final byte[] KEY_GENERATION_SALT = new byte[]{(byte)0x1A,(byte)0xAF,(byte)0x13,(byte)0xF5,(byte)0x12,(byte)0x01,(byte)0xAA,(byte)0xDC};
    static final int KEY_GENERATION_ITERATIONS = 16;
    static final char[] SECRET_KEY = "Test JavaFx Configuration Files".toCharArray();

    static final String FILE_NAME_PULSES_A1 = "slspa";
    static final String FILE_NAME_PULSES_A2 = "slspb";
    static final String FILE_NAME_PULSES_B1 = "slspba";
    static final String FILE_NAME_PULSES_B2 = "slspbb";


    public float getTipWorkTempSetpoint() {
        return tipWorkTempSetpoint;
    }

    public void setTipWorkTempSetpoint(float tipWorkTempSetpoint) {
        this.tipWorkTempSetpoint = tipWorkTempSetpoint;
    }

    public float getTipMaxTempSetpoint() {
        return tipMaxTempSetpoint;
    }

    public void setTipMaxTempSetpoint(float tipMaxTempSetpoint) {
        this.tipMaxTempSetpoint = tipMaxTempSetpoint;
    }

    public float getTipTempHysteresis() {
        return tipTempHysteresis;
    }

    public void setTipTempHysteresis(float tipTempHysteresis) {
        this.tipTempHysteresis = tipTempHysteresis;
    }

    public float getTipTempHysteresisWarn() {
        return tipTempHysteresisWarn;
    }

    public void setTipTempHysteresisWarn(float tipTempHysteresisWarn) {
        this.tipTempHysteresisWarn = tipTempHysteresisWarn;
    }

    public float getTipTempHysteresisMax() {
        return tipTempHysteresisMax;
    }

    public void setTipTempHysteresisMax(float tipTempHysteresisMax) {
        this.tipTempHysteresisMax = tipTempHysteresisMax;
    }

    public float getTankWorkTempSetpoint() {
        return tankWorkTempSetpoint;
    }

    public void setTankWorkTempSetpoint(float tankWorkTempSetpoint) {
        this.tankWorkTempSetpoint = tankWorkTempSetpoint;
    }

    public float getTankMaxTempSetpoint() {
        return tankMaxTempSetpoint;
    }

    public void setTankMaxTempSetpoint(float tankMaxTempSetpoint) {
        this.tankMaxTempSetpoint = tankMaxTempSetpoint;
    }

    public float getTankTempHysteresisUp() {
        return tankTempHysteresisUp;
    }

    public void setTankTempHysteresisUp(float tankTempHysteresisUp) {
        this.tankTempHysteresisUp = tankTempHysteresisUp;
    }

    public float getTankTempHysteresisDown() {
        return tankTempHysteresisDown;
    }

    public void setTankTempHysteresisDown(float tankTempHysteresisDown) {
        this.tankTempHysteresisDown = tankTempHysteresisDown;
    }

    public float getMachineMinTempSetpoint() {
        return machineMinTempSetpoint;
    }

    public void setMachineMinTempSetpoint(float machineMinTempSetpoint) {
        this.machineMinTempSetpoint = machineMinTempSetpoint;
    }

    public float getMachineMaxTempSetpoint() {
        return machineMaxTempSetpoint;
    }

    public void setMachineMaxTempSetpoint(float machineMaxTempSetpoint) {
        this.machineMaxTempSetpoint = machineMaxTempSetpoint;
    }

    public float getMachineTempHysteresis() {
        return machineTempHysteresis;
    }

    public void setMachineTempHysteresis(float machineTempHysteresis) {
        this.machineTempHysteresis = machineTempHysteresis;
    }

    public float getDiodeWorkTempSetpoint() {
        return diodeWorkTempSetpoint;
    }

    public void setDiodeWorkTempSetpoint(float diodeWorkTempSetpoint) {
        this.diodeWorkTempSetpoint = diodeWorkTempSetpoint;
    }


    public float getDiodeMaxTempSetpoint() {
        return diodeMaxTempSetpoint;
    }

    public void setDiodeMaxTempSetpoint(float diodeMaxTempSetpoint) {
        this.diodeMaxTempSetpoint = diodeMaxTempSetpoint;
    }

    public float getDiodeTempHysteresis() {
        return diodeTempHysteresis;
    }

    public void setDiodeTempHysteresis(float diodeTempHysteresis) {
        this.diodeTempHysteresis = diodeTempHysteresis;
    }

    public float getShotFrequency() {
        return shotFrequency;
    }

    public void setShotFrequency(float shotFrequency) {
        this.shotFrequency = shotFrequency;
    }

    public float getShotFluence() {
        return shotFluence;
    }

    public void setShotFluence(float shotFluence) {
        this.shotFluence = shotFluence;
    }

    public boolean isPedalEnabled() {
        return pedalEnabled;
    }

    public void setPedalEnabled(boolean pedalEnabled) {
        this.pedalEnabled = pedalEnabled;
    }

    public boolean isTriggerEnabled() {
        return triggerEnabled;
    }

    public void setTriggerEnabled(boolean triggerEnabled) {
        this.triggerEnabled = triggerEnabled;
    }

    static WorkSettings instance = new WorkSettings().loadConf();

    public static WorkSettings getInstance(){
        return instance;
    }

    public static void main(String args[]) throws IOException {
        System.out.println("diodeMaxTempSetpoint:" + WorkSettings.getInstance().getDiodeMaxTempSetpoint());
        System.out.println("tipWorkTempSetpoint:" + WorkSettings.getInstance().getTipWorkTempSetpoint());
    }

    public static WorkSettings loadConf(){
        try {
            final InputStream file = PredefinedWorkModes.class.getResourceAsStream(fileName);
            final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            instance = mapper.readValue(file, WorkSettings.class);
        } catch (IOException e) {
            logger.error("Error loading predefined Work Modes ",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * Writes the current total pulses to the given file
     *
     * @param pulsesFile File to write to
     * @return
     */
    private static void writePulses(File pulsesFile, long totalPulses) throws Exception
    {
        PBEKeySpec keySpec = new PBEKeySpec(SECRET_KEY);
        SecretKey key = SecretKeyFactory.getInstance(ENCRYPTER_ALGORITHM).generateSecret(keySpec);
        Cipher cipher = Cipher.getInstance(ENCRYPTER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(KEY_GENERATION_SALT, KEY_GENERATION_ITERATIONS));

        CipherOutputStream os = new CipherOutputStream(new FileOutputStream(pulsesFile), cipher);
        os.write(Long.toString(totalPulses).getBytes());
        os.flush();
        os.close();
    }

    /**
     * Reads total pulses from the given file
     *
     * @param pulsesFile File to read from
     *
     * @return The number of pulses
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static long readPulses(File pulsesFile) throws IOException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException
    {
        PBEKeySpec keySpec = new PBEKeySpec(SECRET_KEY);
        SecretKey key = SecretKeyFactory.getInstance(ENCRYPTER_ALGORITHM).generateSecret(keySpec);
        Cipher cipher = Cipher.getInstance(ENCRYPTER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(KEY_GENERATION_SALT, KEY_GENERATION_ITERATIONS));

        BufferedReader br = new BufferedReader(new InputStreamReader(new CipherInputStream(new FileInputStream(pulsesFile), cipher)));
        long result = Long.parseLong(br.readLine());
        br.close();

        return result;
    }

}
