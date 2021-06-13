package classes.model.bean.entity;

import java.sql.Date;

/**
 * Classe utilizzata per rappresentare l'entità Impiegato del DB.
 */
public class PrenotazioneBean {

  private int id;
  private String ora;
  private Date dataPrenotazione;
  private String codiceFiscale;
  private int idOperazione;
  private int idStruttura;
  private boolean convalida;

  /**
   * Costruttore dell'oggetto PrenotazioneBean.
   *
   * @param ora Ora della prenotazione
   * @param data Data della prenotazione
   * @param codiceFiscale chiave esterna dell'utente prenotato
   * @param idOp chiave esterna della prenotazione
   * @param idSt chiave esterna della struttura
   * @param convalida controllo se la convalida è avvenuta
   */
  public PrenotazioneBean(
      String ora, Date data, String codiceFiscale, int idOp, int idSt, boolean convalida) {
    this.ora = ora;
    this.dataPrenotazione = data;
    this.codiceFiscale = codiceFiscale;
    this.idOperazione = idOp;
    this.idStruttura = idSt;
    this.convalida = convalida;
  }

  /**
   * Costruttore vuoto dell'oggetto PrenotazioneBean.
   */
  public PrenotazioneBean() {}

  /**
   * Prelevamento dell'id della prentoazione.
   *
   * @return id della prentoazione
   */
  public int getId() {
    return id;
  }

  /**
   * Impostazione dell'id della prenotazione.
   *
   * @param id nuovo id della prenotazione
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Prelevamento ora della prenotazione.
   *
   * @return ora della prenotazione
   */
  public String getOra() {
    return ora;
  }

  /**
   * Impostazione dell'ora della prenotazione.
   *
   * @param ora nuova ora della prenotazione
   */
  public void setOra(String ora) {
    this.ora = ora;
  }

  /**
   * Prelevamento della data della prenotazione.
   *
   * @return data della prenotazione
   */
  public Date getDataPrenotazione() {
    return dataPrenotazione;
  }

  /**
   * Impostazione data della prenotazione.
   *
   * @param data nuova data della prenotazione
   */
  public void setDataPrenotazione(Date data) {
    this.dataPrenotazione = data;
  }

  /**
   * Prelevamento della chiave esterna dell'utente.
   *
   * @return chiave esterna dell'utente
   */
  public String getCodiceFiscale() {
    return codiceFiscale;
  }

  /**
   * Impostazione della chiave esterna dell'utente.
   *
   * @param codiceFiscale chiave esterna dell'utente
   */
  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
   * Prelevamento della chiave esterna dell'operazione.
   *
   * @return chiave esterna dell'operazione
   */
  public int getIdOperazione() {
    return idOperazione;
  }

  /**
   * Impostazione della chiave esterna dell'operazione.
   *
   * @param idOp nuova chiave esterna dell'operazione
   */
  public void setIdOperazione(int idOp) {
    this.idOperazione = idOp;
  }

  /**
   * Prelevamento della chiave esterna della struttura.
   *
   * @return chiave esterna della struttura
   */
  public int getIdStruttura() {
    return idStruttura;
  }

  /**
   * Impostazione della chiave esterna della struttura.
   *
   * @param idSt nuova chiave esterna della struttura
   */
  public void setIdStruttura(int idSt) {
    this.idStruttura = idSt;
  }

  /**
   * Prelevamento della convalida.
   *
   * @return convalida
   */
  public boolean isConvalida() {
    return convalida;
  }

  /**
   * Impostazione della convalida.
   *
   * @param convalida nuova convalida
   */
  public void setConvalida(boolean convalida) {
    this.convalida = convalida;
  }

  /**
   * Rappresentazione scritta dell'oggetto Prenotazione.
   *
   * @return Stringa con tutti i campi dell'oggetto
   */
  @Override
  public String toString() {
    return "PrenotazioneBean{"
        + "id="
        + id
        + ", ora='"
        + ora
        + '\''
        + ", dataPrenotazione="
        + dataPrenotazione
        + ", codiceFiscale='"
        + codiceFiscale
        + '\''
        + ", idOperazione="
        + idOperazione
        + ", idStruttura="
        + idStruttura
        + ", convalida="
        + convalida
        + '}';
  }
}
