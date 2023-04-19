import java.math.BigInteger;

public class Rekurzia {

    public static int foo(int a, int b, int p) {
        if (a == 0 || b == 0) return 1;
        else {
            return (foo(a - 1, b, p) + foo(a, b - 1, p)) % p;
        }
    }
    public static int foo1(int a, int b, int p) {
        return foo1(a, b, new Integer[a + 1][b + 1], p);
    }
    public static int foo1(int a, int b, Integer[][] res, int p) {
        if (res[a][b] == null)
            res[a][b] = (a == 0 || b == 0)?1:(foo1(a - 1, b, res, p) + foo1(a, b - 1, res, p)) % p;
        return res[a][b];
    }
    public static int foo2(int a, int b, int p) {
        Integer[][] tab = new Integer[a + 1][b + 1];
        for(int i = 0; i <= a; i++)
            for(int j = 0; j <= b; j++)
                tab[i][j] =  (i == 0 || j == 0)?1:(tab[i - 1][j] + tab[i][j - 1]) % p;
        return tab[a][b];
    }

//    public static int komb(int n, int r) {
//        if (r > n) return 0;
//        if (n == r || r == 0) return 1;
//        else return komb(n-1, r-1) + komb(n-1, r);
//    }
    public static int komb(int n, int k, int p) {
        BigInteger citatel = BigInteger.ONE;
        for (int i = n - k + 1; i < n + 1; i++) citatel = citatel.multiply(BigInteger.valueOf(i));
        BigInteger menovatel = BigInteger.ONE;
        for (int i = 1; i < k + 1; i++) menovatel = menovatel.multiply(BigInteger.valueOf(i));
        return citatel.divide(menovatel).mod(BigInteger.valueOf(p)).intValue();
    }

    public static int foo3(int a, int b, int p) {
        return komb(a+b, a, p);
    }
    public static int lucas(int n, int r, int p) {
        int res = 1;
        for(; n > 0 && r > 0; n /= p, r /= p)
            res = (res * komb(n%p, r%p, p)) % p;
        return res;
    }

    public static int lucasR(int n, int r, int p) {
        return (n > 0 && r > 0)?(lucasR(n/p, r/p, p) * komb(n % p, r % p, p)) % p:1;
    }
    public static int foo4(int a, int b, int p) {
        return lucasR(a+b, a, p);
    }
}
