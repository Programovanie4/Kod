public class RZP {

    public static void main(String[] args) {
        var x = 0.0;
        var y = 0.0;
        var vx = 1.0;
        var vy = 0.0;

        for(var t = 0; t < 20; t++) {
            x += vx;
            vx += 1;
            y += vy;
            System.out.println(t+ "s. \t" +  "*".repeat((int)x) + ", "+ y);
        }
    }
}
