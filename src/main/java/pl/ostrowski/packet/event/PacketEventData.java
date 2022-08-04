package pl.ostrowski.packet.event;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import lombok.Getter;
import pl.ostrowski.packet.Packet;

public class PacketEventData extends Packet {

  private static final int SYMBOL_LENGTH = 4;
  private final String eventStringCode;
  @Getter private final EventType eventType;
  @Getter private Event event;

  public PacketEventData(byte[] content) {
    super(Arrays.copyOfRange(content, 0, Packet.HEADER_SIZE));
    eventStringCode =
        new String(
            Arrays.copyOfRange(content, Packet.HEADER_SIZE, Packet.HEADER_SIZE + SYMBOL_LENGTH),
            StandardCharsets.UTF_8);
    eventType = EventType.valueOf(eventStringCode);
    queryEvent(content);
  }

  private void queryEvent(byte[] content) {
    switch (eventType) {
      case FTLP -> this.event =
          new FastestLap(
              Arrays.copyOfRange(
                  content,
                  Packet.HEADER_SIZE + SYMBOL_LENGTH,
                  Packet.HEADER_SIZE + SYMBOL_LENGTH + FastestLap.LENGTH));
      case RTMT -> this.event =
          new Retirement(
              Arrays.copyOfRange(
                  content,
                  Packet.HEADER_SIZE + SYMBOL_LENGTH,
                  Packet.HEADER_SIZE + SYMBOL_LENGTH + Retirement.LENGTH));
      case SPTP -> this.event =
          new SpeedTrapTriggered(
              Arrays.copyOfRange(
                  content,
                  Packet.HEADER_SIZE + SYMBOL_LENGTH,
                  Packet.HEADER_SIZE + SYMBOL_LENGTH + SpeedTrapTriggered.LENGTH));
      case TMPT -> this.event =
          new TeammateInPits(
              Arrays.copyOfRange(
                  content,
                  Packet.HEADER_SIZE + SYMBOL_LENGTH,
                  Packet.HEADER_SIZE + SYMBOL_LENGTH + TeammateInPits.LENGTH));
      case RCWN -> this.event =
          new RaceWinner(
              Arrays.copyOfRange(
                  content,
                  Packet.HEADER_SIZE + SYMBOL_LENGTH,
                  Packet.HEADER_SIZE + SYMBOL_LENGTH + RaceWinner.LENGTH));
      case PENA -> this.event =
          new PenaltyIssued(
              Arrays.copyOfRange(
                  content,
                  Packet.HEADER_SIZE + SYMBOL_LENGTH,
                  Packet.HEADER_SIZE + SYMBOL_LENGTH + PenaltyIssued.LENGTH));
      default -> this.event = null;
    }
  }

  @Override
  public String toString() {
    String ret = super.toString();
    ret += "Event String Code: " + eventStringCode + "\n";
    return ret;
  }
}
