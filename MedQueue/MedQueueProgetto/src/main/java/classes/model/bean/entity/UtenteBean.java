package classes.model.bean.entity;

import java.sql.Date;

/**
 * Classe utilizzata per rappresentare l'entità Utente del DB.
 */
public class UtenteBean {

  private String codiceFiscale;
  private String password;
  private String nome;
  private String cognome;
  private Date dataDiNascita;
  private String email;
  private String numeroDiTelefono;

  /**
   * Costruttore dell'oggetto UtenteBean.
   *
   * @param codiceFiscale chiave primaria dell'utente
   * @param password password dell'utente
   * @param nome nome dell'utente
   * @param cognome cognome dell'utente
   * @param dataDiNascita data di nascita dell'utente
   * @param email email dell'utente
   * @param numeroDiTelefono numero di telefono dell'utente
   */
  public UtenteBean(
      String codiceFiscale,
      String password,
      String nome,
      String cognome,
      java.sql.Date dataDiNascita,
      String email,
      String numeroDiTelefono) {
    this.codiceFiscale = codiceFiscale;
    this.password = password;
    this.nome = nome;
    this.cognome = cognome;
    this.dataDiNascita = dataDiNascita;
    this.email = email;
    this.numeroDiTelefono = numeroDiTelefono;
  }

  /**
   * Costruttore vuoto dell'oggetto UtenteBean.
   */
  public UtenteBean() {}

  /**
   * Prelevamento del codice fiscale dell'utente.
   *
   * @return codice fiscale dell'utente
   */
  public String getCodiceFiscale() {
    return codiceFiscale;
  }

  /**
   * Impostazione codice fiscale dell'utente.
   *
   * @param codiceFiscale nuovo codice fiscale dell'utente
   */
  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
   * Prelevamento della password dell'utente.
   *
   * @return password dell'utente
   */
  public String getPassword() {
    return password;
  }

  /**
   * Impostazione della password dell'utente.
   *
   * @param password nuova password dell'utente
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Prelevamento del nome dell'utente.
   *
   * @return nome dell'utente
   */
  public String getNome() {
    return nome;
  }

  /**
   * Impostazione nome dell'utente.
   *
   * @param nome nuovo nome dell'utente
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Prelevamento del cognome dell'utente.
   *
   * @return cognome dell'utente
   */
  public String getCognome() {
    return cognome;
  }

  /**
   * Impostazione cognome dell'utente.
   *
   * @param cognome nuovo cognome dell'utente.
   */
  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   * Prelevamento della data di nascita dell'utente.
   *
   * @return data di nascita dell'utente
   */
  public java.sql.Date getDataDiNascita() {
    return dataDiNascita;
  }

  /**
   * Impostazione della data di nascita dell'utente.
   *
   * @param dataDiNascita nuova data di nascita dell'utente
   */
  public void setDataDiNascita(Date dataDiNascita) {
    this.dataDiNascita = dataDiNascita;
  }

  /**
   * Prelevamento email dell'utente.
   *
   * @return email dell'utente
   */
  public String getEmail() {
    return email;
  }

  /**
   * Impostazione email dell'utente.
   *
   * @param email nuova email dell'utente
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Prelevamento del numero di telefono dell'utente.
   *
   * @return numero di telefono dell'utente
   */
  public String getNumeroDiTelefono() {
    return numeroDiTelefono;
  }

  /**
   * Impostazione del numero di telefono dell'utente.
   *
   * @param numeroDiTelefono nuovo numero di telefono dell'utente
   */
  public void setNumeroDiTelefono(String numeroDiTelefono) {
    this.numeroDiTelefono = numeroDiTelefono;
  }

  /**
   * Rappresentazione scritta dell'oggetto Utente.
   *
   * @return Stringa con tutti i campi dell'oggetto
   */
  @Override
  public String toString() {
    return "UtenteBean{"
        + "codiceFiscale='"
        + codiceFiscale
        + '\''
        + ", password='"
        + password
        + '\''
        + ", nome='"
        + nome
        + '\''
        + ", cognome='"
        + cognome
        + '\''
        + ", dataDiNascita="
        + dataDiNascita
        + ", email='"
        + email
        + '\''
        + ", numeroDiTelefono="
        + numeroDiTelefono
        + '}';
  }
}
