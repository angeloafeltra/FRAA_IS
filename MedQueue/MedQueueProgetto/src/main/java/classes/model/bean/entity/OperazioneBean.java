package classes.model.bean.entity;

/**
 * Classe utilizzata per rappresentare l'entità Operazione del DB.
 */
public class OperazioneBean {

  private int id;
  private String tipoOperazione;
  private String descrizione;

  /**
   * Costruttore dell'oggetto OperazioneBean.
   *
   * @param tipoOperazione tipo di prenotazione
   * @param descrizione descrizione dell'operazione
   */
  public OperazioneBean(String tipoOperazione, String descrizione) {
    this.tipoOperazione = tipoOperazione;
    this.descrizione = descrizione;
  }

  /**
   * Costruttore vuoto dell'oggetto.
   */
  public OperazioneBean() {}

  /**
   * Prelevamento dell'id dell'operazione.
   *
   * @return id dell'operazione
   */
  public int getId() {
    return id;
  }

  /**
   * Impostazione dell'id dell'operazione.
   *
   * @param id nuovo id dell'operazione
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Prelevamento del tipo di operazione.
   *
   * @return tipo di operazione.
   */
  public String getTipoOperazione() {
    return tipoOperazione;
  }

  /**
   * Impostazione del tipo di operazione.
   *
   * @param tipoOperazione nuovo tipo di operazione
   */
  public void setTipoOperazione(String tipoOperazione) {
    this.tipoOperazione = tipoOperazione;
  }

  /**
   * Prelevamento della descrizione dell'operazione.
   *
   * @return descrizione dell'operazione.
   */
  public String getDescrizione() {
    return descrizione;
  }

  /**
   * Impostazione della descrizione dell'operazione.
   *
   * @param descrizione nuovo tipo di descrizione
   */
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  /**
   * Rappresentazione scritta dell'oggetto Operazione.
   *
   * @return Stringa con tutti i campi dell'oggetto
   */
  @Override
  public String toString() {
    return "OperazioneBean{"
            + "id="
            + id
            + ", tipoOperazione='"
            + tipoOperazione
            + '\''
            + ", descrizione='"
            + descrizione
            + '\''
            + '}';
  }
}
