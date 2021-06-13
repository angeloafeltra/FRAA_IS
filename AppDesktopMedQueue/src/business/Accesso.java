package business;

import bean.ImpiegatoBean;
import eccezioni.InvalidKeyException;
import java.util.regex.Pattern;
import persistence.DaoInterface;
import persistence.DataAccess;

/** Classe che implementa le funzionalità di business relative all'accesso di un impiegato. */
public class Accesso implements AccessoInterface {
  DaoInterface daoOperation = new DataAccess();

  /** Costruttore vuoto. */
  public Accesso() {}

  /**
   * Implementa la funzionalità di business che verifica le credenziali dell'impiegato.
   *
   * <p>Pre-condizione: codicefiscale!=null AND codicefiscale.lenght==16 AND password!=null <br>
   * Post-condizione: Impiegato->select(i|i.codicefiscale==codicefiscale AND
   * i.password==password)</p>
   *
   * @param cf codice fiscale dell'impiegato
   * @param pass password dell'impiegato
   * @return restituisce un oggetto contenente le informazioni di un impiegato
   *      se le credenziali sono giuste oppure un oggetto null se le credenziali sono sbagliate
   */
  public ImpiegatoBean verificaCredenziali(String cf, String pass) {
    try {
      //Controlla il codice fiscale
      if (Pattern.compile(
                  "^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}"
                      + "[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$")
              .matcher(cf)
              .matches()
          == false) {
        throw new InvalidKeyException("Codice Fiscale non rispetta il formato.");
      }
      if (pass == null) {
        throw new InvalidKeyException("Password non inserita.");
      }
      //Controlla la password
      if (Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$").matcher(pass).matches()
          == false) {
        throw new InvalidKeyException("Password non rispetta il formato.");
      } else {
        ImpiegatoBean impiegato = daoOperation.getImpiegato(cf);
        if (impiegato != null) {
          if (impiegato.getPassword().equals(pass)) {
            return impiegato;
          }
        }
      }
    } catch (InvalidKeyException i) {
      System.out.println(i.toString());
    }
    return null;
  }
}
