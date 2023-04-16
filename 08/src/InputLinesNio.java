/**
 * citanie matice cisel
 * @author borovan
 *
 */
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class InputLinesNio {

  public static void main(String[] args) {
      try {
          List<String> lines = Files.readAllLines(Paths.get("subor.txt"));
          for (String line : lines) System.out.println(line);
          lines.forEach(System.out::println);
          System.out.println(lines.stream().mapToInt(String::length).sum());
          System.out.println(lines.stream().mapToLong(line ->line.chars().filter(ch -> ch=='*').count()).sum());

      } catch (IOException e) {
          e.printStackTrace();
      }
  }
}
