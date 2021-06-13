package business;

import bean.ImpiegatoBean;
import eccezioni.InvalidKeyException;

/** Interfaccia per verificare le credenziali di un impiegato. */
public interface AccessoInterface {

  /**
   * Permette di verificare le credenziali di un impiegato.
   * <p>Pre-condizione: codicefiscale!=null AND codicefiscale.lenght==16 AND password!=null <br>
   * Post-condizione: Impiegato->select(i|i.codicefiscale==codicefiscale AND
   * i.password==password)</p>
   *
   * @param cf codice fiscale dell'impiegato
   * @param password password dell'impiegato
   * @return restituisce un oggetto contenente le informazioni di un impiegato
   *      se le credenziali sono giuste oppure un oggetto null se le credenziali sono sbagliate
   */
  public ImpiegatoBean verificaCredenziali(String cf, String password);
}
