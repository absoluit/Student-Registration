module org.example.collegestudentregistration {
    requires javafx.controls;

    opens collegestudentregistration to javafx.fxml;
    exports collegestudentregistration;
}