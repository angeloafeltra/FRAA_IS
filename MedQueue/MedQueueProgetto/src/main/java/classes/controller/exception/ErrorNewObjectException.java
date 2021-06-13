package classes.controller.exception;

/** Classe di eccezione per creazione di oggetti. */
public class ErrorNewObjectException extends RuntimeException {

  /**
   * Metodo che permette di stampare lo stack trace dell'errore riguardo errori nell'input.
   *
   * @param o Oggetto che ha generato l'errore
   */
  public ErrorNewObjectException(Object o) {
    super(
        "Errore nella creazione dell'oggetto "
            + o.getClass().getName()
            + " per problemi con i campi");
  }
}
