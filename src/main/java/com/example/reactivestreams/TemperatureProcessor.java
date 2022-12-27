package com.example.reactivestreams;

import java.util.concurrent.Flow;

public class TemperatureProcessor implements Flow.Processor<TemperatureInfo, TemperatureInfo> {
    private Flow.Subscriber<? super TemperatureInfo> subscriber;

    @Override
    public void subscribe(Flow.Subscriber<? super TemperatureInfo> subscriber) {
        this.subscriber = subscriber;

    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(TemperatureInfo item) {
        subscriber.onNext(new TemperatureInfo(item.getTown(), (item.getTemp() - 32) * 5 / 9));
    }

    @Override
    public void onError(Throwable throwable) {
        subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        subscriber.onComplete();
    }
}
