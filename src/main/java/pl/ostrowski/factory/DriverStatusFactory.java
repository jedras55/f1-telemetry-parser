package pl.ostrowski.factory;

import pl.ostrowski.enums.DriverStatus;

public class DriverStatusFactory {
  public static DriverStatus createDriverStatus(short driverStatusValue) {
    return switch (driverStatusValue) {
      case 0 -> DriverStatus.GARAGE;
      case 1 -> DriverStatus.FLYING_LAP;
      case 2 -> DriverStatus.IN_LAP;
      case 3 -> DriverStatus.OUT_LAP;
      case 4 -> DriverStatus.ON_TRACK;
      default -> DriverStatus.UNKNOWN;
    };
  }
}
