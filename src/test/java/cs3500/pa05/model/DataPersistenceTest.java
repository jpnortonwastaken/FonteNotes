package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class to test data persistence
 */
class DataPersistenceTest {
  private DataPersistence dataPersistence;

  @BeforeEach
  public void setUp() {
    dataPersistence = new DataPersistence();
  }

  /**
   * A test to test the saving and loading of the week
   */
  @Test
  public void testSaveAndLoadWeek() {
    // Create a test Week object
    Week week = new Week("Test Week");

    // Set up file path for saving and loading
    String filePath = "src/exDirectory/testing.bujo";
    String invalidFilePath = "ikjfdnakjf/exDirectory/thisshouldn't work";

    // Save the Week object
    dataPersistence.saveWeek(week, filePath);
    assertThrows(IllegalArgumentException.class, () -> dataPersistence.saveWeek(
        week, invalidFilePath));

    // Load the Week object
    Iweek loadedWeek = dataPersistence.loadWeek(filePath);

    // Assert that the loaded Week is not null and has the same name as the original Week
    assertNotNull(loadedWeek);
    assertEquals(week.getName(), loadedWeek.getName());
  }

  /**
   * A test to test the invalid loading of the week
   */
  @Test
  public void testLoadWeekWithInvalidFile() {
    // Set up an invalid file path
    String invalidFilePath = "invalid_file.json";

    // Attempt to load a Week object from an invalid file
    assertThrows(IllegalArgumentException.class, () -> dataPersistence.loadWeek(invalidFilePath));

  }

}