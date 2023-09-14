package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents an event. Inherits from Activity.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event extends Activity {
  private String startTime;
  private String duration;

  /**
   * Constructs an Event object with a description.
   *
   * @param name        - the name of the Activity
   * @param uuid        - the uuid of the event
   * @param dayOfWeek   - the day of the week
   * @param startTime   - the starting time of the event
   * @param duration    - the duration of the event
   * @param description - the description of the event
   */

  public Event(String name, String uuid, DayOfWeek dayOfWeek,
               String startTime, String duration, String description) {
    super(name, dayOfWeek, uuid, description);
    if (startTime == null || startTime.isEmpty() || duration == null || duration.isEmpty()) {
      throw new IllegalArgumentException("Start time or duration cannot be null or empty.");
    }
    this.startTime = startTime;
    this.duration = duration;
  }

  /**
   * Constructs an Event object without a description.
   *
   * @param name      - the name of the Activity
   * @param uuid      - the uuid of the event
   * @param dayOfWeek - the day of the week
   * @param startTime - the starting time of the event
   * @param duration  - the duration of the event
   */

  public Event(String name, String uuid, DayOfWeek dayOfWeek,
               String startTime, String duration) {
    this(name, uuid, dayOfWeek, startTime, duration, "");
  }

  /**
   * No-argument constructor for deserialization.
   */

  public Event() {
  }

  /**
   * Retrieves the details of this event as a list of strings.
   *
   * @return a list of strings containing the event's details
   */

  @Override
  public List<String> getDetails() {
    ArrayList<String> details;
    details = new ArrayList<>();
    details.add(name);
    details.add(String.valueOf(dayOfWeek));
    details.add(String.valueOf(startTime));
    details.add(String.valueOf(duration));
    return details;
  }



  /**
   * Retrieves the start time of this event.
   *
   * @return the start time of this event
   */

  public String getStartTime() {
    return this.startTime;
  }

  /**
   * Sets the start time of this event.
   *
   * @param startTime - the new start time
   */

  public void setStartTime(String startTime) {
    if (startTime == null || startTime.isEmpty()) {
      throw new IllegalArgumentException("Start time cannot be null or empty.");
    }
    this.startTime = startTime;
  }

  /**
   * Retrieves the duration of this event.
   *
   * @return the duration of this event
   */

  public String getDuration() {
    return this.duration;
  }

  /**
   * Sets the duration of this event.
   *
   * @param duration - the new duration
   */

  public void setDuration(String duration) {
    if (duration == null || duration.isEmpty()) {
      throw new IllegalArgumentException("Duration cannot be null or empty.");
    }
    this.duration = duration;
  }

}
