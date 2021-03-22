/**
 * Sample Skeleton for 'nadaj_paczke.fxml' Controller Class
 */

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Controller class responsible for handling blanket creating view wher customer adresses his parcel.
 */
public class NadajController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="nadImie"
    private TextField nadImie; // Value injected by FXMLLoader

    @FXML // fx:id="nadTel"
    private TextField nadTel; // Value injected by FXMLLoader

    @FXML // fx:id="nadEmail"
    private TextField nadEmail; // Value injected by FXMLLoader

    @FXML // fx:id="nadNazwisko"
    private TextField nadNazwisko; // Value injected by FXMLLoader

    @FXML // fx:id="nadPaczkomat"
    private TextField nadPaczkomat; // Value injected by FXMLLoader

    @FXML // fx:id="ubezpieczenie"
    private TextField ubezpieczenie; // Value injected by FXMLLoader

    @FXML // fx:id="gabaryt"
    private TextField gabaryt; // Value injected by FXMLLoader

    @FXML // fx:id="rodzaj"
    private TextField rodzaj; // Value injected by FXMLLoader

    @FXML // fx:id="odbImie"
    private TextField odbImie; // Value injected by FXMLLoader

    @FXML // fx:id="odbTel"
    private TextField odbTel; // Value injected by FXMLLoader

    @FXML // fx:id="odbEmail"
    private TextField odbEmail; // Value injected by FXMLLoader

    @FXML // fx:id="odbNazwisko"
    private TextField odbNazwisko; // Value injected by FXMLLoader

    @FXML // fx:id="odbPaczkomat"
    private TextField odbPaczkomat; // Value injected by FXMLLoader

    @FXML // fx:id="wrocButton"
    private Button wrocButton; // Value injected by FXMLLoader

    @FXML // fx:id="obliczCeneButton"
    private Button obliczCeneButton; // Value injected by FXMLLoader

    @FXML // fx:id="consoleTextArea"
    private TextArea consoleTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="zaplacButton"
    private Button zaplacButton; // Value injected by FXMLLoader

    @FXML // fx:id="cenaTV"
    private Text cenaTV; // Value injected by FXMLLoader


    /**
     * fields
     * dbUtil - for connecting to database
     * paczkaDAO - object of PaczkaDAO class
     * cena - price calculated by mysql function
     */
    private DBUtil dbUtil;
    private PaczkaDAO paczkaDAO;
    private double cena;

    /**
     * method which calculates the price based on size, insurance and the type of the package
     * @param event event
     * @throws SQLException sql exception
     * @throws ClassNotFoundException class not found expetnion
     */
    @FXML
    void onObliczCene(ActionEvent event) throws SQLException, ClassNotFoundException {

        cena = paczkaDAO.nadajPaczke(dbUtil.getuserName(), odbImie.getText(), odbNazwisko.getText(), odbEmail.getText(),
                Integer.valueOf(odbTel.getText()), Integer.valueOf(odbPaczkomat.getText()),
                gabaryt.getText(), rodzaj.getText(), 1.0, Double.valueOf(ubezpieczenie.getText()));

        cenaTV.setText("Cena: "+cena);
    }

    /**
     * when clicked it takes us back to the previous view
     * @param event event
     */
    @FXML
    void onWroc(ActionEvent event) {
        // close this window...
        wrocButton.getScene().getWindow().hide();
    }

    /**
     * when pressed displays the price we paid for the service in the console below
     * @param event
     */
    @FXML
    void onZaplac(ActionEvent event) {
        consoleTextArea.appendText("Zapłacono "+cena+"!");

    }

    /**
     * // This method is called by the FXMLLoader when initialization is complete
     */
    @FXML
    void initialize() {
        assert nadImie != null : "fx:id=\"nadImie\" was not injected: check your FXML file 'nadaj_paczke.fxml'.";
        assert nadTel != null : "fx:id=\"nadTel\" was not injected: check your FXML file 'nadaj_paczke.fxml'.";
        assert nadEmail != null : "fx:id=\"nadEmail\" was not injected: check your FXML file 'nadaj_paczke.fxml'.";
        assert nadNazwisko != null : "fx:id=\"nadNazwisko\" was not injected: check your FXML file 'nadaj_paczke.fxml'.";
        assert nadPaczkomat != null : "fx:id=\"nadPaczkomat\" was not injected: check your FXML file 'nadaj_paczke.fxml'.";
        assert ubezpieczenie != null : "fx:id=\"ubezpieczenie\" was not injected: check your FXML file 'nadaj_paczke.fxml'.";
        assert gabaryt != null : "fx:id=\"gabaryt\" was not injected: check your FXML file 'nadaj_paczke.fxml'.";
        assert rodzaj != null : "fx:id=\"rodzaj\" was not injected: check your FXML file 'nadaj_paczke.fxml'.";
        assert odbImie != null : "fx:id=\"odbImie\" was not injected: check your FXML file 'nadaj_paczke.fxml'.";
        assert odbTel != null : "fx:id=\"odbTel\" was not injected: check your FXML file 'nadaj_paczke.fxml'.";
        assert odbEmail != null : "fx:id=\"odbEmail\" was not injected: check your FXML file 'nadaj_paczke.fxml'.";
        assert odbNazwisko != null : "fx:id=\"odbNazwisko\" was not injected: check your FXML file 'nadaj_paczke.fxml'.";
        assert odbPaczkomat != null : "fx:id=\"odbPaczkomat\" was not injected: check your FXML file 'nadaj_paczke.fxml'.";
        assert wrocButton != null : "fx:id=\"wrocButton\" was not injected: check your FXML file 'nadaj_paczke.fxml'.";
        assert obliczCeneButton != null : "fx:id=\"obliczCeneButton\" was not injected: check your FXML file 'nadaj_paczke.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'nadaj_paczke.fxml'.";
        assert zaplacButton != null : "fx:id=\"zaplacButton\" was not injected: check your FXML file 'nadaj_paczke.fxml'.";
        assert cenaTV != null : "fx:id=\"cenaTV\" was not injected: check your FXML file 'nadaj_paczke.fxml'.";

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
