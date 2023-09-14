package cs3500.pa05.controller;

import cs3500.pa05.model.DataPersistence;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Iweek;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Theme;
import cs3500.pa05.view.ErrorViewer;
import cs3500.pa05.view.SplashScreen;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * the controller for the journal
 */
public class JournalControllerImpl implements JournalController {
  private final Stage stage;
  private final Stage createStage;
  private final String password;

  Iweek week;
  Render render;
  WeekOverview weekOverview;
  DataPersistence dataPersistence;
  @FXML
  private Label overviewTotalEvents;
  @FXML
  private Label overviewTotalTasks;
  @FXML
  private Label overviewTaskCompleted;
  @FXML
  private Label overviewAllowedEvents;
  @FXML
  private Label overviewAllowedTasks;
  @FXML
  private Label overviewThemeCycle;
  // Inject UI elements from FXML
  @FXML
  private Button addTaskButton;
  @FXML
  private Button addEventButton;
  @FXML
  private Button saveButton;
  @FXML
  private Button openButton;
  @FXML
  private Button themeButton;
  @FXML
  private VBox mondayVbox;
  @FXML
  private VBox tuesdayVbox;
  @FXML
  private VBox wednesdayVbox;
  @FXML
  private VBox thursdayVbox;
  @FXML
  private VBox fridayVbox;
  @FXML
  private VBox saturdayVbox;
  @FXML
  private VBox sundayVbox;
  @FXML
  private Button welcomeOpenButton;
  @FXML
  private Button welcomeCreateButton;
  @FXML
  private TextField newNameTextField;
  @FXML
  private TextField newEventsMaxTextField;
  @FXML
  private TextField newTasksMaxTextField;
  @FXML
  private Button newCreateButton;
  @FXML
  private Label nameWeekLabel;
  @FXML
  private MenuItem menuAddTask;
  @FXML
  private MenuItem menuAddEvent;
  @FXML
  private MenuItem menuSaveAs;
  @FXML
  private MenuItem menuOpen;
  @FXML
  private MenuItem menuCycleTheme;
  @FXML
  private MenuItem menuNewWeek;
  @FXML
  private TextField welcomePasswordTextField;

  /**
   * JournalControllerImpl constructor
   *
   * @param week        - the current week
   * @param createStage - create the stage
   * @param stage       - the stage
   */
  public JournalControllerImpl(Iweek week, Stage stage, Stage createStage) {
    this.week = week;
    this.dataPersistence = new DataPersistence();
    this.stage = stage;
    this.createStage = createStage;
    this.password = "fontenot";
  }

  /**
   * Has the user choose the bujo file
   */

  public void chooseBujoFile() {
    if (welcomePasswordTextField.getText().equals(this.password)) {
      File file = FileSelector.chooseBujoFile();
      if (file != null) {
        DataPersistence dataPersistence = new DataPersistence();
        week = dataPersistence.loadWeek(file.getAbsolutePath());
        if (week != null) {
          weekOverview =
              new WeekOverview(overviewTotalEvents, overviewTotalTasks, overviewTaskCompleted,
                  overviewAllowedEvents, overviewAllowedTasks, overviewThemeCycle, week);
          render = new Render(mondayVbox, tuesdayVbox, wednesdayVbox, thursdayVbox,
              fridayVbox, saturdayVbox, sundayVbox, nameWeekLabel, week, weekOverview);
          Stage stage = (Stage) welcomeOpenButton.getScene().getWindow();
          stage.close();
          SplashScreen.displaySplash();
          this.stage.show();
          this.initialize();
          this.updateTheme();
          render.render(); // Update the UI to reflect the loaded data
        }
      }
    } else {
      ErrorViewer.displayError("Incorrect password");
    }
  }

  /**
   * Creates a new week
   */
  private void saveWeek() {
    File file = FileSelector.saveBujoFile();
    if (file != null) {
      dataPersistence.saveWeek(week, file.getAbsolutePath());
    }
  }

  private void setActions(Button button, MenuItem menuItem, Runnable action) {
    button.setOnAction(e -> action.run());
    menuItem.setOnAction(e -> action.run());
  }

  /**
   * the functionality of the week
   */

  @FXML
  private void initialize() {
    if (week != null) {

      EventTaskPopUp popUp = new EventTaskPopUp(week, render);
      // Set up action listeners for the buttons
      setActions(addTaskButton, menuAddTask, () -> popUp.popUp(new Task()));
      setActions(addEventButton, menuAddEvent, () -> popUp.popUp(new Event()));
      setActions(saveButton, menuSaveAs, this::saveWeek);
      setActions(openButton, menuOpen, this::chooseBujoFile);
      setActions(themeButton, menuCycleTheme, this::changeTheme);
      menuNewWeek.setOnAction(e -> createWeek());

    } else {
      System.err.println("No file Selected");
    }
  }

  /**
   * runs the controller for to initialize the beginning of the view
   */

  @Override
  public void run() {
    welcomeOpenButton.setOnAction(e -> chooseBujoFile());
    welcomeCreateButton.setOnAction(e -> createWeek());
  }

  /**
   * adds events to the week
   */

  @Override
  public void addEvent(String name, String uuid, DayOfWeek dayOfWeek, String startTime,
                       String duration, String description) {
    Event newEvent = new Event(name, uuid, dayOfWeek, startTime, duration);
    week.addEvent(newEvent);
  }

  /**
   * adds tasks to the week
   */

  @Override
  public void addTask(String name, DayOfWeek dayOfWeek, String uuid, String description) {
    Task newTask = new Task(name, dayOfWeek, uuid, description, true);
    week.addTask(newTask);
  }


  /**
   * Creates a new dialogue box when the task button is clicked
   */

  @FXML
  private void createWeek() {
    if (welcomePasswordTextField.getText().equals(this.password)) {
      Stage stage = (Stage) welcomeCreateButton.getScene().getWindow();
      stage.close();
      createStage.show();
      newNameTextField.clear();
      newEventsMaxTextField.clear();
      newTasksMaxTextField.clear();
      newCreateButton.setOnAction(e -> {
        String name = newNameTextField.getText();
        String maxEvents = newEventsMaxTextField.getText();
        String maxTasks = newTasksMaxTextField.getText();

        int events = 0;
        int tasks = 0;
        try {
          events = Integer.parseInt(maxEvents);
          tasks = Integer.parseInt(maxTasks);

          this.week.setName(name);
          this.week.setMaxEvents(events);
          this.week.setMaxTasks(tasks);
          this.week.setTheme(Theme.LIGHT);
          this.week.getEvents().clear();
          this.week.getTasks().clear();

          // Add your desired logic for valid input here

        } catch (NumberFormatException ex) {
          // Display an error message for invalid input
          System.err.println("invalid max tasks or max events");
        }

        if (week != null && !name.isEmpty() && !maxEvents.isEmpty() && !maxTasks.isEmpty()
            && events > 0 && tasks > 0) {
          weekOverview =
              new WeekOverview(overviewTotalEvents, overviewTotalTasks, overviewTaskCompleted,
                  overviewAllowedEvents, overviewAllowedTasks, overviewThemeCycle, week);
          render = new Render(mondayVbox, tuesdayVbox, wednesdayVbox, thursdayVbox,
              fridayVbox, saturdayVbox, sundayVbox, nameWeekLabel, week, weekOverview);
          createStage.close();
          SplashScreen.displaySplash();
          this.stage.show();
          this.initialize();
          render.render(); // Update the UI to reflect the loaded data
        } else if (name.isEmpty()) {
          ErrorViewer.displayError("Invalid name");
        } else if (maxEvents.isEmpty() || events <= 0) {
          ErrorViewer.displayError("Invalid maximum events");
        } else if (maxTasks.isEmpty() || tasks <= 0) {
          ErrorViewer.displayError("Invalid maximum tasks");
        }
      });
    } else {
      ErrorViewer.displayError("Incorrect password");
    }
  }

  /**
   * cycles through the themes
   */

  private void changeTheme() {
    this.week.cycleTheme();
    this.updateTheme();
    render.render();
  }

  /**
   * updates the theme
   */

  private void updateTheme() {
    Scene mainScene = this.stage.getScene();
    mainScene.getStylesheets().clear();
    mainScene.getStylesheets().add(this.week.getTheme().toString().toLowerCase() + ".css");
  }



}