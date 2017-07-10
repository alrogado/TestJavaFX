package com.saphire.conf;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
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
