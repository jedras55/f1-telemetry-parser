package pl.ostrowski.factory;

import pl.ostrowski.enums.Track;

public class TrackFactory {
  public static Track createTrack(short trackValue) {
    return switch (trackValue) {
      case 0 -> Track.AUSTRALIA;
      case 1 -> Track.FRANCE;
      case 2 -> Track.CHINA;
      case 3 -> Track.BAHRAIN;
      case 4 -> Track.SPAIN;
      case 5 -> Track.MONACO;
      case 6 -> Track.CANADA;
      case 7 -> Track.GREAT_BRITAIN;
      case 8 -> Track.GERMANY;
      case 9 -> Track.HUNGARY;
      case 10 -> Track.BELGIUM;
      case 11 -> Track.ITALY;
      case 12 -> Track.SINGAPORE;
      case 13 -> Track.JAPAN;
      case 14 -> Track.UNITED_ARAB_EMIRATES;
      case 15 -> Track.USA;
      case 16 -> Track.BRAZIL;
      case 17 -> Track.AUSTRIA;
      case 18 -> Track.RUSSIA;
      case 19 -> Track.MEXICO;
      case 20 -> Track.AZERBAIJAN;
      case 21 -> Track.BAHRAIN_SHORT;
      case 22 -> Track.GREAT_BRITAIN_SHORT;
      case 23 -> Track.USA_SHORT;
      case 24 -> Track.JAPAN_SHORT;
      case 25 -> Track.VIETNAM;
      case 26 -> Track.NETHERLANDS;
      default -> Track.UNKNOWN;
    };
  }
}
