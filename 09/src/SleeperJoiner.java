
public class SleeperJoiner {
	public static void main(String[] args) throws InterruptedException {
	    Sleeper prvy = new Sleeper("Prvy", 6000);
	    Sleeper druhy = new Sleeper("Druhy", 6000);
	    Joiner treti = new Joiner("Treti", druhy);
	    Joiner stvrty = new Joiner("Stvrty", prvy);
	    Thread.sleep(3000);
	    prvy.interrupt();
	}
}
