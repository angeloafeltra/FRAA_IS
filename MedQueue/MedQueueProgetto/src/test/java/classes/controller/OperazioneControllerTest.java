package classes.controller;

import classes.controller.exception.ErrorNewObjectException;
import classes.controller.exception.InvalidKeyException;
import classes.controller.exception.ObjectNotFoundException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class OperazioneControllerTest {
    private JsonParser parser = new JsonParser();
    private JsonElement jsonElement;
    private JsonObject rootObject;
    private OperazioneController operazioneController = new OperazioneController();

    @Test
    void getOperazioneById() throws SQLException, InvalidKeyException {
        jsonElement = parser.parse("{\"idOperazioneGet\":\"1\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(operazioneController.getOperazioneById(rootObject.toString()));

        jsonElement = parser.parse("{\"idOperazioneGet\":\"545515151\"}");
        rootObject = jsonElement.getAsJsonObject();
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, () -> {
            operazioneController.getOperazioneById(rootObject.toString());
        });

        jsonElement = parser.parse("{\"idOperazioneGet\":\"0\"}");
        rootObject = jsonElement.getAsJsonObject();
        InvalidKeyException invalidKeyException = assertThrows(InvalidKeyException.class, () -> {
            operazioneController.getOperazioneById(rootObject.toString());
        });
    }

    @Test
    void getOperazioneByTipo() throws SQLException {
        jsonElement = parser.parse("{\"tipoOperazioneGet\":\"Prenotazione Ambulatorio\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(operazioneController.getOperazioneByTipo(rootObject.toString()));

        jsonElement = parser.parse("{\"tipoOperazioneGet\":\"Falsa Operazione\"}");
        rootObject = jsonElement.getAsJsonObject();
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, () -> {
            operazioneController.getOperazioneByTipo(rootObject.toString());
        });
    }


    @Test
    void getAllOperazioni() throws SQLException {
        jsonElement = parser.parse("{\"ordineOperazioni\":\"tipoOperazione\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(operazioneController.getAllOperazioni(rootObject.toString()));

        jsonElement = parser.parse("{\"ordineOperazioni\":\"\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(operazioneController.getAllOperazioni(rootObject.toString()));

        jsonElement = parser.parse("{}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(operazioneController.getAllOperazioni(rootObject.toString()));
    }

    @Test
    void newOperazione() throws SQLException {
        jsonElement = parser.parse(
            "{\"newOperazioneTipoOp\":\"Nuova Operazione\",\""
                + "newOperazioneDesc\":\"Operazione di prova\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(operazioneController.newOperazione(rootObject.toString()));

        jsonElement = parser.parse(
                "{\"newOperazioneTipoOp\":\"Operazione ERRATA1325323\",\""
                        + "newOperazioneDesc\":\"Errore131453\"}");
        rootObject = jsonElement.getAsJsonObject();
        ErrorNewObjectException errorNewObjectException = assertThrows(ErrorNewObjectException.class, () -> {
            operazioneController.newOperazione(rootObject.toString());
        });
    }

    @Test
    void deleteOperazione() throws SQLException {
        jsonElement = parser.parse("{\"idOperazioneRemove\":\"4\"}");
        rootObject = jsonElement.getAsJsonObject();
        operazioneController.deleteOperazione(rootObject.toString());
    }

    @Test
    void updateOperazione() throws SQLException {
        jsonElement = parser.parse(
                "{\"updateOperazioneTipoOp\":\"Pagamento Ticket Sanitario\",\""
                        + "updateOperazioneDesc\":\"Pagamento Ticket in vista di una visita medica\",\""
                        + "updateOperazioneId\":\"1\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(operazioneController.updateOperazione(rootObject.toString()));

        jsonElement = parser.parse(
                "{\"updateOperazioneTipoOp\":\"Pagamento Ticket Sanitario\",\""
                        + "updateOperazioneDesc\":\"Pagamento Ticket in vista di una visita medica\",\""
                        + "updateOperazioneId\":\"7846\"}");
        rootObject = jsonElement.getAsJsonObject();
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, () -> {
            operazioneController.updateOperazione(rootObject.toString());
        });
    }
}