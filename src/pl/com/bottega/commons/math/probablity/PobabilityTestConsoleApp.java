package pl.com.bottega.commons.math.probablity;

import static pl.com.bottega.commons.math.probablity.Probability.fromFraction;
import static pl.com.bottega.commons.math.probablity.Probability.fromPercentage;

/**
 * Created by Slawek on 16/04/16.
 */
public class PobabilityTestConsoleApp {
    public static void main(String[] args) {
        shouldCreateFractionRepresenation();
        canNotCreateFractionRepresentaitonIfValueGTOne();

        shouldCreatePercentageRepresenation();
        canNotCreatePercentageRepresentaitonIfValueGTHundred();

        shouldCalcuteIfDifferentRepresentation();
        shouldEqualsZeroIfOneIsZero();
    }

    private static void shouldEqualsZeroIfOneIsZero() {

    }

    private static void shouldCalcuteIfDifferentRepresentation() {

        Probability bothEvents = fromFraction(0.5).and(fromPercentage(50)).and(fromFraction(1));

        System.out.println("wynik: " + bothEvents);
    }

    private static void canNotCreatePercentageRepresentaitonIfValueGTHundred() {
        try {
            Probability p = Probability.fromPercentage(190.5);
            throw new RuntimeException("powinien być wyjątek");
        }
        catch(IllegalArgumentException ex){

        }
    }

    private static void shouldCreatePercentageRepresenation() {
        Probability p = Probability.fromPercentage(90.5);

    }

    private static void canNotCreateFractionRepresentaitonIfValueGTOne() {
        try {
            Probability p = Probability.fromFraction(1.5);
            throw new RuntimeException("powinien byc wyjatek");
        }
        catch(IllegalArgumentException ex){

        }
    }

    private static void shouldCreateFractionRepresenation() {
        Probability p = Probability.fromFraction(0.5);
    }
}
