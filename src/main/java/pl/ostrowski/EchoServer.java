package pl.ostrowski;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import pl.ostrowski.packet.Packet;
import pl.ostrowski.packet.PacketFactory;
import pl.ostrowski.packet.event.PacketEventData;
import pl.ostrowski.packet.lap.PacketLapData;
import pl.ostrowski.packet.motion.PacketMotionData;
import pl.ostrowski.packet.session.PacketSessionData;

public class EchoServer extends Thread {

  public static PacketSessionData packetSessionData = null;
  public static PacketMotionData packetMotionData = null;
  public static PacketLapData packetLapData = null;
  public static PacketEventData packetEventData = null;
  private final byte[] buf = new byte[2048];
  private final PacketStorage packetStorage;
  private DatagramSocket socket;
  private boolean running;

  public EchoServer(PacketStorage packetStorage1) {
    packetStorage = packetStorage1;
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
    Packet packet = PacketFactory.getPacket(content);
    if (packet != null) {
      if (packet instanceof PacketSessionData) {
        packetSessionData = (PacketSessionData) packet;
        packetStorage.setPacketSessionData((PacketSessionData) packet);
      } else if (packet instanceof PacketMotionData) {
        packetMotionData = (PacketMotionData) packet;
        packetStorage.setPacketMotionData((PacketMotionData) packet);
      } else if (packet instanceof PacketLapData) {
        packetLapData = (PacketLapData) packet;
        packetStorage.setPacketLapData((PacketLapData) packet);

      } else if (packet instanceof PacketEventData) {
        packetEventData = (PacketEventData) packet;
        packetStorage.setPacketEventData((PacketEventData) packet);
      }
    }
  }
}
