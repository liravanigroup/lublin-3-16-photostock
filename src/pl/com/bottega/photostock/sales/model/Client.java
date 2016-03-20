package pl.com.bottega.photostock.sales.model;

/**
 * Created by Slawek on 12/03/16.
 */
public class Client {

    private String name;
    private String address;
    private boolean isVip;
    private double debt;
    private double amount;
    private double creditLimit;
    private boolean active = true;

    public Client(String name, String address, boolean isVip,double debt, double amount, double creditLimit) {
        this.name = name;
        this.address = address;
        this.isVip = isVip;
        this.debt = debt;
        this.amount = amount;
        this.creditLimit = creditLimit;
    }

    public Client(String name, String address, double creditLimit) {
        this(name, address, false, 0, 0, creditLimit);
    }

    public boolean canAfford(double price) {
        //jeżeli jest VIP
            //saldo + limit >= amount
        //nie jest VIP
            //saldo >= amount

        if (isVip){
            double purchasePotential = this.amount + (this.creditLimit - this.debt);
            return purchasePotential >= price;
        }
        else{
            return this.amount >= price;
        }
    }


    public void charge(double price, String cause) {
        if (canAfford(price)){
            this.amount -= price;
            if (this.amount < 0){
                this.debt -= amount;
                this.amount = 0;
            }
        }
    }

    public void recharge(double quantity) {
        this.debt -= quantity;
        if (this.debt < 0){
            this.amount -= this.debt;
            this.debt = 0;
        }
    }

    public double getSaldo() {
        return amount - debt;
    }

    public boolean isActive() {
        return active;
    }
}