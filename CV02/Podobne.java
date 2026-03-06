public class Podobne {
    /**
     * vrati true ak pola na rovnakych miestach maju podobne hodnoty
     * retazec "aaa" je podobny cislu 3
     * retazec dlzky n samych a-cok je podobny cislu n
     * obsah poli moze byt lubovolny
     */
//    public static boolean podobne(String[] a, Integer[] b) {
//        if (a == null && b == null) return true;
//        if (a == null || b == null) return false;
//        if (a.length != b.length) return false;
//
//        for (int i = 0; i < a.length; i++)
//            if (!compare(a[i], b[i]))
//                return false;
//        return true;
//    }
    public static boolean podobne(String[] a, Integer[] b) {
        if (obeNull(a,b)) return true;
        if (roznePolia(a,b)) return false;

        for (int i = 0; i < a.length; i++)
            if (!compare(a[i], b[i]))
                return false;
        return true;
    }


//    public static boolean podobne2(String[][] a, Integer[][] b) {
//        if (a == null && b == null) return true;
//        if (a == null || b == null) return false;
//        if (a.length != b.length) return false;
//
//        for (int i = 0; i < a.length; i++)
//            if (!podobne(a[i], b[i]))
//                return false;
//        return true;
//    }
//    public static boolean podobne2(String[][] a, Integer[][] b) {
//        if (obeNull(a,b)) return true;
//        if (roznePolia(a,b)) return false;
//
//        for (int i = 0; i < a.length; i++)
//            if (!podobne(a[i], b[i]))
//                return false;
//        return true;
//    }

    public static boolean podobne2(String[][] a, Integer[][] b) {
        if (obeNull(a,b)) return true;
        if (roznePolia(a,b)) return false;
        for (int i = 0; i < a.length; i++) {
            if (obeNull(a[i],b[i])) return true;
            if (roznePolia(a[i],b[i])) return false;
            for (int j = 0; j < a[j].length; j++)
                if (!compare(a[i][j], b[i][j]))
                    return false;
        }
        return true;
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
    public static boolean compare(String a, Integer b){
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;

        Integer count = 0;
        for (int i = 0; i < a.length(); i++){
            if (a.charAt(i) == 'a'){
                count++;
            }
        }
        return count.equals(b);
    }

    public static void main(String[] args) {
        System.out.println(podobne(null, null));
        System.out.println(podobne(new String[]{"aa", null, ""}, new Integer[]{2, null, 0}));
    }

}