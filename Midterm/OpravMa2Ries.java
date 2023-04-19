import java.util.*;

class Zviera implements Comparable<Zviera>{
    static int totalID;
    int ID;
    public Zviera() {
        totalID++;
        ID = totalID;
    }

    @Override
    public String toString() {
        return Integer.toString(ID);
    }

    @Override
    public int compareTo(Zviera o) {
        return Integer.compare(ID, o.ID);
    }

    @Override
    public boolean equals(Object obj) {
        return this.getClass().equals(obj.getClass());
    }

    @Override
    public int hashCode() {
        return (this instanceof Psicek)?1:0;
    }
}
class Psicek extends Zviera { }
class Macicka extends Zviera {}
public class OpravMa2Ries {
    public static void main(String[] args) {
        var p1 = new Psicek();  // ID 1
        var m1 = new Macicka(); // ID 2
        var p3 = new Psicek();  // ID 3
        var m2 = new Macicka(); // ID 4
        var l = List.of(p1,p3,m1,m2);
        l.forEach( System.out::print);  // 1 3 2 4

        var x = new ArrayList<>(l);
        x.sort(Comparator.comparingInt(z -> -z.ID));
        x.forEach( System.out::print);  // 4 3 2 1

        var y = new TreeSet<>(l);
        y.forEach( System.out::print); // 1234

        var z = new HashSet<>(l);
        System.out.println(z.size());  // 2
    }
}
