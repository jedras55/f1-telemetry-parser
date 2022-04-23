package pl.ostrowski.packet.event;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import lombok.Getter;
import pl.ostrowski.DataTypeUtilities;
import pl.ostrowski.enums.InfringementType;
import pl.ostrowski.enums.PenaltyType;
import pl.ostrowski.factory.InfringementTypeFactory;
import pl.ostrowski.factory.PenaltyTypeFactory;

public class PenaltyIssued extends Event {
  public static final int LENGTH = 7;
  @Getter private final PenaltyType penaltyType;
  @Getter private final InfringementType infringementType;
  @Getter private final int vehicleId;
  @Getter private final int otherVehicleId;
  @Getter private final int time;
  @Getter private final int lapNumber;
  @Getter private final int placesGained;

  public PenaltyIssued(byte[] content) {
    ByteBuffer bb = ByteBuffer.wrap(content);
    bb.order(ByteOrder.LITTLE_ENDIAN);

    penaltyType = PenaltyTypeFactory.createPenaltyType(DataTypeUtilities.convert_uint8(bb.get()));
    infringementType =
        InfringementTypeFactory.createInfringementType(DataTypeUtilities.convert_uint8(bb.get()));
    vehicleId = DataTypeUtilities.convert_uint8(bb.get());
    otherVehicleId = DataTypeUtilities.convert_uint8(bb.get());
    time = DataTypeUtilities.convert_uint8(bb.get());
    lapNumber = DataTypeUtilities.convert_uint8(bb.get());
    placesGained = DataTypeUtilities.convert_uint8(bb.get());
  }
}
