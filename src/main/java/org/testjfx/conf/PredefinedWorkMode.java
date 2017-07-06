package org.testjfx.conf;

import java.util.List;

/**
 * Created by alrogado on 6/21/17.
 */
public class PredefinedWorkMode {

    private String name;

    private List<FreqFluPair> freqFluPairList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FreqFluPair> getFreqFluPairList() {
        return freqFluPairList;
    }

    public void setFreqFluPairList(List<FreqFluPair> freqFluPairList) {
        this.freqFluPairList = freqFluPairList;
    }
}
