package business;

import bean.OperazioneBean;
import bean.PrenotazioneBean;
import java.util.ArrayList;

/** Interfaccia che conterrà tutte le operazioni che l'impiegato puo effettuare. */
public interface GestioneInterface {

  /**
   * Permette di accettare una prenotazione.
   *
   * <p>Pre-condizione: idOperazione>0 AND idStruttura>0
   * Post-condizione: Prenotazione->Select(p|p.idStruttura==idStruttura AND
   * p.idOperazione==idOperazione AND p.convalida==true)</p>
   *
   * @param idOp id della coda che l'impiegato gestisce
   * @param idStruttura id della struttura per la quale l'impiegato lavora
   * @return ritorna le informazioni della prenotazione accettata che l'impiegato dovra servire
   *     oppure se non ce ne sono null
   */
  public PrenotazioneBean accettaPrenotazione(Integer idOp, Integer idStruttura);

  /**
   * Permette di sapere il numero di prenotazioni in coda.
   * <p>
   *     Pre-condizione: idOperazione>0 AND idStruttura>0 <br>
   *     Post-condizione: Prenotazione->exists(p|p.idStruttura==idStruttura AND
   *     p.idOperazione==idOperazione).size()
   * </p>
   *
   * @param idOperazione id della coda
   * @param idStruttura id della struttura che gestisce la coda
   * @return numero di prenotazioni in coda
   */
  public int getNumPrenotazioni(int idOperazione, int idStruttura);

  /**
   * Permette di ottenere le code gestibili.
   * <p>
   *     Post-condizione: Operazioni->asSet(Operazioni)
   * </p>
   *
   * @return ritorna una lista di code
   */
  public ArrayList<OperazioneBean> getListaOperazioni();

  /**
   * Permette di avere le informazioni di una determinata coda.
   *
   * <p>
   *     Pre-condizione: id > 0 <br>
   *     Post-condizione: Operazione->select(o|o.idOperazione==id)
   * </p>
   *
   * @param id id della coda
   * @return ritorna un oggetto contenente le informazioni della coda
   */
  public OperazioneBean getOperazione(int id);

  /**
   * Metodo per rimuovere le prenotazioni scadute.
   *
   * @return numero di prenotazioni eliminate
   */
  public int deletePrenotazioniScadute();
}
