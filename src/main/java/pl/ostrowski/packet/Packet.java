package pl.ostrowski.packet;

import enums.PacketType;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import lombok.Getter;
import pl.ostrowski.DataTypeUtilities;
import pl.ostrowski.factory.PacketTypeFactory;
import pl.ostrowski.util.TimePrinter;

public class Packet {

  // Header Size (bytes)
  public static int HEADER_SIZE = 24;

  // Identifier for the frame the data was retrieved on
  @Getter private final Long frameIdentifier;

  // Packet Format
  @Getter private final int packetFormat;

  // Game major version - "X.00"
  @Getter private final int gameMajorVersion;

  // Game minor version - "1.XX"
  @Getter private final int gameMinorVersion;

  // Version of this packet type, all start from 1
  @Getter private final int packetVersion;

  @Getter private final PacketType packetType;

  // Unique identifier for the session
  @Getter private final BigInteger sessionUID;

  // Session timestamp
  @Getter private final double sessionTime;

  // Index of player's car in the array
  @Getter private final int playerCarIndex;

  // Index of secondary player's car in the array
  @Getter private final int secondaryPlayerCarIndex;

  public Packet(byte[] content) {
    ByteBuffer bb = ByteBuffer.wrap(content);
    bb.order(ByteOrder.LITTLE_ENDIAN);

    packetFormat = DataTypeUtilities.convert_uint16(bb.getShort());
    gameMajorVersion = DataTypeUtilities.convert_uint8(bb.get());
    gameMinorVersion = DataTypeUtilities.convert_uint8(bb.get());
    packetVersion = DataTypeUtilities.convert_uint8(bb.get());
    packetType = PacketTypeFactory.createPacketType(DataTypeUtilities.convert_uint8(bb.get()));
    sessionUID = DataTypeUtilities.convert_uint64(bb.getLong());
    sessionTime = bb.getFloat();
    frameIdentifier = DataTypeUtilities.convert_uint32(bb.getInt());
    playerCarIndex = DataTypeUtilities.convert_uint8(bb.get());
    secondaryPlayerCarIndex = DataTypeUtilities.convert_uint8(bb.get());
  }

  @Override
  public String toString() {
    return "Telemetry is in F1 "
        + packetFormat
        + " format. Game version is "
        + gameMajorVersion
        + "."
        + +gameMinorVersion
        + ". UDP frame identifier is "
        + frameIdentifier
        + ", packet is in "
        + packetVersion
        + " version. Session UID is "
        + sessionUID
        + " and started "
        + TimePrinter.printFormattedSeconds((int) sessionTime)
        + " ago. Player car index is "
        + playerCarIndex
        + " and secondary player index (positive only in coop) is "
        + secondaryPlayerCarIndex
        + ".\n";
  }
}
