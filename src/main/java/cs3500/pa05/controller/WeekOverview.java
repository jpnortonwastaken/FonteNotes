package cs3500.pa05.controller;

import cs3500.pa05.model.Event;
import cs3500.pa05.model.Iweek;
import cs3500.pa05.model.Task;
import javafx.scene.control.Label;

/**
 * Class to represent the overview of the week
 */

public class WeekOverview {

  private final Label overviewTotalEvents;
  private final Label overviewTotalTasks;
  private final Label overviewTaskCompleted;
  private final Label overviewAllowedEvents;
  private final Label overviewAllowedTasks;
  private final Label overviewThemeCycle;
  private final Iweek week;

  /**
   * Constructor for the week overview
   *
   * @param overviewTotalEvents   - the total events label
   * @param overviewTotalTasks    - the total tasks label
   * @param overviewTaskCompleted - the total tasks completed label
   * @param overviewAllowedEvents - the total allowed events label
   * @param overviewAllowedTasks  - the total allowed tasks label
   * @param overviewThemeCycle    - the theme cycle label
   * @param week                  - the week
   */
  public WeekOverview(Label overviewTotalEvents,
                      Label overviewTotalTasks,
                      Label overviewTaskCompleted,
                      Label overviewAllowedEvents,
                      Label overviewAllowedTasks,
                      Label overviewThemeCycle, Iweek week) {
    this.overviewTotalEvents = overviewTotalEvents;
    this.overviewTotalTasks = overviewTotalTasks;
    this.overviewTaskCompleted = overviewTaskCompleted;
    this.overviewAllowedEvents = overviewAllowedEvents;
    this.overviewAllowedTasks = overviewAllowedTasks;
    this.overviewThemeCycle = overviewThemeCycle;
    this.week = week;
  }

  /**
   * sets the overview values
   */

  public void setOverviewAll() {
    this.setOverviewTotalEvents();
    this.setOverviewTotalTasks();
    this.setOverviewTaskCompleted();
    this.setOverviewAllowedEvents();
    this.setOverviewAllowedTasks();
    this.setOverviewThemeCycle();
  }

  /**
   * sets the overview total events
   */

  private void setOverviewTotalEvents() {
    int counter = 0;
    for (Event ignored : week.getEvents()) {
      counter++;
    }
    overviewTotalEvents.setText("Total Events: " + counter);
  }

  /**
   * sets the overview total tasks
   */

  private void setOverviewTotalTasks() {
    int counter = 0;
    for (Task ignored : week.getTasks()) {
      counter++;
    }
    overviewTotalTasks.setText("Total Tasks: " + counter);
  }

  /**
   * sets the overview task completed
   */

  private void setOverviewTaskCompleted() {
    int counter = 0;
    int counterCompleted = 0;
    for (Task t : week.getTasks()) {
      if (t.isCompleted()) {
        counterCompleted++;
      }
      counter++;
    }


    overviewTaskCompleted.setText("Tasks Completed: " + "("
        + counterCompleted + "/" + counter + ") "
        + checkPercentage(counterCompleted, counter) + "%");
  }

  /**
   * checks the percentage
   *
   * @param complete - the number of tasks completed
   * @param total    - the total number of tasks
   * @return - the percentage of tasks completed
   */

  private double checkPercentage(int complete, int total) {

    double percentage = 0;
    if (total != 0) {
      percentage = Math.round((double) complete / total * 100.0);
    }
    return percentage;
  }

  /**
   * sets the overview allowed events
   */

  private void setOverviewAllowedEvents() {
    overviewAllowedEvents.setText("Max Events Allowed: " + week.getMaxEvents());
  }

  /**
   * sets the overview allowed tasks
   */

  private void setOverviewAllowedTasks() {
    overviewAllowedTasks.setText("Max Tasks Allowed: " + week.getMaxTasks());
  }

  /**
   * sets the overview theme cycle
   */

  private void setOverviewThemeCycle() {
    overviewThemeCycle.setText("Theme: " + week.getTheme().toString().substring(0, 1).toUpperCase()
        + week.getTheme().toString().substring(1).toLowerCase());
  }
}
