class Y { int d; }

public class OpravMa1 {
    record X(int c) {}

    static int a = 19;
    static Integer b = 84;
    private static void valueVsReference(int a, Integer b,  X x, Y y) {
        a++;
        b++;
        //x.c++;
        x = new X(x.c+1);
        y.d++;
    }

    public static void main(String[] args) {
        X x = new X(1984);
        Y y = new Y(); y.d = 2023;
        valueVsReference(a, b, x, y);
        System.out.println(a + "," + b + "," + x + "," + y.d);  // 19,84,X[c=1984],2024
    }
}
