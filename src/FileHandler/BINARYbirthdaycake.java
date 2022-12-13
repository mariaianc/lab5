package FileHandler;

import DOMAIN.BirthdayCake;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BINARYbirthdaycake {
    public static void serializeCakes(List<BirthdayCake> cakes, String filename)
    {
        ObjectOutputStream out = null;
        try
        {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(cakes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<BirthdayCake> deserializeCakes(String filename)
    {
        ObjectInputStream in = null;
        ArrayList<BirthdayCake> list = null;
        try
        {
            in = new ObjectInputStream(new FileInputStream(filename));
            list = (ArrayList<BirthdayCake>) in.readObject();
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
