public class HodnotenieAutorske {
    private final String meno;
    private final String trieda;
    private final int znamka;

    public HodnotenieAutorske(String meno, String trieda, int znamka) {
        this.meno = meno;
        this.trieda = trieda;
        this.znamka = znamka;
    }

    @Override
    public String toString() {
        return "Riadok{" +
                "meno='" + meno + '\'' +
                ", trieda='" + trieda + '\'' +
                ", znamka=" + znamka +
                '}';
    }

    public String getMeno() {
        return meno;
    }

    public String getTrieda() {
        return trieda;
    }

    public int getZnamka() {
        return znamka;
    }
}