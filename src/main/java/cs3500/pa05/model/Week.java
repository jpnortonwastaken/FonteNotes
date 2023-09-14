package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a week
 */

public class Week implements Iweek {
  String name;
  Theme theme;
  List<Event> events;
  List<Task> tasks;
  int maxEvents;
  int maxTasks;


  /**
   * Creates a Week instance.
   *
   * @param name      the name of the week
   * @param theme     the theme of the week
   * @param events    a list of events occurring during the week
   * @param tasks     a list of tasks to be completed during the week
   * @param maxEvents the maximum number of events allowed in the week
   * @param maxTasks  the maximum number of tasks allowed in the week
   */

  @JsonCreator
  public Week(@JsonProperty("name") String name,
              @JsonProperty("theme") Theme theme,
              @JsonProperty("events") List<Event> events,
              @JsonProperty("tasks") List<Task> tasks,
              @JsonProperty("max-events") int maxEvents,
              @JsonProperty("max-tasks") int maxTasks) {
    this.name = name;
    this.theme = theme;
    this.events = (events != null) ? events : new ArrayList<>();
    this.tasks = (tasks != null) ? tasks : new ArrayList<>();
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
  }

  /**
   * Creates a Week instance with default values.
   *
   * @param name the name of the week
   */

  public Week(String name) {
    this(name, Theme.LIGHT, null, null, 0, 0);
  }


  @Override
  public void addTask(Task task) {
    tasks.add(task);
  }

  @Override
  public void addEvent(Event event) {
    events.add(event);
  }

  @Override
  public void cycleTheme() {
    if (this.theme.equals(Theme.LIGHT)) {
      this.theme = Theme.DARK;
    } else if (this.theme.equals(Theme.DARK)) {
      this.theme = Theme.MARK;
    } else if (this.theme.equals(Theme.MARK)) {
      this.theme = Theme.LIGHT;
    }
  }


  public List<Task> getTasks() {
    return this.tasks;
  }

  public List<Event> getEvents() {
    return this.events;
  }

  @Override
  public void deleteActivity(String uuid) {
    // Try removing from tasks
    tasks.removeIf(task -> task.getUuid().equals(uuid));

    // Try removing from events
    events.removeIf(event -> event.getUuid().equals(uuid));
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int getMaxTasks() {
    return this.maxTasks;
  }

  public void setMaxTasks(int amount) {
    this.maxTasks = amount;
  }

  @Override
  public int getMaxEvents() {
    return this.maxEvents;
  }

  public void setMaxEvents(int events) {
    this.maxEvents = events;
  }

  @Override
  public Theme getTheme() {
    return this.theme;
  }

  @Override
  public void setTheme(Theme theme) {
    this.theme = theme;
  }

}
