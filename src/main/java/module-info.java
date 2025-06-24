module com.org.gerenciamentocurso {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires static lombok;
    requires org.hibernate.orm.core;

    opens com.org.gerenciamentocurso.Controller to javafx.fxml;



    opens com.org.gerenciamentocurso.Model;
    opens com.org.gerenciamentocurso to javafx.fxml;
    exports com.org.gerenciamentocurso.Controller;
    exports com.org.gerenciamentocurso.DAO;
    exports com.org.gerenciamentocurso.Model;
    exports com.org.gerenciamentocurso;

}