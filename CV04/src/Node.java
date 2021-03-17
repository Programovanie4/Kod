public class Node extends Tree {
    Tree left, right;

    /**
     * konstruktor
     */
    public Node(Tree left, Tree right) {
        this.left = left;
        this.right = right;
    }

    /**
     * hlbka stromu
     */
    @Override
    int depth() {
            return 1+Math.max(  (left==null)?0:left.depth(),
                                (right==null)?0:right.depth());
    }

    /**
     *  je maximálna hodnota stromu, null ak je strom prazdny
     */
    @Override
    Integer max() {
        Integer lmax = (left!=null)?left.max():null;
        Integer rmax = (right!=null)?right.max():null;
        if (lmax == null) return rmax;
        else if (rmax == null) return lmax;
        else return Math.max(lmax, rmax);
    }
    @Override
    public String toString() {
        return "(" + ((left!=null)?left.toString():"null") + "," + ((right!=null)?right.toString():"null") + ")";
    }

    /**
     * platí, ak všetky hodnoty (v listoch stromu) sú rovnaké
     */
    @Override
    boolean isUnique() {
        Integer max = max();    // zoberieme nejaky prvok stromu
        return (max == null || equalsTo(max));  // a tem musi byt rovnaky vsade
    }
    /**
     * prvky v ľavom podstrome sú menšie alebo rovné ako prvky v pravom podstrome, a to pre každý vnútorný vrchol
     */
    @Override
    boolean isOrdered() {
        Integer min = preorder(Integer.MIN_VALUE);
        return min != null;     // ak po prelezeni stromu min != null, tak bol usporiadany, min je najvacsi prvok
    }
    //-------- pomocne
    @Override
    boolean equalsTo(int x) {
        return
            ((left == null || left.equalsTo(x))
            &&
            ((right == null || right.equalsTo(x))));
    }
    /**
     * ak min == null, vrati null
     * inak trochu opatrnejsie right.preorder(left.preorder(min))
     * @return
     */
    @Override
    Integer preorder(Integer min) {
        Integer min1 = (min != null)?((left != null)?left.preorder(min):min):null;
                    // min1 je null, alebo najvacsi v lavom podstrome
        Integer min2 = (min1 != null)?((right != null)?right.preorder(min1):min1):null;
                    // min2 je null, alebo najvacsi v pravom a lavom podstrome
        return min2;
    }
}
