package CC;

public class CC4
{
    private static boolean priestupny(int rok) //12.000
    {
        if (rok % 4 == 0)
        {
            if (rok % 100 == 0 && rok % 400 != 0)
            {
                return false;
            }
            return true;
        }
        return false;
    }

    static String RegExpDate()
    {
        StringBuffer priestupne = new StringBuffer("((0000");
        StringBuffer nepriestupne = new StringBuffer("");

        for (int i = 2; i < 10000; i++)
        {
            String i_str = Integer.toString(i);

            if (priestupny(i))
            {
                priestupne.append("|");
                priestupne.append(("0000" + i_str).substring(i_str.length()));
            }

        }
        priestupne.append(")-(((01|03|05|07|08|10|12)-(0[1-9]|[12][0-9]|3[01]))|((04|06|09|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[12][0-9]))))");
        nepriestupne.append("(([0-9]{4})-(((01|03|05|07|08|10|12)-(0[1-9]|[12][0-9]|3[01]))|((04|06|09|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|1[0-9]|2[0-8]))))");
        return priestupne.append("|" + nepriestupne.toString()).toString();
    }

    public static void main(String[] args)
    {
        String test = "0800-02-29";
        System.out.println(test.matches(RegExpDate()));
        //System.out.println(test.matches("(2017|2019-(((01|03|05|07|08|10|12)-(0[1-9]|[12][0-9]|3[01]))|((04|06|09|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|1[0-9]|2[0-8]))))|(2020-(((01|03|05|07|08|10|12)-(0[1-9]|[12][0-9]|3[01]))|((04|06|09|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[12][0-9]))))"));


    }
}