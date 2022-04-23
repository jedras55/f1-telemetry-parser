package pl.ostrowski.packet.event;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import lombok.Getter;
import pl.ostrowski.DataTypeUtilities;

public class TeammateInPits extends Event {
  public static final int LENGTH = 1;
  @Getter private final int vehicleId;

  public TeammateInPits(byte[] content) {
    ByteBuffer bb = ByteBuffer.wrap(content);
    bb.order(ByteOrder.LITTLE_ENDIAN);

    vehicleId = DataTypeUtilities.convert_uint8(bb.get());
  }
}
