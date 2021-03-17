public class Automat {

    private AutoNode first;

    public Automat(AutoNode first) {
        this.first = first;
    }

    public AutoNode first() {
        return first;
    }

    public String generate(int length) {
        StringBuilder output = new StringBuilder(length);
        int counter = 0;
        AutoNode current = first;
        while (counter < length) {
            output.append(current.getValue());
            current = current.getNextRandom();
            counter++;
        }
        return output.toString();
    }

    public Boolean accepts(String word) {
        AutoNode current = first;
        for (char nextChar : word.toCharArray()) {
            current = current.getNextByValue(nextChar);
            if (null == current) {
                return false;
            }
        }
        return true;
    }

}
