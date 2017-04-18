package BottomUp;

import TopDown.BplusTree;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Pawan on 06-04-2017.
 */
public class Main {

    public static void generateInput(String fileName, int bloackSize, int noofBlock)
    {
        Random rand = new Random();
        try
        {
            FileWriter fw = new FileWriter(fileName);
            PrintWriter pw = new PrintWriter(fw);

            for (int i = 0; i < noofBlock*bloackSize; i++)
                pw.println(rand.nextInt(101));

            pw.close();
        }
        catch (Exception e) {e.printStackTrace();}
    }

    public static void main(String[] args) {

        generateInput("input.txt",10,10);

        HashMap<Integer, Object> a = new HashMap<>();

        BPlussTree B = new BPlussTree(4);

        try {
            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);
            String t;
            while ((t=br.readLine())!=null)
            {
                a.put(Integer.parseInt(t),null);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        /*a.put(3, "HIGH");
        a.put(7, "Great");
        a.put(2, "XYZ");
        a.put(19, "ABC");
        a.put(15, "FuLL");
        a.put(5, "LOW");
        a.put(29, "BOB");
        a.put(31, "MARS");
        a.put(1, "LOL");
        a.put(13, "STyLE");
        a.put(17, "EMpTY");
        a.put(11, "Bad");
        a.put(23, "UVW");*/
        B.insert(a);
        B.printTree();
    }
}