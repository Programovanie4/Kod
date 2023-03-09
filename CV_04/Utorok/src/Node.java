import java.util.Objects;

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
     *  je maximálna hodnota stromu, null ak je strom prazdny
     */
    public Integer max(Integer a, Integer b){
        if( a == null){
            return b;
        }
        if(b == null){
            return a;
        }
        return Math.max(a,b);
    }

    public Integer min(Integer a, Integer b){
        if( a == null){
            return b;
        }
        if(b == null){
            return a;
        }
        return Math.min(a,b);
    }
    @Override
    Integer min() {
        if(left == null && right == null){
            return null;
        }
        if(right == null){
            return left.min();
        }
        if(left == null){
            return right.min();
        }
        return min(left.min(), right.min());
    }
    @Override
    Integer max() {

        if(left == null && right == null){
            return null;
        }
        if(right == null){
            return left.max();
        }
        if(left == null){
            return right.max();
        }
        return max(left.max(), right.max());
    }

    /**
     * hlbka stromu
     */
    @Override
    int depth() {
        int leftDepth = (left == null) ? 0 : left.depth();
        int rightDepth = (right == null) ? 0 : right.depth();
        return 1 + Math.max(leftDepth, rightDepth);
    }

    /**
     * platí, ak všetky hodnoty (v listoch stromu) sú rovnaké
     */
    @Override
    boolean isUnique() {
        if (right == null && left == null)
            return true;
        if (right == null)
            return left.isUnique();
        if ( left == null)
            return right.isUnique();

        if (!(left.isUnique() && right.isUnique()))
            return false;
        Integer maxLeft = left.max();
        Integer maxRight = right.max();


        return Objects.equals(maxLeft, maxRight);
    }

    /**
     * prvky v ľavom podstrome sú menšie alebo rovné ako prvky v pravom podstrome, a to pre každý vnútorný vrchol
     */
    boolean isLessOrEqual(Integer a, Integer b) {
        if (a == null || b == null) return true;
        return a <= b;
    }
    @Override
    boolean isOrdered() {
        if (left == null && right == null) return true;
        if (left == null) return right.isOrdered();
        if (right == null) return left.isOrdered();
        return left.isOrdered() && right.isOrdered() && isLessOrEqual(left.max(), right.min());
    }

    @Override
    public String toString() {
        return "(" + left + " " + right + ")";
    }
}
