package pl.ostrowski.factory;

import pl.ostrowski.enums.Sector;

public class SectorFactory {
  public static Sector createSector(short sectorValue) {
    return switch (sectorValue) {
      case 0 -> Sector.FIRST;
      case 1 -> Sector.SECOND;
      case 2 -> Sector.THIRD;
      default -> Sector.UNKNOWN;
    };
  }
}
