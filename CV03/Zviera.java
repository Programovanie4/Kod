abstract class Zviera {
    abstract String urobZvuk();

    static Zviera[] zoo = {new Pes(), new Macka()};
    public static void main(String[] args) {
        for(Zviera z : zoo) {
            System.out.println(z.urobZvuk());
        }

    }
}
