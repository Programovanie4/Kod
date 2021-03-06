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
            "Aaron","Aar??n","Abadon","Abdon","Abd??n","Abel","??bel","Abelard","Abel??rd","Abelardo","Abele","Abelone","Abigael","Abigail","Abig??l",
            "Abraham","Abrah??m","??brah??m","Abram","Abramo","Absalom","Absalon","Absolon","Absol??n","Ada","Adalbert","Adalberta","Adalbertina","Adalberto","Adalbert??na",
            "Adalbrecht","Adalwin","Adam","??d??m","Adamo","Ad??n","Adda","Adeinaida","Adeineid","Adel","Ad??l","Ad??la","Ad??la","Adelaida","Adelaide",
            "Ad??laide","Adelajda","Adelbert","Ad??le","Adile","Adelhilda","Ad??lia","Adelina","Adelina","Adelinda","Adelinde","Adeline","Adeltraud","Adeltrude","Adin",
            "Adina","Adl??ta","Adolf","Adolfa","Adolfina","Adolfina","Adolfme","Adolfo","Adolph","Adolpha","Adolphe","Adolphina","Adolphine","Adolphus","Adorj??n",
            "Adria","Adriaan","Adrian","Adri??n","Adriana","Adri??na","Adriani","Adriano","Adriauna","Adrien","Adriena","Adrienn","Adrienna","Adrienne","Adrijan",
            "Adrijana","Aegidius","Aemilia","Aemilius","Afabella","Afanas","Afanasij","Afanasija","Afanka","Afda","Afeta","Afete","Afetha","Afette","Afiadna",
            "Afiadni","Afiana","Afina","Afra","Afranija","Africo","Agafja","Agafon","Agafonik","Agata","Ag??ta","Agate","Agatha","Agathe","Agathon",
            "Agatokleia","Agaton","Agat??n","Agatone","Agatonike","Aggel","??gid","??gidius","Aglaia","Aglaja","Agl??ja","Agnes","Agnis","??gnes","Agnesa",
            "Agnese","Agnessa","Agne??a","Agneta","Agnete","Agnethe","Agnieszka","??gosta","Agostino","??goston","Ag??ta","Agueda","Achil","Achiles","Achill",
            "Achille","Achilleas","Achilles","Achillij","Aidan","Aiden","Aimar","Aim??e","Ajrin","Akim","Aksel","Alabhaois","Alad??r","Alain","Alaios",
            "Alajos","Alan","Al??n","Alana","Alanis","Alano","Alanus","Alasdair","Alastair","Alba","Alban","Albana","Albano","Albanus","Albena",
            "Albert","Alberta","Alberte","Albertin","Albertina","Albertina","Albertini","Alberto","Albert??n","Albertyna","Albertyna","Albin","Alb??n","Albina","Albina",
            "Albine","Albino","Albinus","Albrecht","Albyn","Alda","Alde","Aldo","Alec","Alejandra","Alejandro","Aleks","Aleksa","Aleksander","Aleksandr",
            "Aleksandra","Aleksansdar","Aleksej","Aleksi","Aleksija","Aleksina","Aleksis","Aleksy","Alen","Alena","Alena","Alesia","Alessandra","Alessandro","Alessia",
            "Alessio","Alesszia","Ale??","Ale??a","Ale??ka","Aletea","Alithe","Alethea","Al??thea","Aletheia","Alex","Alex","Alexa","Alexander","Alexandr",
            "Alexandra","Alexandre","Alexandre","Alexandros","Alexandru","Alexej","Alexia","Alexie","Alexine","Alexis","Alexis","Alexius","Alfons","Alfonso","Alfonz",
            "Alfred","Alfr??d","Alfreda","Alfr??da","Alfredas","Alfredo","Alfredus","Alfrida","Aliadn??","Alica","Alice","Alicia","Alicja","Alida","Ali??nor",
            "Alina","Aline","Alisa","Alise","Alisha","Alisija","Alison","Alisson","Alithea","Alithia","Alitigone","Aliz","Alkaterini","Allan","Allee",
            "Alleen","Alleen","Allen","Allena","Allene","Alleta","Allette","Allison","Alma","Almaricus","Almi","Almida","Almidija","Almlina","Alna",
            "Alodie","Aloin","Alois","Aloisa","Aloisia","Aloisie","Aloisio","Aloisius","Alojz","Alojza","Alojzia","Alojzie","Alojzja","Alojzy","Alonso",
            "Aloys","Aloysia","Aloysius","Alphonse","Alphonso","Alt??inis","Altemija","Altemis","Altemisa","Alt??mise","Altemisz","Altulini","Altylini","Aluin","Alva",
            "Alvar","Alvaro","Alvian","Alvi??n","Alvin","Alvina","Alvino","Alwin","Alwina","Alwyn","Alysha","Al??bita","Al??bita","Amabel","Amad??",
            "Amadea","Amadea","Amadej","Amadeo","Amadeu","Amadeus","Amadeusz","Amadia","Amado","Amalberga","Amaldeia","Amalia","Am??lia","Am??lie","Am??lie",
            "Ang??lique","Angell","Angelo","Angelos","Angelus","Anghel","Angus","Angyalos","Aniela","Aniello","Anika","Anik??","Aniol","Anisja","Anissa",
            "Anita","Anja","Ann","Anna","Annabel","Annabela","Annabella","Annabelle","Anne","Annetta","Annette","Anni","Annie","Annika","Annouk",
            "Anny","Anouk","Ansel","Anselm","Anselma","Anselme","Anselmo","Anshelm","Antal","Ante","Antea","Anteia","Antheia","Anthony","Anthula",
            "Ant??a","Antigona","Antim","Antin","Antoan","Antoine","Antoinette","Antokleia","Anton","Antonetta","Antoni","Antonia","Antonia","Antonida","Antonie",
            "Antonij","Antonija","Antonije","Antonin","Anton??n","Anton??na","Anton??na","Antonini","Antonino","Antonio","Antonios","Antonis","Antonius","Antony","Antula",
            "Antun","Anu??e","Anzelm","Anzelma","Apolena","Apoliena","Apolka","Apolline","Apollo","Apollonia","Apoll??nia","Apollonie","Apollonija","Apolonia","Apol??nia",
            "Apolonie","Arabela","ArabeUe","Araldo","Aram","Aretta","Archip","Ariadni","Arian","Ariane","Arianna","Arianne","Ariel","Ariela","Ariella",
            "Arielle","Arijadna","Arika","Aristid","Aristide","Aristides","Arisztid","Ark??d","Arkadij","Arkadius","Arkady","Arman","Armand","Armando","Armin",
            "??rmin","Arminio","Arminius","Armyn","Arnaldo","Arnau","Arnaud","Arnault","Arne","Arno","Arn??","Arnold","Arnolda","Arnolde","Arnoldia",
            "Arnoldo","Arno??t","Arno??ta","Arno???ka","Arnoult","Arnout","Aron","??ron","Aronne","Arpad","Arp??d","??rp??d","Arsene","Arsen","Arsenij",
            "Arsenije","Arsenio","Arsenios","Arsenius","Arseniusz","Artem","Arteme","Artemida","Artemij","Arthur","Arthurus","Artim","Ar?om","Artturi","Artur",
            "Art??r","Arturo","Artu??","Arzen","Arz??n","Ashley","Ashley","Askold","Aspasia","Aspasie","Aster","Astra","Astrid","Astrida","Astryda",
            "Asztrid","Atanas","Atanasia","Atanasie","Atanasij","Atanasija","Atanasije","Atanasio","Atan????","Atan????ka","Atan??z","Atan??zia","Atan??zie","Atanazja","Atanazy",
            "Atena","Atene","At??n??","Athana","Athanase","Athanasia","Athanasie","Athanasios","Athanasius","Ath??na","Ath??na","Athene","Athina","Atila","Atilla",
            "Atina","Attila","Attilo","Aubin","Aubine","Audra","Audrey","August","Augusta","Augusta","Auguste","Auguste","Augustin","August??n","Augustina",
            "Augustina","Augustina","Augustine","Augustinus","Augusto","Augustus","Augustyn","August??n","Augustyna","August??na","Auguszta","Auni","Aura","Aurea","Aurel",
            "Aur??l","Aurela","Aureli","Aurelia","Aur??lia","Aurelian","Aureli??n","Aur??li??n","Aurelie","Aur??lie","Aurelij","Aurelija","Aurelio","Aurelius","Aurora",
            "Aurora","Aurooe","Austin","Avdon","Avelina","Aveline","Avgust","Avgusta","Avgustin","Avraam","Avraamij","Avram","Avrelij","Avrelija","Avrora",
            "Axel","Aya","Ayleen","Aylin","Babbette","Babeta","Babett","Babette","Bal??zs","Baldassare","B??lint","Baltasar","Baltazar","Baltaz??r","Balthasar",
            "Balthazar","B??ra","Barab??s","Barab????","Barak","Barbara","Barbara","Barb??","Barber","Barbora","Barbra","Barbro","Barna","Barnaba","Barnabas",
            "Barnab??s","Barnab????","Barnab??","Barnaby","Barnard","Barney","Barrabas","Bart","Barth??lemy","Bartholom??us","Bartholom??","Bartholomeus","Bartholomew","Bartolomej","Bartolomij",
            "Bartolomiej","Bartolomij","Basil","Basile","Basil??o","Basileus","Basilij","Basilio","Basilius","Bastian","Bastien","Bazil","Bazyli","Bea","Beat",
            "Beata","Be??ta","Beate","Beato","Beatrica","Beatrice","B??atrice","Beatrisa","Beatrix","Beatriz","Beatrycza","Beatus","Bedros","Bedoich","Bedoicha",
            "Bedoi??ka","Beitris","Bela","B??la","Bila","Belinda","Bell","Bella","Belle","Biloslava","Bilu??e","Ben","Beoadik","Benedek","Benedetta",
            "Benedetto","Benedict","Benedicta","Benedicte","Benedicto","Benedikt","Benedikta","Benedikte","Benedita","Benedykt","Benedykta","Benet","Benett","Bengta","B??ni",
            "Beniamin","Beniamino","Benigna","Benigni","Benita","Benito","Benjamin","Benjam??n","Benj??min","Benk??","Bennet","Bennie","Benno","Benn??","Bennone",
            "Benny","Beno","Ben??","Beoo","Benoit","Benoite","Benon","Berardo","Berenice","B??r??nice","Berenika","Berenike","Berenik??","Bergit","Berit",
            "Bern??","Bernaard","Bernadeta","Bernadette","Bernard","Bernarda","Bernardeta","Bernardetta","Bernardette","Bernardin","Bernardina","Bernardine","Bernardo","Bernardus","Bernard??na",
            "Bernat","Bern??t","Bernhard","Bernharda","Bernhardina","Bernhardine","Bert","Berta","Bertalan","Berte","Bertel","Bertha","Berthe","Berthold","Bertholda",
            "Bertholdine","Bertil","Bertina","Bertold","Bertolda","Bertoldo","Bertram","Bertr??n","Bertrand","Bertrando","Beryl","Beryll","Bess","Bessie","Bessy",
            "Bita","Betty","Bianca","Bianca","Bianka","Bibi","Bibiana","Bibi??na","Bibijana","Bijanka","Birgid","Birgit","Birgita","Birgitt","Birgitta",
            "Birgitte","Bistar","Bivoj","Bjela","Blago","Blagomila","Blagomir","Blagomira","Blagoslav","Blahomil","Blahomila","Blahom??r","Blahom??ra","Blahoslav","Blahoslava",
            "Blahu??e","Blaise","Blanch","Blanche","Blanka","Blase","Blasios","Blasius","Bla??ej","Bla??eja","Bla??ejka","Bla??en","Bla??ena","Bogdan","Bogdana",
            "Bogislav","Bogislava","Bogohval","Bogoljub","Bogomil","Bogomila","Bogomir","Bogomira","Bogoslav","Bogoslava","Bogo??","Boguchval","Boguchwal","Bogumil","Bogumila",
            "Bogumila","Boguslav","Boguslava","Bogusl??vo","Boguslaw","Boguslawa","Bogusz","Bohdan","Bohdana","Bohdanka","Bohomil","Bohuchval","Bohumir","Bohumil","Bohumila",
            "Bohum??r","Bohum??ra","Bohun","Bohuna","Bohunka","Bohuslav","Bohuslava","Bohu??","Bohu??e","Bochmir","Bojan","Bojana","Boj??na","Bojeslav","Bojislav",
            "Bojislava","B??jka","Bojm??r","Boldizs??r","Bolek","Bolem??r","Boleslav","Boleslava","Boleslaw","Boleslawa","Boljemir","Boljeslav","Bona","Bonifac","Bonif??c",
            "Boniface","Bonifacij","Bonifacio","Bonifacius","Bonifacy","Bonifatij","Bonifatius","Bonifaz","Borb??la","Borek","Borimir","Borim??r","Boris","Borisav","Borislav",
            "Borislav","Borislava","Borislava","Borisz","Borjan","Borko","Borna","Boro","Borys","Boryslaw","Boryslawa","Borzyslaw","Booek","Booislav","Booivoj",
            "Bozsid??r","Bo??ana","Bo??ena","Bo??ena","Bo??etech","Bo??etich","Bo??eticha","Bo??eticha","Bo??idar","Bo??idara","Bo??ika","Bo??in","Bo??ina","Bo??islav","Bo??islava",
            "Bo??ko","Brandon","Branija","Branimer","Branimir","Branim??r","Branimira","Branim??ra","Branisav","Branislav","Branislava","Branka","Branko","Bratislav","Bratislava",
            "Brenda","Brendan","Bret","Bretislav","Brett","Brian","Briano","Bridget","Brighid","Brigida","Brigit","Brigita","Brigitta","Brigitte","Brit",
            "Brita","Britanij","Britt","Britta","Brjaeislav","Brjaeislava","Brooa","Bronik","Bronia","Bronislav","Bronislava","Bronislaw","Bronislawa","Bronislawa","Bronja",
            "Bruce","Bruna","Brungilda","Brunhild","Brunhilda","Brunhilde","Bruno","Br??n??","Brunon","Bryan","Brygida","Boecislava","Boetislav","Boetislava","Boetislava",
            "Budimil","Budim??r","Budislav","Budivoj","Budzimir","Budzislaw","Burga","Burghild","Burghilde","Burglind","Burglinde","Burgunda","Burgunde","Bystr??k","Cacilie",
            "Caecilia","Caesar","Caetano","Caitlin","Cajetanus","Calipso","Calum","Calypso","Cameron","Camila","Camill","Camilla","Camille","Camillo","Camillus",
            "Camilo","CamiUe","Canute","Cara","Carina","Carine","Carl","Carla","Carleen","Carli","Carlina","Carlo","Carlos","Carlota","Carlotta",
            "Carmel","Carmela","Carmen","Carol","Carol","Carola","Carole","Carolin","Carolina","Caroline","Carolyn","Casandra","C??sar","Casey","Casey",
            "Casiano","Casimir","Casimiro","Caspar","Cassandra","Cassandre","Cassian","Cassie","Cassio","Cassius","Castor","C??stor","Castore","C??stulo","Castulus",
            "Catalina","Catarina","Caterina","Catharina","Catherine","Cathia","Catia","Catriona","Cecil","Cecile","C??cile","Cecilia","Cec??lia","Cecili??n","Cecilie",
            "Cec??lie","Cecilija","Cecilio","C??cille","Cecily","Cecylia","Cecyliusz","Cedric","C??dric","Cedrik","C??lanie","Celany","Celestin","Celest??n","Celestina",
            "Celest??na","Celestine","Celestino","Celestyn","Celest??n","Celestyna","Celest??na","Celesztin","Celesztina","Celia","C??lia","Celie","Celina","Celini","C??line",
            "Cenzo","Cesar","Cesare","C??sare","C??sareo","Ceslava","Cestimira","Cezar","C??zar","C??z??r","Cezariusz","Cezary","Cilla","Cinda","Cindy",
            "Cinisula","Cintia","Cinystina","Cipri??n","Cipriano","Cirila","Cirilija","Cirilla","Cirillo","Cirilo","Claire","Clara","Clarika","Clarissa","Clarisse",
            "Clark","Claoe","Claud","Claude","Claude","Claudia","Claudie","Claudina","Claudio","Claudius","Clemens","Clement","Cl??ment","Clemente","Clementia",
            "Clementina","Clementine","Cleopatra","Clotario","Clothilde","Clotilda","Clotilde","Coleman","C??lestin","C??lestine","C??lestinus","Coleta","Coletta","Colette","Colin",
            "C??lina","Colman","Coloman","Colomba","Colombano","Colombe","Colombina","Colombino","Colombo","Colum","Columba","Columban","Columbanus","Columbia","Columbina",
            "Columbine","Cona","Conner","Connor","Conrad","Conrado","Conroy","Constanca","Constance","Constancia","Constans","Constansa","Constant","Constanta","Constantin",
            "Constantine","Constantino","Constantinus","Constanza","Constanze","Constanzo","Consuela","Cora","Cordelia","Cord??lie","Cordula","C??rdula","Cordule","Corina","Corinna",
            "Corinne","Cormac","Corneille","Cornelia","Corn??lie","Cornelio","Cornelis","Cornelius","Corona","Corrado","Cosme","Cosmo","Craig","Cr??pin","Crescentia",
            "Crispin","Crisp??n","Crispino","Crispinus","Crispus","Cristalina","Cristian","Cristi??n","Cristiano","Cristina","Cristinha","Cristino","Cristo","Cristobal","Cristofor",
            "Cristoforus","Ctibor","Ctibora","Ctim??r","Ctirad","Ctirada","Ctislav","Ctislava","Cunegond","Cunegonda","Cun??gonde","Cunegunda","Cunegundes","Curd","Curt",
            "Curzio","Cveta","Cvetan","Cvetana","Cvetimira","Cvetislav","Cvetislava","Cvetomir","Cvetomira","Cvetoslava","Cynthia","Cyntia","Cyntie","Cyprian","Cypri??n",
            "Cyprianus","Cyprien","Cyril","Cyrila","Cyrilka","Cyrill","Cyrilla","Cyrille","Cyrillus","Cyrus","Cyryla","Czeslaw","Czeslawa","Easlav","Easlava",
            "Eedomir","Eenik","Eeoka","Eeslav","Eeslava","Eestimir","Eestislav","Eestm??r","Eestm??ra","Eistoslav","Eistoslava","Daan","Dafne","Dafn??","Dafni",
            "Dag","Dagmar","Dagmara","Dagobert","Dagoberto","Dagomaro","Dahl","Dahl","Dahlia","Daisy","Dajana","Dajena","Dalebor","Dalemil","Dalemir",
            "Dalia","D??lia","Dalibor","Dalibora","Dalida","Dalidia","Dalie","Dalija","Dalila","D??lila","Dalimil","Dalimila","Dalim??r","Dalim??ra","Dalma",
            "Dalmacija","Dalmata","Dalmatinka","Dalmazia","Dalmira","Dalm??ra","Damaris","D??maris","Damia","Damiaan","Damian","Dami??n","Damiana","Dami??na","Damiane",
            "Damiano","Damianos","Damianus","Damien","Damijan","Damijana","Damir","Damjan","Damj??n","Damjana","Damon","Dan","Dana","Danae","Dana??",
            "Danai","Danail","Danaila","Danel","Dane??","Danica","Danice","Daniel","D??niel","Daniela","Daniele","Daniella","Danielle","Daniil","Daniila",
            "Danijel","Danijela","Danika","Danila","Danila","Danilo","Danitza","Danka","Danko","Dankrad","Dankret","Danny","Dano","Danu??e","Danuta",
            "Danylo","Daphne","Daphn??","Dara","Darcy","Darek","Darel","Daren","Daria","D??ria","Darie","Darie","Darien","Darij","Darija",
            "Darije","Darina","Darinka","Dario","Dar??o","Darius","D??rius","Dariusz","Darja","D??rj????","Darko","Darleen","Darlena","Darlene","Daro",
            "Darrel","Darrell","Darren","Darrene","Darryl","Daru??e","Darya","Daryl","D????a","D????d","Dave","David","D??vid","Davida","Davide",
            "Davina","Davinia","Davita","Davor","Davud","Davy","Dawid","Dean","Deana","Debora","Deb??ra","Deborah","D??borah","Deidre","Deinela",
            "Deinse","Dejan","Dejana","Delf??na","Delf??ne","Delia","D??lia","Delie","Delija","Dilila","Delphina","Delphine","Demeter","Demeter","Demetria",
            "Demetrio","Demetrios","Demetrius","D??m??trius","Demi","Demjan","D??nes","Denica","Deniel","Denis","Denisa","Denisija","Dennis","Denny","Denys",
            "Deodoro","Dipold","Derek","Derica","Derika","Derrick","Desanka","Desideria","Desiderij","Desiderije","Desiderio","Desiderius","D??sir??","D??sir??e","Desmond",
            "Despina","Ditmar","Detre","Detrich","Ditoich","Deva","Devana","Divana","Dezider","Dezidera","Deziderija","Dezs??","Dezyderia","Dezyderius","Dezydery",
            "Dia","Diana","Diana","Diandra","Diani","Dianne","Dick","Didda","Diderik","Didier","Didrik","Diede","Dieder","Diederik","Diego",
            "Dietbald","Dieter","Diethild","Diethilde","Dietlind","Dietlinde","Dietmar","Dietrich","Dijana","Dimitar","Dimitr","Dimitra","Dimitri","Dimitrij","Dimitrije",
            "Dimitrina","Dimitrios","Dimitris","Dimitrula","Dina","Dinah","Dinka","Dino","Dionisia","Dionisio","Dioniza","Dion??zia","Dionizij","Dionizija","Dionizije",
            "Dionizja","Dionizy","Dionys","Dionysia","Dionysie","Dionysija","Dionysius","Dion??sos","Dion??z","Dion??zie","Dion??zka","Dionyzy","Disma","Dismas","Dita",
            "Ditmar","Ditm??r","Ditta","Ditte","Divi??","Dlugomil","Dlugomir","Dluho??","Dmitrij","Dmitro","DM??ka","Dobra","Dobrava","Dobrivoj","Dobromil",
            "Dobromila","Dobromila","Dobromir","Dobrom??r","Dobromira","Dobrom??ra","Dobrosav","Dobroslav","Dobroslava","Dobroslaw","Dobroslawa","Dobru??e","Doboena","Dolores","Doloris",
            "Dol??resz","Dolor??za","Domenica","Domenico","Domenika","Domingo","Dominic","Dominica","Dominica","Dominick","Dominicus","Dominik","Dominika","Dominikus","Dominique",
            "Dominique","Domniki","Domokos","Domonkos","Don","Dona","Donal","Donald","Donalda","Donaldo","Donall","Donar","Donat","Don??t","Donata",
            "Don??ta","Donatela","Donatella","Donato","Donatos","Donatus","Donica","Donika","Donna","Donnel","Donnell","Donovan","Dora","Dora","Dore",
            "Doreen","Dorett","Dorette","Dorian","Dori??n","Dorijan","Doris","Dorisa","Dorit","Dorita","Dorith","Dorius","Dorka","Dorkas","Dorofeja",
            "Dorota","Dorotea","Doroteja","Dorothea","Dorothy","Dorotka","Dorottya","Dorrit","Doubrava","Doubravka","Draga","Dragan","Dragana","Dragica","Dragimir",
            "Drago","Dragoljub","Dragoljuba","Dragomil","Dragomila","Dragomir","Dragomira","Dragosav","Dragosava","Dragoslav","Dragosl??va","Dragoslaw","Drago??","Dragotin","Dragutin",
            "Dragut??n","Draho3ub","Drahomil","Drahomila","Drahom??r","Drahom??ra","Drahoo","Drahoslav","Drahoslava","Draho??","Drahot??n","Drahotina","Drahu??e","Drogomila","Drogomir",
            "Drogoslawa","Drogosz","D??brava","Dubravka","Duchoslav","Duchoslava","Dulce","Dulcia","Dulcie","Dulcinea","Dulcinela","Dumitru","Duna","Duncan","Dunja",
            "Dustin","Duszan","Du??an","Du??ana","Du??ka","Du??ko","Dylan","Dymitr","Dysmas","Dyzma","D??ani","Ebba","Ebrahim","Ecaterina","Eckbert",
            "Ed","Eda","Eda","Edda","Eddi","Eddie","Eddy","Edeltraud","Edeltrauda","Edeltraude","Edeltrud","Edeltruda","Edeltrude","Edeltrudis","Edgar",
            "Edg??r","Edgard","Edgardo","Edgaro","Edilberto","Edit","Edita","Edith","??dith","Editha","Edmond","Edmonda","Edmondo","Edmund","Edmunda",
            "Edmundo","Edmundus","Edna","Edoardo","Edon","??douard","??douardine","Eduard","Eduarda","Eduarde","Eduarde","Eduardine","Eduardine","Eduardo","Eduardus",
            "Eduino","Edultride","Edvard","Edv??rd","Edvarda","Edviga","Edvige","Edvin","Edv??n","Edvina","Edvino","Edward","Edwarda","Edwardine","Edwige",
            "Edwin","Edwina","Edwine","Edyta","Efi","Efraim","Efrem","Efrosini","Efrosynija","Eftalia","Eftimia","Eftymia","Egbert","Egedij","Egedije",
            "Egid","Egidij","Egidion","Egidius","Eg??dius","Egmont","Egmund","Egon","Egona","Egonda","Egondina","Egonia","Egyed","Eileen","Eirik",
            "Ekaterina","Ekaterini","Ela","??laine","Elea","Eleanor","Eleazar","El??azar","Eleazaro","Electra","Elefterios","Eleftherios","Elektra","Elem??r","Elen",
            "Elena","Eleni","Eleni","Eleonor","Eleonora","Eleonora","Eleonooe","Eleonooe","Eleuterios","Eleutherios","Elfreda","Elfrida","Elfr??da","Elfried","Elfrieda",
            "Elfriede","Elga","Eli","Elia","Eliah","Eliana","Eli??na","Eliane","??liane","Elias","El??as","??li??s","Eliash","Eliasz","Eli????",
            "Eli????ka","Elie","??lie","Eli??cer","Elieser","Eliezer","Eli??zer","Eligio","Elijah","Elij????","Elin","Elina","Elinor","Eliodoro","Eliot",
            "Elis","Elisa","Elisabet","Elisabeta","Elisabeth","??lisabeth","Elisabetha","Elisabetta","Elisavet","Elisaveta","Elise","Elise","??lise","??lis??e","Elisej",
            "Eliseo","Eliseus","Elisha","Elisha","Elissa","Eli??ka","Eliza","Elizabeta","Elizabeth","Elizaveta","Elizej","Elizeus","Elizeusz","Elka","Elke",
            "Ella","Ellena","Elli","Ellina","Ellinor","Elliot","Ellis","Ellyn","Elma","Elmar","Elm??r","Elmer","Elodia","Elodie","Elpida",
            "Elpidia","Elorid","Elsa","Else","Elsie","Elvina","Elvira","Elv??ra","Elwira","Elyssa","Elza","El??bieta","Ema","Emanuel","Em??nuel",
            "Emanuela","Emanuela","Emanuele","Emanuila","Emeram","Emerentia","Emerenz","Emerenzia","??meric","Emerico","Emericus","Emerich","Emerik","Emery","??mery",
            "Emeryk","Emiel","Emil","Emila","Emile","??mile","Emilia","Em??lia","Emilian","Emili??n","Emiliana","Emili??na","Emiliano","Emilie","Emilie",
            "Em??lie","Emilij","Emilija","Emilio","Emiliusz","Emiljan","Emily","Emma","Emmanuel","Emmanuele","Emmanuelle","Emmanuil","Emmeramus","Emmerico","Emmerich",
            "Emmerik","Emmery","Emmi","Emmy","Ena","Endre","End??e","Engelbert","Engelberto","Engracia","Enik??","Enna","Enne","Enni","Enny",
            "Enoch","??nok","Enrica","Enrico","Enrichetta","Enrika","Enrique","Enriqueta","Enriquez","Enzo","En??e","Ephraim","Erardo","??rasme","Erasmo",
            "Erasmus","Erazim","Erazm","Erazma","Erazmus","Erberto","Erhard","Erhardo","Eric","Erica","??rica","Erico","Erich","Erik","Erika",
            "Erina","Ermanno","Ermin","Erm??n","Erna","Ernest","Ernesta","Ernestina","Ernestina","Ernestine","Ernesto","Ernestyna","Ernest??na","Erneszta","Ernesztina",
            "Ern??","Ernst","Ervin","Erv??n","Ervina","Ervina","Ervino","Erwin","Erwina","Erwine","Eryk","Eryka","Erzs??bet","??saie","Esdras",
            "Esfir","Esmeralda","Esm??ralda","Esperanza","Esra","Estanislao","Estanislau","Estatios","Esteban","Estefania","Estel","Estela","Estella","Estelle","Estephania",
            "Estephano","Ester","Estera","Estevan","Estevao","Esther","Estibalitz","Estrella","Eszmeralda","Eszter","Etel","Etela","Etelka","Ethel","Etheldreda",
            "??tienne","Ettore","Eudokie","EUe","EUen","Eufemia","Euf??mia","Eufemie","Eufrosina","Eufrosyne","Eufrozina","Eufroz??na","Eufrozyna","Eugen","Eugene",
            "Eugenia","Eug??nia","Eugenie","Eug??nie","Eugenio","Eugenius","Eugeniusz","Eulaalia","Eulalia","Eul??lia","Eulalie","Eul??lie","Eunice","Eunie","Eunika",
            "Eunike","Euphemia","Euph??mie","Euphrosine","Euphrosyne","Euridika","Eurydice","Eus??be","Eusebia","Eus??bia","Eusebie","Eusebij","Eusebija","Eusebije","Eusebio",
            "Eusebius","Eus??bius","Euthalia","Euvaldo","EUy","Euzebia","Euz??bia","Euzebiusz","Eva","??va","Evald","Evaldo","Evalyn","Evan","Evangelia",
            "Evangelina","Evangelina","Evang??lina","Evangeline","Evangelista","Evangelos","Evarist","??variste","Evaristo","Evaristus","Evariszt","Evdokia","Evdokie","Eve","Evelia",
            "Eveliina","Evelin","Evelina","Evelina","Eveline","Evelio","Evelyn","Evelyna","??velyne","Evgen","Evgenija","Evita","Evlalija","Evrydiki","Evthalia",
            "Evtimia","Ev??en","Ev??ena","Ev??enie","Ewa","Ewald","Ewaryst","Ewelina","Ewen","Eyjenia","Ezdr????","Ezdre????","Ezechiel","Ez??chiel","Ezechiele",
            "Ezechiello","Ezekiel","Ez??kiel","Ezequiel","Ezra","Fabia","Fabian","Fabi??n","F??bi??n","Fabiana","Fabi??na","Fabiano","Fabianus","Fabie","Fabien",
            "Fabienne","Fabij","Fabijan","Fabijana","Fabio","Fabiola","Fabius","Fabrice","Fabricia","Fabr??cia","Fabricie","Fabricien","Fabricio","Fabricius","Fabr??ciusz",
            "Fabrizia","Fabrizio","Faddej","Fadej","Fanny","Fantino","Farran","Farren","Fatima","F??tima","Fauni","Faust","Fausta","Faustin","Faustina",
            "Faustina","Faustine","Faustino","Faustinus","Fausto","Faustus","Faustyn","Faust??n","Faustyna","Faust??na","Fausztina","Fausztusz","Favij","Favst","Favstina",
            "Fay","Faye","Feba","F??ba","Febe","Febronia","Fedderik","Federica","Federico","Fedor","Fedora","Fed??ra","Fedosij","Felice","Felicia",
            "Fel??cia","Felician","Felici??n","Feliciana","Felicie","Fel??cie","F??licie","Felicija","Felicita","Felicitas","Felicity","Felicja","Felicjan","Felicjana","Feliks",
            "Feliksa","Felipa","Felipe","Felix","F??lix","Felixa","Felizia","Felizitas","Fenella","Feodor","Feodora","Feodosij","Feodosija","Feofan","Feofil",
            "Feofila","Ferdenande","Ferdinand","Ferdin??nd","Ferdinanda","Ferdinande","Ferdinando","Ferdynand","Ferdynanda","Ferenc","Fernand","Fernanda","Fernande","Fernando","Fidele",
            "Fidel","Fid??l","Fidela","Fidelia","Fid??lia","Fidelij","Fidelija","Fidelio","Fidelis","Fidelius","Fidelle","Filbert","Filberto","Filemon","Filem??n",
            "Filemone","Filiberto","Filip","Filipa","Filipina","Filipina","Filipp","Filippa","Filippija","Filippo","Filippos","Filippus","Filomen","Filom??n","Filomena",
            "Filom??na","Filomeno","Fina","Finbar","Fine","Finella","Finette","Finn","Finna","Finne","Finni","Fiona","Fionn","Fionnuala","Fiva",
            "Fjodor","Fjokla","Flavia","Fl??via","Flavian","Flavi??n","Flaviano","Flavie","Flavien","Flavij","Flavija","Flavio","Flavius","Fl??vius","Flawia",
            "Flawian","Flawiusz","Fldelie","Fleur","Floortje","Fl??ra","Florance","Florence","Florencia","Florencij","Florencio","Florentia","Florentij","Florentin","Florent??n",
            "Florentina","Florent??na","Florentine","Florentino","Florentinus","Florentius","Florentyn","Florent??n","Florentyna","Florent??na","Floria","Florian","Flori??n","Fl??ri??n","Floriana",
            "Flori??na","Floriano","Florianus","Florijan","Florin","Floyd","Fodor","Foma","Fomaida","Forest","Forrest","Fortunat","Fortun??t","Fortunato","Fortunatus",
            "Fortunio","Fotini","Franca","Frances","Francesca","Francesco","Francine","Francis","Francisca","Francisco","Franciscus","Franciska","Francisque","Franciszek","Franciszka",
            "Franco","Francois","Francoise","Franjo","Frank","Franka","Franko","Frans","Franti??ek","Franti??ka","Franz","Franzine","Franziska","Franziskus","Fred",
            "Fredderik","Freddi","Freddy","Frederic","Fr??d??ric","Frederica","Frederick","Fredericus","Frederik","Frederika","Frederikke","Fr??d??rique","Fredrik","Fredrika","Fredrikke",
            "Freya","Frida","Fr??da","Friderika","Fridolin","Fridol??n","Fridolino","Fridrich","Frieda","Friederika","Friederike","Friedrich","Frigyes","Fritz","Frosyna",
            "Fryderik","Fryderyka","F??l??p","Gabi","Gabin","Gabina","G??bina","Gabinio","Gabino","Gabinus","G??bor","Gabri??l","Gabriel","Gabriela","Gabriele",
            "Gabriele","Gabriella","Gabrielle","Gabriello","Gabrijel","Gabrina","Gaby","Gaea","Ga??tan","Gaetana","Ga??tane","Gaetano","Gaia","Gaius","Gaj",
            "Gaja","Gajana","Gaj??na","Gajan??","Gajanija","Gal","G??l","Gala","Galatea","Galat??e","Galateia","Galatia","Galia","Galina","Gall",
            "Gallien","Gallo","Gallus","Galo","Galus","Ganna","Gareth","Garik","Garika","Garrick","Gary","Gaspar","G??sp??r","Gaspard","Gaspare",
            "Gaston","Gastone","Gaszton","Gautier","Gavin","Gavriil","Gavril","Gavrilo","Gavrina","Gawel","Gayetano","Gedeon","G??d??on","Gedeone","Geertruda",
            "Geertrudis","Geertruida","Gejza","Gell??rt","Geltrude","Gema","Gemma","Genadij","Genadije","Genadio","Genciana","Genevieve","Geneviive","Gennadij","Gennadio",
            "Gennadius","Genovefa","Genov??fa","Genoveffa","Genoveva","Genov??va","Genoweffa","Genrietta","Genrich","Gentiane","Genziana","Geoffrey","Georg","Georga","George",
            "Georges","Georgette","Georgi","Georgia","Georgij","Georgija","Georgije","Georgina","Georgina","Georgine","Georgios","Georgius","Gerald","Geralda","Geralde",
            "G??ralde","Geraldina","Geraldine","G??raldine","Geraldo","Geraldus","Gerard","G??rard","Gerarda","Gerardina","Gerardo","Gerardus","Gerasim","Geraszim","G??raud",
            "Gerazim","Gerd","Gerda","Geremia","Gerga","Gergana","Gergely","Gergina","Gerhard","Gerharda","Gerharde","Gerhardina","Gerhardine","Gerlach","Gerlind",
            "Gerlinda","Gerlinde","Germaine","German","Gerold","Geronimo","Gerret","Gershom","Gershon","Gerson","Gert","Gerta","Gertie","Gertraud","Gertraude",
            "Gertrud","Gertr??d","Gertruda","Gertr??da","Gertrude","Gerwald","Geza","G??za","Gheorge","Gheorghe","Ghita","Giacint","Giacinto","Giacobbe","Giacomo",
            "Giada","Gianni","Giannina","Gideon","Giko","Gil","Gilbert","Gilberta","Gilberte","Gilberto","Gilbertus","Giles","Gilles","Gina","Gine",
            "Gineta","Ginette","Ginglielmo","Ginlia","Gino","Ginseppa","Giobbe","Gioele","Giona","Gionata","Giordano","Giorgio","Giorgos","Giovanna","Giovanni",
            "Giralda","Girolamo","Gisbert","Gisela","Giselbert","Gisele","Gisella","Gita","Gitta","Gitte","Giuditta","Giuliana","Giuliano","Giulio","Giuseppe",
            "Giuseppina","Giustina","Giustino","Gizela","Gizella","Gjunter","Gladys","Gleb","Glen","Glenn","Gloria","Gloria","Glorie","Glorija","Glory",
            "Godard","Godardo","Godart","Goddard","Goddart","Goran","G??ran","Gorana","Goranka","Gorazd","Gordana","Gordina","Gordon","Gorimira","Gortenzija",
            "Gosciwit","Gostimir","Gostimira","Gostislav","Gostislava","Go??cimir","Go??cislaw","Gotard","Gotardo","Gothard","Goth??rd","Gottdank","Gottfried","Gotthard","Gottlieb",
            "Grace","Gr??ce","Gracia","Gr??cia","Gracian","Graci??n","Graciana","Graci??na","Graciane","Graciano","Gr??cie","Gr??cie","Graciela","Gracija","Gracija",
            "Gracijan","Gracja","Gracjan","Gracjana","Gradita","Grant","Gratia","Gratian","Gratianus","Grazia","Grazian","Graziano","Graziella","Gra??ina","Gra??yna",
            "Greger","Gr??goire","Gregor","Gregoria","Gr??goria","Gr??goriane","Gregorina","Gregorio","Gregorius","Gregoros","Gregory","Greta","Gr??ta","Grete","Gretha",
            "Grethe","Gretchen","Greti","Grigor","Grigore","Grigorena","Grigorij","Grigorija","Grigorina","Griseld","Griselda","Griseldis","Grizelda","Grizeldisz","Grozdan",
            "Gryzelda","Grzegorz","Gualterio","Gualterus","Gualtiero","Gubert","Gudrun","Guendalina","Guglielma","Guglielmina","Gui","Guido","Guilherme","Guillaume","Guillelmina",
            "Guillelmine","Guillermo","Guinevere","Gujd??","Gulliver","Gunar","Gunnar","Gunter","Gunter","Guntero","Gunther","Gun?her","Guntherus","Guntrun","Gustaaf",
            "Gustaf","Gustav","Gustav","Gustava","Gustava","Gustave","Gustavo","Gustavus","Gustaw","Gustawa","Gustina","Gust??na","Gust??na","Guszt??v","Guy",
            "Gvenda","Gvendolina","Gvendol??na","Gvido","Gweanel","Gwen","Gwenda","Gwendolen","Gwendolin","Gwendoline","Gwendolyn","Gwido","Gwidon","Gyorgy","Gy??zo",
            "Gyula","Hadewig","Hadri??n","Hadriana","Hadrianna","Hagar","Haide","Haidi","Haidrun","Hajnal","Hajnalka","Halija","Halina","Halka","Halyna",
            "Hamilton","Hamis","Hana","Hanele","Hanelora","Hanelore","Hanerosa","Hanka","Hanna","Hannah","Hanne","Hannele","Hannelore","Hannerose","Hanni",
            "Hans","Hanu??","Hanu??e","Hanu??ka","Harald","Harbert","Hardi","Hardy","Harieta","Harley","Harold","Harriet","Harry","Harun","Ha??tal",
            "H??ta","Havard","Havel","Havla","Hector","Heda","Hedda","Hedvig","Hedviga","Hedviges","Hedvika","Hedwig","Hedwige","Heidelinde","Heidemarie",
            "Heidi","Heidrun","Heike","Heike","Heiko","Heimeran","Heinrich","Heinz","Hektor","Helen","Hel??n","Helena","Heleni","H??lene","Helga",
            "Heliodor","Hellmuth","Helmut","Helmuth","Heloise","H??loise","Hendrik","Hendrika","Hendrike","Henni","Henny","Henri","Henricius","Henricus","Henrieta",
            "Henrietta","Henriette","Henrich","Henrijeta","Henrik","Henrika","Henrike","Henrique","Henriqueta","Henry","Henryk","Henryka","H??raud","Herbert","Herberta",
            "Herbertine","Herberto","Heribert","Heriet","Herieta","Herma","Herma","Herman","Herman","Hermaun","Hermina","Hermina","Herm??na","Hermini","Herminia",
            "Hernando","Herta","Hertha","Heoman","Heomana","Heom??nka","Hiacynt","Hieronim","Hieronimo","Hieronimus","Hieronym","Hieronymus","Hilaire","Hilar","Hil??r",
            "Hilaria","Hil??ria","Hilarij","Hilario","Hilarius","Hil??rius","Hil??riusz","Hilary","Hilary","Hilda","Hildi","Hillary","Hillary","Hilm??r","Hiob",
            "Hipolit","Hipolito","Hippolit","Hippolite","Hippolitus","Hippolytus","Hjalmar","Homer","Homer","Hom??r","Homire","Homerus","Honey","Honor","Honorata",
            "Honor??ta","Honor??","Honoria","Honorij","Honorina","Honorine","Honorius","Hon??riusz","Honory","Hor??c","Horace","Horacij","Horacio","Horacjusz","Horacy",
            "Horatio","Horatius","Horst","Hortense","Hortensia","Hortensie","Hortensija","Hortensja","Hortenzia","Hortenzie","Horym??ra","Horymrr","Hooislava","Hostimil","Hostimila",
            "Hostim??r","Hostim??ra","Hostislav","Hostislava","Hostiv??t","Hovard","Howard","Hrabra","Hrabri","Hranisav","Hranislav","Hranislava","Hroznat??","Hubert","Huberta",
            "Huberto","Hubertus","Hubrecht","Hugbert","Hugh","Hugo","Hugo","Hugohn","Hugon","Hugue","Hugues","Humbert","Hvalenko","Hvizdo","Hvezdoo",
            "Hvizdoslava","Hviezdoslav","Hviezdoslava","Hyacint","Hyacinta","Hyacinte","Hyacinth","Hyacintha","Hyacinthe","Hyacinthe","Hyacinthus","Hyazint","Hyazinth","Hyazintha","Hyazinthus",
            "Hynek","Hypolit","Chadwick","Chaim","Chaja","Chanel","Chantal","Chantalle","Charalambos","Charilaos","Charline","Charles","Charlette","Charlie","Charlie",
            "Charlota","Charlotta","Charlotti","Chaya","Chelsea","Cheryl","Chiara","Chimalli","Chloe","Chlo??","Chrabr","Chrabro","Chrabro??","Chraniboj","Chranibor",
            "Chranimir","Chranislav","Chranislava","Chr??tien","Chr??tienne","Chris","Chris","Chris","Christa","Christakis","Christalina","Christel","Christiaan","Christian","Christiana",
            "Christiane","Christianus","Christie","Christie","Christien","Christin","Christina","Christine","Christo","Christoffer","Christofor","Christoph","Christophe","Christopher","Christopherus",
            "Christos","Chrudo??","Chrystian","Chrystja","Chval","Chvalislav","Iain","Iakim","Iballa","Iboja","Ibolja","Ibolya","Ibrahim","Ida","Idaberga",
            "Ide","Idzi","Ieron","Ieronim","Ign??c","Ign??c","Ign??ce","Ign??cia","Ign??cie","Ignacij","Ignacio","Ignacja","Ignacy","Ignat","Ign??t",
            "Ign??ta","Ignatia","Ignatij","Ignatius","Ignaz","Ignazio","Ignez","Igor","Iisus","Ika","Iker","Ikerne","Ilarij","Ilario","Ilarion",
            "Ildefonso","Ildico","Ildika","Ildiko","Ildik??","Ileana","Ilian","Iliana","Ilias","Ilija","Ilija","Ilijana","Ilike","Ilja","Ilja",
            "Iljana","Iljina","Ill??s","Illja","Ilona","Ilonka","Ilsa","Ilza","Ima","Imanuil","Imelda","Imma","Immanuel","Immanuela","Immanuil",
            "Imre","Imrich","Ince","Ines","In??s","Inis","Inesa","Inessa","Inez","In??z","Inga","Inge","Ingeborg","Ingeborga","Ingrid",
            "Ingrida","Ingryda","Ingvar","Ingwar","Inigo","Ioigo","Inka","Inken","Inna","Innes","Innocent","Innocentius","Innocenty","Innokentij","Innozenz",
            "Inocenc","Inocencio","Inocent","Inocentius","Inocenzo","Inoeent","Inokentij","Inokentije","Ioakim","Ioan","Ioann","Ioanna","Ioannes","Ioannis","Ioil",
            "Iolanthe","Iona","Ionas","Iordan","Iosif","Ioulia","Ippolit","Ira","Ira","Iraida","Iren","Irena","Irena","Iren??us","Ireni",
            "Ireni","Ir??n??","Irine","Ir??n??e","Ir??n??e","Ireneo","Ireneus","Ireneusz","Irijan","Irin","Irina","Irineu","Irinij","Iris","??risz",
            "Irma","Irme","Irmina","Irvin","Irvine","Irving","Irwin","Isa","Isaac","Isaak","Isaakij","Isabel","Isabela","Isabella","Isabelle",
            "Isacco","Isadora","Isaia","Isaiah","Isaias","Isaja","Isbel","Isidoor","Isidor","Isidora","Isidore","Isidoro","Isidorus","Isis","Iskandar",
            "Ismaele","Isobel","Isolda","Isolde","Israel","Istv??n","Iuda","Iuliain","Iustini","Iva","Ivain","Ivajlo","Ivan","Iv??n","Ivana",
            "Ivana","Ivanka","Ivar","Ivauna","Iver","Iv??s","Iveta","Ivetta","Ivette","Ivica","Ivka","Ivo","Iv??","Ivona","Ivoni",
            "Ivonn","Ivonne","Ivor","Ivo??","Iwa","Iwan","Iwana","Iwo","Iwona","Izaak","Izabel","Izabela","Izabell","Izabella","Izai??s",
            "Izai????","Izaija","Izajasz","Iz??k","Iz??k","Izidor","Izidora","Izid??ra","Izmael","Izmail","Izolda","Izs??k","Izydor","Izydora","Jaakob",
            "Jacek","Jacenty","Jacinto","Jack","Jacob","Jacoba","Jacobina","Jacobine","Jacobo","Jacopo","Jacquelina","Jacqueline","Jacquelyn","Jacques","Jade",
            "Jadon","Jadrana","Jadranka","Jadviga","Jadwiga","Jadyn","Jago","Jahj??","J??chym","Jaime","Jakab","Jake","Jakim","Jakiv","Jakob",
            "J??kob","Jakoba","Jakobe","Jakobina","Jakobine","Jakov","Jakovos","Jakub","Jakuba","Jakubka","Jakup","Jalmari","Jamaica","James","Jamie",
            "Jamin","Jan","J??n","Jana","Jane","Janek","Janella","Janelle","Janet","Janetta","Janette","Janica","Janice","Janick","Janika",
            "Janina","Janis","Janka","Janne","Jannick","Jannik","Jannis","Jano??","J??no??","Jans","Jantra","Janusz","Jar","Jara","Jarema",
            "Jari","Jarina","Jarmil","Jarmila","Jarmilo","Jarol??ma","Jaromil","Jaromila","Jaromila","Jarom??r","Jarom??r","Jarom??ra","Jaromirka","Jarosav","Jaroslav",
            "Jaroslava","Jaroslaw","Jaroslaw","Jaroslawa","Jarounra","Jaru??e","Jas","Jasan","Jasana","Jasen","Jasenka","Jasmin","Jasmina","Jasm??na","Jasm??ne",
            "Jasna","Jasna","Jasnina","Jason","Jasoo","Jas??n","Jasper","Javier","Jayme","Jayne","J??zmin","Jazmina","Jean","Jean","Jeanette",
            "Jeanne","Jeannette","Jeannie","Jeannine","Jed","Jedidiah","Jefferson","Jeffrey","Jeffry","Jefim","Jefrem","Jefrosinija","Jefrosinja","Jegor","Jekaterina",
            "Jela","Jeleazar","Jelena","Jelisaveta","Jelisej","Jelizar","Jelizaveta","Jemeljan","Jenica","Jenifer","Jenny","Jen??","Jenovefa","Jenov??fa","Jens",
            "Jentl","Jeorjia","Jeremej","Jeremi","Jeremia","Jeremiah","Jeremiasz","Jeremi????","Jeremi????","Jeremi????","Jeremi????","J??r??mie","Jeremija","Jeremy","Jergu??",
            "Jerome","J??rome","Jeromos","Jeronim","Jeronimo","Jer??nimo","Jeron??m","Jeron??m","Jerry","Jerzy","Jesika","Jessamine","Jesse","Jessica","Jessie",
            "Jessika","Jeunifer","Jeva","Jevdokija","Jevgen","Jevgenij","Jevgenoja","Jevlalija","Jevnika","Jevnikija","Jevsevija","Jezekija","Jil","Jilj??","Jill",
            "Jilliana","Jim","Jimmy","Jimram","Jindra","Jindra","Jindoich","Jindoi??ka","Jinke","Jinke","Jio??","Jioina","Jitka","Joachim","Joakim",
            "Joanico","Joanis","Joann","Joanna","Joannis","Joannus","Jo??o","Joaqinm","Joaqu??n","Job","J??b","Jocelyna","Jocelyne","Jodie","Jodocus",
            "Jodokus","Jody","Joel","Joelle","Johan","Johana","Johanka","Johann","Johanna","Johannus","Johaunes","John","Johnny","Johnson","Jola",
            "Jolan","Jolana","Jolanda","Jolanta","Jolante","Jolantha","Jolanthe","Jolie","Jolla","Jon","Jona","Jonah","Jonas","J??n??s","Jonasz",
            "Jon????","Jon????","Jonatan","Jonat??n","Jonathan","Jord??n","Jord??na","Jord??na","Jordanka","Jordanus","Jorga","Jorge","J??rgen","Jorgina","Jorgos",
            "Jori","Jorik","Jorika","Joriko","Joris","Jorjia","J??rn","Jos??","Josef","Josefa","Josefina","Josef??na","Josefine","Joseph","Josepha",
            "Josephina","Josephine","Jos??phine","Josephus","Joshua","Joschua","Josif","Josifa","Josip","Josipa","Josse","Jost","Josua","Josu??","Jo??t",
            "Jovan","Jovana","Jovanka","Joy","Joyce","Joyce","Jozef","J??zef","Jozefa","J??zefa","Jozefina","Jozefina","Jozefina","Jozephina","J??zsef",
            "J??zsua","Jozua","Jozue","Jorgen","Juan","Juana","Juda","Juda","Judah","Jud????","Judd","Judif","Judit","Judita","Judith",
            "Judy","Judyta","Jule","Jules","Julia","Julia","Juliaan","Julian","Juli??n","Juliana","Juli??na","Juliane","Julianija","Julianna","Juliano",
            "Julianus","Juli??nusz","Julie","Julien","Julienne","Juliet","Juliette","Julij","Julij","Julijan","Julijana","Julio","Julius","Julius","Juliusz",
            "Juloja","Julyan","Julyna","Jura","Juraj","Juraja","Jur????","J??rgen","Jurij","Justian","Justi??n","Justin","Justin","Justina","Justina",
            "Justina","Justine","Justinian","Justini??n","Justinianus","Justino","Justinus","Justus","Just??n","Just??n","Just??na","Just??na","Jusuf","Jusztin","Jusztina",
            "Juta","Jutka","Jutta","Jutte","Juzef","Juzefa","Kaelin","Kaelyn","Kai","Kai","Kaila","Kain","Kaina","Kaitrin","Kaj",
            "Kaj","Kajet??n","Kajet??n","Kajet??na","Kajet??na","Kajna","Kaliman","Kalina","Kalipsz??","K??lm??n","Kalypso","Kameron","Kamil","Kamila","Kamill",
            "Java","Konstanse","Konstantin","Konstantin","Konstantina","Konstantina","Konstantine","Konstantinos","Konstanty","Konstantyn","Konstant??n","Konstant??na","Konstanze","Konstjantyn","Konsuela","Konzuela",
            "Kora","Kordelia","Kord??lia","Kordula","Korina","Korinna","Kornel","Kornel","Korneli","Kornelia","Korn??lia","Kornelie","Korn??lie","Kornelij","Kornelija",
            "Kornelius","Korneliusz","Kornilij","Koryna","Korynna","Kosma","Kosmas","Kostadin","Kostandin","Kostantin","Kozma","Kozmas","Kr??sa","Krasava","Krasena",
            "Krasimir","Krasim??r","Krasimira","Krasislav","Kr??sna","Kr??sno","Krasnoj","Krasnomir","Krasnomira","Krasomil","Krasomila","Krasom??ra","Krasoslav","Krasoslava","Krescencia",
            "Krescencie","Kreszcencia","Kre??imir","Krispin","Krispus","Krista","Kristen","Kristen","Kristi","Kristi??n","Kristi??n","Kristi??na","Kristi??na","Kristijan","Kristin",
            "Kristina","Kristina","Kristini","Kristof","Kristof","Krist??f","Kristofer","Kristoffer","Kristy","Krist??na","Kriszti??n","Krisztina","Kri??pin","Krstina","Kryspin",
            "Krystian","Krystiana","Krystyn","Krystyna","Kry??p??n","Kry??tof","Krzesimir","Krzysztof","Koesomysl","Koi???an","Koi???ana","Ksaverij","Ksaverije","Ksawera","Ksawery",
            "Ksenia","Ksenija","Kunegunda","Kunhuta","Kunigonde","Kunigunda","Kunigunde","Kuprijan","Kurt","Kuzma","Kventin","Kvita","Kv??tan??","Kvetava","Kvitomil",
            "Kvitomila","Kvitom??r","Kvetoo","Kvitoo","Kvitoslav","Kvitoslav","Kvitoslava","Kvitoslava","Kvito??","Kvitu??e","Kvido","Kvinton","Kvirin","Kvirin","K??la",
            "K??le","K??le","Kyriak","Kyriaki","Kyrill","Kyrillus","Kyrillus","Lada","Ladana","Ladina","Ladim??ra","Ladislao","Ladislas","Ladislau","Ladislaus",
            "Ladislav","Ladislava","Ladiszla","Laika","Ladom??r","Laetitia","Laila","Lajla","Lajos","Lambert","Lamberto","Lambrini","Lamprecht","lan","Lana",
            "Lara","Larina","Larisa","Larissa","Lars","Larysa","L??szl??","L??titia","Laur","Laura","Laure","Lauren","Laurenc","Laurence","Laurence",
            "Laurencia","Laurencie","Laurencij","Laurencije","Laurencio","Laurencja","Laurencjusz","Laurens","Laurent","Laurentia","Laurentin","Laurentin","Laurentino","Laurentius","Laurenty",
            "Laurent??n","Laurenz","Laurette","Laurids","Laurie","Laurits","Laurus","Lavra","Lavrentij","Lavrentije","Lavrin","Lavro","Lawrence","Layla","Lazar",
            "Lazar","L??z??r","Lazare","L??zaro","Lazaros","Lazarus","Lazarz","Lazzaro","Lea","L??a","Leah","Leandar","Leandr","Leandra","L??andre",
            "Leandro","Leandru","Leda","Leda","Lee","Lee","Leila","Leilah","Leilani","Leja","Lejla","Len","Lena","L??na","Lene",
            "Lenek","Leni","Lenica","Lenka","Lenke","Lenko","Lennart","Lenny","Lenta","Leo","Le??","Leocadia","L??ocadie","Leodegar","Leodegario",
            "Leokadia","Leok??dia","Leokadie","Leok??die","Leokadija","Leon","Leon","Leon","Leona","Leona","Leonard","Leonard","L??onard","Leonarda","Leonardo",
            "L??once","Leoni","L??one","Leonela","Leonella","Leoneta","Leonetta","Leonhard","Leonia","Leonid","Leonidas","Leon??das","Leonid??sz","Le??nides","Leonie",
            "L??onie","Leonija","Leonila","Leonor","Leonora","Leontij","Leontija","Leontije","Leontin","Leont??n","Leontina","Leontina","Leontine","L??ontine","Leontinus",
            "Leontyn","Leont??n","Leontyna","Leont??na","Leopold","Leopold","Leopolda","Leopoldina","Leopoldina","Leopoldine","Leopoldo","Leopoldus","Leopoldyna","Leopold??na","Leo??",
            "Lesana","Lesbia","Leschek","Leszek","Le??ek","Leta","Lethea","Lethia","Letia","Leticia","Let??cia","Leticie","Leticija","Letitia","Letizia",
            "Letta","Letycja","Lev","Levi","Lew","Lewis","Leyla","Lia","L??a","Liam","Li??na","Li??na","Liane","Lianna","Lianne",
            "Libina","Liberta","Libislava","Libor","Libora","Liborio","Liborius","Liboslav","Liboslava","Libussa","Libusza","Libu??a","Libu??e","Lida","L??da",
            "Lidia","L??dia","Lidija","Lidmila","Liesa","Liese","L??f","L??h??na","Lij","Lijana","Lil","Lili","Lili","Lilia","Lilia",
            "Lilia","Lilian","Liliana","Liliane","Lilien","Lilii","Lilija","Lilijana","Lilith","Lilja","Liljan","Liljana","Lillian","Lilly","Lilo",
            "Lilou","Lily","Lina","Linda","Lindi","Lindsay","Lindsey","Line","Lingi","Lino","Lins","Linsbel","Linse","Linton","Linus",
            "Lion","Lionardo","Lionel","Lionello","Lior","Liora","Liran","Lirid","Lirida","Liridon","Liron","Lisa","Lisbet","Lise","Lissa",
            "Lisse","Lita","Litka","Liv","Livia","Livij","Livija","Livije","Livio","Liviusz","Liwia","Liwiusz","Liz","Liza","Ljuba",
            "Ljubana","Ljuben","Ljubica","Ljubim","Ljubina","Ljubislava","Ljubomir","Ljubomira","Ljubomyla","Ljubomyra","Ljuboslav","Ljuboslava","Ljubov","Ljubu??a","Ljucian",
            "Ljucija","Ljudevit","Ljudevita","Ljudi??a","Ljudmil","Ljudmila","Ljudvig","LMa","LMe","LMo","LMus","LMus","lna","Lodevika","Lodewijk",
            "Lodovica","Lodovico","Lodowick","Loic","Loins","Lois","Lois","Lois","Loise","Lola","Lolita","Lona","Lone","Longin","Longino",
            "Loni","Lora","Loraine","L??r??nt","Lore","Loreen","Loren","Lorena","Lorenc","Lorenz","Lorenzo","Loreta","Loretta","Lori","Lorina",
            "L??rinc","L??rinc","Lorna","Lorne","Lorraine","Lota","Lotar","Lot??r","Lotario","Lotari??s","Lothaire","Lothar","Lotharius","Lotta","Lotte",
            "Lou","Lou","Louisa","Louise","Loukia","Loup","Luba","Lubina","Lubica","Lubica","Lubislava","Lubomil","Lubomila","Lubom??r","Lubom??r",
            "Lubom??r","Lubom??ra","Lubom??ra","Lubom??ra","Lubor","Lubor","Lubor","Lubora","Luboslav","Luboslav","Luboslava","Luboslava","Luboslaw","Lubo??","Lubo??",
            "Lubov","Luc","Luca","Lucas","Lucia","Luc??a","L??cia","Lucian","Luci??n","Luciana","Luci??na","Lucianna","Luciano","Lucianus","LucibeU",
            "Lucie","Lucien","Lucieune","Lucija","Lucijan","Lucilla","Lucio","Lucius","L??ciusz","Lucja","Lucjana","Lucrece","Lucrice","Lucrecia","Lucrecio",
            "Lucretia","Lucretiu","Lucretius","Lucrezia","Lucrezio","Lucy","Lueina","Ludik","Ludina","Ludev??t","Ludimir","Ludim??r","Ludislav","Ludi??e","Ludivoj",
            "Ludka","Ludmil","Ludmila","Ludmila","Ludmila","Ludmilla","Ludomila","Ludomir","Ludomir","Ludom??r","Ludomira","Ludomira","Ludom??ra","Ludoslav","Ludoslav",
            "Ludoslava","Ludoslava","Ludoslaw","Ludoslawa","Ludovica","Ludovicus","Ludovika","Ludov??t","Ludov??ta","Ludowic","Ludowick","Ludvig","Ludviga","Ludv??k","Ludvika",
            "Ludv??ka","Ludwig","Ludwiga","Ludwik","Ludwika","Luigia","Luigino","Luisa","Luitpold","Luiz","Luiza","Lujza","Luka","Luka","Luk??cs",
            "Lukan","Lukana","Lukas","Lukasz","Luk????","Luk????ka","Luke","Lukia","Lukija","Lukina","Lukrecia","Lukr??cia","Lukrecie","Lukr??cie","Lukrecij",
            "Lukrecija","Lukrecius","Lukrecja","Lukrecjusz","Lukretia","Lum??r","Lum??ra","Luna","Lune","Lusibell","Lusine","Luther","Lutherus","Lutibor","Lutobor",
            "Luzia","Luz??a","Lydia","L??dia","Lydie","L??die","Lynda","Lynete","Lynn","Lynnette","Mabel","Mabela","Mabelia","Mabella","Mabelle",
            "Mable","Mac","Macaire","Macario","Macarius","Maciej","Mackenzie","MacKenzie","Madalena","Maddalena","Maddison","Maddison","Madeleine","Madeline","Madison",
            "Madison","Madleen","Madlen","Madlena","Madonna","Madrona","Madrun","Maeve","Magda","Magdala","Magdalena","Magdal??na","Magdaleni","Magdalina","Magdalini",
            "Magdolna","Maggie","Magia","Magn","Magne","Magno","Magnus","Magnusz","Mahulena","Mahuliena","Macharius","Mai","Maia","Maible","Maik",
            "Maikel","Mailin","Maina","Maire","Maj","M??ja","M??ja","Majja","Majka","Makar","Mak??r","Makarij","Makarije","Makarius","Makariusz",
            "Makary","Makeda","Maksim","Maksima","Maksimian","Maksimilian","Maksimilijan","Maksimiljan","Maksym","Maksymilian","Malena","Malgorzata","Mali","Malin","Mallory",
            "Mallory","Malva","Malv??n","Malv??na","Malv??na","Malvy","Malwina","Malwine","Mandy","Manfred","Manfr??d","Manfredi","Manfredo","Manfried","Manfroy",
            "Manka","Manogu","Manoila","Manol","Manon","Manona","Mansueto","Mansuetus","Mansvet","Mansv??t","Manszv??t","Manuel","Manuela","Manu??la","Manuele",
            "Manuelle","Manuil","Manus","Mara","Marat","Marc","Marcel","Marcela","Marceli","Marceli","Marcelia","Marcelia","Marcelija","Marcelin","Marcelin",
            "Marcelina","Marcel??na","Marcelle","Marcellina","Marcello","Marcellus","Marcelo","Marcia","Marcin","Marco","Marcos","Marcu","Marcus","Mareike","Marek",
            "Marfa","Marga","Margaret","Margareta","Margareta","Margareti","Margaretha","Margarethe","Margaretta","Margarita","Margherita","Margit","Margita","Margitha","Margitta",
            "Margot","Margre?he","Marguerite","Maria","Maria","Mariamna","Marian","Mari??n","Mariana","Marianna","Marianne","Mariano","Marianus","Maribel","Marica",
            "Maricela","Marie","Mariel","Mariela","Mariella","Marielle","Marien","Marieta","Marietta","Mariette","Marij","Marija","Marijan","Marijana","Marika",
            "Marilla","Marilyn","Marilyne","Marilynn","Marin","Marina","Marina","Marini","Marino","Marinus","Mario","Mario","Mariola","Mariole","Marion",
            "Mariona","Marisa","Marisela","Mariska","Mari??a","Marita","Maritta","Marius","M??rius","Mariusz","M??riusz","Marjana","Marjo","Marjorie","Marjory",
            "Mark","M??rk","Markel","Markell","Markellina","Markelo","Mark??ta","Marko","Markos","Markus","M??rkus","Marleen","Marlen","Marlena","Marlene",
            "Marlene","Marl??ne","Marlon","Maro","Maron","Maro??","Marquita","Marro","Marta","Marta","Marten","Martha","Marthe","Martin","Martina",
            "Martina","Martine","Martinian","Martini??n","Martiniano","Martinka","Martino","Martjan","M??rton","Marty","Martyn","Martyna","Maru??e","Marvin","Mary",
            "Maryla","Maryna","Mary??a","Marzella","Marzellus","Maoen","Mao??","Maoila","Masa","Masha","Mascha","Massimiliano","Massimo","Ma??a","M????a",
            "Ma??enka","M??t??","Matea","Matei","Matij","Matij","Matij","Matij","Matij","Matijka","Matelda","Mateo","Mateusz","Matfij","Mathea",
            "Mathias","Mathie","Mathies","Mathieu","Mathilda","Mathilde","Mathis","Matias","Mat??as","Mati????","Matij","Matija","Matija","Matild","Matilda",
            "Matilde","Mato","Matou??","Matt","Mattea","Matteo","Matthaeus","Matthaus","Matthew","Matthias","Matthieu","Matti","Mattia","Mattias","Mattis",
            "Matts","Mat????","Matvej","Matvij","Matyas","Maty????","Maty????","Maty????ka","Matylda","Maud","Maude","Maudie","Maura","Maurice","Mauricio",
            "Mauriciu","Mauricius","Maur??cius","Mauritius","Maurits","Mauritz","Maurizio","Mauro","Maurycjusz","Maurycy","Mavrikij","Max","Maxi??n","Maxie","Maxim",
            "Maxima","Maxima","Maxime","Maxime","Maximiliaan","Maximilian","Maximili??n","Maximiliana","Maximiliane","Maximiliano","Maximilien","Maximilla","Maximo","Maxine","Maxmilian",
            "Maxmili??n","Maxmili??na","Maxmili??na","Maxwell","May","Maya","Mca","Mckenzie","McKenzie","Meeislav","Meeislava","Meda","Medard","Medard","Medard",
            "Medardo","Medardus","M??dea","M??dea","Mefodij","Meg","Megan","Meggi","Meggie","Meggy","Meghan","Mechthild","Mechtild","Mechtilda","Mechtilde",
            "Melana","Mel??nia","Melanie","Mel??nie","M??lanie","Melanija","Melany","Melchior","Melchiorre","Melchor","Melichar","Melina","Melinda","M??linda","Meline",
            "Melisa","Melissa","M??lisse","Melita","Melitta","Melodie","Melody","Melony","Melpomeni","Melvffle","Melvin","Melvina","Menahem","Menachem","Mendel",
            "Menyh??rt","Mercedes","Mercedes","Merc??desz","Merit","Merita","Merlijn","Merlin","Merlinus","Mersija","Mervin","Mervyn","Mesmin","Mi??ek","Meta",
            "Methodius","Metod","Metod","Metodij","Metodi","Metodij","Metodije","Metodio","Metodiu","Metodius","Metody","Metta","Mette","Mia","Micaela",
            "Micislao","Mick","Mickey","Micky","Mieiko","Miecislao","Miecislas","Miecislav","Mieczyslaw","Mieczyslawa","Mieszko","Miguel","Miguela","Miha??la","Mihai",
            "Micha","Michael","Michaela","Michaela","Michail","Michaila","Michajlina","Michajlo","Michal","Michal","Michala","Michalina","Michalis","Michel","Michela",
            "Michele","Michile","Michelina","Michelle","Michiko","Mija","Mika","Mikael","Mikaela","Mike","Mike??","Mikiko","Mikki","Mikl??s","Mikola",
            "Mikolaj","Mikolaj","Mikol????","Mikul????","Mila","Milada","Miladina","Milan","Mil??n","Milana","Mileo","Mildred","Milen","Milena","Milena",
            "Mileni","Mileva","Milic","Milica","Mil??e","Milij","Mil??k","Milika","Mil??n","Milina","Milivoj","Milivoje","Miljan","Miljana","Milju??a",
            "Miljutin","Milka","Milko","Milli","Milo","Miloje","Milojko","Milom??ra","Miloo","Milorad","Milorad","Miloslav","Miloslava","Miloslaw","Miloslaw",
            "Miloslawa","Miloslawa","Milosz","Milo??","Milo??","Milota","Milota","Milotija","Milou??","Milov??n","Milovan??","Milov??n","Milowan","Milton","Milu??a",
            "Milu??e","Milutin","Milutin","Mimi","Mimmi","Mimosa","Mina","M??na","Minail","Minajlo","Min??ly","Mine","Mine","Minerva","Minka",
            "Minna","Minnie","Mir","Mira","M??ra","Mirabel","Mirabela","Mirabella","Mirabelle","MirabeU","Miran","Miranda","Mirandola","Mireo","Mireille",
            "Mirek","Mirela","Mirella","Miriam","Miriama","Miriamna","Miriel","Miriela","Mirina","Mirjam","Mirjana","Mirka","Mirko","Mirko","Mirna",
            "Miroljub","Miroljuba","Miromil","Miromila","Miron","Mir??n","Miroslaus","Miroslav","Miroslava","Miroslavo","Miroslaw","Miroslaw","Miroslawa","Miroslawa","Mirtil",
            "Mirtill","Mirtilo","M??rumila","Misha","Mi??ela","Mitchell","Mitko","Mito","Mlad","Mlada","Mladan","Mladin","Mladina","Mladot??n","Mnata",
            "Mni","Mnislav","Mnislava","Moana","Modest","Modesta","Modeste","Modeste","Modestia","Modestina","Modesto","Modestus","Modest??na","Modeszta","Modesztusz",
            "Modron","Moinque","Moira","Moise","Moisej","Moises","Mois??s","Mojmir","Mojm??r","Mojm??ra","Mojm??ra","Mojsej","Mojsij","Mojsija","Mojsije",
            "Mojzesz","Moj??????","Momir","Momira","Mona","Monica","M??nica","Monika","Monika","Montana","Monte","Montgomery","Monty","M??r","Morgan",
            "Morgan","Morgana","Morganne","Moric","Moric","Moris","Moritz","Moriz","Morna","Morris","Morten","Mortimer","Morton","Mooena","Mooic",
            "Mose","Moses","Moshe","MoUy","Moyra","Moyse","Moyses","Mozes","M??zes","Mstibor","Mstislaus","Mstislav","Mstislava","Mstivoj","M??cislaw",
            "Mudroslava","Muireall","Muriel","Muriela","Murielle","Musa","Mychajlina","Mychajlo","Mylana","Myloslava","Mylycja","Myriam","Myrna","Myron","Myroslava",
            "Myrta","Myrtea","Myrtha","Myrthe","Myrtil","Myrtille","Myrto","Myslibor","My??libor","Naia","Nad??n","Nadije","Nadeschda","Nadi??da","Nadi??da",
            "Nadia","Nadija","Nadine","Nadinka","Nadja","N??dja","Nadjeschda","Nadzieja","Naila","Naille","Nafanail","Naike","Nancy","N??ndor","Naneta",
            "Nanetta","Nanette","Nannette","Nansi","Naomi","Napoleon","Napoleon","Napoleon","Napoleon","Napoleone","Narcis","Narcis","Narcisa","Narciso","Narcissa",
            "Narcisse","Narcisse","Narcisso","Narcissus","Narcisszusz","N??rcisz","Narcyz","Narcyza","Narek","Narkiss","Narkissa","Nas?a","Nastasia","Nastasija","Nasti",
            "Natacha","Natali","Natalia","Nat??lia","Nat??lie","Nat??lie","Natalija","Natalja","Natan","N??t??n","Natanael","Natanaela","Nataniel","Nataniela","Nataniele",
            "Natasha","Natascha","Nata??a","Nata??a","Nathalia","Nathalie","Nathaly","Nathan","Nathanael","Nathanail","Nathanaela","Nathanaille","Nathaniel","Nathaniela","Nazar",
            "Nazareno","Nazarij","Nea","Neal","Ned","Neda","Nedda","Neela","Neele","Nefel??","Nefely","Negislav","Negosav","Negoslav","Nehoslav",
            "Nihoslav","Neil","Neklao","Nela","Neli","Nelia","Nelie","Nelja","Nell","Nella","Nelli","Nellie","Nelly","Nelson","Nemanja",
            "Nena","Nenad","Nenah","Nenela","Nenella","Nennela","Nennele","Nepomuceno","Nepomuk","Neris","Nerys","Nesta","Nestir","Nestor","Neta",
            "Netta","Nette","Neunella","Neven","Nevena","Nevenka","Nezamysl","Nia","Niamh","Niccolo","Nice","Nicea","Niceta","Nicia","Nick",
            "Nicki","Nicki","Nicky","Nicky","Nico","Nico","Nicodime","Nicodemia","Nicodemo","Nic??demus","Nicodim","Nicol","Nicol","Nicola","Nicola",
            "Nicolaas","Nicolae","Nicolai","Nicolao","Nicolas","Nicol??s","Nicolaus","Nicole","Nicolet","Nicoletta","Nicolette","Nicoline","Nicolle","Nicolo","Niegoslaw",
            "Niels","Niezamysl","Nigel","Nicholas","Nicholaus","Nik","Nika","Nikandr","Nikanor","Nike","Nik??","Nik??t??s","Niki","Niki","Nikifor",
            "Nikit","Nikita","Nikita","Nikki","Niklas","Niko","Nikod??m","Nikod??m","Nikod??ma","Nikod??ma","Nikod??mia","Nikodemus","Nikod??musz","Nikodija","Nikodim",
            "Nikodima","Nikol","Nikol","Nikola","Nikola","Nikolaj","Nikolaos","Nikolas","Nikolaus","Nikoleta","Nikoletta","Nikolina","Nikos","Nil","Nila",
            "Nilla","Nilo","Nils","Nina","Ninel","Nino","Nioba","Niobe","Niobe","Nirai","Nita","Noah","Noach","Noam","Nodin",
            "Noe","No??","Noel","No??l","Noil","Noela","Noelia","Noelia","No??lia","Noelie","Noelie","Noillie","Noema","Noemi","No??mi",
            "Noemie","No??mie","NoiUe","Nona","Nonna","Nora","Nora","Norah","Norberdina","Norbert","Norberta","Norberto","Noreen","Norene","Norik",
            "Norika","Noriko","Norma","Norman","Normand","Normann","Notburga","Octave","Octave","Octavia","Octavian","Octavianus","Octavie","Octavien","Octavio",
            "Octavius","Octavus","Od","Oda","Odana","Odd","Oddone","Ode","Ode","Odeta","Odett","Odetta","Odette","Odile","Odilia",
            "Odilie","Odina","Odmar","Odo","Od??","Odoarda","Odol??n","Odolen","Odoljen","Odon","Od??n","Od??n","Odona","Ofelia","Of??lia",
            "Of??lie","OfeUia","Ofilia","Ofillia","Oksana","Oktavia","Okt??via","Oktavian","Oktavi??n","Okt??vi??n","Oktavie","Okt??vie","Oktavij","Oktavij","Oktavij",
            "Oktavija","Oktavius","Okt??vius","Oktaw","Oktawia","Oktawian","Oktawiusz","Ola","Olav","Olavo","Olbram","Olda","Oldrik","Oldoich","Oldoich",
            "Oldoicha","Oldoi??ka","Oleandra","Oleandrija","Oleg","Oleksandr","Oleksandra","Oleksij","Olena","Olesja","Olga","Olga","Olimpia","Olimpiada","Olimpija",
            "Olin","Olina","Oliva","Olivi","Oliver","Oliv??r","Oliverio","Olivia","Olivie","Olivier","Olivija","Oljana","OlMa","OlManne","OlMe",
            "OlMero","Olof","Oluf","Olu??e","Olympe","Olympia","Olympie","Omar","Om??r","Omer","Omero","Ondra","Ondoej","Ondoej","Ondoejka",
            "Onorata","Onorio","Onysij","Onysyfor","Onysym","Ophelia","Oph??lie","Ora","Orah","Oran","Orazio","Orb??n","Oren","Orest","Oresta",
            "Oreste","Orestej","Orestes","Oreszt??sz","Orf??","Orfea","Orfee","Orfej","Orfeo","Orfeus","Orfeusz","Orchidea","Orchid??e","Ori","Oriana",
            "Ori??na","Oriane","Orianna","Orianne","Orin","Orina","Orit","Orlan","Orlando","Orlin","Ornela","Ornella","Orora","Orph??e","Orsola",
            "Ortensia","??scar","Osip","Osipa","Oskar","Oskar","Oskara","Ostap","Osvald","Osvalda","Osvaldo","Oswald","Oswalda","Oswalde","Oswaldina",
            "Oswell","Oszk??r","Oszvald","Ota","Ota","Otakar","Otakara","O?ho","Othon","Otilia","Ot??lia","Otilie","Otilija","Otmar","Otm??r",
            "Oto","Otokar","Otomar","Oton","Otta","Ottavia","Ottavio","Otte","Ottilia","Ottilie","Ottmar","Otto","Ottocar","Ottocare","Ottokar",
            "Ottok??r","Ottomar","Otton","Ottone","Otylia","Ot??lie","Ovide","Ovidij","Ovidio","Ovidiu","Ovidius","Ov??dius","Ov??diusz","Owen","Owidiusz",
            "Oxana","Ox??na","Ozs??b","Paavo","Pabiana","Pablo","Pacifico","Pacifik","P??drig","Paedar","P??l","Palmer","Palmira","Palmiro","Palm??ro",
            "Palmyra","Paloma","Pal??ma","Pamela","Pamela","Panajot","Panajota","Panajotis","Pancrace","Pancracio","Pancratie","Pancratius","Pandora","Pankr??c","Pankracy",
            "Pankrat","Pankratij","Pankratije","Pankratios","Pankratius","Pankraz","Pankrazio","Pantalejmon","Pantaleon","Pantaleon","Pantal??on","Pantaleone","Paola","Paolina","Paolo",
            "Par","Paraska","Paraskene","Paraskeva","Paraskovija","Parcifal","Parcival","Paride","Paris","Paris","Paris","Parisa","Parker","Parsival","Parzifal",
            "Parzival","Pascal","Pascale","Pascalina","Pascual","Paschalis","Paskal","Paskala","Pask??lia","Pasqua","Pasquale","Pasqualia","Pasqualina","Paszk??l","Patiycjusz",
            "P??tran??","Patrice","Patrice","Patricia","Patricia","Patricie","Patricij","Patricij","Patricije","Patricio","Patricius","Patricius","Patrick","Patrik","Patrika",
            "Patrikij","Patrikija","Patrizia","Patrizio","Patrizius","Patrycj","Patrycy","Patryk","Patty","Paul","Paula","Paule","Paulette","Paulin","Paulina",
            "Paul??na","Paulini","Paullus","Paulo","Paulus","Paval","Pavao","Pavel","Pavla","Pavli","Pavlija","Pavlin","Pavl??n","Pavlina","Pavlina",
            "Pavlo","Pavlos","Pavol","Pawel","Peadair","Pearl","Pedro","Peggy","Pelageja","Pelagia","Pelagie","P??lagie","Pelagija","Pelajia","P??legrin",
            "Pelegrino","P??lerin","Pelgrim","Pelhoim","Pellegrin","Pellegrino","Pena","Penelopa","Penelope","P??nelop??","Penka","Penko","Pentti","Pepino","Peppino",
            "Per","Perceval","Percival","Percy","Peregrin","Peregr??n","P??r??grin","Peregrine","Peregrino","Peregrinus","Peregryn","Perez","Perchta","Pericle","Pericles",
            "Perikles","Perikl??s","Perikl??sz","Perkins","Perla","Perlu??e","Persida","Perside","Persis","Perzida","Petar","Peter","Peter","Pitko","Petr",
            "Petra","Petrana","Petrija","Petrik","Petro","Petromila","Petronela","Petronella","Petronij","Petronije","Petronila","Petronilla","P??tronille","Petronilo","Petronio",
            "Petronius","Petr??nius","Petr??niusz","Petros","Petru","Petrula","Petrumila","Petrunelja","Petrus","Petru??e","Petoi","Petoina","Petoini","Petula","Piva",
            "Pivu??e","Phebe","Phffipp","Philbert","Philemon","Philiberte","Philip","Philippa","Philippe","Philippine","Philippos","Phillip","Philomela","Philomele","Philomena",
            "Philom??na","Philomene","Philomine","Ph??be","Phoebe","Phoenix","Phyllis","Pia","Piero","Pierre","Pierrette","Pietro","Pij","Pijo","Pilao",
            "Pilip","Pina","Pinelopi","Pio","Piotr","Piroska","Pirro","Pius","Piusz","Pjotr","Plato","Platon","Plat??n","Platone","Policarpo",
            "Polikarp","Polik??rp","Polikarpo","Poliksemja","Poliksena","Polina","Polissena","Polixena","Polixeni","Polly","Pollyanna","Polyana","Polycarpe","Polycarpus","Polykarp",
            "Polykarpos","Polyxena","Polyxena","Polyxeni","Polyxeni","Polyxeni","Pomnin","Pomnina","Pomninka","Pongr??c","Pravdoljub","Pravdomil","Pravdomila","Pravislav","Pravoinb",
            "Pravoljub","Pravomil","Pravomila","Pravom??r","Pravoslav","Pravoslava","Predrag","Preslava","Pribisav","Pribislav","Pribislava","Pribyslaw","Primislaus","Primislav","Prisca",
            "Priscila","PrisciUa","Priska","Priskilla","PriskiU","Procope","Procopio","Procopi??s","Prochor","Prokop","Prokopa","Prokopi]","Prokopij","Prokopije","Prokopios",
            "Prokopka","Prosper","Prospero","Pr??spero","Protas","Protasij","Prvoslava","Przemyslaw","Przybyslawa","Poemek","Poemysl","Poemyslav","Poemyslava","Poibyslav","Poibyslava",
            "Pulcherija","Qinnton","Qinrino","Qscar","Quentin","Quido","Quincy","Quinetta","Quinn","Quintilien","Quirin","Quirinus","Qunitana","Racibor","Rada",
            "Radak","Radan","Radana","Rade","Radegast","Radek","Radeka","Raden","Radhost","Radie","Radigost","Radij","Radika","Radim","Radima",
            "Radimil","Radimir","Radim??r","Radimira","Radim??ra","Radin","Radislav","Radislava","Radivoj","Radivoje","Radka","Radko","Radmil","Radmila","Radogost",
            "Radohost","Radoje","Radojica","Radojka","Radola","Radolf","Radoljub","Radomil","Radomil","Radomila","Radomila","Radom??r","Radom??r","Radom??ra","Radom??ra",
            "Radomyr","Radon","Radoslav","Radoslava","Radoslaw","Radoslawa","Rado??","Radou??","Radovan","Radovana","Radovot","Radowan","Radslav","Radslava","Raissa",
            "Raduil","Radul","Radula","Radule","Radu??","Radu??e","Rad??z","Radvan","Radvana","Radv??t","Rae","Rafael","Rafaela","Rafaella","Rafail",
            "Rafaila","Rafal","Rafalyna","Raffaela","Raffaele","Raffaelo","Ragnar","Rahel","R??hel","R??chel","R??chel","Rachela","Rachele","Rachil","Rachila",
            "Rachilja","Raimondo","Raimund","Raimunda","Raimunde","Raimundo","Raina","Rainald","Raine","Rainer","Rainerio","Rainhard","Rainhold","Rainier","Raino",
            "Rainold","Raisa","Raissa","Rajka","Rajko","Rajmond","Rajmonda","Rajmund","Rajmunda","Rajna","Rajnald","Rajnold","Rajsa","Rakela","Ralf",
            "Ralica","Ralph","Raluca","Ramon","Ram??n","Ramona","Ram??na","Ran","Rand??l","Randolf","Randolfo","Randolph","Randulf","Randulv","Randy",
            "Ranek","Ranieri","Ranieri","Ranjo","Ranko","Raoul","Raphael","Raphaela","Raquel","Rastisav","Rastislav","Rastislava","Ratiboo","Ratimir","Ratim??r",
            "Ratislav","Ratmir","Ratm??r","Ratoslav","Raul","Raven","Ray","Rayan","Raymond","Raymonde","Raymundo","Rayna","Rayner","Raynor","Rea",
            "Rebeca","Rebecca","R??becca","Rebeka","Rebekah","Rebekka","Redmond","Redmund","Regan","Regina","Regina","Regina","Reginald","Reginaldo","Regini",
            "R??gine","Regnar","R??gnier","Reik","Reimund","Reinald","Reiner","Reingarda","Reinhard","Reinhilda","Reinhold","Reinold","Reinolf","Reka","Remig",
            "Remigio","Remigius","Remigiusz","Remo","Remus","R??musz","Ren","Rena","Renard","Renat","Ren??t","Renata","Ren??ta","Renati","Renato",
            "Renatus","Renaud","Rene","Ren??","Ren??","Ren??e","Reni","Renia","Reno","Renzo","Reuben","Reweka","Reynaldo","Reynaldos","Reynard",
            "Reynold","Rhea","Rh??a","Rhian","Rhianon","Rhomylos","Rhona","Rh??xan??","Ria","Riana","Rianna","Riano","Ricarda","Ricardo","Ricardus",
            "Riccarda","Riccardo","Rick","Rickard","Ricky","Rico","Rieard","Richard","Richard","Richarda","Richie","Rijk","Rik","Rikard","Rikarda",
            "Rikhard","Riley","Riley","Rina","Rinaldo","Rini","Rino","Rio","Risto","Rita","Ritchie","Riva","Rivka","Rjurik","Rob",
            "Robert","Robert","Roberta","Roberte","Roberto","Robertus","Robin","Robin","Robina","Robini","Robinson","Robrecht","Robyn","Rocco","Rock",
            "Rocky","Roda","Rodan","Roderick","Roderico","Roderich","Roderik","Roderyk","Rodger","Rodolfo","Rodolph","Rodolphe","Rodrigo","Rodrig??","Rodrigue",
            "Roeland","Roger","Rogerio","Rogerus","Roch","Rochus","Rok","Roka","Roko","Roksana","Roksolana","R??kus","Rolan","Rol??n","Roland",
            "Rol??nd","Rolanda","Rolande","Rolando","Rolandus","Roleta","Rolf","Rolfe","Rolin","Rolland","Romain","Romaine","Roman","Rom??n","Romana",
            "Romana","Romanija","Romano","Romanus","Romeo","Romeo","Romi","Romil","Romina","Romolo","Romuald","Romualda","Romualdo","Romul","R??mulo",
            "Romulus","Romulusz","Romy","Rona","Rooa","Ronald","Ronaldo","Ronja","Ronny","Roque","Rory","Rosa","Rosalia","Rosal??a","Rosalie","Java",
            "Rosalind","Rosalinda","Rosalinde","Rosaline","Rosalio","Rosamond","Rosamund","Rosamunda","Rosamunde","Rosana","Rosanna","Rosaria","Rosarie","Rosario","Roscislaw",
            "Rose","Roseanna","Roselind","Rosemarie","Rosemonde","Rosen","Rosetta","Rosette","Rosina","Rosini","Rosita","Rosmunda","Ross","Rossa","Rostislav",
            "Rostislava","Rostyslav","Rostyslava","Roswith","Roswitha","Rovena","Rov??na","Rowdy","Rowena","Rowland","Roxana","Rox??na","Roxane","Roxanna","Roy",
            "Roza","R??za","R??za","Rozalia","Roz??lia","Roz??lie","Rozalija","Rozalinda","Rozamunda","Rozana","Rozanna","Roz??rie","Roz??rio","Roz??rka","Rozaroj",
            "Rozeta","Rozina","Rozina","Rozita","Rozvita","Rozyna","Rre??encija","Ruben","Ruben","Rubin","Ruby","Rudi","Rudiger","Rudina","Rudolf",
            "Rudolfa","Rudolfina","Rudolfina","Rudolfine","Rudolph","Rudolpha","Rudolphe","Rudolphina","Rudolphus","Ruf","Ruf","Rufin","Rufino","Rufinus","Rufus",
            "R??fus","R??fusz","Ruggero","Rumold","Runa","Rune","Rupert","Ruperto","Rupertus","Ruprecht","Rurik","Ruryk","Rusana","Ruslan","Ruslana",
            "Ruszl??n","Rut","R??t","Ruta","Rutger","Ruth","Ruvini","Ru??a","Ru??ana","Ru??ena","Ru??ena","Ru??ica","Ryan","Ryszard","Ryszarda",
            "Oehoo","Oehooka","Saba","S??ba","Sab??s","Sabba","Sabel","Sabina","Sabina","Sabini","Sabrin","Sabrina","S??dko","Saffron","Sage",
            "Saima","Saimi","Salamon","Sally","Salmon","Saloma","Salomao","Salome","Salom??","Salomea","Salomeja","Salomena","Salomo","Salomon","Salomon",
            "Salomone","Salomoja","Salvador","Salv??tor","Salv??tor","Salv??tore","Sam","Samanta","Samantha","Samar","Samara","Sammy","S??mo","Samojlo","Sampson",
            "Samson","Samson","Samuel","Samuel","Samuela","Samuele","Samuella","Samuil","Samuilo","Samy","Sana","S??ndor","Sandra","Sandrine","Sandro",
            "Sane","Sanela","Sans??n","Sant??l","Santiago","Santino","Sapfo","Sara","S??ra","Sarah","Sarai","Sarolta","Saron","Sarra","Sasha",
            "Sasha","Sascha","Sascha","Saskia","Saskie","Sa??a","Sa??a","Sa??e","Sa??o","Saturni]","Saturnin","Saturnino","Saturninus","Saturnus","Saul",
            "Saul","Sava","Sava","S??va","S??va","Savana","Savanna","Savannah","Save","Savela","Savelij","Savelja","Saverio","Savina","Savka",
            "Savo","Sawa","Sawin","Saxana","Saxona","Sbislaus","Sbygneus","Scarlett","Scolastica","Scott","Sean","S??bastia","Sebastian","Sebasti??n","Sebasti??n",
            "Sebastiana","Sebasti??na","Sebastiane","Sebastiane","Sebastiano","Sebastianus","S??bastien","Sebastinie","Sebastio","Sebestian","Sebesty??n","Sedrik","Segismundo","Seia","Seiya",
            "Sej","Selena","Selen??","S??l??ne","Selina","Seline","Selwin","Selwyn","Sem","Semj??n","Semjon","Senad","Senadin","Senta","Sentia",
            "Sepp","Serafim","Serafima","Serafin","Seraf??n","Serafina","Seraf??na","Serafino","Serafinus","Serafym","Seraph","S??raphin","Seraphina","Seraphine","S??raphine",
            "Seren","Serena","S??r??na","S??rine","Sereno","Serenus","Serge","Sergej","Sergij","Sergije","Sergio","Sergiu","Sergius","Sergiusz","Serchio",
            "Serv??c","Servacio","Serv??cius","Servacoj","Servais","Servatius","Servaz","Servazio","Servio","Serwacy","Sevastiana","Sevastiani","Sevastija","Sevastijan","Sevastjan",
            "Sevastjana","Severian","Severin","Sever??n","Severina","Sever??na","Severino","Severinus","Severjan","Severyn","Seweryn","Shane","Shanel","Shanell","Shania",
            "Shannen","Shannon","Shari","Sharon","Sheila","Sheley","Shelley","Sheryl","Shilla","Shiloh","Shimon","Shoshanna","Shoshaunah","Scholastica","Scholastika",
            "Schulammit","Sian","Siana","Sibila","Sibille","SibiUa","Sibota","Sibyla","Sibylla","Sibylle","Sid","Sidney","Sidney","Sidoine","Sidon",
            "Sid??n","Sidonia","Sid??nia","Sidonie","Sidonija","Sidonio","Sidonius","Sidony","Siegfried","Sieglinda","Sieglinde","Siegmund","Sielinda","Sigfred","Sigfrid",
            "Sigfrid","Sigfr??d","Sigfrido","Sigismond","Sigismondo","Sigismund","Sigismundo","Sigismundus","Sigizmund","Siglinde","Sigmund","Sikandar","Sikst","Silas","Silka",
            "Silke","Silva","Silvain","Silvan","Silv??n","Silvana","Silv??na","Silvano","Silvanus","Silvestar","Silvester","Silvestr","Silvestra","Silvestoe","Silvia",
            "Silvie","Silvin","Silvius","Sime??o","Simeha","Simeon","Sime??n","Sim??on","Simeona","Simeone","Simion","SiMj","SiMja","SiMje","Simo",
            "SiMo","Simon","Simon","Simona","Simone","Simone","Simoneta","Simonetta","Simonette","Simonida","Simson","Sinclair","Sindy","Sinikka","Sinkler",
            "Siobh??n","Sirio","Sirkka","Siro","Sisi","Sissi","Sissy","Sixt","Sixte","Sixto","Sixtus","Skarlet","Skarleta","Skolasztika","Slav",
            "Sl??va","Sl??va","Sl??va","Slavata","Sl??vek","Slaven","Slavena","Slavina","Slavi","Slaviboj","Slavibor","Slavij","Slavija","Slavimir","Slavin",
            "Slav??na","Slavjana","Sl??vka","Sl??vka","Sl??vko","Slavmyr","Slavoboj","Slavoj","Slavoje","Slavoljub","Slavol??b","Slavomil","Slavomila","Slavom??r","Slavom??r",
            "Slavom??ra","Slavom??ra","Slavomyr","Slavo??","Slaw","Slawa","Slawa","Slawka","Slawomir","Slawomira","Slobodan","Slobodana","Slobodanka","Slobodinka","Slobodna",
            "Smil","Smile","Smiljan","Sne??ana","Sne??anka","Sobislav","Sobislava","Sobieslaw","Sobieslawa","Socrate","Socrates","S??crates","Sofia","Sofia","Sofian",
            "Sofi??n","Sofie","Sofija","Sofijan","Sofio","Sofja","Sofrina","Sofronia","Sofr??nia","Sofronie","Sofronija","Sokrat","Sokrates","Sokratis","Solomon",
            "Solveig","Solveiga","Solvej","Sooa","Sonia","Sonja","Sonny","Sonya","Sophia","Sophie","Sophien","Sophius","Sopnronia","S??ren","Sorley",
            "Soter","Sotera","Sotero","Sotir","Sotira","Sotiria","Sotirios","Sotirka","Spas","Spencer","Sperancie","Spiros","Spyros","Spytihniv","Spytim??r",
            "Srdjan","Srd??an","Stacy","Staffan","St??na","Stanimir","Stanim??r","Stanislao","Stanislas","Stanislau","Stanislaus","Stanislav","Stanislava","Stanislaw","Stanislaw",
            "Stanislawa","Stanko","Stanley","Stas","Stasi","Stasij","Sta??ek","Statis","Stavros","Staza","St??za","Stefan","Stefana","Stefania","Stef??nia",
            "Stefanie","Stefanija","Stefanka","Stefano","Stefanus","Stefanyda","Steffi","Stefi","Stela","Stelia","Stelio","Stella","SteMa","SteMo","Step??n",
            "Stepanida","Stepanija","Stepanyda","Stephan","Stephana","St??phane","St??phane","Stephanie","St??phanie","Stephanos","Stephanus","Stephen","Stevan","Steve","Steven",
            "Stih??n","Stilijan","Stipe","Stjepan","Stoimir","Stojan","Stojimir","Stojm??r","Stomir","Stoezislav","Stoezom??r","Sufija","Sulamit","Sulamita","Sulamith",
            "Sulejka","Sulejman","Sulika","Susan","Susana","Susane","Susann","Susannah","Susanne","Susauna","Susette","Sussan","Suzan","Suzana","Suzanne",
            "Sv??na","Svatava","Svatoboj","Svatoboj","Svatobor","Svatom??r","Svatom??r","Svatom??ra","Svatopluk","Svatopluk","Svatoslav","Svatoslav","Svatoslava","Svato??","Svatu??e",
            "Svebor","Svein","Svemil","Svemir","Sven","Svend","Sveno","Sverad","Sveslav","Svita","Svetana","Svetibor","Svetimir","Svetimira","Svetimirka",
            "Svet??slav","Svetislava","Svitla","Svitla","Svitlana","Svitlana","Svitlu??e","Sveto","Svetomir","Svetomira","Svetoslav","Svitoslav","Svetozar","Svetoz??r","Svierad",
            "Svitlana","Svitozar","Svjatopolk","Svjatoslav","Swana","Swen","Swietobor","Swietomir","Swietopelk","Swietoslaw","Swietoslawa","Sybil","Sybila","Syd","Sydney",
            "Sydney","Sydonia","Sydoniusz","Syjato","Syjatogor","Sykstus","Sylfest","Sylva","Sylvain","Sylvest","Sylvester","Sylvestr","Sylvestre","Sylvie","Sylwester",
            "Sylwia","Sylwin","Sylwiusz","SyM","SyMa","Symeon","Symon","Szabina","Szalv??tor","Szandra","Szaniszl??","Szarlota","Szczepan","Szczesny","Szende",
            "Szer??f","Szerafina","Szer??na","Szer??nusz","Szergiusz","Szerv??c","Szeverin","Szibilla","Szid??nia","Szigfrid","Szilv??na","Szilv??nusz","Szilveszter","Szilvius","SziMa",
            "Szimona","Szixtusz","Szofr??nia","Szonja","Szvetl??na","Szymon","Szymona","??alamoun","??alam??n","??alomoun","??ana","????ndor","??andy","??andy","??anel",
            "??ansone","??antal","????rka","??arlota","??avel","??avol","??ebesti??n","??ebesti??na","??eb??o","??elma","??imon","??imona","??kolastika","??lechtislav","???asta",
            "??tefan","??tef??nia","??tefanie","??tef??nie","??tip??n","??tip??na","??tip??nka","Tabea","Tabita","T??bita","Tabitha","Taddeo","Taddeus","T??d??","Tade????",
            "Tadej","Tadeo","Tadeu","Tadeusz","Tadija","Taida","Tain??","Tainara","Tais","Taisa","Taisia","Taisija","Taito","Takashi","Taka??i",
            "Takeo","Takeshi","Talinlah","Talisa","Talitha","Tallis","Tamara","Tam??s","Tamika","Tamiko","Tamila","Tammy","T??oa","Tancred","Tancride",
            "Tancredi","Tancredo","Tania","Tanita","Tanitha","Tanja","Tankred","Tankr??d","Tano","Tanya","Tapani","Tara","Taras","Tarasij","Tarasije",
            "Tarasio","Tar??z","Tarek","Tarik","Tarja","Tasia","Tasilo","Tasio","Taso","Tasso","Taszil??","Ta??o","Ta???na","Tatiana","Tatiane",
            "Tatiani","Tatijana","Tatjana","Tatj??na","Ta?jana","Tau??","Tauan","Tavi","TavL","Taylor","Taylor","Tea","Tecla","Ted","Tekla",
            "Telma","Tena","Teo","Teobald","Teobaldo","Teodor","Teodora","Teodora","Teodorica","Teodorico","Teodorik","Teodorika","Teodoro","Teodor??","Teodosia",
            "Teodosie","Teodosija","Teodosije","Teodosio","Teodoz","Teod??zia","Teodozie","Teodozija","Teodozja","Teodozjusz","Teodozy","Teofan","Teofane","Te??fanes","Teofanie",
            "Teofil","Teofila","Teofila","Teofilija","Teofilo","Te??filo","Terancio","Terence","T??rence","Terencio","Terencius","Terencjan","Terencjusz","Ter??ne","Terentij",
            "Terentius","Terenzio","Teresa","T??r??sa","Teresia","Teresty??n","Terez","Tereza","Ter??zia","Terezie","Terezija","Terri","Terry","Terzo","Tess",
            "Tessa","Tetjana","Thaddaus","Thadd??e","Thaddeus","Thad??e","Thadeu","Thadeus","Thais","Thaisa","Thalia","Th??lie","Th??lie","Thanasis","Thassilo",
            "Thea","Thecla","Thicle","Thekla","Thelma","Theo","Th??o","Theobald","Theobaldo","Theodebald","Theoderich","Theodor","Theodor","Theodora","Theodore",
            "Theodore","Theodore","Theodore","Theodoric","Theodorik","Theodorik","Theodorika","Theodoros","Theodorus","Theodooi","Theodosia","Theodosie","Th??odosie","Theodosio","Theodosios",
            "Theodosius","Theofan","Theofanes","Theofanie","Theofil","Theofila","Theofilus","Theophania","Theophil","Theophila","Th??ophile","Theophilus","Theopisti","Theresa","Therese",
            "Th??rise","Theresia","Theudebaldo","Thibauld","Thibaut","Thomas","Thomas","Thomasa","Thomasina","Thomasine","Thomaz","Thorstein","Thorsten","Thurstan","Tiago",
            "Tiara","Tib??d","Tiber","Tibere","Tiberij","Tiberio","Tiberiu","Tib??riusz","Tibold","Tibor","Tibora","Tibore","Tiburc","Tiburce","Tiburcij",
            "Tiburcio","Tiburcius","Tiburtius","Tiburzio","Ticia","Ticiana","Tiffany","Tiger","Tihana","Tichomil","Tichomir","Tichon","Tijana","T??m","Timea",
            "T??mea","Timo","Timofej","Timona","Timone","Tim??t","Timotea","Timotei","Timoteo","Timoteos","Timoteus","Timothea","Timoth??e","Timotheos","Timotheu",
            "Timotheus","Timothy","Timotije","Timur","Tina","Tino","Tinomil","Tinomir","Tisa","Tit","Tite","Tito","Titu","Titus","T??tus",
            "Titusz","TiU","Tivadar","Tivurtij","Tizia","Tiziana","Tiziano","Tizio","Tlberius","Tlham??r","Tlchom??r","Tlmon","Toar","Tobia","Tobias",
            "Tob??as","T??bi??s","Tobiasz","Tobi????","Tobie","Tobija","Tobijas","Toby","Todor","T??dor","Todora","Todosie","Tom","Toman","Tomanija",
            "Tomas","Tomasa","Tomasina","Tomassina","Tomasz","Tom????","Tom????","Tom????","Tom????ka","Tom??zia","Tomi","Tomislaus","Tomislav","Tomislava","Tomislaw",
            "Tomislaw","Tomislawa","Tommaso","Tommi","Tommy","Toni","Toni","Tonia","T??nino","Tonko","Tony","Tony","Tonya","Topi","Topias",
            "Tor","Tora","Torcuato","Torkvat","Torkv??t","Torkvato","Torna","Torquato","Torquatus","Torstein","Torsten","Torvid","Torvin","Tosca","Toska",
            "Toszka","Tovia","Tovija","Tracy","Tracy","Trajan","Traude","Traute","Travis","Trent","Trevor","Tristan","Trist??n","Tristano","Tristanus",
            "Tristram","Triszt??n","Trix","Trixa","Trixi","Trixie","Trixy","Troy","Truda","Trude","Tryfon","Ttmotej","Tucker","Tudor","Tuho",
            "Tulia","Tulie","Tullia","Tullio","Tunde","Tund??r","Tuomi","Tuomo","Tuula","Tuulikki","Tworzyjan","Tyberiusz","Tyburcjusz","Tyburcy","Tyge",
            "Tychin","Tycho","Tychon","Tyke","Tymko","Tymofrj","Tymon","Tymoteusz","Tymur","T??na","Tytus","Uberto","Udo","Ugo","Ula",
            "Ul??szl??","Ulf","Ulita","Uljan","Uljana","Ulla","Ulric","Ulrica","Ulrico","Ulrih","Ulrich","Ulrik","Ulrika","Ulrike","Ulrikka",
            "Ulrikke","Ulryka","Umar","Umberto","Una","Unislav","Unna","Upton","Urania","Urbain","Urban","Urban","Urbano","Urbanus","Uri????",
            "Zlato??","Zlatu??a","Zlatu??e","Zoa","Zoana","Zoe","Zo??","Zofia","Zoi","Zoja","Z??ja","Z??lestin","Z??lestine","Z??lestinus","Zoltan",
            "Zolt??n","Zora","Zora","Zor??n","Zor??n","Zoran??","Zorian","Zorica","Zorika","Zorin","Zorina","Zorislav","Zorislava","Zorja","Zorjana",
            "Zorka","Zoro","Zoroslav","Zoroslava","Zoryna","Zosim","Zosima","Zosima","Zosimo","Z??simo","Zosimus","Zosym","Zoya","Zozima","Zozim??",
            "Zsad??ny","Zsaklin","Zsanett","Zseraldina","Zsigmond","Zs??fia","Zsuzsauna","Zuleika","Zulejka","Zulika","Zuzana","Zuzanna","Zvezdan","Zvezdana","Zvezdomir",
            "Zvezdomira","Zvjezdan","Zvonimir","Zvonim??r","Zvonimira","Zvonim??ra","Zwonimir","Zygfryd","Zygmunt","Zyprian","Zyta","??akelina","??akelina","??akelina","??aklina",
            "??aklina","??anda","??anet","??aneta","??aneta","??anna","??anna","??arko","??aro","??dan","??dana","??elhnira","??elibor","??elimir","??elim??r",
            "??elim??ra","??elisav","??elisava","??elislav","??elislava","??elislaw","??elislawa","??elm??r","??elm??ra","??iboid","??igmund","??itomir","??itom??r","??itomira","??itom??ra",
            "??itoslav","??itoslava","??iva","??ivan","??ivana","??ivena","??ivena","??ivka","??ivko","??ivo","??ivodan","??ivodar","??ivojin","??ivomira","??ofia",
            "??ofie","??ywia"};

}