package CC;

import java.util.Arrays;

public class CC5 {

    public static boolean palindrom(String s){

        s = s.toLowerCase();                        //!!!!!!!!!!!!!!
        s = s.replaceAll("[-\\.,:!\"\' ]", "");
//        s = s.replaceAll(" ","");
//        s = s.replaceAll("\\.","");
//        s = s.replaceAll(",","");
//        s = s.replaceAll(":","");
//        s = s.replaceAll("!","");
//        s = s.replaceAll(";","");
//        s = s.replaceAll("\"","");
//        s = s.replaceAll("\'","");
//        s = s.replaceAll("-","");
        //System.out.println(s);

        return new StringBuilder(s).reverse().toString().matches(s);
    }
    public static boolean panagram(String s){

        s = s.toLowerCase();
        s = s.replaceAll(" ","");
        s = s.replaceAll("\\.","");
        s = s.replaceAll(",","");
        s = s.replaceAll(":","");
        s = s.replaceAll("!","");
        s = s.replaceAll(";","");
        s = s.replaceAll("\"","");
        s = s.replaceAll("\'","");
        s = s.replaceAll("-","");

        if(s.length()!=26) return false;
        for (char a = 'a';a<='z';a++){
            if(s.indexOf(a)<0)
                return false;
        }
        return true;
    }

    public static boolean anagram(String s1, String s2){



        s1 = s1.toLowerCase();
        s1 = s1.replaceAll(" ","");
        s1 = s1.replaceAll("\\.","");
        s1 = s1.replaceAll(",","");
        s1 = s1.replaceAll(":","");
        s1 = s1.replaceAll("!","");
        s1 = s1.replaceAll(";","");
        s1 = s1.replaceAll("\"","");
        s1 = s1.replaceAll("\'","");
        s1 = s1.replaceAll("-","");
        s1 = s1.replaceAll("=","");

        s2 = s2.toLowerCase();
        s2 = s2.replaceAll(" ","");
        s2 = s2.replaceAll("\\.","");
        s2 = s2.replaceAll(",","");
        s2 = s2.replaceAll(":","");
        s2 = s2.replaceAll("!","");
        s2 = s2.replaceAll(";","");
        s2 = s2.replaceAll("\"","");
        s2 = s2.replaceAll("\'","");
        s2 = s2.replaceAll("-","");
        s2 = s2.replaceAll("=","");
        if(s1.length() != s2.length()){
            return false;
        }

        char []arrays1 = s1.toCharArray();
        char []arrays2 = s2.toCharArray();

        char []ch1 = s1.toCharArray();
        char []ch2 = s2.toCharArray();

        Arrays.sort(ch1);
        Arrays.sort(ch2);

        return Arrays.equals(ch1,ch2);

    }

    public static void main(String[] args) {
        //System.out.println(palindrom("Jelenovi pivo nelej")); // true
        //System.out.println(panagram("D.V. Pike flung J.Q. Schwartz my box."));
        //System.out.println(panagram( "Dwarf mobs quiz lynx.jpg, kvetch"));
        //System.out.println(anagram("Hrabe povala","Pavol Habera"));
        System.out.println(anagram("The Hospital Ambulance", "A Cab, I Hustle to Help Man"));
        System.out.println("Dwarf mobs qu!!iz lynx.jpg, .kve,tch"
                .replaceAll("[-\\.,:!;\"\' ]", ""));
    }
}
