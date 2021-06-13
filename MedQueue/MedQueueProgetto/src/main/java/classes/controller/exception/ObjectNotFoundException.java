package classes.controller.exception;

/** Classe per il controllo di errori nei controller, generale per ogni oggetto. */
public class ObjectNotFoundException extends RuntimeException {

  /**
   * Metodo che permette di stampare lo stack trace dell'errore generico.
   *
   * @param o Oggetto che ha generato l'errore
   */
  public ObjectNotFoundException(Object o) {
    super(o.getClass().getName() + " non trovato");
  }
}
