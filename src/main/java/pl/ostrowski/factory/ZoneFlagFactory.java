package pl.ostrowski.factory;

import pl.ostrowski.enums.ZoneFlag;

public class ZoneFlagFactory {
  public static ZoneFlag createZoneFlag(short zoneFlagValue) {
    return switch (zoneFlagValue) {
      case 0 -> ZoneFlag.NONE;
      case 1 -> ZoneFlag.GREEN;
      case 2 -> ZoneFlag.BLUE;
      case 3 -> ZoneFlag.YELLOW;
      case 4 -> ZoneFlag.RED;
      default -> ZoneFlag.UNKNOWN;
    };
  }
}
