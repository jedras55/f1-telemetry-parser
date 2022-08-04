package pl.ostrowski;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import pl.ostrowski.packet.Packet;
import pl.ostrowski.packet.PacketFactory;

public class EchoServer extends Thread {

  private final byte[] buf = new byte[2048];
  private final PacketRepository packetRepository;

  private DatagramSocket socket;
  private boolean running;

  public EchoServer(PacketRepository packetRepository) {
    this.packetRepository = packetRepository;
    try {
      socket = new DatagramSocket(20777);
    } catch (SocketException e) {
      e.printStackTrace();
    }
  }

  public void run() {
    running = true;
    while (running) {
      DatagramPacket packet = new DatagramPacket(buf, buf.length);
      try {
        socket.receive(packet);
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        packet = new DatagramPacket(buf, buf.length, address, port);
        newPacket(packet.getData());
        socket.send(packet);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    socket.close();
  }

  public void close() {
    running = false;
  }

  public synchronized void newPacket(byte[] content) {
    String clientId = "kacper_zabaglo";

    Packet packet = PacketFactory.getPacket(content);
    if (packet != null) {
      packetRepository.savePacket(packet, clientId);
    }
  }
}
