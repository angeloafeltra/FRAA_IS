package classes.controller.exception;

/** Classe che rappresenta l'eccezione chiave non valida. */
public class InvalidKeyException extends Exception {

  /**
   * Eccezione per la chiave non valida.
   *
   * @param str stringa da mostrare nell'eccezione
   */
  public InvalidKeyException(String str) {
    super(str);
  }
}
