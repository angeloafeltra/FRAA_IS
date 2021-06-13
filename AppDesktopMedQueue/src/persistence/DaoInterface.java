package persistence;

import bean.ImpiegatoBean;
import bean.OperazioneBean;
import bean.PrenotazioneBean;
import bean.StrutturaBean;
import java.util.ArrayList;

/** Interfaccia che permette operazioni sul database. */
public interface DaoInterface {

  /**
   * Metodo che ci permettere di ottenere una prenotazione dal database in base all'id.
   *
   * @param id id della prenotazione che si vuole prelevare dalla collezione Prenotazione
   * @return ritorna la prenotazione oppure null se non è presente nel database
   */
  public PrenotazioneBean getPrenotazione(int id);

  /**
   * Metodo che ci permette di ottenere una struttura ospedaliera dal database in base all'id.
   *
   * @param id id della struttura ospedaliera che si vuole prelevare dalla collezione Struttura
   * @return ritorna la struttura ospedaliera oppure null se non è presente nel database
   */
  public StrutturaBean getStruttura(int id);

  /**
   * Metodo che ci permette di ottenere un operazione per cui l'utente si puo prenotare in base
   * all'id.
   *
   * @param id id dell'operazione che si vuole prelevare dalla collezione Operazione
   * @return ritorna l'operazione
   */
  public OperazioneBean getOperazione(int id);

  /**
   * Metodo che restituisce un impiegato di una struttura ospedaliera in base al codice fiscale.
   *
   * @param codicefiscale codice fiscale dell'impiegato che si vuole prelevare dalla collezione
   *     Impiegato
   * @return ritorna l'impiegato ospedaliero
   */
  public ImpiegatoBean getImpiegato(String codicefiscale);

  /**
   * Metodo che restituisce tutte le operazioni per cui è possibile prenotarsi.
   *
   * @return ritorna una lista di operazioni
   */
  public ArrayList<OperazioneBean> getOperazioni();

  /**
   * Metodo per cancellare una prenotazione dal database in base all'id.
   *
   * @param id id della prenotazione della collezione prenotazione da cancellare
   * @return 0 se la prenotazione non è stata cancellata, 1 se la prentoazione è stata cancellata
   */
  public int deletePrenotazione(int id);

  /**
   * Metodo che restituisce il numero di prenotazioni convalidate in base all'id dell'oprazione e
   * l'id della struttura.
   *
   * @param idOperazione id dell'operazione della collezione Operazione
   * @param idStruttura id della struttura della collezione Struttura
   * @return numero prenotazioni convalidate
   */
  public int numPrenotazioni(int idOperazione, int idStruttura);

  /**
   * Metodo che restituisce la prima operazione da servire in base all'ora della prenotazione.
   *
   * @param idOperazione id dell'operazione della collezione Operazione
   * @param idStruttura id della struttura della collezione Struttura
   * @return prenotazione da servire
   */
  public PrenotazioneBean serviPrenotazione(int idOperazione, int idStruttura);

  /**
   * Metodo che elimina le prenotazioni scadute dal db.
   *
   * @return numero prenotazioni scadute
   */
  public int deleteOldPrenotation();
}
