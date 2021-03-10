public class Priemer {
    // zaciatocnik nakodil tento aritmeticky priemer, a test je nekompromisny
    // viete opravit kod, aby ste ziskali co najviac bodov
    public static double priemer(String[] data) {
        int sucet = 0;
        for(int i = 0; i < data.length; i++) {
            sucet += Integer.valueOf(data[i]);
        }
        return sucet/data.length;
    }

    public static void main(String[] args) {
        System.out.println(priemer(new String[]{"1","2","3"}));  //(1+2+3)/3=2.0
        System.out.println(priemer(new String[]{"10","20"}));  //(10+20)/2=15
    }

//    public static double priemer(String[] data) {
//        if (data == null) return 0;
//        if (data.length == 0) return 0;
//        long sucet = 0;
//        for(int i = 0; i < data.length; i++) {
//            if (data[i] == null) continue;
//            if (!data[i].matches("-?\\d*")) return 0;
//            //try {
//            sucet += Integer.valueOf(data[i].trim());
//            //} catch (Exception e) {}
//        }
//        return (1.0*sucet)/data.length;
//    }

}