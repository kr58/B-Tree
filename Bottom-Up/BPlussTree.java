package BottomUp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Pawan on 06-04-2017.
 */
public class BPlussTree {

    private int n;
    private Node tree;

    BPlussTree(int n)
    {
        this.n=n;
        tree = new LeafNode(n);
    }

    public void insert(HashMap<Integer,Object> hash)
    {
        Node tempTree=tree;
        ArrayList<Integer> keys =new ArrayList<>(hash.keySet());
        Collections.sort(keys);

        int i=0;
        while(i<keys.size())
        {
            int j=0;
            while(j<n && (i+j)<keys.size())
            {
                if(tempTree.insert(keys.get(i+j),hash.get(keys.get(i+j)),false)==0)
                {
                    Node Sibling = new LeafNode(n);
                    Sibling.insert(keys.get(i+j),hash.get(keys.get(i+j)),false);
                    tempTree.setSibling(Sibling);
                    tempTree=tempTree.getSibling();
                }
                ++j;
            }
            i+=j;
        }
        correct(tree,false,false);

        createTree(tree,false);
    }

    private void correct(Node inner,boolean flag,boolean first)
    {
        Node tempInner = inner;
        Object tempValue=null;
        while(tempInner!=null)
        {
            if(tempInner.getSibling()!=null && tempInner.getSibling().getMinKey() > tempInner.getSibling().getCurrentKeys())
            {
                int currentKey=tempInner.getSibling().getCurrentKeys();
                int minKey=tempInner.getSibling().getMinKey();
                int required=minKey-currentKey;
                int index=n-required-1;
                for(int i=0;i<required;i++)
                {
                    int tempKey=tempInner.keys.remove(index);
                    tempInner.setCurrentKeys(tempInner.getCurrentKeys()-1);
                    if(tempInner instanceof LeafNode) tempValue=((LeafNode)tempInner).removeValue(index);
                    tempInner.getSibling().insert(tempKey,tempValue,first);
                    if(flag==true)
                    {
                        Node n = ((InnerNode) tempInner).removechild(index + 1);
                        n.setParent(tempInner.getSibling());
                        ((InnerNode) tempInner.getSibling()).insertchild(n);
                    }
                }
                tempInner.getSibling().sortNode();
                if(flag==true)((InnerNode)tempInner.getSibling()).sortChildren();
            }
            tempInner=tempInner.getSibling();
        }
    }

    private void createTree(Node node,boolean flag)
    {
        Node tempTree = node;
        if(tempTree.getSibling()!=null)
        {
            Node tempInner = new InnerNode(n);
            tempTree.setParent(tempInner);
            boolean first=false;
            ((InnerNode)tempInner).insertchild(tempTree);
            tempTree = tempTree.getSibling();
            while (tempTree != null)
            {
                if (tempInner.insert(tempTree.keys.get(0),null,first) == 0)
                {
                    first=true;
                    Node Sibling = new InnerNode(n);
                    Sibling.insert(tempTree.keys.get(0),null,first);
                    if(flag==true) tempTree.keys.remove(0);
                    tempInner.setSibling(Sibling);
                    tempInner = tempInner.getSibling();
                    tempTree.setParent(Sibling);
                    ((InnerNode)tempInner).insertchild(tempTree);
                }
                else
                {
                    tempTree.setParent(tempInner);
                    ((InnerNode) tempInner).insertchild(tempTree);
                    if (flag == true) tempTree.keys.remove(0);
                }
                tempTree = tempTree.getSibling();
            }
            correct(tree.getParent(),true,first);
            createTree(node.getParent(),true);
        }
        else return;
    }

    public void printTree()
    {
        Node root = tree;
        while(root.getParent()!=null)
            root=root.getParent();

        ArrayList<Node> nodeList = new ArrayList<>();
        nodeList.add(root);

        boolean done = false;
        while(! done)
        {
            ArrayList<Node> next = new ArrayList<>();
            String toprint = "";
            for(int i=0; i < nodeList.size(); i++)
            {
                Node node = nodeList.get(i);
                toprint += node.toString() + " ";
                if(node instanceof LeafNode) done = true;
                else for(int j=0; j < node.keys.size()+1 ; j++) next.add(((InnerNode)node).getChild(j));
            }
            System.out.println(toprint);
            nodeList = next;
        }
    }
}