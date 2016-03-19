package pl.com.bottega.commons.math;

/**
 * Created by Slawek on 19/03/16.
 */
public class ReverseTestConcoleApp {
    public static void main(String[] args){
        Fraction f = new Fraction(0, 3);

        try {
            Fraction fReversed = f.reverseV2();
        }
        catch(IllegalStateException ex){
            System.out.println("Nie mogę odwrócić bo " + ex.getMessage());
        }
    }
}
