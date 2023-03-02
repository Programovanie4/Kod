public class Obdlznik extends DvaD {
    public Bod2D lavyDolny;
    public double dx, dy;

    public Obdlznik(Bod2D lavyDolny, double dx, double dy) {
        super();
        this.lavyDolny = lavyDolny;
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * definujte obsah obdlznika
     */
    @Override
    public double obsah() {
        return dx * dy;
    }

    /**
     * definujte obvod obdlznika
     */
    @Override
    public double obvod() {
        return 2 * (dx + dy);
    }

    /**
     * definujte test, ci bod b je v obdlzniku, alebo na jeho stranach
     */
    @Override
    public boolean jeV(Bod2D b) {
        double x = b.getX();
        double y = b.getY();
        double rectangleX = lavyDolny.getX();
        double rectangleY = lavyDolny.getY();

        return x >= rectangleX && x <= rectangleX + dx &&
                y >= rectangleY && y <= rectangleY + dy;
    }

    /**
     * posunite oblznika o vektor b
     */
    @Override
    public void posun(Bod2D b) {
        lavyDolny.setX(lavyDolny.getX() + b.getX());
        lavyDolny.setY(lavyDolny.getY() + b.getY());
    }
}
