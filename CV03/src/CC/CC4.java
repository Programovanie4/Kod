package CC;

import java.util.ArrayList;
import java.util.List;

public class CC4 {
    public static int najvacsia(int n) {
        int x = 0;
        for (int i = 1; i <= n; i = 2 * i) {
            x = i;
        }

        return x;
    }

    public static int najmensia(int n) {
        int x = 0;
        for (int i = 1; i <= n; i = 2 * i) {
            x = i;
        }

        while (true) {
            n = n - x;
            if (n == 0) break;
            while (x > n && x != 1) {
                x = x / 2;
            }
        }
        return x;
    }

    public static int damTringelt(int n) {
        List<Integer> mocninyDvojky = new ArrayList<>();
        for (int i = 1; i <= 1048576; i = 2 * i) {   //!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 0x100000
            mocninyDvojky.add(i);
        }

        int counter = 0;
        int x = mocninyDvojky.get(counter);
        while (x <= n) {
            x = mocninyDvojky.get(counter);
            counter = counter + 1;
        }

        if (x == n) {
            return mocninyDvojky.get(counter);
        } else {
            return x - n;
        }
    }

    public static int pocetMoznosti(int n) {        //!!!!!!!!!!!!!
        int counter = 0;
        for (int i = 0; i < 250; i++) {
            for (int j = 0; j < 128; j++) {
                if(i + 2*j > n){
                    continue;
                }
                for (int k = 0; k < 64; k++) {
                    if(i + 2*j + 4*k > n){
                        continue;
                    }
                    for (int l = 0; l < 32; l++) {
                        if(i + 2*j + 4*k + 8*l > n){
                            continue;
                        }
                        for (int m = 0; m < 16; m++) {
                            if(i + 2*j + 4*k + 8*l + 16*m> n){
                                continue;
                            }
                            for (int nn = 0; nn < 8; nn++) {
                                if(i + 2*j + 4*k + 8*l + 16*m + 32*nn> n){
                                    continue;
                                }
                                for (int o = 0; o < 4; o++) {
                                    if(i + 2*j + 4*k + 8*l + 16*m + 32*nn + 64*o> n){
                                        continue;
                                    }
                                    for (int p = 0; p < 2; p++) {
                                        if (i + j * 2 + k * 4 + l * 8 + m * 16 + nn * 32 + o * 64 + p * 128 == n) {
                                            counter++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        System.out.println(pocetMoznosti(19));
////        System.out.println(najvacsia(19));
//        System.out.println(najmensia(1));
//        System.out.println(najmensia(2));
//        System.out.println(najmensia(6));
//        System.out.println(najmensia(7));
//        System.out.println(najmensia(12));
//        System.out.println(najmensia(16));
//        System.out.println(najmensia(19));
    }
}