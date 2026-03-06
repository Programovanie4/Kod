import java.util.Arrays;

public class Gramatika {

    public static String change(String s) {
        String grammar = "AA -> B, AB -> A, AC -> A, BA -> C, BB -> A, BC -> C, CA -> AA, CB -> A, CC -> BB";
        String[] rules = grammar.split(" -> |, "); //["AA", "B" ...]
        StringBuilder res = new StringBuilder();
        for (int i = 0 ; i < s.length() - 1 ; i++){
            String pair = s.substring(i, i+2);
            int index = Arrays.asList(rules).indexOf(pair);
            res.append(rules[index+1]);
        }
        return res.toString();
    }

    public static int numberOfIter(String s) {
        int counter = 0;
        while (s.length() > 1){
            s = change(s);
            counter++;
        }

        return counter;
    }

    public static boolean isCorrectOutput(String s, char c) {

        while (s.length() > 1){
            s = change(s);
        }

        if (s.charAt(0) == c){
            return true;
        }

        return false;
    }
}