package pl.ostrowski.factory;

import pl.ostrowski.enums.Weather;

public class WeatherFactory {
  public static Weather createWeather(short weatherValue) {
    return switch (weatherValue) {
      case 0 -> Weather.CLEAR;
      case 1 -> Weather.LIGHT_CLOUD;
      case 2 -> Weather.OVERCAST;
      case 3 -> Weather.LIGHT_RAIN;
      case 4 -> Weather.HEAVY_RAIN;
      case 5 -> Weather.STORM;
      default -> Weather.UNKNOWN;
    };
  }
}
