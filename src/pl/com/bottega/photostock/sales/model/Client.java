package pl.com.bottega.photostock.sales.model;

/**
 * Created by Slawek on 12/03/16.
 */
public class Client {

    private String name;
    private String address;
    private boolean isVip;
    private double saldo;
    private double creditLimit;

    public Client(String name, String address, boolean isVip, double saldo, double creditLimit) {
        this.name = name;
        this.address = address;
        this.isVip = isVip;
        this.saldo = saldo;
        this.creditLimit = creditLimit;
    }

    public Client(String name, String address, double creditLimit) {
        this(name, address, false, 0, creditLimit);
    }

    public boolean canAfford(double amount) {
        //jeżeli jest VIP
            //saldo + limit >= amount
        //nie jest VIP
            //saldo >= amount

        if (isVip){
            double purchasePotential = saldo + creditLimit;
            return purchasePotential >= amount;
        }
        else{
            return saldo >= amount;
        }
    }


    public void charge(double amount, String cause) {
        saldo -= amount;
    }

    public void recharge(double amount) {
        saldo += amount;
    }

    public double getSaldo() {
        return saldo;
    }
}