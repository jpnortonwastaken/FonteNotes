package cs3500.pa05.controller;

import cs3500.pa05.model.Activity;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Iweek;
import cs3500.pa05.model.Task;
import cs3500.pa05.view.ErrorViewer;
import java.util.UUID;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class that creates a new window to display the details of a task or event.
 */

public class EventTaskPopUp {

  Iweek week;
  Render render;
  TextField name;
  TextArea description;
  SplitMenuButton days;

  TextField start;
  TextField duration;

  /**
   * Constructor for DetailViewer.
   *
   * @param week   - the week the bullet journal is on
   * @param render - the render object in order to render the tasks in
   */

  public EventTaskPopUp(Iweek week, Render render) {
    this.week = week;
    this.render = render;
  }

  /**
   * Opens a new window displaying the details of a task or event.
   *
   * @param activity - the activity to display the details of
   */
  public void popUp(Activity activity) {
    VBox vbox = new VBox();
    vbox.setPadding(new Insets(10, 10, 10, 10));
    vbox.getStyleClass().add("details-vbox");
    vbox.setSpacing(10);

    name = new TextField();
    name.getStyleClass().add("details-name-text-box");
    name.getStyleClass().add("editing");

    description = new TextArea();
    description.getStyleClass().add("details-description-text-area");
    description.getStyleClass().add("editing");
    description.setPrefWidth(400);
    description.setPrefHeight(150);

    days = new SplitMenuButton();
    days.getStyleClass().add("day-split-menu");
    days.setText("Day");


    start = new TextField();
    start.getStyleClass().add("details-name-text-box");
    start.getStyleClass().add("editing");

    duration = new TextField();
    duration.getStyleClass().add("details-name-text-box");
    duration.getStyleClass().add("editing");


    HBox confirmHbox = new HBox();
    confirmHbox.setAlignment(Pos.CENTER_RIGHT);
    Button confirm = new Button("Confirm");
    confirm.getStyleClass().add("main-button");
    confirmHbox.getChildren().add(confirm);

    Label nameLabel = new Label("Name:");
    nameLabel.getStyleClass().add("details-label");

    Label descriptionLabel = new Label("Description:");
    descriptionLabel.getStyleClass().add("details-label");

    Label startLabel = new Label("Start time (ex. 12:30pm):");
    startLabel.getStyleClass().add("details-label");

    Label durationLabel = new Label("Duration in minutes (ex. 100):");
    durationLabel.getStyleClass().add("details-label");

    if (activity instanceof Event) {
      vbox.getChildren().addAll(nameLabel, name, descriptionLabel, description,
          startLabel, start, durationLabel, duration, days, confirmHbox);
    } else {
      vbox.getChildren().addAll(nameLabel, name, descriptionLabel, description, days,
          confirmHbox);
    }

    HandleDay.handleDay(days);

    Stage stage = new Stage();
    Scene scene = new Scene(vbox);
    scene.getStylesheets().clear();
    scene.getStylesheets().add(this.week.getTheme().toString().toLowerCase() + ".css");
    stage.setScene(scene);
    stage.setAlwaysOnTop(true);
    stage.setResizable(false);
    stage.show();
    // Convert the result

    if (activity instanceof Event) {
      confirm.setOnAction(e -> handleConfirmEvent(stage));
    } else {
      confirm.setOnAction(e -> handleConfirmTask(stage));

    }
  }

  /**
   * Handles the confirm button for the task pop up
   *
   * @param stage - the stage that the pop-up is on
   */

  private void handleConfirmTask(Stage stage) {
    if (name.getText().isEmpty()) {
      ErrorViewer.displayError("You did not specify a name for your task");
    } else if (days.getText().equals("Day")) {
      ErrorViewer.displayError("You did not specify a day for your task");
    } else {
      UUID uuid = UUID.randomUUID();
      Task task = new Task(name.getText(),
          DayOfWeek.converter(days.getText()), uuid.toString(),
          description.getText(), false);
      if (checkTaskCount(task.getDay())) {
        week.addTask(task);
      } else {
        ErrorViewer.displayError("You have exceeded the maximum amount of tasks "
            + "(" + week.getMaxTasks() + ")" + " for this day.");
      }
      stage.close();
      render.render();
    }
  }

  /**
   * Handles the confirm button for the task pop up
   *
   * @param stage - the stage that the pop-up is on
   */

  private void handleConfirmEvent(Stage stage) {
    // Return a new Task object with the entered values
    if (name.getText().isEmpty()) {
      ErrorViewer.displayError("You did not specify a name for your event");
    } else if (start.getText().isEmpty()) {
      ErrorViewer.displayError("You did not specify a start time for your event");
    } else if (duration.getText().isEmpty()) {
      ErrorViewer.displayError("You did not specify a duration for your event");
    } else if (days.getText().equals("Day")) {
      ErrorViewer.displayError("You did not specify a day for your event");
    } else {
      UUID uuid = UUID.randomUUID();
      Event event = new Event(name.getText(),
          uuid.toString(),
          DayOfWeek.converter(days.getText()),
          start.getText(),
          duration.getText(),
          description.getText());
      if (checkEventCount((event.getDay()))) {
        week.addEvent(event);
      } else {
        ErrorViewer.displayError("You have exceeded the maximum amount of events "
            + "(" + week.getMaxEvents() + ")" + " for this day.");
      }
      stage.close();
      render.render();
    }
  }


  /**
   * Checks if the user has exceeded the maximum amount of tasks for a given day
   *
   * @param day - the day to check
   * @return true if the user has not exceeded the maximum amount
   *         of tasks for a given day
   */

  private boolean checkTaskCount(DayOfWeek day) {
    int counter = 0;
    for (Task t : week.getTasks()) {
      if (t.getDay().equals(day)) {
        counter++;
      }
    }
    return counter < week.getMaxTasks();
  }


  /**
   * Checks if the user has exceeded the maximum amount of events for a day
   *
   * @param day - the day to check
   * @return true if the user has not exceeded the maximum amount of events for a day
   */

  private boolean checkEventCount(DayOfWeek day) {
    int counter = 0;
    for (Event e : week.getEvents()) {
      if (e.getDay().equals(day)) {
        counter++;
      }
    }
    return counter < week.getMaxEvents();
  }
}
