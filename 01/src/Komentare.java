/**
 * priklad, ktory ilustruje variabilitu komentarov
 * @author PB
 * @version 2009
 * @since 1.0
 */
public class Komentare {
	/**
	 * <code>main</code> je metoda hlavneho programu
	 * <br>
	 * 
	 * @param args pole retazcov prikazoveho riadku
	 * @return <code>void</code> nevracia nic, je to procedura
	 */
    public static void main(String[] args) {
        var ucet = 0.0;
        var pocetPiv = 5;
        ucet = pocetPiv * 1.90;  	// typicky komentar
        System.out.println("Platis = " + ucet);

        ucet = pocetPiv * /* 1.90 */ 2.50;  /* 1.90 je za desinku */
        System.out.println("Platis = " + ucet);
    }
}
