import java.util.Objects;

public class Complx {
    private double real;    // realna cast
    private double imag;    // imaginarna cast

    public Complx(double real, double imag) {
        this.real = real;
        this.imag = imag;
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

    public void setImag(double imag) {
        this.imag = imag;
    }

    public boolean isXxx() {
        return xxx;
    }

    public void setXxx(boolean xxx) {
        this.xxx = xxx;
    }

    @Override
    public String toString() {
        return "Complx{" +
                "real=" + real +
                ", imag=" + imag +
               //, xxx=" + xxx +
                '}';
    }

    private boolean xxx;
}