/**
 * Sample Skeleton for 'login_kurier.fxml' Controller Class
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
 * Controller Class handling kurier's login view. Contains methods called when user wants to login, logout, check
 *  information about customers, paczkomaty or wants to change the status of a parcel
 */
public class LoginKurier {

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

    @FXML // fx:id="statusButton"
    private Button statusButton; // Value injected by FXMLLoader

    @FXML // fx:id="klientButton"
    private Button klientButton; // Value injected by FXMLLoader

    @FXML // fx:id="paczkomatyButton"
    private Button paczkomatyButton; // Value injected by FXMLLoader

    @FXML // fx:id="consoleTextArea"
    private TextArea consoleTextArea; // Value injected by FXMLLoader

    private DBUtil dbUtil;
    private PaczkaDAO PaczkaDAO;

    /**
     * when pressed, after correct login and password it connects employee to his view. If password is wrong it does not let him in.
     * @param event event
     * @throws SQLException exception
     * @throws ClassNotFoundException exception
     */

    @FXML
    void connectButtonPressed(ActionEvent event) throws SQLException, ClassNotFoundException {
        dbUtil = new DBUtil(userTextField.getText(), passwordTextField.getText(), consoleTextArea);
        PaczkaDAO = new PaczkaDAO(dbUtil, consoleTextArea);

        dbUtil.dbConnect();
        String rodzaj = dbUtil.getRodzaj();

        if(rodzaj.equals("admin") || rodzaj.equals("kurier")){
        consoleTextArea.appendText("Access granted for user \"" + userTextField.getText() + "\"." + "\n");
        connectButton.setDisable(true);
        disconnectButton.setDisable(false);
        statusButton.setDisable(false);
        klientButton.setDisable(false);
        paczkomatyButton.setDisable(false);
        }
        else
            consoleTextArea.appendText("Access denied for user \"" + userTextField.getText() + "\"." + "\n");

    }

    /**
     * When pressed logs out current user
     * @param event event
     * @throws SQLException exception
     */

    @FXML
    void disconnectButtonPressed(ActionEvent event) throws SQLException {

        dbUtil.dbDisconnect();
        connectButton.setDisable(false);
        disconnectButton.setDisable(true);
        statusButton.setDisable(true);
        klientButton.setDisable(true);
        paczkomatyButton.setDisable(true);
    }

    /**
     * when pressed shows the view in which employee can search for information about customer
     * @param event
     * @throws IOException
     */

    @FXML
    void onKlientBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("pracownik/klienci.fxml"));
        Stage stage = new Stage();
        stage.initOwner(klientButton.getScene().getWindow());
        stage.setScene(new Scene(loader.load()));

        KlienciController controller = loader.getController();
        controller.initData(userTextField.getText(), passwordTextField.getText());

        // showAndWait will block execution until the window closes...
        stage.showAndWait();
    }

    /**
     * when pressed shows the view in which employee can change the status of the parcel
     * @param event
     * @throws SQLException sql excep
     * @throws ClassNotFoundException
     * @throws IOException input output exception
     */

    @FXML
    void onStatus(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pracownik/paczka.fxml"));
        Stage stage = new Stage();
        stage.initOwner(klientButton.getScene().getWindow());
        stage.setScene(new Scene(loader.load()));

        PaczkaStatusController controller = loader.getController();
        controller.initData(userTextField.getText(), passwordTextField.getText());

        // showAndWait will block execution until the window closes...
        stage.showAndWait();
    }
    /**
     * when pressed shows the view in which employee can search for info about paczkomaty
     * @param event event
     * @throws SQLException sql exception
     * @throws ClassNotFoundException
     * @throws IOException input output exception
     */
    @FXML
    void paczkomatyButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("automaty.fxml"));
        Stage stage = new Stage();
        stage.initOwner(paczkomatyButton.getScene().getWindow());
        stage.setScene(new Scene(loader.load()));

        AutomatyController controller = loader.getController();
        controller.initData(userTextField.getText(), passwordTextField.getText());

        // showAndWait will block execution until the window closes...
        stage.showAndWait();
    }

    /**
     * // This method is called by the FXMLLoader when initialization is complete
     */
    @FXML
    void initialize() {
        assert userTextField != null : "fx:id=\"userTextField\" was not injected: check your FXML file 'login_kurier.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'login_kurier.fxml'.";
        assert connectButton != null : "fx:id=\"connectButton\" was not injected: check your FXML file 'login_kurier.fxml'.";
        assert disconnectButton != null : "fx:id=\"disconnectButton\" was not injected: check your FXML file 'login_kurier.fxml'.";
        assert statusButton != null : "fx:id=\"statusButton\" was not injected: check your FXML file 'login_kurier.fxml'.";
        assert klientButton != null : "fx:id=\"klientButton\" was not injected: check your FXML file 'login_kurier.fxml'.";
        assert paczkomatyButton != null : "fx:id=\"paczkomatyButton\" was not injected: check your FXML file 'login_kurier.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'login_kurier.fxml'.";

    }
}
