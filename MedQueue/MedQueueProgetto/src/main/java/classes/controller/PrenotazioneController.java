package classes.controller;

import classes.controller.exception.ErrorNewObjectException;
import classes.controller.exception.InvalidKeyException;
import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.OperazioneBean;
import classes.model.bean.entity.PrenotazioneBean;
import classes.model.bean.entity.StrutturaBean;
import classes.model.bean.entity.UtenteBean;
import classes.model.dao.OperazioneDao;
import classes.model.dao.PrenotazioneDao;
import classes.model.dao.StrutturaDao;
import classes.model.dao.UtenteDao;
import classes.model.interfaces.OperazioneDaoInterface;
import classes.model.interfaces.PrenotazioneDaoInterface;
import classes.model.interfaces.StrutturaDaoInterface;
import classes.model.interfaces.UtenteDaoInterface;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe utilizzata per controllare i metodi del Model di Prenotazione, in aggiunta si implementa
 * un metodo per poter prendere le prenotazioni di un singolo utente.
 */
@RestController
public class PrenotazioneController {
  private final PrenotazioneDaoInterface prenotazioneDaoInterface = new PrenotazioneDao();
  private final StrutturaDaoInterface strutturaDaoInterface = new StrutturaDao();
  private final OperazioneDaoInterface operazioneDaoInterface = new OperazioneDao();
  private final UtenteDaoInterface utenteDaoInterface = new UtenteDao();

  /**
   * Metodo che permette di utilizzare il prelevamento per id del PrenotazioneModel.
   *
   * @param body corpo della richiesta preso in input
   * @return Prenotazione avente l'id passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   * @throws InvalidKeyException per problemi di chiave primaria
   */
  @PostMapping(value = "/prenotazione/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public PrenotazioneBean getPrenotazioneById(@RequestBody String body) throws SQLException,
          ObjectNotFoundException, InvalidKeyException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("idPrenotazioneGet").getAsString();

    PrenotazioneBean prenotazioneBean =
            prenotazioneDaoInterface.doRetrieveByKey(Integer.valueOf(id));

    if (Integer.valueOf(id) > 0) {
      if (prenotazioneBean != null) {
        return prenotazioneBean;
      } else {
        throw new ObjectNotFoundException(new PrenotazioneBean());
      }
    } else {
      throw new InvalidKeyException("Id prenotazione non valido");
    }
  }

  /**
   * Metodo che permette di utilizzare il prelevamento di tutti gli oggetti del PrenotazioneModel.
   *
   * @param body corpo della richiesta preso in input
   * @return Collezione di Prenotazione
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(value = "/prenotazioni", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public Collection<PrenotazioneBean> getAllPrenotazioni(@RequestBody String body)
          throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String order = jsonObject.get("ordinePrenotazioni").getAsString();
    return prenotazioneDaoInterface.doRetrieveAll(order);
  }

  /**
   * Metodo che permette di utilizzare l'inserimento di una nuova prenotazione tramite
   * PrenotazioneModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   * @throws ParseException per problemi di parse
   * @throws ErrorNewObjectException per problemi di creazione di un oggetto
   * @return conferma/non conferma del salvataggio della prenotazione
   */
  @PostMapping(value = "/newPrenotazione", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public boolean newPrenotazione(@RequestBody String body) throws SQLException,
          ParseException, ErrorNewObjectException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String cf = jsonObject.get("newPrenotazioniCf").getAsString();
    String idOperazione = jsonObject.get("newPrenotazioniIdOp").getAsString();
    String idStruttura = jsonObject.get("newPrenotazioniIdS").getAsString();


    StrutturaBean strutturaBean;
    OperazioneBean operazioneBean;
    UtenteBean utenteBean;
    strutturaBean = strutturaDaoInterface.doRetrieveByKey(Integer.valueOf(idStruttura));
    operazioneBean = operazioneDaoInterface.doRetrieveByKey(Integer.valueOf(idOperazione));
    utenteBean = utenteDaoInterface.doRetrieveByKey(cf);

    boolean checkStruttura = strutturaBean != null;
    boolean checkOperazione = operazioneBean != null;
    boolean checkUtente = utenteBean != null;

    String data = jsonObject.get("newPrenotazioneData").getAsString();
    java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(data);
    java.sql.Date dataPrenotazione = new Date(tmp.getTime());
    LocalDate oggi = LocalDate.now();
    boolean checkDate = dataPrenotazione.toLocalDate().isBefore(oggi);

    String ora = jsonObject.get("newPrenotazioniOra").getAsString();
    if (checkDate) {
      return false;
    } else if (ora.equals("00:00:00")) {
      return false;
    } else if (checkOperazione && checkStruttura && checkUtente) {
      prenotazioneDaoInterface.doSave(new PrenotazioneBean(ora, dataPrenotazione, cf,
              Integer.valueOf(idOperazione), Integer.valueOf(idStruttura), false));
      return true;
    } else {
      throw new ErrorNewObjectException(new PrenotazioneBean());
    }
  }

  /**
   * Metodo che permette di utilizzare l'eliminazione di una prenotazione presente sul DB tramite
   * PrenotazioneModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(value = "/deletePrenotazione", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public void deletePrenotazione(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("deletePrenotazioniId").getAsString();
    int idInt = Integer.valueOf(id);
    prenotazioneDaoInterface.doDelete(prenotazioneDaoInterface.doRetrieveByKey(idInt));
  }

  /**
   * Metodo che permette di utilizzare l'aggiornamento di una prenotazione presente sul DB tramite
   * PrenotazioneModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   * @throws ParseException per problemi di parse
   * @throws ErrorNewObjectException per problemi di creazione dell'oggetto
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   * @return conferma/non conferma dell'aggiornamento
   */
  @PostMapping(value = "/updatePrenotazione", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public boolean updatePrenotazione(@RequestBody String body) throws SQLException,
          ParseException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("updatePrenotazioniId").getAsString();
    String cf = jsonObject.get("updatePrenotazioniCf").getAsString();
    String ora = jsonObject.get("updatePrenotazioniOra").getAsString();
    String idOperazione = jsonObject.get("updatePrenotazioniIdOp").getAsString();
    String idStruttura = jsonObject.get("updatePrenotazioniIdS").getAsString();
    String data = jsonObject.get("updatePrenotazioneData").getAsString();
    java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(data);
    java.sql.Date dataPrenotazione = new Date(tmp.getTime());
    Boolean cv = jsonObject.get("updatePrenotazioneConvalida").getAsBoolean();
    PrenotazioneBean prenotazioneBean =
            prenotazioneDaoInterface.doRetrieveByKey(Integer.valueOf(id));

    if (prenotazioneBean != null) {
      StrutturaBean strutturaBean;
      OperazioneBean operazioneBean;
      operazioneBean = operazioneDaoInterface.doRetrieveByKey(Integer.valueOf(idOperazione));
      strutturaBean = strutturaDaoInterface.doRetrieveByKey(Integer.valueOf(idStruttura));

      boolean checkCodFisc = cf.matches("[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");
      boolean checkOra = ora.matches("^([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$");

      if (checkCodFisc && checkOra && operazioneBean != null && strutturaBean != null) {
        prenotazioneBean.setDataPrenotazione(dataPrenotazione);
        prenotazioneBean.setConvalida(cv);
        prenotazioneBean.setCodiceFiscale(cf);
        prenotazioneBean.setIdStruttura(Integer.valueOf(idStruttura));
        prenotazioneBean.setIdOperazione(Integer.valueOf(idOperazione));
        prenotazioneBean.setOra(ora);
        prenotazioneDaoInterface.doUpdate(prenotazioneBean);
        return true;
      } else {
        throw new ErrorNewObjectException("Errore nell'aggiornamento della prenotazione");
      }
    } else {
      throw new ObjectNotFoundException(new PrenotazioneBean());
    }
  }

  /**
   * Metodo che permette di avere tutte le prenotazione di un utente in base al suo Codice Fiscale.
   *
   * @param body corpo della richiesta preso in input
   * @return Prenotazioni di quell'utente
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(value = "/prenotazioniUtente/{cf}", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public Collection<PrenotazioneBean> getPrenotazioniByCodFisc(@RequestBody String body)
          throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String cf = jsonObject.get("getPrenotazioniByCf").getAsString();
    return prenotazioneDaoInterface.getUtentePrenotazioni(cf);
  }

  /**
   * Metodo per la convalida della prenotazione.
   *
   * @param body corpo della richiesta preso in input
   * @return conferma/non conferma della convalida
   * @throws SQLException per problemi di esecuzione della query
   * @throws ParseException per problemi di parse
   */
  @PostMapping(value = "/convalida", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public int convalidaPrenotazione(@RequestBody String body)
          throws SQLException, ParseException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String cf = jsonObject.get("convalidaPrenotazione").getAsString();

    //Prendo la prenotazione
    Collection<PrenotazioneBean> collection = prenotazioneDaoInterface.getUtentePrenotazioni(cf);
    if (collection == null) {
      return 0;
    }

    Iterator iter = collection.iterator();
    PrenotazioneBean prenotazioneBean = (PrenotazioneBean) iter.next();

    //Impostazioni variabili data, ora e codice fiscale
    prenotazioneBean.getDataPrenotazione();
    java.sql.Date d = prenotazioneBean.getDataPrenotazione();
    String ora = prenotazioneBean.getOra();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Long minHour =
            df.parse(d.toString() + " " + ora).getTime() - (1800 * 1000);
    Long maxHour =
            df.parse(d.toString() + " " + ora).getTime() + (600 * 1000);
    Long timeNow = System.currentTimeMillis();

    Date dateNow = new Date(timeNow);
    Date dateMin = new Date(minHour);
    Date dateMax = new Date(maxHour);

    if (!((dateNow.toLocalDate().getDayOfMonth() == d.toLocalDate().getDayOfMonth())
            && (dateNow.toLocalDate().getMonth() == d.toLocalDate().getMonth()))) {
      return 2;
    } else if (!dateNow.after(dateMin)) {
      return 3;
    } else if (!dateNow.before(dateMax)) {
      prenotazioneDaoInterface.doDelete(prenotazioneBean);
      return 4;
    } else if (prenotazioneBean.isConvalida()) {
      return 5;
    } else {
      prenotazioneBean.setConvalida(true);
      prenotazioneDaoInterface.doUpdate(prenotazioneBean);
      return 1;
    }
  }

  /**
   * Metodo per prelevare gli orari di prenotazione liberi.
   *
   * @param body Il contenuto della request
   * @return Collezione di orari
   * @throws SQLException per problemi di esecuzione della query
   * @throws ParseException per problemi di parse
   */

  @PostMapping(value = "/getOrari", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public List<String> getOrariDisponibili(@RequestBody String body)
          throws SQLException, ParseException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    int idStruttura = jsonObject.get("idStruttura").getAsInt();
    int idOperazione = jsonObject.get("idOperazione").getAsInt();
    String data = jsonObject.get("PrenotazioneData").getAsString();
    java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(data);
    java.sql.Date dataPrenotazione = new Date(tmp.getTime());

    return prenotazioneDaoInterface.getOrariPrenotazione(idStruttura,
            idOperazione, dataPrenotazione);

  }
}
