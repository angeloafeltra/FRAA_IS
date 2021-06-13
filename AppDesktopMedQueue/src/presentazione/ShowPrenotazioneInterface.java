package presentazione;

import bean.PrenotazioneBean;
import javax.swing.JFrame;

/** Interfaccia per la visualizzazione delle prenotazioni. */
public interface ShowPrenotazioneInterface {
  /**
   * Restituisce il frame per mostrare le prenotazioni.
   *
   * @param p bean della prenotazione
   * @return JFrame per rappresentare le prenotazioni.
   */
  public JFrame showPrenotation(PrenotazioneBean p);
}
