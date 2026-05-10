module org.example.p.p12 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.p.p12 to javafx.fxml;
    exports org.example.p.p12;
}