package pl.ostrowski.factory;

import enums.ResultStatus;

public class ResultStatusFactory {
  public static ResultStatus createResultStatus(short resultStatusValue) {
    return switch (resultStatusValue) {
      case 0 -> ResultStatus.INVALID;
      case 1 -> ResultStatus.INACTIVE;
      case 2 -> ResultStatus.ACTIVE;
      case 3 -> ResultStatus.FINISHED;
      case 4 -> ResultStatus.DISQUALIFIED;
      case 5 -> ResultStatus.NOT_CLASSIFIED;
      case 6 -> ResultStatus.RETIRED;
      default -> ResultStatus.UNKNOWN;
    };
  }
}
