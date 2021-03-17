package tester;
public class Nego2 {

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

