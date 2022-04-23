package pl.ostrowski.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PitStatus {
  NONE("None"),
  PITTING("Pitting"),
  PIT_AREA("Pit Area"),
  UNKNOWN("Unknown");

  @Getter private final String description;
}
