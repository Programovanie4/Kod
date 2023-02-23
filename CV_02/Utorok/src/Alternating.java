public class Alternating {

    public static boolean contains(StringBuilder znaky, char x) {
        for (int i = 0; i < znaky.length(); i++) {
            if (znaky.charAt(i) == x) return true;
        }
        return false;
    }

    public static StringBuilder extractChars(String str) {
        StringBuilder znaky = new StringBuilder("");
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (!contains(znaky, c)) {
                znaky.append(c);
            }
        }
        return znaky;
    }

    public static int dlzkaPostupnosti(String str, char ch1, char ch2) {
        char poslednyValidny = ch2;
        int dlzka = 0;
        for (int k = 0; k < str.length(); k++) {
            char c = str.charAt(k);
            if (c == ch1 && poslednyValidny == ch1) return 0;
            if (c == ch1) {
                dlzka++;
                poslednyValidny = ch1;
            }
            else if (c == ch2 && poslednyValidny == ch2) return 0;
            else if (c == ch2) {
                dlzka++;
                poslednyValidny = ch2;
            }
        }
        return dlzka;
    }
    public static int alternate(String str) {
        StringBuilder znaky = extractChars(str);
        int maxDlzka = 0;
        for (int i = 0; i < znaky.length(); i++) {
            for (int j = i + 1; j < znaky.length(); j++) {
                int dlzka = Math.max(dlzkaPostupnosti(str, znaky.charAt(i), znaky.charAt(j)), dlzkaPostupnosti(str, znaky.charAt(j), znaky.charAt(i)));
                maxDlzka = Math.max(dlzka, maxDlzka);

            }
        }

        return maxDlzka;
    }

    public static void main(String[] args) {
        System.out.println(alternate("abaacdabd"));
        System.out.println(alternate("ehdehhihde"));
        System.out.println(alternate("cd"));
        System.out.println(alternate("eeeeeeeeeeeeeeeee"));
    }
}
