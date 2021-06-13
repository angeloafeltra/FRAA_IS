package presentazione;

import bean.ImpiegatoBean;
import java.sql.Connection;

/**
 * Interfaccia che fornisce il pannello di controllo che permetterà all'impiegato di accettare
 * prenotazioni.
 */
public interface ControlPanelInterface {

  /**
   * Metodo che genera un pannello di controllo che permette
   * all'impiegato di accettare prenotazioni.
   *
   * @param impiegato impiegato della collezione Impiegato che si e loggato nell'applicazione
   */
  public void showControlPanel(ImpiegatoBean impiegato);
}
