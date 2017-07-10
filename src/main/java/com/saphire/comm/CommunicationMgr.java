package com.saphire.comm;

import com.saphire.conf.ApplicationSettings;

/**
 * Created by alvaro.lopez on 05/07/2017.
 */
public class CommunicationMgr {

    static ICommunication instance = new InnerCommunication();
    ICommunication comm;

    public static ICommunication getICommunication() {
        return instance;
    }

    public void sendAlive() {
        while (true) {
            try {
                comm.sendAlive();
                Thread.sleep(ApplicationSettings.getInstance().getCommAliveSignalInterval());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Method to be invoked asynchronously when manual show to reset the UI control status in the configured timeout
     */
    public synchronized void manualShot() {
        comm.sendShotCommand();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e1) {
        }

        //ui.getToolsPanel().diodeShotControlOff();
    }
}
