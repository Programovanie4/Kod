import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

record Student(Integer studentID, String courseID, Integer grade) {}
public class MoreExamples {


    public static void main(String[] args) {
        List<Integer> lst = IntStream.range(0, 100).boxed().toList();
        System.out.println(
//            lst.stream().map(x -> x%10).collect(Collectors.toSet())

//                lst.stream().collect(Collectors.toMap(
//                        x -> x,
//                        x -> x
//                ))

//                lst.stream().collect(Collectors.toMap(
//                        Integer::toBinaryString,
//                        x -> Integer.toBinaryString(x).length()
//                ))

//                lst.stream().collect(Collectors.toMap(
//                        x -> x % 10,
//                        x -> x
//                ))

//                lst.parallelStream().collect(
//                        Collectors.groupingBy( e -> (String.valueOf(e).length()) ))

//                lst.stream().collect(
//                        Collectors.groupingBy(
//                                e -> Integer.toString(e).length(),
//                                Collectors.maxBy(Integer::compare))
//                        )

//                lst.stream().collect(
//                        Collectors.groupingBy(
//                                e -> e/10,
//                                Collectors.counting())
//                )

                //lst.stream().reduce(0, Integer::sum)

                //lst.stream().reduce(0, (a,b) -> a+b)
                //lst.stream().mapToLong(e -> e).reduce(0L, (a, b) -> a + b)

                //lst.stream().reduce( Integer::max)

                lst.stream()
                        .map(x -> Integer.toString(x))
                        .reduce( "vysledok",  (acc, x) -> acc+x)

//                lst.stream()
//                        .map(x -> Integer.toString(x))
//                        .reduce(  (acc, x) -> acc+x)


        );


        List<Student> db = List.of(
                new Student(1, "C1", 2),
                new Student(1, "C2", 1),
                new Student(1, "C3", 3),

                new Student(2, "C1", 1),
                new Student(2, "C2", 5),

                new Student(3, "C1", 1),
                new Student(3, "C2", 1),
                new Student(3, "C3", 1),

                new Student(4, "C1", 4),
                new Student(4, "C2", 3),
                new Student(4, "C3", 2)
                );

        System.out.println(
//            db.stream().collect(Collectors.groupingBy(Student::studentID,
//                                                   Collectors.averagingDouble(Student::grade)))
//                // {1=2.0, 2=3.0, 3=1.0, 4=3.0}
//            db.stream().collect(Collectors.groupingBy(Student::courseID,
//                                                   Collectors.maxBy(Comparator.comparingInt(Student::grade))))
//        // {1=2.0, 2=2.5, 3=2.0}

            db.stream().collect(Collectors.groupingBy(Student::courseID,
                                                   Collectors.averagingDouble(Student::grade)))
                    .entrySet().stream()
                    //.max((o1,o2) -> Double.compare(o1.getValue(), o2.getValue()))
                    .max(Comparator.comparingDouble(Map.Entry::getValue))
                    .get()
                    .getValue()

        );

        System.out.println(
                //Stream.iterate("", s -> "a"+s).limit(10).toList()
        //        Stream.iterate(1L, n -> 2*n).takeWhile(n -> n < 1_000_000).toList()
//                Stream.iterate(1L, n -> n < 1_000_000, n -> 2*n).toList()
//
//
//                Stream.iterate(918972645, n -> n > 0, n -> n/10).map(n -> n%10).toList()
//
                Stream.iterate(918972645, n -> n > 0, n -> n/10)
                        .map(n -> n%10)
                        //.reduce((a,y) -> 10*a+y)
                        .reduce(0,(a,y) -> 10*a+y)

        );
    }

}
