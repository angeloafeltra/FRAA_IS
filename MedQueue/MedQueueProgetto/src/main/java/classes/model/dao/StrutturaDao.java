package classes.model.dao;

import classes.model.DriverManagerConnectionPool;
import classes.model.bean.entity.StrutturaBean;
import classes.model.interfaces.StrutturaDaoInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Model per collegare la tabella "Struttura" al backend.
 */
public class StrutturaDao implements StrutturaDaoInterface {
  private static final String nomeTabella = "struttura";

  /**
   * Prelevamento singola struttura.
   *
   * @param id chiave primaria della struttura
   * @return Struttura avente quell'id
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public StrutturaBean doRetrieveByKey(int id) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    StrutturaBean tmp = new StrutturaBean();

    String selectSql = "SELECT * FROM " + nomeTabella + " WHERE id = ?";

    try {
      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSql);
      ps.setInt(1, id);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        tmp.setId(rs.getInt("id"));
        tmp.setNome(rs.getString("nome"));
        tmp.setIndirizzo(rs.getString("indirizzo"));
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

    if (tmp.getNome() != null) {
      return tmp;
    } else {
      return null;
    }
  }

  /**
   * Prelevamento singola struttura.
   *
   * @param nome nome della struttura
   * @return Struttura avente quell'id
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public StrutturaBean doRetrieveByName(String nome) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    StrutturaBean tmp = new StrutturaBean();

    String selectSql = "SELECT * FROM " + nomeTabella + " WHERE nome = ?";

    try {
      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSql);
      ps.setString(1, nome);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        tmp.setId(rs.getInt("id"));
        tmp.setNome(rs.getString("nome"));
        tmp.setIndirizzo(rs.getString("indirizzo"));
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

    if (tmp.getNome() != null) {
      return tmp;
    } else {
      return null;
    }
  }

  /**
   * Prelevamento di tutte le Strutture nel DB.
   *
   * @param order Ordine per la visualizzazione della collezione
   * @return Collezione di Strutture
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public Collection<StrutturaBean> doRetrieveAll(String order) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    Collection<StrutturaBean> result = new LinkedList<StrutturaBean>();

    String selectSql = "SELECT * FROM " + nomeTabella;

    if (order != null && !order.equals("")) {
      selectSql += " ORDER BY " + order;
    }

    try {

      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSql);

      ResultSet rs = ps.executeQuery();


      while (rs.next()) {
        StrutturaBean tmp = new StrutturaBean();
        tmp.setId(rs.getInt("id"));
        tmp.setNome(rs.getString("nome"));
        tmp.setIndirizzo(rs.getString("indirizzo"));
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
   * Inserimento nuova struttura nel DB.
   *
   * @param param Nuovo Struttura
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public void doSave(StrutturaBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "INSERT INTO " + nomeTabella + " (nome, indirizzo, numeroDiTelefono) "
            + "VALUES (?, ?, ?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, param.getNome());
      preparedStatement.setString(2, param.getIndirizzo());
      preparedStatement.setString(3, param.getNumeroDiTelefono());

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
   * Aggiornamento di una struttura presente nel DB.
   *
   * @param param Struttura da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public void doUpdate(StrutturaBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String deleteSql =
        "UPDATE " + nomeTabella + " SET nome = ?, indirizzo = ?, numeroDiTelefono = ? WHERE id = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(deleteSql);
      preparedStatement.setString(1, param.getNome());
      preparedStatement.setString(2, param.getIndirizzo());
      preparedStatement.setString(3, param.getNumeroDiTelefono());
      preparedStatement.setInt(4, param.getId());

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
   * Rimozione di una struttura presente nel DB.
   *
   * @param param Struttura da rimuovere
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public void doDelete(StrutturaBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String deleteSql = "DELETE FROM " + nomeTabella + " WHERE id = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(deleteSql);
      preparedStatement.setInt(1, param.getId());

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
