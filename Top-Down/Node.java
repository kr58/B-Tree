package TopDown;

import java.util.ArrayList;

/**
 * Created by Pawan on 04-04-2017.
 */
abstract public class Node {
    
        protected ArrayList<Integer> keys;          //Keys
        protected Node parent;                      //Parent Node
        private int n;                            // ORDER

        Node(int n)
        {
            parent = null;
            keys = new ArrayList<>();
            this.n = n;
        }

        public int getOrder() {return this.n;}

        abstract Node insert(int key,Object value);
        abstract boolean search(int key);

        protected void traverse(int key, Node right)
        {
            if(parent == null)
            {
                InnerNode newparent = new InnerNode(n);
                newparent.keys.add(key);
                newparent.children.add(this);
                newparent.children.add(right);
                this.setParent(newparent);
                right.setParent(newparent);
            }
            else
            {
                if( parent.keys.size()!=n-1)
                {
                    parent.keys.add(key);
                    ((InnerNode)parent).children.add(right);
                    right.setParent(this.parent);
                }
                else ((InnerNode)parent).split(key, this, right);
            }
        }

        abstract public String toString();

        protected Node findRoot()
        {
            Node node = this;
            while(node.parent != null) node = node.parent;
            return node;
        }

        protected void setParent(Node newparent) {
            this.parent = newparent;
        }
}