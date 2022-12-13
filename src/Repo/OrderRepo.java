package Repo;

import DOMAIN.Entity;
import DOMAIN.Order;
import FileHandler.BINARYorder;
import FileHandler.TXTOrder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

public class OrderRepo implements InterfaceCakes{
    ArrayList<Order> repo;

    @Override
    public String toString() {
        return "OrderRepo{" + +'\n'+
                 repo +'\n'+
                '}'+'\n';
    }

    public OrderRepo()
    {
        this.repo = new ArrayList<Order>();
    }
    public OrderRepo(ArrayList<Order> repo) {
        this.repo = repo;
    }

    public ArrayList<Order> getAll(){
        return this.repo;
    }
    @Override
    public void add(Entity e) {
        if(e.getClass()!= Order.class)throw new RuntimeException("it's not an order");//vede daca e cake
        Order order=(Order) e;//converteste e in cake
        if(repo.contains(order)) throw new RuntimeException("order already exists");
        repo.add(order);
    }

    @Override
    public void remove(int id) {
        for (Order o : repo) {
            if (o.getId() == id) {
                repo.remove(o);
                return;
            }
        }
    }

    @Override
    public void modify(Entity o, Entity n) {
        if(o.getClass()!=Order.class && n.getClass()!=Order.class)throw new RuntimeException("it's not an order");
        Order order1=(Order) o;//converteste e in cake
        Order order2=(Order) n;
        if(order1.getId()!= order2.getId())throw new RuntimeException("order's id doesn't match");//sa nu existe duplicate
        if(!repo.contains(order1)) throw new RuntimeException("order you want to modify doesn't exists");
        repo.set(repo.indexOf(order1), order2);//shop[id1] = cake2
    }

    public ArrayList<Order> getOrders() {
        return repo;
    }
    @Override
    public Entity get(int id) {
        Iterator<Order> it = repo.iterator();
        while(it.hasNext())
        {
            Order c = it.next();
            if(c.getId() == id)
                return c;
        }
        return null;
    }

    public void read_orders_txt(CakeShop cakes, String filename) {
        if(cakes == null || cakes.getAll().isEmpty() ) {
            System.out.println("Cakes repo is null");
            return;
        }
        if(filename == null || filename.isBlank() || filename.isEmpty()) {
            throw new IllegalArgumentException("Invalid data");
        }
        repo = TXTOrder.createOrders( cakes, filename);
    }

    public void write_orders_txt(String filename) {
        if(filename == null ) {
            throw new IllegalArgumentException("Invalid data");
        }
        TXTOrder.writeorders(repo, filename);
    }

    public void write_orders_binary(String filename) {
        if(filename == null || filename.isBlank() || filename.isEmpty()) {
            throw new IllegalArgumentException("Invalid data");
        }
        BINARYorder.serializeOrders(repo, filename);
    }

    public void read_orders_binary(String filename) {
        if(filename == null ) {
            throw new IllegalArgumentException("Invalid data");
        }
        repo = BINARYorder.deserializeOrders(filename);
    }

    public void saveToDB(){
        try {
            InputStream input = new FileInputStream("config.properties");
            Properties prop = new Properties();
            prop.load(input);
            String DBusername = prop.getProperty("DBusername");
            String DBpassword = prop.getProperty("DBpassword");
            String DBname = prop.getProperty("DBname");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DBname, DBusername, DBpassword);
            String stmt = "INSERT INTO orders( person_name, cake_id, order_date, finishing_date, order_id) VALUES (?, ?, ?, ?, ?)" +
                    "ON DUPLICATE KEY UPDATE person_name = ?, cake_id = ?, order_date = ? , finishing_date=?";
            PreparedStatement ps = con.prepareStatement(stmt);
            for( Order o : repo){
                ps.setString(1, o.getPerson_name());
                ps.setInt(2, o.getCake().getId());
                ps.setString(3, o.getOrder_date());
                ps.setString(4, o.getFinishing_date());
                ps.setInt(5, o.getId());
                ps.setString(6, o.getPerson_name());
                ps.setInt(7, o.getCake().getId());
                ps.setString(8, o.getOrder_date());
                ps.setString(9, o.getFinishing_date());
                ps.executeUpdate();
            }
            con.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFromDB(CakeShop cakes_repo){
        try {
            InputStream input = new FileInputStream("config.properties");
            Properties prop = new Properties();
            prop.load(input);
            String DBusername = prop.getProperty("DBusername");
            String DBpassword = prop.getProperty("DBpassword");
            String DBname = prop.getProperty("DBname");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DBname, DBusername, DBpassword);
            String stmt = "SELECT * FROM orders";
            PreparedStatement ps = con.prepareStatement(stmt);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Order o = new Order(rs.getString("persone_name"),
                        cakes_repo.getCake(rs.getInt("cake_id")),
                        rs.getString("order_date"), rs.getString("finishing_date"), rs.getInt("order_id"));
                add(o);
            }
            con.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
