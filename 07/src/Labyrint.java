
class NedaSa extends RuntimeException {  }  // toto nechajte tak ako je

public class Labyrint {
    Integer[][] lab;                                    // labyrint samotny
    public Labyrint(Integer[][] lab) {                  // konstruktor zadarmo
        this.lab = lab;
    } // konstruktor
    @Override
    public String toString() {                           // aspon nejaky toString
        StringBuilder sb = new StringBuilder();
        String newLine = "";
        if (lab == null) sb.append(" null ");
        else
            for (Integer[] row : lab) {
                sb.append(newLine); newLine = "\n";
                if (row == null) sb.append(" null ");
                else
                    for (Integer elem : row)
                        sb.append((elem == null) ? " null" : elem);
            }
        return sb.toString();
    }
    //------------------------- vas kod piste odtialto dole ---------------------------------

    public boolean priechodziNull() {
        return false;  // toto doprogramujte
    }
    // priklady zo zadania
    public static void main(String[] args) {
        //----------------------------------- prikady na priechodziNull
        {
            Labyrint p = new Labyrint(new Integer[][]
                            {{ 1, 2,  3},
                             { 6,null,4},
                             { 7, 8,  9}});
            System.out.println(p.toString() + " .priechodziNull() = " + p.priechodziNull() + "\n");  // true
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                            {{ 1,   2, null},                                // Hint: {{ 1,   2,  8},
                             {null, 3, null},                                //        { 6,   3,  7},
                             {null, 4,  9}});                                //        { 5  , 4,  9}});
            System.out.println(p.toString() + " .priechodziNull() = " + p.priechodziNull() + "\n");  // true
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                            {{ 1,   null, null},                             // Hint: {{ 1,   2,  3},
                             {null, null, null},                             //        { 5,   6,  4},
                             {null, null, 9}});                              //        { 8  , 7,  9}});
            System.out.println(p.toString() + " .priechodziNull() = " + p.priechodziNull() + "\n");  // true
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{ 1,    6,   2},                                    // Hint: {{ 1,   6,  2},
                            {null, null, 3},                                    //        { 4,   5,  3},
                            {null,  7,   9}});                                  //        { 8  , 7,  9}});
            System.out.println(p.toString() + " .priechodziNull() = " + p.priechodziNull() + "\n");  // true
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                           {{ 1,    null,  5},
                            {null,  null,  6},
                            {2,       3,   9}});
            System.out.println(p.toString() + " .priechodziNull() = " + p.priechodziNull() + "\n");  // false
        }
    }
}
