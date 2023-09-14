package cs3500.pa05;

import cs3500.pa05.controller.JournalController;
import cs3500.pa05.controller.JournalControllerImpl;
import cs3500.pa05.model.Iweek;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.JournalGuiView;
import cs3500.pa05.view.JournalGuiViewImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The main entry point of the program
 */

public class Driver extends Application {

  /**
   * @param args - the command line args
   */

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) {
    Iweek week = new Week("Week Name");
    Stage createStage = new Stage();
    JournalController controller = new JournalControllerImpl(week, stage, createStage);
    JournalGuiView jgv = new JournalGuiViewImpl(controller, "board.fxml");
    JournalGuiView jgvp = new JournalGuiViewImpl(controller, "welcome.fxml");
    JournalGuiView jgvn = new JournalGuiViewImpl(controller, "createnew.fxml");
    try {
      // load and place the view's scene onto the stage
      stage.setScene(jgv.load());
      Scene mainScene = stage.getScene();
      mainScene.getStylesheets().clear();
      mainScene.getStylesheets().add(week.getTheme().toString().toLowerCase() + ".css");

      Stage welcomeStage = new Stage();
      welcomeStage.setScene(jgvp.load());
      Scene welcomeScene = welcomeStage.getScene();
      welcomeScene.getStylesheets().clear();
      welcomeScene.getStylesheets().add(week.getTheme().toString().toLowerCase() + ".css");

      createStage.setScene(jgvn.load());
      Scene createScene = createStage.getScene();
      createScene.getStylesheets().clear();
      createScene.getStylesheets().add(week.getTheme().toString().toLowerCase() + ".css");

      controller.run();

      stage.setTitle("FonteNotes");
      stage.getIcons().add(new Image("appicon.jpg"));
      stage.setResizable(false);

      welcomeStage.setTitle("Welcome!");
      welcomeStage.getIcons().add(new Image("appicon.jpg"));
      welcomeStage.setResizable(false);
      welcomeStage.show();

      createStage.setTitle("Create New Bulleted Journal");
      createStage.getIcons().add(new Image("appicon.jpg"));
      createStage.setResizable(false);

    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }
  }
}
