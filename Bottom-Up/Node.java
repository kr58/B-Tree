package BottomUp;

import com.sun.corba.se.impl.naming.cosnaming.NamingUtils;

import java.util.ArrayList;

/**
 * Created by Pawan on 06-04-2017.
 */
abstract public class Node {

    private int n;
    private int minKey;
    private int currentKeys;
    private Node parent;
    private Node sibling;

    ArrayList<Integer> keys;

    Node(int n)
    {
        this.n=n;
        currentKeys=0;
        parent= null;
        sibling=null;
    }

    public  void setParent(Node s) {this.parent=s;}
    public void setSibling(Node s) {this.sibling=s;}
    public void setMinKey(int a) {this.minKey=a;}
    public void setCurrentKeys(int a) {this.currentKeys=a;}
    public int getOrder() {return n;}
    public int getMinKey() {return minKey;}
    public int getCurrentKeys() {return currentKeys;}
    public Node getParent() {return parent;}
    public Node getSibling() {return sibling;}

    abstract public int insert(int key, Object value, boolean first);
    abstract public void sortNode();
    abstract public String toString();
}
