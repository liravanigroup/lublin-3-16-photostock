package pl.com.bottega.photostock.sales.model;

import com.google.common.base.Objects;

import java.util.Date;
import java.util.List;

/**
 * Created by Slawek on 12/03/16.
 */
public class Purchase {

    private Client owner;

    private Date createDate;

    private String number;

    private List<Product> items;

    public Purchase(Client owner, List<Product> items) {
        this.owner = owner;
        this.items = items;
        this.createDate = new Date();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Client getOwner() {
        return owner;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equal(owner, purchase.owner) &&
                Objects.equal(createDate, purchase.createDate) &&
                Objects.equal(number, purchase.number) &&
                Objects.equal(items, purchase.items);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(owner, createDate, number, items);
    }*/

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("owner", owner)
                .add("createDate", createDate)
                .add("number", number)
                .add("items", items)
                .toString();
    }
}
