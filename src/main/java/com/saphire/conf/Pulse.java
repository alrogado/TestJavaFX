package com.saphire.conf;

/**
 * Created by alvaro.lopez on 30/06/2017.
 */
public class Pulse {

    /**
     * Pulse on time
     */
    float onTime;
    /**
     * Pulse off time
     */
    float offTime;
    /**
     * Pulse current
     */
    float current;
    /**
     * Pulse current
     */
    float sequence;


    public Pulse(float onTime, float offTime, float current, float sequence) {
        this.onTime = onTime;
        this.offTime = offTime;
        this.current = current;
        this.sequence = sequence;
    }

    public float getOnTime() {
        return onTime;
    }

    public void setOnTime(float onTime) {
        this.onTime = onTime;
    }

    public float getOffTime() {
        return offTime;
    }

    public void setOffTime(float offTime) {
        this.offTime = offTime;
    }

    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public float getSequence() {
        return sequence;
    }

    public void setSequence(float sequence) {
        this.sequence = sequence;
    }
}
