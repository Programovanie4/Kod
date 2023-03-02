abstract class Expression {
    abstract long value();

    public static void main(String[] args) {
        Expression e1 = new Operator('+',  // 3+4*5
                              new Constant(3),
                              new Operator('*',
                                      new Constant(4),
                                      new Constant(5)));
        System.out.println(e1);
        System.out.println(e1.value());
        Expression e2 = new Operator('*',  // 3*(4+5)
                new Constant(3),
                new Operator('+',
                        new Constant(4),
                        new Constant(5)));
        System.out.println(e2);
        System.out.println(e2.value());
    }
}
