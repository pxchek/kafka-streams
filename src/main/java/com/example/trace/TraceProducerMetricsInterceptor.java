package com.example.trace;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.header.Headers;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class TraceProducerMetricsInterceptor implements ProducerInterceptor<Alert, String> {
    @Override
    public ProducerRecord<Alert, String> onSend(ProducerRecord<Alert, String> record) {
        Headers headers = record.headers();
        String traceId = UUID.randomUUID().toString();
        headers.add("TraceID", traceId.getBytes(StandardCharsets.UTF_8));
        System.out.println("Created TraceID: " +traceId);
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if(Objects.nonNull(exception)) {
            System.out.println("Error: " + exception.getMessage());
        } else {
            System.out.println("Acknowledgement from Producer Worker");
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
