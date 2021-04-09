import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ZnamkyAutorske {
    List<HodnotenieAutorske> riadky;

    public ZnamkyAutorske(String nazovSuboru) throws IOException {
        riadky = Files.lines(Path.of(nazovSuboru)).map(x -> {
            String[] vs = x.split(",");
            return new HodnotenieAutorske(vs[0], vs[1], Integer.parseInt(vs[2]));
        }).collect(Collectors.toList());
    }

    public Map<String, Double> priemerZnamokPreTriedy() {
        return riadky.stream().collect(Collectors.groupingBy(
                HodnotenieAutorske::getTrieda,
                Collectors.averagingInt(HodnotenieAutorske::getZnamka)
        ));
    }

    public Map<String, Map<Integer, Long>> pocetnostZnamokPreTriedy() {
        return riadky.stream().collect(Collectors.groupingBy(
                HodnotenieAutorske::getTrieda,
                Collectors.groupingBy(
                        HodnotenieAutorske::getZnamka,
                        Collectors.counting()
                )
        ));
    }

    public Map<String, Integer> modusZnamokPreTriedy() {
        return riadky.stream().collect(Collectors.groupingBy(
                HodnotenieAutorske::getTrieda,
                Collector.of(
                        (Supplier<ArrayList<Integer>>) ArrayList::new,
                        (res, x) -> res.add(x.getZnamka()),
                        (l1, l2) -> {
                            l1.addAll(l2);
                            return l1;
                        },
                        l -> l.stream().collect(Collectors.groupingBy(
                                x -> x,
                                Collectors.counting()
                        )).entrySet().stream()
                                .max((x, y) -> (int) (x.getValue() - y.getValue())).get().getKey()
                        )
        ));
    }

    public Map<String, Integer> medianZnamokPreTriedy() {
        return riadky.stream().collect(Collectors.groupingBy(
                HodnotenieAutorske::getTrieda,
                Collector.of(
                        (Supplier<ArrayList<Integer>>) ArrayList::new,
                        (res, x) -> res.add(x.getZnamka()),
                        (l1, l2) -> {
                            l1.addAll(l2);
                            return l1;
                        },
                        l -> {
                            l.sort(Integer::compareTo);
                            if (l.size() % 2 == 0) {
                                return (l.get(l.size() / 2) +  l.get(l.size() / 2 + 1)) / 2;
                            } else {
                                return l.get(l.size() / 2 + 1);
                            }
                        }
                )
        ));
    }

    public Map<String, Set<String>> nadpriemerniZiaciVTriedach() {
        Map<String, Double> priemery = priemerZnamokPreTriedy();
        return riadky.stream().filter(x -> x.getZnamka() < priemery.get(x.getTrieda())).collect(Collectors.groupingBy(
                HodnotenieAutorske::getTrieda,
                Collectors.toSet()
        )).entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                x -> x.getValue().stream().map(HodnotenieAutorske::getMeno).collect(Collectors.toSet())
        ));
    }

    public static void main(String[] args) {
        try {
            ZnamkyAutorske z = new ZnamkyAutorske("data.csv");
            System.out.println(z.priemerZnamokPreTriedy());
            System.out.println(z.modusZnamokPreTriedy());
            System.out.println(z.pocetnostZnamokPreTriedy());
            System.out.println(z.medianZnamokPreTriedy());
            System.out.println(z.nadpriemerniZiaciVTriedach());

        } catch (IOException e) {
            System.err.println("aucc");
        }
    }
}
