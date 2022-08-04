package pl.ostrowski.factory;

import enums.PenaltyType;

public class PenaltyTypeFactory {
  public static PenaltyType createPenaltyType(short penaltyValue) {
    return switch (penaltyValue) {
      case 0 -> PenaltyType.DT;
      case 1 -> PenaltyType.STOP_AND_GO;
      case 2 -> PenaltyType.GRID_PENALTY;
      case 3 -> PenaltyType.PENALTY_REMINDER;
      case 4 -> PenaltyType.TIME_PENALTY;
      case 5 -> PenaltyType.WARNING;
      case 6 -> PenaltyType.DISQUALIFIED;
      case 7 -> PenaltyType.REMOVED_FROM_FORMATION_LAP;
      case 8 -> PenaltyType.PARKED_TOO_LONG_TIMER;
      case 9 -> PenaltyType.TYRE_REGULATIONS;
      case 10 -> PenaltyType.THIS_LAP_INVALIDATED;
      case 11 -> PenaltyType.THIS_AND_NEXT_LAP_INVALIDATED;
      case 12 -> PenaltyType.THIS_LAP_INVALIDATED_WITHOUT_REASON;
      case 13 -> PenaltyType.THIS_AND_NEXT_LAP_INVALIDATED_WITHOUT_REASON;
      case 14 -> PenaltyType.THIS_AND_PREVIOUS_LAP_INVALIDATED;
      case 15 -> PenaltyType.THIS_AND_PREVIOUS_LAP_INVALIDATED_WITHOUT_REASON;
      case 16 -> PenaltyType.RETIRED;
      case 17 -> PenaltyType.BLACK_FLAG_TIMER;
      default -> PenaltyType.UNKNOWN;
    };
  }
}
