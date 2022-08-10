package com.example.productreviews;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class KafkaJsonReviewDataProducerWorker implements Callback {
    private static final String BROKERS = "10.0.0.213:9092, 10.0.0.213:9093, 10.0.0.213:9094";

    public static void main(String[] args) throws InterruptedException {
        Properties kafkaProps = new Properties();

        kafkaProps.put("bootstrap.servers", BROKERS);
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "com.example.productreviews.KafkaJsonReviewDataSerializer");

        KafkaProducer producer = new KafkaProducer<String, ReviewData>(kafkaProps);

        ReviewData reviewData = new ReviewData();
        reviewData.setReviewerID("530-4953590-345");
        reviewData.setAsin("676967676");
        reviewData.setReviewerName("Kimi Smith");
        reviewData.setHelpful(List.of(2, 3));
        reviewData.setReviewText("Review Text");
        reviewData.setOverall(5.0);
        reviewData.setUnixReviewTime(1252800000);
        reviewData.setReviewTime("09 13, 2021");

        for(int i = 0; i <= 1; i++) {
            ProducerRecord<String, ReviewData> record = new ProducerRecord<>("quickstart-events", reviewData);
            producer.send(record);

            Thread.sleep(5_000);
        }

        System.out.println("Message sent successfully");
        producer.close();
    }

    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        if (Objects.isNull(exception))
            exception.printStackTrace();
    }
}
