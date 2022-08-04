package pl.ostrowski;

import pl.ostrowski.packet.Packet;

public interface PacketRepository {

  void savePacket(Packet packet, String clientId);
}
