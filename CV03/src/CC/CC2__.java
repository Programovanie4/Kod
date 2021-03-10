package CC;


public class CC2__ {
    final static int NORTH = 1;
    final static int EAST = 2;
    final static int SOUTH = 3;
    final static int WEST = 4;

    static int direction = EAST;
    static long x = 0;
    static long y = 0;

    private static void init() {
        direction = EAST;
        x = 0;
        y = 0;
    }

    private static boolean isDigit(char ch) {
        if (ch >= '0' && ch <= '9') return true;
        return false;
    }

    private static void changeDirection(char ch) {
        if (ch == 'L') {
            switch (direction) {
                case EAST: direction = NORTH; break;
                case NORTH: direction = WEST; break;
                case WEST:  direction = SOUTH; break;
                case SOUTH: direction = EAST; break;
            }
        }
//        if (ch == 'P') {
//            switch (direction) {
//                case "x": direction = "-y";break;
//                case "-x": direction = "y";break;
//                case "y": direction = "x";break;
//                case "-y": direction = "-x";break;
//                default: break;
//            }
//        }
    }

    private static void makeStep(String stepBy) {
        long stepLength = Long.parseLong(stepBy);
//        switch (direction) {
//            case "x": x += stepLength;break;
//            case "-x": x -= stepLength; break;
//            case "y": y += stepLength;break;
//            case "-y": y -= stepLength;break;
//            default: break;
//        }
    }

    private static void stepAll(String trasa) {
        String step = "";
        for (char ch : trasa.toCharArray()) {
            if (isDigit(ch)) step += ch;
            else if (ch == 'L') {
                if (!step.equals("")) makeStep(step);
                changeDirection('L');
                step = "";
            } else if (ch == 'P') {
                if (!step.equals("")) makeStep(step);
                changeDirection('P');
                step = "";
            }
        }
        if (!step.equals("")) makeStep(step);
    }

    public static boolean euklidovska(String trasa, double tolerancia) {
        System.out.println(trasa);
        System.out.println(tolerancia);
        if (trasa.equals("")) return true;
        init();
        stepAll(trasa);
        if (Math.sqrt(Math.pow(x,2) + Math.pow(y,2)) <= tolerancia) return true;
        return false;
    }

    public static boolean manhatanska(String trasa, int tolerancia) {
        System.out.println(trasa);
        System.out.println(tolerancia);
        if (trasa.equals("")) return true;
        init();
        stepAll(trasa);
        if (Math.abs(x) + Math.abs(y) <= tolerancia) return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(euklidovska("", 0));
        System.out.println(euklidovska("", 0));

        System.out.println(euklidovska("100L50P97", 0)); // false
        System.out.println(euklidovska("10L1L9", 2)); // true
        System.out.println(euklidovska("10P1P9", 1)); // false

        System.out.println(manhatanska("200P100L97", 0)); // false
        System.out.println(manhatanska("200P100P197P97", 6)); // true
        System.out.println(manhatanska("42L42P42L42P42L42P42L42P42L42L42P42L42P42L42L42P42L42P42P42L42P42L42L42P42L42P42L42L42P42L42P42L42P42L42P42", 0)); // true

    }

}
