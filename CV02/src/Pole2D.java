public class Pole2D {

    private static boolean reversedCase1(String a, String b) {
        if (a.length() != b.length()) return false;
        for (int i = 0; i < a.length(); i++) {
            char cha = a.charAt(i);
            char chb = b.charAt(i);
            if (Character.isLetter(cha) && Character.isLetter(chb)) {
                if (Math.abs(cha-chb) != Math.abs('a'-'A'))  // 32
                    return false;
            } else if (Character.isLetter(cha))
                return false;
            else if (Character.isLetter(chb))
                return false;
            else if (cha != chb)
                return false;
        }
        return true;
    }

    private static boolean reversedCase(String a, String b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.length() != b.length()) return false;
        if (!a.toUpperCase().equals(b.toUpperCase())) return false;
        if (!a.toLowerCase().equals(b.toLowerCase())) return false;
  /**/  for (int i = 0; i < a.length(); i++) {
            char cha = a.charAt(i);
            char chb = b.charAt(i);
            if (Character.isLetter(cha) && Character.isLetter(chb) && cha== chb)
                return false;
        }
        return true;
    }

    /*
     * prve riesenie
     */
    public static boolean equalsReversedCase2(String[][] a, String[][] b) {
        if(a.length != b.length) // obe su ne-null ale rozne dlhe
            return false;
        for(int i = 0; i < a.length; i++) { // rovnako dlhe
            if(a[i].length != b[i].length) // obe su ne-null ale rozne dlhe
                return false;
            for(int j = 0; j < a[i].length; j++) { // rovnako dlhe
                if (!reversedCase(a[i][j], b[i][j])) return false;
            }
        }
        return true;
    }

    public static boolean equalsReversedCase(String[][] a, String[][] b) {
        if(a == null && b == null)  // obe su null
            return true;
        if(a == null || b == null)   // prave jedno je null, druhe nie
            return false;
        if(a.length != b.length) // obe su ne-null ale rozne dlhe
            return false;
        for(int i = 0; i < a.length; i++) { // rovnako dlhe
            if (a[i] == null && b[i] == null) continue;
            if (a[i] == null || b[i] == null) return false;
            if(a[i].length != b[i].length) // obe su ne-null ale rozne dlhe
                return false;
            for(int j = 0; j < a[i].length; j++) { // rovnako dlhe
                if (a[i][j] == null && b[i][j] == null) continue;
                if (a[i][j] == null || b[i][j] == null) return false;
                if (!reversedCase(a[i][j], b[i][j])) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //System.out.println(reversedCase1(null, null));
        System.out.println(reversedCase1("aBbA", "AbBa"));
        System.out.println(reversedCase1("Rok 2021","rOK 2021"));
        System.out.println(reversedCase1("1000!", "1000!"));
        System.out.println(reversedCase1("<Ok>", "<oK>"));
//        System.out.println(equalsReversedCase(
//                new String[][] {{"a"},{"aBbA", "AbBa"}, null, { null}, {},
//                        {"Rok 2021","rOK 2021"},
//                        {"1000!", "1000!"},
//                        {"<Ok>", "<oK>"}
//                },
//                new String[][] {{"A"}, {"AbBa", "aBbA" }, null, {null}, {},
//                        {"rOK 2021", "Rok 2021"},
//                        {"1000!", "1000!"},
//                        { "<oK>", "<Ok>" }
//                }
//        ));
//        System.out.println(equalsReversedCase1(
//                new String[][] {{"a"},{"aBbA", "AbBa"}, null, { null}, {},
//                        {"Rok 2021","rOK 2021"},
//                        {"1000!", "1000!"},
//                        {"<Ok>", "<oK>"}
//                },
//                new String[][] {{"A"}, {"AbBa", "aBbA" }, null, {null}, {},
//                        {"rOK 2021", "Rok 2021"},
//                        {"1000!", "1000!"},
//                        { "<oK>", "<Ok>" }
//                }
//        ));

    }

    // genericka staticka metoda
    public static <T> boolean obeNull(T[] a, T[] b) {
        return a == null && b == null;
    }
    // genericka staticka metoda
    public static <T> boolean roznePolia(T[] a, T[] b) {
        if (a == null && b == null) return false;
        if (a == null && b != null) return true;
        if (a != null && b == null) return true;
        return a.length != b.length;
    }
    public static boolean equalsReversedCase1(String[][] a, String[][] b){
        if (obeNull(a,b)) return true;
        if (roznePolia(a,b)) return false;
        for(int i = 0; i < a.length; i++) { // rovnako dlhe
            if (obeNull(a[i],b[i])) continue;
            if (roznePolia(a[i],b[i])) return false;
            for(int j = 0; j < a[i].length; j++) { // su rovnako dlhe
                if (!reversedCase(a[i][j], b[i][j])) return false;
            }
        }
        return true;
    }
}
