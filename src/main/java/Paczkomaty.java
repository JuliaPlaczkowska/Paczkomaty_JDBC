/**
 * Sample Skeleton for 'paczkomaty.fxml' Controller Class
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
 * Controller Clas of an admin's view of Paczkomat that contains methods for adding and deleting paczkomat
 */
public class Paczkomaty {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="wyswietlButton"
    private Button wyswietlButton; // Value injected by FXMLLoader

    @FXML // fx:id="wyszukajButton"
    private Button wyszukajButton; // Value injected by FXMLLoader

    @FXML // fx:id="wyszukaj"
    private TextField wyszukaj; // Value injected by FXMLLoader

    @FXML // fx:id="usunButton"
    private Button usunButton; // Value injected by FXMLLoader

    @FXML // fx:id="dodajButton"
    private Button dodajButton; // Value injected by FXMLLoader

    @FXML // fx:id="uzytkownicyTable"
    private TableView<AutomatWyszukaj> uzytkownicyTable; // Value injected by FXMLLoader

    @FXML // fx:id="idCol"
    private TableColumn<?, ?> idCol; // Value injected by FXMLLoader

    @FXML // fx:id="adresCol"
    private TableColumn<?, ?> adresCol; // Value injected by FXMLLoader


    @FXML // fx:id="liczbaWolnychSkrytekCol"
    private TableColumn<?, ?> liczbaWolnychSkrytekCol; // Value injected by FXMLLoader

    @FXML // fx:id="adres"
    private TextField adres; // Value injected by FXMLLoader

    @FXML // fx:id="liczbaSkrytek"
    private TextField liczbaSkrytek; // Value injected by FXMLLoader

    @FXML // fx:id="wrocButton"
    private Button wrocButton; // Value injected by FXMLLoader

    @FXML // fx:id="controllerTextArea"
    private TextArea consoleTextArea; // Value injected by FXMLLoader

    private DBUtil dbUtil;
    private PaczkaDAO paczkaDAO;

    /**
     * adding new paczkomat
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void onDodaj(ActionEvent event) throws SQLException, ClassNotFoundException {
        paczkaDAO.dodajAutomaty(adres.getText(), Integer.valueOf(liczbaSkrytek.getText()));
    }

    /**
     * removing paczkomat
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void onUsun(ActionEvent event) throws SQLException, ClassNotFoundException {
        paczkaDAO.usunAutomat(Integer.parseInt(wyszukaj.getText()));
    }

    /**
     * back button
     * @param event
     */
    @FXML
    void onWroc(ActionEvent event) {
        // close this window...
        wrocButton.getScene().getWindow().hide();

    }

    /**
     * when clicked, displays all the paczkomaty in table view below
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void onWyswietl(ActionEvent event) throws SQLException, ClassNotFoundException {

        this.dbUtil.dbConnect();
        paczkaDAO = new PaczkaDAO(dbUtil, consoleTextArea);


        try {

            if (!wyszukaj.getText().equals(null)) {

                ObservableList<AutomatWyszukaj> paczkiData = paczkaDAO.automatyList("");
                populateTracking(paczkiData);
                consoleTextArea.appendText("Wyszukano automaty dla zapytania: " + wyszukaj.getText() + "." + "\n");

            }
        } catch (SQLException e) {
            consoleTextArea.appendText("Wystąpił błąd.\n");
            throw e;
        }

    }

    /**
     * it's role is to search the paczkomat of a specific ID
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void onWyszukaj(ActionEvent event) throws SQLException, ClassNotFoundException {

        this.dbUtil.dbConnect();
        paczkaDAO = new PaczkaDAO(dbUtil, consoleTextArea);


        try {

            if (!wyszukaj.getText().equals(null)) {

                ObservableList<AutomatWyszukaj> paczkiData = paczkaDAO.automatyListID(Integer.valueOf(wyszukaj.getText()));
                populateTracking(paczkiData);
                consoleTextArea.appendText("Wyszukano automaty dla zapytania: " + wyszukaj.getText() + "." + "\n");

            }
        } catch (SQLException e) {
            consoleTextArea.appendText("Wystąpił błąd.\n");
            throw e;
        }

    }

    private void populateTracking(ObservableList<AutomatWyszukaj> paczkiData) {
        uzytkownicyTable.setItems(paczkiData);
    }

    /**
     * This method is called by the FXMLLoader when initialization is complete
     */
    @FXML
    void initialize() {
        assert wyswietlButton != null : "fx:id=\"wyswietlButton\" was not injected: check your FXML file 'paczkomaty.fxml'.";
        assert wyszukajButton != null : "fx:id=\"wyszukajButton\" was not injected: check your FXML file 'paczkomaty.fxml'.";
        assert wyszukaj != null : "fx:id=\"wyszukaj\" was not injected: check your FXML file 'paczkomaty.fxml'.";
        assert usunButton != null : "fx:id=\"usunButton\" was not injected: check your FXML file 'paczkomaty.fxml'.";
        assert dodajButton != null : "fx:id=\"dodajButton\" was not injected: check your FXML file 'paczkomaty.fxml'.";
        assert uzytkownicyTable != null : "fx:id=\"uzytkownicyTable\" was not injected: check your FXML file 'paczkomaty.fxml'.";
        assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'paczkomaty.fxml'.";
        assert adresCol != null : "fx:id=\"adresCol\" was not injected: check your FXML file 'paczkomaty.fxml'.";
        assert liczbaWolnychSkrytekCol != null : "fx:id=\"liczbaWolnychSkrytekCol\" was not injected: check your FXML file 'paczkomaty.fxml'.";
        assert adres != null : "fx:id=\"adres\" was not injected: check your FXML file 'paczkomaty.fxml'.";
        assert liczbaSkrytek != null : "fx:id=\"liczbaSkrytek\" was not injected: check your FXML file 'paczkomaty.fxml'.";
        assert wrocButton != null : "fx:id=\"wrocButton\" was not injected: check your FXML file 'paczkomaty.fxml'.";
        assert consoleTextArea != null : "fx:id=\"controllerTextArea\" was not injected: check your FXML file 'paczkomaty.fxml'.";

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
