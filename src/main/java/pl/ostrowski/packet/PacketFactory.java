package pl.ostrowski.packet;

import pl.ostrowski.packet.event.PacketEventData;
import pl.ostrowski.packet.lap.PacketLapData;
import pl.ostrowski.packet.motion.PacketMotionData;
import pl.ostrowski.packet.session.PacketSessionData;

public class PacketFactory {

  public static Packet getPacket(byte[] content) {
    Packet p = new Packet(content);
    return switch (p.getPacketType()) {
      case MOTION -> new PacketMotionData(content);
      case SESSION -> new PacketSessionData(content);
      case LAP -> new PacketLapData(content);
      case EVENT -> new PacketEventData(content);
      default -> null;
    };
  }
}
