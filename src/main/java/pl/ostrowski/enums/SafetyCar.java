package pl.ostrowski.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SafetyCar {
  NO_SC("No safety car"),
  SC("Safety car"),
  VSC("Virtual safety car"),
  UNKNOWN("Unknown");

  @Getter private final String description;
}
