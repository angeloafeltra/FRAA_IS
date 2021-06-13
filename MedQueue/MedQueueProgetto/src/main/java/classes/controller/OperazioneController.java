package classes.controller;

import classes.controller.exception.ErrorNewObjectException;
import classes.controller.exception.InvalidKeyException;
import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.OperazioneBean;
import classes.model.dao.OperazioneDao;
import classes.model.interfaces.OperazioneDaoInterface;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/** Classe per controllare i metodi del Model di Operazione. */
@RestController
public class OperazioneController {

  private final OperazioneDaoInterface operazioneModel = new OperazioneDao();

  /**
   * Metodo che permette di utilizzare il prelevamento per id dell'OperazioneModel.
   *
   * @param body corpo della richiesta preso in input
   * @return Operazione avente l'id passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   * @throws InvalidKeyException per problemi di chiave primaria
   */
  @PostMapping(value = "/operazione/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public OperazioneBean getOperazioneById(@RequestBody String body)
      throws SQLException, ObjectNotFoundException, InvalidKeyException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String idString = jsonObject.get("idOperazioneGet").getAsString();
    int id = Integer.valueOf(idString);

    if (id > 0) {
      OperazioneBean operazioneBean = operazioneModel.doRetrieveByKey(id);
      if (operazioneBean != null) {
        return operazioneBean;
      } else {
        throw new ObjectNotFoundException(new OperazioneBean());
      }
    } else {
      throw new InvalidKeyException("Id invalido, occorre un id maggiore di 0");
    }
  }

  /**
   * Metodo che permette di utilizzare il prelevamento per id dell'OperazioneModel.
   *
   * @param body corpo della richiesta preso in input
   * @return Operazione avente l'id passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   */
  @PostMapping(value = "/operazione/{tipo}", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public OperazioneBean getOperazioneByTipo(@RequestBody String body) throws SQLException,
          ObjectNotFoundException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String tipo = jsonObject.get("tipoOperazioneGet").getAsString();

    OperazioneBean operazioneBean = operazioneModel.doRetrieveByTipo(tipo);
    if (operazioneBean != null) {
      return operazioneBean;
    } else {
      throw new ObjectNotFoundException(new OperazioneBean());
    }
  }

  /**
   * Metodo che permette di utilizzare il prelevamento di tutti gli oggetti dell'OperazioneModel.
   *
   * @param body corpo della richiesta preso in input
   * @return Collezione di Operazioni
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(value = "/operazioni", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public Collection<OperazioneBean> getAllOperazioni(@RequestBody String body) throws
          SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();

    if (!jsonObject.toString().equals("{}")) {
      String order = jsonObject.get("ordineOperazioni").getAsString();
      return operazioneModel.doRetrieveAll(order);
    } else {
      String order = "";
      return operazioneModel.doRetrieveAll(order);
    }
  }

  /**
   * Metodo che permette di utilizzare l'inserimento di una nuova operazione tramite
   * OperazioneModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   * @throws ErrorNewObjectException per problemi di creazione di un oggetto
   */
  @PostMapping(value = "/newOperazione", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public boolean newOperazione(@RequestBody String body) throws SQLException,
          ErrorNewObjectException {

    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();

    String tipoOp = jsonObject.get("newOperazioneTipoOp").getAsString();
    String descrizione = jsonObject.get("newOperazioneDesc").getAsString();

    Boolean checkTipoOp = tipoOp.matches("[a-z A-Z]+$");
    Boolean checkDesc = descrizione.matches("[a-z A-Z]+$");
    if (checkDesc && checkTipoOp) {
      operazioneModel.doSave(new OperazioneBean(tipoOp, descrizione));
      return true;
    } else {
      throw new ErrorNewObjectException(new OperazioneBean());
    }
  }

  /**
   * Metodo che permette di utilizzare l'eliminazione di un operazione presente sul DB tramite
   * OperazioneModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(value = "/deleteOperazione", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public void deleteOperazione(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("idOperazioneRemove").getAsString();
    operazioneModel.doDelete(operazioneModel.doRetrieveByKey(Integer.valueOf(id)));
  }

  /**
   * Metodo che permette di utilizzare l'aggiornamento di un operazione presente sul DB tramite
   * OperazioneModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   * @return conferma/non conferma dell'aggiornamento dell'operazione
   */
  @PostMapping(value = "/updateOperazione", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public boolean updateOperazione(@RequestBody String body) throws SQLException,
          ObjectNotFoundException {

    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();

    String tipoOp = jsonObject.get("updateOperazioneTipoOp").getAsString();
    String descrizione = jsonObject.get("updateOperazioneDesc").getAsString();
    String id = jsonObject.get("updateOperazioneId").getAsString();

    OperazioneBean operazioneBean = operazioneModel.doRetrieveByKey(Integer.valueOf(id));

    if (operazioneBean != null) {
      Boolean checkTipoOp = tipoOp.matches("[a-z A-Z]+$");
      Boolean checkDesc = descrizione.matches("[a-z A-Z]+$");
      if (checkDesc && checkTipoOp) {
        operazioneBean.setTipoOperazione(tipoOp);
        operazioneBean.setDescrizione(descrizione);
        operazioneModel.doUpdate(operazioneBean);
        return true;
      } else {
        return false;
      }
    } else {
      throw new ObjectNotFoundException(new OperazioneBean());
    }
  }
}
