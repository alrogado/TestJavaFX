package org.testjfx.comm;

import org.testjfx.conf.Configuration;

/**
 * Created by alvaro.lopez on 05/07/2017.
 */
public class Communication {

    ICommunication comm;

    public void sendAlive() {
        while (true) {
            try {
                comm.sendAlive();
                Thread.sleep(Configuration.getAliveInterval());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
