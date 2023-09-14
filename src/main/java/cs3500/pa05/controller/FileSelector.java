package cs3500.pa05.controller;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Utility class for selecting files with a JavaFX FileChooser.
 */

public class FileSelector {
  /**
   * Shows a FileChooser dialog for the user to select a .bujo file.
   *
   * @return the selected file, or null if the user cancels the operation
   */
  public static File chooseBujoFile() {
    FileChooser fileChooser = new FileChooser();

    // Set extension filter
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
        "BUJO files (*.bujo)", "*.bujo");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show open file dialog
    return fileChooser.showOpenDialog(new Stage());
  }

  /**
   * Shows a FileChooser dialog for the user to select a .bujo file to save to.
   *
   * @return - the selected file, or null if the user cancels the operation
   */

  public static File saveBujoFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save Bujo File");

    // Set initial file name and default directory
    fileChooser.setInitialFileName("MyFile.bujo");

    // Set extension filter
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
        "BUJO files (*.bujo)", "*.bujo");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show save file dialog
    File file = fileChooser.showSaveDialog(null);

    // Ensure the file has the correct extension
    if (file != null) {
      if (!file.getPath().endsWith(".bujo")) {
        file = new File(file.getPath() + ".bujo");
      }
    }
    return file;
  }
}
