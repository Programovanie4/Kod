import java.io.Serializable;

//
// Decompiled by Procyon v0.5.36
//

public class Node implements Serializable
{
    private static final long serialVersionUID = 918972645L;
    Integer value;
    Node left;
    Node right;

    public Node(final Node left, final Integer value, final Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node(final int n) {
        if (n < 2) {
            this.value = n;
            this.left = null;
            this.right = null;
        }
        else {
            this.left = new Node(n - 2);
            this.right = new Node(n - 1);
            this.value = this.left.value + this.right.value;
        }
    }

    @Override
    public String toString() {
        return
                ((this.left != null) ? this.left.toString() : ".") +
                        ((this.value != null) ? this.value.toString() : "+") +
                        ((this.right != null) ? this.right.toString() : ".");
    }
}