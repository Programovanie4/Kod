import java.io.Serializable;
public class State implements Serializable {
        private static final long serialVersionUID = 1L;
        public int[][] playground;

        public State(int size) {
            playground = new int[size][size];
            // inicialna konfiguracia, vyskusajte ine, a odovzdajte s tou, ktora sa vam najviac pacila
            playground[size/2][size/2] = 1;
        }
        public void update() {
            // naprogramuj hru life... toto je len fake ci demo
            System.out.println("update");
            for(int i = 1; i < playground.length-1; i++)
                for(int j = 1; j < playground[i].length-1; j++)
                    if (playground[i][j] == 1)
                        for(int a=-1; a <= 1; a++)
                            for(int b=-1; b <= 1; b++)
                                if (a*b == 0)
                                    playground[i+a][j+b] = 1-playground[i+a][j+b];
        }
    }
