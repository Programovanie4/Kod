import java.util.*;

public class HashSetDemo {
  public static void main(String[] args) {
	  {
	    //Set<String> s = new HashSet<String>(); // >= Java 1.5, zaviedla generics
	    //Set<String> s = new HashSet<>();  // >= Java 1.7, zaviedla diamond operator <>
	    //Set<String> s = new HashSet<>(); // specialitka IntelliJ
        Set<String> s = new LinkedHashSet<>();

		Set<String> s1 = new HashSet<>();

	    for (String a : args)
	      if (!s.add(a))	// nepodarilo sa prida
	        System.out.println("opakuje sa: " + a);
	    System.out.println(s.size() + " rozne: " + s);
	    Object[] poleObj = s.toArray();
	    for(Object o:poleObj) System.out.print(o);
	    
	    String[] poleStr = s.toArray(new String[0]);
	    for(String str:poleStr) System.out.print(str);
	}
    {
    	Set<Integer> s = new HashSet<>();
    	s.add(1); s.add(2); s.add(3);   	
    }
	  {
		  var q = Set.of(true, false);
		// Set<Set<Integer>> powerSet matematicky {{},{0},{1},{0,1}}
		  var powerSet = Set.of(
				  Set.of(), Set.of(0), Set.of(1), Set.of(0,1) );

		  var a = 0;
		  var pole = new String[10];
		  var list = new ArrayList<String>();
		  var map = new HashMap<String, String>();
	  }
    } 
}


