import java.util.function.BiFunction;

public class Integers {
    static Integer maxOf(Integer first, Integer second) {
        return applyOnNull(first, second, Math::max);
    }

    static Integer minOf(Integer first, Integer second) {
        return applyOnNull(first, second, Math::min);
    }

    static Integer applyOnNull(Integer first, Integer second, BiFunction<Integer, Integer, Integer> func) {
        if (first == null && second == null) {
            return null;
        }

        if (first == null) {
            return second;
        }

        if (second == null) {
            return first;
        }

        return func.apply(first, second);
    }

    private Integers(){
        //Prevent instances
    }
}
