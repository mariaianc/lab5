package DOMAIN;

import java.io.Serializable;

public class Order implements Entity, Serializable {
    private String person_name;
    BirthdayCake cake;
    private String order_date;
    private String finishing_date;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Order(String person_name, BirthdayCake cake, String order_date, String finishing_date, int order_id) {
        this.id=order_id;
        this.person_name = person_name;
        this.cake = cake;
        this.order_date = order_date;
        this.finishing_date = finishing_date;
    }


    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public BirthdayCake getCake() {
        return cake;
    }

    public void setCake(BirthdayCake cake) {
        this.cake = cake;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getFinishing_date() {
        return finishing_date;
    }

    public void setFinishing_date(String finishing_date) {
        this.finishing_date = finishing_date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "person_name='" + person_name + '\'' +
                ", cake=" + cake +
                ", order_date='" + order_date + '\'' +
                ", finishing_date='" + finishing_date + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }


}
