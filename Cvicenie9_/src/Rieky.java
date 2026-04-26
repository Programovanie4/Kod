import java.util.ArrayList;
import java.util.List;

public class Rieky {
    public static String simulation(int mapWidth, int n, int simulationTime) {
        long time = simulationTime*1000L;
        List<String> results = new ArrayList<>();
        List<Character> row = new ArrayList<>();
        for (int d=0; d<mapWidth; d++) row.add(' ');
        for (int i=0; i<n; i++) new Rieka(mapWidth, row).start();  // nastartovanie riek
        try {
            Thread.sleep((long) 50);
        } catch (InterruptedException ee1) { }
        for (int last=0; last<=10 && time >= 200; last++, time-=250) {
            StringBuffer sb = new StringBuffer();
            int count = 0;
            for(char ch : row) {
                sb.append(ch);
                if (ch !=  ' ') count++;
            }
            results.add(sb.toString());
            System.out.println(sb.toString());
            if (count > 1) last = 0;
            for (int d=0; d<mapWidth; d++) row.set(d,' ');
            try {
                Thread.sleep((long) 250);
            } catch (InterruptedException ee) {
            }
        }
        return String.join("\n",results);
    }

    public static void main(String[] args) {

        System.out.println(
                //simulation(20, 4, 30),
                simulation(30, 10, 300)
        );
    }
}