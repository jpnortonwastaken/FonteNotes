package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class that represents testing for the week
 */
class WeekTest {

  Iweek week;
  Iweek week1;
  List<Task> tasks;
  List<Event> events;

  /**
   * The before each
   */
  @BeforeEach
  public void setup() {
    List<Task> emptyTasks = new ArrayList<>();
    List<Event> emptyEvents = new ArrayList<>();
    this.week = new Week("Test week", Theme.LIGHT, emptyEvents, emptyTasks,
        5, 5);


    tasks = new ArrayList<>(Arrays.asList(
        new Task("Task 1", DayOfWeek.WEDNESDAY, "123455677"),
        new Task("Task 2", DayOfWeek.THURSDAY, "12231454652413423456"),
        new Task("Task 3", DayOfWeek.FRIDAY, "5678987654rtyh76789"),
        new Task("Task 4", DayOfWeek.SATURDAY, "yhjkiuygbnmkiuhgbnji8678987656")));

    events = new ArrayList<>(Arrays.asList(
        new Event("Event 1", "1234", DayOfWeek.MONDAY, "12:30pm",
            "description"),
        new Event("Event 2", "2468", DayOfWeek.TUESDAY, "12:30pm",
            "description")));

    this.week1 = new Week("Test week1", Theme.LIGHT, events, tasks,
        5, 5);


  }

  /**
   * A test to test add Task
   */
  @Test
  public void testAddTask() {
    Task taskToAdd = new Task("Task to add", DayOfWeek.FRIDAY,
        "8934921348u913u89r3ihf");

    this.week.addTask(taskToAdd);
    assertEquals(taskToAdd, week.getTasks().get(0));

  }

  /**
   * A test to test add Event
   */
  @Test
  public void testAddEvent() {
    Event eventToAdd = new Event("Event to add", "56789ijhgvcft67uio", DayOfWeek.MONDAY,
        "12:30pm", "description");

    this.week.addEvent(eventToAdd);
    assertEquals(eventToAdd, week.getEvents().get(0));


  }

  /**
   * A test to test cycling the theme
   */
  @Test
  public void testCycleTheme() {
    assertEquals(Theme.LIGHT, week.getTheme());
    this.week.cycleTheme();
    assertEquals(Theme.DARK, week.getTheme());
    this.week.cycleTheme();
    assertEquals(Theme.MARK, week.getTheme());
    this.week.cycleTheme();
    assertEquals(Theme.LIGHT, week.getTheme());

  }

  /**
   * A test to test getting the Tasks
   */
  @Test
  public void testGetTasks() {

    assertEquals(new ArrayList<>(), week.getTasks());
    assertEquals(this.tasks, week1.getTasks());
  }

  /**
   * A test to test getting the Events
   */
  @Test
  public void testGetEvents() {

    assertEquals(new ArrayList<>(), week.getEvents());
    assertEquals(this.events, week1.getEvents());
  }

  /**
   * A test to test deleting an Activity
   */
  @Test
  public void testDeleteActivity() {

    String task4Uuid = "yhjkiuygbnmkiuhgbnji8678987656";
    assertEquals(4, week1.getTasks().size());

    week1.deleteActivity(task4Uuid);

    assertEquals(3, week1.getTasks().size());
  }

  /**
   * A test to test setting the name of the week
   */
  @Test
  public void testSetName() {

    assertEquals("Test week1", week1.getName());
    week1.setName("Changed the name");
    assertEquals("Changed the name", week1.getName());
  }

  /**
   * A test to test getting the name of the Week
   */
  @Test
  public void testGetName() {

    assertEquals("Test week1", week1.getName());
    assertEquals("Test week", week.getName());
  }

  /**
   * A test to test setting the max Tasks
   */
  @Test
  public void testSetMaxTasks() {
    Week week2 = new Week("Test Week");
    assertEquals(0, week2.getMaxTasks());
    week2.setMaxTasks(1);
    assertEquals(1, week2.getMaxTasks());
  }

  /**
   * A test to test setting the max Events
   */
  @Test
  public void testSetMaxEvents() {
    Week week2 = new Week("Test Week");
    assertEquals(0, week2.getMaxEvents());
    week2.setMaxEvents(100);
    assertEquals(100, week2.getMaxEvents());
  }

  /**
   * A test to test setting the Theme
   */
  @Test
  public void testSetTheme() {
    assertEquals(Theme.LIGHT, week1.getTheme());
    this.week1.setTheme(Theme.DARK);
    assertEquals(Theme.DARK, week1.getTheme());
  }

  /**
   * A test to test getting the Theme
   */
  @Test
  public void testGetTheme() {
    assertEquals(Theme.LIGHT, week1.getTheme());
    this.week1.setTheme(Theme.DARK);
    assertEquals(Theme.DARK, week1.getTheme());
  }


}