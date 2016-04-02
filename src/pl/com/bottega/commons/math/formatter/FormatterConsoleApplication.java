package pl.com.bottega.commons.math.formatter;

import java.util.Arrays;

public class FormatterConsoleApplication {
    public static void main(String[] args) {
        souldCreateForLargeNumber(123124);
        souldCreateForLargeNumber(12352432334576l);//L na końcu to typ long
        souldCreateForLargeNumber(2154234634573457l);

        souldCreateForLargeString("123124");
        souldCreateForLargeString("12352432334576");
        souldCreateForLargeString("2154234634573457");

        shouldFormatDigits();
/*
        shouldGetFirstNumber();
        shouldGetLastNumber();
        shouldFormatNumbers();*/

    }

    private static void souldCreateForLargeNumber(long number) {
        Formatter formatter = new Formatter(number);
    }

    private static void souldCreateForLargeString(String number) {
        Formatter formatter = new Formatter(number);
    }

    private static void shouldFormatDigits(){
        Formatter formatter = new Formatter(123456789123456789l);
        String[] digits = formatter.formatDigits("pl");
        System.out.println(Arrays.toString(digits));
    }

    private static void shouldFormatNumbers(){
        Formatter formatter = new Formatter("123456789123456789");
        String numbers = formatter.formatNumbers("pl");
    }


    private static void shouldGetLastNumber() {
        Formatter formatter = new Formatter(123456789123456789l);
        String digit = formatter.getLastDigit("pl");
    }

    private static void shouldGetFirstNumber() {
        Formatter formatter = new Formatter(123456789123456789l);
        String digit = formatter.getDigit(1, "pl");
        
    }



}
