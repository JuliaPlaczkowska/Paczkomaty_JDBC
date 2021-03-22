/**
 * Sample Skeleton for 'login_admin.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for logging by admin activity
 */
public class LoginAdmin {

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

    @FXML // fx:id="uzytkownicyButton"
    private Button uzytkownicyButton; // Value injected by FXMLLoader

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
    void connectButtonPressed(ActionEvent event) throws SQLException, ClassNotFoundException {

        dbUtil = new DBUtil(userTextField.getText(), passwordTextField.getText(), consoleTextArea);
        PaczkaDAO = new PaczkaDAO(dbUtil, consoleTextArea);

        dbUtil.dbConnect();

        String rodzaj = dbUtil.getRodzaj();

        if(rodzaj.equals("admin")){
        consoleTextArea.appendText("Access granted for user \"" + userTextField.getText() + "\"." + "\n");
        connectButton.setDisable(true);
        disconnectButton.setDisable(false);
        uzytkownicyButton.setDisable(false);
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
        uzytkownicyButton.setDisable(true);
        paczkomatyButton.setDisable(true);
    }

    /**
     * Method that handles click on paczkomaty button event.
     * It opens paczkomaty activity from "admin/paczkomaty.fxml"
     * @param event click on paczkomaty button
     * @throws IOException
     */
    @FXML
    void paczkomatyButtonPressed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin/paczkomaty.fxml"));
        Stage stage = new Stage();
        stage.initOwner(paczkomatyButton.getScene().getWindow());
        stage.setScene(new Scene(loader.load()));

        Paczkomaty controller = loader.getController();
        controller.initData(userTextField.getText(), passwordTextField.getText());

        // showAndWait will block execution until the window closes...
        stage.showAndWait();
    }
    /**
     * Method that handles click on users button event.
     * It opens users browser activity from "admin/uzytkownicy.fxml"
     * @param event click on uzytkownicy button
     * @throws IOException
     */
    @FXML
    void uzytkownicyButtonPressed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin/uzytkownicy.fxml"));
        Stage stage = new Stage();
        stage.initOwner(uzytkownicyButton.getScene().getWindow());
        stage.setScene(new Scene(loader.load()));

        Uzytkownicy controller = loader.getController();
        controller.initData(userTextField.getText(), passwordTextField.getText());

        // showAndWait will block execution until the window closes...
        stage.showAndWait();

    }

    /**
     * This method is called by the FXMLLoader when initialization is complete
     */
    @FXML
    void initialize() {
        assert userTextField != null : "fx:id=\"userTextField\" was not injected: check your FXML file 'login_admin.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'login_admin.fxml'.";
        assert connectButton != null : "fx:id=\"connectButton\" was not injected: check your FXML file 'login_admin.fxml'.";
        assert disconnectButton != null : "fx:id=\"disconnectButton\" was not injected: check your FXML file 'login_admin.fxml'.";
        assert uzytkownicyButton != null : "fx:id=\"uzytkownicyButton\" was not injected: check your FXML file 'login_admin.fxml'.";
        assert paczkomatyButton != null : "fx:id=\"paczkomatyButton\" was not injected: check your FXML file 'login_admin.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'login_admin.fxml'.";

    }
}
