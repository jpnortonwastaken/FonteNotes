package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * Represents a GUI view for a Bullet Journal.
 */
public interface JournalGuiView {
  /**
   * Loads a scene from Bullet Journal GUI layout.
   *
   * @return the layout
   */
  Scene load() throws IllegalStateException;
}