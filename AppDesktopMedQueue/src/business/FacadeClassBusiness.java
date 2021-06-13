package business;

import bean.ImpiegatoBean;
import bean.OperazioneBean;
import bean.PrenotazioneBean;
import eccezioni.InvalidAccesException;
import eccezioni.InvalidManagementException;
import java.util.ArrayList;

/** Classe che permette di nascondere il funzionamento complesso del business code. */
public class FacadeClassBusiness {

  private final AccessoInterface accesso;
  private final GestioneInterface gestione;

  /** Costruttore della classe. */
  public FacadeClassBusiness() {
    accesso = new Accesso();
    gestione = new Gestione();
  }

  /**
   * Permette di chiedere al sistema di verificare l'autenticazione dell'impiegato.
   *
   * @param cf codice fiscale inserito dall'impiegato
   * @param pw password inserita dall impiegato
   * @return ritorna un oggetto contenente i dati dell'impiegato se l'autenticazione ha successo
   *     altrimenti null.
   */
  public ImpiegatoBean autenticazione(String cf, String pw) {
    try {
      //Controlla codice fiscale e password
      if (cf == null || cf.length() != 16 || pw == null) {
        throw new InvalidAccesException("Pre-condition violata");
      }
      return accesso.verificaCredenziali(cf, pw);
    } catch (InvalidAccesException ex) {
      System.out.println(ex.toString());
    }
    return null;
  }

  /**
   * Permette di chiedere al sistema di accettare una prenotazione in coda per una determinata.
   * struttura
   *
   * @param idStruttura id della struttura
   * @param idOperazione id della coda
   * @return ritorna un oggetto contenente i dati dell della prenotazione oppure un oggetto null
   */
  public PrenotazioneBean accettaPrenotazione(Integer idStruttura, Integer idOperazione) {
    try {
      //Controlla l'id struttura e l'id operazione
      if (idStruttura <= 0 || idOperazione <= 0) {
        throw new InvalidManagementException("Pre-condition violata");
      }
      return gestione.accettaPrenotazione(idStruttura, idOperazione);
    } catch (InvalidManagementException ex) {
      System.out.println(ex.toString());
    }
    return null;
  }

  /**
   * Permette di chiedere al sistema quali sono le code gestibili.
   *
   * @return ritorna una lista di code gestibili
   */
  public ArrayList<OperazioneBean> getCode() {
    return gestione.getListaOperazioni();
  }

  /**
   * Permette di chiedere al sistema le informazioni di una determinata coda.
   *
   * @param idOperazione id della coda
   * @return ritorna un oggetto contenente le informazioni sulla coda oppure un oggetto null
   */
  public OperazioneBean getCoda(int idOperazione) {
    try {
      //Controlla l'id operazione
      if (idOperazione <= 0) {
        throw new InvalidManagementException("Pre-condition violata");
      }
      return gestione.getOperazione(idOperazione);
    } catch (InvalidManagementException ex) {
      System.out.println(ex.toString());
    }
    return null;
  }

  /**
   * Permette di chiedere al sistema il numero di prenotazioni di una coda.
   *
   * @param idOperazione id della coda
   * @param idStruttura id della struttura che gestisce la coda
   * @return ritorna il numero di prenotazioni
   */
  public int getSizeCoda(int idOperazione, int idStruttura) {
    try {
      //Controllo l'id operazione e id struttura
      if (idOperazione <= 0 || idStruttura <= 0) {
        throw new InvalidManagementException("Pre-condition violata");
      }
      return gestione.getNumPrenotazioni(idOperazione, idStruttura);
    } catch (InvalidManagementException ex) {
      System.out.println(ex.toString());
    }
    return 0;
  }

  /**
   * Metodo che elimina le prenotazioni scadute.
   */
  public void eliminaPrenotazioniScadute() {
    gestione.deletePrenotazioniScadute();
  }
}
