package pl.ostrowski.factory;

public class BooleanFactory {
  public static boolean createBoolean(short value) {
    return value == 1;
  }
}
