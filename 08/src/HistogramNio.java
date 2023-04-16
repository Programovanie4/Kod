import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class HistogramNio {

	public static void main(String[] args) {
		try {
            List<String> lines = Files.readAllLines(Paths.get("lenBody.txt"));
            var histo = lines.stream()
                    .mapToDouble(
                        line -> Math.floor(Double.parseDouble(line)))
                    .boxed()
                    .collect(Collectors.groupingBy(d -> d));

      	  for(double i=0; i<31; i++)
              System.out.println(i +"\t" + "*".repeat((histo.get(i) == null)?0:histo.get(i).size()));

            IntStream.range(0,31).boxed().forEach(i->
                System.out.println(i +"\t" + "*".repeat((histo.get((double)i) == null)?0:histo.get((double)i).size()))
            );
	    } catch  (Exception e) {
	      System.out.println ("Mismatch exception:" + e );
	    }
	}
} 
