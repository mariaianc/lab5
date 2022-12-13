package Repo;

import DOMAIN.BirthdayCake;
import DOMAIN.Entity;
import FileHandler.BINARYbirthdaycake;
import FileHandler.TXTBirthdayCake;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;


public class CakeShop implements InterfaceCakes{
    private ArrayList<BirthdayCake> shop;

    public CakeShop()
    {
        shop = new ArrayList<BirthdayCake>();
    }

    @Override
    public void add(Entity e) {
        if(e.getClass()!=BirthdayCake.class)throw new RuntimeException("it's not a cake");//vede daca e cake
        BirthdayCake cake=(BirthdayCake) e;//converteste e in cake
        if(shop.contains(cake)) throw new RuntimeException("cake already exists");
        shop.add(cake);
    }

    public ArrayList<BirthdayCake> getAll(){
        return this.shop;
    }

    @Override
    public void remove(int id) {
        for (BirthdayCake cake : shop) {
            if (cake.getId() == id) {
                shop.remove(cake);
                return;
            }
        }
    }



    @Override
    public void modify(Entity o, Entity n) {
        if(o.getClass()!=BirthdayCake.class && n.getClass()!=BirthdayCake.class)throw new RuntimeException("it's not a cake");
        BirthdayCake cake1=(BirthdayCake) o;//converteste e in cake
        BirthdayCake cake2=(BirthdayCake) n;
        if(cake1.getId()!= cake2.getId())throw new RuntimeException("cake's id doesn't match");//sa nu existe duplicate
        if(!shop.contains(cake1)) throw new RuntimeException("cake you want to modify doesn't exists");
        shop.set(shop.indexOf(cake1), cake2);//shop[id1] = cake2
    }

    @Override
    public Entity get(int id) {//return shop[id], prajitura de la indexul dorit
        Iterator<BirthdayCake> it = shop.iterator();
        while(it.hasNext())
        {
            BirthdayCake c = it.next();
            if(c.getId() == id)
                return c;
        }
        return null;
    }
    public int getSize()
    {
        return this.shop.size();
    }



    public void write_cakes(String filename)
    {
      TXTBirthdayCake.writecakes(shop,filename);
    }

    public void read_cakes(String filename){
        shop = TXTBirthdayCake.createCake(filename);
    }

    public BirthdayCake getCake(int id) {
        BirthdayCake result = null;
        for (BirthdayCake cake : shop) {
            if (cake.getId() == id) {
                return cake;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "CakeShop{" + '\n'+
                 shop +'\n'+
                '}'+'\n';
    }

    public void read_cakes_binary(String filename){
        if (filename == null || filename.isBlank() || filename.isEmpty()) {
            throw new IllegalArgumentException("Invalid data");
        }
        shop = BINARYbirthdaycake.deserializeCakes(filename);
    }

    public void write_cakes_binary(String filename){
        if (filename == null ) {
            throw new IllegalArgumentException("Invalid data");
        }
        BINARYbirthdaycake.serializeCakes(shop, filename);
    }

    public void saveToDB(){
        try{
            InputStream input = new FileInputStream("config.properties");
            Properties prop = new Properties();
            prop.load(input);
            String DBusername = prop.getProperty("DBusername");
            String DBpassword = prop.getProperty("DBpassword");
            String DBname = prop.getProperty("DBname");

            //chestie de sql
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DBname, DBusername, DBpassword);
            String stmt = "INSERT INTO cakes( name, price, expiration,id) VALUES (?,?,?,?) " +
                    "ON DUPLICATE KEY UPDATE name = ?, price = ?, expiration = ?";
            PreparedStatement preparedStatement = con.prepareStatement(stmt);
            for(BirthdayCake cake : shop){
                preparedStatement.setString(1,cake.getName());
                preparedStatement.setDouble(2,cake.getPrice());
                preparedStatement.setString(3,cake.getExpiration());
                preparedStatement.setInt(4,cake.getId());
                preparedStatement.setString(5,cake.getName());
                preparedStatement.setDouble(6,cake.getPrice());
                preparedStatement.setString(7,cake.getExpiration());
                preparedStatement.executeUpdate();
            }
            con.close();

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void readFromDB(){
        try{
            InputStream input = new FileInputStream("config.properties");
            Properties prop = new Properties();
            prop.load(input);
            String DBusername = prop.getProperty("DBusername");
            String DBpassword = prop.getProperty("DBpassword");
            String DBname = prop.getProperty("DBname");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DBname, DBusername, DBpassword);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from cakes");
            while(rs.next()){
                BirthdayCake cake = new BirthdayCake(rs.getString(1),rs.getDouble(2),
                        rs.getString(3),rs.getInt(4));
                shop.add(cake);
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }



    }
