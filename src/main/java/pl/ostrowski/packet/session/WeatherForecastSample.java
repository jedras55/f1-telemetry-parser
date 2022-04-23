package pl.ostrowski.packet.session;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import lombok.Getter;
import pl.ostrowski.DataTypeUtilities;
import pl.ostrowski.enums.SessionType;
import pl.ostrowski.enums.Weather;
import pl.ostrowski.factory.SessionTypeFactory;
import pl.ostrowski.factory.WeatherFactory;

public final class WeatherForecastSample {

  // Structure size in bytes
  public static final int SIZE = 5;

  // Time in minutes the forecast is for
  @Getter private final int timeOffset;

  // Track temp. in degrees celsius
  @Getter private final int trackTemperature;

  // Air temp. in degrees celsius
  @Getter private final int airTemperature;

  @Getter private final Weather weather;

  @Getter private final SessionType sessionType;

  public WeatherForecastSample(byte[] content) {
    ByteBuffer bb = ByteBuffer.wrap(content);
    bb.order(ByteOrder.LITTLE_ENDIAN);

    sessionType = SessionTypeFactory.createSessionType(DataTypeUtilities.convert_uint8(bb.get()));
    timeOffset = DataTypeUtilities.convert_uint8(bb.get());
    weather = WeatherFactory.createWeather(DataTypeUtilities.convert_uint8(bb.get()));
    trackTemperature = DataTypeUtilities.convert_uint8(bb.get());
    airTemperature = DataTypeUtilities.convert_uint8(bb.get());
  }

  @Override
  public String toString() {
    return "Weather in "
        + sessionType.getDescription()
        + " for "
        + timeOffset
        + " minutes will be "
        + weather.getDescription()
        + ", with "
        + airTemperature
        + " air temperature and "
        + trackTemperature
        + " track temperature";
  }
}
