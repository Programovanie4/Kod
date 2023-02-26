public class Operator extends Expression {
    private char op;
    private Expression left, right;

    public Operator(char op, Expression left, Expression right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        String leftString = (left==null)?"":left.toString();
        String rightString = (right==null)?"":right.toString();
        if (op == '*') {
            if (left instanceof Operator && ((Operator) left).op == '+')
                leftString = "(" + leftString + ")";
            if (right instanceof Operator && ((Operator) right).op == '+')
                rightString = "(" + rightString + ")";
        }
        return leftString + op + rightString;
    }

    @Override
    long value() {
        long leftvalue = (left==null)?0:left.value();
        long rightvalue = (right==null)?0:right.value();
        switch (op) {
            case '+' : return leftvalue + rightvalue;
            case '*' : return leftvalue * rightvalue;
            default  : return 0;
        }
    }
}
