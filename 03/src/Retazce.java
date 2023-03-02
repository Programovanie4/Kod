import java.util.Arrays;

public class Retazce {
    public static void main(String[] args) {
        String ja = "Ja";
        String[] p = new String[] {"Jana", "Anna", "Mama"};
        String janaString = p[0];
        System.out.println(janaString == "Jana");
        System.out.println(janaString == "Ja" + "na");
        System.out.println(janaString == ja + "na");
        System.out.println(janaString.equals(ja + "na"));
        System.out.println((ja + "na").equals(janaString));

        System.out.println( (janaString.charAt(0) == 'J') );
        for(int i = 0; i<janaString.length(); i++)
            System.out.print(janaString.charAt(i));
        char[] poleCharov = janaString.toCharArray();
        for(char ch : poleCharov) System.out.print(ch);

        for(int i = 0; i < p.length; i++)
            System.out.println(p[i]);
    }
}
