package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class to test Task
 */
class TaskTest {
  Task task;
  Task taskEmpty;

  @BeforeEach
  public void setup() {
    task =  new Task("Task", DayOfWeek.TUESDAY,
        "2468", "description", false);

    taskEmpty =  new Task();



  }

  /**
   * A test to test get details
   */
  @Test
  public void testGetDetail() {
    List<String> details = new ArrayList<>(Arrays.asList("Task", "TUESDAY", "2468",
        "false"));
    assertEquals(details, this.task.getDetails());
  }

  /**
   * A test to test set complete
   */
  @Test
  public void testSetComplete() {
    this.task.setComplete(true);
    assertTrue(this.task.isCompleted());
  }

  /**
   * A test to test get day
   */
  @Test
  public void testGetDay() {
    assertEquals(DayOfWeek.TUESDAY, task.getDay());
  }

}