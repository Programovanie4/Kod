public class Newton {

    public static void main(String[] args) {
        var x = 0.0;
        var y = 0.0;
        var vx = 1.0;
        var vy = 0.0;
        for (int t = 0; t <200; t++) {
            x += vx;
            y += vy;
            vx += 0.01;
            System.out.println(t + "\t" + "*".repeat((int)x) );

        }
    }
}
