package pl.ostrowski.factory;

import enums.PitStatus;

public class PitStatusFactory {
  public static PitStatus createPitStatus(short pitStatusValue) {
    return switch (pitStatusValue) {
      case 0 -> PitStatus.NONE;
      case 1 -> PitStatus.PITTING;
      case 2 -> PitStatus.PIT_AREA;
      default -> PitStatus.UNKNOWN;
    };
  }
}
