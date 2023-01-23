package com.example.sqlogs;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaSqlLogConsumerWorker {
    private static final String BROKERS = "10.0.0.38:9092, 10.0.0.213:9093, 10.0.0.128:9094";

    public static void main(String[] args) {
        Properties props = new Properties();

        props.put("bootstrap.servers", BROKERS);
        props.put("group.id", "CountryCounter");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("sql-events"));

        Duration timeout = Duration.ofMillis(10_000);

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(timeout);

            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("topic = %s, "
                                + "partition = %d, "
                                + "offset = %d, "
                                + "key = %s, "
                                + "value = %s\n",
                        record.topic(),
                        record.partition(),
                        record.offset(),
                        record.key(),
                        record.value());
            }
        }
    }
}
