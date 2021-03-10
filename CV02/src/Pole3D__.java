public class Pole3D__ {

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
    //---
    public static boolean obeNull(String a, String b) {
        return a == null && b == null;
    }
    public static boolean rozneRetazce(String a, String b) {
        if (a == null && b == null) return false;
        if (a == null && b != null) return true;
        if (a != null && b == null) return true;
        return !(a.equalsIgnoreCase(b));
    }
    public static boolean equalsIgnoreCase(String[][][] a, String[][][] b){
        if (obeNull(a,b)) return true;
        if (roznePolia(a,b)) return false;
        for(int i = 0; i < a.length; i++) { // rovnako dlhe
            if (obeNull(a[i],b[i])) continue;
            if (roznePolia(a[i],b[i])) return false;
            for(int j = 0; j < a[i].length; j++) { // su rovnako dlhe
                if (obeNull(a[i][j],b[i][j])) continue;
                if (roznePolia(a[i][j],b[i][j])) return false;
                for(int k = 0; k < a[i][j].length; k++){ // rovnako dlhe
                    if (obeNull(a[i][j][k],b[i][j][k])) continue;
                    return rozneRetazce(a[i][j][k],b[i][j][k]);
                }
            }
        }
        return true;
    }
}