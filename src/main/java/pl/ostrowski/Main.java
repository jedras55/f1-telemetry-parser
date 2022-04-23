package pl.ostrowski;

import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {
    EchoServer echoServer = new EchoServer(new PacketStorage());
    echoServer.start();

    Scanner scanner = new Scanner(System.in);

    int choice;
    do {
      System.out.println("Choose packet to read");
      System.out.println("1. Car motion data packet (details about all cars motion and location");
      System.out.println("2. Session data packet (details about session in progress");
      System.out.println("3. Lap data packet (details about lap of all cars in session");
      System.out.println("4. Event data packet (details about lap of all cars in session");
      System.out.println("0. Exit");
      choice = scanner.nextInt();
      switch (choice) {
        case 1 -> System.out.println(EchoServer.packetMotionData);
        case 2 -> System.out.println(EchoServer.packetSessionData);
        case 3 -> System.out.println(EchoServer.packetLapData);
        case 4 -> System.out.println(EchoServer.packetEventData);
        case 0 -> echoServer.interrupt();
        default -> System.out.println("Incorrect choice!");
      }
    } while (choice > 0);
  }
}
