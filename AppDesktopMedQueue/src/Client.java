import presentazione.LoginInterface;
import presentazione.LoginView;

/** Classe che rappresenta il client locale. */
public class Client {

  /**
   * Main del client locale.
   *
   * @param args array di Stringhe passato in input
   */
  public static void main(String[] args) {
    LoginInterface medQueue = new LoginView();
    medQueue.showLoginView();
  }
}
