package pl.ostrowski.packet.car.telemetry;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import pl.ostrowski.DataTypeUtilities;
import pl.ostrowski.packet.Packet;

public class PacketCarTelemetryData extends Packet { // 1323

  // Packet Size in bytes (w/o header size)
  public static final int LENGTH = 1347;
  private static final int CAR_TELEMETRY_DATA_SIZE = 22;

  @Getter private final List<CarTelemetryData> carTelemetryDataList; // 1320
  @Getter private final int mfdPanelIndex; // 1
  @Getter private final int mfdPanelIndexSecondaryPlayer; // 1
  @Getter private final int suggestedGear; // 1

  public PacketCarTelemetryData(byte[] content) {
    super(Arrays.copyOfRange(content, 0, Packet.HEADER_SIZE));

    carTelemetryDataList = new ArrayList<>();

    int from = Packet.HEADER_SIZE;
    int to;
    byte[] carTelemetryBytes;
    while (from < Packet.HEADER_SIZE + CAR_TELEMETRY_DATA_SIZE * CarTelemetryData.LENGTH) {
      to = from + CarTelemetryData.LENGTH;

      carTelemetryBytes = Arrays.copyOfRange(content, from, to);

      CarTelemetryData carTelemetryData = new CarTelemetryData(carTelemetryBytes);
      carTelemetryDataList.add(carTelemetryData);

      from += CarTelemetryData.LENGTH;
    }

    ByteBuffer bb =
        ByteBuffer.wrap(
            Arrays.copyOfRange(
                content,
                Packet.HEADER_SIZE + carTelemetryDataList.size() * CarTelemetryData.LENGTH,
                Packet.HEADER_SIZE + carTelemetryDataList.size() * CarTelemetryData.LENGTH + 4));
    bb.order(ByteOrder.LITTLE_ENDIAN);
    mfdPanelIndex = DataTypeUtilities.convert_uint8(bb.get());
    mfdPanelIndexSecondaryPlayer = DataTypeUtilities.convert_uint8(bb.get());
    suggestedGear = DataTypeUtilities.convert_uint8(bb.get());
  }
}
