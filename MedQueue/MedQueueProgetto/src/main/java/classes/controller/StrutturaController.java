package classes.controller;

import classes.controller.exception.ErrorNewObjectException;
import classes.controller.exception.InvalidKeyException;
import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.StrutturaBean;
import classes.model.dao.StrutturaDao;
import classes.model.interfaces.StrutturaDaoInterface;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** Classe per controllare i metodi del Model di Struttura. */
@RestController
public class StrutturaController {
  private final StrutturaDaoInterface strutturaModel = new StrutturaDao();

  /**
   * Metodo che permette di utilizzare il prelevamento per id dello StrutturaModel.
   *
   * @param body corpo della richiesta preso in input
   * @return Struttura avente l'id passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   * @throws InvalidKeyException per problemi con la chiave primaria
   */
  @PostMapping(value = "/struttura/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public StrutturaBean getStrutturaById(@RequestBody String body)
          throws SQLException, ObjectNotFoundException, InvalidKeyException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("idStrutturaGet").getAsString();
    StrutturaBean strutturaBean = strutturaModel.doRetrieveByKey(Integer.valueOf(id));
    if (Integer.valueOf(id) > 0) {
      if (strutturaBean.getNome() != null) {
        return strutturaBean;
      } else {
        throw new ObjectNotFoundException(strutturaBean);
      }
    } else {
      throw new InvalidKeyException("Id invalido, occorre un id maggiore di 0");
    }
  }

  /**
   * Metodo che permette di utilizzare il prelevamento per id dello StrutturaModel.
   *
   * @param body corpo della richiesta preso in input
   * @return Struttura avente l'id passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   */
  @PostMapping(value = "/struttura/{nome}", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public StrutturaBean getStrutturaByNome(@RequestBody String body)
          throws SQLException, ObjectNotFoundException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String nome = jsonObject.get("nomeStrutturaGet").getAsString();
    StrutturaBean strutturaBean = strutturaModel.doRetrieveByName(nome);
    if (strutturaBean != null) {
      return strutturaBean;
    } else {
      throw new ObjectNotFoundException(new StrutturaBean());
    }
  }

  /**
   * Metodo che permette di utilizzare il prelevamento di tutti gli oggetti dello StrutturaModel.
   *
   * @param body corpo della richiesta preso in input
   * @return Collezione di Strutture
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(value = "/strutture", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public Collection<StrutturaBean> getAllStrutture(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    if (!body.equals("{}")) {
      String order = jsonObject.get("ordineStrutture").getAsString();
      return strutturaModel.doRetrieveAll(order);
    } else {
      String order = "";
      return strutturaModel.doRetrieveAll(order);
    }
  }

  /**
   * Metodo che permette di utilizzare l'inserimento di una nuova struttura tramite StrutturaModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   * @throws ErrorNewObjectException per errori di creazione di un nuovo oggetto
   * @return conferma/non conferma del salvataggio della struttura
   */
  @PostMapping(value = "/newStruttura", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public boolean newStruttura(@RequestBody String body) throws SQLException,
          ErrorNewObjectException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();

    String nome = jsonObject.get("newStrutturaNome").getAsString();
    String indirizzo = jsonObject.get("newStrutturaIndirizzo").getAsString();
    String numeroCell = jsonObject.get("newStrutturaNumeroCell").getAsString();

    boolean checkNome = nome.matches("[A-Za-z ]+$");
    boolean checkIndirizzo = indirizzo.matches("^[A-Za-z0-9\\-\\s,\\/]*$");
    boolean checkNumeroCell = numeroCell.matches("^[0-9]{10,12}");

    if (checkIndirizzo && checkNome && checkNumeroCell) {
      strutturaModel.doSave(new StrutturaBean(nome, indirizzo, numeroCell));
      return true;
    } else {
      throw new ErrorNewObjectException(new StrutturaBean());
    }
  }

  /**
   * Metodo che permette di utilizzare l'eliminazione di una struttura presente sul DB tramite
   * StrutturaModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(value = "/deleteStruttura", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public void deleteStruttura(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("deleteStrutturaId").getAsString();
    strutturaModel.doDelete(strutturaModel.doRetrieveByKey(Integer.valueOf(id)));
  }

  /**
   * Metodo che permette di utilizzare l'aggiornamento di una struttura presente sul DB tramite
   * StrutturaModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   * @return conferma/non conferma della modifica della struttura
   */
  @PostMapping(value = "/updateStruttura", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public boolean updateStruttura(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("updateStrutturaId").getAsString();
    StrutturaBean strutturaBean = strutturaModel.doRetrieveByKey(Integer.valueOf(id));
    String nome = jsonObject.get("updateStrutturaNome").getAsString();
    String indirizzo = jsonObject.get("updateStrutturaInd").getAsString();
    String numeroCell = jsonObject.get("updateStrutturaNumeroCell").getAsString();

    if (strutturaBean != null) {
      boolean checkNome = nome.matches("[A-Za-z ]+$");
      boolean checkIndirizzo = indirizzo.matches("^[A-Za-z0-9\\-\\s,\\/]*$");
      boolean checkNumeroCell = numeroCell.matches("^[0-9]{10,12}");

      if (checkIndirizzo && checkNome && checkNumeroCell) {
        strutturaBean.setNumeroDiTelefono(numeroCell);
        strutturaBean.setNome(nome);
        strutturaBean.setIndirizzo(indirizzo);
        strutturaModel.doUpdate(strutturaBean);
        return true;
      } else {
        return false;
      }
    } else {
      throw new ObjectNotFoundException(new StrutturaBean());
    }
  }
}
