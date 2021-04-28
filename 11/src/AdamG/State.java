package AdamG;

import java.io.Serializable;
public class State implements Serializable {
        private static final long serialVersionUID = 1L;
        public int[][] playground;

        public State(int size) {
            playground = new int[size][size];
            // random config nech nie sme stereotipni
            for (int i = 1; i < playground.length - 1; i++) {
                for (int j = 1; j < playground[i].length - 1; j++) {
                    playground[i][j]=Math.random()<0.5?  1:0;
                }}
        }
        public void update() {
            int susedov = 0;
            int[][] playground2 = new int[playground.length][playground.length];
            // naprogramuj hru life... toto je len fake ci demo
            System.out.println("update");
            //trosku som prepisal ten nachystany cyklus aby to zilo aj pri okrajoch
            //btw RIP John Conway, sikovny to clovek
            for (int i = 0; i < playground.length ; i++) {
                for (int j = 0; j < playground[i].length ; j++) {//loop cez all cells
                    susedov = 0;
                    for (int k = i==0?0:-1; k <= (i==playground.length-1?0:1); k++) {
                        for (int l = j==0?0:-1; l <= (j==playground[i].length-1?0:1); l++) {
                            susedov += playground[i + k][j + l];
                        }
                    }

                    susedov -= playground[i][j];

                    // rules of LIFE
                    if ((playground[i][j] == 1) && (susedov == 2)) { //(Live) lives on
                        playground2[i][j] = 1;
                    }else if ((playground[i][j] == 1) && (susedov == 3)) { //(Live) lives on
                        playground2[i][j] = 1;
                    }else if ((playground[i][j] == 0) && (susedov == 3)) { //(Dead) becomes alive
                        playground2[i][j] = 1;
                    }else {
                        playground2[i][j] = 0;
                    }
                }
            }
            playground=playground2;
        }
    }
