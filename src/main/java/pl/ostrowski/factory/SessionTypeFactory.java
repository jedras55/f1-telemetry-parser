package pl.ostrowski.factory;

import enums.SessionType;

public class SessionTypeFactory {
  public static SessionType createSessionType(short sessionTypeValue) {
    return switch (sessionTypeValue) {
      case 1 -> SessionType.PRACTICE_1;
      case 2 -> SessionType.PRACTICE_2;
      case 3 -> SessionType.PRACTICE_3;
      case 4 -> SessionType.PRACTICE_SHORT;
      case 5 -> SessionType.QUALIFYING_1;
      case 6 -> SessionType.QUALIFYING_2;
      case 7 -> SessionType.QUALIFYING_3;
      case 8 -> SessionType.QUALIFYING_SHORT;
      case 9 -> SessionType.QUALIFYING_ONE_SHOT;
      case 10 -> SessionType.RACE_1;
      case 11 -> SessionType.RACE_2;
      case 12 -> SessionType.TIME_TRIAL;
      default -> SessionType.UNKNOWN;
    };
  }
}
