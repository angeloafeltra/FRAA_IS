package classes.controller;

import classes.controller.exception.ErrorNewObjectException;
import classes.controller.exception.InvalidKeyException;
import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.UtenteBean;
import classes.model.dao.UtenteDao;
import classes.model.interfaces.UtenteDaoInterface;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** Classe per controllare i metodi del Model di Utente. */
@RestController
public class UtenteController {
  private final UtenteDaoInterface utenteModel = new UtenteDao();

  /**
   * Metodo che permette di utilizzare il prelevamento per id dell'UtenteModel.
   *
   * @param body corpo della richiesta preso in input
   * @return Utente avente il codice fiscale passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   * @throws InvalidKeyException per problemi di chiave primaria
   */
  @PostMapping(value = "/utente/{cf}", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public UtenteBean getUtenteByCodFisc(@RequestBody String body)
          throws SQLException, ObjectNotFoundException, InvalidKeyException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String cf = jsonObject.get("idUtenteGet").getAsString();
    UtenteBean utenteBean = utenteModel.doRetrieveByKey(cf);
    if (cf.matches("[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$")) {
      if (utenteBean != null) {
        return utenteBean;
      } else {
        throw new ObjectNotFoundException(new UtenteBean());
      }
    } else {
      throw new InvalidKeyException("Codice fiscale non valido");
    }
  }

  /**
   * Metodo che permette di utilizzare il prelevamento di tutti gli oggetti dell'UtenteModel.
   *
   * @param body corpo della richiesta preso in input
   * @return Collezione di Utenti
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(value = "/utenti", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public Collection<UtenteBean> getAllUtenti(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String order = jsonObject.get("ordineUtenti").getAsString();
    return utenteModel.doRetrieveAll(order);
  }

  /**
   * Metodo che permette di utilizzare l'inserimento di un nuovo utente tramite UtenteController.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   * @throws ErrorNewObjectException per problemi di creazione oggetti
   * @throws ParseException per problemi di parsing
   * @return conferma/non conferma del salvataggio dell'utente
   */
  @PostMapping(value = "/newUtente", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public boolean newUtente(@RequestBody String body) throws SQLException,
          ErrorNewObjectException, ParseException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String cf = jsonObject.get("newUtenteCf").getAsString();
    Boolean checkCodFisc;
    checkCodFisc = cf.matches("[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");

    String password = jsonObject.get("newUtentePassword").getAsString();
    Boolean checkPassword;
    checkPassword =
            password.matches("(?=^.{8,}$)(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9]).*$");

    String cognome = jsonObject.get("newUtenteCognome").getAsString();
    Boolean checkSurname;
    checkSurname = cognome.matches("[A-Za-z]+$");

    String nome = jsonObject.get("newUtenteNome").getAsString();
    Boolean checkName;
    checkName = nome.matches("[A-Za-z]+$");

    String phoneNumber = jsonObject.get("newUtentePhoneNumber").getAsString();
    Boolean checkPhoneNumber;
    checkPhoneNumber = phoneNumber.matches("^[0-9]{10,12}");

    String email = jsonObject.get("newUtenteEmail").getAsString();
    Boolean checkMail;
    checkMail = email.matches("\\S+@\\S+\\.\\S+");

    String dataN = jsonObject.get("newUtenteDataN").getAsString();
    java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(dataN);
    java.sql.Date dataNascita = new Date(tmp.getTime());

    UtenteBean utenteBean = new UtenteBean();
    if (checkName && checkSurname && checkPassword
            && checkPhoneNumber && checkCodFisc && checkMail) {
      utenteBean.setNumeroDiTelefono(phoneNumber);
      utenteBean.setPassword(password);
      utenteBean.setEmail(email);
      utenteBean.setCognome(cognome);
      utenteBean.setNome(nome);
      utenteBean.setDataDiNascita(dataNascita);
      utenteBean.setCodiceFiscale(cf);
      utenteModel.doSave(utenteBean);
      return true;
    } else {
      throw new ErrorNewObjectException(new UtenteBean());
    }
  }

  /**
   * Metodo che permette di utilizzare l'eliminazione di un ambulatorio presente sul DB tramite
   * UtenteController.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(value = "/deleteUtente", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public void deleteUtente(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("deleteUtenteId").getAsString();
    utenteModel.doDelete(utenteModel.doRetrieveByKey(id));
  }

  /**
   * Metodo che permette di utilizzare l'aggiornamento di un ambulatorio presente sul DB tramite
   * UtenteController.
   *
   * @return conferma/non conferma dell'aggiornamento dell'utente
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   * @throws ParseException per problemi di parsing
   * @throws ErrorNewObjectException per problemi di creazione oggetti
   * @throws ObjectNotFoundException per problemi di chiave primaria
   */
  @PostMapping(value = "/updateUtente", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public boolean updateUtente(@RequestBody String body) throws SQLException,
          ParseException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String cf = jsonObject.get("updateUtenteCf").getAsString();
    UtenteBean u = utenteModel.doRetrieveByKey(cf);

    if (u != null) {
      String password = jsonObject.get("updateUtentePassword").getAsString();
      Boolean checkPassword;
      checkPassword =
              password.matches("(?=^.{8,}$)(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9]).*$");

      String cognome = jsonObject.get("updateUtenteCognome").getAsString();
      Boolean checkSurname;
      checkSurname = cognome.matches("[A-Za-z ]+$");

      String nome = jsonObject.get("updateUtenteNome").getAsString();
      Boolean checkName;
      checkName = nome.matches("[A-Za-z ]+$");

      String phoneNumber = jsonObject.get("updateUtentePhoneNumber").getAsString();
      Boolean checkPhoneNumber;
      checkPhoneNumber = phoneNumber.matches("^[0-9]{10,12}");

      String email = jsonObject.get("updateUtenteEmail").getAsString();
      Boolean checkMail;
      checkMail = email.matches("\\S+@\\S+\\.\\S+");

      String dataN = jsonObject.get("updateUtenteDataN").getAsString();
      java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(dataN);
      java.sql.Date dataNascita = new Date(tmp.getTime());

      if (checkName && checkSurname && checkPassword
          && checkPhoneNumber && checkMail) {
        u.setPassword(password);
        u.setNome(nome);
        u.setNumeroDiTelefono(phoneNumber);
        u.setDataDiNascita(dataNascita);
        u.setEmail(email);
        utenteModel.doUpdate(u);
        return true;
      } else {
        throw new ErrorNewObjectException(new UtenteBean());
      }
    }
    throw new ObjectNotFoundException(new UtenteBean());
  }
}
