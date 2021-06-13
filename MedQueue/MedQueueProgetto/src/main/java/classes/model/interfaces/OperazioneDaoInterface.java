package classes.model.interfaces;

import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.OperazioneBean;
import java.sql.SQLException;
import java.util.Collection;

/** Interfaccia per il Model delle operazioni. */
public interface OperazioneDaoInterface {

  /**
   * Prelevamento singola operazione.
   *
   * @param id chiave primaria dell'operazione
   * @return Operazione avente quell'id
   * @throws SQLException per problemi di esecuzione della query
   */
  OperazioneBean doRetrieveByKey(int id) throws SQLException;

  /**
   * Prelevamento singola operazione.
   *
   * @param tipo tipo dell'operazione
   * @return Operazione avente quell'id
   * @throws SQLException per problemi di esecuzione della query
   */
  OperazioneBean doRetrieveByTipo(String tipo) throws SQLException;

  /**
   * Prelevamento di tutte le operazioni presenti nel DB.
   *
   * @param order Ordine per la visualizzazione della collezione
   * @return Collezione di tutte le operazioni
   * @throws SQLException per problemi di esecuzione della query
   */
  Collection<OperazioneBean> doRetrieveAll(String order) throws SQLException;

  /**
   * Inserimento di una nuova operazione nel DB.
   *
   * @param param Nuova operazione
   * @throws SQLException per problemi di esecuzione della query
   */
  void doSave(OperazioneBean param) throws SQLException;

  /**
   * Aggiornamento di un operazione sul DB.
   *
   * @param param Operazione da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   */
  void doUpdate(OperazioneBean param) throws SQLException;

  /**
   * Rimozione di un operazione presente sul DB.
   *
   * @param param Operazione da rimuovere
   * @throws SQLException per problemi di esecuzione della query
   */
  void doDelete(OperazioneBean param) throws SQLException;
}
