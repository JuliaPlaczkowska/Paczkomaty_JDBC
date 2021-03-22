

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controller class for browsing user's history
 */
public class HistoriaController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tvNumerPrzesylki"
    private TextField tvNumerPrzesylki; // Value injected by FXMLLoader

    @FXML // fx:id="szukajButton"
    private Button szukajButton; // Value injected by FXMLLoader

    @FXML // fx:id="wrocButton"
    private Button wrocButton; // Value injected by FXMLLoader

    @FXML // fx:id="consoleTextArea"
    private TextArea consoleTextArea; // Value injected by FXMLLoader

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

    private DBUtil dbUtil;
    private PaczkaDAO paczkaDAO;

    /**
     * Method that handles on click event for button search
     * This method reads expression from edit text field and
     * prints results in the table view using populateTracking method
     * @param event button search
     */
    @FXML
    void onSzukaj(ActionEvent event) {


        try {

            if (!tvNumerPrzesylki.getText().equals(null)) {

                ObservableList<Tracking> paczkiData = paczkaDAO.sledzPaczke(Integer.valueOf(tvNumerPrzesylki.getText()));
                populateTracking(paczkiData);
                consoleTextArea.appendText("Wyświetlono informacje trackingowe dla paczki o numerze id: " + tvNumerPrzesylki.getText() + "." + "\n");

            }
        } catch (SQLException e) {
            consoleTextArea.appendText("Wystąpił błąd.\n");
            try {
                throw e;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints given ObservableList in the table view
     * @param paczkiData observable list
     */
    private void populateTracking(ObservableList<Tracking> paczkiData) {
        trackingTable.setItems(paczkiData);
    }

    /**
     * This method shows full user history
     */
    private void wyswietlHistorie(){

        dbUtil.getuserName();
        try {

            if (!tvNumerPrzesylki.getText().equals(null)) {

                ObservableList<Tracking> paczkiData = paczkaDAO.historiaPaczek(dbUtil.getuserName());
                populateTracking(paczkiData);
                consoleTextArea.appendText("Wyświetlono historie dla klienta o nazwie: " + dbUtil.getuserName() + "." + "\n");

            }
        } catch (SQLException e) {
            consoleTextArea.appendText("Wystąpił błąd.\n");
            try {
                throw e;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that handles on click event for button back.
     * It closes the window, so that user easily gets back to the previous activity
     * @param event button search clicked
     */
    @FXML
    void onWroc(ActionEvent event) {
        wrocButton.getScene().getWindow().hide();
    }
    /**
     * This method is called by the FXMLLoader when initialization is complete
     */
    @FXML
    void initialize() {
        assert tvNumerPrzesylki != null : "fx:id=\"tvNumerPrzesylki\" was not injected: check your FXML file 'historia_zamowien.fxml'.";
        assert szukajButton != null : "fx:id=\"szukajButton\" was not injected: check your FXML file 'historia_zamowien.fxml'.";
        assert wrocButton != null : "fx:id=\"wrocButton\" was not injected: check your FXML file 'historia_zamowien.fxml'.";


    }
    /**
     * method used to bring the login and password from previous activity
     * @param login login
     * @param haslo password
     */
    void initData(String login, String haslo){
        this.dbUtil = new DBUtil(login, haslo, consoleTextArea);
        try {
            this.dbUtil.dbConnect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        paczkaDAO = new PaczkaDAO(dbUtil, consoleTextArea);

        wyswietlHistorie();
    }
}
