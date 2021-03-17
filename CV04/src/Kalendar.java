import java.util.Calendar;

public class Kalendar {
    final static int Den_1_1_2000 = 6;

    // 11.3. D=11, m=3
    static int denvRoku(int d, int m, int r) {
        final int[] dni    = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int pocet = d-1;
        for(int i = 0; i < m-1; i++) pocet += dni[i];
        for(int i = 2000; i<r; i++) pocet += 365 + ((i%4==0)?1:0);
        return (pocet+Den_1_1_2000)%7;
    }








//    static int denvRoku(int d, int m, int r) {
//        final int[] dni    = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
//        int pocet = d-1;
//        for(int i = 0; i < m-1; i++) pocet += dni[i];
//        for(int i = 2000; i < r; i++) pocet += 365+((i%4==0)?1:0);
//        if (r%4==0 && m>2) pocet++;
//        return (pocet+Den_1_1_2000)%7;
//    }



    static int denvRoku1(int d, int m, int r) {
        //final int[] dni    = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //final int[] dni7   = { 3, 0, 3, 2, 3, 2, 3, 3, 2, 3, 2, 3};
        final int[] sucty7 = { 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5};
        int pocet = d-1;
        //for(int i = 0; i < m-1; i++) pocet += dni[i];
        pocet += sucty7[m-1];
        //for(int i = 2000; i < r; i++) pocet += 1+((i%4==0)?1:0);
        pocet += (r-2000)+(int)Math.ceil((r-2000.0)/4);
        if (r%4==0 && m>2) pocet++;
        return (pocet+Den_1_1_2000)%7;
    }









    static int denvRoku2(int d, int m, int r) {
        final int[] sucty7 = { 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5};
        return ( d-1 + sucty7[m-1] + (r-2000)+(int)Math.ceil((r-2000.0)/4) +
                ((r%4==0 && m>2)?1:0)+Den_1_1_2000)%7;
    }






    static int denvRoku3(int d, int m, int r) {
        Calendar cal = Calendar.getInstance();
        cal.set(r,m-1,d);
        return cal.get(Calendar.DAY_OF_WEEK)-1;
    }








    // https://katzentier.de/_misc/perpetual_calendar.htm
    static int google(int d, int m, int y) {
        m += 10;
        if (m <= 12) y--; else m -= 12;
        return ( d + y + y/4 -y/100 + y/400 + 31*m/12 ) % 7;
    }






    public static void main(String[] args) {
        System.out.println(denvRoku(11,3,2021));
//        System.out.println(denvRoku3(11,3,2021));
//        for (int d=1; d < 29; d++)
//            for (int m=1; m <= 12; m++)
//                for (int r=2000; r < 2100; r++) {
//                    if (denvRoku(d, m, r) != google(d, m, r))
//                        System.out.println(denvRoku(d, m, r) + "!=!" + google(d, m, r));
//                    if (denvRoku1(d, m, r) != google(d, m, r))
//                        System.out.println(denvRoku1(d, m, r) + "!=!" + google(d, m, r));
//                    if (denvRoku2(d, m, r) != google(d, m, r))
//                        System.out.println(denvRoku2(d, m, r) + "!=" + google(d, m, r));
//                    if (denvRoku3(d,m,r) != google(d,m,r))
//                        System.out.println(denvRoku3(d, m, r) + "=!" + google(d, m, r));
//
//                }
//        System.out.println("o.k.");
    }
}
