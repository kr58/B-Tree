package TopDown;

import java.util.ArrayList;

/**
 * Created by Pawan on 04-04-2017.
 */
public class BplusTree {

    private Node tree;
    private int n;

    BplusTree(int n)
    {
        this.n = n;
        tree = new LeafNode(this.n);
    }

    public void insert(int key,Object value)
    {
        tree = tree.insert(key,value);
    }

    public void search(int x)
    {
        if(tree.search(x)) System.out.println("Found !");
        else System.out.println("Not Found !");
    }

    public void printTree()
    {
        ArrayList<Node> nodeList = new ArrayList<>();
        nodeList.add(tree);

        boolean done = false;
        while(! done)
        {
            ArrayList<Node> nextLevelList = new ArrayList<>();
            String toprint = "";
            for(int i=0; i < nodeList.size(); i++)
            {
                Node node = (Node)nodeList.get(i);
                toprint += node.toString() + " ";
                if(node instanceof LeafNode) done = true;
                else for(int j=0; j < node.keys.size()+1 ; j++) nextLevelList.add(((InnerNode)node).getChild(j));
            }
            System.out.println(toprint);
            nodeList = nextLevelList;
        }
    }
}