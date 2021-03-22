/**
 * Sample Skeleton for 'uzytkownicy.fxml' Controller Class
 */

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controller class of the view responsible for managing admin's function of adding and removing users
 */

public class Uzytkownicy {

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
    private TableView<Uzytkownik> uzytkownicyTable; // Value injected by FXMLLoader

    @FXML // fx:id="idCol"
    private TableColumn<?, ?> idCol; // Value injected by FXMLLoader

    @FXML // fx:id="loginCol"
    private TableColumn<?, ?> loginCol; // Value injected by FXMLLoader

    @FXML // fx:id="hasloCol"
    private TableColumn<?, ?> hasloCol; // Value injected by FXMLLoader

    @FXML // fx:id="rodzajCol"
    private TableColumn<?, ?> rodzajCol; // Value injected by FXMLLoader

    @FXML // fx:id="login"
    private TextField login; // Value injected by FXMLLoader

    @FXML // fx:id="haslo"
    private TextField haslo; // Value injected by FXMLLoader

    @FXML // fx:id="rodzaj"
    private TextField rodzaj; // Value injected by FXMLLoader

    @FXML // fx:id="wrocButton"
    private Button wrocButton; // Value injected by FXMLLoader

    @FXML // fx:id="consoleTextArea"
    private TextArea consoleTextArea; // Value injected by FXMLLoader

    private DBUtil dbUtil;
    private PaczkaDAO paczkaDAO;

    @FXML
    void onDodaj(ActionEvent event) throws SQLException, ClassNotFoundException {
        paczkaDAO.dodajUzytkownika(login.getText(), haslo.getText(), rodzaj.getText());
    }

    @FXML
    void onUsun(ActionEvent event) throws SQLException, ClassNotFoundException {
        paczkaDAO.usunUzytkownika(Integer.parseInt(wyszukaj.getText()));

    }

    @FXML
    void onWroc(ActionEvent event) {
        // close this window...
        wrocButton.getScene().getWindow().hide();
    }

    @FXML
    void onWyswietl(ActionEvent event) throws SQLException, ClassNotFoundException {
        populateUzytkownicy(paczkaDAO.uzytkownicyList(0));
    }

    @FXML
    void onWyszukaj(ActionEvent event) throws SQLException, ClassNotFoundException {
        populateUzytkownicy(paczkaDAO.uzytkownicyList(Integer.parseInt(wyszukaj.getText())));
    }

    /**
     * prints results in table view
     * @param paczkiData database of created packages
     */
    private void populateUzytkownicy(ObservableList<Uzytkownik> paczkiData) {
        uzytkownicyTable.setItems(paczkiData);
    }

    /**
     * This method is called by the FXMLLoader when initialization is complete
     */
    @FXML
    void initialize() {
        assert wyswietlButton != null : "fx:id=\"wyswietlButton\" was not injected: check your FXML file 'uzytkownicy.fxml'.";
        assert wyszukajButton != null : "fx:id=\"wyszukajButton\" was not injected: check your FXML file 'uzytkownicy.fxml'.";
        assert wyszukaj != null : "fx:id=\"wyszukaj\" was not injected: check your FXML file 'uzytkownicy.fxml'.";
        assert usunButton != null : "fx:id=\"usunButton\" was not injected: check your FXML file 'uzytkownicy.fxml'.";
        assert dodajButton != null : "fx:id=\"dodajButton\" was not injected: check your FXML file 'uzytkownicy.fxml'.";
        assert uzytkownicyTable != null : "fx:id=\"uzytkownicyTable\" was not injected: check your FXML file 'uzytkownicy.fxml'.";
        assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'uzytkownicy.fxml'.";
        assert loginCol != null : "fx:id=\"loginCol\" was not injected: check your FXML file 'uzytkownicy.fxml'.";
        assert hasloCol != null : "fx:id=\"hasloCol\" was not injected: check your FXML file 'uzytkownicy.fxml'.";
        assert rodzajCol != null : "fx:id=\"rodzajCol\" was not injected: check your FXML file 'uzytkownicy.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'uzytkownicy.fxml'.";
        assert haslo != null : "fx:id=\"haslo\" was not injected: check your FXML file 'uzytkownicy.fxml'.";
        assert rodzaj != null : "fx:id=\"rodzaj\" was not injected: check your FXML file 'uzytkownicy.fxml'.";
        assert wrocButton != null : "fx:id=\"wrocButton\" was not injected: check your FXML file 'uzytkownicy.fxml'.";

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
