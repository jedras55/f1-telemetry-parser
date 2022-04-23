package pl.ostrowski.packet.participants;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import lombok.Getter;
import pl.ostrowski.DataTypeUtilities;
import pl.ostrowski.factory.BooleanFactory;

public class ParticipantData {

  public static final int LENGTH = 54;

  @Getter private final boolean aiControlled;
  @Getter private final int driverId;
  @Getter private final int teamId;
  @Getter private final int raceNumber;
  private final int nationality;
  @Getter private final String name;

  public ParticipantData(byte[] content) {

    ByteBuffer bb = ByteBuffer.wrap(content);
    bb.order(ByteOrder.LITTLE_ENDIAN);

    aiControlled = BooleanFactory.createBoolean(DataTypeUtilities.convert_uint8(bb.get()));
    driverId = DataTypeUtilities.convert_uint8(bb.get());
    teamId = DataTypeUtilities.convert_uint8(bb.get());
    raceNumber = DataTypeUtilities.convert_uint8(bb.get());
    nationality = DataTypeUtilities.convert_uint8(bb.get());
    name = new String(Arrays.copyOfRange(content, 5, 53), StandardCharsets.UTF_8);
  }

  public String getDriver() {
    return switch (driverId) {
      case 0 -> "Carlos SAINZ";
      case 2 -> "Daniel RICCIARDO";
      case 3 -> "Fernando ALONSO";
      case 6 -> "Kimi RÄIKKÖNEN";
      case 7 -> "Lewis HAMILTON";
      case 8 -> "Marcus ERICSSON";
      case 9 -> "Max VERSTAPPEN";
      case 10 -> "Nico HULKENBERG";
      case 11 -> "Kevin MAGNUSSEN";
      case 12 -> "Romain GROSJEAN";
      case 13 -> "Sebastian VETTEL";
      case 14 -> "Sergio PÉREZ";
      case 15 -> "Valtteri BOTTAS";
      case 17 -> "Esteban OCON";
      case 18 -> "Stoffel VANDOORNE";
      case 19 -> "Lance STROLL";
      case 20 -> "Arron BARNES";
      case 21 -> "Martin Giles";
      case 22 -> "Alex MURRAY";
      case 23 -> "Lucas ROTH";
      case 24 -> "Igor CORREIA";
      case 25 -> "Sophie LEVASSEUR";
      case 26 -> "Jonas SCHIFFER";
      case 27 -> "Alain FOREST";
      case 28 -> "Jay LETOURNEAU";
      case 29 -> "Esto SAARI";
      case 30 -> "Yasar ATIYEH";
      case 31 -> "Callisto CALABRESI";
      case 32 -> "Naota IZUM";
      case 33 -> "Howard CLARKE";
      case 34 -> "Wilheim KAUFMANN";
      case 35 -> "Marie LAURSEN";
      case 36 -> "Flavio NIEVES";
      case 58 -> "Charles LECLERC";
      case 59 -> "Pierre GASLY";
      case 60 -> "Brendon HARTLEY";
      case 61 -> "Segey SIROTKIN";
      default -> "** UNKNOWN **";
    };
  }

  public String getTeam() {
    return switch (teamId) {
      case 0 -> "Mercedes";
      case 1 -> "Ferrari";
      case 2 -> "RedBull";
      case 3 -> "Williams";
      case 4 -> "Racing Point";
      case 5 -> "Renault";
      case 6 -> "Alpha Tauri";
      case 7 -> "Haas";
      case 8 -> "McLaren";
      case 9 -> "Alfa Romeo";
      case 10 -> "McLaren '88";
      case 11 -> "McLaren '91";
      case 12 -> "Williams '92";
      case 13 -> "Ferrari '95";
      case 14 -> "Williams '96";
      case 15 -> "McLaren '98";
      case 16 -> "Ferrari 2002";
      case 17 -> "Ferarri 2004";
      case 18 -> "Renault 2006";
      case 19 -> "Ferrari 2007";
      case 20 -> "McLaren 2008";
      case 21 -> "RedBull 2010";
      case 22 -> "Ferarri '76";
      case 34 -> "McLaren '76";
      case 35 -> "Lotus '72";
      case 36 -> "Ferrari '79";
      case 37 -> "McLaren '82";
      case 38 -> "Williams 2003";
      case 39 -> "Brawn GP 2009";
      case 40 -> "Lotus '78";
      default -> "** UNKNOWN **";
    };
  }

  public String getNationality() {
    return switch (nationality) {
      case 1 -> "USA";
      case 2 -> "ARG";
      case 3 -> "AUS";
      case 4 -> "AUT";
      case 5 -> "AZE";
      case 6 -> "BAH";
      case 7 -> "BEL";
      case 8 -> "BOL";
      case 9 -> "BRA";
      case 10 -> "GBR";
      case 11 -> "BUL";
      case 12 -> "CAM";
      case 13 -> "CAN";
      case 14 -> "CHL";
      case 15 -> "CHN";
      case 16 -> "COL";
      case 17 -> "CRI";
      case 18 -> "CRO";
      case 19 -> "CYP";
      case 20 -> "CZE";
      case 21 -> "DEN";
      case 22 -> "NED";
      case 23 -> "ECU";
      case 24 -> "ENG";
      case 25 -> "EMI";
      case 26 -> "EST";
      case 27 -> "FIN";
      case 28 -> "FRA";
      case 29 -> "GER";
      case 30 -> "GHA";
      case 31 -> "GRE";
      case 32 -> "GUA";
      case 33 -> "HON";
      case 34 -> "HKO";
      case 35 -> "HUN";
      case 36 -> "ICS";
      case 37 -> "IND";
      case 38 -> "IDN";
      case 39 -> "IRL";
      case 40 -> "ISR";
      case 41 -> "ITA";
      case 42 -> "JAM";
      case 43 -> "JAP";
      case 44 -> "JOR";
      case 45 -> "KUW";
      case 46 -> "LAT";
      case 47 -> "LEB";
      case 48 -> "LIT";
      case 49 -> "LUX";
      case 50 -> "MLS";
      case 51 -> "MAL";
      case 52 -> "MEX";
      case 53 -> "MON";
      case 54 -> "NZE";
      case 55 -> "NIC";
      case 56 -> "NKR";
      case 57 -> "NIR";
      case 58 -> "NOR";
      case 59 -> "OMA";
      case 60 -> "PAK";
      case 61 -> "PAN";
      case 62 -> "PAR";
      case 63 -> "PER";
      case 64 -> "POL";
      case 65 -> "POR";
      case 66 -> "QAT";
      case 67 -> "ROM";
      case 68 -> "RUS";
      case 69 -> "SAL";
      case 70 -> "SAU";
      case 71 -> "SCO";
      case 72 -> "SER";
      case 73 -> "SIN";
      case 74 -> "SLK";
      case 75 -> "SLN";
      case 76 -> "SKR";
      case 77 -> "SAF";
      case 78 -> "ESP";
      case 79 -> "SWE";
      case 80 -> "SWI";
      case 81 -> "TAI";
      case 82 -> "THA";
      case 83 -> "TUR";
      case 84 -> "URU";
      case 85 -> "UKR";
      case 86 -> "VEN";
      case 87 -> "WEL";
      default -> "UNKNOWN";
    };
  }

  public String toString() {
    String ret = "Controlled by AI: " + isAiControlled() + "\n";
    ret += "Driver: " + getDriver() + " (ID: " + driverId + ")\n";
    ret += "Team: " + getTeam() + "\n";
    ret += "Race Number: " + raceNumber + "\n";
    ret += "Nationality: " + getNationality() + "\n";
    ret += "Name: " + getName() + "\n";
    return ret;
  }
}
