package pl.ostrowski.packet.participants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import pl.ostrowski.DataTypeUtilities;
import pl.ostrowski.packet.Packet;

public class PacketParticipantsData extends Packet {

  @Getter private final int numberOfCars;
  @Getter private final List<ParticipantData> participants = new ArrayList<>();

  public PacketParticipantsData(byte[] content) {
    super(Arrays.copyOfRange(content, 0, Packet.HEADER_SIZE));

    numberOfCars = DataTypeUtilities.convert_uint8(content[21]);

    int from = Packet.HEADER_SIZE + 1;
    int to;
    byte[] participant;
    int count = 0;
    while (count < numberOfCars) {
      to = from + ParticipantData.LENGTH;

      participant = Arrays.copyOfRange(content, from, to);

      ParticipantData participantData = new ParticipantData(participant);
      participants.add(participantData);

      from += ParticipantData.LENGTH;
      count++;
    }
  }

  @Override
  public String toString() {
    String ret = super.toString();
    ret += "Num car: " + numberOfCars + "\n";
    for (ParticipantData par : participants) {
      ret += "\n------------\n";
      ret += par.toString();
    }

    return ret;
  }
}
