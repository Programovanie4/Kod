/**
 * priklad na jednorozmerne pole
 * @author PB
 * @version 2009
 */
public class Jednoduche {
 public static void main(String[] args) {
     int[] prvocisla = { 2, 3, 5, 7, 11, 13, 19 };
     var prvocisla_ = new int[]{2, 3, 5, 7, 11, 13, 19};
 final int MAX = 20;				// konstanta - velkost pola

 // int[] poleInt; 					// definicia pola
 // poleInt = new int[MAX];			// vytvorenie pola
   //  int[] poleInt = new int[MAX];			// definicia pola s vytvorenim
    var poleInt = new int[MAX];			// definicia pola s vytvorenim
    for (var i = 0;  i < poleInt.length;  i++) {	// i < MAX
      poleInt[i] = i + 1;				// inicializacia pola
      System.out.print(poleInt[i] + "  ");
    } // for
  }  // main
} // class}
