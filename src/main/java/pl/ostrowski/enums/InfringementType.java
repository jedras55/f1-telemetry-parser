package pl.ostrowski.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum InfringementType {
  BLOCKING_BY_SLOW_DRIVING("Blocking by slow driving"),
  BLOCKING_BY_WRONG_WAY_DRIVING("Blocking by wrong way driving"),
  REVERSING_OFF_THE_START_LINE("Reversing off the start line"),
  BIG_COLLISION("Big Collision"),
  SMALL_COLLISION("Small Collision"),
  COLLISION_FAILED_TO_HAND_BACK_POSITION_SINGLE("Collision failed to hand back position single"),
  COLLISION_FAILED_TO_HAND_BACK_POSITION_MULTIPLE(
      "Collision failed to hand back position multiple"),
  CORNER_CUTTING_GAINED_TIME("Corner cutting gained time"),
  CORNER_CUTTING_OVERTAKE_SINGLE("Corner cutting overtake single"),
  CORNER_CUTTING_OVERTAKE_MULTIPLE("Corner cutting overtake multiple"),
  CROSSED_PIT_EXIT_LANE("Crossed pit exit lane"),
  IGNORING_BLUE_FLAGS("Ignoring blue flags"),
  IGNORING_YELLOW_FLAGS("Ignoring yellow flags"),
  IGNORING_DRIVE_THROUGH("Ignoring drive through"),
  TOO_MANY_DRIVE_THROUGHS("Too many drive throughs"),
  DRIVE_THROUGH_REMINDER_SERVE_WITHIN_N_LAPS("Drive through reminder serve within n laps"),
  DRIVE_THROUGH_REMINDER_SERVE_THIS_LAP("Drive through reminder serve this lap"),
  PIT_LANE_SPEEDING("Pit lane speeding"),
  PARKED_FOR_TOO_LONG("Parked for too long"),
  IGNORING_TYRE_REGULATIONS("Ignoring tyre regulations"),
  TOO_MANY_PENALTIES("Too many penalties"),
  MULTIPLE_WARNINGS("Multiple warnings"),
  APPROACHING_DISQUALIFICATION("Approaching disqualification"),
  TYRE_REGULATIONS_SELECT_SINGLE("Tyre regulations select single"),
  TYRE_REGULATIONS_SELECT_MULTIPLE("Tyre regulations select multiple"),
  LAP_INVALIDATED_CORNER_CUTTING("Lap invalidated corner cutting"),
  LAP_INVALIDATED_RUNNING_WIDE("Lap invalidated running wide"),
  CORNER_CUTTING_RAN_WIDE_GAINED_TIME_MINOR("Corner cutting ran wide gained time minor"),
  CORNER_CUTTING_RAN_WIDE_GAINED_TIME_SIGNIFICANT(
      "Corner cutting ran wide gained time significant"),
  CORNER_CUTTING_RAN_WIDE_GAINED_TIME_EXTREME("Corner cutting ran wide gained time extreme"),
  LAP_INVALIDATED_WALL_RIDING("Lap invalidated wall riding"),
  LAP_INVALIDATED_FLASHBACK_USED("Lap invalidated flashback used"),
  LAP_INVALIDATED_RESET_TO_TRACK("Lap invalidated reset to track"),
  BLOCKING_THE_PITLANE("Blocking the pitlane"),
  JUMP_START("Jump start"),
  SAFETY_CAR_TO_CAR_COLLISION("Safety car to car collision"),
  SAFETY_CAR_ILLEGAL_OVERTAKE("Safety car illegal overtake"),
  SAFETY_CAR_EXCEEDING_ALLOWED_PACE("Safety car exceeding allowed pace"),
  VIRTUAL_SAFETY_CAR_EXCEEDING_ALLOWED_PACE("Virtual safety car exceeding allowed pace"),
  FORMATION_LAP_BELOW_ALLOWED_SPEED("Formation lap below allowed speed"),
  RETIRED_MECHANICAL_FAILURE("Retired mechanical failure"),
  RETIRED_TERMINALLY_DAMAGED("Retired terminally damaged"),
  SAFETY_CAR_FALLING_TOO_FAR_BACK("Safety car falling too far back"),
  BLACK_FLAG_TIMER("Black flag timer"),
  UNSERVED_STOP_GO_PENALTY("Unserved stop go penalty"),
  UNSERVED_DRIVE_THROUGH_PENALTY("Unserved drive through penalty"),
  ENGINE_COMPONENT_CHANGE("Engine component change"),
  GEARBOX_CHANGE("Gearbox change"),
  LEAGUE_GRID_PENALTY("League grid penalty"),
  RETRY_PENALTY("Retry penalty"),
  ILLEGAL_TIME_GAIN("Illegal time gain"),
  MANDATORY_PITSTOP("Mandatory pitstop"),
  UNKNOWN("Unknown");

  @Getter private final String description;
}
