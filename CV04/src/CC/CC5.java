package CC;

public class CC5 {//1400000

    static String castRokaBezZmeny(int i) {
        StringBuilder castBezZmeny = new StringBuilder();

        // 31 dnove mesiace
        castBezZmeny.append("(").append(i).append("[-](0[13578]|1[02])[-](0[1-9]|[12][0-9]|[31]))|");

        // 30 dnove mesiace
        castBezZmeny.append("(").append(i).append("[-](0[469]|1[1])[-](0[1-9]|[12][0-9]|[30]))|");
        return castBezZmeny.toString();
    }

    static String prestupny(int i) {
        return "(" + castRokaBezZmeny(i) + ("(" + i + "[-](0[2])[-](0[1-9]|[12][0-9])))|"); // feb prestupny
    }

    static String normalny(int i) {
        return "(" + castRokaBezZmeny(i) + ("(" + i + "[-](0[2])[-](0[1-9]|[12][0-8])))|"); // feb neprestupny
    }

    static String RegExpDate() {
        StringBuilder RED = new StringBuilder();

        for (int i = 0; i < 10000; i++) {
            if (i % 4 == 0) {
                if (i % 100 == 0 && i % 400 == 0) {
                    RED.append(prestupny(i));
                } else if (i % 100 == 0) RED.append(normalny(i));
                else RED.append(prestupny(i));
            } else {
                RED.append(normalny(i));
            }
        }
        return RED.substring(0, RED.length() - 1); // zbavim sa konecneho |
    }

    public static void main(String[] args) {
        System.out.println("1900-02-29".matches(RegExpDate()));
        System.out.println("1900-02-28".matches(RegExpDate()));
        System.out.println("1904-02-29".matches(RegExpDate()));
        System.out.println("1908-02-29".matches(RegExpDate()));

        System.out.println();

        System.out.println("2600-02-29".matches(RegExpDate()));
        System.out.println("1800-02-29".matches(RegExpDate()));
        System.out.println("2100-02-29".matches(RegExpDate()));
        System.out.println("2200-02-29".matches(RegExpDate()));
        System.out.println("2300-02-29".matches(RegExpDate()));
        System.out.println("2500-02-29".matches(RegExpDate()));
        System.out.println("1900-02-29".matches(RegExpDate()));

        System.out.println();

        System.out.println("1600-02-29".matches(RegExpDate()));
        System.out.println("2000-02-29".matches(RegExpDate()));
        System.out.println("2400-02-29".matches(RegExpDate()));

        System.out.println();

        System.out.println("1900-10-31".matches(RegExpDate()));
        System.out.println("1900-02-32".matches(RegExpDate()));
        System.out.println("1904-10-40".matches(RegExpDate()));
        System.out.println("1908-13-10".matches(RegExpDate()));

    }
}