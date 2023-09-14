package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class to test Event
 */
class EventTest {
  Event event;

  @BeforeEach
  public void setup() {
    event =  new Event("Event", "2468", DayOfWeek.TUESDAY, "12:30pm",
            "100", "description");



  }

  /**
   * A test to test get detail
   */
  @Test
  public void testGetDetail() {
    List<String> details = new ArrayList<>(Arrays.asList("Event", "TUESDAY", "12:30pm",
        "100"));
    assertEquals(details, this.event.getDetails());
  }

  /**
   * A test to test get name
   */
  @Test
  public void testGetName() {
    Event emptyEvent = new Event();
    assertEquals("Event", this.event.getName());
  }

  /**
   * A test to test get description
   */
  @Test
  public void testGetDescription() {
    assertEquals("description",
        this.event.getDescription());
  }

  /**
   * A test to test set description
   */
  @Test
  public void testSetDescription() {
    String newDescription = "new test description";
    assertEquals("description", this.event.getDescription());
    this.event.setDescription(newDescription);
    assertEquals(newDescription, this.event.getDescription());

  }

  /**
   * A test to test set description when the description is null
   */
  @Test
  public void testSetDescriptionNull() {
    assertThrows(IllegalArgumentException.class, () -> this.event.setDescription(null));
  }

  /**
   * A test to test get day
   */
  @Test
  public void testGetDay() {
    assertEquals(DayOfWeek.TUESDAY, this.event.getDay());
  }

  /**
   * A test to test getting the start time
   */
  @Test
  public void testGetStartTime() {
    assertEquals("12:30pm", this.event.getStartTime());
  }

  /**
   * A test to test setting the start time
   */
  @Test
  public void testSetStartTime() {
    this.event.setStartTime("4:00pm");
    assertEquals("4:00pm", this.event.getStartTime());

    assertThrows(IllegalArgumentException.class, () -> this.event.setStartTime(""));
    assertThrows(IllegalArgumentException.class, () -> this.event.setStartTime(null));

  }

  /**
   * A test to test getting the duration
   */
  @Test
  public void testGetDuration() {
    assertEquals("100", this.event.getDuration());
  }

  /**
   * A test to test setting the duration
   */
  @Test
  public void testSetDuration() {
    this.event.setDuration("500");
    assertEquals("500", this.event.getDuration());

    assertThrows(IllegalArgumentException.class, () -> this.event.setDuration(""));
    assertThrows(IllegalArgumentException.class, () -> this.event.setDuration(null));
  }


  /**
   * A test to test getting the uuid
   */
  @Test
  public void testGetUuid() {
    assertEquals("2468", this.event.getUuid());

  }

  /**
   * A test to test the event constructor
   */
  @Test
  public void testEventConstructor_ValidInputs() {
    Event event = new Event("Meeting", "1234", DayOfWeek.MONDAY, "09:00",
        "2 hours", "Team meeting");
    assertEquals("09:00", event.getStartTime());
    assertEquals("2 hours", event.getDuration());
  }

  /**
   * A test to test the event constructor
   */
  @Test
  public void testEventConstructor_NullStartTime() {
    assertThrows(
        IllegalArgumentException.class, () ->
            new Event("Meeting", "1234", DayOfWeek.MONDAY,
                null, "2 hours", "Team meeting"));
  }

  /**
   * A test to test the event constructor
   */
  @Test
  public void testEventConstructor_NullDuration() {
    assertThrows(
        IllegalArgumentException.class, () ->
            new Event("Meeting", "1234", DayOfWeek.MONDAY,
                "09:00", null, "Team meeting"));
  }

  /**
   * A test to test the event constructor
   */
  @Test
  public void testEventConstructor_NullStartTimeAndDuration() {
    assertThrows(
        IllegalArgumentException.class, () -> new Event("Meeting", "1234",
            DayOfWeek.MONDAY, null, null, "Team meeting"));
  }

  /**
   * A test to test the event constructor
   */
  @Test
  public void testEventConstructor_EmptyStartTime() {
    assertThrows(
        IllegalArgumentException.class, () ->
            new Event("Meeting", "1234", DayOfWeek.MONDAY,
                "", "2 hours", "Team meeting"));
  }

  /**
   * A test to test the event constructor
   */
  @Test
  public void testEventConstructor_EmptyDuration() {
    assertThrows(
        IllegalArgumentException.class, () -> new Event("Meeting", "1234",
            DayOfWeek.MONDAY, "09:00", "", "Team meeting"));
  }

  /**
   * A test to test the event constructor
   */
  @Test
  public void testEventConstructor_EmptyStartTimeAndDuration() {
    assertThrows(
        IllegalArgumentException.class, () -> new Event("Meeting", "1234",
            DayOfWeek.MONDAY, "", "", "Team meeting"));
  }

  /**
   * A test to test the event constructor
   */
  @Test
  public void testEventConstructor_EmptyStringsStartTimeAndDuration() {
    assertThrows(
        IllegalArgumentException.class, () -> new Event("Meeting", "1234",
            DayOfWeek.MONDAY, "", "", "Team meeting"));
  }


}