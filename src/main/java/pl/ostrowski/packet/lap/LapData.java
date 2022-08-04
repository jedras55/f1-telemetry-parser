package pl.ostrowski.packet.lap;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Getter;
import pl.ostrowski.DataTypeUtilities;
import enums.DriverStatus;
import enums.PitStatus;
import enums.ResultStatus;
import enums.Sector;
import pl.ostrowski.factory.BooleanFactory;
import pl.ostrowski.factory.DriverStatusFactory;
import pl.ostrowski.factory.PitStatusFactory;
import pl.ostrowski.factory.ResultStatusFactory;
import pl.ostrowski.factory.SectorFactory;

public class LapData {
  public static final int SIZE = 43;

  @Getter private final Long lastLapTimeInMS;
  @Getter private final Long currentLapTimeInMS;
  @Getter private final int sector1TimeInMS;
  @Getter private final int sector2TimeInMS;
  @Getter private final double lapDistance;

  @Getter private final double totalDistance;

  @Getter private final double safetyCarDelta;
  @Getter private final int carPosition;
  @Getter private final int currentLapNum;
  @Getter private final PitStatus pitStatus;
  @Getter private final int numPitStops;
  @Getter private final Sector sector;
  @Getter private final boolean currentLapInvalid;
  @Getter private final int penalties;
  @Getter private final int warnings;
  @Getter private final int numUnservedDriveThroughPens;
  @Getter private final int numUnservedStopGoPens;
  @Getter private final int gridPosition;
  @Getter private final DriverStatus driverStatus;

  @Getter private final ResultStatus resultStatus;

  @Getter private final boolean pitLaneTimerActive;
  @Getter private final int pitLaneTimeInLaneInMS;
  @Getter private final int pitStopTimerInMS;
  @Getter private final boolean pitStopShouldServePen;

  public LapData(byte[] content) {
    ByteBuffer bb = ByteBuffer.wrap(content);
    bb.order(ByteOrder.LITTLE_ENDIAN);

    lastLapTimeInMS = DataTypeUtilities.convert_uint32(bb.getInt()); // 4
    currentLapTimeInMS = DataTypeUtilities.convert_uint32(bb.getInt()); // 4
    sector1TimeInMS = DataTypeUtilities.convert_uint16(bb.getShort()); // 2
    sector2TimeInMS = DataTypeUtilities.convert_uint16(bb.getShort()); // 2
    lapDistance = DataTypeUtilities.convert_float(bb.getFloat()); // 4

    totalDistance = DataTypeUtilities.convert_float(bb.getFloat()); // 4

    safetyCarDelta = DataTypeUtilities.convert_float(bb.getFloat()); // 4
    carPosition = DataTypeUtilities.convert_uint8(bb.get()); // 1
    currentLapNum = DataTypeUtilities.convert_uint8(bb.get()); // 1
    pitStatus = PitStatusFactory.createPitStatus(DataTypeUtilities.convert_uint8(bb.get())); // 1
    numPitStops = DataTypeUtilities.convert_uint8(bb.get()); // 1
    sector = SectorFactory.createSector(DataTypeUtilities.convert_uint8(bb.get())); // 1
    currentLapInvalid =
        BooleanFactory.createBoolean(DataTypeUtilities.convert_uint8(bb.get())); // 1
    penalties = DataTypeUtilities.convert_uint8(bb.get()); // 1
    warnings = DataTypeUtilities.convert_uint8(bb.get()); // 1
    numUnservedDriveThroughPens = DataTypeUtilities.convert_uint8(bb.get()); // 1
    numUnservedStopGoPens = DataTypeUtilities.convert_uint8(bb.get()); // 1
    gridPosition = DataTypeUtilities.convert_uint8(bb.get()); // 1
    driverStatus =
        DriverStatusFactory.createDriverStatus(DataTypeUtilities.convert_uint8(bb.get())); // 1

    resultStatus =
        ResultStatusFactory.createResultStatus(DataTypeUtilities.convert_uint8(bb.get())); // 1

    pitLaneTimerActive =
        BooleanFactory.createBoolean(DataTypeUtilities.convert_uint8(bb.get())); // 1
    pitLaneTimeInLaneInMS = DataTypeUtilities.convert_uint16(bb.getShort()); // 2
    pitStopTimerInMS = DataTypeUtilities.convert_uint16(bb.getShort()); // 2
    pitStopShouldServePen =
        BooleanFactory.createBoolean(DataTypeUtilities.convert_uint8(bb.get())); // 1
  }

  private String formatSeconds(double ms, boolean large) {
    double seconds = ms / 1000;
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
    return formatSeconds(lastLapTimeInMS, large);
  }

  public String getCurrentLapTime(boolean large) {
    return formatSeconds(currentLapTimeInMS, large);
  }

  public String getSector1Time(boolean large) {
    if (sector == Sector.FIRST) {
      return formatSeconds(currentLapTimeInMS, large);
    } else if (sector == Sector.SECOND || sector == Sector.THIRD) {
      return formatSeconds(this.sector1TimeInMS, large);
    } else {
      return null;
    }
  }

  public String getSector2Time(boolean large) {
    if (sector == Sector.SECOND) {
      double sector2 = currentLapTimeInMS - sector1TimeInMS;
      return formatSeconds(sector2, large);
    } else if (sector == Sector.THIRD) {
      return formatSeconds(this.sector2TimeInMS, large);
    } else {
      return null;
    }
  }

  public String getSector3Time(boolean large) {
    if (sector == Sector.THIRD) {
      double sector3 = currentLapTimeInMS - sector1TimeInMS - sector2TimeInMS;
      return formatSeconds(sector3, large);
    } else {
      return null;
    }
  }

  public double getSector3Float() {
    if (sector == Sector.THIRD) {
      return currentLapTimeInMS - sector1TimeInMS - sector2TimeInMS;
    } else {
      return 0f;
    }
  }

  @Override
  public String toString() {
    String ret = "Last lap time: " + getLastLapTime(true) + "\n";
    ret += "Current lap time: " + getCurrentLapTime(true) + "\n";
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
