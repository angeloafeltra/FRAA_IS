package classes.controller;

import classes.model.bean.entity.PrenotazioneBean;
import classes.model.dao.PrenotazioneDao;
import classes.model.interfaces.PrenotazioneDaoInterface;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** Classe per visualizzare la coda di una struttura. */
@RestController
public class ViewCoda {
  public final PrenotazioneDaoInterface prenotazioneModel = new PrenotazioneDao();

  /**
   * Metodo che permette di visualizzare la coda di una struttura tramite l'id.
   *
   * @param body corpo della richiesta preso in input
   * @return Coda della struttura
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(value = "/visualizzaCoda/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public Collection<PrenotazioneBean> getAllPrenotazioniByStruttura(@RequestBody String body)
      throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("getAllPrenotazionyByStruttura").getAsString();
    return prenotazioneModel.getCodaStruttura(Integer.valueOf(id));
  }
}
