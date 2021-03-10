package H6;

import org.omg.CORBA.BAD_INV_ORDER;

public class Magic11 {

    public static int kontrapriklad() {
        int[][] pole = {{7, 8, 9},
                        {4, 5, 6},
                        {1, 2, 3}};
        int res = 0;
        int res2 = 0;
        for(int r1 = 0; r1 < 3; r1++){
            for(int s1 = 0; s1 < 3; s1++){
                for(int r2 = 0; r2 < 3; r2++){
                    for(int s2 = 0; s2 < 3; s2++){
                        res = 1000*pole[r1][s1] + 100*pole[r1][s2]+ 10*pole[r2][s2] + pole[r2][s1];
                        res2 = 1000*pole[r2][s1] + 100*pole[r1][s1]+ 10*pole[r1][s2] + pole[r2][s2];
                        if(res % 11 != 0){
//                            System.out.println(res);
                            return res;
                        }
                        if(res2 % 11 != 0){
//                            System.out.println(res);
                            return res2;
                        }
//                        if(res2 == 2563){
//                            System.out.println(res2);
//                        }
//                        System.out.println(res);
//                        System.out.println(res2);

                    }
                }
            }
        }
        return 0;

    }

    public static void main(String[] args) {
        System.out.println(kontrapriklad());
    }
}
