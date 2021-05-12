package BypassExcellencie;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.Random;

public class CanvClickHandler implements EventHandler<MouseEvent> {
    private Integer[] prevCoord = new Integer[2];
    private GamePane gp;

    public CanvClickHandler(GamePane gp) {
        this.gp = gp;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (gp.srzbl.secs == 200){
            mouseEvent.consume();
            return;
        }
        int i = (int) mouseEvent.getX()/gp.srzbl.sz;
        int j = (int) mouseEvent.getY()/gp.srzbl.sz;
        System.out.println(mouseEvent.getX());
        System.out.println(i);
        System.out.println(j);
        // j represent row number (config[j][i]) and i column
        if (j >= 0 && j < gp.srzbl.config.length && i >= 0 && i < gp.srzbl.config[0].length){
            if (prevCoord[0] == null || !((Math.abs(prevCoord[0] - j) == 1 && Math.abs(prevCoord[1] - i) == 0) ||
                    (Math.abs(prevCoord[0] - j) == 0 && Math.abs(prevCoord[1] - i) == 1))){
                prevCoord[0] = j;
                prevCoord[1] = i;
            }
            else{
                int tmp = gp.srzbl.config[j][i];
                gp.srzbl.config[j][i] = gp.srzbl.config[prevCoord[0]][prevCoord[1]];
                gp.srzbl.config[prevCoord[0]][prevCoord[1]] = tmp;
                int matches = getMatches();
                if (matches == 0){
                    gp.srzbl.config[prevCoord[0]][prevCoord[1]] = gp.srzbl.config[j][i];
                    gp.srzbl.config[j][i] = tmp;
                }
                else {
                    gp.srzbl.score += matches;
                    gp.update();
                }
                prevCoord[0] = null;
                prevCoord[1] = null;
            }
        }
        System.out.println(prevCoord[0]);
        mouseEvent.consume();
    }

    private int getMatches(){
        // config[y][x]
        int res = 0;
        for (int y = 0; y < gp.srzbl.config.length; y++){
            for (int x = 0; x < gp.srzbl.config[0].length; x++){
                if (y < gp.srzbl.config.length-2 && gp.srzbl.config[y][x].equals(gp.srzbl.config[y+1][x]) && gp.srzbl.config[y][x].equals(gp.srzbl.config[y+2][x])) {
                    res++;
                    for (int y1 = y-1; y1 >= 0; y1--){  // viem, ze som na najvyssej pretoze hladam od hora dole
                        gp.srzbl.config[y1+3][x] = gp.srzbl.config[y1][x];
                    }

                    gp.srzbl.config[0][x] = new Random().nextInt(6)+1;
                    gp.srzbl.config[1][x] = new Random().nextInt(6)+1;
                    gp.srzbl.config[2][x] = new Random().nextInt(6)+1;
                    return 1 + getMatches();
                }
                else if (x < gp.srzbl.config[0].length-2 && gp.srzbl.config[y][x].equals(gp.srzbl.config[y][x+1]) && gp.srzbl.config[y][x].equals(gp.srzbl.config[y][x+2])) {
                    res++;
                    for (int x1 = x; x1 <= x+2; x1++){
                        for (int y1 = y-1; y1 >= 0; y1--){
                            gp.srzbl.config[y1+1][x1] = gp.srzbl.config[y1][x1];
                        }
                        gp.srzbl.config[0][x1] = new Random().nextInt(6)+1;
                    }
                    return 1 + getMatches();
                }
            }
        }
        return 0;
    }
}
