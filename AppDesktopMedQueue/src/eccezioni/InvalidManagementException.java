package eccezioni;

/** Classe che rappresenta l'eccezione Management non valido. */
public class InvalidManagementException extends Exception {

  /**
   * Eccezione per il management non valido.
   *
   * @param str messaggio da mostrare
   */
  public InvalidManagementException(String str) {
    super(str);
  }
}
