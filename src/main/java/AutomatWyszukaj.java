import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
        * Class AutomatWyszukaj
        * can be used to create modified Atomat object from the database
        * with only selected parameters
        */
public class AutomatWyszukaj {

    private IntegerProperty id;
    private StringProperty adres;
    private IntegerProperty liczba_wolnych_skrytek;

    public AutomatWyszukaj() {
        this.id = new SimpleIntegerProperty();
        this.adres = new SimpleStringProperty();
        this.liczba_wolnych_skrytek = new SimpleIntegerProperty();
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

    public String getAdres() {
        return adres.get();
    }

    public StringProperty adresProperty() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres.set(adres);
    }

    public int getLiczba_wolnych_skrytek() {
        return liczba_wolnych_skrytek.get();
    }

    public IntegerProperty liczba_wolnych_skrytekProperty() {
        return liczba_wolnych_skrytek;
    }

    public void setLiczba_wolnych_skrytek(int liczba_wolnych_skrytek) {
        this.liczba_wolnych_skrytek.set(liczba_wolnych_skrytek);
    }
}
