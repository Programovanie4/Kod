record Dvojica(Integer first, Integer second) {
        static int pocetInstancii = 0;
        public Dvojica {
            pocetInstancii++;
        }
        public static void main(String[] args) {
            var x = new Dvojica(1,2);
            System.out.println(x);      // Dvojica[first=1, second=2]
            // x.second *= 2*x.first;
            var y = new Dvojica(x.first, 2*x.first);
            System.out.println(x.equals(y));        // true
            System.out.println(x == y);             // false
            System.out.println("pocet instancii " + pocetInstancii); // pocet instancii 2
        }
}
