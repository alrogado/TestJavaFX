package org.testjfx.conf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by alrogado on 6/21/17.
 */
public class PredefinedWorkModes {

    private static Logger logger = LoggerFactory.getLogger(PredefinedWorkModes.class);

    @JsonProperty
    private List<PredefinedWorkMode> predefinedWorkModes;

    public List<PredefinedWorkMode> getPredefinedWorkModes() {
        return predefinedWorkModes;
    }

    public void setPredefinedWorkModes(List<PredefinedWorkMode> predefinedWorkModes) {
        this.predefinedWorkModes = predefinedWorkModes;
    }

    static String fileName = "/predefinedWorkModes.conf";

    public static PredefinedWorkModes  loadConf() {
        final InputStream file = PredefinedWorkModes.class.getResourceAsStream(fileName);
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory()); // jackson databind
        /*
        Now this is not needed
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addKeyDeserializer(FreqFluPair.class, new FreqFluPairKeyDeserializer());
        simpleModule.addKeyDeserializer(Pulse.class, new PulseKeyDeserializer());
        mapper.registerModule(simpleModule);*/
        try {
            instance = mapper.readValue(file, PredefinedWorkModes.class);
        } catch (IOException e) {
            logger.error("Problem loading predefined workmodes", e);
        }
        return instance;
    }

    static PredefinedWorkModes instance= new PredefinedWorkModes().loadConf();

    public static PredefinedWorkModes getInstance(){
        return instance;
    }

    /*
    PBEKeySpec keySpec = new PBEKeySpec(SECRET_KEY);
    SecretKey key = SecretKeyFactory.getInstance(ENCRYPTER_ALGORITHM).generateSecret(keySpec);
    Cipher cipher = Cipher.getInstance(ENCRYPTER_ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(KEY_GENERATION_SALT, KEY_GENERATION_ITERATIONS));
    Properties config = new Properties();
    config.load(new CipherInputStream(ApplicationConf.class.getResourceAsStream(FILE_NAME_CONFIGURATION),cipher));
     */

    public static void main(String args[]) throws IOException {
        System.out.println("WorkModes Size:" + PredefinedWorkModes.getInstance().getPredefinedWorkModes().size());
    }

}
