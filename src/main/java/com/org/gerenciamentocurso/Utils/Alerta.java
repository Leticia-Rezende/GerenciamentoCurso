package com.org.gerenciamentocurso.Utils;

import javafx.scene.control.Alert;

public class Alerta {
    public static void exibirAlerta(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
