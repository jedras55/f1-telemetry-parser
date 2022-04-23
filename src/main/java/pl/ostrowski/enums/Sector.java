package pl.ostrowski.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Sector {
  FIRST("1"),
  SECOND("2"),
  THIRD("3"),
  UNKNOWN("Unknown");

  @Getter private final String description;
}
