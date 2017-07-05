package org.testjfx.comm;

import org.testjfx.conf.Configuration;

/**
 * Created by alvaro.lopez on 05/07/2017.
 */
public class CommunicationMgr {

    Communication comm;

    /**
     * Method for sending the screen alive signal to the micro
     */
    public void sendAliveSignal() {
        while (true) {
            comm.sendAlive();

            try {
                Thread.sleep(Configuration.commAliveSignalInterval);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
