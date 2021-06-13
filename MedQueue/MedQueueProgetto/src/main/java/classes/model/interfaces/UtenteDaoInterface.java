package classes.model.interfaces;

import classes.model.bean.entity.UtenteBean;
import java.sql.SQLException;
import java.util.Collection;

/** Interfaccia per il Model dell'utente. */
public interface UtenteDaoInterface {

  /**
   * Prelevamento singolo utente.
   *
   * @param cf chiave primaria dell'utente
   * @return Utente avente quel codice fiscale
   * @throws SQLException per problemi di esecuzione della query
   */
  UtenteBean doRetrieveByKey(String cf) throws SQLException;

  /**
   * Prelevamento di tutti gli utenti presenti nel DB.
   *
   * @param order Ordine per la visualizzazione della collezione
   * @return Collezione di utenti
   * @throws SQLException cf per problemi di esecuzione della query
   */
  Collection<UtenteBean> doRetrieveAll(String order) throws SQLException;

  /**
   * Inserimento nuovo utente nel DB.
   *
   * @param param Nuovo Utente
   * @throws SQLException per problemi di esecuzione della query
   */
  void doSave(UtenteBean param) throws SQLException;

  /**
   * Aggiornamento di un utente presente nel DB.
   *
   * @param param Utente da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   */
  void doUpdate(UtenteBean param) throws SQLException;

  /**
   * Rimozione di un utente presente nel DB.
   *
   * @param param Utente da rimuovere
   * @throws SQLException per problemi di esecuzione della query
   */
  void doDelete(UtenteBean param) throws SQLException;
}
