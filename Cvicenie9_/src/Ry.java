import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ry {

    public static String simulation(int mapWidth, int n, int simulationTime) {
        List<String> results = new ArrayList<>();
        char[] riadok = new char[mapWidth];
        Arrays.fill(riadok, ' ');
        for(int r = 0; r < n; r++) new R(mapWidth, riadok).start();
        int elapsedTime = 0;
        int d = 0;
        while (d < 10 && elapsedTime < simulationTime * 1000L) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            elapsedTime += 250;
            StringBuilder sb = new StringBuilder();
            int count = 0;
            sb.append(elapsedTime);
            for (char ch : riadok) {
                sb.append(ch);
                if (ch != ' ') count++;
            }
            Arrays.fill(riadok, ' ');
            results.add(sb.toString());
             System.out.println(sb.toString());
            if (count == 1) {
                d++;
            }
        }


        return String.join("\n", results);
    }

    public static void main(String[] args) {
        System.out.println(simulation(30, 10, 30));
    }
}
