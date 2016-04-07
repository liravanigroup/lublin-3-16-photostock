package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.Money;

/**
 * Created by Slawek on 07/04/16.
 */
public class MoneyTestConsoleApp {
    public static void main(String[] args) {
        shouldConstruct();

        shouldEqualsIfSameCurrency();
        canNotEqualsIfDifferentCurrency();

        shouldAddHugeNumbers();
        shouldAddSmallNumbers();
        canNotAddIfDifferentCurrency();

        shouldMultipleByTinyRatio();
        shouldMultipleBYLargeRatio();

    }

    private static void shouldConstruct() {
        new Money(10.5, "PLN");
        new Money(10, 50, "PLN");
    }

    private static void shouldEqualsIfSameCurrency() {
        Money m1 = new Money(10.5, "PLN");
        Money m2 = new Money(10, 50, "PLN");

        boolean eq = m1.equals(m2);
        if (!eq)
            System.out.println("coś nie tak z porównywaniem");
    }

    private static void canNotEqualsIfDifferentCurrency() {
        Money m1 = new Money(10.5, "PLN");
        Money m2 = new Money(10, 50, "USD");

        boolean eq = m1.equals(m2);
        if (eq)
            System.out.println("coś nie tak z porównywaniem");
    }

    private static void shouldAddHugeNumbers() {
        Money m1 = new Money(100_000_000d, "PLN");
        Money m2 = new Money(300_000_000d, "PLN");

        Money expectedSum = new Money(400_000_000d, "PLN");

        Money sum = m1.add(m2);

        boolean eq = expectedSum.equals(sum);
        if (!eq)
            System.out.println("Coś nie tak z dodawaniem");
    }

    private static void shouldAddSmallNumbers() {
        //TODO
    }

    private static void canNotAddIfDifferentCurrency() {
        //TODO
    }

    private static void shouldMultipleByTinyRatio() {
        //TODO
    }

    private static void shouldMultipleBYLargeRatio() {
        //TODO
    }
}
