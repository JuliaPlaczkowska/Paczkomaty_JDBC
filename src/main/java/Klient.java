import javafx.beans.property.*;

        /**
        * Class klient
        * can be used to create Klient object from the database
        */
public class Klient {



    private IntegerProperty id;
    private StringProperty imie;
    private StringProperty nazwisko;
    private StringProperty email;
    private IntegerProperty nr_tel;

            /**
             * constructor
             */
    public Klient() {

        id = new SimpleIntegerProperty();
        imie = new SimpleStringProperty();
        nazwisko = new SimpleStringProperty();
        email = new SimpleStringProperty();
        nr_tel = new SimpleIntegerProperty();


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

    public String getImie() {
        return imie.get();
    }

    public StringProperty imieProperty() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie.set(imie);
    }

    public String getNazwisko() {
        return nazwisko.get();
    }

    public StringProperty nazwiskoProperty() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public int getNr_tel() {
        return nr_tel.get();
    }

    public IntegerProperty nr_telProperty() {
        return nr_tel;
    }

    public void setNr_tel(int nr_tel) {
        this.nr_tel.set(nr_tel);
    }
}
