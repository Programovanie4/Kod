import static java.util.Arrays.asList;

public class Javaberg {
    public static void main(String[] args) {
        System.out.println(
                asList(
                'I', 'x',
                '`', 'u',
                '`', 'a',
                'x', 'd',
                'q', 'f')
                .stream()
                .filter( c-> c != 120)
                .map(c -> (char) (c+1))
                .map(c -> ""+c)
                .reduce((a,b) -> a+b)
                .get());
    }
}
