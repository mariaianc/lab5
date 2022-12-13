package Service;

import DOMAIN.BirthdayCake;
import DOMAIN.Order;
import Repo.CakeShop;
import Repo.OrderRepo;

public class Controller {
    CakeShop cakeRepo;
    OrderRepo orderRepo;

    public Controller()
    {
        cakeRepo = new CakeShop();
        orderRepo = new OrderRepo();
    }

    public Controller(CakeShop cakeRepo, OrderRepo orderRepo) {
        this.cakeRepo = cakeRepo;
        this.orderRepo = orderRepo;
    }

    public BirthdayCake get_cake(int id)
    {
        return cakeRepo.getCake(id);
    }


    public void addCake(BirthdayCake cake) {
        cakeRepo.add(cake);
    }
    public void addOrder(Order order) {
        orderRepo.add(order);
    }

    public String getAllCakes(){return cakeRepo.toString();}

    public String getAllOrders(){return orderRepo.toString();}

    public void removeCake(int id)
    {
        this.cakeRepo.remove(id);
    }

    public void removeOrder(int id)
    {
        this.orderRepo.remove(id);
    }

    public void read_cake_txt(String filename) {
        cakeRepo.read_cakes(filename);
    }

    public void write_cake_txt(String filename) {
        cakeRepo.write_cakes(filename);
    }

    public void read_orders_txt(CakeShop cakes, String filename) {
        try {
            orderRepo.read_orders_txt(cakes, filename);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void write_orders_txt(String filename) {
        try {
            orderRepo.write_orders_txt(filename);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void read_cakes_binary(String filename) {
        try {
            cakeRepo.read_cakes_binary(filename);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void write_cakes_binary(String filename) {
        try {
            cakeRepo.write_cakes_binary(filename);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void read_orders_binary(String filename) {
        try {
            orderRepo.read_orders_binary(filename);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void write_orders_binary(String filename) {
        try {
            orderRepo.write_orders_binary(filename);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public CakeShop getCakeRepo() {
        return cakeRepo;
    }

    public OrderRepo getOrderRepo() {
        return orderRepo;
    }

    public void write_cakes_data_base(){
        cakeRepo.saveToDB();
    }

    public void write_orders_data_base(){
        orderRepo.saveToDB();
    }

    public void read_orders_db()
    {
        orderRepo.readFromDB(cakeRepo);
    }

    public void read_cakes_db()
    {
        cakeRepo.readFromDB();
    }

}
