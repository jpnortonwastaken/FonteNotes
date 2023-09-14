package cs3500.pa05.view;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A class to show an error to the user
 */
public class SplashScreen {

  /**
   * Displays the splash screen to the user
   */
  public static void displaySplash() {
    JournalGuiView jgvi = new JournalGuiViewImpl("splash.fxml");

    Stage stage = new Stage();
    stage.setScene(jgvi.load());
    stage.setTitle("Splash Screen");
    stage.setAlwaysOnTop(true);
    stage.setResizable(false);
    stage.show();

    FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.75), stage.getScene().getRoot());
    fadeIn.setFromValue(0.0);
    fadeIn.setToValue(1.0);

    FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.75), stage.getScene().getRoot());
    fadeOut.setFromValue(1.0);
    fadeOut.setToValue(0.0);
    fadeOut.setOnFinished(e -> stage.close());

    PauseTransition stayOn = new PauseTransition(Duration.seconds(0.50));

    new SequentialTransition(fadeIn, stayOn, fadeOut).play();
  }


}
