package FileHandler;


import DOMAIN.Order;

import java.io.*;
import java.util.ArrayList;

public class BINARYorder {
    public static void serializeOrders(ArrayList<Order> orders, String filename) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(orders);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static ArrayList<Order> deserializeOrders(String filename) {
        ObjectInputStream in = null;
        ArrayList<Order> list = null;
        try
        {
            in = new ObjectInputStream(new FileInputStream(filename));
            list = (ArrayList<Order>) in.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
