module cs3500.pa05 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;

    requires org.controlsfx.controls;
  requires java.desktop;

  opens cs3500.pa05 to javafx.fxml;
    opens cs3500.pa05.model to com.fasterxml.jackson.databind;
    exports cs3500.pa05;
    exports cs3500.pa05.controller;
    exports cs3500.pa05.model;
    exports cs3500.pa05.view;
  opens cs3500.pa05.controller to com.fasterxml.jackson.databind, javafx.fxml;

}