package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a week
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Task extends Activity {
  private boolean isComplete;

  /**
   * Constructs a Task object.
   *
   * @param name        - the name of the Activity
   * @param dayOfWeek   - the day of the week
   * @param uuid        - the uuid of the event
   * @param description - the description of the event
   * @param isComplete  - whether the task is complete
   */

  @JsonCreator
  public Task(@JsonProperty("name") String name,
              @JsonProperty("dayOfWeek") DayOfWeek dayOfWeek,
              @JsonProperty("uuid") String uuid,
              @JsonProperty("description") String description,
              @JsonProperty("completed") boolean isComplete) {
    super(name, dayOfWeek, uuid, description);
    this.isComplete = isComplete;
  }

  /**
   * Constructs a Task object without a description.
   *
   * @param name      - the name of the Activity
   * @param dayOfWeek - the day of the week
   * @param uuid      - the uuid of the event
   */
  public Task(String name, DayOfWeek dayOfWeek, String uuid) {
    super(name, dayOfWeek, uuid, "");
    this.isComplete = false;
  }

  /**
   * No-argument constructor for deserialization.
   */

  public Task() {
    // No arg constructor
  }

  /**
   * Retrieves the details of this event as a list of strings.
   * ignored by JSON
   *
   * @return details - the details of this event as a list of strings
   */

  @JsonIgnore
  @Override
  public List<String> getDetails() {
    ArrayList<String> details = new ArrayList<>();
    details.add(name);
    details.add(String.valueOf(dayOfWeek));
    details.add(String.valueOf(uuid));
    details.add(String.valueOf(isComplete));
    return details;
  }

  /**
   * Sets the completion status of the task.
   *
   * @param complete - the completion status of the task
   */

  public void setComplete(boolean complete) {
    isComplete = complete;
  }

  /**
   * Gets the completion status of the task.
   *
   * @return isComplete
   */

  public boolean isCompleted() {
    return this.isComplete;
  }
}