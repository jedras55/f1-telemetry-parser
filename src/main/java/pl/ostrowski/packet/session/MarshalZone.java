package pl.ostrowski.packet.session;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import lombok.Getter;
import pl.ostrowski.DataTypeUtilities;
import pl.ostrowski.enums.ZoneFlag;
import pl.ostrowski.factory.ZoneFlagFactory;

public final class MarshalZone {
  // Structure size in bytes
  public static final int SIZE = 5;

  // Percent (0-100) of way through the lap the marshal zone starts
  @Getter private final int zoneStartTrackPercent;

  @Getter private final ZoneFlag zoneFlag;

  public MarshalZone(byte[] content) {
    ByteBuffer bb = ByteBuffer.wrap(content);
    bb.order(ByteOrder.LITTLE_ENDIAN);

    zoneStartTrackPercent = (int) (DataTypeUtilities.convert_float(bb.getFloat()) * 100);
    zoneFlag = ZoneFlagFactory.createZoneFlag(DataTypeUtilities.convert_uint8(bb.get()));
  }

  @Override
  public String toString() {
    return "Marshal zone at "
        + zoneStartTrackPercent
        + " percent of track, flag is "
        + zoneFlag.getDescription();
  }
}
