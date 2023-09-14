package cs3500.pa05.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

/**
 * An interface that represents a week
 */

@JsonDeserialize(as = Week.class)
public interface Iweek {

  /**
   * Adds a task to the week.
   *
   * @param task - the task to add
   */

  void addTask(Task task);

  /**
   * Adds an event to the week.
   *
   * @param event - the event to add
   */

  void addEvent(Event event);

  /**
   * Gets the theme of the week.
   */

  void cycleTheme();

  /**
   * Gets the theme of the week.
   *
   * @return - the theme of the week
   */

  List<Task> getTasks();

  /**
   * Gets the events of the week.
   *
   * @return - the events of the week
   */

  List<Event> getEvents();

  /**
   * Deletes an activity from the week.
   *
   * @param uuid - the UUID of the activity to delete
   */

  void deleteActivity(String uuid);

  /**
   * Gets the name of the week.
   *
   * @return - the name of the week
   */

  String getName();

  /**
   * Sets the name of the week.
   *
   * @param name - the name of the week
   */

  void setName(String name);

  /**
   * Gets the max number of tasks.
   *
   * @return - the max number of tasks
   */

  int getMaxTasks();

  /**
   * Gets the max number of events.
   *
   * @param amount - the max number of events
   */

  void setMaxTasks(int amount);

  /**
   * Gets the max number of events.
   *
   * @return - the max number of events
   */

  int getMaxEvents();

  /**
   * Sets the max number of events.
   *
   * @param events - the max number of events
   */

  void setMaxEvents(int events);

  /**
   * Gets the theme of the week.
   *
   * @return - the theme of the week
   */

  Theme getTheme();

  /**
   * Sets the theme of the week.
   *
   * @param theme - the theme of the week
   */

  void setTheme(Theme theme);
}