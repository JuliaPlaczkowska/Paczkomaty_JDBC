/**
 * Sample Skeleton for 'automaty.fxml' Controller Class
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
 * Controller class for browsing Automaty table.
 */

public class AutomatyController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="idPaczkomatuTextField"
    private TextField idPaczkomatuTextField; // Value injected by FXMLLoader

    @FXML // fx:id="szukajButton"
    private Button szukajButton; // Value injected by FXMLLoader

    @FXML // fx:id="wrocButton"
    private Button wrocButton; // Value injected by FXMLLoader

    @FXML // fx:id="trackingTable"
    private TableView<AutomatWyszukaj> trackingTable; // Value injected by FXMLLoader

    @FXML // fx:id="idCol"
    private TableColumn<AutomatWyszukaj, String> idCol; // Value injected by FXMLLoader

    @FXML // fx:id="adresCol"
    private TableColumn<AutomatWyszukaj, String> adresCol; // Value injected by FXMLLoader

    @FXML // fx:id="liczbaWolnychSkrytekCol"
    private TableColumn<AutomatWyszukaj, String> liczbaWolnychSkrytekCol; // Value injected by FXMLLoader

    @FXML // fx:id="consoleTextArea"
    private TextArea consoleTextArea; // Value injected by FXMLLoader


    private DBUtil dbUtil;
    private PaczkaDAO paczkaDAO;

    /**
     * Method that handles on click event for button search
     * This method reads experessin from edit text field and
     * prints results in the table view using populateTracking method
     * @param event button search clicked
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void szukajButtonPressed(ActionEvent event) throws SQLException, ClassNotFoundException {
        this.dbUtil.dbConnect();
        paczkaDAO = new PaczkaDAO(dbUtil, consoleTextArea);

        try {

            if (!idPaczkomatuTextField.getText().equals(null)) {

                ObservableList<AutomatWyszukaj> paczkiData = paczkaDAO.automatyList(String.valueOf(idPaczkomatuTextField.getText()));
                populateTracking(paczkiData);
                consoleTextArea.appendText("Wyszukano automaty dla zapytania: " + idPaczkomatuTextField.getText() + "." + "\n");

            }
        } catch (SQLException e) {
            consoleTextArea.appendText("Wystąpił błąd.\n");
            throw e;
        }

    }

    /**
     * Prints given ObservableList in the table view
     * @param paczkiData
     */
    private void populateTracking(ObservableList<AutomatWyszukaj> paczkiData) {
        trackingTable.setItems(paczkiData);
    }

    /**
     * Method that handles on click event for button back.
     * It closes the window, so that user easily gets back to the previous activity
     * @param event button search clicked
     */
    @FXML
    void wrocButtonClicked(ActionEvent event) {
        // close this window...
        wrocButton.getScene().getWindow().hide();
    }

    /**
     * This method is called by the FXMLLoader when initialization is complete
     */
    @FXML
    void initialize() {
        assert idPaczkomatuTextField != null : "fx:id=\"idPaczkomatuTextField\" was not injected: check your FXML file 'automaty.fxml'.";
        idPaczkomatuTextField.setDisable(false);
        assert szukajButton != null : "fx:id=\"szukajButton\" was not injected: check your FXML file 'automaty.fxml'.";
        szukajButton.setDisable(false);
        assert wrocButton != null : "fx:id=\"wrocButton\" was not injected: check your FXML file 'automaty.fxml'.";
        assert trackingTable != null : "fx:id=\"trackingTable\" was not injected: check your FXML file 'automaty.fxml'.";
        assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'automaty.fxml'.";
        assert adresCol != null : "fx:id=\"adresCol\" was not injected: check your FXML file 'automaty.fxml'.";
        assert liczbaWolnychSkrytekCol != null : "fx:id=\"liczbaWolnychSkrytekCol\" was not injected: check your FXML file 'automaty.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'automaty.fxml'.";

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
    }
}
