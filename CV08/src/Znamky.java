import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Znamky {
    List<Riadok> riadky;

    public Znamky(String nazovSuboru) {
        // toto doprogramujte
        try {
            riadky = Files.lines(Path.of(nazovSuboru)).map(v -> {
                String[] stlpce = v.split(",");
                return new Riadok(stlpce[0], stlpce[1], Integer.parseInt(stlpce[2]));
            }).collect(Collectors.toList());

            System.out.println(riadky);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Map<String, Double> priemerZnamokPreTriedy() {
        return riadky.stream().collect(Collectors.groupingBy(
                Riadok::getTrieda,
                Collectors.averagingInt(Riadok::getZnamka)
        ));
    }

    public Map<String, Map<Integer, Long>> pocetnostZnamokPreTriedy() {
        return null; // toto doprogramujte
    }

    public Map<String, Integer> modusZnamokPreTriedy() {
        return null; // toto doprogramujte
    }

    public Map<String, Integer> medianZnamokPreTriedy() {
        return null; // toto doprogramujte
    }

    public Map<String, Set<String>> nadpriemerniZiaciVTriedach() {
        return null; // toto doprogramujte
    }

    public static void main(String[] args) {

        List<Integer> ints = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9); // "0123456789"

        System.out.println(ints.stream().reduce(
                "",
                (acc, v) -> acc + v,
                (s1, s2) -> s1 + s2));


        System.out.println(ints.stream().collect(
                Collector.of(
                        StringBuilder::new, // supplier
                        StringBuilder::append, // accumulator
                        StringBuilder::append, //combiner
                        Object::toString // finisher
                )
        ));

        Znamky z = new Znamky("data.csv");
        System.out.println(z.priemerZnamokPreTriedy()); // {A=3.0714285714285716, B=3.25, C=2.9782608695652173, D=3.0625}
        System.out.println(z.modusZnamokPreTriedy()); // {A=5, B=3, C=3, D=2}
        System.out.println(z.pocetnostZnamokPreTriedy()); // {A={1=10, 2=8, 3=4, 4=9, 5=11}, B={1=8, 2=9, 3=18, 4=17, 5=12}, C={1=8, 2=10, 3=13, 4=5, 5=10}, D={1=6, 2=12, 3=11, 4=11, 5=8}}
        System.out.println(z.medianZnamokPreTriedy()); // {A=2, B=5, C=2, D=3}
        System.out.println(z.nadpriemerniZiaciVTriedach()); // {A=[garrulous-zephyr, lively-smoke, juicy-pin, clumsy-behavior, private-bird, healthy-hammer, familiar-pen, breakable-juice, draconian-lace, wasteful-sleep, gullible-breath, smiling-weight, puny-nerve, efficient-time, well-to-do-elbow, obeisant-tin, auspicious-uncle, abundant-downtown, freezing-lizards, snobbish-wheel], B=[diligent-trouble, shivering-ear, industrious-heart, green-grip, languid-advertisement, useless-drop, grateful-chickens, annoying-sea, gentle-existence, precious-heart, acceptable-desk, hysterical-magic, jumpy-property, broken-hen, efficacious-ring, stimulating-birds, regular-fiction, divergent-sidewalk, tricky-oil, diligent-prose, omniscient-day, strong-skin, disgusted-believe, straight-owner, public-skate, imaginary-metal, zealous-name, lean-copper, amusing-line], C=[steep-waste, languid-stem, young-crow, hateful-station, short-wheel, lucky-quiver, crooked-adjustment, hilarious-line, screeching-death, utter-vase, skinny-doll, fat-cellar, complete-partner, weak-stocking, sad-detail, guarded-giants, freezing-calendar, mindless-spy, ill-informed-cork, uttermost-drain, trashy-rat, true-sheet, interesting-glue, numerous-discussion, faithful-sheet, mushy-iron, pumped-animal, spotted-group], D=[victorious-argument, physical-plastic, conscious-word, old-brush, bawdy-room, narrow-fifth, hoc-basketball, safe-design, nauseating-wing, aquatic-kettle, nostalgic-egg, ossified-spring, labored-step, cheap-value, ragged-maid, happy-walk, learned-truck, determined-shake, mute-train]}
    }
}

class Riadok {
    private final String meno;
    private final String trieda;
    private final Integer znamka;

    public Riadok(String meno, String trieda, Integer znamka) {
        this.meno = meno;
        this.trieda = trieda;
        this.znamka = znamka;
    }

    @Override
    public String toString() {
        return "Riadok{" +
                "meno='" + meno + '\'' +
                ", trieda='" + trieda + '\'' +
                ", znamka=" + znamka +
                '}';
    }

    public String getMeno() {
        return meno;
    }

    public String getTrieda() {
        return trieda;
    }

    public Integer getZnamka() {
        return znamka;
    }
}
