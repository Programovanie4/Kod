package H6;

public class Premenna extends Polynom {
    String prem;

    public Premenna(String s) {
        prem = s;
    }

    @Override
    Double valueAt(String[] vars, double[] values) {
        for(int i = 0;i < vars.length; i++){
            if(vars[i].equals(prem)){
                return values[i];
            }
        }
        return null;
    }

    @Override
    Polynom derive(String var) {
        if(prem.equals(var)){
            return new Konstanta(1);
        }
        return new Konstanta(0);
    }

    @Override
    public String toString() {
        return prem;
    }
}
