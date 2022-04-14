module com.testcase.testracers {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.testcase.testracers to javafx.fxml;
    exports com.testcase.testracers;
    exports com.testcase.testracers.logic;
    opens com.testcase.testracers.logic to javafx.fxml;
}