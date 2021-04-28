package RichardM;

import java.io.Serializable;
public class State implements Serializable {
        private static final long serialVersionUID = 1L;
        public int[][] playground;

        public State(int size) {
            playground = new int[size][size];
            // inicialna konfiguracia, vyskusajte ine, a odovzdajte s tou, ktora sa vam najviac pacila
            //playground[size/2][size/2] = 1;

            // asi najviac clean incializacia je: blinker, period 2
            int riadkov = 0;
            for (int i = 0; i < playground.length; i++) {
                if (i == playground.length / 2 && riadkov == 0) {
                    for (int j = playground.length / 3; j < playground.length / 3 + 3; j++) {
                        playground[i][j] = 1;
                    }
                    riadkov = 1;
                }
            }
            //playground[size/2][size/2] = 1;
        }
        public void update() {
            /*
            // naprogramuj hru life... toto je len fake ci demo
            System.out.println("update");
            for(int i = 1; i < playground.length-1; i++)
                for(int j = 1; j < playground[i].length-1; j++)
                    if (playground[i][j] == 1)
                        for(int a=-1; a <= 1; a++)
                            for(int b=-1; b <= 1; b++)
                                if (a*b == 0)
                                    playground[i+a][j+b] = 1-playground[i+a][j+b];
        }*/
            
            //neverim že nie je slovenská verzia Wikipédie na toto :-(
            //https://cs.wikipedia.org/wiki/Hra_%C5%BEivota
            int[][] dalsiaGeneracia = new int[playground.length][playground[0].length];
            for (int a = 1; a < playground.length - 1; a++) {
                for (int b = 1; b < playground[0].length - 1; b++) {
                    int nazive = 0;
                    for (int i = -1; i <= 1; i++)
                        for (int j = -1; j <= 1; j++)
                            nazive += playground[a + i][b + j];

                    nazive -= playground[a][b];

                    // pravidlá

                    // Každá živá buňka s méně než dvěma živými sousedy zemře
                    if ((playground[a][b] == 1) && (nazive < 2))
                        dalsiaGeneracia[a][b] = 0;

                    // Každá živá buňka s více než třemi živými sousedy zemře.
                    else if ((playground[a][b] == 1) && (nazive > 3))
                        dalsiaGeneracia[a][b] = 0;

                    // Každá mrtvá buňka s právě třemi živými sousedy oživne.
                    else if ((playground[a][b] == 0) && (nazive == 3))
                        dalsiaGeneracia[a][b] = 1;

                    // Každá živá buňka se dvěma nebo třemi živými sousedy zůstává žít.
                    else
                        dalsiaGeneracia[a][b] = playground[a][b];
                }
            }
            playground = dalsiaGeneracia;
        }
    }
