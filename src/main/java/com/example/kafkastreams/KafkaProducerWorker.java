package com.example.kafkastreams;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class KafkaProducerWorker {
    private static final String BROKERS = "localhost:49092, localhost:39092, localhost:29092";

    public static void main(String[] args) throws InterruptedException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        Properties kafkaProps = new Properties();

        kafkaProps.put("bootstrap.servers", BROKERS);
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer producer = new KafkaProducer<String, String>(kafkaProps);

        for (int i = 1; i <= 100; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>("quickstart-events",
                    "Web order WEB" + i * 234 + " sent to kafka broker " + "for processing at " + dtf.format(LocalTime.now()));

            producer.send(record);

            Thread.sleep(5_000);
        }

        System.out.println("Messages sent successfully");
        producer.close();
    }
}
