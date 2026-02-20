public class Sedemciferne {

//    public static int najvacsie7() {
//        for (int c1 = 9; c1 >= 1; c1--) {
//            for (int c2 = 9; c2 >= 1; c2--) {
//                if (c2 == c1) continue;
//                for (int c3 = 9; c3 >= 1; c3--) {
//                    if (c3 == c1 || c3 == c2) continue;
//                    for (int c4 = 9; c4 >= 1; c4--) {
//                        if (c4 == c1 || c4 == c2 || c4 == c3) continue;
//                        for (int c5 = 9; c5 >= 1; c5--) {
//                            if (c5 == c1 || c5 == c2 || c5 == c3 || c5 == c4) continue;
//                            for (int c6 = 9; c6 >= 1; c6--) {
//                                if (c6 == c1 || c6 == c2 || c6 == c3 || c6 == c4 || c6 == c5) continue;
//                                for (int c7 = 9; c7 >= 1; c7--) {
//                                    if (c7 == c1 || c7 == c2 || c7 == c3 || c7 == c4 || c7 == c5 || c7 == c6) continue;
//                                    int c = 1000000*c1 + 100000*c2 + 10000*c3 + 1000*c4 + 100*c5 + 10*c6 + c7;
//                                    if (c % c1 == 0 && c % c2 == 0 && c % c3 == 0 && c % c4 == 0 &&
//                                            c % c5 == 0 && c % c6 == 0 && c % c7 == 0) {
//                                        return c;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return -1;
//    }

 // ine riesenie, ak poznas retazce
//    /**
//     *
//     * @param -  postupne vytvarane cislo
//     * @return -1, ak nenajde
//     */
//    private static int rekurzia(int cislo) {
//        if (cislo > 1_000_000) {
//            return splna(cislo) ? cislo : -1;
//        } else {
//            for (int c = 9; c > 0; c--) {
//                if (("" + cislo).contains("" + c)) // String.valueOf(cislo).contains(String.valueOf(c));
//                    continue;
//                int x = rekurzia(cislo * 10 + c);
//                if (x > 0) return x;
//            }
//            return -1;
//        }
//    }
//

    // ine riesenie, ak poznas pole booleanov
    public static int najvacsie7() {
        // start recursion with an array tracking used digits
        boolean[] used = new boolean[10];
        return rekurzia(0, used, 0);
//      return 9867312;
    }

    /**
     *
     * @param cislo -  postupne vytvarane cislo
     * @return -1, ak nenajde
     */
    private static int rekurzia(int cislo, boolean[] used, int digitsCount) {
        if (digitsCount == 7) {
            return splna(cislo) ? cislo : -1;
        } else {
            for (int c = 9; c > 0; c--) {
                if (used[c]) // already used this digit
                    continue;
                used[c] = true;
                int x = rekurzia(cislo * 10 + c, used, digitsCount + 1);
                used[c] = false;
                if (x > 0) return x;
            }
            return -1;
        }
    }

    private static boolean splna(long n) {
        for (char ch : (String.valueOf(n)).toCharArray()) {
            int num = ch - '0'; // ascii
            if (num == 0 || n % num != 0) return false;
        }
        return true;
    }



    public static void main(String[] args) {
        System.out.println(najvacsie7());
    }
}
