import javafx.beans.property.*;

/**
 * Class automat
 * can be used to create  Atomat object from the database
 */
public class Automat {

    private IntegerProperty id;
    private StringProperty adres;
    private IntegerProperty liczba_skrytek;
    private IntegerProperty liczba_wolnych_skrytek;

    /**
     * constructor
     */
    public Automat() {

        id = new SimpleIntegerProperty();
        adres = new SimpleStringProperty();
        liczba_skrytek = new SimpleIntegerProperty();
        liczba_wolnych_skrytek = new SimpleIntegerProperty();


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

    public int getLiczba_skrytek() {
        return liczba_skrytek.get();
    }

    public IntegerProperty liczba_skrytekProperty() {
        return liczba_skrytek;
    }

    public void setLiczba_skrytek(int liczba_skrytek) {
        this.liczba_skrytek.set(liczba_skrytek);
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
