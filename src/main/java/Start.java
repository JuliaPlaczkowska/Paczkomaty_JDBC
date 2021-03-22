import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller class that manages all buttons in the main menu.
 */

public class Start {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnGuest;

    @FXML // fx:id="btnLoginKurier"
    private Button btnLoginKurier; // Value injected by FXMLLoader

    @FXML // fx:id="btnLoginAdmin"
    private Button btnLoginAdmin; // Value injected by FXMLLoader

    /**
     * when this button is pressed it takes us to the guest's view where guest can post his package or take it from the stash
     * @param event
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void onGuest(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("tracking.fxml"));
        Stage stage = new Stage();
        stage.initOwner(btnGuest.getScene().getWindow());
        stage.setScene(new Scene(loader.load()));

        TrackingController controller = loader.getController();
        controller.initData("root", "1108Julka");

        // showAndWait will block execution until the window closes...
        stage.showAndWait();
    }

    /**
     * when button onLogin pressed it takes us to the signed customer view
     * @param event event
     * @throws IOException exception
     */

    @FXML
    void onLogin(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));

        Stage stage = new Stage();
        stage.initOwner(btnGuest.getScene().getWindow());
        stage.setScene(new Scene(loader.load()));

        // showAndWait will block execution until the window closes...
        stage.showAndWait();

    }
    /**
     * when button onLogin pressed it takes us to the admin view
     * @param event event
     * @throws IOException exception
     */
    @FXML
    void onLoginAdmin(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login_admin.fxml"));

        Stage stage = new Stage();
        stage.initOwner(btnGuest.getScene().getWindow());
        stage.setScene(new Scene(loader.load()));

        // showAndWait will block execution until the window closes...
        stage.showAndWait();
    }
    /**
     * when button onLogin pressed it takes us to the worker postman view
     * @param event event
     * @throws IOException exception
     */
    @FXML
    void onLoginKurier(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("pracownik/login_kurier.fxml"));

        Stage stage = new Stage();
        stage.initOwner(btnGuest.getScene().getWindow());
        stage.setScene(new Scene(loader.load()));

        // showAndWait will block execution until the window closes...
        stage.showAndWait();
    }

    /**
     * This method is called by the FXMLLoader when initialization is complete
     */
    @FXML
    void initialize() {
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'start.fxml'.";
        assert btnGuest != null : "fx:id=\"btnGuest\" was not injected: check your FXML file 'start.fxml'.";

    }
}
