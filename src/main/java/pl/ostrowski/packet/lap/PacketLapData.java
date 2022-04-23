package pl.ostrowski.packet.lap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pl.ostrowski.packet.Packet;

public class PacketLapData extends Packet {

  public static int LENGTH = 1166;
  public List<LapData> allCarsLapData = new ArrayList<>();

  public PacketLapData(byte[] content) {
    super(Arrays.copyOfRange(content, 0, Packet.HEADER_SIZE));

    int from = Packet.HEADER_SIZE;
    int to;
    byte[] lapBytes;
    while (from < LENGTH) {
      to = from + LapData.SIZE;

      lapBytes = Arrays.copyOfRange(content, from, to);

      LapData lapData = new LapData(lapBytes);
      allCarsLapData.add(lapData);

      from += LapData.SIZE;
    }
  }

  @Override
  public String toString() {

    String ret = super.toString();

    for (LapData lapData : allCarsLapData) {
      ret += "\n------------\n";
      ret += lapData.toString();
    }

    return ret;
  }
}
