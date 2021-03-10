package CC;

public class CC1 {
    public static int pocet12(int n){
        if (n < 0) return 0;
        if (n == 0) return 1;
        return pocet12(n-1) + pocet12(n-2);
    }
    public static int pocet123(int n){
        if (n < 0) return 0;
        if (n == 0) return 1;
        return pocet123(n-1) + pocet123(n-2) + pocet123(n-3);
    }

    public static void main(String[] args) {
        for(int i = 0; i<100; i++)
            System.out.println(i + "\t = " + pocet12(i)+ "\t = " + pocet123(i));
    }
}
