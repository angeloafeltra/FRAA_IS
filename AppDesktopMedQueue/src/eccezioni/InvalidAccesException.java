package eccezioni;

/** Eccezione per l'accesso non valido. */
public class InvalidAccesException extends Exception {

  /**
   * Eccezione per l'accesso non valido.
   *
   * @param str messaggio da mostrare al lancio dell'eccezione
   */
  public InvalidAccesException(String str) {
    super(str);
  }
}
