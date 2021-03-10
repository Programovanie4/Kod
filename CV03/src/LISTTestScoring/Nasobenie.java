package LISTTestScoring;

public class Nasobenie {
    public static int foo(int a, int b) {
        int sum = 0;
        while (a > 0) {
            if (a % 2 > 0) sum += b;
            a /= 2; b *= 2;
        }
        return sum;
    }
    public static int goo(int a, int b) {
//        if (a == 0) return 0;
//        return ((a % 2 > 0)?b:0) + goo(a/2, b+b);
        return (a == 0)
                 ?0
                 :((a % 2 > 0)
                        ?b
                        :0) + goo(a/2, b+b);
//        goo 0 b = 0
//        goo a b | a `mod` 2 == 0 = pom
//                | otherwise      = b + pom
//        where pom = goo (a `div` 2) (b+b)
    }

    public static void main(String[] args) {
        for(int a = 0; a < 1000; a++)
            for(int b = 0; b < 1000; b++)
                if (foo(a,b) != a*b)
                    System.out.println(foo(a,b));
                else if (goo(a,b) != a*b)
                    System.out.println(goo(a,b));
    }
}
