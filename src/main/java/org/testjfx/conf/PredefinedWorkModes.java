package org.testjfx.conf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by alrogado on 6/21/17.
 */
public class PredefinedWorkModes extends Settings {

    @JsonProperty
    private List<PredefinedWorkMode> predefinedWorkModes;

    public List<PredefinedWorkMode> getPredefinedWorkModes() {
        return predefinedWorkModes;
    }

    public void setPredefinedWorkModes(List<PredefinedWorkMode> predefinedWorkModes) {
        this.predefinedWorkModes = predefinedWorkModes;
    }

    static PredefinedWorkModes instance = new PredefinedWorkModes().loadConf(PredefinedWorkModes.class);

    public static PredefinedWorkModes getInstance(){
        return instance;
    }

    public static void main(String args[]) throws IOException {
        System.out.println("WorkModes Size:" + PredefinedWorkModes.getInstance().getPredefinedWorkModes().size());
    }

    @Override
    public String getFileName() {
        return "predefinedWorkModes.conf";
    }
}
