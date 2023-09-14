package cs3500.pa05.controller;

import cs3500.pa05.model.Activity;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Iweek;
import cs3500.pa05.model.Task;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class that creates a new window to display the details of a task or event.
 */

public class DetailViewer {

  private final Render render;

  TextField nameField;
  TextArea descriptionField;
  TextField timeField;
  TextField durationField;
  Iweek week;

  /**
   * Constructor for DetailViewer.
   *
   * @param render - the renderer in order to render the Activity
   * @param week   - the current weeks
   */

  public DetailViewer(Render render, Iweek week) {
    this.week = week;
    this.render = render;
  }

  /**
   * Opens a new window displaying the details of a task or event.
   *
   * @param activity The task or event to display the details of.
   */

  public void displayDetails(Activity activity) {
    Stage stage;
    stage = new Stage();
    VBox vbox = new VBox();
    vbox.setPadding(new Insets(10, 10, 10, 10));
    vbox.getStyleClass().add("details-vbox");
    vbox.setSpacing(10);

    // Create text fields for name and description
    Label nameLabel = new Label("Name");
    nameLabel.getStyleClass().add("details-label");
    nameField = new TextField(activity.getName());
    nameField.getStyleClass().add("details-name-text-box");

    Label descriptionLabel = new Label("Description");
    descriptionLabel.getStyleClass().add("details-label");
    descriptionField = new TextArea(activity.getDescription());
    descriptionField.getStyleClass().add("details-description-text-area");
    descriptionField.setPrefWidth(400);
    descriptionField.setPrefHeight(150);

    nameField.setEditable(false);
    descriptionField.setEditable(false);

    vbox.getChildren().add(nameLabel);
    vbox.getChildren().add(nameField);
    vbox.getChildren().add(descriptionLabel);
    vbox.getChildren().add(descriptionField);

    Button editButton;
    editButton = new Button("Edit");
    Button saveButton = new Button("Save");
    saveButton.setVisible(false);
    Button deleteButton;
    deleteButton = new Button("Delete");
    Button doneButton;
    doneButton = new Button("Done");

    // Parse description and create links for URLs
    List<Hyperlink> links = new ArrayList<>();
    String description = activity.getDescription();
    String[] words = description.split(" ");
    for (String word : words) {
      if (isUrl(word)) {
        Hyperlink link = new Hyperlink(word);
        link.setOnAction(e -> {
          try {
            Desktop.getDesktop().browse(new URI(word));
          } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
          }
        });
        links.add(link);
      }
    }

    vbox.getChildren().addAll(links);

    if (activity instanceof Event event) {

      VBox timeVbox = new VBox();
      timeVbox.setSpacing(10);
      Label timeLabel = new Label("Start time");
      timeLabel.getStyleClass().add("details-label");
      timeField = new TextField(event.getStartTime()); // get the start time of the event
      timeField.getStyleClass().add("details-name-text-box");
      timeVbox.getChildren().add(timeLabel);
      timeVbox.getChildren().add(timeField);

      VBox durationVbox = new VBox();
      durationVbox.setSpacing(10);
      Label durationLabel = new Label("Duration in minutes");
      durationLabel.getStyleClass().add("details-label");
      durationField = new TextField(event.getDuration()); // get the duration of the event
      durationField.getStyleClass().add("details-name-text-box");
      durationVbox.getChildren().add(durationLabel);
      durationVbox.getChildren().add(durationField);

      timeField.setEditable(false);
      durationField.setEditable(false);

      HBox timeAndDurationBox = new HBox(); // HBox to put the time and duration side by side
      timeAndDurationBox.setSpacing(10);
      timeAndDurationBox.getChildren().addAll(timeVbox, durationVbox);
      vbox.getChildren().add(timeAndDurationBox);
    }

    if (activity instanceof Task task) {

      Label completedLabel = new Label("Status");
      completedLabel.getStyleClass().add("details-label");
      Label completedText;
      if (task.isCompleted()) {
        completedText = new Label("Completed");
      } else {
        completedText = new Label("Not completed");
      }
      completedText.getStyleClass().add("status-text-label");

      VBox completedVbox = new VBox();
      completedVbox.setSpacing(10);
      completedVbox.getChildren().addAll(completedLabel, completedText);
      vbox.getChildren().add(completedVbox);
    }

    editButton.getStyleClass().add("main-button");
    editButton.setOnAction(e -> {
      nameField.setEditable(true);
      nameField.requestFocus();
      nameField.getStyleClass().add("editing");
      descriptionField.setEditable(true);
      descriptionField.getStyleClass().add("editing");
      if (activity instanceof Event) {
        timeField.setEditable(true);
        timeField.getStyleClass().add("editing");
        durationField.setEditable(true);
        durationField.getStyleClass().add("editing");
      }
      editButton.setVisible(false);
      saveButton.setVisible(true);
    });

    saveButton.getStyleClass().add("main-button");
    saveButton.setOnAction(e -> {
      saveChanges(activity);
      nameField.setEditable(false);
      descriptionField.setEditable(false);
      if (activity instanceof Event) {
        timeField.setEditable(false);
        durationField.setEditable(false);
      }
      stage.close();
    });

    deleteButton.getStyleClass().add("main-button");
    deleteButton.getStyleClass().add("delete-button");
    deleteButton.setOnAction(e -> {
      render.deleteAndRerender(activity.getUuid());
      stage.close();  // Close the details view after deletion
    });

    doneButton.getStyleClass().add("main-button");
    doneButton.setOnAction(e -> stage.close());

    HBox hbox = new HBox();
    hbox.setAlignment(Pos.CENTER_RIGHT);
    hbox.setSpacing(10);

    StackPane stackPane = new StackPane();
    stackPane.getChildren().add(editButton);
    stackPane.getChildren().add(saveButton);

    hbox.getChildren().add(stackPane);
    hbox.getChildren().add(doneButton);

    BorderPane borderPane = new BorderPane();
    borderPane.setLeft(deleteButton);
    borderPane.setRight(hbox);

    vbox.getChildren().add(borderPane);

    Scene scene = new Scene(vbox);
    scene.getStylesheets().clear();
    scene.getStylesheets().add(this.week.getTheme().toString().toLowerCase() + ".css");
    stage.setScene(scene);
    stage.setTitle(activity.getDetails().get(0) + " Details");
    stage.setAlwaysOnTop(true);
    stage.setResizable(false);
    stage.show();
    Platform.runLater(vbox::requestFocus);
  }

  /**
   * Check if a string is a URL.
   *
   * @param string - the string to check
   * @return - true if the string is a URL, false otherwise
   */

  private boolean isUrl(String string) {
    String urlRegex = "((http|https)://)(www.)?"
        + "[a-zA-Z0-9@:%._+~#?&//=]"
        + "{2,256}\\.[a-z]"
        + "{2,6}\\b([-a-zA-Z0-9@:%"
        + "._+~#?&//=]*)";
    Pattern pattern = Pattern.compile(urlRegex);
    Matcher matcher = pattern.matcher(string);
    return matcher.matches();
  }

  /**
   * Save the changes made to the activity.
   *
   * @param activity - the activity to save changes to
   */

  private void saveChanges(Activity activity) {
    activity.setName(nameField.getText());
    activity.setDescription(descriptionField.getText());
    if (activity instanceof Event event) {
      event.setStartTime(timeField.getText()); // Set the new time
      event.setDuration(durationField.getText()); // Set the new duration
    }

    // Rerender the week
    render.render();
  }
}
