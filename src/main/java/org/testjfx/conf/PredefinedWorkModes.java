package org.testjfx.conf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
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

    //todo see ant happens with the list property serialization
    // private ListProperty<PredefinedWorkMode> predefinedWorkModesProperty= new SimpleListProperty<>();

    public List<PredefinedWorkMode> getPredefinedWorkModes() {
        return predefinedWorkModes;
    }

    public void setPredefinedWorkModes(List<PredefinedWorkMode> predefinedWorkModes) {
        this.predefinedWorkModes = predefinedWorkModes;
    }

   /* public ObservableList<PredefinedWorkMode> getPredefinedWorkModesProperty() {
        return predefinedWorkModesProperty.get();
    }

    public ListProperty<PredefinedWorkMode> predefinedWorkModesProperty() {
        return predefinedWorkModesProperty;
    }*/

    static PredefinedWorkModes instance = new PredefinedWorkModes().loadConf(PredefinedWorkModes.class);

    public static PredefinedWorkModes getInstance(){
        return instance;
    }

    public static void main(String args[]) throws IOException {
        System.out.println("WorkModes Size:" + PredefinedWorkModes.getInstance().getPredefinedWorkModes().size());
    }

    {
        //predefinedWorkModesProperty.addListener(changeListener);
    }

    @Override
    public String getFileName() {
        return "predefinedWorkModes.conf";
    }
}
