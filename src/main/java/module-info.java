module com.org.gerenciamentocurso {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires lombok;


    opens com.org.gerenciamentocurso.model to org.hibernate.orm.core;
    opens com.org.gerenciamentocurso. to javafx.fxml;

    opens com.org.gerenciamentocurso.controller to javafx.fxml;
    opens com.org.gerenciamentocurso.controller to javafx.fxml;
    exports com.org.gerenciamentocurso;
}
