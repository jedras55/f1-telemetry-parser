package pl.ostrowski.factory;

import pl.ostrowski.enums.SafetyCar;

public class SafetyCarFactory {
  public static SafetyCar createSafetyCar(short safetyCarValue) {
    return switch (safetyCarValue) {
      case 0 -> SafetyCar.NO_SC;
      case 1 -> SafetyCar.SC;
      case 2 -> SafetyCar.VSC;
      default -> SafetyCar.UNKNOWN;
    };
  }
}
