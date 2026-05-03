module org.example._1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example._1 to javafx.fxml;
    exports org.example._1;
}