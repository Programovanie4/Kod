import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegDate {

    // aj som sa cudoval, ako dokaze z cisla napr. x = 0157324344 spravit riadny datum ked nie je dostatok cifier na to :),
    // prestudoval som si Date() a vidim, ze sa zacina od 1900 + (x), takze ak to takto funguje, tak veru asi sa neda spravit
    // vseobecny :D, skusil som dat nieco dokopy, napokon som sa vsak inspiroval https://www.baeldung.com/java-date-regular-expressions

    // zaujimavy clanok v liste, predpokladam prelozeny, nabuduce mozte kludne prilozit aj EN verziu, trvalo mi to dlhsie rozlustit, ako by to bolo v originali :)

    // druhy link hadze 404

    public static String RegExpDate() { // -- leap feb -- + -- non-leap feb -- + -- rest --
        return "^((2000|2400|2800|(19|2\\d(0[48]|[2468][048]|[13579][26])))-02-29)" + "|^(((19|2\\d)\\d{2})-02-(0[1-9]|1\\d|2[0-8]))"
                + "|^(((19|2\\d)\\d{2})-(0[13578]|10|12)-(0[1-9]|[12]\\d|3[01]))|^(((19|2\\d)\\d{2})-(0[469]|11)-(0[1-9]|[12]\\d|30))";
    }

    public static boolean matches(String date) {
        //        Pattern pattern = Pattern.compile("^((2000|2400|2800|(19|2\\d(0[48]|[2468][048]|[13579][26])))-02-29)" + "|^(((19|2\\d)\\d{2})-02-(0[1-9]|1\\d|2[0-8]))"
        //                + "|^(((19|2\\d)\\d{2})-(0[13578]|10|12)-(0[1-9]|[12]\\d|3[01]))|^(((19|2\\d)\\d{2})-(0[469]|11)-(0[1-9]|[12]\\d|30))");
        Pattern pattern = Pattern.compile(RegExpDate());
        Matcher match = pattern.matcher(date);
        return match.matches();
    }

    public static void main(String[] args) {
        System.out.println(matches("2500-02-29"));
        Date s;
    }
}