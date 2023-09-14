package cs3500.pa05.model;

/**
 * An enum that represents the day of the week
 */

public enum DayOfWeek {

  /**
   * Represents the day of the week
   */
  MONDAY,

  /**
   * Represents the day of the week
   */
  TUESDAY,

  /**
   * Represents the day of the week
   */
  WEDNESDAY,

  /**
   * Represents the day of the week
   */
  THURSDAY,

  /**
   * Represents the day of the week
   */
  FRIDAY,

  /**
   * Represents the day of the week
   */
  SATURDAY,
  SUNDAY;

  /**
   * Converts a string representation of a day of the week to its corresponding enum constant.
   *
   * @param s the string representation of the day of the week
   * @return the corresponding DayOfWeek enum constant
   */

  public static DayOfWeek converter(String s) {
    return switch (s) {
      case "MONDAY" -> MONDAY;
      case "TUESDAY" -> TUESDAY;
      case "WEDNESDAY" -> WEDNESDAY;
      case "THURSDAY" -> THURSDAY;
      case "FRIDAY" -> FRIDAY;
      case "SATURDAY" -> SATURDAY;
      case "SUNDAY" -> SUNDAY;

      default -> null;
    };
  }
}

