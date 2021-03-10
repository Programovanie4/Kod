package CC;

public class Pole3D {
    public static boolean equalsIgnoreCase(String[][][] a, String[][][] b){
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == null && b[i] == null) continue;
            if (a[i] == null || b[i] == null) return false;
            if (a[i].length != b[i].length) return false;
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] == null && b[i][j] == null) continue;
                if (a[i][j] == null || b[i][j] == null) return false;
                if (a[i][j].length != b[i][j].length) return false;
                for (int k = 0; k < a[i][j].length; k++) {
                    if (a[i][j][k] == null && b[i][j][k] == null) continue;
                    if (a[i][j][k] == null || b[i][j][k] == null) return false;
                    if (!check(a[i][j][k], b[i][j][k])) return false;
                }
            }

        }
        return true;
    }

    private static boolean check(String a, String b){
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.length() != b.length()) return false;
        return a.equalsIgnoreCase(b);
    }

    public static void main(String[] args) {

    }
    //-------
    // genericka staticka metoda
    public static <T> boolean obeNull(T[] a, T[] b) {
        return a == null && b == null;
    }
    public static boolean obeNull(String a, String b) {
        return a == null && b == null;
    }

    // genericka staticka metoda
    public static <T> boolean roznePolia(T[] a, T[] b) {
        if (a == null && b == null) return false;
        if (a == null && b != null) return true;
        if (a != null && b == null) return true;
        return a.length != b.length;
    }

    public static boolean equalsIgnoreCaseKrajsie(String[][][] a, String[][][] b){
        if (obeNull(a,b)) return true;
        if (roznePolia(a,b)) return false;
        for (int i = 0; i < a.length; i++) {
            if (obeNull(a[i],b[i])) continue;
            if (roznePolia(a[i],b[i])) return false;
            for (int j = 0; j < a[i].length; j++) {
                if (obeNull(a[i][j],b[i][j])) continue;
                if (roznePolia(a[i][j],b[i][j])) return false;
                for (int k = 0; k < a[i][j].length; k++) {
                    if (obeNull(a[i][j][k],b[i][j][k])) continue;
                    if (a[i][j][k] == null || b[i][j][k] == null) return false;
                    if (!check(a[i][j][k], b[i][j][k])) return false;
                }
            }

        }
        return true;
    }

}
