public class OpravMa3 {
    public static void main(String[] args) {
        int[] a = {4,5,2,12,1,2,3,0,3,4,1,2,3,6,4,5,5,2,12,1,2,3,55,2,2,4,8,9,3,54,2,3};
        for (int j = 0; j < a.length ; j++) {	// cyklus for-to-do
            for (int i = a.length-1; i>j ; i--) {	// cyklus for-downto-do
                if (a[i-1] > a[i]) {
                    int temp = a[i];
                    a[i] = a[i-1];
                    a[i-1] = temp;
                } // if
            } // for
        } // for
        for (int elem:a) 			// cyklus for-each-element
            System.out.println(elem);
    }

}
