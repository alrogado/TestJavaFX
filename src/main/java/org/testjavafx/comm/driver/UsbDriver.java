package org.testjavafx.comm.driver;

import org.usb4java.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

/**
 * Created by alvaro.lopez on 07/07/2017.
 */
public class UsbDriver implements ICommDriver {
    // Global definitions
    private static final long WRITE_TIMEOUT = 50;
    private static final long READ_TIMEOUT = 100;

    /**
     * The USB device Vendor Identifier
     */
    short usbVendorId;
    /**
     * The USB device Product Identifier
     */
    short usbProductId;
    /**
     * The USB interface
     */
    int usbInterface;
    /**
     * The input endpoint
     */
    byte usbInEndpoint;
    /**
     * The output endpoint
     */
    byte usbOutEndpoint;


    /**
     * Indicates when communication is available
     */
    boolean available;
    /**
     * USB context
     */
    Context context;
    /**
     * USB device handle
     */
    DeviceHandle deviceHandle;


    @Override
    public void initialize(String arguments) {
        String[] args = arguments.split(",");
        this.usbVendorId = Short.parseShort(args[0]);
        this.usbProductId = Short.parseShort(args[1]);

        this.usbInterface = Integer.parseInt(args[2]);

        this.usbInEndpoint = args[3].startsWith("0x") ? Byte.parseByte(args[3], 16) : Byte.parseByte(args[3]);
        this.usbOutEndpoint = args[4].startsWith("0x") ? Byte.parseByte(args[4], 16) : Byte.parseByte(args[4]);
    }

    @Override
    public void start() throws IOException {
        this.available = false;
        // Creating context object
        context = new Context();

        // Initialize the libusb context
        int result = LibUsb.init(context);
        if (result != LibUsb.SUCCESS)
            throw new LibUsbException("Unable to initialize libusb", result);

        // Open device
        DeviceHandle handle = LibUsb.openDeviceWithVidPid(null, this.usbVendorId, this.usbProductId);

        if (handle == null)
            throw new IOException("Usb device not found");

        // Claim interface
        if (LibUsb.hasCapability(LibUsb.CAP_SUPPORTS_DETACH_KERNEL_DRIVER) && (LibUsb.kernelDriverActive(handle, this.usbInterface) == 1)) {
            result = LibUsb.detachKernelDriver(handle, this.usbInterface);
            if (result != LibUsb.SUCCESS)
                throw new LibUsbException("Unable to detach kernel driver", result);
        }

        result = LibUsb.claimInterface(handle, this.usbInterface);
        if (result != LibUsb.SUCCESS)
            throw new LibUsbException("Unable to claim interface", result);

        this.available = true;
    }

    @Override
    public void stop() {
        if (this.available) {
            int result = LibUsb.releaseInterface(deviceHandle, this.usbInterface);
            if (result != LibUsb.SUCCESS) throw new LibUsbException("Unable to release interface", result);

            LibUsb.close(deviceHandle);
            LibUsb.exit(context);
        }
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void sendBytes(byte[] bytes) {
        ByteBuffer buffer = BufferUtils.allocateByteBuffer(bytes.length);
        buffer.put(bytes);

        IntBuffer transferred = BufferUtils.allocateIntBuffer();

        int result = LibUsb.bulkTransfer(deviceHandle, usbOutEndpoint, buffer, transferred, WRITE_TIMEOUT);

        if (result != LibUsb.SUCCESS)
            throw new LibUsbException("Unable to send data", result);
    }

    @Override
    public void readBytes(byte[] bytes, int offset, int length) {
        ByteBuffer buffer = BufferUtils.allocateByteBuffer(length).order(ByteOrder.LITTLE_ENDIAN);

        IntBuffer transferred = BufferUtils.allocateIntBuffer();

        int result = LibUsb.bulkTransfer(deviceHandle, usbInEndpoint, buffer, transferred, READ_TIMEOUT);

        if (result != LibUsb.SUCCESS)
            throw new LibUsbException("Unable to read data", result);

        buffer.rewind();
        buffer.get(bytes, offset, length);
    }

}
