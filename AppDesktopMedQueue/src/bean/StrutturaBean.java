package bean;

import java.util.Objects;

/** Classe che rappresenta una struttura ospedaliera. */
public class StrutturaBean {

  private int id;
  private String nome;
  private String indirizzo;
  private String numeroDiTelefono;

  /** Inizializza un nuovo oggetto StrutturaBean. */
  public StrutturaBean() {}

  /**
   * Inizializza un nuovo oggetto StrutturaBean settando gli attributi.
   *
   * @param id id della struttura
   * @param nome nome della struttura
   * @param indirizzo indirizzo della struttura
   * @param numeroDiTelefono numero di telefono della struttura
   */
  public StrutturaBean(int id, String nome, String indirizzo, String numeroDiTelefono) {
    this.id = id;
    this.nome = nome;
    this.indirizzo = indirizzo;
    this.numeroDiTelefono = numeroDiTelefono;
  }

  /**
   * Metodo per ottenere l'id di una struttura.
   *
   * @return id struttura
   */
  public int getId() {
    return id;
  }

  /**
   * Metodo per settare l'id ad un oggetto StrutturaBean.
   *
   * @param id id della struttura
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Metodo per ottenere il nome di una struttura.
   *
   * @return nome della struttura
   */
  public String getNome() {
    return nome;
  }

  /**
   * Metodo per settare il nome ad un oggetto StrutturaBean.
   *
   * @param nome nome della struttura
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Metodo che restituisce l'indirizzo della struttura.
   *
   * @return indirizzo struttura
   */
  public String getIndirizzo() {
    return indirizzo;
  }

  /**
   * Metodo per settare un indirizzo all'oggetto StrutturaBean.
   *
   * @param indirizzo indirizzo della struttura
   */
  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  /**
   * Metodo che restituisce in numero di telefono della struttura.
   *
   * @return numero di telefono della struttura
   */
  public String getNumeroDiTelefono() {
    return numeroDiTelefono;
  }

  /**
   * Metodo per settare il numero di telefono ad un oggetto StrutturaBean.
   *
   * @param numeroDiTelefono numero di telefono
   */
  public void setNumeroDiTelefono(String numeroDiTelefono) {
    this.numeroDiTelefono = numeroDiTelefono;
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof StrutturaBean)) {
      return false;
    }
    StrutturaBean that = (StrutturaBean) o;
    return getId() == that.getId()
        && Objects.equals(getNome(), that.getNome())
        && Objects.equals(getIndirizzo(), that.getIndirizzo())
        && Objects.equals(getNumeroDiTelefono(), that.getNumeroDiTelefono());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getNome(), getIndirizzo(), getNumeroDiTelefono());
  }
}
