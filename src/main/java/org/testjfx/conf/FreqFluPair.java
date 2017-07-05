package org.testjfx.conf;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

/**
 * Created by alvaro.lopez on 30/06/2017.
 */
@JacksonStdImpl
public class FreqFluPair {

    float frequency;
    float fluency;
    Pulse pulse;

    public FreqFluPair(String value) {
        this(
                Float.parseFloat(value.split(",")[0]),
                Float.parseFloat(value.split(",")[1]),
                new Pulse(
                        Float.parseFloat(value.split(",")[2]),
                        Float.parseFloat(value.split(",")[3]),
                        Float.parseFloat(value.split(",")[4]),
                        Float.parseFloat(value.split(",")[5])
                ));

    }

    public FreqFluPair(float frequency, float fluency, Pulse pulse) {
        this.frequency = frequency;
        this.fluency = fluency;
        this.pulse = pulse;
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public float getFluency() {
        return fluency;
    }

    public void setFluency(float fluency) {
        this.fluency = fluency;
    }

    public Pulse getPulse() {
        return pulse;
    }

    public void setPulse(Pulse pulse) {
        this.pulse = pulse;
    }
}
