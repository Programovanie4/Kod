package ErikRJJ;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;

public class State implements Serializable
{
        private static final long serialVersionUID = 1L;
        public int[][] playground;

        public State(int size)
        {
            playground = pulsar(size); //randomConfig(size);
        }

        public void update()
        {
            int[][] newPlayground = new int[playground.length][playground.length];
            //System.out.println("update");
            for(int i = 1; i < playground.length-1; i++)
                for(int j = 1; j < playground[i].length-1; j++)
                {
                    int neighbours = playground[i - 1][j - 1] + playground[i - 1][j] + playground[i - 1][j + 1] +
                                    playground[i][j - 1] + playground[i][j + 1] +
                                    playground[i + 1][j - 1] + playground[i + 1][j] + playground[i + 1][j + 1];

                    if ((neighbours == 3 || neighbours == 2) && playground[i][j] == 1)
                        newPlayground[i][j] = 1;
                    if (neighbours == 3 && playground[i][j] == 0)
                        newPlayground[i][j] = 1;
                }
            playground = newPlayground;
        }

        private int[][] randomConfig(int size)
        {
            int[][] newPlayground = new int[size][size];
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    newPlayground[i][j] = (int)Math.floor(Math.random() * 2);
            return newPlayground;
        }

    private int[][] pulsar(int size)
    {
        if (size < 13)
        {
            System.out.println("Improper size for pulsar.");
            return randomConfig(size);
        }
        int[][] newPlayground = new int[size][size];
        int middle = size / 2;
        int[] coords1 = {middle - 1, middle - 6, middle + 1, middle + 6};
        int[] coords2 = {middle - 2, middle - 3, middle - 4, middle + 2, middle + 3, middle + 4};
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 4; j++)
            {
                newPlayground[coords1[j]][coords2[i]] = 1;
                newPlayground[coords2[i]][coords1[j]] = 1;
            }

        /*for (int i = 0; i < size; i++)
            System.out.println(Arrays.toString(newPlayground[i]));*/
        return newPlayground;
    }
}
