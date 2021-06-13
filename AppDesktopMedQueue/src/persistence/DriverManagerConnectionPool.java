package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe che permette di gestire il driver per la connessione al DataBase.
 */
public class DriverManagerConnectionPool {

  private static List<Connection> freeDbConnections;

  static {
    freeDbConnections = new LinkedList<>();
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("DB driver not found:" + e.getMessage());
    }
  }

  /**
   * Costruttore che crea l'oggetto con una lista di connessioni.
   */
  public DriverManagerConnectionPool() {
    freeDbConnections = new LinkedList<>();
  }

  /**
   * Metodo che permette di creare una connessione al database
   * in base all'ip, la porta e il nome del DataBase.
   *
   * @return una nuova connessione se tutto è andato a buon fine, null altrimenti
   */
  public static synchronized Connection createDbConnection() {
    try {
      Connection newConnection;
      String ip = "localhost";
      String port = "3306";
      String db =
          "medqueue?serverTimezone=UTC&useLegacyDatetimeCode=false&useUnicode=true"
                  + "&useJDBCCompliantTimezoneShift=true&zeroDateTimeBehavior=convertToNull"
                  + "&autoReconnect=true&useSSL=false";
      String username = "root";
      String password = "angelo99";

      newConnection =
          DriverManager.getConnection(
              "jdbc:mysql://" + ip + ":" + port + "/" + db, username, password);

      newConnection.setAutoCommit(true);
      return newConnection;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Metodo che permette di interagire con la lista di connessioni e prenderne una.
   *
   * @return la connessione disponibile
   * @throws SQLException se c'è un errore di sintassi SQL
   */
  public static synchronized Connection getConnection() throws SQLException {
    Connection connection;

    if (!freeDbConnections.isEmpty()) {
      connection = freeDbConnections.get(0);
      freeDbConnections.remove(0);

      try {
        if (connection.isClosed()) {
          connection = getConnection();
        }
      } catch (SQLException e) {
        connection.close();
        connection = getConnection();
      }
    } else {
      connection = createDbConnection();
    }

    return connection;
  }

  /**
   * Metodo che rilascia la connessione e le risorse associate.
   *
   * @param connection prende in input la connessione da rilasciare e eliminare
   * @throws SQLException se c'è qualche errore a livello di sintassi SQL
   */
  public static synchronized void releaseConnection(Connection connection) throws SQLException {
    if (connection != null) {
      freeDbConnections.add(connection);
    }
  }
}
