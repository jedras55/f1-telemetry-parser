package pl.ostrowski;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import enums.DriverStatus;
import pl.ostrowski.packet.lap.LapData;
import pl.ostrowski.packet.lap.PacketLapData;
import pl.ostrowski.packet.session.PacketSessionData;

public class ClosestDeltaFinder {

  private static final DecimalFormat df = new DecimalFormat("0.000");
  private final Map<Integer, Delta> deltas = new HashMap<>();
  int accuracyDistance = 5;
  int totalLapsInRace;
  int trackLength;

  public void setSession(PacketSessionData packetSessionData) {
    this.totalLapsInRace = packetSessionData.getTotalLaps();
    this.trackLength = packetSessionData.getTrackLength();
  }

  public void setDelta(PacketLapData packetLapData) {
    if (totalLapsInRace > 0) {
      packetLapData.getAllCarsLapData().forEach(
          carLapData -> {
            if (carLapData.getCurrentLapTimeInMS() > 0
                && carLapData.getDriverStatus() != DriverStatus.GARAGE) {
              int index = (int) Math.floor(carLapData.getTotalDistance() / accuracyDistance);
              Delta delta;
              if (deltas.containsKey(index)) {
                delta = deltas.get(index);
              } else {
                delta = new Delta();
              }
              delta.getDeltaMap().put(carLapData.getGridPosition(), packetLapData.getSessionTime());
              if (index > 0) {
                deltas.put(index, delta);
              }
            }
          });
    }
  }

  public String getClosestDelta(PacketLapData packetLapData) {
    df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));

    List<LapData> packetLapDataList =
        packetLapData.getAllCarsLapData().stream()
            .sorted(Comparator.comparingInt(LapData::getCarPosition))
            .filter(lapData -> lapData.getDriverStatus() != DriverStatus.GARAGE)
            .collect(Collectors.toList());

    double deltaToNext;
    double minDeltaToNext = Double.MAX_VALUE;
    int minDeltaToNextPosition = 0;
    double lastDeltaToLeader = 0;

    for (int i = 0; i < packetLapDataList.size(); i++) {
      LapData lapData = packetLapDataList.get(i);
      int index = (int) Math.floor(lapData.getTotalDistance() / accuracyDistance);
      Delta delta = deltas.get(index);
      if (delta != null) {
        double currentDriverTime = delta.getDeltaMap().get(lapData.getGridPosition());
        double fastestDriverTime = delta.getDeltaMap().entrySet().iterator().next().getValue();
        double deltaToLeader = currentDriverTime - fastestDriverTime;
        if (deltaToLeader == 0) {
          System.out.println("1. ---");
        } else {
          deltaToNext = deltaToLeader - lastDeltaToLeader;
          if (deltaToNext < minDeltaToNext) {
            minDeltaToNext = deltaToNext;
            minDeltaToNextPosition = i + 1;
          }
          System.out.println(
              (i + 1) + ". +" + df.format(deltaToLeader) + "    +" + df.format(deltaToNext));
        }
        lastDeltaToLeader = deltaToLeader;
      }
    }

    System.out.println(
        "Najmniejsza delta jest miedzy zawodnikami na "
            + (minDeltaToNextPosition - 1)
            + " i "
            + minDeltaToNextPosition
            + " pozycji");
    return "";
  }
}
