import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class that runs the application
 */

public class RacketApp extends Application {

    private static Scene scene;


    /**
     * creates the stage
     * @param stage stage
     * @throws Exception exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("start"), 600, 400);
        stage.setScene(scene);
        stage.setTitle("Paczuszki");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RacketApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
