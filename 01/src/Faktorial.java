import java.util.Random;

public class Faktorial {

    public static long faktorial(int n) {
        long res = 1L;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }
    // 20 !
    // maxlong
    // 734943729347293472937429123192310244912834019234092384012480349
    private static long nad1(int n, int k) {
//        return faktorial(n)/(faktorial(k)*faktorial(n-k));
        return sucin(n-k+1, n) / sucin(1,k);
    }
//    public static long nad(int n, int k) {
////        if (k < n/2) return nad1(n,k);
////        else return nad1(n,n-k);
//        return (k < n/2)?nad1(n,k):nad1(n,n-k);
//    }

    public static long nad(int n, int k) {
        if (n == k || k == 0) return 1;
        else return nad(n-1,k-1) + nad(n-1, k);
    }

    public static long sucin(int from, int to) {
        long res = 1L;
        int _from = from;
        for(; _from <= to; _from++) {
            res *= _from;
        }
        return res;
    }

    public static long sucin1(int from, int to) {
        if (from > to) return 1;
        else if (from == to) return from;
        else { // from < to
            int mid = (from + to) / 2;
            return sucin(from, mid) * sucin(mid + 1, to);
        }
    }


    public static int binDlzka(long n) {
        return (n == 0)?1:binDlzka1(n);
    }

    private static int binDlzka1(long n) {
        return (n == 0)?0:1+binDlzka1(n >> 1);
    }

    public static void main(String[] args) {
        System.out.println(sucin1(1,10));
        System.out.println(binDlzka(32));
        System.out.println(binDlzka(10));
        System.out.println(binDlzka(0));
        // [0,0] polomer 5
        int r = 5;
        int count = 0;
        for(int x = -100; x <= 100; x++) {
            for(int y = -100; y <= 100; y++) {
                if (x*x+y*y <= r*r)
                    count++;
            }
        }
        System.out.println(count);

        StringBuffer res = new StringBuffer();
        for (int i = 0; i < 1_000_000; i++) {
            //res += "a";
            res.append("a");
        }
        String str = res.toString();
        //System.out.println(str);
        int len = 0;
        //len = str.length();
        System.out.println(len);
        for(int i = 0; i < str.length(); i++ ) {
//            if (str.charAt(i) == 'b' || str.charAt(i) == 'B')
            if (Character.toUpperCase(str.charAt(i)) == 'B')
                len++;
        }
        System.out.println(len);
        for(char ch : str.toCharArray()) {
            if (Character.toUpperCase(ch) == 'B')
                len++;
        }



//        System.out.println(faktorial(5));
//        System.out.println(faktorial(10));
//        System.out.println(nad(4,2));
//

        final int MAX = 100;
//        finito:
//        for (int n = 0; n < MAX; n++) {
//            for (int k = 0; k <= n; k++) {
//                long res = nad(n,k);
//                System.out.print(res + "(" + k + ")\t");
//                if (res < 0) {
//                    break finito;
//                }
//            }
//            System.out.println();
//        }
//          long[] a = new long[MAX];
//          a[0] = 1;
//          int n = 1;
//          koniec:
//          do {
//            // a = { 1,3,3,1,0,0,0,0,0,0}
//              long[] b = new long[MAX];
//              b[0] = 1;
//              for(int k = 1; k <= n; k++) {
//                  b[k] = a[k-1] + a[k];
//                  System.out.print(b[k] + "(" + k + ")\t");
//                  if (b[k] < 0) {
//                      break koniec;
//                  }
//              }
//              a = b;
////              for(int k = n; k >= 0; k--) {
////                  a[k] = a[k] + ((k==0)?0:a[k-1]);
////                  System.out.print(a[k] + "(" + k + ")\t");
////                  if (a[k] < 0) {
////                      break koniec;
////                  }
////              }
//              System.out.println();
//            // a = { 1,4,6,4,1,0,0,0,0}
//          } while (++n < MAX);
//

    }
}
