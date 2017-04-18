package BottomUp;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Pawan on 06-04-2017.
 */
public class LeafNode extends Node {

    private ArrayList<Object> values;

    LeafNode(int n)
    {
        super(n);
        if(((n-1)/2)%2!=0) setMinKey(((n-1)/2) + 1);
        else setMinKey(((n-1)/2));
        keys=new ArrayList<>();
        values = new ArrayList<>();
    }

    public int insert(int key, Object value, boolean first)
    {
        if(getCurrentKeys() != getOrder()-1)
        {
            keys.add(key);
            values.add(value);
            setCurrentKeys(getCurrentKeys()+1);
            return 1;
        }
        else return 0;
    }

    public void sortNode()
    {
        for (int i=1; i<getCurrentKeys(); i++)
        {
            int key = keys.get(i);
            Object value = values.get(i);
            int j = i-1;
            while (j>=0 && keys.get(j) > key)
            {
                keys.add(j+1,keys.get(j));
                values.add(j+1,values.get(j));
                j = j-1;
            }
            keys.add(j+1,key);
            values.add(j+1,value);
        }
        while(getCurrentKeys()!=keys.size())
        {
            keys.remove(getCurrentKeys());
            values.remove(getCurrentKeys());
        }
    }

    public Object removeValue(int index) { return values.remove(index);}

    public String toString()
    {
        String s = "";
        for(int i=0; i < getCurrentKeys(); i++) s += (keys.get(i)).toString() + " " + values.get(i) + " " ;
        return s + "-";
    }
}