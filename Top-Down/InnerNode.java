package TopDown;

import java.util.ArrayList;

/**
 * Created by Pawan on 04-04-2017.
 */
public class InnerNode extends Node
{
    protected ArrayList<Node> children;

    InnerNode(int x)
    {
        super(x);
        children = new ArrayList<>();
    }

    private Node getchildren(int key)
    {
        int i = 0;
        boolean found = false;
        while(!found && i < keys.size())
        {
            if( keys.get(i) >= key ) found = true;
            else i++;
        }
        return children.get(i);
    }

    protected boolean search(int key)
    {
        Node next = this.getchildren(key);
        return next.search(key);
    }

    protected void split(int key, Node left, Node right)
    {
        int splitpos, insertpos = 0;

        if(getOrder()%2 == 0) splitpos = getOrder()/2;
        else splitpos = (getOrder()+1)/2 -1;

        insertpos = keys.size();
        keys.add(key);
        this.children.remove(((InnerNode)this).children.size()-1);
        this.children.add(left);
        this.children.add(right);

        int mid = keys.remove(splitpos);
        InnerNode newright = new InnerNode(getOrder());

        for(int i=keys.size()-splitpos; i > 0; i--)
        {
            newright.keys.add(this.keys.remove(splitpos));
            newright.children.add(this.children.remove(splitpos+1));
        }
        newright.children.add(this.children.remove(splitpos+1));

        if(insertpos < splitpos)
        {
            left.setParent(this);
            right.setParent(this);
        }
        else if(insertpos == splitpos)
        {
            left.setParent(this);
            right.setParent(newright);
        }
        else
        {
            left.setParent(newright);
            right.setParent(newright);
        }
        this.traverse(mid, newright);
    }

    protected Node insert(int key,Object value)
    {
        Node next = this.getchildren(key);
        return next.insert(key,value);
    }

    public String toString()
    {
        String s = "";
        for(int i=0; i < keys.size(); i++) s += (keys.get(i)).toString() + " ";
        return s + "-";
    }

    public Node getChild(int index) {return children.get(index);}
}