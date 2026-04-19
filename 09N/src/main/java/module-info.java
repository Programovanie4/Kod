module org.example._9n {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example._9n to javafx.fxml;
    exports org.example._9n;
}