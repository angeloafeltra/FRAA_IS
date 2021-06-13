package business;

import bean.OperazioneBean;
import bean.PrenotazioneBean;
import eccezioni.InvalidKeyException;
import java.util.ArrayList;
import persistence.DaoInterface;
import persistence.DataAccess;

/** Classe che conterrà tutte le operazioni che l'impiegato puo effettuare. * */
public class Gestione implements GestioneInterface {
  private final DaoInterface dao = new DataAccess();

  /** Costruttore vuoto. */
  public Gestione() {}

  /**
   * Implementa la funzionalità di business che permette ad un impiegato di accettare una
   * prenotazione.
   *
   * <p>Pre-condizione: idOperazione>0 AND idStruttura>0 <br>
   * Post-condizione: Prenotazione->Select(p|
   *    p.idStruttura==idStruttura AND * p.idOperazione==idOperazione AND p.convalida==true)</p>
   *
   * @param idOp id della coda che l'impiegato gestisce
   * @param idStruttura id della struttura per la quale l'impiegato lavora
   * @return ritorna le informazioni della prenotazione accettata che l'impiegato dovra servire
   *     oppure se non ce ne sono null
   */
  public PrenotazioneBean accettaPrenotazione(Integer idOp, Integer idStruttura) {
    try {
      //Controllo id operazione e struttura
      if (idOp == null || idStruttura == null) {
        throw new InvalidKeyException("Id non valido, occorre un id>0");
      }
      if (idOp <= 0 || idStruttura <= 0) {
        throw new InvalidKeyException("Id non valido, occorre un id>0");
      } else {
        PrenotazioneBean p = dao.serviPrenotazione(idOp, idStruttura);
        if (p != null) {
          if (p.getId() <= 0) {
            throw new InvalidKeyException("Id non valido occorre un id>0");
          } else {
            if (dao.deletePrenotazione(p.getId()) > 0) {
              return p;
            }
          }
        }
      }

    } catch (InvalidKeyException i) {
      System.out.println(i.toString());
    }
    return null;
  }

  /**
   * Implementa la funzionalità di business che
   *      permette di sapere il numero di prenotazioni in coda.
   *
   * <p>Pre-condizione: idOperazione>0 AND idStruttura>0 <br>
   * Post-condizione: Prenotazione->exists(p|p.idStruttura==idStruttura AND
   *      p.idOperazione==idOperazione).size()</p>
   *
   * @param idOperazione id della coda
   * @param idStruttura id della struttura che gestisce la coda
   * @return numero di prenotazioni in coda
   */
  public int getNumPrenotazioni(int idOperazione, int idStruttura) {
    try {
      //Controllo dell'id operazione e struttura
      if (idOperazione <= 0 || idStruttura <= 0) {
        throw new InvalidKeyException("Id non valido occorre un id>0");
      } else {
        return dao.numPrenotazioni(idOperazione, idStruttura);
      }
    } catch (InvalidKeyException i) {
      System.out.println(i.toString());
    }
    return 0;
  }

  /**
   * Implementa la funzionalità di business che
   *      permette all'impiegato di conoscere le code gestibili.
   *
   * <p>Post-condizione: Operazioni->asSet(Operazioni)</p>
   *
   * @return ritorna una lista di code
   */
  public ArrayList<OperazioneBean> getListaOperazioni() {
    return dao.getOperazioni();
  }

  /**
   * Implementa la funzionalità di business che restituisce le informazioni su una coda.
   *
   * <p>Pre-condizione: id > 0 <br>
   * Post-condizione: Operazione->select(o|o.idOperazione==id)</p>
   *
   * @param id id della coda
   * @return ritorna un oggetto contenente le informazioni della coda
   */
  public OperazioneBean getOperazione(int id) {
    try {
      //Controllo sull'id
      if (id <= 0) {
        throw new InvalidKeyException("Id non valido occorre un id>0");
      } else {
        return dao.getOperazione(id);
      }
    } catch (InvalidKeyException i) {
      System.out.println(i.toString());
    }
    return null;
  }

  public int deletePrenotazioniScadute() {
    return dao.deleteOldPrenotation();
  }
}
