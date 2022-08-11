package com.example.serializers;

import com.example.trace.Alert;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class AlertSerializer implements Serializer<Alert>, Deserializer<Alert> {

    public byte[] serialize(String topic, Alert key) {
        if (key == null) {
            return null;
        }
        return key.getStageId().getBytes(StandardCharsets.UTF_8);
    }

    public Alert deserialize(String topic, byte[] value) {
        return null;
    }

    @Override
    public void configure(final Map<String, ?> configs, final boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }

}