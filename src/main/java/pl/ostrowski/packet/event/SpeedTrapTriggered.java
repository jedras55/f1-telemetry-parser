package pl.ostrowski.packet.event;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import lombok.Getter;
import pl.ostrowski.DataTypeUtilities;

public class SpeedTrapTriggered extends Event {

  public static final int LENGTH = 5;
  @Getter private final int vehicleId;
  @Getter private final double speed;

  public SpeedTrapTriggered(byte[] content) {
    ByteBuffer bb = ByteBuffer.wrap(content);
    bb.order(ByteOrder.LITTLE_ENDIAN);

    vehicleId = DataTypeUtilities.convert_uint8(bb.get());
    speed = DataTypeUtilities.convert_float(bb.getFloat());
  }
}
