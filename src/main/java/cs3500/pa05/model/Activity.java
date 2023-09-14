package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * An abstract class that represents an activity.
 */

public abstract class Activity {

  /**
   * The name of the Activity.
   */

  public String name;

  /**
   * The day of the week the activity occurs on.
   */

  @JsonProperty("dayOfWeek")
  public DayOfWeek dayOfWeek;

  /**
   * The universally unique identifier for this activity.
   */

  public String uuid;

  /**
   * A description of the activity.
   */

  public String description;

  /**
   * Constructs an Activity object.
   *
   * @param name        the name of the Activity
   * @param dayOfWeek   the day of the week the activity occurs on
   * @param uuid        the universally unique identifier for this activity
   * @param description a description of the activity
   */

  public Activity(String name, DayOfWeek dayOfWeek, String uuid, String description) {
    this.name = name;
    this.dayOfWeek = dayOfWeek;
    this.uuid = uuid;
    this.description = description;
  }

  /**
   * No-arg constructor for deserialization.
   */

  public Activity() {
  }

  /**
   * Method to get details of an activity. To be implemented by subclasses.
   *
   * @return a list of strings representing the details of the activity
   */

  public abstract List<String> getDetails();

  /**
   * Gets the UUID of the activity.
   *
   * @return the UUID of the activity
   */

  public String getUuid() {
    return this.uuid;
  }

  /**
   * Gets the name of the activity.
   *
   * @return the name of the activity
   */

  public String getName() {
    return this.name;
  }

  /**
   * Sets the name of the activity.
   *
   * @param name the new name of the activity
   */

  public void setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    this.name = name;
  }

  /**
   * Gets the day of the activity.
   *
   * @return - the day of the activity
   */
  public DayOfWeek getDay() {
    return this.dayOfWeek;
  }

  /**
   * Gets the description of the activity.
   *
   * @return the description of the activity
   */

  public String getDescription() {
    return this.description;
  }

  /**
   * Sets the description of the activity.
   *
   * @param description the new description of the activity
   */

  public void setDescription(String description) {
    if (description == null) {
      throw new IllegalArgumentException("Description cannot be null.");
    }
    this.description = description;
  }
}
