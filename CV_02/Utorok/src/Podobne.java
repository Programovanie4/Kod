public class Podobne {
    /**
     * vrati true ak pola na rovnakych miestach maju podobne hodnoty
     * retazec "aaa" je podobny cislu 3
     * retazec dlzky n samych a-cok je podobny cislu n
     * obsah poli moze byt lubovolny
     */
    private static int numberOfA(String s){
        int numberOfA = 0;
        for(char c:s.toCharArray()){
            if(c== 'a'){
                numberOfA++;
            }
        }
        return numberOfA;
    }
    public static boolean podobne(String[] a, Integer[] b) {
        if (a == null && b == null) return true;
        if(a == null || b == null) return false;
        if( a.length != b.length) return false;
        for(int i=0;i<a.length;i++){
            String str = a[i];
            Integer integer = b[i];

            if(str == null && integer == null) continue;
            if(str == null || integer == null) return false;

            int numberOfA = numberOfA(str);

            if(numberOfA != integer){
                return false;
            }
        }
        return true; // toto asi nebude cele riesenie ....
    }

    public static void main(String[] args) {
        System.out.println(podobne(null, null));
        System.out.println(podobne(new String[]{"aa", null, ""}, new Integer[]{2, null, 0}));
    }

    public static boolean podobne2(String[][] a, Integer[][] b) {
        return false; // ostra hra
    }
}