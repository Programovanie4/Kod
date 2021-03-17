package CC;

public class CC1 {
//    public class Trpaslici {

        public static long[] makaj(int n){

            long minimum = -1;
            long maximum = -1;
            int pocet = 0;
            for (int i = 1; i <= Math.sqrt(n); i++) {
                if (n%i!=0)
                    continue;
                for (int j = i; j <= n/i; j++) {
                    if (n%(i*j)!=0)
                        continue;
                    for (int k = j; k <= n/(i*j); k++) {
                        if (i*j*k == n){
                            pocet += 1;
                            long plocha = 2*i*j+2*j*k+2*k*i;
                            if (minimum == -1){
                                minimum = plocha;
                                maximum = plocha;
                            }
                            if (plocha < minimum)
                                minimum = plocha;
                            else if (maximum < plocha)
                                maximum = plocha;
                        }
                    }
                }
            }

            return new long[] {pocet, minimum, maximum};

        }

        public static int pocetMoznosti(int n){

            long[] pole = makaj(n);

            return (int) pole[0];
        }

        public static long rozdiel(int n){

            long[] pole = makaj(n);

            return pole[2] - pole[1];
        }


        public static void main(String[] args) {
            System.out.println(pocetMoznosti(4));
            //System.out.println(rozdiel(21474836));
        }

    }

/*
int i = Integer.MAX_VALUE
int j = Integer.MAX_VALUE
long plocha = 2*i*j;
long plocha = 2L*i*j;
 */