package org.testjfx.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
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
 * Created by alvaro.lopez on 08/07/2017.
 */
public abstract class Settings {

    protected static Logger logger = LoggerFactory.getLogger(Settings.class);

    ChangeListener changeListener = (observable, oldValue, newValue) -> {
        System.out.println("changed " + oldValue + "->" + newValue);
        saveConf();
    };

    public <T extends Settings> T loadConf(Class<T> valueType) {
        InputStream file = valueType.getResourceAsStream(System.getProperty("user.dir") + File.separator + getFileName());
        if (file == null)
            file = valueType.getResourceAsStream("/" + getFileName());
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory()); // jackson databind
        /*
        Now this is not needed
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addKeyDeserializer(FreqFluPair.class, new FreqFluPairKeyDeserializer());
        simpleModule.addKeyDeserializer(Pulse.class, new PulseKeyDeserializer());
        mapper.registerModule(simpleModule);*/
        T t = null;
        try {
            t = mapper.readValue(file, valueType);
        } catch (IOException e) {
            logger.error("Problem loading "+ valueType.getClass().getSimpleName(), e);
        }
        addChangeListener(changeListener);
        return t;
    }

    protected abstract void addChangeListener(ChangeListener changeListener);

    public void saveConf(){
        try {
            final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+ File.separator+getFileName());
            SequenceWriter sw = mapper.writerWithDefaultPrettyPrinter().writeValues(fos);
            sw.write(this);
            sw.close();
        } catch (IOException e) {
            logger.error("Error saving PredefinedWorkModes Settings ",e);
            throw new RuntimeException(e);
        }
    }

    public abstract String getFileName();

    // Encryption/decryption o fconfiguration files
    static final String ENCRYPTER_ALGORITHM = "PBEWithMD5AndDES";
    static final byte[] KEY_GENERATION_SALT = new byte[]{(byte)0x1A,(byte)0xAF,(byte)0x13,(byte)0xF5,(byte)0x12,(byte)0x01,(byte)0xAA,(byte)0xDC};
    static final int KEY_GENERATION_ITERATIONS = 16;
    static final char[] SECRET_KEY = "Test JavaFx Configuration Files".toCharArray();

    /**
     * Writes the current total pulses to the given file
     *
     * @param pulsesFile File to write to
     * @return
     */
    private void writePulses(File pulsesFile, long totalPulses) throws Exception
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
    private long readPulses(File pulsesFile) throws IOException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException
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
