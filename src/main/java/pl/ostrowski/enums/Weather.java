package pl.ostrowski.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Weather {
  CLEAR("Clear"),
  LIGHT_CLOUD("Light cloud"),
  OVERCAST("Overcast"),
  LIGHT_RAIN("Light rain"),
  HEAVY_RAIN("Heavy rain"),
  STORM("Storm"),
  UNKNOWN("Unknown");

  @Getter private final String description;
}
