import java.util.Arrays;

public class Words {

    private static String clean(String s) {
        return
                s.toLowerCase()
                .replaceAll(" ", "")
                .replaceAll(",", "")
                .replaceAll("\\.", "")
                        // pozor, argument je regularny vyraz a "." znamena akekolvek pismenko,
                        // teda nahradi vsetky pismenka za epsilon
                .replaceAll(":", "")
                .replaceAll("\"", "")
                .replaceAll("\'", "")
                .replaceAll("-", "")
                .replaceAll("!", "")
                .replaceAll(";", "");

    }

    public static boolean palindrom(String s) {
        String s2 = clean(s);
        for (int i = 0, j = s2.length() - 1; i < j; i++, j--) {
            if (s2.charAt(i) != s2.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static boolean panagram(String s) {
        String s2 = clean(s);
        if (s2.length() != 26) {    // musi mat presne 26 pismen
            return false;
        }
        for (char x = 'a'; x <= 'z'; x++) { // neexistuje ziadne, ktore sa tam nenachadza
            if (s2.indexOf(x) == -1) {
                return false;
            }
        }
        return true;
    }

    public static boolean anagram(String s1, String s2) {
        char[] ch1 = clean(s1).toCharArray();   // vyrobime polia znakov oboch
        char[] ch2 = clean(s2).toCharArray();
        Arrays.sort(ch1);                       // utriedime
        Arrays.sort(ch2);
        return Arrays.equals(ch1,ch2);          // porovname
    }
}

