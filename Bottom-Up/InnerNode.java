package BottomUp;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Pawan on 06-04-2017.
 */
public class InnerNode extends Node{

    private ArrayList<Node> children;
    private int noofchild;

    InnerNode(int n)
    {
        super(n);
        if((n/2)%2!=0) setMinKey((n/2) + 1);
        else setMinKey(n/2);
        children = new ArrayList<>();
        keys = new ArrayList<>();
        noofchild=0;
    }

    public int insert(int key,Object value,boolean first)
    {
        if(first == true){
            if(getCurrentKeys() != getOrder())
            {
                keys.add(key);
                setCurrentKeys(getCurrentKeys()+1);
                return 1;
            }
            else return 0;
        }
        else{
            if(getCurrentKeys() != getOrder()-1)
            {
                keys.add(key);
                setCurrentKeys(getCurrentKeys()+1);
                return 1;
            }
            else return 0;
        }
    }

    public void sortNode() { Collections.sort(keys);}

    public void insertchild(Node n)
    {
        ++noofchild;
        children.add(n);
    }

    public Node removechild(int index)
    {
        --noofchild;
        return children.remove(index);
    }

    public String toString()
    {
        String s = "";
        for(int i=0; i < keys.size(); i++) s += (keys.get(i)).toString() + " ";
        return s + "-";
    }

    public void sortChildren()
    {
        for (int i=1; i<noofchild; i++)
        {
            Node key = children.get(i);
            int j = i-1;
            while (j>=0 && children.get(j).keys.get(0) > key.keys.get(0))
            {
                children.add(j+1,children.get(j));
                j = j-1;
            }
            children.add(j+1,key);
        }
        while(noofchild!=children.size()) { children.remove(noofchild);}
    }

    public Node getChild(int index) {return children.get(index);}
}