package JanP;

import java.io.Serializable;

public class State implements Serializable {
    private static final long serialVersionUID = 1L;
    public int[][] playground;

    public State(int size) {
        playground = new int[size][size];
        // inicialna konfiguracia, vyskusajte ine, a odovzdajte s tou, ktora sa vam najviac pacila

        // block
//        for (int i = size / 2; i < size / 2 + 2; i++) {
//            playground[size / 2][i] = 1;
//            playground[size / 2 + 1][i] = 1;
//        }
//
//        // blinker
//        for (int i = size / 3; i < size / 3 + 3; i++) {
//            playground[size / 3][i] = 1;
//        }
//
//        // toad
//        for (int i = 1; i < 4; i++) {
//            playground[2][i] = 1;
//        }
//        for (int i = 2; i < 5; i++) {
//            playground[3][i] = 1;
//        }
//
//
//        // glider
//        for (int i = 1; i < 4; i++) {
//            playground[10][i] = 1;
//        }
//        playground[9][3] = 1;
//        playground[8][2] = 1;


        // najviac sa mi paci diehard - uz len nazov je super :D, a pekne pripomina zivot cloveka
        // najprv rastie a potom uz z neho ubuda az pokym neumrie uplne
        for (int i = 10; i < 12; i++) {
            playground[i][i] = 1;
            playground[10][i] = 1;
        }
        for (int i = 15; i < 18; i++) {
            playground[11][i] = 1;
        }
        playground[9][16] = 1;

    }

    public void update() {
        System.out.println("update");
        int[][] oldPlayground = deepCopy(playground); // bud takto ta to da, alebo by som si mohol niekde ulozit, ze
        // kde treba po prechode zmenit 1 > 0 a 0 > 1 , kedze to nie je az take vypoctovo zlozite , tak to nejdem prerabat na to druhe

        for (int row = 0; row < playground.length; row++)
            for (int column = 0; column < playground[row].length; column++) {
                var pocetSus = neighboursCount(row, column, oldPlayground);
                if (pocetSus >= 4) die(row, column); // stay dead or die of overpop
                if (pocetSus <= 1) die(row, column); // stay dead or die of loneliness
                if (pocetSus == 3) beBorn(row, column); //stay alive or be born
                // stay alive or dead otherwise - neighours == 2
            }
    }

    public int neighboursCount(int row, int column, int[][] playground) {

        int numNeighbors = 0;
        for (int x = row - 1; x <= row + 1; x++) {
            for (int y = column - 1; y <= column + 1; y++) {
                if (!(x == row && y == column) && // do not count yourself as a neighbour
                        playground[Math.floorMod(x, playground[0].length)][Math.floorMod(y, playground.length)] == 1) {
                    numNeighbors++;
                }
            }
        }
        return numNeighbors;
    }

    private void die(int row, int col) {
        playground[row][col] = 0;
    }

    private void beBorn(int row, int col) {
        playground[row][col] = 1;
    }


    int[][] deepCopy(int[][] playground) {
        return java.util.Arrays.stream(playground).map(arr -> arr.clone()).toArray($ -> playground.clone());
    }
}
