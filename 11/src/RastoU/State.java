package RastoU;

import java.io.Serializable;
import java.util.Arrays;

public class State implements Serializable {
        private static final long serialVersionUID = 1L;
        public int[][] playground;

        public State(int size) {
            playground = new int[size][size];
            // inicialna konfiguracia, vyskusajte ine, a odovzdajte s tou, ktora sa vam najviac pacila

            String[] pom = new String[] {
                    "..OOO...OOO..",
                    ".............",
                    "O....O.O....O",
                    "O....O.O....O",
                    "O....O.O....O",
                    "..OOO...OOO..",
                    ".............",
                    "..OOO...OOO..",
                    "O....O.O....O",
                    "O....O.O....O",
                    "O....O.O....O",
                    ".............",
                    "..OOO...OOO.."
            };
            for (int i = 0; i < pom.length; i++) {
                for (int j = 0; j < pom[i].length(); j++) {
                    if (pom[i].charAt(j) == 'O')
                        playground[2+i][2+j] = 1;
                    else playground[2+i][2+j] = 0;
                }
            }

//            playground[size/2][size/2] = 1;
//            playground[size/2][size/2-1] = 1;
//            playground[size/2][size/2+1] = 1;

        }



        public int numOfNeighbors(int i, int j) {
            int out = 0;
            for (int r : new int[]{-1, 0, 1}) {
                for (int s : new int[]{-1, 0, 1}) {
                    if (r != 0 || s != 0) {
                        if (0 <= i+r && i+r < playground.length
                            && 0 <= j+s && j+s < playground.length) {
                            if (playground[i+r][j+s] == 1) out++;
                        }
                    }
                }
            }
            return out;
        }

        public void update() {
            System.out.println("update");
            int[][] nove = new int[playground.length][playground.length];
            for(int i = 0; i < playground.length-1; i++)
                for(int j = 0; j < playground[i].length-1; j++) {
                    int neighbors = numOfNeighbors(i, j);

                    if (playground[i][j] == 1 && (neighbors < 2 || neighbors > 3)) {
                        nove[i][j] = 0;
                    } else if (playground[i][j] == 0 && neighbors == 3) {
                        nove[i][j] = 1;
                    } else {
                        nove[i][j] = playground[i][j];
                    }
                }
            System.arraycopy(nove, 0, playground, 0, playground.length);
        }
    }
