package pl.ostrowski.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PenaltyType {
  DT("Drive through"),
  STOP_AND_GO("Stop and go"),
  GRID_PENALTY("Grid penalty"),
  PENALTY_REMINDER("Penalty reminder"),
  TIME_PENALTY("Time penalty"),
  WARNING("Warning"),
  DISQUALIFIED("Disqualified"),
  REMOVED_FROM_FORMATION_LAP("Removed from formation lap"),
  PARKED_TOO_LONG_TIMER("Parked too long timer"),
  TYRE_REGULATIONS("Tyre regulations"),
  THIS_LAP_INVALIDATED("This lap invalidated"),
  THIS_AND_NEXT_LAP_INVALIDATED("This and next lap invalidated"),
  THIS_LAP_INVALIDATED_WITHOUT_REASON("This lap invalidated without reason"),
  THIS_AND_NEXT_LAP_INVALIDATED_WITHOUT_REASON("This and next lap invalidated without reason"),
  THIS_AND_PREVIOUS_LAP_INVALIDATED("This and previous lap invalidated"),
  THIS_AND_PREVIOUS_LAP_INVALIDATED_WITHOUT_REASON(
      "This and previous lap invalidated without reason"),
  RETIRED("Retired"),
  BLACK_FLAG_TIMER("Black flag timer"),
  UNKNOWN("Unknown");

  @Getter private final String description;
}
