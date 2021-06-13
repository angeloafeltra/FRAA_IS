package classes.model.interfaces;

import classes.model.bean.entity.StrutturaBean;
import java.sql.SQLException;
import java.util.Collection;

/** Interfaccia per il Model delle strutture. */
public interface StrutturaDaoInterface {

  /**
   * Prelevamento singola struttura.
   *
   * @param id chiave primaria della struttura
   * @return Struttura avente quell'id
   * @throws SQLException per problemi di esecuzione della query
   */
  StrutturaBean doRetrieveByKey(int id) throws SQLException;

  /**
   * Prelevamento singola struttura.
   *
   * @param nome nome della struttura
   * @return Struttura avente quell'id
   * @throws SQLException per problemi di esecuzione della query
   */
  StrutturaBean doRetrieveByName(String nome) throws SQLException;

  /**
   * Prelevamento di tutte le Strutture nel DB.
   *
   * @param order Ordine per la visualizzazione della collezione
   * @return Collezione di Strutture
   * @throws SQLException per problemi di esecuzione della query
   */
  Collection<StrutturaBean> doRetrieveAll(String order) throws SQLException;

  /**
   * Inserimento nuova struttura nel DB.
   *
   * @param param Nuovo Struttura
   * @throws SQLException per problemi di esecuzione della query
   */
  void doSave(StrutturaBean param) throws SQLException;

  /**
   * Aggiornamento di una struttura presente nel DB.
   *
   * @param param Struttura da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   */
  void doUpdate(StrutturaBean param) throws SQLException;

  /**
   * Rimozione di una struttura presente nel DB.
   *
   * @param param Struttura da rimuovere
   * @throws SQLException per problemi di esecuzione della query
   */
  void doDelete(StrutturaBean param) throws SQLException;
}
