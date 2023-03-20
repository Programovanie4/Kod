sealed interface Tree1 permits Node, Leaf {
    int size();
    int root();
    boolean isBST();
}
record Node(Tree1 left, int value, Tree1 right) implements Tree1 {
    @Override
    public int size() {
            return 1 + ((left == null)?0:left.size()) + ((right == null)?0:right.size());
    }

    @Override
    public int root() {
            return value;
            }

    @Override
    public boolean isBST() {  // bez zmeny
        return (left == null || left.root() < value && left.isBST())
        &&
        (right == null || right.root() > value && right.isBST());
        }
}

record Leaf(int value) implements Tree1 {
    @Override
    public int size() {
            return 1;
            }
    @Override
    public int root() {
            return value;
            }
    @Override
    public boolean isBST() {
            return true;
            }
}

public class BST1 {

    static int velkost1(Tree1 t) {
        return
                switch (t) {
                    case Node n -> 1 + ((n.left() == null) ? 0 : velkost1(n.left())) +
                            ((n.right() == null) ? 0 : velkost1(n.right()));
                    case Leaf l -> 1;
                };
    }

    static int velkost2(Tree1 t) {
        if (t instanceof Node) {
            Node n = (Node) t;
            return 1 + ((n.left() == null) ? 0 : velkost1(n.left())) +
                    ((n.right() == null) ? 0 : velkost1(n.right()));
        } else if (t instanceof Leaf) {
            Leaf l = (Leaf)t;
            return 1;
        } else {
            return 999;
        }
    }


    public static void main(String[] args) {
        Tree1 c1 = new Leaf(17);
        System.out.println(c1);
        Tree1 c2 = new Node(c1, 4, c1);
        System.out.println(c2);
        Tree1 c3 = new Node(c1, 5,null);
        System.out.println(c3.size());
        Tree1 c = new Node(new Leaf(1), 2, new Leaf(3));
        System.out.println(c.isBST());
        System.out.println(c.size());
        System.out.println(velkost1(c));
    }
}