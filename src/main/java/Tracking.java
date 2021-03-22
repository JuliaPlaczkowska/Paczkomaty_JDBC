import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * Class that contains getter and setters necessary to handle creating the blanket for the parcel
 */
public class Tracking {

    private IntegerProperty id;
    private StringProperty data_utworzenia;
    private StringProperty data_i_godzina_nadania;
    private StringProperty data_i_godzina_odbioru;
    private StringProperty status_przesylki;

    public Tracking() {
        id = new SimpleIntegerProperty();
        data_utworzenia = new SimpleStringProperty();
        data_i_godzina_nadania = new SimpleStringProperty();
        data_i_godzina_odbioru = new SimpleStringProperty();
        status_przesylki = new SimpleStringProperty();
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

    public String getData_utworzenia() {
        return data_utworzenia.get();
    }

    public StringProperty data_utworzeniaProperty() {
        return data_utworzenia;
    }

    public void setData_utworzenia(String data_utworzenia) {
        this.data_utworzenia.set(data_utworzenia);
    }

    public String getData_i_godzina_nadania() {
        return data_i_godzina_nadania.get();
    }

    public StringProperty data_i_godzina_nadaniaProperty() {
        return data_i_godzina_nadania;
    }

    public void setData_i_godzina_nadania(String data_i_godzina_nadania) {
        this.data_i_godzina_nadania.set(data_i_godzina_nadania);
    }

    public String getData_i_godzina_odbioru() {
        return data_i_godzina_odbioru.get();
    }

    public StringProperty data_i_godzina_odbioruProperty() {
        return data_i_godzina_odbioru;
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
}
