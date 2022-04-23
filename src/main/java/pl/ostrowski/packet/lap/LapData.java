package pl.ostrowski.packet.lap;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Getter;
import pl.ostrowski.DataTypeUtilities;
import pl.ostrowski.enums.DriverStatus;
import pl.ostrowski.enums.PitStatus;
import pl.ostrowski.enums.ResultStatus;
import pl.ostrowski.enums.Sector;
import pl.ostrowski.factory.BooleanFactory;
import pl.ostrowski.factory.DriverStatusFactory;
import pl.ostrowski.factory.PitStatusFactory;
import pl.ostrowski.factory.ResultStatusFactory;
import pl.ostrowski.factory.SectorFactory;

public class LapData {
  public static final int SIZE = 53;
  @Getter private final double lastLapTime;
  @Getter private final double currentLapTime;
  @Getter private final int sector1Time;
  @Getter private final int sector2Time;
  @Getter private final double bestLapTime;
  @Getter private final int bestLapNum;
  @Getter private final int bestLapSector1Time;
  @Getter private final int bestLapSector2Time;
  @Getter private final int bestLapSector3Time;
  @Getter private final int bestOverallSector1Time;
  @Getter private final int bestOverallSector1LapNum;
  @Getter private final int bestOverallSector2Time;
  @Getter private final int bestOverallSector2LapNum;
  @Getter private final int bestOverallSector3Time;
  @Getter private final int bestOverallSector3LapNum;
  @Getter private final double lapDistance;
  @Getter private final double totalDistance;
  @Getter private final double safetyCarDelta;
  @Getter private final int carPosition;
  @Getter private final int currentLapNum;
  @Getter private final PitStatus pitStatus;
  @Getter private final Sector sector;
  @Getter private final boolean currentLapInvalid;
  @Getter private final int penalties;
  @Getter private final int gridPosition;
  @Getter private final DriverStatus driverStatus;
  @Getter private final ResultStatus resultStatus;

  public LapData(byte[] content) {
    ByteBuffer bb = ByteBuffer.wrap(content);
    bb.order(ByteOrder.LITTLE_ENDIAN);

    lastLapTime = DataTypeUtilities.convert_float(bb.getFloat()); // 4
    currentLapTime = DataTypeUtilities.convert_float(bb.getFloat()); // 4
    sector1Time = DataTypeUtilities.convert_uint16(bb.getShort()); // 2
    sector2Time = DataTypeUtilities.convert_uint16(bb.getShort()); // 2
    bestLapTime = DataTypeUtilities.convert_float(bb.getFloat()); // 4
    bestLapNum = DataTypeUtilities.convert_uint8(bb.get()); // 1
    bestLapSector1Time = DataTypeUtilities.convert_uint16(bb.getShort()); // 2
    bestLapSector2Time = DataTypeUtilities.convert_uint16(bb.getShort()); // 2
    bestLapSector3Time = DataTypeUtilities.convert_uint16(bb.getShort()); // 2
    bestOverallSector1Time = DataTypeUtilities.convert_uint16(bb.getShort()); // 2
    bestOverallSector1LapNum = DataTypeUtilities.convert_uint8(bb.get()); // 1
    bestOverallSector2Time = DataTypeUtilities.convert_uint16(bb.getShort()); // 2
    bestOverallSector2LapNum = DataTypeUtilities.convert_uint8(bb.get()); // 1
    bestOverallSector3Time = DataTypeUtilities.convert_uint16(bb.getShort()); // 2
    bestOverallSector3LapNum = DataTypeUtilities.convert_uint8(bb.get()); // 1
    lapDistance = DataTypeUtilities.convert_float(bb.getFloat()); // 4
    totalDistance = DataTypeUtilities.convert_float(bb.getFloat()); // 4
    safetyCarDelta = DataTypeUtilities.convert_float(bb.getFloat()); // 4
    carPosition = DataTypeUtilities.convert_uint8(bb.get()); // 1
    currentLapNum = DataTypeUtilities.convert_uint8(bb.get()); // 1
    pitStatus = PitStatusFactory.createPitStatus(DataTypeUtilities.convert_uint8(bb.get())); // 1
    sector = SectorFactory.createSector(DataTypeUtilities.convert_uint8(bb.get())); // 1
    currentLapInvalid =
        BooleanFactory.createBoolean(DataTypeUtilities.convert_uint8(bb.get())); // 1
    penalties = DataTypeUtilities.convert_uint8(bb.get()); // 1
    gridPosition = DataTypeUtilities.convert_uint8(bb.get()); // 1
    driverStatus =
        DriverStatusFactory.createDriverStatus(DataTypeUtilities.convert_uint8(bb.get())); // 1
    resultStatus =
        ResultStatusFactory.createResultStatus(DataTypeUtilities.convert_uint8(bb.get())); // 1
  }

  private String formatSeconds(double seconds, boolean large) {
    if (seconds <= 0 || seconds == Float.POSITIVE_INFINITY) {
      return "";
    }
    if (large) {
      Date date = new Date((long) (seconds * 1000));
      return new SimpleDateFormat("m:ss.SSS").format(date);
    }
    if (seconds >= 60) {
      return ((int) (seconds / 60)) + ((int) seconds) + "." + (int) ((seconds * 10) % 10);
    }
    return ((int) seconds) + "." + (int) ((seconds * 10) % 10);
  }

  public String getLastLapTime(boolean large) {
    return formatSeconds(lastLapTime, large);
  }

  public String getCurrentLapTime(boolean large) {
    return formatSeconds(currentLapTime, large);
  }

  public String getBestLapTime(boolean large) {
    return formatSeconds(bestLapTime, large);
  }

  public String getSector1Time(boolean large) {
    if (sector == Sector.FIRST) {
      return formatSeconds(currentLapTime, large);
    } else if (sector == Sector.SECOND || sector == Sector.THIRD) {
      return formatSeconds(this.sector1Time, large);
    } else {
      return null;
    }
  }

  public String getSector2Time(boolean large) {
    if (sector == Sector.SECOND) {
      double sector2 = currentLapTime - sector1Time;
      return formatSeconds(sector2, large);
    } else if (sector == Sector.THIRD) {
      return formatSeconds(this.sector2Time, large);
    } else {
      return null;
    }
  }

  public String getSector3Time(boolean large) {
    if (sector == Sector.THIRD) {
      double sector3 = currentLapTime - sector1Time - sector2Time;
      return formatSeconds(sector3, large);
    } else {
      return null;
    }
  }

  public double getSector3Float() {
    if (sector == Sector.THIRD) {
      return currentLapTime - sector1Time - sector2Time;
    } else {
      return 0f;
    }
  }

  @Override
  public String toString() {
    String ret = "Last lap time: " + getLastLapTime(true) + "\n";
    ret += "Current lap time: " + getCurrentLapTime(true) + "\n";
    ret += "Best lap time: " + getBestLapTime(true) + "\n";
    ret += "Sector 1 time: " + getSector1Time(true) + "\n";
    ret += "Sector 2 time: " + getSector2Time(true) + "\n";
    ret += "Lap distance: " + lapDistance + " m\n";
    ret += "Total distance: " + totalDistance + " m\n";
    ret += "SC Delta: " + safetyCarDelta + "\n";
    ret += "Car position: " + carPosition + "\n";
    ret += "Current Lap: " + currentLapNum + "\n";
    ret += "Pit status: " + getPitStatus() + "\n";
    ret += "Sector: " + getSector() + "\n";
    ret += "Current Lap Invalid: " + isCurrentLapInvalid() + "\n";
    ret += "Penalties: " + penalties + "\n";
    ret += "Grid Position: " + gridPosition + "\n";
    ret += "Driver Status: " + getDriverStatus() + "\n";
    ret += "Result Status: " + getResultStatus() + "\n";

    return ret;
  }
}
