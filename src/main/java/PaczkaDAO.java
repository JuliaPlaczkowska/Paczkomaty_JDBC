import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.zip.DeflaterOutputStream;

/**
 * This class is used perform particular MySQL queries and updates
 * and returns the results.
 */

public class PaczkaDAO {

    private DBUtil dbUtil;
    private TextArea consoleTextArea;

    public PaczkaDAO(DBUtil dbUtil, TextArea consoleTextArea) {
        this.dbUtil = dbUtil;
        this.consoleTextArea = consoleTextArea;
    }

    /**
     * This method creates and returns observable list of the Przesylka class objects
     * from the given result set
     * @param rs result set
     * @return observable list of the parcels
     * @throws SQLException
     */
    private ObservableList<Przesylka> getPaczkiList(ResultSet rs) throws SQLException {

        ObservableList<Przesylka> przesylkiList = FXCollections.observableArrayList();

        while (rs.next()) {

            Przesylka p = new Przesylka();
            p.setId(rs.getInt("idPRZESYLKI"));
            p.setData_utworzenia(LocalDate.parse(rs.getString("data_utworzenia")));
            p.setRozmiar(rs.getString("rozmiar"));
            p.setRodzaj(rs.getString("rodzaj"));
            p.setWaga(rs.getDouble("waga"));
            p.setWartosc_ubezpieczenia(rs.getDouble("wartosc_ubezpiczenia"));
            p.setData_i_godzina_nadania("data_i_godzina_nadania");
            p.setData_i_godzina_odbioru("data_i_godzina_odbioru");
            p.setStatus_przesylki(rs.getString("status_przesylki"));
            p.setIdNadawca(Integer.parseInt("idNADAWCA"));
            p.setIdOdbiorca(Integer.parseInt("idODBIORCA"));

           przesylkiList.add(p);
        }

        return przesylkiList;
    }

    /**
     * Returns observable list for the parcel tracking
     * @param rs result set
     * @return observable list
     * @throws SQLException
     */

    private ObservableList<Tracking> getTrackingList(ResultSet rs) throws SQLException {

        ObservableList<Tracking> przesylkiList = FXCollections.observableArrayList();

        while (rs.next()) {

            Tracking p = new Tracking();
            p.setId(rs.getInt("idPRZESYLKI"));
            p.setData_utworzenia(rs.getString("data_utworzenia"));
            p.setData_i_godzina_nadania(rs.getString("data_i_godzina_nadania"));
            p.setData_i_godzina_odbioru(rs.getString("data_i_godzina_odbioru"));
            p.setStatus_przesylki(rs.getString("status_przesylki"));

            przesylkiList.add(p);
        }

        return przesylkiList;
    }

    /**
     * Creates observable list of the parcels that match to the giveen id number
     * @param id parcel id
     * @return observable list
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ObservableList<Tracking> sledzPaczke(int id) throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM paczka_dla_klienta WHERE idPRZESYLKI LIKE '%" + id + "%';";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<Tracking> przesylki = getTrackingList(resultSet);

            consoleTextArea.appendText(selectStmt + "\n");

            return przesylki;

        } catch (SQLException e) {
            consoleTextArea.appendText("Wystapil problem podczas szukania pszesylki o id: "+id+". Upewnij sie, ze podany numer jest prawidlowy \n");
            throw e;
        }

    }
    /**
     * Creates observable list of the parcels that match to the given customer name.
     * The customer can be a sender as well as a recipient
     * @param Klient customer's user name
     * @return observable list
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ObservableList<Tracking> historiaPaczek(String Klient) throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM paczka_dla_klienta k JOIN przesylki p on p.idPRZESYLKI=k.idPRZESYLKI WHERE p.idNADAWCA=(select idUZYTKOWNICY from uzytkownicy where login='"+Klient+"');";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<Tracking> przesylki = getTrackingList(resultSet);

            consoleTextArea.appendText(selectStmt + "\n");

            return przesylki;

        } catch (SQLException e) {
            consoleTextArea.appendText("Nie posiadasz żadnych paczek w historii \n");
            throw e;
        }

    }

    /**
     * Creates observable list of the parcel lockers from the result set.
     * @param rs result set
     * @return observable list
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private ObservableList<AutomatWyszukaj> getAutomatyList(ResultSet rs) throws SQLException {

        ObservableList<AutomatWyszukaj> przesylkiList = FXCollections.observableArrayList();

        while (rs.next()) {

            AutomatWyszukaj p = new AutomatWyszukaj();
            p.setId(rs.getInt("idAUTOMATY"));
            p.setAdres(rs.getString("aders"));
            p.setLiczba_wolnych_skrytek(rs.getInt("liczba_wolnych_skrytek"));
            przesylkiList.add(p);
        }

        return przesylkiList;
    }

    /**
     * Creates observable list of the parcel lockers which contains given substring in address String.
     * @param adres address
     * @return observable list
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ObservableList<AutomatWyszukaj> automatyList(String adres) throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM automaty_klient WHERE aders LIKE '%" + adres + "%';";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<AutomatWyszukaj> przesylki = getAutomatyList(resultSet);

            consoleTextArea.appendText(selectStmt + "\n");

            return przesylki;

        } catch (SQLException e) {
            consoleTextArea.appendText("Wystapil problem podczas szukania automatu. Upewnij sie, ze podany adres jest prawidlowy \n");
            throw e;
        }

    }
    /**
     * Creates observable list of the parcel lockers which match to the given id number.
     * @param id locker id
     * @return observable list
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ObservableList<AutomatWyszukaj> automatyListID(int id) throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM automaty_klient WHERE idAUTOMATY LIKE '%" + id + "%';";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<AutomatWyszukaj> przesylki = getAutomatyList(resultSet);

            consoleTextArea.appendText(selectStmt + "\n");

            return przesylki;

        } catch (SQLException e) {
            consoleTextArea.appendText("Wystapil problem podczas szukania automatu. Upewnij sie, ze podany adres jest prawidlowy \n");
            throw e;
        }

    }

    /**
     * Creates new parcel object in database and returns service price calculated by MySQL function
     * @param Nadawca sender user name
     * @param imieOdbiorca receiver's name
     * @param nazwiskoOdbiorca receiver's last name
     * @param email receiver's email
     * @param nr_tel receiver's phone number
     * @param idPaczkomat locker id
     * @param rozmiar parcel size
     * @param rodzaj parcel type
     * @param waga parcel weight
     * @param ubezpieczenie parcel insurance
     * @return price
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Double nadajPaczke(String Nadawca, String imieOdbiorca, String nazwiskoOdbiorca,
                                            String email, int nr_tel, int idPaczkomat, String rozmiar, String rodzaj,
                                            double waga, double ubezpieczenie) throws SQLException, ClassNotFoundException {

        String selectStmt = "call utworzenie_etykiety((select idUZYTKOWNICY from uzytkownicy where login='"+Nadawca+"'), '"+imieOdbiorca+"', '"+nazwiskoOdbiorca+"', '"+
                email+"', "+nr_tel+", "+idPaczkomat+", '"+rozmiar+"', '"+rodzaj+"', "+waga+", "+ubezpieczenie+");";


        dbUtil.dbExecuteUpdate(selectStmt);

        selectStmt = "select cena from przesylki order by idPRZESYLKI desc limit 1";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
            resultSet.next();
            double cena = resultSet.getDouble("cena");

            consoleTextArea.appendText(selectStmt + "\n"+
                    "Cena wynosi "+cena+"\n");

            return cena;

        } catch (SQLException e) {
            consoleTextArea.appendText("Wystapil problem podczas dodawania paczki. Sprawdź poprwność danych \n");
            throw e;
        }

    }

    //pracownik

    /**
     * Changes status of the parcel using MySQL procedure
     * @param id parcel id
     * @param czy_wlozono boolean that determines if the parcel was placed in the locker
     * @param id_paczkomatu locker id
     */

    public void zmienStatusPrzesylki(int id, boolean czy_wlozono, int id_paczkomatu) {

        String selectStmt = "call paczka_skrytka("+id+", "+czy_wlozono+", "+id_paczkomatu+");";
        try {
            dbUtil.dbExecuteUpdate(selectStmt);
        } catch (SQLException throwables) {
            consoleTextArea.appendText("Wystapil problem podczas zmiany statusu. Sprawdź poprawność danych \n");
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Generates observable list of the customers for the given id number
     * @param id customer id
     * @return observable list
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ObservableList<Klient> klienciList(int id) throws SQLException, ClassNotFoundException {



        String selectStmt = "SELECT * FROM klienci WHERE idKLIENCI LIKE '%" + id + "%';";
        if(id==0)
            selectStmt = "SELECT * FROM klienci";

        try {

            ResultSet rs = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<Klient> uzytkownicy = FXCollections.observableArrayList();

            while (rs.next()) {

                Klient p = new Klient();
                p.setId(rs.getInt("idKLIENCI"));
                p.setImie(rs.getString("imie"));
                p.setNazwisko(rs.getString("nazwisko"));
                p.setEmail(rs.getString("email"));
                p.setNr_tel(rs.getInt("nr_tel"));
                uzytkownicy.add(p);
            };

            consoleTextArea.appendText(selectStmt + "\n");

            return uzytkownicy;

        } catch (SQLException e) {
            consoleTextArea.appendText("Wystapil problem podczas wyswietlania uzytkownikow. \n");
            throw e;
        }

    }


    //admin

    /**
     * Generates observable list of the system users for the given id number.
     * If given id numer equals to 0 it generates list of all system users
     * @param id user id
     * @return observable list
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public ObservableList<Uzytkownik> uzytkownicyList(int id) throws SQLException, ClassNotFoundException {



        String selectStmt = "SELECT * FROM uzytkownicy WHERE idUZYTKOWNICY LIKE '%" + id + "%';";
        if(id==0)
            selectStmt = "SELECT * FROM uzytkownicy";

        try {

            ResultSet rs = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<Uzytkownik> uzytkownicy = FXCollections.observableArrayList();

            while (rs.next()) {

                Uzytkownik p = new Uzytkownik();
                p.setId(rs.getInt("idUZYTKOWNICY"));
                p.setLogin(rs.getString("login"));
                p.setHaslo(rs.getString("haslo"));
                p.setRodzaj(rs.getString("rodzaj"));
                uzytkownicy.add(p);
            };

            consoleTextArea.appendText(selectStmt + "\n");

            return uzytkownicy;

        } catch (SQLException e) {
            consoleTextArea.appendText("Wystapil problem podczas eyswietlania uzytkownikow. \n");
            throw e;
        }

    }

    /**
     * adds new user to the db
     * @param login login
     * @param haslo password
     * @param rodzaj role
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void dodajUzytkownika(String login, String haslo, String rodzaj) throws SQLException, ClassNotFoundException {
        String selectStmt = "insert into uzytkownicy( login, haslo, rodzaj) values('"+login+"', '"+haslo+"', '"+rodzaj+"');";
        dbUtil.dbExecuteUpdate(selectStmt);
    }

    /**
     * adds new paczkomat to the db
     * @param adres adress
     * @param liczba_skrytek number of slots in the paczkomat
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void dodajAutomaty(String adres, int liczba_skrytek) throws SQLException, ClassNotFoundException {
        String selectStmt = "insert into automaty( aders, liczba_skrytek, liczba_wolnych_skrytek) values('"+adres+"', '"+liczba_skrytek+"', '"+liczba_skrytek+"');";
        dbUtil.dbExecuteUpdate(selectStmt);
    }
    /**
     * it removes user with the given id form the db
     * @param id user id
     * @throws SQLException sql exception
     * @throws ClassNotFoundException
     */
    public void usunUzytkownika(int id) throws SQLException, ClassNotFoundException {
        String selectStmt = "delete from uzytkownicy where idUZYTKOWNICY="+id+";";
        dbUtil.dbExecuteUpdate(selectStmt);
    }

    /**
     * it removes chosen locker form the db
     * @param id locker id
     * @throws SQLException sql exception
     * @throws ClassNotFoundException
     */
    public void usunAutomat(int id) throws SQLException, ClassNotFoundException {
        String selectStmt = "delete from automaty where idAUTOMATY="+id+";";
        dbUtil.dbExecuteUpdate(selectStmt);
    }




}
