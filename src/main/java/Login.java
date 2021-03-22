/**
 * Sample Skeleton for 'login.fxml' Controller Class
 */

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for logging by customer activity
 */
public class Login {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="userTextField"
    private TextField userTextField; // Value injected by FXMLLoader

    @FXML // fx:id="passwordTextField"
    private PasswordField passwordTextField; // Value injected by FXMLLoader

    @FXML // fx:id="connectButton"
    private Button connectButton; // Value injected by FXMLLoader

    @FXML // fx:id="disconnectButton"
    private Button disconnectButton; // Value injected by FXMLLoader

    @FXML // fx:id="trackButton"
    private Button trackButton; // Value injected by FXMLLoader

    @FXML // fx:id="nadajButton"
    private Button nadajButton; // Value injected by FXMLLoader

    @FXML // fx:id="historyButton"
    private Button historyButton; // Value injected by FXMLLoader

    @FXML // fx:id="paczkomatyButton"
    private Button paczkomatyButton; // Value injected by FXMLLoader

    @FXML // fx:id="consoleTextArea"
    private TextArea consoleTextArea; // Value injected by FXMLLoader



    private DBUtil dbUtil;
    private PaczkaDAO PaczkaDAO;

    /**
     * Method that handles on click event for button connect.
     * It connects to database using DBUtil class object.
     * If connection is successful it enables buttons
     * giving access to more application features
     * @param event click on connect button
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void connectButtonPressed(ActionEvent event) throws SQLException, ClassNotFoundException{


        dbUtil = new DBUtil(userTextField.getText(), passwordTextField.getText(), consoleTextArea);
        PaczkaDAO = new PaczkaDAO(dbUtil, consoleTextArea);

        dbUtil.dbConnect();

        String rodzaj = dbUtil.getRodzaj();

        if(rodzaj.equals("admin") || rodzaj.equals("klient")){
        consoleTextArea.appendText("Access granted for user \"" + userTextField.getText() + "\"." + "\n");
        connectButton.setDisable(true);
        disconnectButton.setDisable(false);
        trackButton.setDisable(false);
        historyButton.setDisable(false);
        nadajButton.setDisable(false);
        paczkomatyButton.setDisable(false);
        }
        else
            consoleTextArea.appendText("Access denied for user \"" + userTextField.getText() + "\"." + "\n");
    }

    /**
     * Method that handles on click event for button disconnect.
     * It disconnects from database using DBUtil class object
     * and disables some buttons
     * @param event
     * @throws SQLException
     */
    @FXML
    void disconnectButtonPressed(ActionEvent event) throws SQLException {

        dbUtil.dbDisconnect();
        connectButton.setDisable(false);
        disconnectButton.setDisable(true);
        trackButton.setDisable(true);
        historyButton.setDisable(true);
        nadajButton.setDisable(true);
        paczkomatyButton.setDisable(true);

    }

    /**
     * Method that handles click on history button event.
     * It opens history activity from "historia_zamowien.fxml"
     * @param event click on history button
     * @throws IOException
     */
    @FXML
    void historyButtonPressed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("historia_zamowien.fxml"));
        Stage stage = new Stage();
        stage.initOwner(trackButton.getScene().getWindow());
        stage.setScene(new Scene(loader.load()));

        HistoriaController controller = loader.getController();
        controller.initData(userTextField.getText(), passwordTextField.getText());

        // showAndWait will block execution until the window closes...
        stage.showAndWait();
    }
    /**
     * Method that handles click on nadaj button event.
     * It opens posting a parcel activity from "nadaj_paczke.fxml"
     * @param event click on nadaj button
     * @throws IOException
     */
    @FXML
    void nadajButtonPressed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("nadaj_paczke.fxml"));
        Stage stage = new Stage();
        stage.initOwner(trackButton.getScene().getWindow());
        stage.setScene(new Scene(loader.load()));

        NadajController controller = loader.getController();
        controller.initData(userTextField.getText(), passwordTextField.getText());

        // showAndWait will block execution until the window closes...
        stage.showAndWait();
    }
    /**
     * Method that handles click on paczkomaty button event.
     * It opens paczkomaty activity from "automaty.fxml"
     * @param event click on paczkomaty button
     * @throws IOException
     */
    @FXML
    void paczkomatyButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("automaty.fxml"));
        Stage stage = new Stage();
        stage.initOwner(trackButton.getScene().getWindow());
        stage.setScene(new Scene(loader.load()));

        AutomatyController controller = loader.getController();
        controller.initData(userTextField.getText(), passwordTextField.getText());

        // showAndWait will block execution until the window closes...
        stage.showAndWait();

    }
    /**
     * Method that handles click on track button event.
     * It opens tracking activity from "trcking.fxml",
     * user can not only track a parcel but he can also mark it as sent or picked up
     * @param event click on track button
     * @throws IOException
     */
    @FXML
    void trackButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("tracking.fxml"));
        Stage stage = new Stage();
        stage.initOwner(trackButton.getScene().getWindow());
        stage.setScene(new Scene(loader.load()));

        TrackingController controller = loader.getController();
        controller.initData(userTextField.getText(), passwordTextField.getText());

        // showAndWait will block execution until the window closes...
        stage.showAndWait();
    }

    /**
     * This method is called by the FXMLLoader when initialization is complete
     */
    @FXML
    void initialize() {
        assert userTextField != null : "fx:id=\"userTextField\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'login.fxml'.";
        assert connectButton != null : "fx:id=\"connectButton\" was not injected: check your FXML file 'login.fxml'.";
        assert disconnectButton != null : "fx:id=\"disconnectButton\" was not injected: check your FXML file 'login.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'login.fxml'.";

    }
}
