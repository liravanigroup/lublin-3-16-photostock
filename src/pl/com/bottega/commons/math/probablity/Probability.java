package pl.com.bottega.commons.math.probablity;

import sun.awt.image.ImageRepresentation;

/**
 * Created by Slawek on 16/04/16.
 */
public class Probability {

    public static Probability fromFraction(double value) {
        if (value < 0 || value > 1)
            throw new IllegalArgumentException("value must be <0, 1>");
        return new Probability(value);
    }

    public static Probability fromPercentage(double value) {
        if (value < 0 || value > 100)
            throw new IllegalArgumentException("value must be <0, 100>");
        return new Probability(value / 100);
    }

    /*
    public enum ProbabilityRepresentation{
        PERC, FRAC;
    }*/

    private double value;//wartosc ułamkowa

    private Probability(double value) {
        this.value = value;
    }

    public Probability and(Probability v2){
        return new Probability(value * v2.value);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
