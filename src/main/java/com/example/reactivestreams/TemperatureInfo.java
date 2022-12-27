package com.example.reactivestreams;

import java.util.Random;
import java.util.StringJoiner;

/**
 * Mimic a remote thermometer between 0 and 99 F.
 */
public class TemperatureInfo {
    public static final Random random = new Random();

    private final String town;
    private final int temp;

    public TemperatureInfo(String town, int temp) {
        this.town = town;
        this.temp = temp;
    }

    public static TemperatureInfo fetch(final String town) {
        if(random.nextInt(100) == 0) {
            throw new RuntimeException("Error!");
        }
        return new TemperatureInfo(town, random.nextInt(100));
    }

    public String getTown() {
        return town;
    }

    public int getTemp() {
        return temp;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TemperatureInfo.class.getSimpleName() + "[", "]")
                .add("town='" + town + "'")
                .add("temp=" + temp)
                .toString();
    }
}
