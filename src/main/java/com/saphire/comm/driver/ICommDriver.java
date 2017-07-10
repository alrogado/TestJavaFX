package com.saphire.comm.driver;

import java.io.IOException;

/**
 * Created by alvaro.lopez on 07/07/2017.
 */
public interface ICommDriver {

    /**
     * Method for initializing the driver. It will receive the driver configured arguments
     *
     * @param arguments Arguments defined in application properties
     * @
     */
    public void initialize(String arguments);


    /**
     * Method for opening communication channels
     *
     * @
     */
    public void start() throws IOException;

    /**
     * Check if communication is available
     *
     * @return true if communication is available. Otherwise false
     */
    public boolean isAvailable();

    /**
     * Send the given bytes
     *
     * @param bytes Bytes to be sent
     * @
     */
    public void sendBytes(byte[] bytes) throws IOException;

    /**
     * Read the given length bytes from the communication channel and fill the given byte array from the given offset position
     *
     * @param bytes  Byte array to be filled with the read bytes
     * @param offset Start position for filling the target byte array
     * @param length Number of bytes to be read from the communication channel and filled in the target byte array
     * @
     */
    public void readBytes(byte[] bytes, int offset, int length) throws IOException;

    /**
     * Close the communication channel
     *
     * @
     */
    public void stop() throws IOException;
}
