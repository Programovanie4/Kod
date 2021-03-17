import java.util.Arrays;

enum Produkt implements Comparable<Produkt> {
    CHIEB_CELOZRNNY(100,855),
    CHIEB_BIELY(100, 1234),
    KNACKBROT(100,1242),
    JOGURT_BIELY(100,229),
    SALAMA(100, 2090),
    PAPRIKA(100,90),
    MOZZARELLA(100,1147);

    private int unit;
    private int kJ;

    Produkt(int vaha, int kJ) {
        this.unit = vaha;
        this.kJ = kJ;
    }


    public int energykJ(int weight) {
        return weight*kJ/100;
    }
    public int energykCal(int weight) {
        return (int)(weight/100.0*kJ*0.2390057361);
    }

    public int dailykJ(int energy) {
        return energy/kJ;
    }
    public int dailykCal(int energy) {
        return (int)((double)energy*4.2/kJ);
    }

    public static void main(String[] args) {
        Produkt[] produkty = { CHIEB_BIELY, MOZZARELLA, JOGURT_BIELY, PAPRIKA};
        for (Produkt p : produkty) {
            System.out.println(p + " denne " + p.dailykCal(2500)*p.unit + " gramov");
        }
        for(Produkt p : Produkt.values()) {
            System.out.println(p + " denne " + p.dailykCal(2500)*p.unit + " gramov");
        }
        Arrays.sort(produkty);
        System.out.println(Arrays.asList(produkty));
        System.out.println(PAPRIKA.compareTo(CHIEB_BIELY));
        System.out.println(CHIEB_CELOZRNNY.compareTo(CHIEB_BIELY));
    }
}