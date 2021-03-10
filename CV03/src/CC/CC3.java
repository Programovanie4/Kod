package CC;

public class CC3 {
    public static boolean euklidovska(String trasa, double tolerancia){
        int[] xy = hodnoty(trasa);
        int x = xy[0];
        int y = xy[1];

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) <= tolerancia;
    }

    public static boolean manhatanska(String trasa, int tolerancia){
        int[] xy = hodnoty(trasa);
        int x = xy[0];
        int y = xy[1];

        return (Math.abs(x) + Math.abs(y)) <= tolerancia;
    }

    private static int[] hodnoty(String trasa){
        int x = 0;
        int y = 0;
        int[] xy = new int[2];
        int hodnota = 0;

        boolean h = true;           // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        boolean d = false;
        boolean p = false;
        boolean l = false;

        for (int i = 0; i < trasa.length(); i++) {
            if (trasa.charAt(i) == 'P' || trasa.charAt(i) == 'L') {

                if (h) {
                    if (trasa.charAt(i) == 'P') {
                        p = true;
                    } else {
                        l = true;
                    }
                    h = false;
                    y -= hodnota;

                } else if (d) {
                    if (trasa.charAt(i) == 'P') {
                        l = true;
                    } else {
                        p = true;
                    }
                    d = false;
                    y += hodnota;

                } else if (p) {
                    if (trasa.charAt(i) == 'P') {
                        d = true;
                    } else {
                        h = true;
                    }
                    p = false;
                    x += hodnota;

                } else if (l) {
                    if (trasa.charAt(i) == 'P') {
                        h = true;
                    } else {
                        p = true;
                    }
                    l = false;
                    x -= hodnota;
                }

                hodnota = 0;
            }
            else{
                hodnota *= 10;
                hodnota += trasa.charAt(i)-'0';
            }
        }

        if(h){
            y -= hodnota;
        }
        else if(d){
            y += hodnota;
        }
        else if(p){
            x += hodnota;
        }
        else if(l) {
            x -= hodnota;
        }

        xy[0] = x;
        xy[1] = y;
        return xy;
    }

    public static void main(String[] args) {
        System.out.println(euklidovska("100L50P97", 0)); // false
        System.out.println(euklidovska("10L1L9", 2)); // true
        System.out.println(euklidovska("10P1P9", 1)); // false

        System.out.println(manhatanska("200P100L97", 0)); // false
        System.out.println(manhatanska("200P100P197P97", 6)); // true
        System.out.println(manhatanska("42L42P42L42P42L42P42L42P42L42L42P42L42P42L42L42P42L42P42P42L42P42L42L42P42L42P42L42L42P42L42P42L42P42L42P42", 0)); // true
    }
}
