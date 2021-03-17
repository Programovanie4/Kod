import java.util.ArrayList;

interface Car {
	final static int speed = 50;		// in km/h
	public void distance();
    static int foo() {
        return speed;
    }
}
