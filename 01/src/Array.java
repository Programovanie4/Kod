import java.util.Arrays;

public class Array {

	public static void main(String[] args) {
		int[] a = new int[10];		// pole primitivneho typu int
		Arrays.fill(a, 0);			// vypln pole nulami
		Integer[] aInteger = new Integer[100];	// pole objektov typu Integer
		Integer[] bInteger = new Integer[10];	// pole objektov typu Integer
		Arrays.fill(aInteger,0,100,10);
		System.arraycopy(aInteger, 11, bInteger, 3, 7);
		for(Integer elem:bInteger) // null,null,null,10,10,10,10,10,10,10,
			System.out.print(elem+",");
		String[] s = {"janko","marienka","jozko","mracik"};
		String[] s_copy = new String[4];
		System.arraycopy(s, 0, s_copy, 0, s.length);
		Arrays.sort(s);
		for(String elem:s) // janko,jozko,marienka,mracik,
			System.out.print(elem+",");
		System.out.println(		// -5 
				Arrays.binarySearch(s, "sandokan"));
		System.out.println(		// 2
				Arrays.binarySearch(s, "marienka"));
		System.out.println(Arrays.equals(s, s_copy));
	}
}
