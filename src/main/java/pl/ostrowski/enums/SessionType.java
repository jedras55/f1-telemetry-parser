package pl.ostrowski.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SessionType {
  PRACTICE_1("Practice 1"),
  PRACTICE_2("Practice 2"),
  PRACTICE_3("Practice 3"),
  PRACTICE_SHORT("Short practice"),
  QUALIFYING_1("Qualifying 1"),
  QUALIFYING_2("Qualifying 2"),
  QUALIFYING_3("Qualifying 3"),
  QUALIFYING_SHORT("Short qualifying"),
  QUALIFYING_ONE_SHOT("One shot qualifying"),
  RACE_1("Race"),
  RACE_2("Sprint Race"),
  TIME_TRIAL("Time trial"),
  UNKNOWN("Unknown");

  @Getter private final String description;
}
