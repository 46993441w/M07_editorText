package editor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editor.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Editor de Textos");
        primaryStage.setScene(new Scene(root, 600, 375));
        primaryStage.show();

        Controller controller = (Controller) loader.getController();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
