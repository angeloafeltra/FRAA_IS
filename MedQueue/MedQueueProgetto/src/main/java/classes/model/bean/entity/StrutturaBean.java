package classes.model.bean.entity;

/**
 * Classe utilizzata per rappresentare l'entità Struttura del DB.
 */
public class StrutturaBean {

  private int id;
  private String nome;
  private String indirizzo;
  private String numeroDiTelefono;

  /**
   * Costruttore dell'oggetto StrutturaBean.
   *
   * @param nome nome della struttura
   * @param indirizzo indirizzo della struttura
   * @param numeroDiTelefono numero di telefono della struttura
   */
  public StrutturaBean(String nome, String indirizzo, String numeroDiTelefono) {
    this.nome = nome;
    this.indirizzo = indirizzo;
    this.numeroDiTelefono = numeroDiTelefono;
  }

  /**
   * Costruttore vuoto dell'oggetto StrutturaBean.
   */
  public StrutturaBean() {}

  /**
   * Prelevamento dell'id della struttura.
   *
   * @return id della struttura
   */
  public int getId() {
    return id;
  }

  /**
   * Impostazione dell'id della struttura.
   *
   * @param id nuovo id della struttura
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Prelevamento del nome della struttura.
   *
   * @return nome della struttura
   */
  public String getNome() {
    return nome;
  }

  /**
   * Impostazione del nome della struttura.
   *
   * @param nome nuovo nome della struttura
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Prelevamento dell'indirizzo della struttura.
   *
   * @return indirizzo della struttura
   */
  public String getIndirizzo() {
    return indirizzo;
  }

  /**
   * Impostazione dell'indirizzo della struttura.
   *
   * @param indirizzo nuovo indirizzo della struttura
   */
  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  /**
   * Prelevamento numero di telefono della struttura.
   *
   * @return numero di telefono della struttura
   */
  public String getNumeroDiTelefono() {
    return numeroDiTelefono;
  }

  /**
   * Impostazione numero di telefono della struttura.
   *
   * @param numeroDiTelefono nuovo numero di telefono della struttura
   */
  public void setNumeroDiTelefono(String numeroDiTelefono) {
    this.numeroDiTelefono = numeroDiTelefono;
  }

  /**
   * Rappresentazione scritta dell'oggetto Struttura.
   *
   * @return Stringa con tutti i campi dell'oggetto
   */
  @Override
  public String toString() {
    return "StrutturaBean{"
        + "id="
        + id
        + ", nome='"
        + nome
        + '\''
        + ", indirizzo='"
        + indirizzo
        + '\''
        + ", numeroDiTelefono='"
        + numeroDiTelefono
        + '\''
        + '}';
  }
}
