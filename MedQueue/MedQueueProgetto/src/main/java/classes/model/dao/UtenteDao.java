package classes.model.dao;

import classes.model.DriverManagerConnectionPool;
import classes.model.bean.entity.UtenteBean;
import classes.model.interfaces.UtenteDaoInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Model per collegare la tabella "Utente" al backend.
 */
public class UtenteDao implements UtenteDaoInterface {
  private static final String nomeTabella = "medqueue.utente";

  /**
   * Prelevamento singolo utente.
   *
   * @param cf chiave primaria dell'utente
   * @return Utente avente quel codice fiscale
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public UtenteBean doRetrieveByKey(String cf) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    UtenteBean tmp = new UtenteBean();

    String selectSql = "SELECT * FROM " + nomeTabella + " WHERE codiceFiscale = ?";

    try {
      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSql);
      ps.setString(1, cf);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        tmp.setCodiceFiscale(rs.getString("codiceFiscale"));
        tmp.setPassword(rs.getString("password"));
        tmp.setNome(rs.getString("nome"));
        tmp.setCognome(rs.getString("cognome"));
        tmp.setDataDiNascita(rs.getDate("dataDiNascita"));
        tmp.setEmail(rs.getString("indirizzoEmail"));
        tmp.setNumeroDiTelefono(rs.getString("numeroDiTelefono"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(con);
      }
    }

    if (tmp.getCodiceFiscale() != null) {
      return tmp;
    } else {
      return null;
    }
  }

  /**
   * Prelevamento di tutti gli utenti presenti nel DB.
   *
   * @param order Ordine per la visualizzazione della collezione
   * @return Collezione di utenti
   * @throws SQLException cf per problemi di esecuzione della query
   */
  @Override
  public Collection<UtenteBean> doRetrieveAll(String order) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    Collection<UtenteBean> result = new LinkedList<UtenteBean>();

    String selectSql = "SELECT * FROM " + nomeTabella;

    if (order != null && !order.equals("")) {
      selectSql += " ORDER BY " + order;
    }

    try {

      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSql);

      ResultSet rs = ps.executeQuery();


      while (rs.next()) {
        UtenteBean tmp = new UtenteBean();
        tmp.setCodiceFiscale(rs.getString("codiceFiscale"));
        tmp.setPassword(rs.getString("password"));
        tmp.setNome(rs.getString("nome"));
        tmp.setCognome(rs.getString("cognome"));
        tmp.setDataDiNascita(rs.getDate("dataDiNascita"));
        tmp.setEmail(rs.getString("indirizzoEmail"));
        tmp.setNumeroDiTelefono(rs.getString("numeroDiTelefono"));
        result.add(tmp);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(con);
      }
    }

    return result;
  }

  /**
   * Inserimento nuovo utente nel DB.
   *
   * @param param Nuovo Utente
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public void doSave(UtenteBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "INSERT INTO " + nomeTabella + " VALUES (?, ?, ?, ?, ?, ?, ?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, param.getCodiceFiscale());
      preparedStatement.setString(2, param.getPassword());
      preparedStatement.setString(3, param.getNome());
      preparedStatement.setString(4, param.getCognome());
      preparedStatement.setDate(5, param.getDataDiNascita());
      preparedStatement.setString(6, param.getEmail());
      preparedStatement.setString(7, param.getNumeroDiTelefono());

      preparedStatement.executeUpdate();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
  }

  /**
   * Aggiornamento di un utente presente nel DB.
   *
   * @param param Utente da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public void doUpdate(UtenteBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String deleteSql =
        "UPDATE "
            + nomeTabella
            + " SET password = ?, nome = ?, cognome = ?,"
            + " dataDiNascita = ?, indirizzoEmail = ?, numeroDiTelefono = ?"
            + " WHERE codiceFiscale = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(deleteSql);
      preparedStatement.setString(1, param.getPassword());
      preparedStatement.setString(2, param.getNome());
      preparedStatement.setString(3, param.getCognome());
      preparedStatement.setDate(4, param.getDataDiNascita());
      preparedStatement.setString(5, param.getEmail());
      preparedStatement.setString(6, param.getNumeroDiTelefono());
      preparedStatement.setString(7, param.getCodiceFiscale());

      preparedStatement.executeUpdate();

    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return;
  }

  /**
   * Rimozione di un utente presente nel DB.
   *
   * @param param Utente da rimuovere
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public void doDelete(UtenteBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String deleteSql = "DELETE FROM " + nomeTabella + " WHERE codiceFiscale = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(deleteSql);
      preparedStatement.setString(1, param.getCodiceFiscale());

      preparedStatement.executeUpdate();

    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return;
  }
}
