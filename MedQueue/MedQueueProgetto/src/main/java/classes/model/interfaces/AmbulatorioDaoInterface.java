package classes.model.interfaces;

import classes.model.bean.entity.AmbulatoriBean;
import java.sql.SQLException;
import java.util.Collection;

/** Interfaccia per il Model degli Ambulatori. */
public interface AmbulatorioDaoInterface {

  /**
   * Prelevamento singolo ambulatorio.
   *
   * @param id chiave primaria dell'ambulatorio
   * @return Ambulatorio avente quell'id
   * @throws SQLException per problemi di esecuzione della query
   */
  AmbulatoriBean doRetrieveByKey(int id) throws SQLException;

  /**
   * Prelevamento di tutti gli ambulatori presenti nel DB.
   *
   * @param order Ordine per la visualizzazione della collezione
   * @return Collezione di ambulatori
   * @throws SQLException per problemi di esecuzione della query
   */
  Collection<AmbulatoriBean> doRetrieveAll(String order) throws SQLException;

  /**
   * Insierimento di un nuovo ambulatorio nel DB.
   *
   * @param param Nuovo ambulatorio
   * @throws SQLException per problemi di esecuzione della query
   */
  void doSave(AmbulatoriBean param) throws SQLException;

  /**
   * Aggiornamento di un ambulatorio presente nel DB.
   *
   * @param param Ambulatorio da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   */
  void doUpdate(AmbulatoriBean param) throws SQLException;

  /**
   * Rimozione di un ambulatorio presente nel DB.
   *
   * @param param Ambulatorio da rimuovere
   * @throws SQLException per problemi di esecuzione della query
   */
  void doDelete(AmbulatoriBean param) throws SQLException;
}
