package CC;

import java.lang.System;
import java.lang.Math;

public class Ada {
    public static int ageAL(){
        return 36;
    }
    public static double bernoulli_(int ns){
        //https://codegolf.stackexchange.com/questions/65382/bernoulli-numbers
        if(ns == 0)
            return 1;
        if(ns%2 != 0 && ns != 1 ){
            return 0;
        }
        else {
            double t = 0;
            for( int k = 0; k< ns; k++){
                t += combination(ns, k) * bernoulli_(k) / (ns - k + 1);
            }
            return 1 - t;
        }
    }
    public static double factorial(int n){
        if( n < 1)
            return 1;
        else{
            return n * factorial(n - 1);
        }
    }


    public static double combination(int m, int k){
        if(k <= m)
            return factorial(m)/(factorial(k) * factorial(m - k));
        else{
            return 0;
        }
    }

    public static double bernoulli(int ns){
        return (ns == 1)?-0.5:bernoulli_(ns);
    }


    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            System.out.println(bernoulli(i)); // 1
        }
        /*
         * 0  1.0
         * 1  +-0.5
         * 2  0.166666
         * 3  0
         * 4  -0.03333
         * 5  0
         * 6  0.023809523
         * 7  0
         * 8  -0.0333333
         * 9  0
         * 10  0.0757575
         * */


    }
}