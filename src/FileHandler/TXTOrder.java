package FileHandler;

import DOMAIN.Order;
import Repo.CakeShop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TXTOrder {


        public static ArrayList<Order> createOrders(CakeShop cakes, String filename) {
            ArrayList<Order> orders = new ArrayList<>();
            BufferedReader br = null;

            try
            {
                br = new BufferedReader(new FileReader(filename));
                String line = null;
                while ((line = br.readLine()) != null)
                {
                    String[] elems = line.split("[|]");
                    if (elems.length < 4)
                        continue;


                    Order o = new Order(elems[0].strip(),
                            cakes.getCake(Integer.parseInt(elems[1].strip())),
                            elems[2].strip(),
                            elems[3].strip(),
                            Integer.parseInt(elems[4].strip()));
                    orders.add(o);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (br != null)
                    try {
                        br.close();
                    }
                    catch (IOException e)
                    {
                        System.out.println("Error while closing the file " + e);
                    }
            }
            return orders;
        }

    public static void writeorders(List<Order> orders, String filename)
    {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filename));
            for (Order o: orders)
            {
                bw.write(o.getPerson_name() + " | " + o.getCake().getId() + " | " +
                        o.getOrder_date()+ " | "+o.getFinishing_date()+ " | "+o.getId());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try
            {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
