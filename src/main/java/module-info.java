module com.testcase.testracers {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;


    opens com.testcase.testracers to javafx.fxml;
    exports com.testcase.testracers;
    exports com.testcase.testracers.logic;
    opens com.testcase.testracers.logic to javafx.fxml;
    exports com.testcase.testracers.pars;
    opens com.testcase.testracers.pars to javafx.fxml;
}