import java.util.regex.Pattern;

public class RegDate {
    // uvadzam zdroj https://gist.github.com/Boomerkuwanger/33ab62b1c1784b5222ca, ale este som jeho kod upravoval
    public static String  RegExpDate(){
        return "([1-9][0-9]*)*(((([13579][26]|[2468][048])[02468][048]|([13579][26]|[2468][048])[13579][26])[-](((1[02]|0[13578])[-](3[01]|[12][0-9]|0[1-9]))|((11|0[469])[-](30|[12][0-9]|0[1-9]))|((02)[-](2[0-9]|1[1-9]|0[1-9]))))|((([13579][01345789]|[2468][1235679])[02468][1235679]|([13579][01345789]|[2468][1235679])[13579][01345789])[-](((1[02]|0[13578])[-](3[01]|[12][0-9]|0[1-9]))|((11|0[469])[-](30|[12][0-9]|0[1-9]))|((02)[-](2[0-8]|1[1-9]|0[1-9])))))";
    }

    public static void main(String[] args) {
        System.out.println(Pattern.matches(RegDate.RegExpDate(),
                "1600-02-30"));//true (2nd char is s)
    }
}
