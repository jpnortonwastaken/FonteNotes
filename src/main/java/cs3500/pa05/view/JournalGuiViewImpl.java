package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * The view for the bulleted journal
 */
public class JournalGuiViewImpl implements JournalGuiView {

  private final FXMLLoader loader;

  /**
   * @param controller - the controller of that will control the fxml
   * @param fxml       - the fxml that loader should load
   */
  public JournalGuiViewImpl(JournalController controller, String fxml) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource(fxml));
    this.loader.setController(controller);
  }

  /**
   * @param fxml       - the fxml that loader should load
   */
  public JournalGuiViewImpl(String fxml) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource(fxml));
  }

  /**
   * Loads a scene from a BulletJournal GUI layout.
   *
   * @return the layout
   */

  @Override
  public Scene load() throws IllegalStateException {
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException(exc);
    }
  }
}
