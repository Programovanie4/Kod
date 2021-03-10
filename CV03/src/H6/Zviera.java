package H6;

abstract public class Zviera {
    abstract public String urobZvuk();

    public static void main(String[] args) {
        Pes pes = new Pes();
        Macka macka = new Macka();
        //System.out.println(pes.urobZvuk());
        //System.out.println(macka.urobZvuk());
        Zviera[] pole = {
                    new Pes(),
                    new Macka()
        };
        for(Zviera i:pole){
            System.out.println(i.urobZvuk());
        }
    }
}

