package cs3500.pa05.view;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A class to show an error to the user
 */
public class ErrorViewer {

  /**
   * Displays an error message to the user
   *
   * @param errorMessage - the error
   */
  public static void displayError(String errorMessage) {

    VBox vbox = new VBox();
    vbox.setPadding(new Insets(10, 10, 10, 10));
    vbox.setSpacing(10);

    Label errorLabel = new Label(errorMessage);
    errorLabel.setStyle("-fx-font-family: 'Arial';"
        + "-fx-font-size: 18.0px;"
        + "-fx-text-fill: black;"
        + "-fx-padding: 10;");
    vbox.getChildren().add(errorLabel);

    Stage stage = new Stage();
    Scene scene = new Scene(vbox);
    stage.setScene(scene);
    stage.setTitle("Error");
    stage.setAlwaysOnTop(true);
    stage.setResizable(false);
    stage.show();
  }


}
