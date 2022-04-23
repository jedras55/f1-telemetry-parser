package pl.ostrowski.packet.motion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import pl.ostrowski.packet.Packet;

public class PacketMotionData extends Packet {

  private static final int CAR_MOTION_NUMBER = 22;
  @Getter
  private final List<CarMotionData> carMotionDataList = new ArrayList<>();
  @Getter
  private ExtraCarMotionData extraCarMotionData;

  public PacketMotionData(byte[] content) {
    super(Arrays.copyOfRange(content, 0, Packet.HEADER_SIZE));

    int from = Packet.HEADER_SIZE;
    int to;
    int count = 0;
    while (count < CAR_MOTION_NUMBER) {
      to = from + CarMotionData.SIZE;

      byte[] carMotionDataBytes = Arrays.copyOfRange(content, from, to);

      CarMotionData carMotionData = new CarMotionData(carMotionDataBytes);
      carMotionDataList.add(carMotionData);

      from += CarMotionData.SIZE;
      count++;
      extraCarMotionData =
          new ExtraCarMotionData(
              Arrays.copyOfRange(
                  content,
                  Packet.HEADER_SIZE + CAR_MOTION_NUMBER * CarMotionData.SIZE,
                  Packet.HEADER_SIZE
                      + CAR_MOTION_NUMBER * CarMotionData.SIZE
                      + ExtraCarMotionData.SIZE));
    }
  }

  @Override
  public String toString() {
    String ret = super.toString();

    for (CarMotionData cmd : carMotionDataList) {
      ret += "-----------------\n";
      ret += cmd.toString();
    }

    ret += extraCarMotionData.toString();
    return ret;
  }
}
