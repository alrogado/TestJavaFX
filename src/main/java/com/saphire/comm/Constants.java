package com.saphire.comm;

/**
 * Created by alvaro.lopez on 07/07/2017.
 */
public class Constants {
    // ASCII codes
    public static final byte ACK = 0x06;
    public static final byte NAK = 0x15;
    public static final byte SOH = 0x01;
    public static final byte FS = 0x1C;
    // Global definitions
    static final int MAX_COMM_ERRORS = 10;
    static final String DRIVER_SOCKET = "SOCKET";
    static final String DRIVER_USB = "USB";
    static final String DRIVER_RXTX = "RXTX";
    // Message information
    static final int MESSAGE_LENGTH = 6;
    static final int SOH_POSITION = 0;
    static final int CODE_POSITION = 1;
    static final int FS_POSITION = 2;
    static final int VALUE_POSITION = 3;
    static final int VALUE_LENGTH = 2;
    static final int CRC_POSITION = 5;
    static final byte[] VALUE_EMPTY = new byte[]{0x00, 0x00};
    // Input data codes
    static final byte COMM_TRIGGER_ACTIVATED = 0x20;
    static final byte COMM_PEDAL_ACTIVATED = 0x21;
    static final byte COMM_INTERLOCK_ACTIVATED = 0x22;
    static final byte COMM_KEY_ACTIVATED = 0x23;
    static final byte COMM_FLOW_ACTIVATED = 0x24;
    static final byte COMM_FLOW_VALUE = (byte) 0x91;
    static final byte COMM_TIP_TEMPERATURE = 0x25;
    static final byte COMM_DIODE_TEMPERATURE = 0x26;
    static final byte COMM_TANK_TEMPERATURE = 0x27;
    static final byte COMM_MACHINE_TEMPERATURE = 0x28;
    static final byte COMM_PS_VOLTAGE = 0x29;
    static final byte COMM_CURRENT = 0x2A;
    static final byte COMM_ERROR_DRIVER = 0x2B;
    static final byte COMM_ERROR_TIP_PROBE = 0x2C;
    static final byte COMM_ERROR_DIODE_PROBE = 0x2D;
    static final byte COMM_ERROR_TANK_PROBE = 0x2E;
    static final byte COMM_ERROR_TANK_LOW_LEVEL_ACTIVATED = 0x2F;
    static final byte COMM_ERROR_FLOW = 0x30;
    static final byte COMM_ERROR_MACHINE_PROBE = 0x31;
    static final byte COMM_ERROR_HIGH_COMPRESSION = 0x32;
    static final byte COMM_ERROR_LOW_COMPRESSION = 0x33;
    static final byte COMM_ERROR_HIGH_TEMPERATURE_TIP = 0x34;
    static final byte COMM_ERROR_HIGH_TEMPERATURE_DIODE = 0x35;
    static final byte COMM_ERROR_HIGH_TEMPERATURE_MACHINE = 0x36;
    static final byte COMM_ERROR_HIGH_TEMPERATURE_TANK = 0x37;
    static final byte COMM_ERROR_DS1307 = 0x38;
    static final byte COMM_ERROR_DEE = 0x39;
    static final byte COMM_ERROR_LOW_TEMPERATURE_TIP = 0x43;
    static final byte COMM_ERROR_LOW_TEMPERATURE_DIODE = 0x44;
    static final byte COMM_ERROR_LOW_TEMPERATURE_MACHINE = 0x45;
    static final byte COMM_ERROR_LOW_TEMPERATURE_TANK = 0x46;
    static final byte COMM_LASER_SHOT = 0x41;
    static final byte COMM_MICRO_VERSION = (byte) 0x92;
    // Input / output data codes
    static final byte COMM_SHUTDOWN = 0x42;
    // Output codes
    static final byte COMM_PEDAL_ENABLED = 0x50;
    static final byte COMM_TRIGGER_ENABLED = 0x70;
    static final byte COMM_WORKING_MODE = 0x51;
    static final byte COMM_PUMP_ACTIVATED = 0x52;
    static final byte COMM_COMPRESSOR_ACTIVATED = 0x53;
    static final byte COMM_EXTERNAL_FAN_ACTIVATED = 0x54;
    static final byte COMM_INTERNAL_FAN_ACTIVATED = 0x55;
    static final byte COMM_TEC_ACTIVATED = 0x56;
    static final byte COMM_PS_ACTIVATED = 0x57;
    static final byte COMM_LASER_ENABLED = 0x58;
    static final byte COMM_TIP_WORK_TEMP = 0x59;
    static final byte COMM_TIP_MAX_TEMP = 0x5A;
    static final byte COMM_TIP_TEMP_HYSTERESIS = 0x5B;
    static final byte COMM_RED_HYSTERESIS = 0x3B;
    static final byte COMM_ORANGE_HYSTERESIS = 0x3C;
    static final byte COMM_DIODE_WORK_TEMP = 0x5C;
    static final byte COMM_DIODE_MAX_TEMP = 0x5D;
    static final byte COMM_DIODE_TEMP_HYSTERESIS = 0x5E;
    static final byte COMM_TANK_WORK_TEMP = 0x5F;
    static final byte COMM_TANK_MAX_TEMP = 0x60;
    static final byte COMM_TANK_TEMP_HYSTERESIS_UP = 0x61;
    static final byte COMM_TANK_TEMP_HYSTERESIS_DOWN = 0x65;
    static final byte COMM_MACHINE_MIN_TEMP = 0x62;
    static final byte COMM_MACHINE_MAX_TEMP = 0x63;
    static final byte COMM_MACHINE_TEMP_HYSTERESIS = 0x64;
    static final byte COMM_SHOT_COMMAND = 0x67;
    static final byte COMM_START = 0x68;
    static final byte COMM_ALIVE = 0x69;
    static final byte COMM_SAVE_VARIABLES = 0x66;
    static final byte COMM_SESSION_CURRENT = 0x71;
    static final byte COMM_SESSION_PULSE_ON = 0x72;
    static final byte COMM_SESSION_PULSE_OFF = 0x73;
    static final byte COMM_SESSION_PULSE_SEQUENCE = 0x74;
    static final byte COMM_SESSION_READY = 0x40;
    static final byte COMM_TOTAL_SHOT_H = 0x75;
    static final byte COMM_TOTAL_SHOT_L = 0x76;
    static final byte[] READING_DATA = {

            Constants.COMM_TRIGGER_ACTIVATED,
            Constants.COMM_PEDAL_ACTIVATED,
            Constants.COMM_INTERLOCK_ACTIVATED,
            Constants.COMM_KEY_ACTIVATED,
            Constants.COMM_FLOW_ACTIVATED,
            Constants.COMM_FLOW_VALUE,
            Constants.COMM_TIP_TEMPERATURE,
            Constants.COMM_DIODE_TEMPERATURE,
            Constants.COMM_TANK_TEMPERATURE,
            Constants.COMM_MACHINE_TEMPERATURE,
            Constants.COMM_PS_VOLTAGE,
            Constants.COMM_CURRENT,
            Constants.COMM_ERROR_DRIVER,
            Constants.COMM_ERROR_TIP_PROBE,
            Constants.COMM_ERROR_DIODE_PROBE,
            Constants.COMM_ERROR_TANK_PROBE,
            Constants.COMM_ERROR_TANK_LOW_LEVEL_ACTIVATED,
            Constants.COMM_ERROR_FLOW,
            Constants.COMM_ERROR_MACHINE_PROBE,
            Constants.COMM_ERROR_HIGH_COMPRESSION,
            Constants.COMM_ERROR_LOW_COMPRESSION,
            Constants.COMM_SESSION_READY,
            Constants.COMM_MICRO_VERSION,
            Constants.COMM_TOTAL_SHOT_H,
            Constants.COMM_TOTAL_SHOT_L
    };
    // Request data code
    static final byte COMM_REQUEST_DATA = 0x79;
    // Acknowledge messages
    private static final byte[] ACK_MESSAGE = new byte[]{ACK};
    private static final byte[] NAK_MESSAGE = new byte[]{NAK};
    private static final byte[] START_MESSAGE_VALUE = new byte[]{0x00, 0x00};
    private static final byte[] ALIVE_MESSAGE_VALUE = new byte[]{0x00, 0x00};
    private static final byte[] SHOT_MESSAGE_VALUE = new byte[]{0x00, 0x00};
    private static final byte[] SHUTDOWN_MESSAGE_VALUE = new byte[]{0x00, 0x00};
    // Communication parameters
    private static final int COMM_RETRIES = 3;
}
