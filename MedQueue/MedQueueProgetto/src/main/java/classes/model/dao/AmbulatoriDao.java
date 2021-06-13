package classes.model.dao;

import classes.model.DriverManagerConnectionPool;
import classes.model.bean.entity.AmbulatoriBean;
import classes.model.interfaces.AmbulatorioDaoInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Model per collegare la tabella "Ambulatori" al backend.
 */
public class AmbulatoriDao implements AmbulatorioDaoInterface {

  private static final String nomeTabella = "ambulatorio";

  /**
   * Prelevamento singolo ambulatorio.
   *
   * @param id chiave primaria dell'ambulatorio
   * @return Ambulatorio avente quell'id
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public AmbulatoriBean doRetrieveByKey(int id) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    AmbulatoriBean tmp = new AmbulatoriBean();

    String selectSql = "SELECT * FROM " + nomeTabella + " WHERE id = ?";

    try {
      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSql);
      ps.setInt(1, id);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        tmp.setId(rs.getInt("Id"));
        tmp.setNome(rs.getString("Nome"));
        tmp.setIdStruttura(rs.getInt("idStruttura"));
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
   * Prelevamento di tutti gli ambulatori presenti nel DB.
   *
   * @param order Ordine per la visualizzazione della collezione
   * @return Collezione di ambulatori
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public Collection<AmbulatoriBean> doRetrieveAll(String order) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    Collection<AmbulatoriBean> result = new LinkedList<AmbulatoriBean>();

    String selectSql = "SELECT * FROM " + nomeTabella;

    if (order != null && !order.equals("")) {
      selectSql += " ORDER BY " + order;
    }

    try {

      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSql);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        AmbulatoriBean tmp = new AmbulatoriBean();
        tmp.setId(rs.getInt("id"));
        tmp.setNome(rs.getString("nome"));
        tmp.setIdStruttura(rs.getInt("idStruttura"));
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
   * Insierimento di un nuovo ambulatorio nel DB.
   *
   * @param param Nuovo ambulatorio
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public void doSave(AmbulatoriBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "INSERT INTO " + nomeTabella + " VALUES (?, ?, ?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setInt(1, param.getId());
      preparedStatement.setString(2, param.getNome());
      preparedStatement.setInt(3, param.getIdStruttura());

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
   * Aggiornamento di un ambulatorio presente nel DB.
   *
   * @param param Ambulatorio da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public void doUpdate(AmbulatoriBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String deleteSql = "UPDATE " + nomeTabella + " SET nome = ?, idStruttura = ? WHERE id = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(deleteSql);
      preparedStatement.setString(1, param.getNome());
      preparedStatement.setInt(2, param.getIdStruttura());
      preparedStatement.setInt(3, param.getId());

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
   * Rimozione di un ambulatorio presente nel DB.
   *
   * @param param Ambulatorio da rimuovere
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public void doDelete(AmbulatoriBean param) throws SQLException {
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
