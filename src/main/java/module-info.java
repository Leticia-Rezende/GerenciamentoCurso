module com.org.gerenciamentocurso {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires static lombok;


    opens com.org.gerenciamentocurso to javafx.fxml;
    exports com.org.gerenciamentocurso.Controller;
    exports com.org.gerenciamentocurso.DAO;
    exports com.org.gerenciamentocurso.Model;
    exports com.org.gerenciamentocurso;

}