import javafx.scene.control.TextArea;
import java.sql.*;
import javax.sql.rowset.CachedRowSet;

/**
 * DBUtil class is responsible for connecting user with a database
 * based on given login and password, executing queries and updates.
 */

public class DBUtil {

    private String userName;
    private String userPassword;
    private TextArea consoleTextArea;

    private Connection conn = null;

    /**
     * provides access to user's name
     * @return user name
     */
    public String getuserName(){
        return  userName;
    }

    /**
     * constructor
     * @param userName user name
     * @param userPassword user password
     * @param consoleTextArea text area to print performed operations and outputs
     */
    public DBUtil(String userName, String userPassword, TextArea consoleTextArea) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.consoleTextArea = consoleTextArea;
    }

    /**
     * This method connects to database
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void dbConnect() throws SQLException, ClassNotFoundException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            consoleTextArea.appendText("No MySQL JDBC Driver found." + "\n");
            e.printStackTrace();
            throw e;
        }

        try {
            conn = DriverManager.getConnection(createURL());
        } catch (SQLException e) {
            consoleTextArea.appendText("Connection Failed! Try again." + "\n");
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * This method disconnects from database
     * @throws SQLException
     */
    public void dbDisconnect() throws SQLException {

        try {

            if (conn != null && !conn.isClosed()) {

                conn.close();
                consoleTextArea.appendText("Connection closed. Bye!" + "\n");

            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Method that returns String value of the user type.
     * @return user type
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String getRodzaj() throws SQLException, ClassNotFoundException {

        String selectStmt = "select rodzaj from uzytkownicy where login='"+userName+"';";

        ResultSet resultSet = dbExecuteQuery(selectStmt);
        resultSet.next();
        return resultSet.getString("rodzaj");

    }

    /**
     * This method creates URL using user name and password
     * @return url
     */
    private String createURL() {

        StringBuilder urlSB = new StringBuilder("jdbc:mysql://");
        urlSB.append("localhost:3306/");
        urlSB.append("kurier?");
        urlSB.append("useUnicode=true&characterEncoding=utf-8");
        urlSB.append("&user=");
        urlSB.append(userName);
        urlSB.append("&password=");
        urlSB.append(userPassword);
        urlSB.append("&serverTimezone=CET");

        return urlSB.toString();
    }

    /**
     * This method executes given SQL query statement.
     * When execution fails the exception is printed in the console text area
     * @param queryStmt query statement
     * @return query result
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {

        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs;

        try {

            dbConnect();

            stmt = conn.prepareStatement(queryStmt);

            resultSet = stmt.executeQuery(queryStmt);

            crs = new CachedRowSetWrapper();

            crs.populate(resultSet);
        } catch (SQLException e) {
            consoleTextArea.appendText("Problem occurred at executeQuery operation. \n");
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }

        return crs;
    }

    /**
     * This method executes given SQL update statement.
     * When execution fails the exception is printed in the console text area
     * @param sqlStmt update statement
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public  void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {

        Statement stmt = null;
        try {
            dbConnect();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);

        } catch (SQLException e) {
            consoleTextArea.appendText("Problem occurred at executeUpdate operation. \n");
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }

}
