package com.example.productreviews;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaJsonReviewDataConsumerWorker {
    private static final String BROKERS = "localhost:49092, localhost:39092, localhost:29092";

    public static void main(String[] args) {
        Properties props = new Properties();

        props.put("bootstrap.servers", BROKERS);
        props.put("group.id", "quickstart-events");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "com.example.productreviews.KafkaJsonReviewDataDeserializer");

        KafkaConsumer<String, ReviewData> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("quickstart-events"));

        Duration timeout = Duration.ofMillis(100);

        while (true) {
            ConsumerRecords<String, ReviewData> records = consumer.poll(timeout);

            for (ConsumerRecord<String, ReviewData> record : records) {
                System.out.printf("topic = %s, partition = %d, offset = %d, " + "reviewerID = %s, reviewerName = %s\n", record.topic(),
                        record.partition(), record.offset(), record.value().reviewerID, record.value().reviewerName);
            }
        }
    }
}
