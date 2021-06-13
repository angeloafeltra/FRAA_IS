package classes.model.interfaces;

import classes.model.bean.entity.PrenotazioneBean;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/** Interfaccia per il Model delle Prenotazioni. */
public interface PrenotazioneDaoInterface {

  /**
   * Prelevamento singola prenotazione.
   *
   * @param id chiave primaria della prenotazione
   * @return Prenotazione avente quell'id
   * @throws SQLException per problemi di esecuzione della query
   */
  PrenotazioneBean doRetrieveByKey(int id) throws SQLException;

  /**
   * Prelevamento di tutte le prenotazione.
   *
   * @param order Ordine per la visualizzazione della collezione
   * @return Collezione di tutte le prenotazioni
   * @throws SQLException per problemi di esecuzione della query
   */
  Collection<PrenotazioneBean> doRetrieveAll(String order) throws SQLException;

  /**
   * Inserimento di una nuova prenotazione nel DB.
   *
   * @param param Nuova prenotazione
   * @throws SQLException per problemi di esecuzione della query
   */
  void doSave(PrenotazioneBean param) throws SQLException;

  /**
   * Aggiornamento di una prenotazione presente nel DB.
   *
   * @param param Prenotazione da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   */
  void doUpdate(PrenotazioneBean param) throws SQLException;

  /**
   * Rimozione di una prenotazione presente sul DB.
   *
   * @param param Prenotazione da rimuovere
   * @throws SQLException per problemi di esecuzione della query
   */
  void doDelete(PrenotazioneBean param) throws SQLException;

  /**
   * Metodo per prelevare tutte le prenotazioni di un utente.
   *
   * @param cf chiave primaria dell'utente di cui vogliamo prendere le prenotazioni
   * @return Collezione di prenotazioni dell'utente avente quel codice fiscale
   * @throws SQLException per problemi di esecuzione della query
   */
  Collection<PrenotazioneBean> getUtentePrenotazioni(String cf) throws SQLException;

  /**
   * Metodo per prelevare tutte le prenotazioni di una struttura.
   *
   * @param idStruttura chiave primaria della struttura di cui vogliamo la coda
   * @return Collezione che rappresenta la coda della struttura
   * @throws SQLException per problemi di esecuzione della query
   */
  Collection<PrenotazioneBean> getCodaStruttura(int idStruttura) throws SQLException;

  /**
   * Metodo per prelevare gli orari di prenotazione liberi.
   *
   * @param idStruttura id della Struttura selezionata
   * @param idOperazione id dell'operazione selezionata
   * @param dataPrenotazione data della prenotazione
   * @return Collezione di orari
   * @throws SQLException per problemi di esecuzione della query
   */
  List<String> getOrariPrenotazione(int idStruttura, int idOperazione,
                                    java.sql.Date dataPrenotazione)throws SQLException;
}
