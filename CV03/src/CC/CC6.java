package CC;

public class CC6 {
        private static boolean ignoreCharacter(char c)
        {
            return " ,.:!\"\'-\\".contains(""+c);

//            switch (c)          //!!!!!!!!!!!!!!!!!!
//            {
//                case ' ':
//                case ',':
//                case '.':
//                case ':':
//                case '!':
//                case ';':
//                case '"':
//                case '-':
//                case '\'':
//                    return true;
//            }
//            return false;
        }



     //

        public static boolean palindrom(String s)
        {
            if(s.length() <= 1) return true;
            int start_index = 0, end_index = s.length() - 1;

            while  (end_index > 0 && start_index < s.length() / 2)
            {
                char c1 = java.lang.Character.toLowerCase(s.charAt(start_index)) , c2 = java.lang.Character.toLowerCase(s.charAt(end_index));

                if (ignoreCharacter(c1))
                    start_index++;
                if (ignoreCharacter(c2))
                    end_index--;

                if ( ! ignoreCharacter(c1) && ! ignoreCharacter(c2))
                {
                    if (c1 != c2) return false;
                    start_index++;
                    end_index--;
                }
            }
            return true;
        }

        private static int[] frequencyOfAlphabetSymbols(String s)
        {
            int[] fr = new int[26];

            for (int i = 0; i < s.length(); i++)
            {
                char c = java.lang.Character.toLowerCase(s.charAt(i));   //!!!!!!!!!!!!
                if ('a' <= c && c <= 'z')
                {
                    fr[c - 'a'] += 1;
                }
            }
            return fr;
        }

        public static boolean panagram(String s)
        {
            int[] fr = frequencyOfAlphabetSymbols(s);

            for (int i = 0; i < fr.length; i++)
            {
                if (fr[i] != 1) return false;
            }
            return true;
        }

        public static boolean anagram(String s1, String s2)
        {
            int[] fr1 = frequencyOfAlphabetSymbols(s1), fr2 = frequencyOfAlphabetSymbols(s2);

            for (int i = 0; i < fr1.length; i++)
            {
                if (fr1[i] != fr2[i]) return false;
            }
            return true;
        }

        public static void main(String[] args)
        {
            System.out.println(palindrom("Kobyla ma maly bok"));
            System.out.println(palindrom("Java horko ma, mokro Havaj"));
            System.out.println(palindrom("Jelenovi pivo nelej."));
            System.out.println(palindrom("J     "));
            System.out.println(palindrom("abcd"));

            System.out.println(panagram("abcd"));
            System.out.println(panagram("D.V. Pike flung J.Q. Schwartz my box."));
            System.out.println(panagram("Dwarf mobs quiz lynx.jpg, kvetch"));

            System.out.println(anagram("Hrabe povala", "Pavol Habera"));
            System.out.println(anagram("A Gentleman", "Elegant Man"));
            System.out.println(anagram("To be or not to be: that is the question, whether tis nobler in the mind to suffer the slings and arrows of outrageous fortune.", "In one of the Bards best-thought-of tragedies, our insistent hero, Hamlet, queries on two fronts about how life turns rotten."));
        }

    }