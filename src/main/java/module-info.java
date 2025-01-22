module br.com.editor_texto_javafx.editortextojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens br.com.editor_texto_javafx.editortextojavafx to javafx.fxml;
    exports br.com.editor_texto_javafx.editortextojavafx;
}