public class Alternating {
    public static boolean contains(StringBuilder str, char ch) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                return true;
            }
        }
        return false;
    }

    public static StringBuilder extractChars(String str) {
        StringBuilder differentChars = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char ch = str.charAt(i);
            if (!contains(differentChars, ch)) {
                differentChars.append(ch);
            }
        }
        return differentChars;
    }

    public static int count(String str, char first, char second) {
        int count = 0;
        char lastChar = 's';
        boolean valid = false;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char ch = str.charAt(i);
            if (ch == first || ch == second) {
                if (lastChar != ch || valid == false) {
                    valid = true;
                    lastChar = ch;
                    count++;
                } else {
                    return 0;
                }
            }
        }
        return count;
    }

    public static int alternate(String str) {
        int maxCount = 0;
        StringBuilder chars = extractChars(str);
        int length = chars.length();
        for (int i = 0; i < length; i++) {
            char first = chars.charAt(i);
            for (int j = i + 1; j < length; j++) {
                char second = chars.charAt(j);
                int count = count(str, first, second);
                maxCount = Math.max(count, maxCount);
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        System.out.println(alternate("abaacdabd"));
        System.out.println(alternate("ehdehhihde"));
        System.out.println(alternate("cd"));
        System.out.println(alternate("eeeeeeeeeeeeeeeee"));
    }
}
