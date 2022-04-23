package pl.ostrowski.packet.motion;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import lombok.Getter;
import pl.ostrowski.DataTypeUtilities;

public class CarMotionData {

  public static int SIZE = 60;

  @Getter private final double worldPositionX;
  @Getter private final double worldPositionY;
  @Getter private final double worldPositionZ;
  @Getter private final double worldVelocityX;
  @Getter private final double worldVelocityY;
  @Getter private final double worldVelocityZ;
  @Getter private final int worldForwardDirX;
  @Getter private final int worldForwardDirY;
  @Getter private final int worldForwardDirZ;
  @Getter private final int worldRightDirX;
  @Getter private final int worldRightDirY;
  @Getter private final int worldRightDirZ;
  @Getter private final double gForceLateral;
  @Getter private final double gForceLongitudinal;
  @Getter private final double gForceVertical;
  @Getter private final double yaw;
  @Getter private final double pitch;
  @Getter private final double roll;

  public CarMotionData(byte[] content) {
    ByteBuffer bb = ByteBuffer.wrap(content);
    bb.order(ByteOrder.LITTLE_ENDIAN);

    worldPositionX = DataTypeUtilities.convert_float(bb.getFloat());
    worldPositionY = DataTypeUtilities.convert_float(bb.getFloat());
    worldPositionZ = DataTypeUtilities.convert_float(bb.getFloat());
    worldVelocityX = DataTypeUtilities.convert_float(bb.getFloat());
    worldVelocityY = DataTypeUtilities.convert_float(bb.getFloat());
    worldVelocityZ = DataTypeUtilities.convert_float(bb.getFloat());

    worldForwardDirX = (int) DataTypeUtilities.convert_float_normalised_vector(bb.getShort());
    worldForwardDirY = (int) DataTypeUtilities.convert_float_normalised_vector(bb.getShort());
    worldForwardDirZ = (int) DataTypeUtilities.convert_float_normalised_vector(bb.getShort());
    worldRightDirX = (int) DataTypeUtilities.convert_float_normalised_vector(bb.getShort());
    worldRightDirY = (int) DataTypeUtilities.convert_float_normalised_vector(bb.getShort());
    worldRightDirZ = (int) DataTypeUtilities.convert_float_normalised_vector(bb.getShort());

    gForceLateral = DataTypeUtilities.convert_float(bb.getFloat());
    gForceLongitudinal = DataTypeUtilities.convert_float(bb.getFloat());
    gForceVertical = DataTypeUtilities.convert_float(bb.getFloat());
    yaw = DataTypeUtilities.convert_float(bb.getFloat());
    pitch = DataTypeUtilities.convert_float(bb.getFloat());
    roll = DataTypeUtilities.convert_float(bb.getFloat());
  }

  public String toString() {
    String ret = "World Position X: " + worldPositionX + "\n";
    ret += "World Position Y: " + worldPositionY + "\n";
    ret += "World Position Z: " + worldPositionZ + "\n";

    ret += "World Forward Dir X: " + worldForwardDirX + "\n";
    ret += "World Forward Dir Y: " + worldForwardDirY + "\n";
    ret += "World Forward Dir Z: " + worldForwardDirZ + "\n";
    ret += "World Right Dir X: " + worldRightDirX + "\n";
    ret += "World Right Dir Y: " + worldRightDirY + "\n";
    ret += "World Right Dir Z: " + worldRightDirZ + "\n";

    ret += "G Force Lateral: " + gForceLateral + "\n";
    ret += "G Force Longitudinal: " + gForceLongitudinal + "\n";
    ret += "G Force Vertical: " + gForceVertical + "\n";
    ret += "Yaw: " + yaw + "\n";
    ret += "Pitch: " + pitch + "\n";
    ret += "Roll: " + roll + "\n";

    return ret;
  }
}
