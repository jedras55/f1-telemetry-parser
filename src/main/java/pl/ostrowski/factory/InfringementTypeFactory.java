package pl.ostrowski.factory;

import enums.InfringementType;

public class InfringementTypeFactory {
  public static InfringementType createInfringementType(short infringementTypeValue) {
    return switch (infringementTypeValue) {
      case 0 -> InfringementType.BLOCKING_BY_SLOW_DRIVING;
      case 1 -> InfringementType.BLOCKING_BY_WRONG_WAY_DRIVING;
      case 2 -> InfringementType.REVERSING_OFF_THE_START_LINE;
      case 3 -> InfringementType.BIG_COLLISION;
      case 4 -> InfringementType.SMALL_COLLISION;
      case 5 -> InfringementType.COLLISION_FAILED_TO_HAND_BACK_POSITION_SINGLE;
      case 6 -> InfringementType.COLLISION_FAILED_TO_HAND_BACK_POSITION_MULTIPLE;
      case 7 -> InfringementType.CORNER_CUTTING_GAINED_TIME;
      case 8 -> InfringementType.CORNER_CUTTING_OVERTAKE_SINGLE;
      case 9 -> InfringementType.CORNER_CUTTING_OVERTAKE_MULTIPLE;
      case 10 -> InfringementType.CROSSED_PIT_EXIT_LANE;
      case 11 -> InfringementType.IGNORING_BLUE_FLAGS;
      case 12 -> InfringementType.IGNORING_YELLOW_FLAGS;
      case 13 -> InfringementType.IGNORING_DRIVE_THROUGH;
      case 14 -> InfringementType.TOO_MANY_DRIVE_THROUGHS;
      case 15 -> InfringementType.DRIVE_THROUGH_REMINDER_SERVE_WITHIN_N_LAPS;
      case 16 -> InfringementType.DRIVE_THROUGH_REMINDER_SERVE_THIS_LAP;
      case 17 -> InfringementType.PIT_LANE_SPEEDING;
      case 18 -> InfringementType.PARKED_FOR_TOO_LONG;
      case 19 -> InfringementType.IGNORING_TYRE_REGULATIONS;
      case 20 -> InfringementType.TOO_MANY_PENALTIES;
      case 21 -> InfringementType.MULTIPLE_WARNINGS;
      case 22 -> InfringementType.APPROACHING_DISQUALIFICATION;
      case 23 -> InfringementType.TYRE_REGULATIONS_SELECT_SINGLE;
      case 24 -> InfringementType.TYRE_REGULATIONS_SELECT_MULTIPLE;
      case 25 -> InfringementType.LAP_INVALIDATED_CORNER_CUTTING;
      case 26 -> InfringementType.LAP_INVALIDATED_RUNNING_WIDE;
      case 27 -> InfringementType.CORNER_CUTTING_RAN_WIDE_GAINED_TIME_MINOR;
      case 28 -> InfringementType.CORNER_CUTTING_RAN_WIDE_GAINED_TIME_SIGNIFICANT;
      case 29 -> InfringementType.CORNER_CUTTING_RAN_WIDE_GAINED_TIME_EXTREME;
      case 30 -> InfringementType.LAP_INVALIDATED_WALL_RIDING;
      case 31 -> InfringementType.LAP_INVALIDATED_FLASHBACK_USED;
      case 32 -> InfringementType.LAP_INVALIDATED_RESET_TO_TRACK;
      case 33 -> InfringementType.BLOCKING_THE_PITLANE;
      case 34 -> InfringementType.JUMP_START;
      case 35 -> InfringementType.SAFETY_CAR_TO_CAR_COLLISION;
      case 36 -> InfringementType.SAFETY_CAR_ILLEGAL_OVERTAKE;
      case 37 -> InfringementType.SAFETY_CAR_EXCEEDING_ALLOWED_PACE;
      case 38 -> InfringementType.VIRTUAL_SAFETY_CAR_EXCEEDING_ALLOWED_PACE;
      case 39 -> InfringementType.FORMATION_LAP_BELOW_ALLOWED_SPEED;
      case 40 -> InfringementType.RETIRED_MECHANICAL_FAILURE;
      case 41 -> InfringementType.RETIRED_TERMINALLY_DAMAGED;
      case 42 -> InfringementType.SAFETY_CAR_FALLING_TOO_FAR_BACK;
      case 43 -> InfringementType.BLACK_FLAG_TIMER;
      case 44 -> InfringementType.UNSERVED_STOP_GO_PENALTY;
      case 45 -> InfringementType.UNSERVED_DRIVE_THROUGH_PENALTY;
      case 46 -> InfringementType.ENGINE_COMPONENT_CHANGE;
      case 47 -> InfringementType.GEARBOX_CHANGE;
      case 48 -> InfringementType.LEAGUE_GRID_PENALTY;
      case 49 -> InfringementType.RETRY_PENALTY;
      case 50 -> InfringementType.ILLEGAL_TIME_GAIN;
      case 51 -> InfringementType.MANDATORY_PITSTOP;
      default -> InfringementType.UNKNOWN;
    };
  }
}
