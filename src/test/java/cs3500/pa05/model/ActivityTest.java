package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class that tests Activity
 */
class ActivityTest {
  Activity event;
  Activity task;

  @BeforeEach
  public void setup() {
    task = new Task("Activity: Task", DayOfWeek.SATURDAY, "yhjkiuygbnmkiuhgbnji8678987656",
        "description", false);
    event = new Event("Activity: Event", "1234", DayOfWeek.MONDAY, "12:30pm",
        "100", "description");
  }

  /**
   * A test that tests getting the day
   */
  @Test
  public void testGetDay() {
    assertEquals(DayOfWeek.SATURDAY, task.getDay());
    assertEquals(DayOfWeek.MONDAY, event.getDay());
  }

  /**
   * A test that tests getting the uuid
   */
  @Test
  public void testGetUuid() {
    assertEquals("yhjkiuygbnmkiuhgbnji8678987656", task.getUuid());
    assertEquals("1234", event.getUuid());
  }

  /**
   * A test that tests getting the name
   */
  @Test
  public void testGetName() {
    assertEquals("Activity: Task", task.getName());
    assertEquals("Activity: Event", event.getName());
  }

  /**
   * A test that tests getting the description
   */
  @Test
  public void testGetDescription() {
    assertEquals("description", task.getDescription());
    assertEquals("description", event.getDescription());
  }

  /**
   * A test that tests setting the description
   */
  @Test
  public void testSetDescription() {
    task.setDescription("This is a new description for Task");
    event.setDescription("This is a new description for Event");
    assertEquals("This is a new description for Task", task.getDescription());
    assertEquals("This is a new description for Event", event.getDescription());
  }

  /**
   * A test that tests setting the name
   */
  @Test
  public void testSetName() {
    task.setName("This is a new name for Task");
    event.setName("This is a new name for Event");
    assertEquals("This is a new name for Task", task.getName());
    assertEquals("This is a new name for Event", event.getName());

    assertThrows(IllegalArgumentException.class, () -> task.setName(""));
    assertThrows(IllegalArgumentException.class, () -> task.setName(null));
  }

}