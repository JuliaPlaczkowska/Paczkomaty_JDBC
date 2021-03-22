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
 * Controller class responsible for tracking view. It contains all the information about package and methods used for
 * actions like changing parcel's status or just browsing the parcel data.
 */
public class TrackingController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="idPaczkiTextField"
    private TextField idPaczkiTextField; // Value injected by FXMLLoader

    @FXML // fx:id="szukajButton"
    private Button szukajButton; // Value injected by FXMLLoader

    @FXML // fx:id="wrocButton"
    private Button wrocButton; // Value injected by FXMLLoader

    @FXML
    private TextField idPaczkomatuTextField;

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
     * When button responsible for changing status named like NADAJ  is clicked then the status of the [arcel changes in database.
     * This method is for customer useage
     * @param event click
     * @throws ClassNotFoundException exception
     */

    @FXML
    void onStatus(ActionEvent event) throws ClassNotFoundException {
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
     * When button responsible for changing status named like ODBIERZ is clicked then the status of the [arcel changes in database
     * This method is for CUSTOMER usage.
     * @param event click
     * @throws ClassNotFoundException exception
     */

    @FXML
    void onStatus2(ActionEvent event) throws ClassNotFoundException {
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
     * when button named SZUKAJ is pressed then parcel's details are displayed in the table view below
     * @param event click
     * @throws SQLException sql exception
     * @throws ClassNotFoundException exception
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

    /**
     * displays ObservableList in table view
     * @param paczkiData paczki Data
     */
    private void populateTracking(ObservableList<Tracking> paczkiData) {
        trackingTable.setItems(paczkiData);
    }

    /**
     * when button WROC pressed it gets us back to the previous view
     * @param event event
     */
    @FXML
    void wrocButtonClicked(ActionEvent event) {
        // close this window...
        wrocButton.getScene().getWindow().hide();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws SQLException, ClassNotFoundException {
        assert idPaczkiTextField != null : "fx:id=\"idPaczkiTextField\" was not injected: check your FXML file 'tracking.fxml'.";
        idPaczkiTextField.setDisable(false);
        assert szukajButton != null : "fx:id=\"szukajButton\" was not injected: check your FXML file 'tracking.fxml'.";
        szukajButton.setDisable(false);
        assert wrocButton != null : "fx:id=\"wrocButton\" was not injected: check your FXML file 'tracking.fxml'.";
        assert trackingTable != null : "fx:id=\"trackingTable\" was not injected: check your FXML file 'tracking.fxml'.";
        assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'tracking.fxml'.";
        assert dataUtworzeniaCol != null : "fx:id=\"dataUtworzeniaCol\" was not injected: check your FXML file 'tracking.fxml'.";
        assert dataNadaniaCol != null : "fx:id=\"dataNadaniaCol\" was not injected: check your FXML file 'tracking.fxml'.";
        assert dataOdbioruCol != null : "fx:id=\"dataOdbioruCol\" was not injected: check your FXML file 'tracking.fxml'.";
        assert statusCol != null : "fx:id=\"statusCol\" was not injected: check your FXML file 'tracking.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'tracking.fxml'.";




    }
    /**
     * method used to bring the login and password from previous activity
     * @param login login
     * @param haslo password
     */
    void initData(String login, String haslo) throws SQLException, ClassNotFoundException {
        this.dbUtil = new DBUtil(login, haslo, consoleTextArea);

        this.dbUtil.dbConnect();
        paczkaDAO = new PaczkaDAO(dbUtil, consoleTextArea);

    }
}
