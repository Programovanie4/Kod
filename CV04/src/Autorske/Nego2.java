package Autorske;

public class Nego2 {
    //https://torstencurdt.com/tech/posts/modulo-of-negative-numbers
    //https://stackoverflow.com/questions/11720656/modulo-operation-with-negative-numbers
    //https://en.wikipedia.org/wiki/Modulo_operation
    private static int mymodulo(int a, int b) {
        //return a < 0 ? b-1 - (-a-1) % b : a % b;
        //return  (((a % b) + b) % b);
        int m = a % b;
        if (m < 0) {
            // m += (b < 0) ? -b : b; // avoid this form: it is UB when b == INT_MIN
            m = (b < 0) ? m - b : m + b;
        }
        return m;
    }
    private static int mydiv(int a, int b) {
        return (a-mymodulo(a,b))/b;
    }

    public static String toNego1(int i) {
        final int base = -2;
        String res = "";
        while (i != 0) {
            int m = mymodulo(i, base);
            res = "" + m + res;
            i = (i-m)/base;
        }
        return res;
    }

    public static String toNego(int i) {
        String binary = "";
        while (i != 0) {
            int zv = i%-2;
            i = i/-2;
            if (zv < 0) {
                zv = zv + 2;
                i += 1;
            }
            binary = "" + zv + binary;
        }
//        System.out.println(binary);
        return binary;
    }

    public static int fromNego(String str) {
        int out = 0;
        int rad = 1;
        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(str.length()-i-1);
            out += (c=='1'?1:0)*rad;
            rad *= -2;
        }
        return out;
    }

    public static String sucet(String a, String b) {
        int ai = fromNego(a);
        int bi = fromNego(b);
        return toNego(ai+bi);
    }

    public static String opacne(String a) {
        return toNego(-fromNego(a));
    }

    public static void main(String[] args) {
        System.out.println(mymodulo(-1,-2));
        System.out.println(mydiv(-1,-2));
        System.out.println(Nego2.toNego1(2));


        System.out.println(Nego2.fromNego("110"));
        System.out.println(Nego2.fromNego("11011"));
        System.out.println(Nego2.fromNego("1100"));

        System.out.println(Nego2.toNego(2).equals("110"));
        System.out.println(Nego2.toNego(7).equals("11011"));
        System.out.println(Nego2.toNego(-4).equals("1100"));

        System.out.println(Nego2.sucet("110","11011").equals("11001"));
        System.out.println(Nego2.opacne("1100").equals("100"));
        System.out.println(Nego2.opacne("110011").equals("10001"));
    }

}

