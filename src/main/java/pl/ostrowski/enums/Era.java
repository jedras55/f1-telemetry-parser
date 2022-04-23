package pl.ostrowski.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Era {
  F1_MODERN("F1 modern"),
  F1_CLASSIC("F1 classic"),
  F2("F2"),
  F1_GENERIC("F1 generic"),
  UNKNOWN("Unknown");

  @Getter private final String description;
}
