package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * A class to test the DayOfWeek
 */
class DayOfWeekTest {


  /**
   * A test to test convert day
   */
  @Test
  public void testConvertDay() {
    assertEquals(DayOfWeek.MONDAY, DayOfWeek.converter("MONDAY"));
    assertEquals(DayOfWeek.TUESDAY, DayOfWeek.converter("TUESDAY"));
    assertEquals(DayOfWeek.WEDNESDAY, DayOfWeek.converter("WEDNESDAY"));
    assertEquals(DayOfWeek.THURSDAY, DayOfWeek.converter("THURSDAY"));
    assertEquals(DayOfWeek.FRIDAY, DayOfWeek.converter("FRIDAY"));
    assertEquals(DayOfWeek.SATURDAY, DayOfWeek.converter("SATURDAY"));
    assertEquals(DayOfWeek.SUNDAY, DayOfWeek.converter("SUNDAY"));
    assertNull(DayOfWeek.converter("TESTSHOULDNOTWORK"));
  }

}