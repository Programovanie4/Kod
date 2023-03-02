public class Constant extends Expression {
    long constant;

    public Constant(long constant) {
        this.constant = constant;
    }

    @Override
    long value() {
        return constant;
    }

    @Override
    public String toString() {
        return ""+constant;
    }
}
