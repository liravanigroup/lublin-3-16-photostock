package pl.com.bottega.photostock.sales.model;

/**
 * Created by Slawek on 12/03/16.
 */
public class Client {

    private String name;
    private String address;
    private boolean isVip;
    private Money debt;
    private Money amount;
    private Money creditLimit;
    private boolean active = true;

    public Client(String name, String address, boolean isVip, Money debt, Money amount, Money creditLimit) {
        this.name = name;
        this.address = address;
        this.isVip = isVip;
        this.debt = debt;
        this.amount = amount;
        this.creditLimit = creditLimit;
    }

    public Client(String name, String address, Money creditLimit) {
        this(name, address, false, new Money(0), new Money(0), creditLimit);
    }

    public boolean canAfford(Money price) {
        //jeżeli jest VIP
            //saldo + limit >= amount
        //nie jest VIP
            //saldo >= amount

        if (isVip){
            Money purchasePotential = this.amount.add(this.creditLimit.substract(this.debt));
            return purchasePotential.ge(price);
        }
        else{
            return this.amount.ge(price);
        }
    }


    public void charge(Money price, String cause) {
        if (canAfford(price)){
            this.amount = this.amount.substract(price);
            if (this.amount.lt(this.amount.getZero())){
                this.debt = this.debt.substract(amount);
                this.amount = this.amount.getZero();
            }
        }
    }

    public void recharge(Money quantity) {
        this.debt = this.debt.substract(quantity);
        if (this.debt.lt(this.debt.getZero())){
            this.amount = this.amount.substract(this.debt);
            this.debt = this.debt.getZero();
        }
    }

    public Money getSaldo() {
        return amount.substract(debt);
    }

    public boolean isActive() {
        return active;
    }

    public String introducte(){
        return name;// + " - " + status.getPolishString();
    }
}