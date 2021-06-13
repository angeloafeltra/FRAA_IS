package classes.model.dao;

import classes.model.DriverManagerConnectionPool;
import classes.model.bean.entity.PrenotazioneBean;
import classes.model.interfaces.PrenotazioneDaoInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Model per collegare la tabella "Prenotazione" al backend.
 */
public class PrenotazioneDao implements PrenotazioneDaoInterface {
  private static final String nomeTabella = "prenotazione";
  private String[] elencoOrari = {"09:00:00", "09:15:00", "09:30:00", "09:45:00",
                                  "10:00:00", "10:15:00", "10:30:00", "10:45:00",
                                  "11:00:00", "11:15:00", "11:30:00", "11:45:00",
                                  "12:00:00", "12:15:00", "12:30:00", "12:45:00",
                                  "13:00:00"};


  /**
   * Prelevamento singola prenotazione.
   *
   * @param id chiave primaria della prenotazione
   * @return Prenotazione avente quell'id
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public PrenotazioneBean doRetrieveByKey(int id) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    PrenotazioneBean tmp = new PrenotazioneBean();

    String selectSql = "SELECT * FROM " + nomeTabella + " WHERE id = ?";

    try {
      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSql);
      ps.setInt(1, id);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        tmp.setId(rs.getInt("id"));
        tmp.setOra(rs.getString("ora"));
        tmp.setDataPrenotazione(rs.getDate("data"));
        tmp.setCodiceFiscale(rs.getString("codiceFiscale"));
        tmp.setConvalida(rs.getBoolean("convalida"));
        tmp.setIdStruttura(rs.getInt("idStruttura"));
        tmp.setIdOperazione(rs.getInt("idOperazione"));
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
   * Prelevamento di tutte le prenotazione.
   *
   * @param order Ordine per la visualizzazione della collezione
   * @return Collezione di tutte le prenotazioni
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public Collection<PrenotazioneBean> doRetrieveAll(String order) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    Collection<PrenotazioneBean> result = new LinkedList<PrenotazioneBean>();

    String selectSql = "SELECT * FROM " + nomeTabella;

    if (order != null && !order.equals("")) {
      selectSql += " ORDER BY " + order;
    }

    try {

      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSql);

      ResultSet rs = ps.executeQuery();


      while (rs.next()) {
        PrenotazioneBean tmp = new PrenotazioneBean();
        tmp.setId(rs.getInt("id"));
        tmp.setOra(rs.getString("ora"));
        tmp.setDataPrenotazione(rs.getDate("data"));
        tmp.setCodiceFiscale(rs.getString("codiceFiscale"));
        tmp.setConvalida(rs.getBoolean("convalida"));
        tmp.setIdStruttura(rs.getInt("idStruttura"));
        tmp.setIdOperazione(rs.getInt("idOperazione"));
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
   * Inserimento di una nuova prenotazione nel DB.
   *
   * @param param Nuova prenotazione
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public void doSave(PrenotazioneBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "INSERT INTO " + nomeTabella
            + "(data, ora, convalida, codiceFiscale, idOperazione, idStruttura) "
            + " VALUES (?, ?, ?, ?, ?, ?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setDate(1, param.getDataPrenotazione());
      preparedStatement.setString(2, param.getOra());
      preparedStatement.setBoolean(3, param.isConvalida());
      preparedStatement.setString(4, param.getCodiceFiscale());
      preparedStatement.setInt(5, param.getIdOperazione());
      preparedStatement.setInt(6, param.getIdStruttura());

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
   * Aggiornamento di una prenotazione presente nel DB.
   *
   * @param param Prenotazione da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public void doUpdate(PrenotazioneBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String updateSql =
        "UPDATE "
            + nomeTabella
            + " SET ora = ?, data = ?, codiceFiscale = ?,"
            + " idOperazione = ?, idStruttura = ?, convalida = ? WHERE id = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(updateSql);
      preparedStatement.setString(1, param.getOra());
      preparedStatement.setDate(2, param.getDataPrenotazione());
      preparedStatement.setString(3, param.getCodiceFiscale());
      preparedStatement.setInt(4, param.getIdOperazione());
      preparedStatement.setInt(5, param.getIdStruttura());
      preparedStatement.setBoolean(6, param.isConvalida());
      preparedStatement.setInt(7, param.getId());

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
   * Rimozione di una prenotazione presente sul DB.
   *
   * @param param Prenotazione da rimuovere
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public void doDelete(PrenotazioneBean param) throws SQLException {
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

  /**
   * Metodo per prelevare tutte le prenotazioni di un utente.
   *
   * @param cf chiave primaria dell'utente di cui vogliamo prendere le prenotazioni
   * @return Collezione di prenotazioni dell'utente avente quel codice fiscale
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public Collection<PrenotazioneBean> getUtentePrenotazioni(String cf) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    Collection<PrenotazioneBean> result = new LinkedList<PrenotazioneBean>();


    String selectSql = "SELECT * FROM " + nomeTabella + " WHERE codiceFiscale = ? ORDER BY data";

    try {
      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSql);
      ps.setString(1, cf);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        PrenotazioneBean tmp = new PrenotazioneBean();
        tmp.setId(rs.getInt("id"));
        tmp.setOra(rs.getString("ora"));
        tmp.setDataPrenotazione(rs.getDate("data"));
        tmp.setCodiceFiscale(rs.getString("codiceFiscale"));
        tmp.setConvalida(rs.getBoolean("convalida"));
        tmp.setIdStruttura(rs.getInt("idStruttura"));
        tmp.setIdOperazione(rs.getInt("idOperazione"));
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

    if (result.isEmpty()) {
      return null;
    }
    return result;
  }

  /**
   * Metodo per prelevare tutte le prenotazioni di una struttura.
   *
   * @param idStruttura chiave primaria della struttura di cui vogliamo la coda
   * @return Collezione che rappresenta la coda della struttura
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public Collection<PrenotazioneBean> getCodaStruttura(int idStruttura) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Collection<PrenotazioneBean> result = new LinkedList<PrenotazioneBean>();


    String selectPrenotazioniSql = "SELECT * FROM " + nomeTabella
            + " WHERE idStruttura = ? ORDER BY data AND ora";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectPrenotazioniSql);

      preparedStatement.setInt(1, idStruttura);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        PrenotazioneBean tmp = new PrenotazioneBean();
        tmp.setId(rs.getInt("id"));
        tmp.setOra(rs.getString("ora"));
        tmp.setDataPrenotazione(rs.getDate("data"));
        tmp.setCodiceFiscale(rs.getString("codiceFiscale"));
        tmp.setConvalida(rs.getBoolean("convalida"));
        tmp.setIdStruttura(rs.getInt("idStruttura"));
        tmp.setIdOperazione(rs.getInt("idOperazione"));
        result.add(tmp);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }

    return result;
  }

  /**
   * Metodo per prelevare gli orari di prenotazione liberi.
   *
   * @param idStruttura id della Struttura selezionata
   * @param idOperazione id dell'operazione selezionata
   * @param dataPrenotazione data della prenotazione
   * @return Collezione di orari
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public List<String> getOrariPrenotazione(int idStruttura, int idOperazione,
                                           java.sql.Date dataPrenotazione)throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<String> query = new ArrayList<String>();
    List<String> result = new ArrayList<String>();


    String selecrOrariSql = "SELECT ora FROM " + nomeTabella
            + " WHERE idStruttura =? AND idOperazione=? AND data=? ORDER BY ora";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selecrOrariSql);

      preparedStatement.setInt(1, idStruttura);
      preparedStatement.setInt(2, idOperazione);
      preparedStatement.setDate(3, dataPrenotazione);
      ResultSet res = preparedStatement.executeQuery();

      while (res.next()) {
        query.add(res.getString("ora"));
      }

      //Esempio per testare giorno completamente pieno
      /*query.clear();
      for(int i = 0;  i < elencoOrari.length; i++) {
        query.add(elencoOrari[i]);
      }*/

      if (query.size() > 0) {
        for (int i = 0, j = 0; i < elencoOrari.length; i++) {
          if (!elencoOrari[i].equals(query.get(j))) {
            result.add(elencoOrari[i]);
          } else {
            if (j < query.size() - 1) {
              j++;
            } else {
              j = query.size() - 1;
            }
          }
        }
      } else {
        //Se sono liberi tutti gli orari
        for (int i = 0;  i < elencoOrari.length; i++) {
          result.add(elencoOrari[i]);
        }
      }

      //Se sono occupati tutti gli orari
      if (result.size() == 0) {
        System.exit(400);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return result;
  }

}
