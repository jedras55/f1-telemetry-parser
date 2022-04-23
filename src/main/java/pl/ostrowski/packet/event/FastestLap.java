package pl.ostrowski.packet.event;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import lombok.Getter;
import pl.ostrowski.DataTypeUtilities;

public class FastestLap extends Event {

  public static final int LENGTH = 5;
  @Getter private final int vehicleId;
  @Getter private final double lapTime;

  public FastestLap(byte[] content) {
    ByteBuffer bb = ByteBuffer.wrap(content);
    bb.order(ByteOrder.LITTLE_ENDIAN);

    vehicleId = DataTypeUtilities.convert_uint8(bb.get());
    lapTime = DataTypeUtilities.convert_float(bb.getFloat());
  }
}
