package org.testjfx.comm;

import org.testjfx.conf.ApplicationConf;

/**
 * Created by alvaro.lopez on 05/07/2017.
 */
public class Communication {

    ICommunication comm;

    public void sendAlive() {
        while (true) {
            try {
                comm.sendAlive();
                Thread.sleep(ApplicationConf.getInstance().getAliveInterval());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Method to be invoked asynchronously when manual show to reset the UI control status in the configured timeout
     */
    public synchronized void manualShot()
    {
        comm.sendShotCommand();

        try {
            Thread.sleep(200);
        }catch (InterruptedException e1) {}

        //ui.getToolsPanel().diodeShotControlOff();
    }

    static ICommunication instance = new InnerCommunication();

    public static ICommunication getICommunication(){
        return instance;
    }
}
