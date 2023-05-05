package BypassExcellencie;

import javafx.application.Platform;

import java.util.TimerTask;

public class Rdtsc extends TimerTask {
    private GamePane gp;

    public Rdtsc(GamePane gp) {
        this.gp = gp;
    }

    @Override
    public void run() {
        if (gp.srzbl.secs == 200){
            return;
        }
        Platform.runLater(new Runnable() {
            public void run() {
                gp.timeVal.setText(Integer.toString(gp.srzbl.secs));
            }
        });
        gp.srzbl.secs++;
    }
}