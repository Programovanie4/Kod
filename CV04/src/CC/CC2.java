package CC;

public class CC2 {
//    public class Ockovanie {  // pekny kod na RC
        public static boolean ok(String rc){
            String r_c = rc;
            if(!r_c.matches("^[0-9]{2}[0156]{1}[0-9]{1}[0123]{1}[0-9]{1}/[0-9]{3,4}$") ){
                return false;
            }

            int rok = Integer.parseInt(r_c.substring(0,2));
            if(r_c.length()==11 && Long.parseLong(r_c.replaceFirst("/", ""))%11 != 0){
                return false;
            }
            int mesiac = Integer.parseInt(r_c.substring(2, 4)) % 50;
            int den = Integer.parseInt(r_c.substring(4,6));

            if(mesiac > 12 || den > 31 || den < 1 || mesiac < 1) return false;
            if((rok > 53 ) && r_c.length() != 11) return false;

            if(mesiac == 2){
                if (rok%4 == 0 && den > 29) return false;
                else if(rok%4 != 0 && den >  28) return false;
            }

            if(((mesiac == 4) || (mesiac == 6) || (mesiac == 9) || (mesiac == 11)) && den > 30) return false;
            else{
                if(den > 31) return false;
            }
            return true;
        }

        public static int korektni(String[] osoby){

            int vysl = 0;
            for(String s:osoby){
                String[] osoba = s.replace(", ", ",").split(",");

                if(osoba.length < 2) continue;
                String r_c = osoba[1];
                if(ok(r_c)) vysl += 1;
            }

            return vysl;
        }
        //24.2.2021
        public static int kandidati75(String[] osoby){

            int vysl = 0;
            for(String s:osoby){
                String[] osoba = s.replace(", ",",").split(",");
                if(osoba.length < 2) continue;
                String r_c = osoba[1];
                if(ok(r_c)) {
                    int rok = Integer.parseInt(r_c.substring(0,2));
                    int mesiac = Integer.parseInt(r_c.substring(2, 4)) % 50;
                    int den = Integer.parseInt(r_c.substring(4,6));
                    //2021 - 75 =
                    if(rok > 21 && rok < 46) vysl++;
                    else if(rok == 46 && mesiac < 2) vysl++;
                    else if(rok == 46 && mesiac == 2 && den <= 24) vysl++;
                    if(rok <= 21 && r_c.length() == 10) vysl ++;
                }
            }

            return vysl;
        }

        public static void main(String[] args) {

            String [] osoby = {"a, 800229/0010, 827 ,a@b.c"};
            System.out.println(korektni(osoby));
//        "Jo??ko Mrkvi??ka, 771224/1240, 0902 354 787, jozo.mrkva@gmail.com", //je pr??klad korektn??ho rodn??ho ????sla, ale nie 75+
//                "Jo??ko P????ik, 430211/837, 0912 454 732, jozo.pucik@gmail.com" ,//je pr??klad korektn??ho rodn??ho ????sla s trojcifernou koncovkou, a je 75+
//                "Adam ??angala, 430211/8372, 0912 454 732, adams@gmail.com" ,//je pr??klad nekorektn??ho rodn??ho ????sla, lebo nie je delite??n?? 11timi, a je 75+
//                "Anna Kareninov??, 406212/123, 0932 124 234, annacar@gmail.com",// je pr??klad korektn??ho ??ensk??ho rodn??ho ????sla, a je 75+
//                "Sansa Starkov??, 995321/1235, 0999999999, allmenmustdie@gmail.com", //je pr??klad korektn??ho ??ensk??ho rodn??ho ????sla, ale nie je 75+
//                "Rysav?? Jalovica, 600600, , ", //je pr??klad nekorektn??ho rodn??ho ????sla
//        "Ve??k?? Gatsby, 6412287365, 911, vg@vg.com ",// je pr??klad form??tom nekorektn??ho rodn??ho ????sla
//        "Krstn?? otec, 211224/009, 000 000 000, ?????????.?????????.???"}; //je pr??klad nekorektn??ho rodn??ho ????sla, lebo ten de?? nebol.
//        System.out.println(kandidati75(osoby));
//        System.out.println(ok("211224/009"));
        }
    }
