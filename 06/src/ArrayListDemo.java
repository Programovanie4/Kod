import java.util.*;
public class ArrayListDemo {
  public static void main(String[] args) {
    String[] p = {"a","b","c","d"};
    List <String> s = new ArrayList<String>();
      for (String a : p)
          s.add(a);
    List <String> s1 = new ArrayList<>();
    ArrayList<String> s2 = new ArrayList<>();
    //ArrayList<String> s3 = new List<String>();

    List<String> s4 = List.of("a","b","c","d");

    s.add("e");
    s1.add("e");
    s2.add("e");
    //s4.add("e");


    for (Iterator<String> it = s.iterator();it.hasNext(); )
  	  System.out.print(it.next());
    System.out.println();

	s.set(1,"99");	s.remove(2);

	for (ListIterator<String> it = 				
	     s.listIterator(s.size());it.hasPrevious(); )
  	  System.out.print(it.previous());
    System.out.println();
	HashSet<String> hs = new HashSet<String>();
	hs.add("A");	hs.add("B");
	s.addAll(2,hs);
	System.out.println(s);
  }
}
