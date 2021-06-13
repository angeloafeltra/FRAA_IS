package classes.model.bean.entity;

/**
 * Classe utilizzata per rappresentare l'entità Ambulatori del DB.
 */
public class AmbulatoriBean {

  private String nome;
  private int id;
  private int idStruttura;

  /**
   * Costruttore dell'oggetto AmbulatoriBean.
   *
   * @param nome Nome della struttura
   * @param idStruttura Chiave primaria della struttura
   */
  public AmbulatoriBean(String nome, int idStruttura) {
    this.nome = nome;
    this.idStruttura = idStruttura;
  }

  /**
   * Costruttore vuoto del bean ambulatorio.
   */
  public AmbulatoriBean(){}

  /**
   * Prelevamento del nome dell'ambulatorio.
   *
   * @return Nome della struttura
   */
  public String getNome() {
    return nome;
  }

  /**
   * Impostazione del nome dell'ambulatorio.
   *
   * @param nome Nuovo nome dell'struttura
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Impostazione dell'id dell'ambulatorio.
   *
   * @param id Nuova chiave primaria della struttura
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Prelevamento dell'id dell'ambulatorio.
   *
   * @return Chiave primaria dell'ambulatorio
   */
  public int getId() {
    return id;
  }

  /**
   * Prelevamento dell'id della struttura dove risiede l'ambulatorio.
   *
   * @return Chiave esterna della struttura
   */
  public int getIdStruttura() {
    return idStruttura;
  }

  /**
   * Impostazione dell'id della struttura dove risiede l'ambulatorio.
   *
   * @param idStruttura Nuova chiave esterna della struttura
   */
  public void setIdStruttura(int idStruttura) {
    this.idStruttura = idStruttura;
  }

  /**
   * Rappresentazione scritta dell'oggetto Ambulatorio.
   *
   * @return Stringa con tutti i campi dell'oggetto
   */
  @Override
  public String toString() {
    return "AmbulatoriBean{"
            + "nome='" + nome + '\''
            + ", id=" + id
            + ", idStruttura=" + idStruttura
            + '}';
  }
}
