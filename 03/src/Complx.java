import java.util.Objects;

public class Complx {
    private double real;    // realna cast
    private double imag;    // imaginarna cast

    boolean xxx;

    public boolean isXxx() {
        return xxx;
    }

    public void setXxx(boolean xxx) {
        this.xxx = xxx;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImag() {
        return imag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complx complx = (Complx) o;
        return Double.compare(complx.real, real) == 0 && Double.compare(complx.imag, imag) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imag);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("real=").append(real);
        sb.append(", imag=").append(imag);
        sb.append('}');
        return sb.toString();
    }

    public void setImag(double imag) {
        this.imag = imag;
    }

    public Complx(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }
}
