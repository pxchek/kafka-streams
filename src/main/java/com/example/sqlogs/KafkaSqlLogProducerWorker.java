package com.example.sqlogs;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class KafkaSqlLogProducerWorker {
    private static final String BROKERS = "localhost:49092, localhost:39092, localhost:29092";

    public static void main(String[] args) throws InterruptedException, IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        Properties kafkaProps = new Properties();

        kafkaProps.put("bootstrap.servers", BROKERS);
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer producer = new KafkaProducer<String, String>(kafkaProps);

        Files
                .readAllLines(Path.of("/Users/<id>/IdeaProjects/kafka-streams/src/main/resources/words.txt"), StandardCharsets.UTF_8)
                .stream()
                .map(line -> new ProducerRecord<>("sql-events", line))
                .forEach(producerRecord -> producer.send(producerRecord));

        System.out.println("Messages sent successfully");
        producer.close();
    }
}