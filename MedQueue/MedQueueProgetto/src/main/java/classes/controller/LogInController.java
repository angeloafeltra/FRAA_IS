package classes.controller;

import classes.model.bean.entity.UtenteBean;
import classes.model.dao.UtenteDao;
import classes.model.interfaces.UtenteDaoInterface;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import javax.servlet.http.HttpServlet;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** Classe che ci permette di effettuare il login al sito. */
@RestController
public class LogInController extends HttpServlet {

  private final UtenteDaoInterface utenteDaoInterface = new UtenteDao();
  private final String username = "convalidaPortale";
  private final String password = "convalidaPortale1!";

  /**
   * Metodo che controlla le credenziali inviate.
   *
   * @param body corpo della richiesta preso in input
   * @return Permesso/Non permesso di accesso
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping (value = "/login", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public UtenteBean login(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String username = jsonObject.get("username").getAsString();
    String password = jsonObject.get("password").getAsString();

    if (username.equals(this.username) && password.equals(this.password)) {
      return new UtenteBean();
    } else {
      UtenteBean utenteBean = utenteDaoInterface.doRetrieveByKey(username);

      if (utenteBean != null && password.equals(utenteBean.getPassword())) {
        return utenteBean;
      } else {
        return null;
      }
    }
  }

  /**
   * Metodo che permette di registrarsi al sito.
   *
   * @param body corpo della richiesta preso in input
   * @return conferma o meno della registrazione
   * @throws SQLException per problemi di esecuzione della query
   * @throws ParseException per problemi di conversione di data
   */
  @PostMapping (value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public int signup(@RequestBody String body) throws SQLException,
          ParseException {

    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();

    String nome = jsonObject.get("nomeNewUtente").getAsString();
    Boolean checkName;
    checkName = nome.matches("[A-Za-z]{2,35}$");

    String cognome = jsonObject.get("cognomeNewUtente").getAsString();
    Boolean checkSurname;
    checkSurname = cognome.matches("[A-Za-z]{2,35}$");

    String codFisc = jsonObject.get("codFiscNewUtente").getAsString();
    Boolean checkCodFisc;
    checkCodFisc = codFisc.matches("[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$")
            && (utenteDaoInterface.doRetrieveByKey(codFisc) == null);

    String password = jsonObject.get("passwordNewUtente").getAsString();
    Boolean checkPassword;
    checkPassword =
            password.matches("(?=^.{6,50}$)(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9]).*$");

    String phoneNumber = jsonObject.get("numeroTelefonoNewUtente").getAsString();
    Boolean checkPhoneNumber;
    checkPhoneNumber = phoneNumber.matches("^[0-9]{10,12}");

    String dataNascita = jsonObject.get("dataDiNascitaNewUtente").getAsString();
    java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(dataNascita);
    java.sql.Date dataDiNascita = new Date(tmp.getTime());
    LocalDate dataNascitaTest = LocalDate.of(dataDiNascita.toLocalDate().getYear(),
            dataDiNascita.toLocalDate().getMonthValue(),
            dataDiNascita.toLocalDate().getDayOfMonth());
    LocalDate oggi = LocalDate.now();
    Boolean checkDataDiNascita = (Period.between(dataNascitaTest, oggi).getYears()) > 17;

    String email = jsonObject.get("emailNewUtente").getAsString();
    Boolean checkMail;
    checkMail = email.matches(
            "^(?=.{8,255}$)[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$"
    );

    UtenteBean utenteBean;

    Collection<UtenteBean> allUtenti = utenteDaoInterface.doRetrieveAll("");

    for (UtenteBean b : allUtenti) {
      if (b.getCodiceFiscale().matches(codFisc)) {
        return 2;
      }
    }

    if (!checkPassword) {
      return 0;
    } else if (!checkDataDiNascita) {
      return 4;
    } else {
      if (checkName && checkSurname && checkPhoneNumber
              && checkCodFisc && checkMail) {
        utenteBean = new UtenteBean(codFisc, password, nome, cognome,
                dataDiNascita, email, phoneNumber);
        utenteDaoInterface.doSave(utenteBean);
        return 1;
      } else {
        return 3;
      }
    }
  }
}
