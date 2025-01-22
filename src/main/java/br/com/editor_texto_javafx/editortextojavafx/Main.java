package br.com.editor_texto_javafx.editortextojavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader root = new FXMLLoader((getClass().getResource("EditorTexto.fxml")));
            Scene scene = new Scene(root.load());
            Controller controller = root.getController();
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}