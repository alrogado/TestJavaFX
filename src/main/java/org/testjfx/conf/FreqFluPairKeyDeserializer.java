package org.testjfx.conf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.io.IOException;

/**
 * Created by alvaro.lopez on 30/06/2017.
 */
public class FreqFluPairKeyDeserializer extends KeyDeserializer {
    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String[] split = key.split(",");
        return new FreqFluPair(
                Float.parseFloat(split[0]),
                Float.parseFloat(split[1]),
                new Pulse(
                        Float.parseFloat(split[2]),
                        Float.parseFloat(split[3]),
                        Float.parseFloat(split[4]),
                        Float.parseFloat(split[5])
                ));
    }
}
