module org.example.howtowithjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.howtowithjavafx to javafx.fxml;
    exports org.example.howtowithjavafx;
}