module com.example.csd214test3bbishalamgai {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.csd214test3bbishalamgai to javafx.fxml;
    exports com.example.csd214test3bbishalamgai;
}