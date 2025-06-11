package com.org.gerenciamentocurso.Utils;

import java.nio.file.Paths;

public class PathFXML {
    public static String pathBase() {
        return Paths.get("src/main/java/com/org/gerenciamentocurso/View").toAbsolutePath().toString();
    }
}
