package pl.ostrowski.packet;

import dto.car.telemetry.CarTelemetryData;
import dto.lap.LapData;
import dto.session.MarshalZone;
import dto.session.WeatherForecastSample;
import enums.PacketType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import pl.ostrowski.packet.car.telemetry.PacketCarTelemetryData;
import pl.ostrowski.packet.event.PacketEventData;
import pl.ostrowski.packet.lap.PacketLapData;
import pl.ostrowski.packet.motion.PacketMotionData;
import pl.ostrowski.packet.session.PacketSessionData;

public class PacketFactory {

  public static Packet getPacket(byte[] content) {
    Packet p = new Packet(content);
    return switch (p.getPacketType()) {
      case MOTION -> new PacketMotionData(content);
      case SESSION -> new PacketSessionData(content);
      case LAP -> new PacketLapData(content);
      case EVENT -> new PacketEventData(content);
      case CAR_TELEMETRY -> new PacketCarTelemetryData(content);
      default -> null;
    };
  }

  public static dto.Packet toDto(Packet packet) {
    return switch (packet.getPacketType()) {
      case MOTION -> {
        PacketMotionData p = (PacketMotionData) packet;
        yield new dto.motion.PacketMotionData(
            0,
            0,
            0,
            0,
            0,
            PacketType.MOTION,
            BigInteger.ONE.toString(),
            0,
            0,
            0,
            new ArrayList<>(),
            null);
      }
      case SESSION -> {
        PacketSessionData p = (PacketSessionData) packet;
        yield new dto.session.PacketSessionData(
            p.getFrameIdentifier().intValue(),
            p.getPacketFormat(),
            p.getGameMajorVersion(),
            p.getGameMinorVersion(),
            p.getPacketVersion(),
            PacketType.SESSION,
            p.getSessionUID().toString(),
            p.getSessionTime(),
            p.getPlayerCarIndex(),
            p.getSecondaryPlayerCarIndex(),
            p.getWeather(),
            p.getTrackTemperature(),
            p.getAirTemperature(),
            p.getTotalLaps(),
            p.getTrackLength(),
            p.getSessionType(),
            p.getTrack(),
            p.getEra(),
            p.getSessionTimeLeft(),
            p.getSessionDuration(),
            p.getPitSpeedLimit(),
            p.isGamePaused(),
            p.isSpectating(),
            p.getSpectatorCarIndex(),
            p.isSliProNativeSupport(),
            p.getNumberOfMarshalZones(),
            p.getMarshalZones().stream()
                .map(
                    marshalZone ->
                        new MarshalZone(
                            marshalZone.getZoneStartTrackPercent(), marshalZone.getZoneFlag()))
                .collect(Collectors.toList()),
            p.getSafetyCarStatus(),
            p.isOnlineGame(),
            p.getNumberOWeatherForecasts(),
            p.getWeatherForecastSamples().stream()
                .map(
                    weatherForecastSample ->
                        new WeatherForecastSample(
                            weatherForecastSample.getTimeOffset(),
                            weatherForecastSample.getTrackTemperature(),
                            weatherForecastSample.getTrackTemperatureChange(),
                            weatherForecastSample.getAirTemperature(),
                            weatherForecastSample.getAirTemperatureChange(),
                            weatherForecastSample.getRainPercentage(),
                            weatherForecastSample.getWeather(),
                            weatherForecastSample.getSessionType()))
                .collect(Collectors.toList()));
      }
      case LAP -> {
        PacketLapData p = (PacketLapData) packet;
        yield new dto.lap.PacketLapData(
            p.getFrameIdentifier().intValue(),
            p.getPacketFormat(),
            p.getGameMajorVersion(),
            p.getGameMinorVersion(),
            p.getPacketVersion(),
            PacketType.LAP,
            p.getSessionUID().toString(),
            p.getSessionTime(),
            p.getPlayerCarIndex(),
            p.getSecondaryPlayerCarIndex(),
            p.getAllCarsLapData().stream()
                .map(
                    car ->
                        new LapData(
                            car.getLastLapTimeInMS().intValue(),
                            car.getCurrentLapTimeInMS().intValue(),
                            car.getSector1TimeInMS(),
                            car.getSector2TimeInMS(),
                            car.getLapDistance(),
                            car.getTotalDistance(),
                            car.getSafetyCarDelta(),
                            car.getCarPosition(),
                            car.getCurrentLapNum(),
                            car.getPitStatus(),
                            car.getNumPitStops(),
                            car.getSector(),
                            car.isCurrentLapInvalid(),
                            car.getPenalties(),
                            car.getWarnings(),
                            car.getNumUnservedDriveThroughPens(),
                            car.getNumUnservedStopGoPens(),
                            car.getGridPosition(),
                            car.getDriverStatus(),
                            car.getResultStatus(),
                            car.isPitLaneTimerActive(),
                            car.getPitLaneTimeInLaneInMS(),
                            car.getPitStopTimerInMS(),
                            car.isPitStopShouldServePen()))
                .collect(Collectors.toList()));
      }
      case EVENT -> {
        PacketEventData p = (PacketEventData) packet;
        yield new dto.event.PacketEventData(
            0, 0, 0, 0, 0, PacketType.EVENT, "", 0, 0, 0, "code", null, null);
      }
      case CAR_TELEMETRY -> {
        PacketCarTelemetryData p = (PacketCarTelemetryData) packet;
        yield new dto.car.telemetry.PacketCarTelemetryData(
            p.getFrameIdentifier().intValue(),
            p.getPacketFormat(),
            p.getGameMajorVersion(),
            p.getGameMinorVersion(),
            p.getPacketVersion(),
            PacketType.LAP,
            p.getSessionUID().toString(),
            p.getSessionTime(),
            p.getPlayerCarIndex(),
            p.getSecondaryPlayerCarIndex(),
            p.getCarTelemetryDataList().stream()
                .map(
                    k ->
                        new CarTelemetryData(
                            k.getSpeed(),
                            k.getThrottle(),
                            k.getSteer(),
                            k.getBrake(),
                            k.getClutch(),
                            k.getGear(),
                            k.getEngineRPM(),
                            k.getDrs() == 1,
                            k.getRevLightsPercent(),
                            k.getRevLightsBitValue(),
                            Arrays.stream(k.getBrakesTemperature())
                                .boxed()
                                .collect(Collectors.toList()),
                            Arrays.stream(k.getTyresSurfaceTemperature())
                                .boxed()
                                .collect(Collectors.toList()),
                            Arrays.stream(k.getTyresInnerTemperature())
                                .boxed()
                                .collect(Collectors.toList()),
                            k.getEngineTemperature(),
                            Arrays.stream(k.getTyresPressure())
                                .boxed()
                                .collect(Collectors.toList()),
                            Arrays.stream(k.getSurfaceType()).boxed().collect(Collectors.toList())))
                .collect(Collectors.toList()),
            p.getMfdPanelIndex(),
            p.getMfdPanelIndexSecondaryPlayer(),
            p.getSuggestedGear());
      }
      default -> null;
    };
  }
}
