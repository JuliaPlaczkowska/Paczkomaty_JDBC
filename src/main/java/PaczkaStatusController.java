/**
 * Sample Skeleton for 'tracking.fxml' Controller Class
 */

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * controller Class that handles the kurier's view in which an employee can withdraw or put in the package int the stash
 * and customer view when
 */
public class PaczkaStatusController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="idPaczkiTextField"
    private TextField idPaczkiTextField; // Value injected by FXMLLoader

    @FXML
    private Button wDostawieButton;

    @FXML
    private Button dostarczonaButton;

    @FXML
    private TextField idPaczkomatuTextField;

    @FXML // fx:id="szukajButton"
    private Button szukajButton; // Value injected by FXMLLoader

    @FXML // fx:id="wrocButton"
    private Button wrocButton; // Value injected by FXMLLoader

    @FXML // fx:id="trackingTable"
    private TableView<Tracking> trackingTable; // Value injected by FXMLLoader

    @FXML // fx:id="idCol"
    private TableColumn<Tracking, String> idCol; // Value injected by FXMLLoader

    @FXML // fx:id="dataUtworzeniaCol"
    private TableColumn<Tracking, String> dataUtworzeniaCol; // Value injected by FXMLLoader

    @FXML // fx:id="dataNadaniaCol"
    private TableColumn<Tracking, String> dataNadaniaCol; // Value injected by FXMLLoader

    @FXML // fx:id="dataOdbioruCol"
    private TableColumn<Tracking, String> dataOdbioruCol; // Value injected by FXMLLoader

    @FXML // fx:id="statusCol"
    private TableColumn<Tracking, String> statusCol; // Value injected by FXMLLoader

    @FXML // fx:id="consoleTextArea"
    private TextArea consoleTextArea; // Value injected by FXMLLoader

    private DBUtil dbUtil;
    private PaczkaDAO paczkaDAO;

    /**
     * when button named W DOSTAWIE  clicked it shows updated status of the parcel in console bel
     * @param event event
     * @throws ClassNotFoundException exception
     */

    @FXML
    void onStatus(ActionEvent event) throws ClassNotFoundException {
        paczkaDAO.zmienStatusPrzesylki(Integer.parseInt(idPaczkiTextField.getText()), false, Integer.parseInt(idPaczkomatuTextField.getText()));
        try {

            if (!idPaczkiTextField.getText().equals(null)) {

                ObservableList<Tracking> paczkiData = paczkaDAO.sledzPaczke(Integer.valueOf(idPaczkiTextField.getText()));
                populateTracking(paczkiData);
                consoleTextArea.appendText("Zaktualizowano status dla paczki o numerze id: " + idPaczkiTextField.getText() + "." + "\n");

            }
        } catch (SQLException e) {
            consoleTextArea.appendText("Wystąpił błąd.\n");
        }
    }
    /**
     * when button named DOSTARCZONO  clicked it shows updated status of the parcel in console bel
     * @param event event
     * @throws ClassNotFoundException exception
     */

    @FXML
    void onStatus2(ActionEvent event) throws ClassNotFoundException {
        paczkaDAO.zmienStatusPrzesylki(Integer.parseInt(idPaczkiTextField.getText()), true, Integer.parseInt(idPaczkomatuTextField.getText()));
        try {

            if (!idPaczkiTextField.getText().equals(null)) {

                ObservableList<Tracking> paczkiData = paczkaDAO.sledzPaczke(Integer.valueOf(idPaczkiTextField.getText()));
                populateTracking(paczkiData);
                consoleTextArea.appendText("Zaktualizowano status dla paczki o numerze id: " + idPaczkiTextField.getText() + "." + "\n");

            }
        } catch (SQLException e) {
            consoleTextArea.appendText("Wystąpił błąd.\n");
        }
    }

    /**
     * When pressed, shows the parcel's details in table view
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void szukajButtonPressed(ActionEvent event) throws SQLException, ClassNotFoundException {


        try {

            if (!idPaczkiTextField.getText().equals(null)) {

                ObservableList<Tracking> paczkiData = paczkaDAO.sledzPaczke(Integer.valueOf(idPaczkiTextField.getText()));
                populateTracking(paczkiData);
                consoleTextArea.appendText("Wyświetlono informacje trackingowe dla paczki o numerze id: " + idPaczkiTextField.getText() + "." + "\n");

            }
        } catch (SQLException e) {
            consoleTextArea.appendText("Wystąpił błąd.\n");
            throw e;
        }

    }

    private void populateTracking(ObservableList<Tracking> paczkiData) {

        trackingTable.setItems(paczkiData);
    }

    /**
     * when clicked take us back to the previous view
     * @param event event
     */
    @FXML
    void wrocButtonClicked(ActionEvent event) {
        // close this window...
        wrocButton.getScene().getWindow().hide();
    }

    /**
     * // This method is called by the FXMLLoader when initialization is complete
     * @throws SQLException exception
     * @throws ClassNotFoundException exception
     */
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert idPaczkiTextField != null : "fx:id=\"idPaczkiTextField\" was not injected: check your FXML file 'pracownik/paczka.fxml'.";
        idPaczkiTextField.setDisable(false);
        assert szukajButton != null : "fx:id=\"szukajButton\" was not injected: check your FXML file 'pracownik/paczka.fxml'.";
        szukajButton.setDisable(false);
        assert wrocButton != null : "fx:id=\"wrocButton\" was not injected: check your FXML file 'pracownik/paczka.fxml'.";
        assert trackingTable != null : "fx:id=\"trackingTable\" was not injected: check your FXML file 'pracownik/paczka.fxml'.";
        assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'pracownik/paczka.fxml'.";
        assert dataUtworzeniaCol != null : "fx:id=\"dataUtworzeniaCol\" was not injected: check your FXML file 'pracownik/paczka.fxml'.";
        assert dataNadaniaCol != null : "fx:id=\"dataNadaniaCol\" was not injected: check your FXML file 'pracownik/paczka.fxml'.";
        assert dataOdbioruCol != null : "fx:id=\"dataOdbioruCol\" was not injected: check your FXML file 'pracownik/paczka.fxml'.";
        assert statusCol != null : "fx:id=\"statusCol\" was not injected: check your FXML file 'pracownik/paczka.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'pracownik/paczka.fxml'.";


/**
 * method used to bring the login and password from previous activity
 * @param login login
 * @param haslo password
 */

    }

    void initData(String login, String haslo) throws SQLException, ClassNotFoundException {
        this.dbUtil = new DBUtil(login, haslo, consoleTextArea);

        this.dbUtil.dbConnect();
        paczkaDAO = new PaczkaDAO(dbUtil, consoleTextArea);

    }
}
