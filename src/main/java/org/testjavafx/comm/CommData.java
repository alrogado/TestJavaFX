package org.testjavafx.comm;

import org.testjavafx.conf.ApplicationSettings;

/**
 * Created by alvaro.lopez on 07/07/2017.
 */
public class CommData {

    // Globar definitions
    public static final float UNKNOWN_VALUE = -99999999999999999999999999999999999999f;
    public static final int UNKNOWN_INTEGER_VALUE = -999999999;
    
	
	/* *******************************************************************************************
	 *                                           INPUTS	
	 * *******************************************************************************************/

    /**
     * Laser trigger activation signal
     */
    public boolean triggerActivated = false;
    /**
     * Pedal activation signal
     */
    public boolean pedalActivated = false;
    /**
     * Interllock activation signal
     */
    public boolean interlockActivated = true;
    /**
     * Key activation signal
     */
    public boolean keyActivated = false;
    /**
     * Flow activation signal
     */
    public boolean flowActivated = false;

    /**
     * Flow value
     */
    public float flowValue = UNKNOWN_VALUE;
    /**
     * Tip temperature
     */
    public float tipTemperature = UNKNOWN_VALUE;
    /**
     * Diode temperature
     */
    public float diodeTemperature = UNKNOWN_VALUE;
    /**
     * Tank temperature
     */
    public float tankTemperature = UNKNOWN_VALUE;
    /**
     * Machine temperature
     */
    public float machineTemperature = UNKNOWN_VALUE;
    /**
     * Power source voltage
     */
    public float psVoltage = UNKNOWN_VALUE;
    /**
     * Real diode current
     */
    public float realCurrent = UNKNOWN_VALUE;

    /**
     * Driver error signal
     */
    public boolean driverError = false;
    /**
     * Tip probe error signal
     */
    public boolean tipProbeError = false;
    /**
     * Diode probe error signal
     */
    public boolean diodeProbeError = false;
    /**
     * Tank probe error signal
     */
    public boolean tankProbeError = false;
    /**
     * Tank low level signal
     */
    public boolean tankLowLevelError = false;
    /**
     * Flow error signal
     */
    public boolean flowError = false;
    /**
     * Machine probe error signal
     */
    public boolean machineProbeError = false;
    /**
     * High compression error signal
     */
    public boolean highCompressionError = false;
    /**
     * Low compression error signal
     */
    public boolean lowCompressionError = false;
    /**
     * High tip temperature error signal
     */
    public boolean highTipTemperatureError = false;
    /**
     * High diode temperature error signal
     */
    public boolean highDiodeTemperatureError = false;
    /**
     * High machine temperature error signal
     */
    public boolean highMachineTemperatureError = false;
    /**
     * High tank temperature error signal
     */
    public boolean highTankTemperatureError = false;
    /**
     * Low tip temperature error signal
     */
    public boolean lowTipTemperatureError = false;
    /**
     * Low diode temperature error signal
     */
    public boolean lowDiodeTemperatureError = false;
    /**
     * Low machine temperature error signal
     */
    public boolean lowMachineTemperatureError = false;
    /**
     * Low tank temperature error signal
     */
    public boolean lowTankTemperatureError = false;
    /**
     * DS1307 Error
     */
    public boolean ds1307Error = false;
    /**
     * Total counter read error
     */
    public boolean deeError = false;

    /**
     * Communications error signal
     */
    public boolean communicationError = false;
	
	/* *******************************************************************************************
	 *                                     INPUTS/OUTPUTS	
	 * *******************************************************************************************/
    /**
     * Session ready signal
     */
    public boolean sessionReady = false;
	

	/* *******************************************************************************************
	 *                                         OUTPUTS	
	 * *******************************************************************************************/
    /**
     * Pedal enabling signal
     */
    public boolean pedalEnabled = false;
    /**
     * Trigger enabling signal
     */
    public boolean triggerEnabled = false;
    /**
     * Manual mode signal
     */
    public int workingMode = 1;
    /**
     * Pump activation signal
     */
    public boolean pumpActivated = false;
    /**
     * Compressor activation signal
     */
    public boolean compressorActivated = false;
    /**
     * External fan activation signal
     */
    public boolean externalFanActivated = false;
    /**
     * Internal fan activation signal
     */
    public boolean internalFanActivated = false;
    /**
     * TEC activation signal
     */
    public boolean tecActivated = false;
    /**
     * Power source activation signal
     */
    public boolean psActivated = false;
    /**
     * Laser enabling signal
     */
    public boolean laserEnabled = false;
    /**
     * Orange temperature hysteresis
     */
    public float tipTempHysteresisWarn = UNKNOWN_VALUE;
    /**
     * Red temperature hysteresis
     */
    public float tipTempHysteresisMax = UNKNOWN_VALUE;
    /**
     * Tip minimum temperature set-point
     */
    public float tipWorkTempSetpoint = UNKNOWN_VALUE;
    /**
     * Tip maximum temperature set-point
     */
    public float tipMaxTempSetpoint = UNKNOWN_VALUE;
    /**
     * Diode minimum temperature set-point
     */
    public float diodeWorkTempSetpoint = UNKNOWN_VALUE;
    /**
     * Diode maximum temperature set-point
     */
    public float diodeMaxTempSetpoint = UNKNOWN_VALUE;
    /**
     * Tank minimum temperature set-point
     */
    public float tankWorkTempSetpoint = UNKNOWN_VALUE;
    /**
     * Tank maximum temperature set-point
     */
    public float tankMaxTempSetpoint = UNKNOWN_VALUE;
    /**
     * Machine minimum temperature set-point
     */
    public float machineMinTempSetpoint = UNKNOWN_VALUE;
    /**
     * Machine maximum temperature set-point
     */
    public float machineMaxTempSetpoint = UNKNOWN_VALUE;

    /**
     * Tip hysteresis temperature set-point
     */
    public float tipTempHysteresis = UNKNOWN_VALUE;
    /**
     * Diode hysteresis temperature set-point
     */
    public float diodeTempHysteresis = UNKNOWN_VALUE;
    /**
     * Tank hysteresis temperature up set-point
     */
    public float tankTempHysteresisUp = UNKNOWN_VALUE;
    /**
     * Tank hysteresis temperature low set-point
     */
    public float tankTempHysteresisDown = UNKNOWN_VALUE;
    /**
     * Machine hysteresis temperature set-point
     */
    public float machineTempHysteresis = UNKNOWN_VALUE;
    /**
     * Total shots
     */
    public long totalPulses = 0;


    /**
     * Session current
     */
    public float sessionCurrent = UNKNOWN_VALUE;
    /**
     * Session pulse on time
     */
    public float sessionPulseOnTime = UNKNOWN_VALUE;
    /**
     * Session pulse off time
     */
    public float sessionPulseOffTime = UNKNOWN_VALUE;
    /**
     * Session pulse sequence
     */
    public float sessionPulseSequence = UNKNOWN_VALUE;

    /**
     * Micro version
     */
    public String microVersion = "";

    /**
     * Date time
     */
    public int second = UNKNOWN_INTEGER_VALUE;
    public int minute = UNKNOWN_INTEGER_VALUE;
    public int hour = UNKNOWN_INTEGER_VALUE;
    public int dayOfWeek = UNKNOWN_INTEGER_VALUE;
    public int dayOfMonth = UNKNOWN_INTEGER_VALUE;
    public int month = UNKNOWN_INTEGER_VALUE;
    public int year = UNKNOWN_INTEGER_VALUE;

    /**
     * Gets the hexadecimal value for the given bytes
     *
     * @param bytes Byte array to get transformation
     * @return Hexadecimal representation
     */
    public static String getHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xff));
            sb.append(" ");
        }

        return sb.toString();
    }

    /**
     * Gets the value bytes for communications corresponding to the given data code
     *
     * @param code Data code to get value of
     * @return Value bytes
     */
    public byte[] getValue(byte code) {
        byte[] bytes = null;

        if (code == Constants.COMM_TRIGGER_ACTIVATED)
            bytes = booleanToBytes(triggerActivated, ApplicationSettings.getInstance().getActivationSignalTrigger());
        else if (code == Constants.COMM_PEDAL_ACTIVATED)
            bytes = booleanToBytes(pedalActivated, ApplicationSettings.getInstance().getActivationSignalPedal());
        else if (code == Constants.COMM_INTERLOCK_ACTIVATED)
            bytes = booleanToBytes(interlockActivated, ApplicationSettings.getInstance().getActivationSignalInterlock());
        else if (code == Constants.COMM_KEY_ACTIVATED)
            bytes = booleanToBytes(keyActivated, ApplicationSettings.getInstance().getActivationSignalKey());
        else if (code == Constants.COMM_FLOW_ACTIVATED)
            bytes = booleanToBytes(flowActivated, ApplicationSettings.getInstance().getActivationSignalFlow());
        else if (code == Constants.COMM_FLOW_VALUE)
            bytes = valueToBytes(flowValue);
        else if (code == Constants.COMM_TIP_TEMPERATURE)
            bytes = valueToBytes(tipTemperature);
        else if (code == Constants.COMM_DIODE_TEMPERATURE)
            bytes = valueToBytes(diodeTemperature);
        else if (code == Constants.COMM_TANK_TEMPERATURE)
            bytes = decimal2ValueToBytes(tankTemperature);
        else if (code == Constants.COMM_MACHINE_TEMPERATURE)
            bytes = valueToBytes(machineTemperature);
        else if (code == Constants.COMM_PS_VOLTAGE)
            bytes = decimal3ValueToBytes(psVoltage);
        else if (code == Constants.COMM_CURRENT)
            bytes = valueToBytes(realCurrent);
        else if (code == Constants.COMM_ERROR_DRIVER)
            bytes = booleanToBytes(driverError, 1);
        else if (code == Constants.COMM_ERROR_TIP_PROBE)
            bytes = booleanToBytes(tipProbeError, 1);
        else if (code == Constants.COMM_ERROR_DIODE_PROBE)
            bytes = booleanToBytes(diodeProbeError, 1);
        else if (code == Constants.COMM_ERROR_TANK_PROBE)
            bytes = booleanToBytes(tankProbeError, 1);
        else if (code == Constants.COMM_ERROR_TANK_LOW_LEVEL_ACTIVATED)
            bytes = booleanToBytes(tankLowLevelError, ApplicationSettings.getInstance().getActivationSignalTankLowLevel());
        else if (code == Constants.COMM_ERROR_FLOW)
            bytes = booleanToBytes(flowError, ApplicationSettings.getInstance().getActivationSignalFlowError());
        else if (code == Constants.COMM_ERROR_MACHINE_PROBE)
            bytes = booleanToBytes(machineProbeError, 1);
        else if (code == Constants.COMM_ERROR_HIGH_COMPRESSION)
            bytes = booleanToBytes(highCompressionError, ApplicationSettings.getInstance().getActivationSignalHighCompression());
        else if (code == Constants.COMM_ERROR_LOW_COMPRESSION)
            bytes = booleanToBytes(lowCompressionError, ApplicationSettings.getInstance().getActivationSignalLowCompression());
        else if (code == Constants.COMM_ERROR_HIGH_TEMPERATURE_TIP)
            bytes = booleanToBytes(highTipTemperatureError, 1);
        else if (code == Constants.COMM_ERROR_HIGH_TEMPERATURE_DIODE)
            bytes = booleanToBytes(highDiodeTemperatureError, 1);
        else if (code == Constants.COMM_ERROR_HIGH_TEMPERATURE_MACHINE)
            bytes = booleanToBytes(highMachineTemperatureError, 1);
        else if (code == Constants.COMM_ERROR_HIGH_TEMPERATURE_TANK)
            bytes = booleanToBytes(highTankTemperatureError, 1);
        else if (code == Constants.COMM_ERROR_LOW_TEMPERATURE_TIP)
            bytes = booleanToBytes(lowTipTemperatureError, 1);
        else if (code == Constants.COMM_ERROR_LOW_TEMPERATURE_DIODE)
            bytes = booleanToBytes(lowDiodeTemperatureError, 1);
        else if (code == Constants.COMM_ERROR_LOW_TEMPERATURE_MACHINE)
            bytes = booleanToBytes(lowMachineTemperatureError, 1);
        else if (code == Constants.COMM_ERROR_LOW_TEMPERATURE_TANK)
            bytes = booleanToBytes(lowTankTemperatureError, 1);
        else if (code == Constants.COMM_ERROR_DS1307)
            bytes = booleanToBytes(ds1307Error, 1);
        else if (code == Constants.COMM_ERROR_DEE)
            bytes = booleanToBytes(deeError, 1);
        else if (code == Constants.COMM_SESSION_READY)
            bytes = booleanToBytes(sessionReady, 1);
        else if (code == Constants.COMM_PEDAL_ENABLED)
            bytes = booleanToBytes(pedalEnabled, 1);
        else if (code == Constants.COMM_TRIGGER_ENABLED)
            bytes = booleanToBytes(triggerEnabled, 1);
        else if (code == Constants.COMM_WORKING_MODE)
            bytes = intToBytes(workingMode);
        else if (code == Constants.COMM_PUMP_ACTIVATED)
            bytes = booleanToBytes(pumpActivated, 1);
        else if (code == Constants.COMM_COMPRESSOR_ACTIVATED)
            bytes = booleanToBytes(compressorActivated, 1);
        else if (code == Constants.COMM_EXTERNAL_FAN_ACTIVATED)
            bytes = booleanToBytes(externalFanActivated, 1);
        else if (code == Constants.COMM_INTERNAL_FAN_ACTIVATED)
            bytes = booleanToBytes(internalFanActivated, 1);
        else if (code == Constants.COMM_TEC_ACTIVATED)
            bytes = booleanToBytes(tecActivated, 1);
        else if (code == Constants.COMM_PS_ACTIVATED)
            bytes = booleanToBytes(psActivated, 1);
        else if (code == Constants.COMM_LASER_ENABLED)
            bytes = booleanToBytes(laserEnabled, 1);
        else if (code == Constants.COMM_ORANGE_HYSTERESIS)
            bytes = valueToBytes(tipTempHysteresisWarn);
        else if (code == Constants.COMM_RED_HYSTERESIS)
            bytes = valueToBytes(tipTempHysteresisMax);
        else if (code == Constants.COMM_TIP_WORK_TEMP)
            bytes = valueToBytes(tipWorkTempSetpoint);
        else if (code == Constants.COMM_TIP_MAX_TEMP)
            bytes = valueToBytes(tipMaxTempSetpoint);
        else if (code == Constants.COMM_TIP_TEMP_HYSTERESIS)
            bytes = valueToBytes(tipTempHysteresis);
        else if (code == Constants.COMM_DIODE_WORK_TEMP)
            bytes = valueToBytes(diodeWorkTempSetpoint);
        else if (code == Constants.COMM_DIODE_MAX_TEMP)
            bytes = valueToBytes(diodeMaxTempSetpoint);
        else if (code == Constants.COMM_DIODE_TEMP_HYSTERESIS)
            bytes = valueToBytes(diodeTempHysteresis);
        else if (code == Constants.COMM_TANK_WORK_TEMP)
            bytes = valueToBytes(tankWorkTempSetpoint);
        else if (code == Constants.COMM_TANK_MAX_TEMP)
            bytes = valueToBytes(tankMaxTempSetpoint);
        else if (code == Constants.COMM_TANK_TEMP_HYSTERESIS_UP)
            bytes = valueToBytes(tankTempHysteresisUp);
        else if (code == Constants.COMM_TANK_TEMP_HYSTERESIS_DOWN)
            bytes = valueToBytes(tankTempHysteresisDown);
        else if (code == Constants.COMM_MACHINE_MIN_TEMP)
            bytes = valueToBytes(machineMinTempSetpoint);
        else if (code == Constants.COMM_MACHINE_MAX_TEMP)
            bytes = valueToBytes(machineMaxTempSetpoint);
        else if (code == Constants.COMM_MACHINE_TEMP_HYSTERESIS)
            bytes = valueToBytes(machineTempHysteresis);
        else if (code == Constants.COMM_SESSION_CURRENT)
            bytes = valueToBytes(sessionCurrent);
        else if (code == Constants.COMM_SESSION_PULSE_ON)
            bytes = valueToBytes(sessionPulseOnTime);
        else if (code == Constants.COMM_SESSION_PULSE_OFF)
            bytes = valueToBytes(sessionPulseOffTime);
        else if (code == Constants.COMM_SESSION_PULSE_SEQUENCE)
            bytes = valueToBytes(sessionPulseSequence);
        else if (code == Constants.COMM_TOTAL_SHOT_H)
            bytes = intToBytes((int) ((totalPulses >> 16) & 0x0000FFFF));
        else if (code == Constants.COMM_TOTAL_SHOT_L)
            bytes = intToBytes((int) (totalPulses & 0x0000FFFF));

        return bytes;
    }

    /**
     * Sets the corresponding value of the given data code with the communication bytes value
     *
     * @param code  Data code
     * @param bytes Communication bytes
     */
    public void setValue(byte code, byte[] bytes) {
        if (code == Constants.COMM_TRIGGER_ACTIVATED)
            triggerActivated = bytesToBoolean(bytes, ApplicationSettings.getInstance().getActivationSignalTrigger());
        else if (code == Constants.COMM_PEDAL_ACTIVATED)
            pedalActivated = bytesToBoolean(bytes, ApplicationSettings.getInstance().getActivationSignalPedal());
        else if (code == Constants.COMM_INTERLOCK_ACTIVATED)
            interlockActivated = bytesToBoolean(bytes, ApplicationSettings.getInstance().getActivationSignalInterlock());
        else if (code == Constants.COMM_KEY_ACTIVATED)
            keyActivated = bytesToBoolean(bytes, ApplicationSettings.getInstance().getActivationSignalKey());
        else if (code == Constants.COMM_FLOW_ACTIVATED)
            flowActivated = bytesToBoolean(bytes, ApplicationSettings.getInstance().getActivationSignalFlow());
        else if (code == Constants.COMM_FLOW_VALUE)
            flowValue = bytesToValue(bytes);
        else if (code == Constants.COMM_TIP_TEMPERATURE)
            tipTemperature = bytesToValue(bytes);
        else if (code == Constants.COMM_DIODE_TEMPERATURE)
            diodeTemperature = bytesToValue(bytes);
        else if (code == Constants.COMM_TANK_TEMPERATURE)
            tankTemperature = bytesToDecimal2Value(bytes);
        else if (code == Constants.COMM_MACHINE_TEMPERATURE)
            machineTemperature = bytesToValue(bytes);
        else if (code == Constants.COMM_PS_VOLTAGE)
            psVoltage = bytesToDecimal3Value(bytes);
        else if (code == Constants.COMM_CURRENT)
            realCurrent = bytesToValue(bytes);
        else if (code == Constants.COMM_ERROR_DRIVER)
            driverError = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_ERROR_TIP_PROBE)
            tipProbeError = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_ERROR_DIODE_PROBE)
            diodeProbeError = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_ERROR_TANK_PROBE)
            tankProbeError = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_ERROR_TANK_LOW_LEVEL_ACTIVATED)
            tankLowLevelError = bytesToBoolean(bytes, ApplicationSettings.getInstance().getActivationSignalTankLowLevel());
        else if (code == Constants.COMM_ERROR_FLOW)
            flowError = bytesToBoolean(bytes, ApplicationSettings.getInstance().getActivationSignalFlowError());
        else if (code == Constants.COMM_ERROR_MACHINE_PROBE)
            machineProbeError = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_ERROR_HIGH_COMPRESSION)
            highCompressionError = bytesToBoolean(bytes, ApplicationSettings.getInstance().getActivationSignalHighCompression());
        else if (code == Constants.COMM_ERROR_LOW_COMPRESSION)
            lowCompressionError = bytesToBoolean(bytes, ApplicationSettings.getInstance().getActivationSignalLowCompression());
        else if (code == Constants.COMM_ERROR_HIGH_TEMPERATURE_TIP)
            highTipTemperatureError = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_ERROR_HIGH_TEMPERATURE_DIODE)
            highDiodeTemperatureError = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_ERROR_HIGH_TEMPERATURE_MACHINE)
            highMachineTemperatureError = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_ERROR_HIGH_TEMPERATURE_TANK)
            highTankTemperatureError = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_ERROR_LOW_TEMPERATURE_TIP)
            lowTipTemperatureError = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_ERROR_LOW_TEMPERATURE_DIODE)
            lowDiodeTemperatureError = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_ERROR_LOW_TEMPERATURE_MACHINE)
            lowMachineTemperatureError = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_ERROR_LOW_TEMPERATURE_TANK)
            lowTankTemperatureError = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_ERROR_DS1307)
            ds1307Error = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_ERROR_DEE)
            deeError = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_SESSION_READY)
            sessionReady = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_PEDAL_ENABLED)
            pedalEnabled = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_TRIGGER_ENABLED)
            triggerEnabled = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_WORKING_MODE)
            workingMode = bytesToInt(bytes);
        else if (code == Constants.COMM_PUMP_ACTIVATED)
            pumpActivated = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_COMPRESSOR_ACTIVATED)
            compressorActivated = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_EXTERNAL_FAN_ACTIVATED)
            externalFanActivated = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_INTERNAL_FAN_ACTIVATED)
            internalFanActivated = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_TEC_ACTIVATED)
            tecActivated = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_PS_ACTIVATED)
            psActivated = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_LASER_ENABLED)
            laserEnabled = bytesToBoolean(bytes, 1);
        else if (code == Constants.COMM_ORANGE_HYSTERESIS)
            tipTempHysteresisWarn = bytesToValue(bytes);
        else if (code == Constants.COMM_RED_HYSTERESIS)
            tipTempHysteresisMax = bytesToValue(bytes);
        else if (code == Constants.COMM_TIP_WORK_TEMP)
            tipWorkTempSetpoint = bytesToValue(bytes);
        else if (code == Constants.COMM_TIP_MAX_TEMP)
            tipMaxTempSetpoint = bytesToValue(bytes);
        else if (code == Constants.COMM_TIP_TEMP_HYSTERESIS)
            tipTempHysteresis = bytesToValue(bytes);
        else if (code == Constants.COMM_DIODE_WORK_TEMP)
            diodeWorkTempSetpoint = bytesToValue(bytes);
        else if (code == Constants.COMM_DIODE_MAX_TEMP)
            diodeMaxTempSetpoint = bytesToValue(bytes);
        else if (code == Constants.COMM_DIODE_TEMP_HYSTERESIS)
            diodeTempHysteresis = bytesToValue(bytes);
        else if (code == Constants.COMM_TANK_WORK_TEMP)
            tankWorkTempSetpoint = bytesToValue(bytes);
        else if (code == Constants.COMM_TANK_MAX_TEMP)
            tankMaxTempSetpoint = bytesToValue(bytes);
        else if (code == Constants.COMM_TANK_TEMP_HYSTERESIS_UP)
            tankTempHysteresisUp = bytesToValue(bytes);
        else if (code == Constants.COMM_TANK_TEMP_HYSTERESIS_DOWN)
            tankTempHysteresisDown = bytesToValue(bytes);
        else if (code == Constants.COMM_MACHINE_MIN_TEMP)
            machineMinTempSetpoint = bytesToValue(bytes);
        else if (code == Constants.COMM_MACHINE_MAX_TEMP)
            machineMaxTempSetpoint = bytesToValue(bytes);
        else if (code == Constants.COMM_MACHINE_TEMP_HYSTERESIS)
            machineTempHysteresis = bytesToValue(bytes);
        else if (code == Constants.COMM_SESSION_CURRENT)
            sessionCurrent = bytesToValue(bytes);
        else if (code == Constants.COMM_SESSION_PULSE_ON)
            sessionPulseOnTime = bytesToValue(bytes);
        else if (code == Constants.COMM_SESSION_PULSE_OFF)
            sessionPulseOffTime = bytesToValue(bytes);
        else if (code == Constants.COMM_SESSION_PULSE_SEQUENCE)
            sessionPulseSequence = bytesToValue(bytes);
        else if (code == Constants.COMM_MICRO_VERSION)
            microVersion = bytesToVersion(bytes);
        else if (code == Constants.COMM_TOTAL_SHOT_H)
            totalPulses = ((bytesToInt(bytes) << 16) & 0xFFFF0000) + (totalPulses & 0x0000FFFF);
        else if (code == Constants.COMM_TOTAL_SHOT_L)
            totalPulses = (totalPulses & 0xFFFF0000) + (bytesToInt(bytes) & 0x0000FFFF);
    }

    /**
     * Convert the version bytes to a version string
     *
     * @param bytes Version bytes
     * @return Version string
     */
    String bytesToVersion(byte[] bytes) {
        return "/" + bytes[Constants.VALUE_LENGTH - 2] + "." + bytes[Constants.VALUE_LENGTH - 1];
    }

    /**
     * Convert integer value to communication bytes
     *
     * @param value Value to convert
     * @return Value communication bytes
     */
    byte[] intToBytes(int value) {
        boolean negative = false;
        byte[] result = new byte[Constants.VALUE_LENGTH];

        if (value < 0) {
            value = -value;
            negative = true;
        }

        for (int i = 0; i < Constants.VALUE_LENGTH; i++) {
            result[i] = (byte) (value >> (Constants.VALUE_LENGTH - 1 - i) * 8);
        }

        if (negative) result[0] = (byte) (result[0] | 0x80);

        return result;
    }

    /**
     * Convert normal application values (floats) to communications value bytes
     *
     * @param value Value to convert
     * @return Value communication bytes
     */
    byte[] valueToBytes(float value) {
        return intToBytes((int) (value));
    }

    /**
     * Convert 3 decimal application values (floats) to communications value bytes
     *
     * @param value Value to convert
     * @return Value communication bytes
     */
    byte[] decimal3ValueToBytes(float value) {
        return intToBytes((int) (value * 1000f));
    }

    /**
     * Convert 2 decimal application values (floats) to communications value bytes
     *
     * @param value Value to convert
     * @return Value communication bytes
     */
    byte[] decimal2ValueToBytes(float value) {
        return intToBytes((int) (value * 100f));
    }

    /**
     * Convert signals (booleans) to communications value bytes
     *
     * @param value Value to convert
     * @return Value communication bytes
     */
    byte[] booleanToBytes(boolean value, int activationValue) {
        if (activationValue == 0)
            return intToBytes(value ? 0 : 1);
        else
            return intToBytes(value ? 1 : 0);
    }

    /**
     * Convert communications bytes value to integer value
     *
     * @param bytes Bytes to be converted
     * @return Integer value
     */
    int bytesToInt(byte[] bytes) {
        boolean negative = false;
        int result = 0;

        if ((bytes[0] & 0x80) != 0) {
            bytes[0] = (byte) (bytes[0] & 0x7F);
            negative = true;
        }

        for (int i = 0; i < Constants.VALUE_LENGTH; i++) {
            result = result | (bytes[i] & 0xFF) << (Constants.VALUE_LENGTH - 1 - i) * 8;
        }

        if (negative) result = -result;

        return result;
    }

    /**
     * Converts communication bytes value to normal application values (floats)
     *
     * @param bytes Bytes to be converted
     * @return value
     */
    float bytesToValue(byte[] bytes) {
        return ((float) bytesToInt(bytes));
    }

    /**
     * Converts communication bytes value to 3 decimal application values (floats)
     *
     * @param bytes Bytes to be converted
     * @return value
     */
    float bytesToDecimal3Value(byte[] bytes) {
        return ((float) bytesToInt(bytes)) / 1000f;
    }

    /**
     * Converts communication bytes value to 2 decimal application values (floats)
     *
     * @param bytes Bytes to be converted
     * @return value
     */
    float bytesToDecimal2Value(byte[] bytes) {
        return ((float) bytesToInt(bytes)) / 100f;
    }

    /**
     * Converts communication bytes value to signal application values (booleans)
     *
     * @param bytes Bytes to be converted
     * @return value
     */
    boolean bytesToBoolean(byte[] bytes, int activationValue) {
        return bytesToInt(bytes) == activationValue;
    }

    /**
     * Gets the hexadecimal value for the given byte
     *
     * @param code Byte to get transformation
     * @return Hexadecimal representation
     */
    public String getValueHexString(byte code) {
        byte[] bytes = getValue(code);

        return getHexString(bytes);
    }
}
