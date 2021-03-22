/**
 * Sample Skeleton for 'klienci.fxml' Controller Class
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

public class KlienciController {

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


    @FXML // fx:id="uzytkownicyTable"
    private TableView<Klient> uzytkownicyTable; // Value injected by FXMLLoader

    @FXML // fx:id="idCol"
    private TableColumn<?, ?> idCol; // Value injected by FXMLLoader

    @FXML // fx:id="imieCol"
    private TableColumn<?, ?> imieCol; // Value injected by FXMLLoader

    @FXML // fx:id="nazwiskoCol"
    private TableColumn<?, ?> nazwiskoCol; // Value injected by FXMLLoader

    @FXML // fx:id="emailCol"
    private TableColumn<?, ?> emailCol; // Value injected by FXMLLoader

    @FXML // fx:id="nr_telCol1"
    private TableColumn<?, ?> nr_telCol1; // Value injected by FXMLLoader

    @FXML // fx:id="wrocButton"
    private Button wrocButton; // Value injected by FXMLLoader

    @FXML // fx:id="consoleTextArea"
    private TextArea consoleTextArea; // Value injected by FXMLLoader

    private DBUtil dbUtil;
    private PaczkaDAO paczkaDAO;


    @FXML
    void onWroc(ActionEvent event) {

        // close this window...
        wrocButton.getScene().getWindow().hide();
    }

    @FXML
    void onWyswietl(ActionEvent event) throws SQLException, ClassNotFoundException {
        populateUzytkownicy(paczkaDAO.klienciList(0));
    }


    @FXML
    void onWyszukaj(ActionEvent event) throws SQLException, ClassNotFoundException {
        populateUzytkownicy(paczkaDAO.klienciList(Integer.parseInt(wyszukaj.getText())));
    }

    private void populateUzytkownicy(ObservableList<Klient> paczkiData) {
        uzytkownicyTable.setItems(paczkiData);
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert wyswietlButton != null : "fx:id=\"wyswietlButton\" was not injected: check your FXML file 'klienci.fxml'.";
        assert wyszukajButton != null : "fx:id=\"wyszukajButton\" was not injected: check your FXML file 'klienci.fxml'.";
        assert wyszukaj != null : "fx:id=\"wyszukaj\" was not injected: check your FXML file 'klienci.fxml'.";
        assert uzytkownicyTable != null : "fx:id=\"uzytkownicyTable\" was not injected: check your FXML file 'klienci.fxml'.";
        assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'klienci.fxml'.";
        assert imieCol != null : "fx:id=\"imieCol\" was not injected: check your FXML file 'klienci.fxml'.";
        assert nazwiskoCol != null : "fx:id=\"nazwiskoCol\" was not injected: check your FXML file 'klienci.fxml'.";
        assert emailCol != null : "fx:id=\"emailCol\" was not injected: check your FXML file 'klienci.fxml'.";
        assert nr_telCol1 != null : "fx:id=\"nr_telCol1\" was not injected: check your FXML file 'klienci.fxml'.";
        assert wrocButton != null : "fx:id=\"wrocButton\" was not injected: check your FXML file 'klienci.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'klienci.fxml'.";

    }
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
