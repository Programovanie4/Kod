public class Podobne {
    /**
     * vrati true ak pola na rovnakych miestach maju podobne hodnoty
     * retazec "aaa" je podobny cislu 3
     * retazec dlzky n samych a-cok je podobny cislu n
     * obsah poli moze byt lubovolny
     */
    public static boolean podobne(String[] a, Integer[] b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == null && b[i] == null) continue;
            if (a[i] == null || b[i] == null) return false;
            if (countA(a[i]) != b[i]) {return false; }
        }

        return true; // toto asi nebude cele riesenie ....
    }

    public static int countA(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a') {count++;}
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(podobne(null, null));
        System.out.println(podobne(new String[]{"aa", null, ""}, new Integer[]{2, null, 0}));
    }

    public static boolean podobne2(String[][] a, Integer[][] b) {
        return false; // ostra hra
    }
}