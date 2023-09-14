package cs3500.pa05.controller;

import cs3500.pa05.model.DayOfWeek;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

/**
 * A class for choosing a day when creating an Activity
 */
public class HandleDay {

  /**
   * A method for choosing a day when creating an Activity
   *
   * @param dayOfWeek - the day of the week the Activity is going to be on
   */
  public static void handleDay(SplitMenuButton dayOfWeek) {
    MenuItem choice1 = new MenuItem(DayOfWeek.MONDAY.toString());
    choice1.getStyleClass().add("day-split-menu-item");
    choice1.setOnAction(event -> {
      dayOfWeek.setText(choice1.getText());
    });

    MenuItem choice2 = new MenuItem(DayOfWeek.TUESDAY.toString());
    choice2.getStyleClass().add("day-split-menu-item");
    choice2.setOnAction(event -> {
      dayOfWeek.setText(choice2.getText());
    });

    MenuItem choice3 = new MenuItem(DayOfWeek.WEDNESDAY.toString());
    choice3.getStyleClass().add("day-split-menu-item");
    choice3.setOnAction(event -> {
      dayOfWeek.setText(choice3.getText());
    });

    MenuItem choice4 = new MenuItem(DayOfWeek.THURSDAY.toString());
    choice4.getStyleClass().add("day-split-menu-item");
    choice4.setOnAction(event -> {
      dayOfWeek.setText(choice4.getText());
    });

    MenuItem choice5 = new MenuItem(DayOfWeek.FRIDAY.toString());
    choice5.getStyleClass().add("day-split-menu-item");
    choice5.setOnAction(event -> {
      dayOfWeek.setText(choice5.getText());
    });

    MenuItem choice6 = new MenuItem(DayOfWeek.SATURDAY.toString());
    choice6.getStyleClass().add("day-split-menu-item");
    choice6.setOnAction(event -> {
      dayOfWeek.setText(choice6.getText());
    });

    MenuItem choice0 = new MenuItem(DayOfWeek.SUNDAY.toString());
    choice0.getStyleClass().add("day-split-menu-item");
    choice0.setOnAction(event -> {
      dayOfWeek.setText(choice0.getText());
    });

    dayOfWeek.getItems().addAll(choice0, choice1, choice2, choice3, choice4, choice5, choice6);
  }
}
