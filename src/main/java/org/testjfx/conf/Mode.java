package org.testjfx.conf;

import java.util.List;

/**
 * Created by alrogado on 6/21/17.
 */
public class Mode {

    private String name;

    private List<FreqFluPair> pulsesFF;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FreqFluPair> getPulsesFF() {
        return pulsesFF;
    }

    public void setPulsesFF(List<FreqFluPair> pulsesFF) {
        this.pulsesFF = pulsesFF;
    }
}
