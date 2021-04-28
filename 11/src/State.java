import java.io.Serializable;
public class State implements Serializable {
    private static final long serialVersionUID = 1L;
    public int[][] playground;

    public State(int size) {
        playground = new int[size][size];
        // inicialna konfiguracia, vyskusajte ine, a odovzdajte s tou, ktora sa vam najviac pacila
//        playground[size/2][size/2] = 1;
//        playground[1+size/2][size/2] = 1;
//        playground[2+size/2][size/2] = 1;
//
//        playground[1+size/2][1+size/2] = 1;
        //playground[0+size/2][2+size/2] = 1;

        playground[size/2][size/2] = 1;
        playground[1+size/2][size/2] = 1;
        playground[2+size/2][size/2] = 1;

        playground[1+size/2][2+size/2] = 1;
        playground[2+size/2][1+size/2] = 1;

    }
    public void update() {
        // naprogramuj hru life... toto je len fake ci demo
        System.out.println("update");

//        for(int i = 1; i < playground.length-1; i++)
//            for(int j = 1; j < playground[i].length-1; j++)
//                if (playground[i][j] == 1)
//                    for(int a=-1; a <= 1; a++)
//                        for(int b=-1; b <= 1; b++)
//                            if (a*b == 0)
//                                playground[i+a][j+b] = 1-playground[i+a][j+b];

        int[][] copy = new int[playground.length][playground[0].length];
        for(int i = 0; i < playground.length; i++)
            for(int j = 0; j < playground[i].length; j++) {
                int livesAround = 0;
                for (int a = -1; a <= 1; a++)
                    for (int b = -1; b <= 1; b++)
                        if ((a | b) != 0)
                            if (i + a >= 0 && j + b >= 0 && i + a < playground.length && j + b < playground[0].length)
                                livesAround += playground[i + a][j + b];
                if (playground[i][j] == 1 && livesAround < 2)
                    copy[i][j] = 0;
                else if (playground[i][j] == 1 && (livesAround == 2 || livesAround == 3))
                    copy[i][j] = 1;
                else if (playground[i][j] == 1 && livesAround >3)
                    copy[i][j] = 0;
                else if (playground[i][j] == 0 && livesAround == 3)
                    copy[i][j] = 1;
            }
        for(int i = 1; i < playground.length-1; i++)
            for(int j = 1; j < playground[i].length-1; j++)
                playground[i][j] = copy[i][j];
    }
}
