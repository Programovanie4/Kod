import java.util.*;

public class Pancakes1 {
    //http://www.geekviewpoint.com/java/numbers/permutation_index
    static int permutationIndex(String permutation) {
        int index = 0;
        int position = 2;// position 1 is paired with factor 0 and so is skipped
        int factor = 1;
        for (int p = permutation.length() - 2; p >= 0; p--) {
            int successors = 0;
            for (int q = p + 1; q < permutation.length(); q++) {
                if (permutation.charAt(p) > permutation.charAt(q)) {
                    successors++;
                }
            }
            index += (successors * factor);
            factor *= position;
            position++;
        }
        return index;
    }
    static String flip(String x, int k) {
        if (k == 1) return x;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < k; i++) sb.append(x.charAt(k-i-1));
        for (int i = k; i < x.length(); i++) sb.append(x.charAt(i));
        return sb.toString();
    }

    public static void main(String[] args) {
        for (int len = 2; len < 20; len++) {
            //Map<String, Integer> hm = new HashMap<>();
            List<String> queue = new ArrayList<>();
            String init = "";
            long fact = 1;
            for(int i = 0; i<len; i++) {
                init = init + (char) ('A' + i);
                fact *= (i + 1);
            }
            int[] p = new int[(int)fact];
            Arrays.fill(p, -1);
            //System.out.println(permutationIndex(init));
            //hm.put(init, 0);
            p[0] = 0;
            int max = 0;
            queue.add(init);
            int fill = 1;
            while(!queue.isEmpty()) {
                String fst = queue.get(0);
                int fstIndex = permutationIndex(fst);
                //int fstSteps = hm.get(fst);
                int fstSteps = p[fstIndex];
                queue.remove(0);
                for (int k = 1; k <= len; k++) {
                    String xx = flip(fst, k);
                    int xxIndex = permutationIndex(xx);
//                    if (hm.get(xx) == null) {
//                        hm.put(xx, fstSteps + 1);
                      if (p[xxIndex] < 0) {
                        p[xxIndex] = fstSteps+1;
                        fill++;
                        if (fill % 1000000 == 0)
                            System.out.println(fill + " / " + fact );
                        if (fstSteps+1 > max) max = fstSteps+1;
                        queue.add(xx);
                    }
                }
            }
            //System.out.println("len " + len + " : " + Collections.max(hm.values()) + ", "+ hm.size());
            System.out.println("len " + len + " : " + max );
        }
    }
}
