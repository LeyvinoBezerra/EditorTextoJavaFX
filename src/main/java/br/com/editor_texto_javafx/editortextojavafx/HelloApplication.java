package br.com.editor_texto_javafx.editortextojavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        BorderPane root = (BorderPane)FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/resources/br/com/editor_texto_javafx/editortextojavafx.hello-view.fxml")));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}