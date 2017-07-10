package com.saphire.comm.driver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by alvaro.lopez on 07/07/2017.
 */
public class SocketDriver implements ICommDriver {
    /**
     * Socket target host
     */
    String host;

    /**
     * Socket target port
     */
    int port;

    /**
     * Communication socket
     */
    Socket socket;
    /**
     * Socket input stream
     */
    InputStream in;
    /**
     * Socket output stream
     */
    OutputStream out;

    @Override
    public void initialize(String arguments) {
        String[] args = arguments.split(",");
        this.host = args[0];
        this.port = Integer.parseInt(args[1]);
    }

    @Override
    public void start() throws IOException {
        try {
            socket = new Socket(host, port);
            socket.setReceiveBufferSize(1);

            in = socket.getInputStream();
            out = socket.getOutputStream();
        } catch (Exception e) {
            in.close();
            out.close();
            socket.close();
        } finally {
            socket = null;
            in = null;
            out = null;
        }
    }

    @Override
    public boolean isAvailable() {
        return (in != null && out != null);
    }

    @Override
    public void sendBytes(byte[] bytes) throws IOException {
        out.write(bytes);
    }

    @Override
    public void readBytes(byte[] bytes, int offset, int length) throws IOException {
        in.read(bytes, offset, length);
    }

    @Override
    public void stop() throws IOException {
        in.close();
        out.close();
        socket.close();
        socket = null;
        in = null;
        out = null;
    }

}
