import java.util.Random;

public class AutomatSol {

    private AutoNodeSol first;

    public AutomatSol(AutoNodeSol first) {
        this.first = first;
    }

    public AutoNodeSol first() {
        return first;
    }

    public String generate(int length) {
        StringBuilder output = new StringBuilder(length);
        int counter = 0;
        AutoNodeSol current = first;
        while (counter < length) {
            output.append(current.getValue());
            current = current.getNextRandom();
            counter++;
        }
        return output.toString();
    }

    public Boolean accepts(String word) {
        AutoNodeSol current = first;
        for (char nextChar : word.toCharArray()) {
            current = current.getNextByValue(nextChar);
            if (null == current) {
                return false;
            }
        }
        return true;
    }

}
