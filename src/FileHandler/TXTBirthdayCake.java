package FileHandler;

import DOMAIN.BirthdayCake;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TXTBirthdayCake {
    public static ArrayList<BirthdayCake> createCake(String filename) {
        ArrayList<BirthdayCake> cakes = new ArrayList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filename));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] elems = line.split("[|]");
                if (elems.length < 4)
                    continue;
                BirthdayCake c = new BirthdayCake(elems[0].strip(), Double.parseDouble(elems[1].strip())
                        , elems[2].strip(), Integer.parseInt(elems[3].strip()));
                cakes.add(c);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Error while closing the file " + e);
                }
        }
        return cakes;
    }

    public static void writecakes(List<BirthdayCake> cakes, String filename)
    {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filename));
            for (BirthdayCake c: cakes)
            {
                bw.write(c.getName() + " | " + c.getPrice() + " | " + c.getExpiration()+ " | "+c.getId());
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
