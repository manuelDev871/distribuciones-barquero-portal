module org.example.proyectointermodulardistribucionesbarquero {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example.proyectointermodulardistribucionesbarquero.controller to javafx.fxml;
    opens org.example.proyectointermodulardistribucionesbarquero.main to javafx.graphics;

    exports org.example.proyectointermodulardistribucionesbarquero.main;
}