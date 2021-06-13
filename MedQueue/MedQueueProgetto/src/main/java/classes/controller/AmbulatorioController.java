package classes.controller;

import classes.controller.exception.ErrorNewObjectException;
import classes.controller.exception.InvalidKeyException;
import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.AmbulatoriBean;
import classes.model.bean.entity.StrutturaBean;
import classes.model.dao.AmbulatoriDao;
import classes.model.dao.StrutturaDao;
import classes.model.interfaces.AmbulatorioDaoInterface;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/** Classe per controllare i metodi del Model di Ambulatori. */
@RestController
public class AmbulatorioController {

  private final AmbulatorioDaoInterface ambulatorioModel = new AmbulatoriDao();
  private final StrutturaDao strutturaDao = new StrutturaDao();

  /**
   * Metodo che permette di utilizzare il prelevamento per id dell'AmbulatoriModel.
   *
   * @param body corpo della richiesta preso in input
   * @return Ambulatorio avente l'id passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException problemi di oggetto non trovato
   * @throws InvalidKeyException per problemi con la chiave primaria
   */
  @PostMapping(
      value = "/ambulatorio/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public AmbulatoriBean getById(@RequestBody String body)
      throws SQLException, ObjectNotFoundException, InvalidKeyException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    int id = jsonObject.get("id").getAsInt();

    if (id > 0) {
      AmbulatoriBean ambulatoriBean = ambulatorioModel.doRetrieveByKey(id);

      if (ambulatoriBean != null) {
        return ambulatoriBean;
      } else {
        throw new ObjectNotFoundException(new AmbulatoriBean());
      }
    } else {
      throw new InvalidKeyException("Id invalido, occorre un id maggiore di 0");
    }
  }

  /**
   * Metodo che permette di utilizzare il prelevamento di tutti gli oggetti dell'AmbulatoriModel.
   *
   * @param body corpo della richiesta preso in input
   * @return Collezione di Ambulatori
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(value = "/ambulatori", produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<AmbulatoriBean> getAllAmbulatori(@RequestBody String body)
          throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String order = jsonObject.get("ordineAmbulatori").getAsString();
    return ambulatorioModel.doRetrieveAll(order);
  }

  /**
   * Metodo che permette di utilizzare l'inserimento di un nuovo ambulatorio tramite
   * AmbulatorioModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   * @throws ErrorNewObjectException per problemi nell'input
   * @return conferma/non conferma del salvataggio dell'ambulatorio
   */
  @PostMapping(value = "/newAmbulatorio", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public boolean newAmbulatorio(@RequestBody String body) throws SQLException,
          ErrorNewObjectException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String idStruttura = jsonObject.get("newAmbulatorioIdS").getAsString();
    String nome = jsonObject.get("newAmbulatorioNome").getAsString();

    StrutturaBean strutturaBean = strutturaDao.doRetrieveByKey(Integer.valueOf(idStruttura));

    boolean checkNome = nome.matches("^[a-z A-Z ,.'-]+$");
    boolean checkIdStruttura = strutturaBean != null;

    if (checkNome && checkIdStruttura) {
      ambulatorioModel.doSave(new
              AmbulatoriBean(nome, Integer.valueOf(idStruttura)));
      return true;
    } else {
      throw new ErrorNewObjectException(new AmbulatoriBean());
    }
  }

  /**
   * Metodo che permette di utilizzare l'eliminazione di un ambulatorio presente sul DB tramite
   * AmbulatorioModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(value = "/deleteAmbulatorio", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public void deleteAmbulatorio(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("idAmbulatorioRemove").getAsString();
    ambulatorioModel.doDelete(ambulatorioModel.doRetrieveByKey(Integer.valueOf(id)));
  }

  /**
   * Metodo che permette di utilizzare l'aggiornamento di un ambulatorio presente sul DB tramite
   * AmbulatorioModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   * @return conferma/non conferma dell'aggiornamento dell'ambulatorio
   */
  @PostMapping(value = "/updateAmbulatorio", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public boolean updateAmbulatorio(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("idAmbulatorioUpdate").getAsString();
    String nome = jsonObject.get("AmbulatoriUpdateName").getAsString();
    String idStruttura = jsonObject.get("AmbulatoriUpdateIdStruttura").getAsString();
    AmbulatoriBean a = ambulatorioModel.doRetrieveByKey(Integer.valueOf(id));


    if (a != null) {
      StrutturaBean strutturaBean;
      strutturaBean = strutturaDao.doRetrieveByKey(Integer.valueOf(idStruttura));

      Boolean checkNome = nome.matches("^[a-z A-Z 0-9 ,.'-]+$");
      Boolean checkIdStruttura = strutturaBean != null;

      if (checkNome && checkIdStruttura) {
        a.setNome(nome);
        a.setIdStruttura(Integer.valueOf(idStruttura));
        ambulatorioModel.doUpdate(a);
        return true;
      }
      return false;
    } else {
      throw new ObjectNotFoundException(new AmbulatoriBean());
    }
  }
}
