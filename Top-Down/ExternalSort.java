package TopDown;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Pawan on 06-04-2017.
 */
public class ExternalSort {

    int[] Buffer;

    public void merge(int[] arr, int l, int m, int r)
    {
        int i,j,k;
        int n1 = m-l+1;
        int n2 = r-m;

        int[] a=new int[n1];
        int[] b=new int[n2];

        for(i=0;i<n1;i++) a[i]=arr[l+i];
        for(i=0;i<n2;i++) b[i]=arr[m+1+i];

        i=0;j=0;k=l;
        while (i<n1 && j<n2)
        {
            if(a[i] <= b[j]) arr[k++]=a[i++];
            else arr[k++]=b[j++];
        }

        while (i<n1) arr[k++]=a[i++];
        while (j<n2) arr[k++]=b[j++];
    }

    public void mergeSort(int[] arr, int l, int r)
    {
        if(l<r)
        {
            int m= l + (r-l)/2;
            mergeSort(arr,l,m);
            mergeSort(arr,m+1,r);
            merge(arr,l,m,r);
        }
    }

    public void sortFile(String input, String Output, int bloackSize, int noofBlock)
    {
        BufferedReader br = null;
        FileReader fr = null;
        int i=0;
        try {
            fr = new FileReader(input);
            br = new BufferedReader(fr);
            String sCurrentLine;
            for(int j=0;j<noofBlock;j++)
            {
                i=0;
                Buffer=new int[bloackSize];
                while ((sCurrentLine = br.readLine()) != null && i<bloackSize) {
                    Buffer[i]=(Integer.parseInt(sCurrentLine));
                    ++i;
                }
                mergeSort(Buffer,0,bloackSize-1);
                FileWriter fw = new FileWriter(Output + Integer.toString(j) + ".txt");
                PrintWriter pw= new PrintWriter(fw);

                for (int k = 0; k < bloackSize; k++) pw.println(Buffer[k]);

                pw.close();
                fw.close();
            }
            br.close();
            fr.close();

            int[] num = new int[noofBlock];
            BufferedReader[] brw= new BufferedReader[noofBlock];
            for(i=0;i<noofBlock;i++)
            {
                brw[i] = new BufferedReader(new FileReader(Output + Integer.toString(i) + ".txt"));
                String currentData = brw[i].readLine();
                if (currentData == null) num[i]=(Integer.MAX_VALUE);
                else num[i]=(Integer.parseInt(currentData));
            }
            FileWriter fw = new FileWriter(input);
            PrintWriter pw = new PrintWriter(fw);

            for(i=0;i<noofBlock*bloackSize;i++)
            {
                int min=num[0];
                int minFile=0;

                for(int j=0;j<noofBlock;j++)
                {
                    if(min> num[j])
                    {
                        min=num[j];
                        minFile=j;
                    }
                }
                pw.println(min);
                String t = brw[minFile].readLine();
                if (t != null)
                    num[minFile] = Integer.parseInt(t);
                else
                    num[minFile] = Integer.MAX_VALUE;
            }
            for (i = 0; i < noofBlock; i++)
                brw[i].close();

            pw.close();
            fw.close();
        }
        catch (Exception e){e.printStackTrace();}
    }

    public String generateInput(String fileName, int bloackSize, int noofBlock)
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
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return fileName;
    }
}
