public class Sedemciferne {
    private static int char2int(char ch) {
        return ch - '0';
    }
    private static int rek(int number) {
        if (number > 1_000_000){
            return (splna(number))?number:-1;
        } else {
            for (int c = 9; c > 0; c--) {
                if ((""+number).contains(""+c)) {
                    continue;
                } else {
                    var x = rek(10*number+ c);
                    if (x > 0) {
                        return x;
                    }
                }
            }
            return -1;
        }
    }
    private static Boolean splna(long n){
        for(char ch:(String.valueOf(n)).toCharArray()){
            int num = char2int(ch);
            if (n % num != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(rek(0));
    }
}
