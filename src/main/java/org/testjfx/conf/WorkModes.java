package org.testjfx.conf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by alrogado on 6/21/17.
 */
public class WorkModes {

    String fileName = "/workModes.conf";

    @JsonProperty
    private List<Mode> workModes;

    public List<Mode> getWorkModes() {
        return workModes;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setWorkModes(List<Mode> workModes) {
        this.workModes = workModes;
    }

    public WorkModes loadConf() throws IOException {
        final InputStream file = WorkModes.class.getResourceAsStream(fileName);
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory()); // jackson databind
        return mapper.readValue(file, WorkModes.class);
    }

    public static void main(String args[]) throws IOException {
        WorkModes workModes = new WorkModes().loadConf();
        System.out.println("Size:"+workModes.getWorkModes().size());
    }

    /*
    PBEKeySpec keySpec = new PBEKeySpec(SECRET_KEY);
    SecretKey key = SecretKeyFactory.getInstance(ENCRYPTER_ALGORITHM).generateSecret(keySpec);
    Cipher cipher = Cipher.getInstance(ENCRYPTER_ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(KEY_GENERATION_SALT, KEY_GENERATION_ITERATIONS));
    Properties config = new Properties();
    config.load(new CipherInputStream(Configuration.class.getResourceAsStream(FILE_NAME_CONFIGURATION),cipher));
     */
}