package pl.ostrowski.util;

public class TimePrinter {

  public static String printFormattedSeconds(int sec) {
    int hours = sec / 3600;
    sec -= 3600 * hours;
    int minutes = sec / 60;
    sec -= 60 * minutes;
    int seconds = sec % 60;
    if (hours != 0) {
      return String.format("%d:%02d:%02d", hours, minutes, seconds);
    } else return String.format("%d:%02d", minutes, seconds);
  }
}
