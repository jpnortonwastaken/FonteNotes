package cs3500.pa05.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;

/**
 * This class is responsible for persisting IWeek objects to files,
 * and loading them back into memory. Uses the Jackson library for
 * object serialization and deserialization to and from JSON.
 */

public class DataPersistence {
  private final ObjectMapper mapper;

  /**
   * Default constructor for DataPersistence.
   * Initializes the ObjectMapper and enables indented output for human-readable JSON.
   */

  public DataPersistence() {
    this.mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
  }

  /**
   * Loads an IWeek object from a file located at a specified path.
   *
   * @param filePath the path of the file from which to load the IWeek
   * @return the IWeek object that was loaded from the file, or null if an error occurred
   */

  public Iweek loadWeek(String filePath) {
    try {
      return mapper.readValue(new File(filePath), Week.class);
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid file");
    }
  }

  /**
   * Saves an IWeek object to a file located at a specified path.
   *
   * @param week     the IWeek object to be saved
   * @param filePath the path of the file where the IWeek will be saved
   */

  public void saveWeek(Iweek week, String filePath) {
    try {
      mapper.writeValue(new File(filePath), week);
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid file");
    }
  }
}
