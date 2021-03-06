import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestBasics {
    private static LISTTestScoring scoring = null;
    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }

    @Test
    public void testdelitele23456789() {
        IntStream iis1 = Stream
                .iterate(0, i->i+4)
                .limit(10000)
                .mapToInt(e -> (int)e);
        IntStream iis2 = IntStream.range(0,10000);
        IntStream iis3 = IntStream.range(0,1000).map(e -> e+e);
        IntStream iis4 = IntStream.range(0,1000).map(e -> 5*e-e);
        IntStream iis5 = IntStream.range(0,1000).map(e -> e*e-e);
        IntStream iis6 = IntStream.range(0,1000).map(e -> 2*e*e-e);
        List<IntStream> testStreams = List.of(iis1,iis2,iis3,iis4,iis5,iis6);

        for(IntStream is : testStreams) {
            List<Integer> ls = is.boxed().collect(Collectors.toList());
            IntStream is1 = ls.stream().mapToInt(i -> (int) i);
            IntStream is2 = ls.stream().mapToInt(i -> (int) i);
            assertArrayEquals("delitele23456789 ",
                    mydelitele23456789(is1).boxed().collect(Collectors.toList()).toArray(new Integer[0]),
                    Basics.delitele23456789(is2).boxed().collect(Collectors.toList()).toArray(new Integer[0]));
        }
        scoring.updateScore("lang:common_list_test_scoring_name", 15);
    }

    @Test
    public void testdokonale() {
        IntStream iis1 = Stream
                .iterate(0, i->i+4)
                .limit(10000)
                .mapToInt(e -> (int)e);
        IntStream iis2 = IntStream.range(0,1000);
        IntStream iis3 = IntStream.range(0,100).map(e -> e+e);
        IntStream iis4 = IntStream.range(0,100).map(e -> 5*e-e);
        IntStream iis5 = IntStream.range(0,100).map(e -> e*e-e);
        IntStream iis6 = IntStream.range(0,100).map(e -> 2*e*e-e);
        List<IntStream> testStreams = List.of(iis1,iis2,iis3,iis4,iis5,iis6);


        for(IntStream is : testStreams) {
            List<Integer> ls = is.boxed().collect(Collectors.toList());
            IntStream is1 = ls.stream().mapToInt(i -> (int) i);
            IntStream is2 = ls.stream().mapToInt(i -> (int) i);
            assertArrayEquals("dokonale ",
                    mydokonale(is1).boxed().collect(Collectors.toList()).toArray(new Integer[0]),
                    Basics.dokonale(is2).boxed().collect(Collectors.toList()).toArray(new Integer[0]));
        }
        scoring.updateScore("lang:common_list_test_scoring_name", 20);
    }

    @Test
    public void testprepona() {
        IntStream iis1 = Stream
                .iterate(0, i->i+4)
                .limit(1000)
                .mapToInt(e -> (int)e);
        IntStream iis2 = IntStream.range(0,1000);
        IntStream iis3 = IntStream.range(0,1000).map(e -> e+e);
        IntStream iis4 = IntStream.range(0,1000).map(e -> 5*e-e);
        IntStream iis5 = IntStream.range(0,100).map(e -> e*e-e);
        IntStream iis6 = IntStream.range(0,100).map(e -> 2*e*e-e);
        List<IntStream> testStreams = List.of(iis1,iis2,iis3,iis4,iis5,iis6);

        for(IntStream is : testStreams) {
            List<Integer> ls = is.boxed().collect(Collectors.toList());
            IntStream is1 = ls.stream().mapToInt(i -> (int) i);
            IntStream is2 = ls.stream().mapToInt(i -> (int) i);
            assertArrayEquals("prepona ",
                    myprepona(is1).boxed().collect(Collectors.toList()).toArray(new Integer[0]),
                    Basics.prepona(is2).boxed().collect(Collectors.toList()).toArray(new Integer[0]));
        }
        scoring.updateScore("lang:common_list_test_scoring_name", 20);
    }

    @Test
    public void testdobreuzatvorkovane() {
        Stream<String> testStream =
                Stream.of("((()))()", "())(()", "()(()", "()())", "",
                        "(", "))", "()()()()()", ")))))))", ")()()()()()(", "((()))");
        List<String> ls = testStream.collect(Collectors.toList());
        Stream<String> is1 = ls.stream();
        Stream<String> is2 = ls.stream();

        assertArrayEquals("dobreuzatvorkovane ",
                mydobreUzatvorkovane(is1).collect(Collectors.toList()).toArray(new String[0]),
                Basics.dobreUzatvorkovane(is2).collect(Collectors.toList()).toArray(new String[0]));
        scoring.updateScore("lang:common_list_test_scoring_name", 20);
    }

    @Test
    public void testabecedaStream() {
        {
            List<String> ls = Arrays.asList(spoluziaci).stream().map(s -> s.toLowerCase()).collect(Collectors.toList());
            //List<String> ls = Arrays.asList(spoluziaciCelejSkoly);

            List<List<String>> res1 = myabecedaStream(ls)
                                    .map(s -> s.collect(Collectors.toList()))
                                    .collect(Collectors.toList());
            List<List<String>> res2 = Basics.abecedaStream(ls)
                                    .map(s -> s.collect(Collectors.toList()))
                                    .collect(Collectors.toList());
            res1.forEach(l -> l.sort(String::compareTo));
            res2.forEach(l -> l.sort(String::compareTo));

            assertEquals("abecedaStream test 1", res1, res2);
        }
        {
//            List<String> ls = Arrays.asList(spoluziaci);
            List<String> ls = Arrays.asList(spoluziaciCelejSkoly).stream().map(s -> s.toLowerCase()).collect(Collectors.toList());

            List<List<String>> res1 = myabecedaStream(ls)
                    .map(s -> s.collect(Collectors.toList()))
                    .collect(Collectors.toList());
            List<List<String>> res2 = Basics.abecedaStream(ls)
                    .map(s -> s.collect(Collectors.toList()))
                    .collect(Collectors.toList());
            res1.forEach(l -> l.sort(String::compareTo));
            res2.forEach(l -> l.sort(String::compareTo));
            System.out.println("res2 = " + res2);

            assertEquals("abecedaStream test 2", res1, res2);
        }
        scoring.updateScore("lang:common_list_test_scoring_name", 15);
    }


    @Test
    public void testabecedaMap() {
        {
            List<String> ls = Arrays.asList(spoluziaci).stream().map(s -> s.toLowerCase()).collect(Collectors.toList());
            //List<String> ls = Arrays.asList(spoluziaciCelejSkoly);

            Map<String, List<String>> res1lists = new TreeMap<>();
            Map<String, List<String>> res2lists = new TreeMap<>();

            myabecedaMap(ls).forEach((k, v) -> res1lists.put(k, v.sorted().collect(Collectors.toList())));
            Basics.abecedaMap(ls).forEach((k, v) -> res2lists.put(k, v.sorted().collect(Collectors.toList())));


            for(String key : res2lists.keySet()) {
                assertEquals("abecedaMap test 1: vysledok['"+key+"'] ma zly obsah:",
                        res1lists.get(key), res2lists.get(key));
            }
        }
        {
//            List<String> ls = Arrays.asList(spoluziaci);
            List<String> ls = Arrays.asList(spoluziaciCelejSkoly).stream().map(s -> s.toLowerCase()).collect(Collectors.toList());

            Map<String, List<String>> res1lists = new TreeMap<>();
            Map<String, List<String>> res2lists = new TreeMap<>();

            myabecedaMap(ls).forEach((k, v) -> res1lists.put(k, v.sorted().collect(Collectors.toList())));
            Basics.abecedaMap(ls).forEach((k, v) -> res2lists.put(k, v.sorted().collect(Collectors.toList())));


            for(String key : res2lists.keySet()) {
                assertEquals("abecedaMap test 2: vysledok['"+key+"'] ma zly obsah:",
                        res1lists.get(key), res2lists.get(key));
            }
        }
        scoring.updateScore("lang:common_list_test_scoring_name", 20);
    }



    //----- Juraj
    static Collector<Object, ?, List<Object>> toList = Collectors.toList();

    public static IntStream mydelitele23456789(IntStream input){
        return input.filter(n -> IntStream.range(2, 10).allMatch(d -> n%d == 0));
    }

    public static IntStream mydokonale(IntStream input) {
        IntPredicate dokonale = n -> IntStream.range(1, n).filter(d -> n%d == 0).sum() == n;
        return input.filter(dokonale);
    }

    public static IntStream myprepona(IntStream input) {
        IntPredicate jeStvorec = n -> Math.sqrt(n) == (int)Math.sqrt(n);
        IntPredicate prepona = c -> IntStream.range(1, c).anyMatch(a -> jeStvorec.test(c*c - a*a));
        return input.filter(prepona);
    }

    public static Stream<String> mydobreUzatvorkovane(Stream<String> input) {
        Predicate<String> jeOK = str -> str.chars().boxed().reduce(0,
                (count, ch) -> {
                    if(ch == '(') return count + 1;
                    if(count <= 0) return - str.length();
                    else return count - 1;
                }
        ) == 0;
        return input.filter(jeOK);
    }

    public static Stream<Stream<String>> myabecedaStream(List<String> words){
        Stream<String> abeceda = IntStream.range('a', 'z'+1).mapToObj(s -> ""+(char)s);
        return abeceda.map(a -> words.stream().filter(str -> str.startsWith(a)));
    }

    public static Map<String, Stream<String>> myabecedaMap(List<String> words){
        Stream<String> abeceda = IntStream.range('a', 'z'+1).mapToObj(s -> ""+(char)s);
        return abeceda.collect(Collectors.toMap(k -> k, v -> words.stream().filter(str -> str.startsWith(v))));
    }

    public static final String[] spoluziaci = {
            "Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine","Antoine",
            "Lubomir", "Jana","Ivan","Pedro","Zofia","Filomena","Ivan","Maria","Jano","Zuzana","Ivan","Dagmar","Zuzana","Florian","Filomena","Filomena",
            "Petra","Luba","Luba","Ivan","Zuzana","Sona","Luba","Florian","Maria","Filomena","Florian","Kamil","Lubomir","Rexana","Karol",
            "Peter","Zuzana","Lubomir","Petra","Dagmar","Florian","Jana","Ivan","Filomena","Filomena","Zofia","Petra","Kamil","Filomena","Zuzana",
            "Sona","Petra","Dagmar","Filomena","Zofia","Jano","Maria","Zuzana","Zofia","Pista","Maria","Zofia","Pedro","Filomena","Pedro",
            "Sona","Florian","Filomena","Luba","Zuzana","Maria","Ivan","Pedro","Maria","Florian","Sona","Petra","Pista","Lubomir","Jano",
            "Zuzana","Maria","Jano","Filomena","Sona","Rexana","Dagmar","Peter","Florian","Jano","Jano","Petra","Zuzana","Florian","Jano",
            "Florian","Peter","Florian","Kamil","Petra","Zofia","Pista","Sona","Florian","Jana","Petra","Kamil","Florian","Maria","Filomena",
            "Dagmar","Pista","Sona","Pista","Jana","Jano","Kamil","Ivan","Jana","Filomena","Petra","Jano","Pedro","Karol","Maria",
            "Zofia","Pedro","Petra","Peter","Kamil","Jana","Jana","Lubomir","Florian","Rexana","Kamil","Lubomir","Sona","Ivan","Petra",
            "Rexana","Filomena","Pedro","Pedro","Luba","Filomena","Pedro","Zofia","Dagmar","Kamil","Petra","Pedro","Maria","Zofia","Sona",
            "Filomena","Zofia","Dagmar","Luba","Rexana","Maria","Peter","Zuzana","Ivan","Zuzana","Jano","Lubomir","Luba","Filomena","Maria",
            "Pedro","Ivan","Zuzana","Zuzana","Pista","Karol","Kamil","Kamil","Florian","Jana","Jana","Filomena","Ivan","Zuzana","Jana",
            "Filomena","Sona","Pista","Jano","Karol","Ivan","Pedro","Florian","Zuzana","Filomena","Lubomir","Luba","Ivan","Petra","Rexana",
            "Sona","Luba","Kamil","Florian","Filomena","Dagmar","Ivan","Dagmar","Pedro","Pedro","Pista","Zofia","Zofia","Zuzana","Lubomir",
            "Ivan","Maria","Dagmar","Filomena","Pista","Filomena","Pedro","Jano","Maria","Luba","Pedro","Pedro","Florian","Florian","Maria",
            "Zuzana","Jano","Karol","Rexana","Zuzana","Jana","Pedro","Jano","Sona","Maria","Filomena","Rexana","Pedro","Pista","Kamil",
            "Sona","Pedro","Lubomir","Dagmar","Peter","Jana","Jana","Ivan","Pedro","Jano","Maria","Florian","Pedro","Rexana","Filomena",
            "Karol","Petra","Jano","Pista","Jano","Zofia","Jano","Lubomir","Filomena","Dagmar","Florian","Maria","Zofia","Rexana","Rexana",
            "Peter","Luba","Karol","Maria","Jana","Peter","Sona","Pedro","Filomena","Zuzana","Petra","Ivan","Jano","Zuzana","Pedro",
            "Pista","Jano","Pista","Pedro","Rexana","Jano","Maria","Jano","Zofia"};

    public static final String[] spoluziaciCelejSkoly = {
            "Aaron","Aarón","Abadon","Abdon","Abdón","Abel","Ábel","Abelard","Abelárd","Abelardo","Abele","Abelone","Abigael","Abigail","Abigél",
            "Abraham","Abrahám","Ábrahám","Abram","Abramo","Absalom","Absalon","Absolon","Absolón","Ada","Adalbert","Adalberta","Adalbertina","Adalberto","Adalbertýna",
            "Adalbrecht","Adalwin","Adam","Ádám","Adamo","Adán","Adda","Adeinaida","Adeineid","Adel","Adél","Adéla","Adéla","Adelaida","Adelaide",
            "Adélaide","Adelajda","Adelbert","Adéle","Adile","Adelhilda","Adélia","Adelina","Adelina","Adelinda","Adelinde","Adeline","Adeltraud","Adeltrude","Adin",
            "Adina","Adléta","Adolf","Adolfa","Adolfina","Adolfina","Adolfme","Adolfo","Adolph","Adolpha","Adolphe","Adolphina","Adolphine","Adolphus","Adorján",
            "Adria","Adriaan","Adrian","Adrián","Adriana","Adriána","Adriani","Adriano","Adriauna","Adrien","Adriena","Adrienn","Adrienna","Adrienne","Adrijan",
            "Adrijana","Aegidius","Aemilia","Aemilius","Afabella","Afanas","Afanasij","Afanasija","Afanka","Afda","Afeta","Afete","Afetha","Afette","Afiadna",
            "Afiadni","Afiana","Afina","Afra","Afranija","Africo","Agafja","Agafon","Agafonik","Agata","Agáta","Agate","Agatha","Agathe","Agathon",
            "Agatokleia","Agaton","Agatón","Agatone","Agatonike","Aggel","Ägid","Ägidius","Aglaia","Aglaja","Aglája","Agnes","Agnis","Ágnes","Agnesa",
            "Agnese","Agnessa","Agneša","Agneta","Agnete","Agnethe","Agnieszka","Ágosta","Agostino","Ágoston","Agóta","Agueda","Achil","Achiles","Achill",
            "Achille","Achilleas","Achilles","Achillij","Aidan","Aiden","Aimar","Aimée","Ajrin","Akim","Aksel","Alabhaois","Aladár","Alain","Alaios",
            "Alajos","Alan","Alán","Alana","Alanis","Alano","Alanus","Alasdair","Alastair","Alba","Alban","Albana","Albano","Albanus","Albena",
            "Albert","Alberta","Alberte","Albertin","Albertina","Albertina","Albertini","Alberto","Albertýn","Albertyna","Albertyna","Albin","Albín","Albina","Albina",
            "Albine","Albino","Albinus","Albrecht","Albyn","Alda","Alde","Aldo","Alec","Alejandra","Alejandro","Aleks","Aleksa","Aleksander","Aleksandr",
            "Aleksandra","Aleksansdar","Aleksej","Aleksi","Aleksija","Aleksina","Aleksis","Aleksy","Alen","Alena","Alena","Alesia","Alessandra","Alessandro","Alessia",
            "Alessio","Alesszia","Aleš","Aleša","Aleška","Aletea","Alithe","Alethea","Aléthea","Aletheia","Alex","Alex","Alexa","Alexander","Alexandr",
            "Alexandra","Alexandre","Alexandre","Alexandros","Alexandru","Alexej","Alexia","Alexie","Alexine","Alexis","Alexis","Alexius","Alfons","Alfonso","Alfonz",
            "Alfred","Alfréd","Alfreda","Alfréda","Alfredas","Alfredo","Alfredus","Alfrida","Aliadné","Alica","Alice","Alicia","Alicja","Alida","Aliénor",
            "Alina","Aline","Alisa","Alise","Alisha","Alisija","Alison","Alisson","Alithea","Alithia","Alitigone","Aliz","Alkaterini","Allan","Allee",
            "Alleen","Alleen","Allen","Allena","Allene","Alleta","Allette","Allison","Alma","Almaricus","Almi","Almida","Almidija","Almlina","Alna",
            "Alodie","Aloin","Alois","Aloisa","Aloisia","Aloisie","Aloisio","Aloisius","Alojz","Alojza","Alojzia","Alojzie","Alojzja","Alojzy","Alonso",
            "Aloys","Aloysia","Aloysius","Alphonse","Alphonso","Altéinis","Altemija","Altemis","Altemisa","Altémise","Altemisz","Altulini","Altylini","Aluin","Alva",
            "Alvar","Alvaro","Alvian","Alvián","Alvin","Alvina","Alvino","Alwin","Alwina","Alwyn","Alysha","Alžbita","Alžbita","Amabel","Amadé",
            "Amadea","Amadea","Amadej","Amadeo","Amadeu","Amadeus","Amadeusz","Amadia","Amado","Amalberga","Amaldeia","Amalia","Amália","Amálie","Amálie",
            "Angélique","Angell","Angelo","Angelos","Angelus","Anghel","Angus","Angyalos","Aniela","Aniello","Anika","Anikó","Aniol","Anisja","Anissa",
            "Anita","Anja","Ann","Anna","Annabel","Annabela","Annabella","Annabelle","Anne","Annetta","Annette","Anni","Annie","Annika","Annouk",
            "Anny","Anouk","Ansel","Anselm","Anselma","Anselme","Anselmo","Anshelm","Antal","Ante","Antea","Anteia","Antheia","Anthony","Anthula",
            "Antía","Antigona","Antim","Antin","Antoan","Antoine","Antoinette","Antokleia","Anton","Antonetta","Antoni","Antonia","Antonia","Antonida","Antonie",
            "Antonij","Antonija","Antonije","Antonin","Antonín","Antonína","Antonína","Antonini","Antonino","Antonio","Antonios","Antonis","Antonius","Antony","Antula",
            "Antun","Anuše","Anzelm","Anzelma","Apolena","Apoliena","Apolka","Apolline","Apollo","Apollonia","Apollónia","Apollonie","Apollonija","Apolonia","Apolónia",
            "Apolonie","Arabela","ArabeUe","Araldo","Aram","Aretta","Archip","Ariadni","Arian","Ariane","Arianna","Arianne","Ariel","Ariela","Ariella",
            "Arielle","Arijadna","Arika","Aristid","Aristide","Aristides","Arisztid","Arkád","Arkadij","Arkadius","Arkady","Arman","Armand","Armando","Armin",
            "Ármin","Arminio","Arminius","Armyn","Arnaldo","Arnau","Arnaud","Arnault","Arne","Arno","Arnó","Arnold","Arnolda","Arnolde","Arnoldia",
            "Arnoldo","Arnošt","Arnošta","Arnoš?ka","Arnoult","Arnout","Aron","Áron","Aronne","Arpad","Arpád","Árpád","Arsene","Arsen","Arsenij",
            "Arsenije","Arsenio","Arsenios","Arsenius","Arseniusz","Artem","Arteme","Artemida","Artemij","Arthur","Arthurus","Artim","Ar?om","Artturi","Artur",
            "Artúr","Arturo","Artuš","Arzen","Arzén","Ashley","Ashley","Askold","Aspasia","Aspasie","Aster","Astra","Astrid","Astrida","Astryda",
            "Asztrid","Atanas","Atanasia","Atanasie","Atanasij","Atanasija","Atanasije","Atanasio","Atanáš","Atanáška","Atanáz","Atanázia","Atanázie","Atanazja","Atanazy",
            "Atena","Atene","Aténé","Athana","Athanase","Athanasia","Athanasie","Athanasios","Athanasius","Athéna","Athéna","Athene","Athina","Atila","Atilla",
            "Atina","Attila","Attilo","Aubin","Aubine","Audra","Audrey","August","Augusta","Augusta","Auguste","Auguste","Augustin","Augustín","Augustina",
            "Augustina","Augustina","Augustine","Augustinus","Augusto","Augustus","Augustyn","Augustýn","Augustyna","Augustýna","Auguszta","Auni","Aura","Aurea","Aurel",
            "Aurél","Aurela","Aureli","Aurelia","Aurélia","Aurelian","Aurelián","Aurélián","Aurelie","Aurélie","Aurelij","Aurelija","Aurelio","Aurelius","Aurora",
            "Aurora","Aurooe","Austin","Avdon","Avelina","Aveline","Avgust","Avgusta","Avgustin","Avraam","Avraamij","Avram","Avrelij","Avrelija","Avrora",
            "Axel","Aya","Ayleen","Aylin","Babbette","Babeta","Babett","Babette","Balázs","Baldassare","Bálint","Baltasar","Baltazar","Baltazár","Balthasar",
            "Balthazar","Bára","Barabás","Barabáš","Barak","Barbara","Barbara","Barbé","Barber","Barbora","Barbra","Barbro","Barna","Barnaba","Barnabas",
            "Barnabás","Barnabáš","Barnabé","Barnaby","Barnard","Barney","Barrabas","Bart","Barthélemy","Bartholomäus","Bartholomé","Bartholomeus","Bartholomew","Bartolomej","Bartolomij",
            "Bartolomiej","Bartolomij","Basil","Basile","Basiléo","Basileus","Basilij","Basilio","Basilius","Bastian","Bastien","Bazil","Bazyli","Bea","Beat",
            "Beata","Beáta","Beate","Beato","Beatrica","Beatrice","Béatrice","Beatrisa","Beatrix","Beatriz","Beatrycza","Beatus","Bedros","Bedoich","Bedoicha",
            "Bedoiška","Beitris","Bela","Béla","Bila","Belinda","Bell","Bella","Belle","Biloslava","Biluše","Ben","Beoadik","Benedek","Benedetta",
            "Benedetto","Benedict","Benedicta","Benedicte","Benedicto","Benedikt","Benedikta","Benedikte","Benedita","Benedykt","Benedykta","Benet","Benett","Bengta","Béni",
            "Beniamin","Beniamino","Benigna","Benigni","Benita","Benito","Benjamin","Benjamín","Benjámin","Benkö","Bennet","Bennie","Benno","Bennó","Bennone",
            "Benny","Beno","Benö","Beoo","Benoit","Benoite","Benon","Berardo","Berenice","Bérénice","Berenika","Berenike","Bereniké","Bergit","Berit",
            "Berná","Bernaard","Bernadeta","Bernadette","Bernard","Bernarda","Bernardeta","Bernardetta","Bernardette","Bernardin","Bernardina","Bernardine","Bernardo","Bernardus","Bernardýna",
            "Bernat","Bernát","Bernhard","Bernharda","Bernhardina","Bernhardine","Bert","Berta","Bertalan","Berte","Bertel","Bertha","Berthe","Berthold","Bertholda",
            "Bertholdine","Bertil","Bertina","Bertold","Bertolda","Bertoldo","Bertram","Bertrán","Bertrand","Bertrando","Beryl","Beryll","Bess","Bessie","Bessy",
            "Bita","Betty","Bianca","Bianca","Bianka","Bibi","Bibiana","Bibiána","Bibijana","Bijanka","Birgid","Birgit","Birgita","Birgitt","Birgitta",
            "Birgitte","Bistar","Bivoj","Bjela","Blago","Blagomila","Blagomir","Blagomira","Blagoslav","Blahomil","Blahomila","Blahomír","Blahomíra","Blahoslav","Blahoslava",
            "Blahuše","Blaise","Blanch","Blanche","Blanka","Blase","Blasios","Blasius","Blažej","Blažeja","Blažejka","Blažen","Blažena","Bogdan","Bogdana",
            "Bogislav","Bogislava","Bogohval","Bogoljub","Bogomil","Bogomila","Bogomir","Bogomira","Bogoslav","Bogoslava","Bogoš","Boguchval","Boguchwal","Bogumil","Bogumila",
            "Bogumila","Boguslav","Boguslava","Boguslávo","Boguslaw","Boguslawa","Bogusz","Bohdan","Bohdana","Bohdanka","Bohomil","Bohuchval","Bohumir","Bohumil","Bohumila",
            "Bohumír","Bohumíra","Bohun","Bohuna","Bohunka","Bohuslav","Bohuslava","Bohuš","Bohuše","Bochmir","Bojan","Bojana","Bojána","Bojeslav","Bojislav",
            "Bojislava","Bójka","Bojmír","Boldizsár","Bolek","Bolemír","Boleslav","Boleslava","Boleslaw","Boleslawa","Boljemir","Boljeslav","Bona","Bonifac","Bonifác",
            "Boniface","Bonifacij","Bonifacio","Bonifacius","Bonifacy","Bonifatij","Bonifatius","Bonifaz","Borbála","Borek","Borimir","Borimír","Boris","Borisav","Borislav",
            "Borislav","Borislava","Borislava","Borisz","Borjan","Borko","Borna","Boro","Borys","Boryslaw","Boryslawa","Borzyslaw","Booek","Booislav","Booivoj",
            "Bozsidár","Božana","Božena","Božena","Božetech","Božetich","Božeticha","Božeticha","Božidar","Božidara","Božika","Božin","Božina","Božislav","Božislava",
            "Božko","Brandon","Branija","Branimer","Branimir","Branimír","Branimira","Branimíra","Branisav","Branislav","Branislava","Branka","Branko","Bratislav","Bratislava",
            "Brenda","Brendan","Bret","Bretislav","Brett","Brian","Briano","Bridget","Brighid","Brigida","Brigit","Brigita","Brigitta","Brigitte","Brit",
            "Brita","Britanij","Britt","Britta","Brjaeislav","Brjaeislava","Brooa","Bronik","Bronia","Bronislav","Bronislava","Bronislaw","Bronislawa","Bronislawa","Bronja",
            "Bruce","Bruna","Brungilda","Brunhild","Brunhilda","Brunhilde","Bruno","Brúnó","Brunon","Bryan","Brygida","Boecislava","Boetislav","Boetislava","Boetislava",
            "Budimil","Budimír","Budislav","Budivoj","Budzimir","Budzislaw","Burga","Burghild","Burghilde","Burglind","Burglinde","Burgunda","Burgunde","Bystrík","Cacilie",
            "Caecilia","Caesar","Caetano","Caitlin","Cajetanus","Calipso","Calum","Calypso","Cameron","Camila","Camill","Camilla","Camille","Camillo","Camillus",
            "Camilo","CamiUe","Canute","Cara","Carina","Carine","Carl","Carla","Carleen","Carli","Carlina","Carlo","Carlos","Carlota","Carlotta",
            "Carmel","Carmela","Carmen","Carol","Carol","Carola","Carole","Carolin","Carolina","Caroline","Carolyn","Casandra","Cäsar","Casey","Casey",
            "Casiano","Casimir","Casimiro","Caspar","Cassandra","Cassandre","Cassian","Cassie","Cassio","Cassius","Castor","Cástor","Castore","Cástulo","Castulus",
            "Catalina","Catarina","Caterina","Catharina","Catherine","Cathia","Catia","Catriona","Cecil","Cecile","Cécile","Cecilia","Cecília","Cecilián","Cecilie",
            "Cecílie","Cecilija","Cecilio","Cécille","Cecily","Cecylia","Cecyliusz","Cedric","Cédric","Cedrik","Célanie","Celany","Celestin","Celestín","Celestina",
            "Celestína","Celestine","Celestino","Celestyn","Celestýn","Celestyna","Celestýna","Celesztin","Celesztina","Celia","Célia","Celie","Celina","Celini","Céline",
            "Cenzo","Cesar","Cesare","Césare","Césareo","Ceslava","Cestimira","Cezar","Cézar","Cézár","Cezariusz","Cezary","Cilla","Cinda","Cindy",
            "Cinisula","Cintia","Cinystina","Ciprián","Cipriano","Cirila","Cirilija","Cirilla","Cirillo","Cirilo","Claire","Clara","Clarika","Clarissa","Clarisse",
            "Clark","Claoe","Claud","Claude","Claude","Claudia","Claudie","Claudina","Claudio","Claudius","Clemens","Clement","Clément","Clemente","Clementia",
            "Clementina","Clementine","Cleopatra","Clotario","Clothilde","Clotilda","Clotilde","Coleman","Cölestin","Cólestine","Cölestinus","Coleta","Coletta","Colette","Colin",
            "Cólina","Colman","Coloman","Colomba","Colombano","Colombe","Colombina","Colombino","Colombo","Colum","Columba","Columban","Columbanus","Columbia","Columbina",
            "Columbine","Cona","Conner","Connor","Conrad","Conrado","Conroy","Constanca","Constance","Constancia","Constans","Constansa","Constant","Constanta","Constantin",
            "Constantine","Constantino","Constantinus","Constanza","Constanze","Constanzo","Consuela","Cora","Cordelia","Cordélie","Cordula","Córdula","Cordule","Corina","Corinna",
            "Corinne","Cormac","Corneille","Cornelia","Cornélie","Cornelio","Cornelis","Cornelius","Corona","Corrado","Cosme","Cosmo","Craig","Crépin","Crescentia",
            "Crispin","Crispín","Crispino","Crispinus","Crispus","Cristalina","Cristian","Cristián","Cristiano","Cristina","Cristinha","Cristino","Cristo","Cristobal","Cristofor",
            "Cristoforus","Ctibor","Ctibora","Ctimír","Ctirad","Ctirada","Ctislav","Ctislava","Cunegond","Cunegonda","Cunégonde","Cunegunda","Cunegundes","Curd","Curt",
            "Curzio","Cveta","Cvetan","Cvetana","Cvetimira","Cvetislav","Cvetislava","Cvetomir","Cvetomira","Cvetoslava","Cynthia","Cyntia","Cyntie","Cyprian","Cyprián",
            "Cyprianus","Cyprien","Cyril","Cyrila","Cyrilka","Cyrill","Cyrilla","Cyrille","Cyrillus","Cyrus","Cyryla","Czeslaw","Czeslawa","Easlav","Easlava",
            "Eedomir","Eenik","Eeoka","Eeslav","Eeslava","Eestimir","Eestislav","Eestmír","Eestmíra","Eistoslav","Eistoslava","Daan","Dafne","Dafné","Dafni",
            "Dag","Dagmar","Dagmara","Dagobert","Dagoberto","Dagomaro","Dahl","Dahl","Dahlia","Daisy","Dajana","Dajena","Dalebor","Dalemil","Dalemir",
            "Dalia","Dália","Dalibor","Dalibora","Dalida","Dalidia","Dalie","Dalija","Dalila","Dálila","Dalimil","Dalimila","Dalimír","Dalimíra","Dalma",
            "Dalmacija","Dalmata","Dalmatinka","Dalmazia","Dalmira","Dalmíra","Damaris","Dámaris","Damia","Damiaan","Damian","Damián","Damiana","Damiána","Damiane",
            "Damiano","Damianos","Damianus","Damien","Damijan","Damijana","Damir","Damjan","Damján","Damjana","Damon","Dan","Dana","Danae","Danaé",
            "Danai","Danail","Danaila","Danel","Daneš","Danica","Danice","Daniel","Dániel","Daniela","Daniele","Daniella","Danielle","Daniil","Daniila",
            "Danijel","Danijela","Danika","Danila","Danila","Danilo","Danitza","Danka","Danko","Dankrad","Dankret","Danny","Dano","Danuše","Danuta",
            "Danylo","Daphne","Daphné","Dara","Darcy","Darek","Darel","Daren","Daria","Dária","Darie","Darie","Darien","Darij","Darija",
            "Darije","Darina","Darinka","Dario","Darío","Darius","Dárius","Dariusz","Darja","Dárjúš","Darko","Darleen","Darlena","Darlene","Daro",
            "Darrel","Darrell","Darren","Darrene","Darryl","Daruše","Darya","Daryl","Dáša","Dáúd","Dave","David","Dávid","Davida","Davide",
            "Davina","Davinia","Davita","Davor","Davud","Davy","Dawid","Dean","Deana","Debora","Debóra","Deborah","Déborah","Deidre","Deinela",
            "Deinse","Dejan","Dejana","Delfína","Delfíne","Delia","Délia","Delie","Delija","Dilila","Delphina","Delphine","Demeter","Demeter","Demetria",
            "Demetrio","Demetrios","Demetrius","Démétrius","Demi","Demjan","Dénes","Denica","Deniel","Denis","Denisa","Denisija","Dennis","Denny","Denys",
            "Deodoro","Dipold","Derek","Derica","Derika","Derrick","Desanka","Desideria","Desiderij","Desiderije","Desiderio","Desiderius","Désiré","Désirée","Desmond",
            "Despina","Ditmar","Detre","Detrich","Ditoich","Deva","Devana","Divana","Dezider","Dezidera","Deziderija","Dezsö","Dezyderia","Dezyderius","Dezydery",
            "Dia","Diana","Diana","Diandra","Diani","Dianne","Dick","Didda","Diderik","Didier","Didrik","Diede","Dieder","Diederik","Diego",
            "Dietbald","Dieter","Diethild","Diethilde","Dietlind","Dietlinde","Dietmar","Dietrich","Dijana","Dimitar","Dimitr","Dimitra","Dimitri","Dimitrij","Dimitrije",
            "Dimitrina","Dimitrios","Dimitris","Dimitrula","Dina","Dinah","Dinka","Dino","Dionisia","Dionisio","Dioniza","Dionízia","Dionizij","Dionizija","Dionizije",
            "Dionizja","Dionizy","Dionys","Dionysia","Dionysie","Dionysija","Dionysius","Dionýsos","Dionýz","Dionýzie","Dionýzka","Dionyzy","Disma","Dismas","Dita",
            "Ditmar","Ditmár","Ditta","Ditte","Diviš","Dlugomil","Dlugomir","Dluhoš","Dmitrij","Dmitro","DMška","Dobra","Dobrava","Dobrivoj","Dobromil",
            "Dobromila","Dobromila","Dobromir","Dobromír","Dobromira","Dobromíra","Dobrosav","Dobroslav","Dobroslava","Dobroslaw","Dobroslawa","Dobruše","Doboena","Dolores","Doloris",
            "Dolóresz","Doloróza","Domenica","Domenico","Domenika","Domingo","Dominic","Dominica","Dominica","Dominick","Dominicus","Dominik","Dominika","Dominikus","Dominique",
            "Dominique","Domniki","Domokos","Domonkos","Don","Dona","Donal","Donald","Donalda","Donaldo","Donall","Donar","Donat","Donát","Donata",
            "Donáta","Donatela","Donatella","Donato","Donatos","Donatus","Donica","Donika","Donna","Donnel","Donnell","Donovan","Dora","Dora","Dore",
            "Doreen","Dorett","Dorette","Dorian","Dorián","Dorijan","Doris","Dorisa","Dorit","Dorita","Dorith","Dorius","Dorka","Dorkas","Dorofeja",
            "Dorota","Dorotea","Doroteja","Dorothea","Dorothy","Dorotka","Dorottya","Dorrit","Doubrava","Doubravka","Draga","Dragan","Dragana","Dragica","Dragimir",
            "Drago","Dragoljub","Dragoljuba","Dragomil","Dragomila","Dragomir","Dragomira","Dragosav","Dragosava","Dragoslav","Dragosláva","Dragoslaw","Dragoš","Dragotin","Dragutin",
            "Dragutín","Draho3ub","Drahomil","Drahomila","Drahomír","Drahomíra","Drahoo","Drahoslav","Drahoslava","Drahoš","Drahotín","Drahotina","Drahuše","Drogomila","Drogomir",
            "Drogoslawa","Drogosz","Dúbrava","Dubravka","Duchoslav","Duchoslava","Dulce","Dulcia","Dulcie","Dulcinea","Dulcinela","Dumitru","Duna","Duncan","Dunja",
            "Dustin","Duszan","Dušan","Dušana","Duška","Duško","Dylan","Dymitr","Dysmas","Dyzma","Džani","Ebba","Ebrahim","Ecaterina","Eckbert",
            "Ed","Eda","Eda","Edda","Eddi","Eddie","Eddy","Edeltraud","Edeltrauda","Edeltraude","Edeltrud","Edeltruda","Edeltrude","Edeltrudis","Edgar",
            "Edgár","Edgard","Edgardo","Edgaro","Edilberto","Edit","Edita","Edith","Édith","Editha","Edmond","Edmonda","Edmondo","Edmund","Edmunda",
            "Edmundo","Edmundus","Edna","Edoardo","Edon","Édouard","Édouardine","Eduard","Eduarda","Eduarde","Eduarde","Eduardine","Eduardine","Eduardo","Eduardus",
            "Eduino","Edultride","Edvard","Edvárd","Edvarda","Edviga","Edvige","Edvin","Edvín","Edvina","Edvino","Edward","Edwarda","Edwardine","Edwige",
            "Edwin","Edwina","Edwine","Edyta","Efi","Efraim","Efrem","Efrosini","Efrosynija","Eftalia","Eftimia","Eftymia","Egbert","Egedij","Egedije",
            "Egid","Egidij","Egidion","Egidius","Egídius","Egmont","Egmund","Egon","Egona","Egonda","Egondina","Egonia","Egyed","Eileen","Eirik",
            "Ekaterina","Ekaterini","Ela","Élaine","Elea","Eleanor","Eleazar","Eléazar","Eleazaro","Electra","Elefterios","Eleftherios","Elektra","Elemír","Elen",
            "Elena","Eleni","Eleni","Eleonor","Eleonora","Eleonora","Eleonooe","Eleonooe","Eleuterios","Eleutherios","Elfreda","Elfrida","Elfrída","Elfried","Elfrieda",
            "Elfriede","Elga","Eli","Elia","Eliah","Eliana","Eliána","Eliane","Éliane","Elias","Elías","Éliás","Eliash","Eliasz","Eliáš",
            "Eliáška","Elie","Élie","Eliécer","Elieser","Eliezer","Eliézer","Eligio","Elijah","Elijáš","Elin","Elina","Elinor","Eliodoro","Eliot",
            "Elis","Elisa","Elisabet","Elisabeta","Elisabeth","Élisabeth","Elisabetha","Elisabetta","Elisavet","Elisaveta","Elise","Elise","Élise","Élisée","Elisej",
            "Eliseo","Eliseus","Elisha","Elisha","Elissa","Eliška","Eliza","Elizabeta","Elizabeth","Elizaveta","Elizej","Elizeus","Elizeusz","Elka","Elke",
            "Ella","Ellena","Elli","Ellina","Ellinor","Elliot","Ellis","Ellyn","Elma","Elmar","Elmár","Elmer","Elodia","Elodie","Elpida",
            "Elpidia","Elorid","Elsa","Else","Elsie","Elvina","Elvira","Elvíra","Elwira","Elyssa","Elza","Elžbieta","Ema","Emanuel","Emánuel",
            "Emanuela","Emanuela","Emanuele","Emanuila","Emeram","Emerentia","Emerenz","Emerenzia","Émeric","Emerico","Emericus","Emerich","Emerik","Emery","Émery",
            "Emeryk","Emiel","Emil","Emila","Emile","Émile","Emilia","Emília","Emilian","Emilián","Emiliana","Emiliána","Emiliano","Emilie","Emilie",
            "Emílie","Emilij","Emilija","Emilio","Emiliusz","Emiljan","Emily","Emma","Emmanuel","Emmanuele","Emmanuelle","Emmanuil","Emmeramus","Emmerico","Emmerich",
            "Emmerik","Emmery","Emmi","Emmy","Ena","Endre","Endže","Engelbert","Engelberto","Engracia","Enikó","Enna","Enne","Enni","Enny",
            "Enoch","Énok","Enrica","Enrico","Enrichetta","Enrika","Enrique","Enriqueta","Enriquez","Enzo","Enže","Ephraim","Erardo","Érasme","Erasmo",
            "Erasmus","Erazim","Erazm","Erazma","Erazmus","Erberto","Erhard","Erhardo","Eric","Erica","Érica","Erico","Erich","Erik","Erika",
            "Erina","Ermanno","Ermin","Ermín","Erna","Ernest","Ernesta","Ernestina","Ernestina","Ernestine","Ernesto","Ernestyna","Ernestýna","Erneszta","Ernesztina",
            "Ernö","Ernst","Ervin","Ervín","Ervina","Ervina","Ervino","Erwin","Erwina","Erwine","Eryk","Eryka","Erzsébet","Ésaie","Esdras",
            "Esfir","Esmeralda","Esméralda","Esperanza","Esra","Estanislao","Estanislau","Estatios","Esteban","Estefania","Estel","Estela","Estella","Estelle","Estephania",
            "Estephano","Ester","Estera","Estevan","Estevao","Esther","Estibalitz","Estrella","Eszmeralda","Eszter","Etel","Etela","Etelka","Ethel","Etheldreda",
            "Étienne","Ettore","Eudokie","EUe","EUen","Eufemia","Eufémia","Eufemie","Eufrosina","Eufrosyne","Eufrozina","Eufrozína","Eufrozyna","Eugen","Eugene",
            "Eugenia","Eugénia","Eugenie","Eugénie","Eugenio","Eugenius","Eugeniusz","Eulaalia","Eulalia","Eulália","Eulalie","Eulálie","Eunice","Eunie","Eunika",
            "Eunike","Euphemia","Euphémie","Euphrosine","Euphrosyne","Euridika","Eurydice","Eusébe","Eusebia","Eusébia","Eusebie","Eusebij","Eusebija","Eusebije","Eusebio",
            "Eusebius","Eusébius","Euthalia","Euvaldo","EUy","Euzebia","Euzébia","Euzebiusz","Eva","Éva","Evald","Evaldo","Evalyn","Evan","Evangelia",
            "Evangelina","Evangelina","Evangélina","Evangeline","Evangelista","Evangelos","Evarist","Évariste","Evaristo","Evaristus","Evariszt","Evdokia","Evdokie","Eve","Evelia",
            "Eveliina","Evelin","Evelina","Evelina","Eveline","Evelio","Evelyn","Evelyna","Évelyne","Evgen","Evgenija","Evita","Evlalija","Evrydiki","Evthalia",
            "Evtimia","Evžen","Evžena","Evženie","Ewa","Ewald","Ewaryst","Ewelina","Ewen","Eyjenia","Ezdráš","Ezdreáš","Ezechiel","Ezéchiel","Ezechiele",
            "Ezechiello","Ezekiel","Ezékiel","Ezequiel","Ezra","Fabia","Fabian","Fabián","Fábián","Fabiana","Fabiána","Fabiano","Fabianus","Fabie","Fabien",
            "Fabienne","Fabij","Fabijan","Fabijana","Fabio","Fabiola","Fabius","Fabrice","Fabricia","Fabrícia","Fabricie","Fabricien","Fabricio","Fabricius","Fabríciusz",
            "Fabrizia","Fabrizio","Faddej","Fadej","Fanny","Fantino","Farran","Farren","Fatima","Fátima","Fauni","Faust","Fausta","Faustin","Faustina",
            "Faustina","Faustine","Faustino","Faustinus","Fausto","Faustus","Faustyn","Faustýn","Faustyna","Faustýna","Fausztina","Fausztusz","Favij","Favst","Favstina",
            "Fay","Faye","Feba","Féba","Febe","Febronia","Fedderik","Federica","Federico","Fedor","Fedora","Fedóra","Fedosij","Felice","Felicia",
            "Felícia","Felician","Felicián","Feliciana","Felicie","Felície","Félicie","Felicija","Felicita","Felicitas","Felicity","Felicja","Felicjan","Felicjana","Feliks",
            "Feliksa","Felipa","Felipe","Felix","Félix","Felixa","Felizia","Felizitas","Fenella","Feodor","Feodora","Feodosij","Feodosija","Feofan","Feofil",
            "Feofila","Ferdenande","Ferdinand","Ferdinánd","Ferdinanda","Ferdinande","Ferdinando","Ferdynand","Ferdynanda","Ferenc","Fernand","Fernanda","Fernande","Fernando","Fidele",
            "Fidel","Fidél","Fidela","Fidelia","Fidélia","Fidelij","Fidelija","Fidelio","Fidelis","Fidelius","Fidelle","Filbert","Filberto","Filemon","Filemón",
            "Filemone","Filiberto","Filip","Filipa","Filipina","Filipina","Filipp","Filippa","Filippija","Filippo","Filippos","Filippus","Filomen","Filomén","Filomena",
            "Filoména","Filomeno","Fina","Finbar","Fine","Finella","Finette","Finn","Finna","Finne","Finni","Fiona","Fionn","Fionnuala","Fiva",
            "Fjodor","Fjokla","Flavia","Flávia","Flavian","Flavián","Flaviano","Flavie","Flavien","Flavij","Flavija","Flavio","Flavius","Flávius","Flawia",
            "Flawian","Flawiusz","Fldelie","Fleur","Floortje","Flóra","Florance","Florence","Florencia","Florencij","Florencio","Florentia","Florentij","Florentin","Florentín",
            "Florentina","Florentína","Florentine","Florentino","Florentinus","Florentius","Florentyn","Florentýn","Florentyna","Florentýna","Floria","Florian","Florián","Flórián","Floriana",
            "Floriána","Floriano","Florianus","Florijan","Florin","Floyd","Fodor","Foma","Fomaida","Forest","Forrest","Fortunat","Fortunát","Fortunato","Fortunatus",
            "Fortunio","Fotini","Franca","Frances","Francesca","Francesco","Francine","Francis","Francisca","Francisco","Franciscus","Franciska","Francisque","Franciszek","Franciszka",
            "Franco","Francois","Francoise","Franjo","Frank","Franka","Franko","Frans","František","Františka","Franz","Franzine","Franziska","Franziskus","Fred",
            "Fredderik","Freddi","Freddy","Frederic","Frédéric","Frederica","Frederick","Fredericus","Frederik","Frederika","Frederikke","Frédérique","Fredrik","Fredrika","Fredrikke",
            "Freya","Frida","Frída","Friderika","Fridolin","Fridolín","Fridolino","Fridrich","Frieda","Friederika","Friederike","Friedrich","Frigyes","Fritz","Frosyna",
            "Fryderik","Fryderyka","Fülöp","Gabi","Gabin","Gabina","Gábina","Gabinio","Gabino","Gabinus","Gábor","Gabriäl","Gabriel","Gabriela","Gabriele",
            "Gabriele","Gabriella","Gabrielle","Gabriello","Gabrijel","Gabrina","Gaby","Gaea","Gaétan","Gaetana","Gaétane","Gaetano","Gaia","Gaius","Gaj",
            "Gaja","Gajana","Gajána","Gajané","Gajanija","Gal","Gál","Gala","Galatea","Galatée","Galateia","Galatia","Galia","Galina","Gall",
            "Gallien","Gallo","Gallus","Galo","Galus","Ganna","Gareth","Garik","Garika","Garrick","Gary","Gaspar","Gáspár","Gaspard","Gaspare",
            "Gaston","Gastone","Gaszton","Gautier","Gavin","Gavriil","Gavril","Gavrilo","Gavrina","Gawel","Gayetano","Gedeon","Gédéon","Gedeone","Geertruda",
            "Geertrudis","Geertruida","Gejza","Gellért","Geltrude","Gema","Gemma","Genadij","Genadije","Genadio","Genciana","Genevieve","Geneviive","Gennadij","Gennadio",
            "Gennadius","Genovefa","Genovéfa","Genoveffa","Genoveva","Genovéva","Genoweffa","Genrietta","Genrich","Gentiane","Genziana","Geoffrey","Georg","Georga","George",
            "Georges","Georgette","Georgi","Georgia","Georgij","Georgija","Georgije","Georgina","Georgina","Georgine","Georgios","Georgius","Gerald","Geralda","Geralde",
            "Géralde","Geraldina","Geraldine","Géraldine","Geraldo","Geraldus","Gerard","Gérard","Gerarda","Gerardina","Gerardo","Gerardus","Gerasim","Geraszim","Géraud",
            "Gerazim","Gerd","Gerda","Geremia","Gerga","Gergana","Gergely","Gergina","Gerhard","Gerharda","Gerharde","Gerhardina","Gerhardine","Gerlach","Gerlind",
            "Gerlinda","Gerlinde","Germaine","German","Gerold","Geronimo","Gerret","Gershom","Gershon","Gerson","Gert","Gerta","Gertie","Gertraud","Gertraude",
            "Gertrud","Gertrúd","Gertruda","Gertrúda","Gertrude","Gerwald","Geza","Géza","Gheorge","Gheorghe","Ghita","Giacint","Giacinto","Giacobbe","Giacomo",
            "Giada","Gianni","Giannina","Gideon","Giko","Gil","Gilbert","Gilberta","Gilberte","Gilberto","Gilbertus","Giles","Gilles","Gina","Gine",
            "Gineta","Ginette","Ginglielmo","Ginlia","Gino","Ginseppa","Giobbe","Gioele","Giona","Gionata","Giordano","Giorgio","Giorgos","Giovanna","Giovanni",
            "Giralda","Girolamo","Gisbert","Gisela","Giselbert","Gisele","Gisella","Gita","Gitta","Gitte","Giuditta","Giuliana","Giuliano","Giulio","Giuseppe",
            "Giuseppina","Giustina","Giustino","Gizela","Gizella","Gjunter","Gladys","Gleb","Glen","Glenn","Gloria","Gloria","Glorie","Glorija","Glory",
            "Godard","Godardo","Godart","Goddard","Goddart","Goran","Góran","Gorana","Goranka","Gorazd","Gordana","Gordina","Gordon","Gorimira","Gortenzija",
            "Gosciwit","Gostimir","Gostimira","Gostislav","Gostislava","Gošcimir","Gošcislaw","Gotard","Gotardo","Gothard","Gothárd","Gottdank","Gottfried","Gotthard","Gottlieb",
            "Grace","Gráce","Gracia","Grácia","Gracian","Gracián","Graciana","Graciána","Graciane","Graciano","Grácie","Grácie","Graciela","Gracija","Gracija",
            "Gracijan","Gracja","Gracjan","Gracjana","Gradita","Grant","Gratia","Gratian","Gratianus","Grazia","Grazian","Graziano","Graziella","Gražina","Gražyna",
            "Greger","Grégoire","Gregor","Gregoria","Grégoria","Grégoriane","Gregorina","Gregorio","Gregorius","Gregoros","Gregory","Greta","Gréta","Grete","Gretha",
            "Grethe","Gretchen","Greti","Grigor","Grigore","Grigorena","Grigorij","Grigorija","Grigorina","Griseld","Griselda","Griseldis","Grizelda","Grizeldisz","Grozdan",
            "Gryzelda","Grzegorz","Gualterio","Gualterus","Gualtiero","Gubert","Gudrun","Guendalina","Guglielma","Guglielmina","Gui","Guido","Guilherme","Guillaume","Guillelmina",
            "Guillelmine","Guillermo","Guinevere","Gujdó","Gulliver","Gunar","Gunnar","Gunter","Gunter","Guntero","Gunther","Gun?her","Guntherus","Guntrun","Gustaaf",
            "Gustaf","Gustav","Gustav","Gustava","Gustava","Gustave","Gustavo","Gustavus","Gustaw","Gustawa","Gustina","Gustína","Gustýna","Gusztáv","Guy",
            "Gvenda","Gvendolina","Gvendolína","Gvido","Gweanel","Gwen","Gwenda","Gwendolen","Gwendolin","Gwendoline","Gwendolyn","Gwido","Gwidon","Gyorgy","Gyózo",
            "Gyula","Hadewig","Hadrián","Hadriana","Hadrianna","Hagar","Haide","Haidi","Haidrun","Hajnal","Hajnalka","Halija","Halina","Halka","Halyna",
            "Hamilton","Hamis","Hana","Hanele","Hanelora","Hanelore","Hanerosa","Hanka","Hanna","Hannah","Hanne","Hannele","Hannelore","Hannerose","Hanni",
            "Hans","Hanuš","Hanuše","Hanuška","Harald","Harbert","Hardi","Hardy","Harieta","Harley","Harold","Harriet","Harry","Harun","Haštal",
            "Háta","Havard","Havel","Havla","Hector","Heda","Hedda","Hedvig","Hedviga","Hedviges","Hedvika","Hedwig","Hedwige","Heidelinde","Heidemarie",
            "Heidi","Heidrun","Heike","Heike","Heiko","Heimeran","Heinrich","Heinz","Hektor","Helen","Helén","Helena","Heleni","Hélene","Helga",
            "Heliodor","Hellmuth","Helmut","Helmuth","Heloise","Héloise","Hendrik","Hendrika","Hendrike","Henni","Henny","Henri","Henricius","Henricus","Henrieta",
            "Henrietta","Henriette","Henrich","Henrijeta","Henrik","Henrika","Henrike","Henrique","Henriqueta","Henry","Henryk","Henryka","Héraud","Herbert","Herberta",
            "Herbertine","Herberto","Heribert","Heriet","Herieta","Herma","Herma","Herman","Herman","Hermaun","Hermina","Hermina","Hermína","Hermini","Herminia",
            "Hernando","Herta","Hertha","Heoman","Heomana","Heománka","Hiacynt","Hieronim","Hieronimo","Hieronimus","Hieronym","Hieronymus","Hilaire","Hilar","Hilár",
            "Hilaria","Hilária","Hilarij","Hilario","Hilarius","Hilárius","Hiláriusz","Hilary","Hilary","Hilda","Hildi","Hillary","Hillary","Hilmár","Hiob",
            "Hipolit","Hipolito","Hippolit","Hippolite","Hippolitus","Hippolytus","Hjalmar","Homer","Homer","Homér","Homire","Homerus","Honey","Honor","Honorata",
            "Honoráta","Honoré","Honoria","Honorij","Honorina","Honorine","Honorius","Honóriusz","Honory","Horác","Horace","Horacij","Horacio","Horacjusz","Horacy",
            "Horatio","Horatius","Horst","Hortense","Hortensia","Hortensie","Hortensija","Hortensja","Hortenzia","Hortenzie","Horymíra","Horymrr","Hooislava","Hostimil","Hostimila",
            "Hostimír","Hostimíra","Hostislav","Hostislava","Hostivít","Hovard","Howard","Hrabra","Hrabri","Hranisav","Hranislav","Hranislava","Hroznatá","Hubert","Huberta",
            "Huberto","Hubertus","Hubrecht","Hugbert","Hugh","Hugo","Hugo","Hugohn","Hugon","Hugue","Hugues","Humbert","Hvalenko","Hvizdo","Hvezdoo",
            "Hvizdoslava","Hviezdoslav","Hviezdoslava","Hyacint","Hyacinta","Hyacinte","Hyacinth","Hyacintha","Hyacinthe","Hyacinthe","Hyacinthus","Hyazint","Hyazinth","Hyazintha","Hyazinthus",
            "Hynek","Hypolit","Chadwick","Chaim","Chaja","Chanel","Chantal","Chantalle","Charalambos","Charilaos","Charline","Charles","Charlette","Charlie","Charlie",
            "Charlota","Charlotta","Charlotti","Chaya","Chelsea","Cheryl","Chiara","Chimalli","Chloe","Chloé","Chrabr","Chrabro","Chrabroš","Chraniboj","Chranibor",
            "Chranimir","Chranislav","Chranislava","Chrétien","Chrétienne","Chris","Chris","Chris","Christa","Christakis","Christalina","Christel","Christiaan","Christian","Christiana",
            "Christiane","Christianus","Christie","Christie","Christien","Christin","Christina","Christine","Christo","Christoffer","Christofor","Christoph","Christophe","Christopher","Christopherus",
            "Christos","Chrudoš","Chrystian","Chrystja","Chval","Chvalislav","Iain","Iakim","Iballa","Iboja","Ibolja","Ibolya","Ibrahim","Ida","Idaberga",
            "Ide","Idzi","Ieron","Ieronim","Ignác","Ignác","Ignáce","Ignácia","Ignácie","Ignacij","Ignacio","Ignacja","Ignacy","Ignat","Ignát",
            "Ignáta","Ignatia","Ignatij","Ignatius","Ignaz","Ignazio","Ignez","Igor","Iisus","Ika","Iker","Ikerne","Ilarij","Ilario","Ilarion",
            "Ildefonso","Ildico","Ildika","Ildiko","Ildikó","Ileana","Ilian","Iliana","Ilias","Ilija","Ilija","Ilijana","Ilike","Ilja","Ilja",
            "Iljana","Iljina","Illés","Illja","Ilona","Ilonka","Ilsa","Ilza","Ima","Imanuil","Imelda","Imma","Immanuel","Immanuela","Immanuil",
            "Imre","Imrich","Ince","Ines","Inés","Inis","Inesa","Inessa","Inez","Inéz","Inga","Inge","Ingeborg","Ingeborga","Ingrid",
            "Ingrida","Ingryda","Ingvar","Ingwar","Inigo","Ioigo","Inka","Inken","Inna","Innes","Innocent","Innocentius","Innocenty","Innokentij","Innozenz",
            "Inocenc","Inocencio","Inocent","Inocentius","Inocenzo","Inoeent","Inokentij","Inokentije","Ioakim","Ioan","Ioann","Ioanna","Ioannes","Ioannis","Ioil",
            "Iolanthe","Iona","Ionas","Iordan","Iosif","Ioulia","Ippolit","Ira","Ira","Iraida","Iren","Irena","Irena","Irenáus","Ireni",
            "Ireni","Iréné","Irine","Irénée","Irénée","Ireneo","Ireneus","Ireneusz","Irijan","Irin","Irina","Irineu","Irinij","Iris","írisz",
            "Irma","Irme","Irmina","Irvin","Irvine","Irving","Irwin","Isa","Isaac","Isaak","Isaakij","Isabel","Isabela","Isabella","Isabelle",
            "Isacco","Isadora","Isaia","Isaiah","Isaias","Isaja","Isbel","Isidoor","Isidor","Isidora","Isidore","Isidoro","Isidorus","Isis","Iskandar",
            "Ismaele","Isobel","Isolda","Isolde","Israel","István","Iuda","Iuliain","Iustini","Iva","Ivain","Ivajlo","Ivan","Iván","Ivana",
            "Ivana","Ivanka","Ivar","Ivauna","Iver","Ivés","Iveta","Ivetta","Ivette","Ivica","Ivka","Ivo","Ivó","Ivona","Ivoni",
            "Ivonn","Ivonne","Ivor","Ivoš","Iwa","Iwan","Iwana","Iwo","Iwona","Izaak","Izabel","Izabela","Izabell","Izabella","Izaiás",
            "Izaiáš","Izaija","Izajasz","Izák","Izák","Izidor","Izidora","Izidóra","Izmael","Izmail","Izolda","Izsák","Izydor","Izydora","Jaakob",
            "Jacek","Jacenty","Jacinto","Jack","Jacob","Jacoba","Jacobina","Jacobine","Jacobo","Jacopo","Jacquelina","Jacqueline","Jacquelyn","Jacques","Jade",
            "Jadon","Jadrana","Jadranka","Jadviga","Jadwiga","Jadyn","Jago","Jahjá","Jáchym","Jaime","Jakab","Jake","Jakim","Jakiv","Jakob",
            "Jákob","Jakoba","Jakobe","Jakobina","Jakobine","Jakov","Jakovos","Jakub","Jakuba","Jakubka","Jakup","Jalmari","Jamaica","James","Jamie",
            "Jamin","Jan","Ján","Jana","Jane","Janek","Janella","Janelle","Janet","Janetta","Janette","Janica","Janice","Janick","Janika",
            "Janina","Janis","Janka","Janne","Jannick","Jannik","Jannis","Janoš","Jánoš","Jans","Jantra","Janusz","Jar","Jara","Jarema",
            "Jari","Jarina","Jarmil","Jarmila","Jarmilo","Jarolíma","Jaromil","Jaromila","Jaromila","Jaromír","Jaromír","Jaromíra","Jaromirka","Jarosav","Jaroslav",
            "Jaroslava","Jaroslaw","Jaroslaw","Jaroslawa","Jarounra","Jaruše","Jas","Jasan","Jasana","Jasen","Jasenka","Jasmin","Jasmina","Jasmína","Jasmíne",
            "Jasna","Jasna","Jasnina","Jason","Jasoo","Jasón","Jasper","Javier","Jayme","Jayne","Jázmin","Jazmina","Jean","Jean","Jeanette",
            "Jeanne","Jeannette","Jeannie","Jeannine","Jed","Jedidiah","Jefferson","Jeffrey","Jeffry","Jefim","Jefrem","Jefrosinija","Jefrosinja","Jegor","Jekaterina",
            "Jela","Jeleazar","Jelena","Jelisaveta","Jelisej","Jelizar","Jelizaveta","Jemeljan","Jenica","Jenifer","Jenny","Jenó","Jenovefa","Jenovéfa","Jens",
            "Jentl","Jeorjia","Jeremej","Jeremi","Jeremia","Jeremiah","Jeremiasz","Jeremiáš","Jeremiáš","Jeremiáš","Jeremiáš","Jérémie","Jeremija","Jeremy","Jerguš",
            "Jerome","Jérome","Jeromos","Jeronim","Jeronimo","Jerónimo","Jeroným","Jeroným","Jerry","Jerzy","Jesika","Jessamine","Jesse","Jessica","Jessie",
            "Jessika","Jeunifer","Jeva","Jevdokija","Jevgen","Jevgenij","Jevgenoja","Jevlalija","Jevnika","Jevnikija","Jevsevija","Jezekija","Jil","Jiljí","Jill",
            "Jilliana","Jim","Jimmy","Jimram","Jindra","Jindra","Jindoich","Jindoiška","Jinke","Jinke","Jioí","Jioina","Jitka","Joachim","Joakim",
            "Joanico","Joanis","Joann","Joanna","Joannis","Joannus","Joáo","Joaqinm","Joaquín","Job","Jób","Jocelyna","Jocelyne","Jodie","Jodocus",
            "Jodokus","Jody","Joel","Joelle","Johan","Johana","Johanka","Johann","Johanna","Johannus","Johaunes","John","Johnny","Johnson","Jola",
            "Jolan","Jolana","Jolanda","Jolanta","Jolante","Jolantha","Jolanthe","Jolie","Jolla","Jon","Jona","Jonah","Jonas","Jónás","Jonasz",
            "Jonáš","Jonáš","Jonatan","Jonatán","Jonathan","Jordán","Jordána","Jordána","Jordanka","Jordanus","Jorga","Jorge","Jórgen","Jorgina","Jorgos",
            "Jori","Jorik","Jorika","Joriko","Joris","Jorjia","Jórn","José","Josef","Josefa","Josefina","Josefína","Josefine","Joseph","Josepha",
            "Josephina","Josephine","Joséphine","Josephus","Joshua","Joschua","Josif","Josifa","Josip","Josipa","Josse","Jost","Josua","Josué","Jošt",
            "Jovan","Jovana","Jovanka","Joy","Joyce","Joyce","Jozef","Józef","Jozefa","Józefa","Jozefina","Jozefina","Jozefina","Jozephina","József",
            "Józsua","Jozua","Jozue","Jorgen","Juan","Juana","Juda","Juda","Judah","Judáš","Judd","Judif","Judit","Judita","Judith",
            "Judy","Judyta","Jule","Jules","Julia","Julia","Juliaan","Julian","Julián","Juliana","Juliána","Juliane","Julianija","Julianna","Juliano",
            "Julianus","Juliánusz","Julie","Julien","Julienne","Juliet","Juliette","Julij","Julij","Julijan","Julijana","Julio","Julius","Julius","Juliusz",
            "Juloja","Julyan","Julyna","Jura","Juraj","Juraja","Juráš","Júrgen","Jurij","Justian","Justián","Justin","Justin","Justina","Justina",
            "Justina","Justine","Justinian","Justinián","Justinianus","Justino","Justinus","Justus","Justýn","Justýn","Justýna","Justýna","Jusuf","Jusztin","Jusztina",
            "Juta","Jutka","Jutta","Jutte","Juzef","Juzefa","Kaelin","Kaelyn","Kai","Kai","Kaila","Kain","Kaina","Kaitrin","Kaj",
            "Kaj","Kajetán","Kajetán","Kajetána","Kajetána","Kajna","Kaliman","Kalina","Kalipszó","Kálmán","Kalypso","Kameron","Kamil","Kamila","Kamill",
            "Java","Konstanse","Konstantin","Konstantin","Konstantina","Konstantina","Konstantine","Konstantinos","Konstanty","Konstantyn","Konstantýn","Konstantýna","Konstanze","Konstjantyn","Konsuela","Konzuela",
            "Kora","Kordelia","Kordélia","Kordula","Korina","Korinna","Kornel","Kornel","Korneli","Kornelia","Kornélia","Kornelie","Kornélie","Kornelij","Kornelija",
            "Kornelius","Korneliusz","Kornilij","Koryna","Korynna","Kosma","Kosmas","Kostadin","Kostandin","Kostantin","Kozma","Kozmas","Krása","Krasava","Krasena",
            "Krasimir","Krasimír","Krasimira","Krasislav","Krásna","Krásno","Krasnoj","Krasnomir","Krasnomira","Krasomil","Krasomila","Krasomíra","Krasoslav","Krasoslava","Krescencia",
            "Krescencie","Kreszcencia","Krešimir","Krispin","Krispus","Krista","Kristen","Kristen","Kristi","Kristián","Kristián","Kristiána","Kristiána","Kristijan","Kristin",
            "Kristina","Kristina","Kristini","Kristof","Kristof","Kristóf","Kristofer","Kristoffer","Kristy","Kristýna","Krisztián","Krisztina","Krišpin","Krstina","Kryspin",
            "Krystian","Krystiana","Krystyn","Krystyna","Kryšpín","Kryštof","Krzesimir","Krzysztof","Koesomysl","Koiš?an","Koiš?ana","Ksaverij","Ksaverije","Ksawera","Ksawery",
            "Ksenia","Ksenija","Kunegunda","Kunhuta","Kunigonde","Kunigunda","Kunigunde","Kuprijan","Kurt","Kuzma","Kventin","Kvita","Kvétaná","Kvetava","Kvitomil",
            "Kvitomila","Kvitomír","Kvetoo","Kvitoo","Kvitoslav","Kvitoslav","Kvitoslava","Kvitoslava","Kvitoš","Kvituše","Kvido","Kvinton","Kvirin","Kvirin","Kýla",
            "Kýle","Kýle","Kyriak","Kyriaki","Kyrill","Kyrillus","Kyrillus","Lada","Ladana","Ladina","Ladimíra","Ladislao","Ladislas","Ladislau","Ladislaus",
            "Ladislav","Ladislava","Ladiszla","Laika","Ladomér","Laetitia","Laila","Lajla","Lajos","Lambert","Lamberto","Lambrini","Lamprecht","lan","Lana",
            "Lara","Larina","Larisa","Larissa","Lars","Larysa","László","Látitia","Laur","Laura","Laure","Lauren","Laurenc","Laurence","Laurence",
            "Laurencia","Laurencie","Laurencij","Laurencije","Laurencio","Laurencja","Laurencjusz","Laurens","Laurent","Laurentia","Laurentin","Laurentin","Laurentino","Laurentius","Laurenty",
            "Laurentýn","Laurenz","Laurette","Laurids","Laurie","Laurits","Laurus","Lavra","Lavrentij","Lavrentije","Lavrin","Lavro","Lawrence","Layla","Lazar",
            "Lazar","Lázár","Lazare","Lázaro","Lazaros","Lazarus","Lazarz","Lazzaro","Lea","Léa","Leah","Leandar","Leandr","Leandra","Léandre",
            "Leandro","Leandru","Leda","Leda","Lee","Lee","Leila","Leilah","Leilani","Leja","Lejla","Len","Lena","Léna","Lene",
            "Lenek","Leni","Lenica","Lenka","Lenke","Lenko","Lennart","Lenny","Lenta","Leo","Leó","Leocadia","Léocadie","Leodegar","Leodegario",
            "Leokadia","Leokádia","Leokadie","Leokádie","Leokadija","Leon","Leon","Leon","Leona","Leona","Leonard","Leonard","Léonard","Leonarda","Leonardo",
            "Léonce","Leoni","Léone","Leonela","Leonella","Leoneta","Leonetta","Leonhard","Leonia","Leonid","Leonidas","Leonídas","Leonidász","Leónides","Leonie",
            "Léonie","Leonija","Leonila","Leonor","Leonora","Leontij","Leontija","Leontije","Leontin","Leontín","Leontina","Leontina","Leontine","Léontine","Leontinus",
            "Leontyn","Leontýn","Leontyna","Leontýna","Leopold","Leopold","Leopolda","Leopoldina","Leopoldina","Leopoldine","Leopoldo","Leopoldus","Leopoldyna","Leopoldýna","Leoš",
            "Lesana","Lesbia","Leschek","Leszek","Lešek","Leta","Lethea","Lethia","Letia","Leticia","Letícia","Leticie","Leticija","Letitia","Letizia",
            "Letta","Letycja","Lev","Levi","Lew","Lewis","Leyla","Lia","Lía","Liam","Liána","Liána","Liane","Lianna","Lianne",
            "Libina","Liberta","Libislava","Libor","Libora","Liborio","Liborius","Liboslav","Liboslava","Libussa","Libusza","Libuša","Libuše","Lida","Lída",
            "Lidia","Lídia","Lidija","Lidmila","Liesa","Liese","Líf","Líhána","Lij","Lijana","Lil","Lili","Lili","Lilia","Lilia",
            "Lilia","Lilian","Liliana","Liliane","Lilien","Lilii","Lilija","Lilijana","Lilith","Lilja","Liljan","Liljana","Lillian","Lilly","Lilo",
            "Lilou","Lily","Lina","Linda","Lindi","Lindsay","Lindsey","Line","Lingi","Lino","Lins","Linsbel","Linse","Linton","Linus",
            "Lion","Lionardo","Lionel","Lionello","Lior","Liora","Liran","Lirid","Lirida","Liridon","Liron","Lisa","Lisbet","Lise","Lissa",
            "Lisse","Lita","Litka","Liv","Livia","Livij","Livija","Livije","Livio","Liviusz","Liwia","Liwiusz","Liz","Liza","Ljuba",
            "Ljubana","Ljuben","Ljubica","Ljubim","Ljubina","Ljubislava","Ljubomir","Ljubomira","Ljubomyla","Ljubomyra","Ljuboslav","Ljuboslava","Ljubov","Ljubuša","Ljucian",
            "Ljucija","Ljudevit","Ljudevita","Ljudiša","Ljudmil","Ljudmila","Ljudvig","LMa","LMe","LMo","LMus","LMus","lna","Lodevika","Lodewijk",
            "Lodovica","Lodovico","Lodowick","Loic","Loins","Lois","Lois","Lois","Loise","Lola","Lolita","Lona","Lone","Longin","Longino",
            "Loni","Lora","Loraine","Lóránt","Lore","Loreen","Loren","Lorena","Lorenc","Lorenz","Lorenzo","Loreta","Loretta","Lori","Lorina",
            "Lórinc","Lórinc","Lorna","Lorne","Lorraine","Lota","Lotar","Lotár","Lotario","Lotariús","Lothaire","Lothar","Lotharius","Lotta","Lotte",
            "Lou","Lou","Louisa","Louise","Loukia","Loup","Luba","Lubina","Lubica","Lubica","Lubislava","Lubomil","Lubomila","Lubomír","Lubomír",
            "Lubomír","Lubomíra","Lubomíra","Lubomíra","Lubor","Lubor","Lubor","Lubora","Luboslav","Luboslav","Luboslava","Luboslava","Luboslaw","Luboš","Luboš",
            "Lubov","Luc","Luca","Lucas","Lucia","Lucía","Lúcia","Lucian","Lucián","Luciana","Luciána","Lucianna","Luciano","Lucianus","LucibeU",
            "Lucie","Lucien","Lucieune","Lucija","Lucijan","Lucilla","Lucio","Lucius","Lúciusz","Lucja","Lucjana","Lucrece","Lucrice","Lucrecia","Lucrecio",
            "Lucretia","Lucretiu","Lucretius","Lucrezia","Lucrezio","Lucy","Lueina","Ludik","Ludina","Ludevít","Ludimir","Ludimír","Ludislav","Ludiše","Ludivoj",
            "Ludka","Ludmil","Ludmila","Ludmila","Ludmila","Ludmilla","Ludomila","Ludomir","Ludomir","Ludomír","Ludomira","Ludomira","Ludomíra","Ludoslav","Ludoslav",
            "Ludoslava","Ludoslava","Ludoslaw","Ludoslawa","Ludovica","Ludovicus","Ludovika","Ludovít","Ludovíta","Ludowic","Ludowick","Ludvig","Ludviga","Ludvík","Ludvika",
            "Ludvíka","Ludwig","Ludwiga","Ludwik","Ludwika","Luigia","Luigino","Luisa","Luitpold","Luiz","Luiza","Lujza","Luka","Luka","Lukács",
            "Lukan","Lukana","Lukas","Lukasz","Lukáš","Lukáška","Luke","Lukia","Lukija","Lukina","Lukrecia","Lukrécia","Lukrecie","Lukrécie","Lukrecij",
            "Lukrecija","Lukrecius","Lukrecja","Lukrecjusz","Lukretia","Lumír","Lumíra","Luna","Lune","Lusibell","Lusine","Luther","Lutherus","Lutibor","Lutobor",
            "Luzia","Luzía","Lydia","Lýdia","Lydie","Lýdie","Lynda","Lynete","Lynn","Lynnette","Mabel","Mabela","Mabelia","Mabella","Mabelle",
            "Mable","Mac","Macaire","Macario","Macarius","Maciej","Mackenzie","MacKenzie","Madalena","Maddalena","Maddison","Maddison","Madeleine","Madeline","Madison",
            "Madison","Madleen","Madlen","Madlena","Madonna","Madrona","Madrun","Maeve","Magda","Magdala","Magdalena","Magdaléna","Magdaleni","Magdalina","Magdalini",
            "Magdolna","Maggie","Magia","Magn","Magne","Magno","Magnus","Magnusz","Mahulena","Mahuliena","Macharius","Mai","Maia","Maible","Maik",
            "Maikel","Mailin","Maina","Maire","Maj","Mája","Mája","Majja","Majka","Makar","Makár","Makarij","Makarije","Makarius","Makariusz",
            "Makary","Makeda","Maksim","Maksima","Maksimian","Maksimilian","Maksimilijan","Maksimiljan","Maksym","Maksymilian","Malena","Malgorzata","Mali","Malin","Mallory",
            "Mallory","Malva","Malvín","Malvína","Malvína","Malvy","Malwina","Malwine","Mandy","Manfred","Manfréd","Manfredi","Manfredo","Manfried","Manfroy",
            "Manka","Manogu","Manoila","Manol","Manon","Manona","Mansueto","Mansuetus","Mansvet","Mansvét","Manszvét","Manuel","Manuela","Manuéla","Manuele",
            "Manuelle","Manuil","Manus","Mara","Marat","Marc","Marcel","Marcela","Marceli","Marceli","Marcelia","Marcelia","Marcelija","Marcelin","Marcelin",
            "Marcelina","Marcelína","Marcelle","Marcellina","Marcello","Marcellus","Marcelo","Marcia","Marcin","Marco","Marcos","Marcu","Marcus","Mareike","Marek",
            "Marfa","Marga","Margaret","Margareta","Margareta","Margareti","Margaretha","Margarethe","Margaretta","Margarita","Margherita","Margit","Margita","Margitha","Margitta",
            "Margot","Margre?he","Marguerite","Maria","Maria","Mariamna","Marian","Marián","Mariana","Marianna","Marianne","Mariano","Marianus","Maribel","Marica",
            "Maricela","Marie","Mariel","Mariela","Mariella","Marielle","Marien","Marieta","Marietta","Mariette","Marij","Marija","Marijan","Marijana","Marika",
            "Marilla","Marilyn","Marilyne","Marilynn","Marin","Marina","Marina","Marini","Marino","Marinus","Mario","Mario","Mariola","Mariole","Marion",
            "Mariona","Marisa","Marisela","Mariska","Mariša","Marita","Maritta","Marius","Márius","Mariusz","Máriusz","Marjana","Marjo","Marjorie","Marjory",
            "Mark","Márk","Markel","Markell","Markellina","Markelo","Markéta","Marko","Markos","Markus","Márkus","Marleen","Marlen","Marlena","Marlene",
            "Marlene","Marléne","Marlon","Maro","Maron","Maroš","Marquita","Marro","Marta","Marta","Marten","Martha","Marthe","Martin","Martina",
            "Martina","Martine","Martinian","Martinián","Martiniano","Martinka","Martino","Martjan","Márton","Marty","Martyn","Martyna","Maruše","Marvin","Mary",
            "Maryla","Maryna","Maryša","Marzella","Marzellus","Maoen","Maoí","Maoila","Masa","Masha","Mascha","Massimiliano","Massimo","Maša","Máša",
            "Mašenka","Máté","Matea","Matei","Matij","Matij","Matij","Matij","Matij","Matijka","Matelda","Mateo","Mateusz","Matfij","Mathea",
            "Mathias","Mathie","Mathies","Mathieu","Mathilda","Mathilde","Mathis","Matias","Matías","Matiáš","Matij","Matija","Matija","Matild","Matilda",
            "Matilde","Mato","Matouš","Matt","Mattea","Matteo","Matthaeus","Matthaus","Matthew","Matthias","Matthieu","Matti","Mattia","Mattias","Mattis",
            "Matts","Matúš","Matvej","Matvij","Matyas","Matyáš","Matyáš","Matyáška","Matylda","Maud","Maude","Maudie","Maura","Maurice","Mauricio",
            "Mauriciu","Mauricius","Maurícius","Mauritius","Maurits","Mauritz","Maurizio","Mauro","Maurycjusz","Maurycy","Mavrikij","Max","Maxián","Maxie","Maxim",
            "Maxima","Maxima","Maxime","Maxime","Maximiliaan","Maximilian","Maximilián","Maximiliana","Maximiliane","Maximiliano","Maximilien","Maximilla","Maximo","Maxine","Maxmilian",
            "Maxmilián","Maxmiliána","Maxmiliána","Maxwell","May","Maya","Mca","Mckenzie","McKenzie","Meeislav","Meeislava","Meda","Medard","Medard","Medard",
            "Medardo","Medardus","Médea","Médea","Mefodij","Meg","Megan","Meggi","Meggie","Meggy","Meghan","Mechthild","Mechtild","Mechtilda","Mechtilde",
            "Melana","Melánia","Melanie","Melánie","Mélanie","Melanija","Melany","Melchior","Melchiorre","Melchor","Melichar","Melina","Melinda","Mélinda","Meline",
            "Melisa","Melissa","Mélisse","Melita","Melitta","Melodie","Melody","Melony","Melpomeni","Melvffle","Melvin","Melvina","Menahem","Menachem","Mendel",
            "Menyhért","Mercedes","Mercedes","Mercédesz","Merit","Merita","Merlijn","Merlin","Merlinus","Mersija","Mervin","Mervyn","Mesmin","Mišek","Meta",
            "Methodius","Metod","Metod","Metodij","Metodi","Metodij","Metodije","Metodio","Metodiu","Metodius","Metody","Metta","Mette","Mia","Micaela",
            "Micislao","Mick","Mickey","Micky","Mieiko","Miecislao","Miecislas","Miecislav","Mieczyslaw","Mieczyslawa","Mieszko","Miguel","Miguela","Mihaéla","Mihai",
            "Micha","Michael","Michaela","Michaela","Michail","Michaila","Michajlina","Michajlo","Michal","Michal","Michala","Michalina","Michalis","Michel","Michela",
            "Michele","Michile","Michelina","Michelle","Michiko","Mija","Mika","Mikael","Mikaela","Mike","Mikeš","Mikiko","Mikki","Miklós","Mikola",
            "Mikolaj","Mikolaj","Mikoláš","Mikuláš","Mila","Milada","Miladina","Milan","Milán","Milana","Mileo","Mildred","Milen","Milena","Milena",
            "Mileni","Mileva","Milic","Milica","Milíe","Milij","Milík","Milika","Milín","Milina","Milivoj","Milivoje","Miljan","Miljana","Miljuša",
            "Miljutin","Milka","Milko","Milli","Milo","Miloje","Milojko","Milomíra","Miloo","Milorad","Milorad","Miloslav","Miloslava","Miloslaw","Miloslaw",
            "Miloslawa","Miloslawa","Milosz","Miloš","Miloš","Milota","Milota","Milotija","Milouš","Milován","Milovaná","Milovín","Milowan","Milton","Miluša",
            "Miluše","Milutin","Milutin","Mimi","Mimmi","Mimosa","Mina","Mína","Minail","Minajlo","Minály","Mine","Mine","Minerva","Minka",
            "Minna","Minnie","Mir","Mira","Míra","Mirabel","Mirabela","Mirabella","Mirabelle","MirabeU","Miran","Miranda","Mirandola","Mireo","Mireille",
            "Mirek","Mirela","Mirella","Miriam","Miriama","Miriamna","Miriel","Miriela","Mirina","Mirjam","Mirjana","Mirka","Mirko","Mirko","Mirna",
            "Miroljub","Miroljuba","Miromil","Miromila","Miron","Mirón","Miroslaus","Miroslav","Miroslava","Miroslavo","Miroslaw","Miroslaw","Miroslawa","Miroslawa","Mirtil",
            "Mirtill","Mirtilo","Mírumila","Misha","Mišela","Mitchell","Mitko","Mito","Mlad","Mlada","Mladan","Mladin","Mladina","Mladotín","Mnata",
            "Mni","Mnislav","Mnislava","Moana","Modest","Modesta","Modeste","Modeste","Modestia","Modestina","Modesto","Modestus","Modestýna","Modeszta","Modesztusz",
            "Modron","Moinque","Moira","Moise","Moisej","Moises","Moisés","Mojmir","Mojmír","Mojmíra","Mojmíra","Mojsej","Mojsij","Mojsija","Mojsije",
            "Mojzesz","Mojžíš","Momir","Momira","Mona","Monica","Mónica","Monika","Monika","Montana","Monte","Montgomery","Monty","Mór","Morgan",
            "Morgan","Morgana","Morganne","Moric","Moric","Moris","Moritz","Moriz","Morna","Morris","Morten","Mortimer","Morton","Mooena","Mooic",
            "Mose","Moses","Moshe","MoUy","Moyra","Moyse","Moyses","Mozes","Mózes","Mstibor","Mstislaus","Mstislav","Mstislava","Mstivoj","Mšcislaw",
            "Mudroslava","Muireall","Muriel","Muriela","Murielle","Musa","Mychajlina","Mychajlo","Mylana","Myloslava","Mylycja","Myriam","Myrna","Myron","Myroslava",
            "Myrta","Myrtea","Myrtha","Myrthe","Myrtil","Myrtille","Myrto","Myslibor","Myšlibor","Naia","Nadán","Nadije","Nadeschda","Nadižda","Nadižda",
            "Nadia","Nadija","Nadine","Nadinka","Nadja","Nádja","Nadjeschda","Nadzieja","Naila","Naille","Nafanail","Naike","Nancy","Nándor","Naneta",
            "Nanetta","Nanette","Nannette","Nansi","Naomi","Napoleon","Napoleon","Napoleon","Napoleon","Napoleone","Narcis","Narcis","Narcisa","Narciso","Narcissa",
            "Narcisse","Narcisse","Narcisso","Narcissus","Narcisszusz","Nárcisz","Narcyz","Narcyza","Narek","Narkiss","Narkissa","Nas?a","Nastasia","Nastasija","Nasti",
            "Natacha","Natali","Natalia","Natália","Natálie","Natálie","Natalija","Natalja","Natan","Nátán","Natanael","Natanaela","Nataniel","Nataniela","Nataniele",
            "Natasha","Natascha","Nataša","Nataša","Nathalia","Nathalie","Nathaly","Nathan","Nathanael","Nathanail","Nathanaela","Nathanaille","Nathaniel","Nathaniela","Nazar",
            "Nazareno","Nazarij","Nea","Neal","Ned","Neda","Nedda","Neela","Neele","Nefelé","Nefely","Negislav","Negosav","Negoslav","Nehoslav",
            "Nihoslav","Neil","Neklao","Nela","Neli","Nelia","Nelie","Nelja","Nell","Nella","Nelli","Nellie","Nelly","Nelson","Nemanja",
            "Nena","Nenad","Nenah","Nenela","Nenella","Nennela","Nennele","Nepomuceno","Nepomuk","Neris","Nerys","Nesta","Nestir","Nestor","Neta",
            "Netta","Nette","Neunella","Neven","Nevena","Nevenka","Nezamysl","Nia","Niamh","Niccolo","Nice","Nicea","Niceta","Nicia","Nick",
            "Nicki","Nicki","Nicky","Nicky","Nico","Nico","Nicodime","Nicodemia","Nicodemo","Nicódemus","Nicodim","Nicol","Nicol","Nicola","Nicola",
            "Nicolaas","Nicolae","Nicolai","Nicolao","Nicolas","Nicolás","Nicolaus","Nicole","Nicolet","Nicoletta","Nicolette","Nicoline","Nicolle","Nicolo","Niegoslaw",
            "Niels","Niezamysl","Nigel","Nicholas","Nicholaus","Nik","Nika","Nikandr","Nikanor","Nike","Niké","Nikétás","Niki","Niki","Nikifor",
            "Nikit","Nikita","Nikita","Nikki","Niklas","Niko","Nikodém","Nikodém","Nikodéma","Nikodéma","Nikodémia","Nikodemus","Nikodémusz","Nikodija","Nikodim",
            "Nikodima","Nikol","Nikol","Nikola","Nikola","Nikolaj","Nikolaos","Nikolas","Nikolaus","Nikoleta","Nikoletta","Nikolina","Nikos","Nil","Nila",
            "Nilla","Nilo","Nils","Nina","Ninel","Nino","Nioba","Niobe","Niobe","Nirai","Nita","Noah","Noach","Noam","Nodin",
            "Noe","Noé","Noel","Noél","Noil","Noela","Noelia","Noelia","Noélia","Noelie","Noelie","Noillie","Noema","Noemi","Noémi",
            "Noemie","Noémie","NoiUe","Nona","Nonna","Nora","Nora","Norah","Norberdina","Norbert","Norberta","Norberto","Noreen","Norene","Norik",
            "Norika","Noriko","Norma","Norman","Normand","Normann","Notburga","Octave","Octave","Octavia","Octavian","Octavianus","Octavie","Octavien","Octavio",
            "Octavius","Octavus","Od","Oda","Odana","Odd","Oddone","Ode","Ode","Odeta","Odett","Odetta","Odette","Odile","Odilia",
            "Odilie","Odina","Odmar","Odo","Odó","Odoarda","Odolán","Odolen","Odoljen","Odon","Odón","Odón","Odona","Ofelia","Ofélia",
            "Ofélie","OfeUia","Ofilia","Ofillia","Oksana","Oktavia","Oktávia","Oktavian","Oktavián","Oktávián","Oktavie","Oktávie","Oktavij","Oktavij","Oktavij",
            "Oktavija","Oktavius","Oktávius","Oktaw","Oktawia","Oktawian","Oktawiusz","Ola","Olav","Olavo","Olbram","Olda","Oldrik","Oldoich","Oldoich",
            "Oldoicha","Oldoiška","Oleandra","Oleandrija","Oleg","Oleksandr","Oleksandra","Oleksij","Olena","Olesja","Olga","Olga","Olimpia","Olimpiada","Olimpija",
            "Olin","Olina","Oliva","Olivi","Oliver","Olivér","Oliverio","Olivia","Olivie","Olivier","Olivija","Oljana","OlMa","OlManne","OlMe",
            "OlMero","Olof","Oluf","Oluše","Olympe","Olympia","Olympie","Omar","Omár","Omer","Omero","Ondra","Ondoej","Ondoej","Ondoejka",
            "Onorata","Onorio","Onysij","Onysyfor","Onysym","Ophelia","Ophélie","Ora","Orah","Oran","Orazio","Orbán","Oren","Orest","Oresta",
            "Oreste","Orestej","Orestes","Oresztész","Orfé","Orfea","Orfee","Orfej","Orfeo","Orfeus","Orfeusz","Orchidea","Orchidée","Ori","Oriana",
            "Oriána","Oriane","Orianna","Orianne","Orin","Orina","Orit","Orlan","Orlando","Orlin","Ornela","Ornella","Orora","Orphée","Orsola",
            "Ortensia","Óscar","Osip","Osipa","Oskar","Oskar","Oskara","Ostap","Osvald","Osvalda","Osvaldo","Oswald","Oswalda","Oswalde","Oswaldina",
            "Oswell","Oszkár","Oszvald","Ota","Ota","Otakar","Otakara","O?ho","Othon","Otilia","Otília","Otilie","Otilija","Otmar","Otmár",
            "Oto","Otokar","Otomar","Oton","Otta","Ottavia","Ottavio","Otte","Ottilia","Ottilie","Ottmar","Otto","Ottocar","Ottocare","Ottokar",
            "Ottokár","Ottomar","Otton","Ottone","Otylia","Otýlie","Ovide","Ovidij","Ovidio","Ovidiu","Ovidius","Ovídius","Ovídiusz","Owen","Owidiusz",
            "Oxana","Oxána","Ozséb","Paavo","Pabiana","Pablo","Pacifico","Pacifik","Pádrig","Paedar","Pál","Palmer","Palmira","Palmiro","Palmíro",
            "Palmyra","Paloma","Palóma","Pamela","Pamela","Panajot","Panajota","Panajotis","Pancrace","Pancracio","Pancratie","Pancratius","Pandora","Pankrác","Pankracy",
            "Pankrat","Pankratij","Pankratije","Pankratios","Pankratius","Pankraz","Pankrazio","Pantalejmon","Pantaleon","Pantaleon","Pantaléon","Pantaleone","Paola","Paolina","Paolo",
            "Par","Paraska","Paraskene","Paraskeva","Paraskovija","Parcifal","Parcival","Paride","Paris","Paris","Paris","Parisa","Parker","Parsival","Parzifal",
            "Parzival","Pascal","Pascale","Pascalina","Pascual","Paschalis","Paskal","Paskala","Paskália","Pasqua","Pasquale","Pasqualia","Pasqualina","Paszkál","Patiycjusz",
            "Pátraná","Patrice","Patrice","Patricia","Patricia","Patricie","Patricij","Patricij","Patricije","Patricio","Patricius","Patricius","Patrick","Patrik","Patrika",
            "Patrikij","Patrikija","Patrizia","Patrizio","Patrizius","Patrycj","Patrycy","Patryk","Patty","Paul","Paula","Paule","Paulette","Paulin","Paulina",
            "Paulína","Paulini","Paullus","Paulo","Paulus","Paval","Pavao","Pavel","Pavla","Pavli","Pavlija","Pavlin","Pavlín","Pavlina","Pavlina",
            "Pavlo","Pavlos","Pavol","Pawel","Peadair","Pearl","Pedro","Peggy","Pelageja","Pelagia","Pelagie","Pélagie","Pelagija","Pelajia","Pélegrin",
            "Pelegrino","Pélerin","Pelgrim","Pelhoim","Pellegrin","Pellegrino","Pena","Penelopa","Penelope","Pénelopé","Penka","Penko","Pentti","Pepino","Peppino",
            "Per","Perceval","Percival","Percy","Peregrin","Peregrín","Pérégrin","Peregrine","Peregrino","Peregrinus","Peregryn","Perez","Perchta","Pericle","Pericles",
            "Perikles","Periklés","Periklész","Perkins","Perla","Perluše","Persida","Perside","Persis","Perzida","Petar","Peter","Peter","Pitko","Petr",
            "Petra","Petrana","Petrija","Petrik","Petro","Petromila","Petronela","Petronella","Petronij","Petronije","Petronila","Petronilla","Pétronille","Petronilo","Petronio",
            "Petronius","Petrónius","Petróniusz","Petros","Petru","Petrula","Petrumila","Petrunelja","Petrus","Petruše","Petoi","Petoina","Petoini","Petula","Piva",
            "Pivuše","Phebe","Phffipp","Philbert","Philemon","Philiberte","Philip","Philippa","Philippe","Philippine","Philippos","Phillip","Philomela","Philomele","Philomena",
            "Philoména","Philomene","Philomine","Phóbe","Phoebe","Phoenix","Phyllis","Pia","Piero","Pierre","Pierrette","Pietro","Pij","Pijo","Pilao",
            "Pilip","Pina","Pinelopi","Pio","Piotr","Piroska","Pirro","Pius","Piusz","Pjotr","Plato","Platon","Platón","Platone","Policarpo",
            "Polikarp","Polikárp","Polikarpo","Poliksemja","Poliksena","Polina","Polissena","Polixena","Polixeni","Polly","Pollyanna","Polyana","Polycarpe","Polycarpus","Polykarp",
            "Polykarpos","Polyxena","Polyxena","Polyxeni","Polyxeni","Polyxeni","Pomnin","Pomnina","Pomninka","Pongrác","Pravdoljub","Pravdomil","Pravdomila","Pravislav","Pravoinb",
            "Pravoljub","Pravomil","Pravomila","Pravomír","Pravoslav","Pravoslava","Predrag","Preslava","Pribisav","Pribislav","Pribislava","Pribyslaw","Primislaus","Primislav","Prisca",
            "Priscila","PrisciUa","Priska","Priskilla","PriskiU","Procope","Procopio","Procopiús","Prochor","Prokop","Prokopa","Prokopi]","Prokopij","Prokopije","Prokopios",
            "Prokopka","Prosper","Prospero","Próspero","Protas","Protasij","Prvoslava","Przemyslaw","Przybyslawa","Poemek","Poemysl","Poemyslav","Poemyslava","Poibyslav","Poibyslava",
            "Pulcherija","Qinnton","Qinrino","Qscar","Quentin","Quido","Quincy","Quinetta","Quinn","Quintilien","Quirin","Quirinus","Qunitana","Racibor","Rada",
            "Radak","Radan","Radana","Rade","Radegast","Radek","Radeka","Raden","Radhost","Radie","Radigost","Radij","Radika","Radim","Radima",
            "Radimil","Radimir","Radimír","Radimira","Radimíra","Radin","Radislav","Radislava","Radivoj","Radivoje","Radka","Radko","Radmil","Radmila","Radogost",
            "Radohost","Radoje","Radojica","Radojka","Radola","Radolf","Radoljub","Radomil","Radomil","Radomila","Radomila","Radomír","Radomír","Radomíra","Radomíra",
            "Radomyr","Radon","Radoslav","Radoslava","Radoslaw","Radoslawa","Radoš","Radouš","Radovan","Radovana","Radovot","Radowan","Radslav","Radslava","Raissa",
            "Raduil","Radul","Radula","Radule","Raduš","Raduše","Radúz","Radvan","Radvana","Radvít","Rae","Rafael","Rafaela","Rafaella","Rafail",
            "Rafaila","Rafal","Rafalyna","Raffaela","Raffaele","Raffaelo","Ragnar","Rahel","Ráhel","Ráchel","Ráchel","Rachela","Rachele","Rachil","Rachila",
            "Rachilja","Raimondo","Raimund","Raimunda","Raimunde","Raimundo","Raina","Rainald","Raine","Rainer","Rainerio","Rainhard","Rainhold","Rainier","Raino",
            "Rainold","Raisa","Raissa","Rajka","Rajko","Rajmond","Rajmonda","Rajmund","Rajmunda","Rajna","Rajnald","Rajnold","Rajsa","Rakela","Ralf",
            "Ralica","Ralph","Raluca","Ramon","Ramón","Ramona","Ramóna","Ran","Randál","Randolf","Randolfo","Randolph","Randulf","Randulv","Randy",
            "Ranek","Ranieri","Ranieri","Ranjo","Ranko","Raoul","Raphael","Raphaela","Raquel","Rastisav","Rastislav","Rastislava","Ratiboo","Ratimir","Ratimír",
            "Ratislav","Ratmir","Ratmír","Ratoslav","Raul","Raven","Ray","Rayan","Raymond","Raymonde","Raymundo","Rayna","Rayner","Raynor","Rea",
            "Rebeca","Rebecca","Rébecca","Rebeka","Rebekah","Rebekka","Redmond","Redmund","Regan","Regina","Regina","Regina","Reginald","Reginaldo","Regini",
            "Régine","Regnar","Régnier","Reik","Reimund","Reinald","Reiner","Reingarda","Reinhard","Reinhilda","Reinhold","Reinold","Reinolf","Reka","Remig",
            "Remigio","Remigius","Remigiusz","Remo","Remus","Rémusz","Ren","Rena","Renard","Renat","Renát","Renata","Renáta","Renati","Renato",
            "Renatus","Renaud","Rene","René","René","Renée","Reni","Renia","Reno","Renzo","Reuben","Reweka","Reynaldo","Reynaldos","Reynard",
            "Reynold","Rhea","Rhéa","Rhian","Rhianon","Rhomylos","Rhona","Rhóxané","Ria","Riana","Rianna","Riano","Ricarda","Ricardo","Ricardus",
            "Riccarda","Riccardo","Rick","Rickard","Ricky","Rico","Rieard","Richard","Richard","Richarda","Richie","Rijk","Rik","Rikard","Rikarda",
            "Rikhard","Riley","Riley","Rina","Rinaldo","Rini","Rino","Rio","Risto","Rita","Ritchie","Riva","Rivka","Rjurik","Rob",
            "Robert","Robert","Roberta","Roberte","Roberto","Robertus","Robin","Robin","Robina","Robini","Robinson","Robrecht","Robyn","Rocco","Rock",
            "Rocky","Roda","Rodan","Roderick","Roderico","Roderich","Roderik","Roderyk","Rodger","Rodolfo","Rodolph","Rodolphe","Rodrigo","Rodrigó","Rodrigue",
            "Roeland","Roger","Rogerio","Rogerus","Roch","Rochus","Rok","Roka","Roko","Roksana","Roksolana","Rókus","Rolan","Rolán","Roland",
            "Rolánd","Rolanda","Rolande","Rolando","Rolandus","Roleta","Rolf","Rolfe","Rolin","Rolland","Romain","Romaine","Roman","Román","Romana",
            "Romana","Romanija","Romano","Romanus","Romeo","Romeo","Romi","Romil","Romina","Romolo","Romuald","Romualda","Romualdo","Romul","Rómulo",
            "Romulus","Romulusz","Romy","Rona","Rooa","Ronald","Ronaldo","Ronja","Ronny","Roque","Rory","Rosa","Rosalia","Rosalía","Rosalie","Java",
            "Rosalind","Rosalinda","Rosalinde","Rosaline","Rosalio","Rosamond","Rosamund","Rosamunda","Rosamunde","Rosana","Rosanna","Rosaria","Rosarie","Rosario","Roscislaw",
            "Rose","Roseanna","Roselind","Rosemarie","Rosemonde","Rosen","Rosetta","Rosette","Rosina","Rosini","Rosita","Rosmunda","Ross","Rossa","Rostislav",
            "Rostislava","Rostyslav","Rostyslava","Roswith","Roswitha","Rovena","Rovéna","Rowdy","Rowena","Rowland","Roxana","Roxána","Roxane","Roxanna","Roy",
            "Roza","Róza","Róza","Rozalia","Rozália","Rozálie","Rozalija","Rozalinda","Rozamunda","Rozana","Rozanna","Rozárie","Rozário","Rozárka","Rozaroj",
            "Rozeta","Rozina","Rozina","Rozita","Rozvita","Rozyna","Rrešencija","Ruben","Ruben","Rubin","Ruby","Rudi","Rudiger","Rudina","Rudolf",
            "Rudolfa","Rudolfina","Rudolfina","Rudolfine","Rudolph","Rudolpha","Rudolphe","Rudolphina","Rudolphus","Ruf","Ruf","Rufin","Rufino","Rufinus","Rufus",
            "Rúfus","Rúfusz","Ruggero","Rumold","Runa","Rune","Rupert","Ruperto","Rupertus","Ruprecht","Rurik","Ruryk","Rusana","Ruslan","Ruslana",
            "Ruszlán","Rut","Rút","Ruta","Rutger","Ruth","Ruvini","Ruža","Ružana","Ružena","Ružena","Ružica","Ryan","Ryszard","Ryszarda",
            "Oehoo","Oehooka","Saba","Sába","Sabás","Sabba","Sabel","Sabina","Sabina","Sabini","Sabrin","Sabrina","Sádko","Saffron","Sage",
            "Saima","Saimi","Salamon","Sally","Salmon","Saloma","Salomao","Salome","Salomé","Salomea","Salomeja","Salomena","Salomo","Salomon","Salomon",
            "Salomone","Salomoja","Salvador","Salvátor","Salvátor","Salvátore","Sam","Samanta","Samantha","Samar","Samara","Sammy","Sámo","Samojlo","Sampson",
            "Samson","Samson","Samuel","Samuel","Samuela","Samuele","Samuella","Samuil","Samuilo","Samy","Sana","Sándor","Sandra","Sandrine","Sandro",
            "Sane","Sanela","Sansón","Santál","Santiago","Santino","Sapfo","Sara","Sára","Sarah","Sarai","Sarolta","Saron","Sarra","Sasha",
            "Sasha","Sascha","Sascha","Saskia","Saskie","Saša","Saša","Saše","Sašo","Saturni]","Saturnin","Saturnino","Saturninus","Saturnus","Saul",
            "Saul","Sava","Sava","Sáva","Sáva","Savana","Savanna","Savannah","Save","Savela","Savelij","Savelja","Saverio","Savina","Savka",
            "Savo","Sawa","Sawin","Saxana","Saxona","Sbislaus","Sbygneus","Scarlett","Scolastica","Scott","Sean","Sébastia","Sebastian","Sebastián","Sebastián",
            "Sebastiana","Sebastiána","Sebastiane","Sebastiane","Sebastiano","Sebastianus","Sébastien","Sebastinie","Sebastio","Sebestian","Sebestyén","Sedrik","Segismundo","Seia","Seiya",
            "Sej","Selena","Selené","Séléne","Selina","Seline","Selwin","Selwyn","Sem","Semjén","Semjon","Senad","Senadin","Senta","Sentia",
            "Sepp","Serafim","Serafima","Serafin","Serafín","Serafina","Serafína","Serafino","Serafinus","Serafym","Seraph","Séraphin","Seraphina","Seraphine","Séraphine",
            "Seren","Serena","Séréna","Sérine","Sereno","Serenus","Serge","Sergej","Sergij","Sergije","Sergio","Sergiu","Sergius","Sergiusz","Serchio",
            "Servác","Servacio","Servácius","Servacoj","Servais","Servatius","Servaz","Servazio","Servio","Serwacy","Sevastiana","Sevastiani","Sevastija","Sevastijan","Sevastjan",
            "Sevastjana","Severian","Severin","Severín","Severina","Severína","Severino","Severinus","Severjan","Severyn","Seweryn","Shane","Shanel","Shanell","Shania",
            "Shannen","Shannon","Shari","Sharon","Sheila","Sheley","Shelley","Sheryl","Shilla","Shiloh","Shimon","Shoshanna","Shoshaunah","Scholastica","Scholastika",
            "Schulammit","Sian","Siana","Sibila","Sibille","SibiUa","Sibota","Sibyla","Sibylla","Sibylle","Sid","Sidney","Sidney","Sidoine","Sidon",
            "Sidón","Sidonia","Sidónia","Sidonie","Sidonija","Sidonio","Sidonius","Sidony","Siegfried","Sieglinda","Sieglinde","Siegmund","Sielinda","Sigfred","Sigfrid",
            "Sigfrid","Sigfríd","Sigfrido","Sigismond","Sigismondo","Sigismund","Sigismundo","Sigismundus","Sigizmund","Siglinde","Sigmund","Sikandar","Sikst","Silas","Silka",
            "Silke","Silva","Silvain","Silvan","Silván","Silvana","Silvána","Silvano","Silvanus","Silvestar","Silvester","Silvestr","Silvestra","Silvestoe","Silvia",
            "Silvie","Silvin","Silvius","Simeáo","Simeha","Simeon","Simeón","Siméon","Simeona","Simeone","Simion","SiMj","SiMja","SiMje","Simo",
            "SiMo","Simon","Simon","Simona","Simone","Simone","Simoneta","Simonetta","Simonette","Simonida","Simson","Sinclair","Sindy","Sinikka","Sinkler",
            "Siobhán","Sirio","Sirkka","Siro","Sisi","Sissi","Sissy","Sixt","Sixte","Sixto","Sixtus","Skarlet","Skarleta","Skolasztika","Slav",
            "Sláva","Sláva","Sláva","Slavata","Slávek","Slaven","Slavena","Slavina","Slavi","Slaviboj","Slavibor","Slavij","Slavija","Slavimir","Slavin",
            "Slavína","Slavjana","Slávka","Slávka","Slávko","Slavmyr","Slavoboj","Slavoj","Slavoje","Slavoljub","Slavolúb","Slavomil","Slavomila","Slavomír","Slavomír",
            "Slavomíra","Slavomíra","Slavomyr","Slavoš","Slaw","Slawa","Slawa","Slawka","Slawomir","Slawomira","Slobodan","Slobodana","Slobodanka","Slobodinka","Slobodna",
            "Smil","Smile","Smiljan","Snežana","Snežanka","Sobislav","Sobislava","Sobieslaw","Sobieslawa","Socrate","Socrates","Sócrates","Sofia","Sofia","Sofian",
            "Sofián","Sofie","Sofija","Sofijan","Sofio","Sofja","Sofrina","Sofronia","Sofrónia","Sofronie","Sofronija","Sokrat","Sokrates","Sokratis","Solomon",
            "Solveig","Solveiga","Solvej","Sooa","Sonia","Sonja","Sonny","Sonya","Sophia","Sophie","Sophien","Sophius","Sopnronia","Sóren","Sorley",
            "Soter","Sotera","Sotero","Sotir","Sotira","Sotiria","Sotirios","Sotirka","Spas","Spencer","Sperancie","Spiros","Spyros","Spytihniv","Spytimír",
            "Srdjan","Srdžan","Stacy","Staffan","Stána","Stanimir","Stanimír","Stanislao","Stanislas","Stanislau","Stanislaus","Stanislav","Stanislava","Stanislaw","Stanislaw",
            "Stanislawa","Stanko","Stanley","Stas","Stasi","Stasij","Stašek","Statis","Stavros","Staza","Stáza","Stefan","Stefana","Stefania","Stefánia",
            "Stefanie","Stefanija","Stefanka","Stefano","Stefanus","Stefanyda","Steffi","Stefi","Stela","Stelia","Stelio","Stella","SteMa","SteMo","Stepán",
            "Stepanida","Stepanija","Stepanyda","Stephan","Stephana","Stéphane","Stéphane","Stephanie","Stéphanie","Stephanos","Stephanus","Stephen","Stevan","Steve","Steven",
            "Stihán","Stilijan","Stipe","Stjepan","Stoimir","Stojan","Stojimir","Stojmír","Stomir","Stoezislav","Stoezomír","Sufija","Sulamit","Sulamita","Sulamith",
            "Sulejka","Sulejman","Sulika","Susan","Susana","Susane","Susann","Susannah","Susanne","Susauna","Susette","Sussan","Suzan","Suzana","Suzanne",
            "Svána","Svatava","Svatoboj","Svatoboj","Svatobor","Svatomír","Svatomír","Svatomíra","Svatopluk","Svatopluk","Svatoslav","Svatoslav","Svatoslava","Svatoš","Svatuše",
            "Svebor","Svein","Svemil","Svemir","Sven","Svend","Sveno","Sverad","Sveslav","Svita","Svetana","Svetibor","Svetimir","Svetimira","Svetimirka",
            "Svetíslav","Svetislava","Svitla","Svitla","Svitlana","Svitlana","Svitluše","Sveto","Svetomir","Svetomira","Svetoslav","Svitoslav","Svetozar","Svetozár","Svierad",
            "Svitlana","Svitozar","Svjatopolk","Svjatoslav","Swana","Swen","Swietobor","Swietomir","Swietopelk","Swietoslaw","Swietoslawa","Sybil","Sybila","Syd","Sydney",
            "Sydney","Sydonia","Sydoniusz","Syjato","Syjatogor","Sykstus","Sylfest","Sylva","Sylvain","Sylvest","Sylvester","Sylvestr","Sylvestre","Sylvie","Sylwester",
            "Sylwia","Sylwin","Sylwiusz","SyM","SyMa","Symeon","Symon","Szabina","Szalvátor","Szandra","Szaniszló","Szarlota","Szczepan","Szczesny","Szende",
            "Szeráf","Szerafina","Szeréna","Szerénusz","Szergiusz","Szervác","Szeverin","Szibilla","Szidónia","Szigfrid","Szilvána","Szilvánusz","Szilveszter","Szilvius","SziMa",
            "Szimona","Szixtusz","Szofrónia","Szonja","Szvetlána","Szymon","Szymona","Šalamoun","Šalamún","Šalomoun","Šana","Šándor","Šandy","Šandy","Šanel",
            "Šansone","Šantal","Šárka","Šarlota","Šavel","Šavol","Šebestián","Šebestiána","Šebío","Šelma","Šimon","Šimona","Školastika","Šlechtislav","Š?asta",
            "Štefan","Štefánia","Štefanie","Štefánie","Štipán","Štipána","Štipánka","Tabea","Tabita","Tábita","Tabitha","Taddeo","Taddeus","Tádé","Tadeáš",
            "Tadej","Tadeo","Tadeu","Tadeusz","Tadija","Taida","Tainá","Tainara","Tais","Taisa","Taisia","Taisija","Taito","Takashi","Takaši",
            "Takeo","Takeshi","Talinlah","Talisa","Talitha","Tallis","Tamara","Tamás","Tamika","Tamiko","Tamila","Tammy","Táoa","Tancred","Tancride",
            "Tancredi","Tancredo","Tania","Tanita","Tanitha","Tanja","Tankred","Tankréd","Tano","Tanya","Tapani","Tara","Taras","Tarasij","Tarasije",
            "Tarasio","Taráz","Tarek","Tarik","Tarja","Tasia","Tasilo","Tasio","Taso","Tasso","Tasziló","Tašo","Ta?ána","Tatiana","Tatiane",
            "Tatiani","Tatijana","Tatjana","Tatjána","Ta?jana","Tauá","Tauan","Tavi","TavL","Taylor","Taylor","Tea","Tecla","Ted","Tekla",
            "Telma","Tena","Teo","Teobald","Teobaldo","Teodor","Teodora","Teodora","Teodorica","Teodorico","Teodorik","Teodorika","Teodoro","Teodoró","Teodosia",
            "Teodosie","Teodosija","Teodosije","Teodosio","Teodoz","Teodózia","Teodozie","Teodozija","Teodozja","Teodozjusz","Teodozy","Teofan","Teofane","Teófanes","Teofanie",
            "Teofil","Teofila","Teofila","Teofilija","Teofilo","Teófilo","Terancio","Terence","Térence","Terencio","Terencius","Terencjan","Terencjusz","Teréne","Terentij",
            "Terentius","Terenzio","Teresa","Térésa","Teresia","Terestyén","Terez","Tereza","Terézia","Terezie","Terezija","Terri","Terry","Terzo","Tess",
            "Tessa","Tetjana","Thaddaus","Thaddée","Thaddeus","Thadée","Thadeu","Thadeus","Thais","Thaisa","Thalia","Thálie","Thálie","Thanasis","Thassilo",
            "Thea","Thecla","Thicle","Thekla","Thelma","Theo","Théo","Theobald","Theobaldo","Theodebald","Theoderich","Theodor","Theodor","Theodora","Theodore",
            "Theodore","Theodore","Theodore","Theodoric","Theodorik","Theodorik","Theodorika","Theodoros","Theodorus","Theodooi","Theodosia","Theodosie","Théodosie","Theodosio","Theodosios",
            "Theodosius","Theofan","Theofanes","Theofanie","Theofil","Theofila","Theofilus","Theophania","Theophil","Theophila","Théophile","Theophilus","Theopisti","Theresa","Therese",
            "Thérise","Theresia","Theudebaldo","Thibauld","Thibaut","Thomas","Thomas","Thomasa","Thomasina","Thomasine","Thomaz","Thorstein","Thorsten","Thurstan","Tiago",
            "Tiara","Tibád","Tiber","Tibere","Tiberij","Tiberio","Tiberiu","Tibériusz","Tibold","Tibor","Tibora","Tibore","Tiburc","Tiburce","Tiburcij",
            "Tiburcio","Tiburcius","Tiburtius","Tiburzio","Ticia","Ticiana","Tiffany","Tiger","Tihana","Tichomil","Tichomir","Tichon","Tijana","Tím","Timea",
            "Tímea","Timo","Timofej","Timona","Timone","Timót","Timotea","Timotei","Timoteo","Timoteos","Timoteus","Timothea","Timothée","Timotheos","Timotheu",
            "Timotheus","Timothy","Timotije","Timur","Tina","Tino","Tinomil","Tinomir","Tisa","Tit","Tite","Tito","Titu","Titus","Títus",
            "Titusz","TiU","Tivadar","Tivurtij","Tizia","Tiziana","Tiziano","Tizio","Tlberius","Tlhamér","Tlchomír","Tlmon","Toar","Tobia","Tobias",
            "Tobías","Tóbiás","Tobiasz","Tobiáš","Tobie","Tobija","Tobijas","Toby","Todor","Tódor","Todora","Todosie","Tom","Toman","Tomanija",
            "Tomas","Tomasa","Tomasina","Tomassina","Tomasz","Tomáš","Tomáš","Tomáš","Tomáška","Tomázia","Tomi","Tomislaus","Tomislav","Tomislava","Tomislaw",
            "Tomislaw","Tomislawa","Tommaso","Tommi","Tommy","Toni","Toni","Tonia","Tónino","Tonko","Tony","Tony","Tonya","Topi","Topias",
            "Tor","Tora","Torcuato","Torkvat","Torkvát","Torkvato","Torna","Torquato","Torquatus","Torstein","Torsten","Torvid","Torvin","Tosca","Toska",
            "Toszka","Tovia","Tovija","Tracy","Tracy","Trajan","Traude","Traute","Travis","Trent","Trevor","Tristan","Tristán","Tristano","Tristanus",
            "Tristram","Trisztán","Trix","Trixa","Trixi","Trixie","Trixy","Troy","Truda","Trude","Tryfon","Ttmotej","Tucker","Tudor","Tuho",
            "Tulia","Tulie","Tullia","Tullio","Tunde","Tundér","Tuomi","Tuomo","Tuula","Tuulikki","Tworzyjan","Tyberiusz","Tyburcjusz","Tyburcy","Tyge",
            "Tychin","Tycho","Tychon","Tyke","Tymko","Tymofrj","Tymon","Tymoteusz","Tymur","Týna","Tytus","Uberto","Udo","Ugo","Ula",
            "Ulászló","Ulf","Ulita","Uljan","Uljana","Ulla","Ulric","Ulrica","Ulrico","Ulrih","Ulrich","Ulrik","Ulrika","Ulrike","Ulrikka",
            "Ulrikke","Ulryka","Umar","Umberto","Una","Unislav","Unna","Upton","Urania","Urbain","Urban","Urban","Urbano","Urbanus","Uriáš",
            "Zlatoš","Zlatuša","Zlatuše","Zoa","Zoana","Zoe","Zoé","Zofia","Zoi","Zoja","Zója","Zólestin","Zólestine","Zólestinus","Zoltan",
            "Zoltán","Zora","Zora","Zorán","Zorán","Zoraná","Zorian","Zorica","Zorika","Zorin","Zorina","Zorislav","Zorislava","Zorja","Zorjana",
            "Zorka","Zoro","Zoroslav","Zoroslava","Zoryna","Zosim","Zosima","Zosima","Zosimo","Zósimo","Zosimus","Zosym","Zoya","Zozima","Zozimé",
            "Zsadány","Zsaklin","Zsanett","Zseraldina","Zsigmond","Zsófia","Zsuzsauna","Zuleika","Zulejka","Zulika","Zuzana","Zuzanna","Zvezdan","Zvezdana","Zvezdomir",
            "Zvezdomira","Zvjezdan","Zvonimir","Zvonimír","Zvonimira","Zvonimíra","Zwonimir","Zygfryd","Zygmunt","Zyprian","Zyta","Žakelina","Žakelina","Žakelina","Žaklina",
            "Žaklina","Žanda","Žanet","Žaneta","Žaneta","Žanna","Žanna","Žarko","Žaro","Ždan","Ždana","Želhnira","Želibor","Želimir","Želimír",
            "Želimíra","Želisav","Želisava","Želislav","Želislava","Želislaw","Želislawa","Želmír","Želmíra","Žiboid","Žigmund","Žitomir","Žitomír","Žitomira","Žitomíra",
            "Žitoslav","Žitoslava","Živa","Živan","Živana","Živena","Živena","Živka","Živko","Živo","Živodan","Živodar","Živojin","Živomira","Žofia",
            "Žofie","Žywia"};

}