package pl.ostrowski.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ResultStatus {
  INVALID("Invalid"),
  INACTIVE("Inactive"),
  ACTIVE("Active"),
  FINISHED("Finished"),
  DISQUALIFIED("Disqualified"),
  NOT_CLASSIFIED("Not classified"),
  RETIRED("Retired"),
  UNKNOWN("Unknown");

  @Getter private final String description;
}
