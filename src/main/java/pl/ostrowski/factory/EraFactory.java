package pl.ostrowski.factory;

import pl.ostrowski.enums.Era;

public class EraFactory {
  public static Era createSessionType(short eraValue) {
    return switch (eraValue) {
      case 0 -> Era.F1_MODERN;
      case 1 -> Era.F1_CLASSIC;
      case 2 -> Era.F2;
      case 3 -> Era.F1_GENERIC;
      default -> Era.UNKNOWN;
    };
  }
}
