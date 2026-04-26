module org.example._0 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example._0 to javafx.fxml;
    exports org.example._0;
}