package org.testjfx.conf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.io.IOException;

/**
 * Created by alvaro.lopez on 30/06/2017.
 */
public class PulseKeyDeserializer extends KeyDeserializer {
    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return new Pulse(Float.parseFloat(key.split(",")[0]), Float.parseFloat(key.split(",")[1]), Float.parseFloat(key.split(",")[2]), Float.parseFloat(key.split(",")[3]));
    }
}
