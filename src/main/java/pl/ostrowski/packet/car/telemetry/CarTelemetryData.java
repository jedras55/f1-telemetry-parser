package pl.ostrowski.packet.car.telemetry;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import lombok.Getter;
import pl.ostrowski.DataTypeUtilities;

@Getter
public class CarTelemetryData { // 60

  public static final int LENGTH = 60;

  private final int speed; // 2
  private final double throttle; // 4
  private final double steer; // 4
  private final double brake; // 4
  private final int clutch; // 1
  private final int gear; // 1
  private final int engineRPM; // 2
  private final int drs; // 1
  private final int revLightsPercent; // 1
  private final int revLightsBitValue; // 2
  private final int[] brakesTemperature; // 8
  private final int[] tyresSurfaceTemperature; // 4
  private final int[] tyresInnerTemperature; // 4
  private final int engineTemperature; // 2
  private final double[] tyresPressure; // 16
  private final int[] surfaceType; // 4

  public CarTelemetryData(byte[] content) {
    ByteBuffer bb = ByteBuffer.wrap(content);
    bb.order(ByteOrder.LITTLE_ENDIAN);

    speed = DataTypeUtilities.convert_uint16(bb.getShort());
    throttle = DataTypeUtilities.convert_float(bb.getFloat());
    steer = DataTypeUtilities.convert_float(bb.getFloat());
    brake = DataTypeUtilities.convert_float(bb.getFloat());
    clutch = DataTypeUtilities.convert_uint8(bb.get());
    gear = DataTypeUtilities.convert_uint8(bb.get());
    engineRPM = DataTypeUtilities.convert_uint16(bb.getShort());
    drs = DataTypeUtilities.convert_uint8(bb.get());
    revLightsPercent = DataTypeUtilities.convert_uint8(bb.get());
    revLightsBitValue = DataTypeUtilities.convert_uint16(bb.getShort());

    brakesTemperature = new int[4];
    brakesTemperature[0] = DataTypeUtilities.convert_uint16(bb.getShort());
    brakesTemperature[1] = DataTypeUtilities.convert_uint16(bb.getShort());
    brakesTemperature[2] = DataTypeUtilities.convert_uint16(bb.getShort());
    brakesTemperature[3] = DataTypeUtilities.convert_uint16(bb.getShort());

    tyresSurfaceTemperature = new int[4];
    tyresSurfaceTemperature[0] = DataTypeUtilities.convert_uint8(bb.get());
    tyresSurfaceTemperature[1] = DataTypeUtilities.convert_uint8(bb.get());
    tyresSurfaceTemperature[2] = DataTypeUtilities.convert_uint8(bb.get());
    tyresSurfaceTemperature[3] = DataTypeUtilities.convert_uint8(bb.get());

    tyresInnerTemperature = new int[4];
    tyresInnerTemperature[0] = DataTypeUtilities.convert_uint8(bb.get());
    tyresInnerTemperature[1] = DataTypeUtilities.convert_uint8(bb.get());
    tyresInnerTemperature[2] = DataTypeUtilities.convert_uint8(bb.get());
    tyresInnerTemperature[3] = DataTypeUtilities.convert_uint8(bb.get());

    engineTemperature = DataTypeUtilities.convert_uint16(bb.getShort());

    tyresPressure = new double[4];
    tyresPressure[0] = DataTypeUtilities.convert_float(bb.getFloat());
    tyresPressure[1] = DataTypeUtilities.convert_float(bb.getFloat());
    tyresPressure[2] = DataTypeUtilities.convert_float(bb.getFloat());
    tyresPressure[3] = DataTypeUtilities.convert_float(bb.getFloat());

    surfaceType = new int[4];
    surfaceType[0] = DataTypeUtilities.convert_uint8(bb.get());
    surfaceType[1] = DataTypeUtilities.convert_uint8(bb.get());
    surfaceType[2] = DataTypeUtilities.convert_uint8(bb.get());
    surfaceType[3] = DataTypeUtilities.convert_uint8(bb.get());
  }
}
