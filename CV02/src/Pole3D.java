public class Pole3D {
    public static boolean equalsIgnoreCase(String[][][] a, String[][][] b){
        if(a == null && b == null)  // obe su null
            return true;
        if(a == null || b == null)   // prave jedno je null, druhe nie
            return false;
        if(a.length != b.length) // obe su ne-null ale rozne dlhe
            return false;
        for(int i = 0; i < a.length; i++) { // rovnako dlhe
            if(a[i] == null && b[i] == null)  // obe su null
                continue;
            if(a[i] == null || b[i] == null)   // jedno je null
                return false;
            if(a[i].length != b[i].length)  // obe su ne-null ale rozne dlhe
                return false;
            for(int j = 0; j < a[i].length; j++) { // su rovnako dlhe
                if(a[i][j] == null && b[i][j] == null)  // obe su null
                    continue;
                if(a[i][j] == null || b[i][j] == null)  // prave jedno je null
                    return false;
                if(a[i][j].length != b[i][j].length)  // obe su nenull ale rozne dlhe
                    return false;
                for(int k = 0; k < a[i][j].length; k++){ // rovnako dlhe
                    if(a[i][j][k] == null && b[i][j][k] == null)  // ...
                        continue;
                    if(a[i][j][k] == null || b[i][j][k] == null)  // ...
                        return false;
                    if(!a[i][j][k].equalsIgnoreCase(b[i][j][k]))  // konecne porovnavame ignorujuc velke a mele pismenak
                        return false;
                }
            }
        }
        return true;
    }
}