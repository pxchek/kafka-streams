package com.example.reactivestreams;

import java.util.concurrent.Flow;

public class Main {
    public static void main(String[] args) {
        TemperatureSubscriber subscriber = new TemperatureSubscriber();
        getTemperatures("Chicago").subscribe(subscriber);
    }

    private static Flow.Publisher<TemperatureInfo> getTemperatures(String town) {
        return subscriber -> {
            TemperatureProcessor processor = new TemperatureProcessor();
            processor.subscribe(subscriber);
            TemperatureSubscription subscription = new TemperatureSubscription(subscriber, town);
            processor.onSubscribe(subscription);
        };
    }
}
