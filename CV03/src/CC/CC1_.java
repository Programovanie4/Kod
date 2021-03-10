package CC;

public class CC1_ {
    public static int pocet12(int n){
        int a = 1;
        int b = 1;
        while (--n > 0) {
            a = a+b;
            b = a-b;
        }
        return a;
    }

//  dnesny warmup, najdes v zostave RoomBa3
//
//    public static int pocet123(int n){
//        int a = ?;
//        int b = ?;
//        int c = ?;
//        while (???) {
//        ???
//        }
//        return ?;
//    }

    public static void main(String[] args) {
        for(int i = 0; i<100; i++)
            System.out.println(i + "\t = " + pocet12(i)+ "\t = " + pocet123(i));
    }



    public static int pocet123(int n){
        int a = 1;
        int b = 1;
        int c = 2;
        while (--n > 0) {
            c = a+b+c;
            b = c-a-b;
            a = c-b-a;
        }
        return b;
    }

}
