package com.example.trace;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;

import java.util.Map;

public class TraceConsumerMetricsInterceptor implements ConsumerInterceptor<Alert, String> {
    @Override
    public ConsumerRecords<Alert, String> onConsume(ConsumerRecords<Alert, String> records) {
        if(records.isEmpty()) {
            return records;
        } else {
            for(ConsumerRecord<Alert, String> record: records) {
                Headers headers = record.headers();
                for(Header header : headers) {
                    if("TraceID".equals(header.key())) {
                        System.out.println("TraceID is " + new String(header.value()));
                    }

                }
            }
        }
        return records;
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
