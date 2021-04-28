package SabinaS;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class State implements Serializable
{
        private class Pair
        {
            int x;
            int y;

            public Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }
        }

        private static final long serialVersionUID = 1L;
        public int[][] playground;

        private static final int [] xs = new int [] {-1, -1, -1, 0, 0, 1, 1, 1};
        private static final int [] ys = new int [] {-1, 0, 1,-1, 1,-1, 0, 1};

        public State(int size)
        {
            playground = new int[size][size];
            // inicialna konfiguracia, vyskusajte ine, a odovzdajte s tou, ktora sa vam najviac pacila
            //IntStream.range(1, 10).forEach(x -> playground[10][x] = 1);

            //small oscilator
            /*playground[10][10] = 1;
            playground[10][11] = 1;
            playground[10][12] = 1;
            playground[9][13] = 1;
            playground[9][11] = 1;
            playground[9][12] = 1;*/

            // small spaceship
            /*playground[10][10] = 1;
            playground[10][12] = 1;
            playground[11][11] = 1;
            playground[11][12] = 1;
            playground[12][11] = 1;*/



            // ak nahodou nepouzivas moju verziu Life.java, dopln delay na 4000 ms, aby sa zobrazil aj prvy frame
            // teletubbie became oscilators
            // cap
            playground[10][14] = 1;
            playground[10][15] = 1;
            playground[10][16] = 1;
            playground[11][15] = 1;
            playground[12][15] = 1;

            // eyes
            playground[16][13] = 1;
            playground[16][14] = 1;
            playground[16][16] = 1;
            playground[16][17] = 1;

            // smile
            playground[18][14] = 1;
            playground[19][15] = 1;
            playground[18][16] = 1;

            // forehead
            playground[13][13] = 1;
            playground[13][14] = 1;
            playground[13][16] = 1;
            playground[13][17] = 1;
            playground[14][12] = 1;
            playground[14][13] = 1;
            playground[14][14] = 1;
            playground[14][16] = 1;
            playground[14][17] = 1;
            playground[14][18] = 1;
            playground[15][11] = 1;
            playground[15][19] = 1;

            // ears and face
            playground[16][10] = 1;
            playground[16][11] = 1;
            playground[16][19] = 1;
            playground[16][20] = 1;
            playground[17][11] = 1;
            playground[17][19] = 1;
            playground[18][11] = 1;
            playground[18][19] = 1;
            playground[19][12] = 1;
            playground[19][18] = 1;

            // chin
            for(int i=0; i<5; i++)
                playground[20][13+i] = 1;


        }
        public void update() {
            System.out.println("update");
            Set<Pair> zmenit = new HashSet<>();
            for(int i = 1; i < playground.length-1; i++)
            {
                for (int j = 1; j < playground[i].length - 1; j++)
                {
                    int ones = 0;
                    for(int k=0; k<8; k++)
                    {
                        if(j+xs[k] >= 0 && j+xs[k] < playground.length)
                        {
                            if(i+ys[k] >= 0 && i+ys[k] < playground.length)
                            {
                                //System.out.println( i+ " " + j + " " + (i+ys[k]) + " " + + (j+xs[k]));
                                if(playground[i+ys[k]][j+xs[k]] == 1)
                                {
                                    ones++;
                                }
                            }
                        }
                    }
                    if (playground[i][j] == 1)
                    {
                        if(ones < 2 || ones > 3)
                        {
                            zmenit.add(new Pair(i,j));
                        }
                    }
                    else
                    {
                        if(ones == 3)
                        {
                            zmenit.add(new Pair(i,j));
                        }
                    }
                }
            }

            zmenit.forEach(p ->
            {
                playground[p.x][p.y] = 1 - playground[p.x][p.y];
            });

        }
    }
