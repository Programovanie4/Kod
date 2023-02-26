record Pair<A,B>(A first, B second) {
    static int pocetInstancii = 0;
    public Pair {
        pocetInstancii++;
    }
    public static void main(String[] args) {
        var x = new Pair<>(1,2);
        System.out.println(x);          // Pair[first=1, second=2]
        // x.second *= 2*x.first;
        var y = new Pair<>(x.first, 2*x.first);
        System.out.println(x.equals(y));        // true
        System.out.println(x == y);             // false
        var z = new Pair<>(Math.PI, Math.E);
        System.out.println(z);                  // Pair[first=3.141592653589793, second=2.718281828459045]
        var w = new Pair<>(y,z);
        System.out.println(w);                  // // Pair[first=3.141592653589793, second=2.718281828459045]
        System.out.println("pocet instancii " + pocetInstancii); //// Pair[first=3.141592653589793, second=2.718281828459045]
    }
}
