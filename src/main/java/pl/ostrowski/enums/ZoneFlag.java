package pl.ostrowski.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ZoneFlag {
  UNKNOWN("Unknown"),
  NONE("None"),
  GREEN("Green"),
  BLUE("Blue"),
  YELLOW("Yellow"),
  RED("Red");

  @Getter private final String description;
}
