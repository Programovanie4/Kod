import java.util.*;

public class Pancakes {

    static long reverse(long x, long acc) {
        return (x == 0)?acc:reverse(x/10, 10*acc+(x % 10));
    }
    static long flip_(long x, int k) {
        return (k == 0)?reverse(x,0):10*flip_(x/10, k-1)+(x % 10);
    }
    static long flip(long x, int k) {
        return (k == 1)?x:flip_(x, (int)Math.ceil(Math.log10(x))-k);
    }

    public static void main(String[] args) {
        for (int len = 2; len < 20; len++) {
            Map<Long, Integer> hm = new HashMap<>();
            List<Long> queue = new ArrayList<>();
            long init = 0L;
            for(int i = len; i>=1; i--)
                init = 10*init+i;
            hm.put(init, 0);
            queue.add(init);
            while(!queue.isEmpty()) {
                long fst = queue.get(0);
                int fstSteps = hm.get(fst);
                queue.remove(0);
                for (int k = 1; k <= len; k++) {
                    long xx = flip(fst, k);
                    if (hm.get(xx) == null) {
                        hm.put(xx, fstSteps + 1);
                        queue.add(xx);
                    }
                }
            }
            System.out.println("len " + len + " : " + Collections.max(hm.values()) + ", "+ hm.size());
        }
//        System.out.println(reverse(123, 0));
//        System.out.println(flip(12345, 2));
//        System.out.println(flip(12345, 6));

//        {4321=1, 4132=3, 2341=2, 3142=4, 4231=4, 3241=3, 2314=2, 1324=3, 2413=4, 3214=1, 1423=3, 1234=0, 3124=2, 3412=3, 4213=3, 2134=1, 4312=2, 1432=3, 4123=2, 1243=3, 3421=2, 1342=3, 2143=3, 2431=3}

    }
}
