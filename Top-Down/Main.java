package TopDown;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InterruptedIOException;

/**
 * Created by Pawan on 04-04-2017.
 */
public class Main {

    public static void main(String[] args)
    {
        ExternalSort s=new ExternalSort();

        //int[] a={3,6,23,1,54,6,24,3,65,47,658,7,34,5};
        //s.mergeSort(a,0,a.length-1);
        //for(int i=0;i<a.length;i++) System.out.println(a[i] + " ");
        s.generateInput("input.txt", 10, 10);
        s.sortFile("input.txt","output.txt" , 10, 10);

        BplusTree B = new BplusTree(4);
        try {
            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);
            String t;
            while ((t=br.readLine())!=null)
            {
                B.insert(Integer.parseInt(t),null);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        /*B.insert(1,"LOL");
        B.insert(2,"xyz");
        B.insert(3,"HIGH");
        B.insert(4,"Low");
        B.insert(5,"Great");
        B.insert((6),"BAD");
        B.insert(7,"Style");
        B.insert(8,"Full");
        B.insert(9,"Empty");
        B.insert((10),"ABC");
        B.insert((11),"uvw");
        //B.insert((12),"BOb");
        //B.insert((13),"MARS");*/
        B.printTree();
    }
}
