package MarcelP;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class State implements Serializable {
        private static final long serialVersionUID = 1L;
        public int[][] playground;

        public State(int size) {
            playground = new int[size][size];
            // Heavy-weight spaceship(HWSS) ktora sa zrazi s oscilatorom s periodou 15
            // za vzniku dvoch still life a jedneho velkeho oscilatoru
            playground[20][18] = 1;
            playground[20][19] = 1;
            playground[20][20] = 1;
            playground[20][21] = 1;
            playground[20][22] = 1;
            playground[20][23] = 1;
            playground[20][24] = 1;
            playground[20][25] = 1;

            playground[21][18] = 1;
            playground[21][20] = 1;
            playground[21][21] = 1;
            playground[21][22] = 1;
            playground[21][24] = 1;
            playground[21][25] = 1;

            playground[22][18] = 1;
            playground[22][19] = 1;
            playground[22][20] = 1;
            playground[22][21] = 1;
            playground[22][22] = 1;
            playground[22][23] = 1;
            playground[22][24] = 1;
            playground[22][25] = 1;


            playground[20][9] = 1;
            playground[20][10] = 1;

            playground[21][5] = 1;
            playground[21][6] = 1;
            playground[21][7] = 1;
            playground[21][8] = 1;
            playground[21][10] = 1;
            playground[21][11] = 1;

            playground[22][5] = 1;
            playground[22][6] = 1;
            playground[22][7] = 1;
            playground[22][8] = 1;
            playground[22][9] = 1;
            playground[22][10] = 1;

            playground[23][6] = 1;
            playground[23][7] = 1;
            playground[23][8] = 1;
            playground[23][9] = 1;

        }
        public void update() {
            System.out.println("update");
            Set<Integer[]> alive = new HashSet<>();
            for (int i = 1; i < playground.length - 1; i++){
                for (int j = 1; j < playground[i].length - 1; j++) {
                    int zive = 0;
                    for (int a = -1; a <= 1; a++)
                        for (int b = -1; b <= 1; b++)
                            if (a!=0 || b!=0) {
                                if (playground[i + a][j + b] == 1)
                                    zive++;
                            }
                    if(playground[i][j] == 1){
                        if(zive==2 || zive==3)
                            alive.add(new Integer[]{i,j});
                    }else{
                        if(zive==3)
                            alive.add(new Integer[]{i,j});
                    }
                }
            }

            for (int i = 1; i < playground.length - 1; i++){
                for (int j = 1; j < playground[i].length - 1; j++) {
                    playground[i][j]=0;
                }
            }
            for(var t:alive){
                playground[t[0]][t[1]]=1;
            }

        }
    }
