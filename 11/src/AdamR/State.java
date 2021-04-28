package AdamR;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class State implements Serializable {
        private static final long serialVersionUID = 1L;
        public int[][] playground;
        private final int SIZE;

        public State(int size) {
            SIZE = size;
            playground = new int[size][size];
            // inicialna konfiguracia, vyskusajte ine, a odovzdajte s tou, ktora sa vam najviac pacila
            /** konfiguracia 1
            //lavy horny roh počiatku:
            int y = 8, x = 10;
            playground[y+1][x+1] = 1;
            playground[y][x+2] = 1;
            playground[y+1][x+3] = 1;

            int r = 0;
            for(int i = 1; i <= 12; i++){
                if(i <= 6)
                    playground[y+1+i][x] = 1;
                else
                    playground[y+1+i-6][x+4] = 1;
            }

            playground[y+8][x+1] = 1;
            playground[y+1+8][x+2] = 1;
            playground[y+8][x+3] = 1;
            */
             // Konfiguracia 2
            var indexyA = List.of(7,12,14,19);
            var indexyB = List.of(9,10,11,15,16,17);
            for(Integer r: indexyA){
                for(Integer s: indexyB){
                    playground[r][s] = 1;
                }
            }
            for(Integer r: indexyB){
                for(Integer s: indexyA){
                    playground[r][s] = 1;
                }
            }
        }
        public void update() {
            System.out.println("update");
            int[][] nextGeneration = new int[SIZE][SIZE];

            for(int i = 1; i < SIZE; i++)
                for(int j = 1; j < SIZE; j++) {
                    int ps = pocetSusedov(i, j);
                    if(ps == 3 || ps == 2 && playground[i][j] == 1){
                        nextGeneration[i][j] = 1;
                    }
                }
            playground = nextGeneration;
        }

        private int pocetSusedov(int x, int y){
            int pocet = 0;
            for(int riadok = x-1; riadok <= x+1; riadok++){
                for(int stlpec = y-1; stlpec <= y+1; stlpec++){
                    if(0 <= riadok && riadok < SIZE &&
                        0 <= stlpec && stlpec < SIZE){
                        pocet += playground[riadok][stlpec];
                    }
                }
            }
            return pocet - playground[x][y];
        }
    }
