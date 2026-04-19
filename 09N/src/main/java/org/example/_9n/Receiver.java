package org.example._9n;

import java.io.IOException;
import java.io.PipedReader;

class Receiver extends Thread {
  private PipedReader in;
  public Receiver(Sender sender) throws IOException {
    in = new PipedReader(sender.getPipedWriter());
  }
  public void run() {
    try {
      while(true) {
        System.out.println("Read: " + (char)in.read());
      }
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
}
