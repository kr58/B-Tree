package TopDown;

import java.util.ArrayList;

/**
 * Created by Pawan on 04-04-2017.
 */
public class LeafNode extends Node
{
    protected LeafNode nextNode;
    protected ArrayList<Object> values;

    LeafNode(int degree)
    {
        super(degree);
        nextNode = null;
        values = new ArrayList<>();
    }

    private void setNextNode(LeafNode next) {nextNode = next;}

    protected LeafNode getNextNode() {return nextNode;}

    protected boolean search(int key)
    {
        for(int i=0; i < keys.size(); i++) if(keys.get(i) == key) return true;
        return false;
    }

    protected void split(int key,Object value)
    {
        keys.add(keys.size(), key);
        values.add(values.size(), value);

        int pos;
        if(getOrder()%2 == 0) pos = getOrder()/2;
        else pos = (getOrder()+1)/2;

        LeafNode right = new LeafNode(getOrder());
        for(int i = keys.size()-pos; i > 0; i--)
        {
            right.keys.add(keys.remove(pos));
            right.values.add(values.remove(pos));
        }

        right.setNextNode(this.getNextNode());
        this.setNextNode(right);
        int mid = right.keys.get(0);
        this.traverse(mid, right);
    }

    protected Node insert(int key,Object value)
    {
        if(keys.size() < getOrder()-1)
        {
            keys.add(keys.size(), key);
            values.add(values.size(),value);
        }
        else this.split(key,value);
        return this.findRoot();
    }

    public String toString()
    {
        String s = "";
        for(int i=0; i < keys.size(); i++) s += (keys.get(i)).toString() + " " + values.get(i) + " ";
        return s + "-";
    }
}