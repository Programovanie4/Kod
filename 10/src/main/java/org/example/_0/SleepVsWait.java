package org.example._0;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class SleepVsWait {
    private static Object objekt = new Object();

    static long start = System.currentTimeMillis();
    static String time() { return ""+(System.currentTimeMillis()-start)/1000 + " s. "; }

    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        new Thread(() -> {
                System.out.println(time() + Thread.currentThread().getName() + " sa nastartoval");
                try { Thread.sleep(6000); } catch (InterruptedException e) {}
                System.out.println(time() +"Object " + objekt + " sa ide unlocknut");
                // IllegalMonitorStateException
                synchronized (objekt) {
                    objekt.notify();
                }
            }
        ).start();
        System.out.println(time() +mainThread.getName() + " sa zobudi za 5 sek.");
        Thread.sleep(5000);
        System.out.println(time() +mainThread.getName() + " zobudil sa");

        // IllegalMonitorStateException
        synchronized (objekt) {
            System.out.println(time() +"Object " + objekt + " sa ide locknut na max. 7 sek. ");
            objekt.wait(7000);
            System.out.println(time() +"Object " + objekt + " je volny");
        }
    }
}
