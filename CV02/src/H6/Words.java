package H6;

import java.util.Arrays;

public class Words {
    public static String clean(String s){
        String []forbidden ={"\\.", ",", ";", "\'",
                "\"", " ", "!", "-", ":"};
        for(String str : forbidden){
            s = s.replaceAll(str, "");
        }
        s = s.toLowerCase();
        return s;
    }
    public static boolean palindrom(String s){
        if(s == null){
            return false;
        }
        s = clean(s);
        for(int i =0; i < s.length() / 2 ; i++){
            if(s.charAt(i) != s.charAt(s.length() - i - 1)){
                return false;
            }
        }
        return true;
    }
    public static boolean panagram(String s){
        s = clean(s);
        if(s.length() != 26){
            return false;
        }
        for(char ch = 'a'; ch <= 'z'; ch++){
            if(s.indexOf(ch) < 0){
                return false;
            }
        }
        return true;
    }
    public static boolean anagram(String s1, String s2){
        s1 = clean(s1);
        s2 = clean(s2);
        if(s1.length() != s2.length()){
            return false;
        }
        char []ch1 = s1.toCharArray();
        char []ch2 = s2.toCharArray();

        Arrays.sort(ch1);
        Arrays.sort(ch2);

        return Arrays.equals(ch1, ch2);
    }

    public static void main(String[] args) {
        System.out.println(palindrom("jelenovipivoneleja"));
    }
}
