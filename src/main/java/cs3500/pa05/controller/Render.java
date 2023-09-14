package cs3500.pa05.controller;

import cs3500.pa05.model.Activity;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Iweek;
import cs3500.pa05.model.Task;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Renders the task or event
 */

public class Render {

  private final VBox mondayVbox;

  private final VBox tuesdayVbox;

  private final VBox wednesdayVbox;

  private final VBox thursdayVbox;

  private final VBox fridayVbox;

  private final VBox saturdayVbox;

  private final VBox sundayVbox;

  private final Label nameWeekLabel;

  private final Iweek week;

  private final DetailViewer detailViewer;
  WeekOverview weekOverview;

  /**
   * Constructor for the render
   *
   * @param mondayVbox    - monday Vbox
   * @param tuesdayVbox   - tuesday Vbox
   * @param wednesdayVbox - wednesday Vbox
   * @param thursdayVbox  - thursday Vbox
   * @param fridayVbox    - friday Vbox
   * @param saturdayVbox  - saturdayVbox
   * @param sundayVbox    - sundayVbox
   * @param week          - the current week
   * @param weekOverview  - the week overview
   * @param nameWeekLabel - the label for the week name
   */

  public Render(VBox mondayVbox, VBox tuesdayVbox, VBox wednesdayVbox, VBox thursdayVbox,
                VBox fridayVbox, VBox saturdayVbox, VBox sundayVbox, Label nameWeekLabel,
                Iweek week,
                WeekOverview weekOverview) {
    this.mondayVbox = mondayVbox;
    this.tuesdayVbox = tuesdayVbox;
    this.wednesdayVbox = wednesdayVbox;
    this.thursdayVbox = thursdayVbox;
    this.fridayVbox = fridayVbox;
    this.saturdayVbox = saturdayVbox;
    this.sundayVbox = sundayVbox;
    this.nameWeekLabel = nameWeekLabel;
    this.week = week;
    this.detailViewer = new DetailViewer(this, this.week);
    this.weekOverview = weekOverview;
  }

  /**
   * clears the screen and then renders the events and tasks for the weeks
   */

  public void render() {
    clearBoard();
    renderName();
    renderEvents(this.week.getEvents());
    renderTasks(this.week.getTasks());
    weekOverview.setOverviewAll();
  }

  /**
   * sets the name of the week label
   */

  private void renderName() {
    this.nameWeekLabel.setText(this.week.getName());
  }

  /**
   * empties the all the vboxes in the week
   */

  private void clearBoard() {
    this.mondayVbox.getChildren().clear();
    this.tuesdayVbox.getChildren().clear();
    this.wednesdayVbox.getChildren().clear();
    this.thursdayVbox.getChildren().clear();
    this.fridayVbox.getChildren().clear();
    this.saturdayVbox.getChildren().clear();
    this.sundayVbox.getChildren().clear();
  }

  /**
   * @param tasks - the week's tasks
   */

  public void renderTasks(List<Task> tasks) {

    for (Task t : tasks) {
      HBox contents = new HBox();
      contents.getStyleClass().add("task-hbox");

      Label taskLabel = new Label(t.getName());
      taskLabel.getStyleClass().add("task-event-label");

      taskLabel.setOnMouseEntered(event -> taskLabel.setStyle("-fx-font-size: 13pt;"));
      taskLabel.setOnMouseExited(event -> taskLabel.setStyle("-fx-font-size: 12pt;"));

      // click behavior
      taskLabel.setOnMouseClicked(e -> detailViewer.displayDetails(t));

      String checkmark = new String(Character.toChars(0x2714));
      Button button = new Button(checkmark);
      button.getStyleClass().add("checkbox");
      button.setId(t.getUuid());
      if (t.isCompleted()) {
        button.getStyleClass().add("completed");
      }

      button.setOnAction(e -> handleCheck(button.getId()));
      contents.getChildren().add(button);
      contents.getChildren().add(taskLabel);

      this.addContents(t, contents);
    }
  }


  /**
   * @param uuid - the uuid of the task
   */

  private void handleCheck(String uuid) {
    for (Task t : this.week.getTasks()) {
      if (t.getUuid().equals(uuid)) {
        t.setComplete(!t.isCompleted());
      }
    }
    render();
  }

  /**
   * @param events - the events of the week
   */

  public void renderEvents(List<Event> events) {
    for (Event e : events) {
      VBox contents = new VBox();
      contents.getStyleClass().add("event-vbox");

      Label taskLabel = new Label(e.getName());
      taskLabel.getStyleClass().add("task-event-label");
      taskLabel.setId(e.getUuid());
      contents.getChildren().add(taskLabel);

      // setting the hover behavior
      taskLabel.setOnMouseEntered(event -> taskLabel.setStyle("-fx-font-size: 13pt;"));
      taskLabel.setOnMouseExited(event -> taskLabel.setStyle("-fx-font-size: 12pt;"));

      // setting the click behavior
      taskLabel.setOnMouseClicked(event -> detailViewer.displayDetails(e));

      Label startLabel = new Label(e.getStartTime());
      Label durationLabel = new Label(e.getDuration() + " mins");
      startLabel.getStyleClass().add("event-data-label");
      durationLabel.getStyleClass().add("event-data-label");

      HBox hbox = new HBox();
      hbox.getChildren().add(startLabel);
      hbox.getChildren().add(durationLabel);
      hbox.getStyleClass().add("event-data-hbox");

      contents.getChildren().add(hbox);

      this.addContents(e, contents);
    }
  }

  /**
   * deletes the activity and renders the week
   *
   * @param uuid - the uuid of the activity
   */

  public void deleteAndRerender(String uuid) {
    this.week.deleteActivity(uuid);
    render();
  }

  /**
   * adds the contents to the correct vbox
   *
   * @param activity - the activity to be added
   * @param contents - the contents of the activity
   */

  private void addContents(Activity activity, Pane contents) {
    if (activity.getDay().equals(DayOfWeek.MONDAY)) {
      this.mondayVbox.getChildren().add(contents);
    } else if (activity.getDay().equals(DayOfWeek.TUESDAY)) {
      this.tuesdayVbox.getChildren().add(contents);
    } else if (activity.getDay().equals(DayOfWeek.WEDNESDAY)) {
      this.wednesdayVbox.getChildren().add(contents);
    } else if (activity.getDay().equals(DayOfWeek.THURSDAY)) {
      this.thursdayVbox.getChildren().add(contents);
    } else if (activity.getDay().equals(DayOfWeek.FRIDAY)) {
      this.fridayVbox.getChildren().add(contents);
    } else if (activity.getDay().equals(DayOfWeek.SATURDAY)) {
      this.saturdayVbox.getChildren().add(contents);
    } else if (activity.getDay().equals(DayOfWeek.SUNDAY)) {
      this.sundayVbox.getChildren().add(contents);
    }
  }
}

