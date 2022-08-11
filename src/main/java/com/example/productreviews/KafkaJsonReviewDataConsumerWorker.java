package com.example.productreviews;

import com.example.trace.Alert;
import com.example.trace.TraceConsumerMetricsInterceptor;
import com.example.trace.TraceProducerMetricsInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaJsonReviewDataConsumerWorker {
    private static final String BROKERS = "10.0.0.213:9092, 10.0.0.213:9093, 10.0.0.213:9094";

    public static void main(String[] args) {
        Properties props = new Properties();

        props.put("bootstrap.servers", BROKERS);
        props.put("group.id", "quickstart-events");
        props.put("key.deserializer", "com.example.serializers.AlertSerializer");
        props.put("value.deserializer", "com.example.productreviews.KafkaJsonReviewDataDeserializer");
        props.put("interceptor.classes", TraceConsumerMetricsInterceptor.class.getName());
        KafkaConsumer<Alert, ReviewData> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("quickstart-events"));

        Duration timeout = Duration.ofMillis(100);

        while (true) {
            ConsumerRecords<Alert, ReviewData> records = consumer.poll(timeout);

            for (ConsumerRecord<Alert, ReviewData> record : records) {
                System.out.printf("topic = %s, partition = %d, offset = %d, " + "reviewerID = %s, reviewerName = %s\n", record.topic(),
                        record.partition(), record.offset(), record.value().reviewerID, record.value().reviewerName);
            }
        }
    }
}
