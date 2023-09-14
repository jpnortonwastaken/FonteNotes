package cs3500.pa05.controller;

import cs3500.pa05.model.DayOfWeek;

/**
 * An interface that represents a controller
 */

public interface JournalController {

  /**
   * Runs the controller
   */

  void run();

  /**
   * Adds an event to the model
   *
   * @param name        - the name of the event
   * @param uuid        - the unique identifier of the event
   * @param dayOfWeek   - the day of the week the event is on
   * @param startTime   - the starting time of the event
   * @param duration    - the duration of the event
   * @param description - the description of the event
   */

  void addEvent(String name, String uuid, DayOfWeek dayOfWeek, String startTime, String duration,
                String description);

  /**
   * Adds a task to the model
   *
   * @param name        - the name of the task
   * @param dayOfWeek   - the day of the week the task is on
   * @param uuid        - the unique identifier of the task
   * @param description - the description of the task
   */

  void addTask(String name, DayOfWeek dayOfWeek, String uuid, String description);
}