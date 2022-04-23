package pl.ostrowski.packet.session;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import pl.ostrowski.DataTypeUtilities;
import pl.ostrowski.enums.Era;
import pl.ostrowski.enums.SafetyCar;
import pl.ostrowski.enums.SessionType;
import pl.ostrowski.enums.Track;
import pl.ostrowski.enums.Weather;
import pl.ostrowski.enums.ZoneFlag;
import pl.ostrowski.factory.BooleanFactory;
import pl.ostrowski.factory.EraFactory;
import pl.ostrowski.factory.SafetyCarFactory;
import pl.ostrowski.factory.SessionTypeFactory;
import pl.ostrowski.factory.TrackFactory;
import pl.ostrowski.factory.WeatherFactory;
import pl.ostrowski.packet.Packet;
import pl.ostrowski.util.TimePrinter;

public class PacketSessionData extends Packet {

  // Packet Size in bytes (w/o header size)
  public static final int LENGTH = 227;
  private static final int PACKET_SIZE_TO_MARSHAL_ZONES = 19;
  private static final int MARSHAL_ZONES_NUMBER = 21;
  private static final int WEATHER_FORECAST_NUMBER = 20;
  private static final int PACKET_SIZE_TO_WEATHER_FORECAST =
      PACKET_SIZE_TO_MARSHAL_ZONES + MARSHAL_ZONES_NUMBER * MarshalZone.SIZE + 3;
  @Getter private final Weather weather;
  // Track temp in degrees celsius
  @Getter private final int trackTemperature;
  // Air temp in degrees celsius
  @Getter private final int airTemperature;
  // Total number of laps in this race
  @Getter private final int totalLaps;
  // Track length in metres
  @Getter private final int trackLength;
  @Getter private final SessionType sessionType;
  @Getter private final Track track;
  @Getter private final Era era;
  // Time left in session in seconds
  @Getter private final int sessionTimeLeft;
  // Session duration in seconds
  @Getter private final int sessionDuration;
  // Pit speed limit in kilometres per hour
  @Getter private final int pitSpeedLimit;
  // Whether the game is paused
  @Getter private final boolean gamePaused;
  // Whether the player is spectating
  @Getter private final boolean spectating;
  // Index of the car being spectated
  @Getter private final int spectatorCarIndex;
  // SLI Pro support
  @Getter private final boolean sliProNativeSupport;
  // Number of marshal zones to follow
  @Getter private final int numberOfMarshalZones;
  // List of marshal zones – max 21
  @Getter private final List<MarshalZone> marshalZones = new ArrayList<>();
  @Getter private final SafetyCar safetyCarStatus;
  @Getter private final boolean onlineGame;
  // Number of weather forecasts to follow
  @Getter private final int numberOWeatherForecasts;
  // List of marshal zones – max 21
  @Getter private final List<WeatherForecastSample> weatherForecastSamples = new ArrayList<>();

  public PacketSessionData(byte[] content) {
    super(Arrays.copyOfRange(content, 0, Packet.HEADER_SIZE));

    ByteBuffer bb = ByteBuffer.wrap(Arrays.copyOfRange(content, Packet.HEADER_SIZE, LENGTH));
    bb.order(ByteOrder.LITTLE_ENDIAN);

    weather = WeatherFactory.createWeather(DataTypeUtilities.convert_uint8(bb.get()));
    trackTemperature = DataTypeUtilities.convert_uint8(bb.get());
    airTemperature = DataTypeUtilities.convert_uint8(bb.get());
    totalLaps = DataTypeUtilities.convert_uint8(bb.get());
    trackLength = DataTypeUtilities.convert_uint16(bb.getShort());
    sessionType = SessionTypeFactory.createSessionType(DataTypeUtilities.convert_uint8(bb.get()));
    track = TrackFactory.createTrack(DataTypeUtilities.convert_uint8(bb.get()));
    era = EraFactory.createSessionType(DataTypeUtilities.convert_uint8(bb.get()));
    sessionTimeLeft = DataTypeUtilities.convert_uint16(bb.getShort());
    sessionDuration = DataTypeUtilities.convert_uint16(bb.getShort());
    pitSpeedLimit = DataTypeUtilities.convert_uint8(bb.get());
    gamePaused = BooleanFactory.createBoolean(DataTypeUtilities.convert_uint8(bb.get()));
    spectating = BooleanFactory.createBoolean(DataTypeUtilities.convert_uint8(bb.get()));
    spectatorCarIndex = DataTypeUtilities.convert_uint8(bb.get());
    sliProNativeSupport = BooleanFactory.createBoolean(DataTypeUtilities.convert_uint8(bb.get()));
    numberOfMarshalZones = DataTypeUtilities.convert_uint8(bb.get());
    safetyCarStatus =
        SafetyCarFactory.createSafetyCar(
            DataTypeUtilities.convert_uint8(
                bb.get(PACKET_SIZE_TO_MARSHAL_ZONES + MarshalZone.SIZE * MARSHAL_ZONES_NUMBER)));
    onlineGame =
        BooleanFactory.createBoolean(
            DataTypeUtilities.convert_uint8(
                bb.get(
                    PACKET_SIZE_TO_MARSHAL_ZONES + MarshalZone.SIZE * MARSHAL_ZONES_NUMBER + 1)));
    numberOWeatherForecasts =
        DataTypeUtilities.convert_uint8(
            bb.get(PACKET_SIZE_TO_MARSHAL_ZONES + MarshalZone.SIZE * MARSHAL_ZONES_NUMBER + 2));
    fillMarshalZones(content);
    fillWeatherForecast(content);
  }

  private void fillMarshalZones(byte[] content) {
    int from = Packet.HEADER_SIZE + PACKET_SIZE_TO_MARSHAL_ZONES;
    int to;
    int count = 0;
    while (count < MARSHAL_ZONES_NUMBER) {
      to = from + MarshalZone.SIZE;
      byte[] marshalZoneBytes = Arrays.copyOfRange(content, from, to);
      MarshalZone marshalZone = new MarshalZone(marshalZoneBytes);
      if (marshalZones.size() < numberOfMarshalZones
          && (marshalZone.getZoneFlag() != ZoneFlag.UNKNOWN
              && marshalZone.getZoneFlag() != ZoneFlag.NONE)) marshalZones.add(marshalZone);
      from += MarshalZone.SIZE;
      count++;
    }
  }

  private void fillWeatherForecast(byte[] content) {
    int from = Packet.HEADER_SIZE + PACKET_SIZE_TO_WEATHER_FORECAST;
    int to;
    int count = 0;
    while (count < WEATHER_FORECAST_NUMBER) {
      to = from + WeatherForecastSample.SIZE;
      byte[] weatherForecastBytes = Arrays.copyOfRange(content, from, to);
      WeatherForecastSample weatherForecastSample = new WeatherForecastSample(weatherForecastBytes);
      if (weatherForecastSamples.size() < numberOWeatherForecasts
          && weatherForecastSample.getSessionType() != SessionType.UNKNOWN)
        weatherForecastSamples.add(weatherForecastSample);

      from += WeatherForecastSample.SIZE;
      count++;
    }
  }

  @Override
  public String toString() {
    String ret = super.toString();

    ret +=
        "Actual weather is "
            + getWeather().getDescription()
            + ". Track temperature is "
            + trackTemperature
            + " ºC"
            + " and air temperature is "
            + airTemperature
            + " ºC. Race has "
            + totalLaps
            + " total laps. Track length is "
            + trackLength
            + " m. "
            + getSessionType().getDescription()
            + " is in "
            + getTrack().getDescription()
            + " in "
            + getEra().getDescription()
            + " bolids.\n"
            + "Session will finish in "
            + TimePrinter.printFormattedSeconds(getSessionTimeLeft())
            + " because maximum session duration is "
            + TimePrinter.printFormattedSeconds(getSessionDuration())
            + ". Pit speed limit is "
            + pitSpeedLimit
            + ". Safety car status is "
            + getSafetyCarStatus().getDescription()
            + ".\n"
            + "Game is "
            + (gamePaused ? "" : "not ")
            + "paused. Player is "
            + (spectating
                ? "spectating car with " + spectatorCarIndex + " index."
                : "not spectating any car.\n")
            + "\nThere are "
            + numberOfMarshalZones
            + "  marshal zones, but "
            + marshalZones.size()
            + " active.";

    for (MarshalZone mz : marshalZones) {
      ret += "\n";
      ret += mz.toString();
    }
    ret += "\n";
    ret += "\n";
    ret += "There are " + numberOWeatherForecasts + " weather forecasts.";
    for (WeatherForecastSample wfs : weatherForecastSamples) {
      ret += "\n";
      ret += wfs.toString();
    }
    ret += "\n";
    ret += "\n";
    return ret;
  }
}
