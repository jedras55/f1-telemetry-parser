package pl.ostrowski.factory;

import enums.PacketType;

public class PacketTypeFactory {
  public static PacketType createPacketType(short packetTypeValue) {
    return switch (packetTypeValue) {
      case 0 -> PacketType.MOTION;
      case 1 -> PacketType.SESSION;
      case 2 -> PacketType.LAP;
      case 3 -> PacketType.EVENT;
      case 4 -> PacketType.PARTICIPANTS;
      case 5 -> PacketType.CAR_SETUPS;
      case 6 -> PacketType.CAR_TELEMETRY;
      case 7 -> PacketType.CAR_STATUS;
      case 8 -> PacketType.FINAL_CLASSIFICATION;
      case 9 -> PacketType.LOBBY_INFO;
      case 10 -> PacketType.CAR_DAMAGE;
      case 11 -> PacketType.SESSION_HISTORY;
      default -> PacketType.UNKNOWN;
    };
  }
}
