package DOMAIN;

import java.io.Serializable;

public class BirthdayCake implements Entity, Serializable {

    private String name;
    private double price;
    private String expiration;
    private int id;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public BirthdayCake() {
    }

    public BirthdayCake(String name, double price, String expiration, int id) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.expiration = expiration;

    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BirthdayCake that = (BirthdayCake) o;
        return id == that.id;
    }

    @Override
    public String toString() {
        return "BirthdayCake{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", expiration='" + expiration + '\'' +
                ", id=" + id +
                '}' + '\n';
    }

}
