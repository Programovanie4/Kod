package MirkaS;

import java.io.Serializable;
public class State implements Serializable {
        private static final long serialVersionUID = 1L;
        public int[][] playground;


        public State(int size) {
            playground = new int[size][size];
            // inicialna konfiguracia, vyskusajte ine, a odovzdajte s tou, ktora sa vam najviac pacila
            playground[size/10][size/10] = 1;
//            playground[size/10][size/10+1] = 1;
//            playground[size/10][size/10+2] = 1;
        }
        // https://tutorialspoint.dev/data-structure/matrix-archives/program-for-conways-game-of-life
        public void update() {
            // naprogramuj hru life... toto je len fake ci demo
            System.out.println("update");
            int[][] future = new int[playground.length][playground[0].length];
            for(int i = 1; i < playground.length-1; i++) {
                for (int j = 1; j < playground[i].length - 1; j++){
                    int zivysused = 0;

                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++)
                            zivysused += playground[i + x][j + y];
                        //System.out.println("susedia " + aliveNeighbours);
                    }
                    zivysused -= playground[i][j];

                    if ((playground[i][j] == 1)&& (zivysused < 2)){
                        future[i][j] = 0;

                    }else if ((playground[i][j] == 0) && (zivysused > 3)) {
                        future[i][j] = 0;

                    } else if ((playground[i][j] == 1) && (zivysused == 3)) {
                        //future[i][j] = 1;
                        for (int a = -1; a <= 1; a++)
                            for (int b = -1; b <= 1; b++)
                                if (a * b == 1)
                                    playground[i + a][j + b] = 1 - playground[i + a][j + b];
                    }else{
                        future[i][j] = playground[i][j];
                    }


                }
            }

        }
    }
