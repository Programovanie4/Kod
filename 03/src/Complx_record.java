record Complx_record(double real, double imag) {
    Complx_record {  // tzv. kompaktny konstuktor
    }
//  tzv. kanonicky konstruktor, a moze byt jeden, nie oba konstruktory
//    Complx_record(double real, double imag) {
//        this.real = real;  this.imag = imag;
//    }

    //data class/record moze mat staticku premenu
    static int counter = 0;
    //data class/record moze mat staticku metodu
    static int getCounter() {
        return counter;
    }
    // aj staticku sekciu
    static {
       int pocitadlo = 0;
    }
    //data class/record NEmoze mat triednu premennu, jedine hodnoty v objekte su zname z konstruktora
    //int index = 0;

    //data class/record naprik tomu moze mat triednu metodu
    double abs() {
        return Math.sqrt(real*real + imag*imag);
    }
}
