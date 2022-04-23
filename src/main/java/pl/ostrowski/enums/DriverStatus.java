package pl.ostrowski.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DriverStatus {
  GARAGE("In garage"),
  FLYING_LAP("Flying lap"),
  IN_LAP("In lap"),
  OUT_LAP("Out lap"),
  ON_TRACK("On track"),
  UNKNOWN("Unknown");

  @Getter private final String description;
}
