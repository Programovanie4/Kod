public class Magic11 {

    public static int kontrapriklad() {
        int[][] kb = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
        for(int r1 = 0; r1 < 3; r1++) {
            for (int r2 = 0; r2 < 3; r2++) {
                for (int s1 = 0; s1 < 3; s1++) {
                    for (int s2 = 0; s2 < 3; s2++) {
                        int cislo =
                                kb[r1][s1] * 1000 +
                                kb[r1][s2] * 100 +
                                kb[r2][s2] * 10 +
                                kb[r2][s1] * 1;
                        //System.out.print(cislo+", ");
                        if (cislo % 11 > 0)
                            return cislo;
                        cislo = kb[r1][s1] * 1000 +
                                kb[r2][s1] * 100 +
                                kb[r2][s2] * 10 +
                                kb[r1][s2] * 1;
                        //System.out.print(cislo+", ");
                        if (cislo % 11 > 0)
                            return cislo;
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
