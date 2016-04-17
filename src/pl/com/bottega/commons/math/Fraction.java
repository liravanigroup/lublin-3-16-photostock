package pl.com.bottega.commons.math;

/**
 * Created by Beata Iłowiecka on 19.03.2016.
 */
public class Fraction {

    private final int nominator;
    private final int denominator;

    public static final Fraction ONE = new Fraction(1, 1);
    public static final Fraction ZERO = new Fraction(0, 1);

    /**
     * Klasa modeluje ułamek
     *
     * @param nominator
     * @param denominator
     *
     * @throws  IllegalArgumentException gdy mianownik jest równy 0
     */
    public Fraction(int nominator, int denominator) throws IllegalArgumentException {
        if (denominator == 0)
            throw new IllegalArgumentException("Denominator can not be zero");

        this.nominator = nominator;
        this.denominator = denominator;
    }

    public Fraction(int nominator) {
        this.nominator = nominator;
        this.denominator = 10;
        // nie chcemy walidować sprawdzać poprawnosć liczby 10
        //this(nominator, 10);
    }

    /**
     *
     * @param literal ułamek w reprezentaacji licznik/mianownik, np. 3/4
     */
    public Fraction(String literal) throws IllegalArgumentException {
        String[] words = literal.split("/");

        if (words.length != 2){
            throw new IllegalArgumentException("To nie jest ułamek");
        }

//      this(Integer.parseInt(words[0]), Integer.parseInt(words[1]));

        try {
            nominator = Integer.parseInt(words[0]);
            denominator = Integer.parseInt(words[1]);

            if (denominator == 0)
                throw new IllegalArgumentException("Denominator can not be zero");
        }
        catch (NumberFormatException ex){
            throw new IllegalArgumentException("To nie jest ułamek" + ex);
        }
    }

    public Fraction add(Fraction addend){

        if (denominator == addend.denominator){
            int sumOfNominators = nominator + addend.nominator;
            Fraction sum = new Fraction(sumOfNominators, denominator);
            return sum;
        } else {
            int thisNominator = nominator * addend.denominator;
            int addendNominator = denominator * addend.nominator;
            int commonDenominator = this.denominator * addend.denominator;
            return new Fraction(thisNominator + addendNominator, commonDenominator);
        }
    }

    /*public String toString(){

        return nominator + " / " + denominator;
    }*/


    public String toString(){

        int newNominator = nominator % denominator;
        int absoluteNumber =  nominator / denominator;

        if (newNominator != 0){
            return getFirstLine(newNominator, absoluteNumber) +
                    getSecondLine(newNominator, absoluteNumber) +
                    getThirdLine(absoluteNumber);
        }
        else {
            return getSecondLine(newNominator, absoluteNumber);
        }
    }

    private int addSpaces(int absoluteNumber){

        int spaces = 0;

        if (absoluteNumber != 0){
            spaces += String.valueOf(absoluteNumber).length() + 1;
        }

        return spaces;
    }

    private int addMoreSpaces(int newNominator){

        int spaces = (String.valueOf(denominator).length() / 2)-(String.valueOf(newNominator).length() / 2);

        if (String.valueOf(denominator).length() - String.valueOf(newNominator).length() == 1){
            return 0;
        }
        else if (String.valueOf(newNominator).length() % 2 == 1 && String.valueOf(denominator).length() % 2 == 0){
            return --spaces;
        }
        else {
            return spaces;
        }
    }

    private String getFirstLine(int newNominator, int absoluteNumber) {

        StringBuilder firstLine = new StringBuilder();
        int spaces = addSpaces(absoluteNumber) + addMoreSpaces(newNominator);


        for (int i = 0; i < spaces; i++){
            firstLine.append(" ");
        }

        firstLine.append(newNominator);
        firstLine.append("\n");

        return firstLine.toString();
    }

    private String getSecondLine(int newNominator, int absoluteNumber) {

        StringBuilder secondLine = new StringBuilder();

        if (absoluteNumber != 0) {
            secondLine.append(absoluteNumber);
            secondLine.append(" ");
        }

        if (newNominator != 0){
            for (int i = 0; i < String.valueOf(denominator).length(); i++) {
                secondLine.append("-");
            }
        }
        secondLine.append("\n");

        return secondLine.toString();
    }

    private String getThirdLine(int absoluteNumber) {

        StringBuilder thirdLine = new StringBuilder();

        for (int i = 0; i < addSpaces(absoluteNumber); i++){
            thirdLine.append(" ");
        }
        thirdLine.append(denominator);
        thirdLine.append("\n");

        return thirdLine.toString();
    }

    public Fraction reverseV2()throws IllegalArgumentException {
        if (nominator == 0){
            throw new IllegalStateException("Can not reverse zero");
        }
        return new Fraction(denominator, nominator);
    }


    public Fraction reverse() throws IllegalStateException {
        try {
            return new Fraction(denominator, nominator);
        }
        catch (IllegalArgumentException ex){
            throw new IllegalStateException("Zero can not be reversed", ex);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fraction)) return false;

        Fraction fraction = (Fraction) o;

        if (nominator != fraction.nominator) return false;
        return denominator == fraction.denominator;

    }

    @Override
    public int hashCode() {
        int result = nominator;
        result = 31 * result + denominator;
        return result;
    }
}
