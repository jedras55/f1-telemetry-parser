package pl.ostrowski.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Track {
  AUSTRALIA("Australia"),
  FRANCE("France"),
  CHINA("China"),
  BAHRAIN("Bahrain"),
  SPAIN("Spain"),
  MONACO("Monaco"),
  CANADA("Canada"),
  GREAT_BRITAIN("Great Britain"),
  GERMANY("Germany"),
  HUNGARY("Hungary"),
  BELGIUM("Belgium"),
  ITALY("Italy"),
  SINGAPORE("Singapore"),
  JAPAN("Japan"),
  UNITED_ARAB_EMIRATES("United Arab Emirates"),
  USA("USA"),
  BRAZIL("Brazil"),
  AUSTRIA("Austria"),
  RUSSIA("Russia"),
  MEXICO("Mexico"),
  AZERBAIJAN("Azerbaijan"),
  BAHRAIN_SHORT("Bahrain short"),
  GREAT_BRITAIN_SHORT("Great Britain short"),
  USA_SHORT("USA short"),
  JAPAN_SHORT("Japan short"),
  VIETNAM("Vietnam"),
  NETHERLANDS("Netherlands"),
  UNKNOWN("Unknown");

  @Getter private final String description;
}
