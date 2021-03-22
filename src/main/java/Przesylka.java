import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Class Przesylka contains the getters and setters for generating the package info
 */

public class Przesylka {

    private IntegerProperty id;
    private ObjectProperty<LocalDate> data_utworzenia;
    private StringProperty rozmiar;
    private StringProperty rodzaj;
    private DoubleProperty waga;
    private DoubleProperty wartosc_ubezpieczenia;
    private DoubleProperty cena;
    private IntegerProperty idAutomat;
    private StringProperty data_i_godzina_nadania;
    private StringProperty data_i_godzina_odbioru;
    private StringProperty status_przesylki;
    private IntegerProperty idNadawca;
    private IntegerProperty idOdbiorca;

    /**
     * empty constructor of the Przesylk class
     */
    public Przesylka() {
        id = new SimpleIntegerProperty();
        data_utworzenia = new SimpleObjectProperty<>();
        rozmiar = new SimpleStringProperty();
        rodzaj = new SimpleStringProperty();
        waga = new SimpleDoubleProperty();
        wartosc_ubezpieczenia = new SimpleDoubleProperty();
        cena = new SimpleDoubleProperty();
        idAutomat = new SimpleIntegerProperty();
        data_i_godzina_nadania = new SimpleStringProperty();
        data_i_godzina_odbioru = new SimpleStringProperty();
        status_przesylki = new SimpleStringProperty();
        idNadawca = new SimpleIntegerProperty();
        idOdbiorca = new SimpleIntegerProperty();
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public LocalDate getData_utworzenia() {
        return data_utworzenia.get();
    }

    public ObjectProperty<LocalDate> data_utworzeniaProperty() {
        return data_utworzenia;
    }

    public void setData_utworzenia(LocalDate data_utworzenia) {
        this.data_utworzenia.set(data_utworzenia);
    }

    public String getRozmiar() {
        return rozmiar.get();
    }

    public StringProperty rozmiarProperty() {
        return rozmiar;
    }

    public void setRozmiar(String rozmiar) {
        this.rozmiar.set(rozmiar);
    }

    public String getRodzaj() {
        return rodzaj.get();
    }

    public StringProperty rodzajProperty() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj.set(rodzaj);
    }

    public double getWaga() {
        return waga.get();
    }

    public DoubleProperty wagaProperty() {
        return waga;
    }

    public void setWaga(double waga) {
        this.waga.set(waga);
    }

    public double getWartosc_ubezpieczenia() {
        return wartosc_ubezpieczenia.get();
    }

    public DoubleProperty wartosc_ubezpieczeniaProperty() {
        return wartosc_ubezpieczenia;
    }

    public void setWartosc_ubezpieczenia(double wartosc_ubezpieczenia) {
        this.wartosc_ubezpieczenia.set(wartosc_ubezpieczenia);
    }

    public double getCena() {
        return cena.get();
    }

    public DoubleProperty cenaProperty() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena.set(cena);
    }

    public int getIdAutomat() {
        return idAutomat.get();
    }

    public IntegerProperty idAutomatProperty() {
        return idAutomat;
    }

    public void setIdAutomat(int idAutomat) {
        this.idAutomat.set(idAutomat);
    }

    public String getData_i_godzina_nadania() {
        return data_i_godzina_nadania.get();
    }

    public String data_i_godzina_nadaniaProperty() {
        return data_i_godzina_nadania.get();
    }

    public void setData_i_godzina_nadania(String data_i_godzina_nadania) {
        this.data_i_godzina_nadania.set(data_i_godzina_nadania);
    }

    public String getData_i_godzina_odbioru() {
        return data_i_godzina_odbioru.get();
    }

    public String data_i_godzina_odbioruProperty() {
        return data_i_godzina_odbioru.get();
    }

    public void setData_i_godzina_odbioru(String data_i_godzina_odbioru) {
        this.data_i_godzina_odbioru.set(data_i_godzina_odbioru);
    }

    public String getStatus_przesylki() {
        return status_przesylki.get();
    }

    public StringProperty status_przesylkiProperty() {
        return status_przesylki;
    }

    public void setStatus_przesylki(String status_przesylki) {
        this.status_przesylki.set(status_przesylki);
    }

    public int getIdNadawca() {
        return idNadawca.get();
    }

    public IntegerProperty idNadawcaProperty() {
        return idNadawca;
    }

    public void setIdNadawca(int idNadawca) {
        this.idNadawca.set(idNadawca);
    }

    public int getIdOdbiorca() {
        return idOdbiorca.get();
    }

    public IntegerProperty idOdbiorcaProperty() {
        return idOdbiorca;
    }

    public void setIdOdbiorca(int idOdbiorca) {
        this.idOdbiorca.set(idOdbiorca);
    }
}
