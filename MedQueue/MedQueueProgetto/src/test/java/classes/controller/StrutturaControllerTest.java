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

class StrutturaControllerTest {
    private JsonParser parser = new JsonParser();
    private StrutturaController strutturaController = new StrutturaController();
    private JsonElement jsonElement;
    private JsonObject rootObject;

    @Test
    void getStrutturaById() throws SQLException, InvalidKeyException {
        jsonElement = parser.parse("{\"idStrutturaGet\":\"1\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(strutturaController.getStrutturaById(rootObject.toString()));

        jsonElement = parser.parse("{\"idStrutturaGet\":\"0\"}");
        rootObject = jsonElement.getAsJsonObject();
        JsonObject finalRootObject = rootObject;
        InvalidKeyException invalidKeyException = assertThrows(InvalidKeyException.class, () -> {
            strutturaController.getStrutturaById(finalRootObject.toString());
        });
    }

    @Test
    void getStrutturaByNome() throws SQLException {
        jsonElement = parser.parse("{\"nomeStrutturaGet\":\"Rehab Center\"}");
        rootObject = jsonElement.getAsJsonObject();
        strutturaController.getStrutturaByNome(rootObject.toString());

        jsonElement = parser.parse("{\"nomeStrutturaGet\":\"Non trovato\"}");
        rootObject = jsonElement.getAsJsonObject();
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, () -> {
            strutturaController.getStrutturaByNome(rootObject.toString());
        });
    }

    @Test
    void getAllStrutture() throws SQLException {
        jsonElement = parser.parse("{\"ordineStrutture\":\"indirizzo\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(strutturaController.getAllStrutture(rootObject.toString()));

        jsonElement = parser.parse("{\"ordineStrutture\":\"\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(strutturaController.getAllStrutture(rootObject.toString()));

        jsonElement = parser.parse("{}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(strutturaController.getAllStrutture(rootObject.toString()));
    }

    @Test
    void newStruttura() throws SQLException {
        jsonElement = parser.parse(
                "{\"newStrutturaNome\":\"San Donatello\"," +
                        "\"newStrutturaIndirizzo\":\"Via Italia 10, Milano MI\"," +
                        "\"newStrutturaNumeroCell\":\"0818729112\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(strutturaController.newStruttura(rootObject.toString()));

        jsonElement = parser.parse(
                "{\"newStrutturaNome\":\"ERRORE !\"," +
                        "\"newStrutturaIndirizzo\":\"!!!!!!\"," +
                        "\"newStrutturaNumeroCell\":\"081872911584651\"}");
        rootObject = jsonElement.getAsJsonObject();
        ErrorNewObjectException errorNewObjectException = assertThrows(ErrorNewObjectException.class, () -> {
            strutturaController.newStruttura(rootObject.toString());
        });
    }

    @Test
    void deleteStruttura() throws SQLException {
        jsonElement = parser.parse("{\"deleteStrutturaId\":\"4\"}");
        rootObject = jsonElement.getAsJsonObject();
        strutturaController.deleteStruttura(rootObject.toString());
    }

    @Test
    void updateStruttura() throws SQLException {
        jsonElement = parser.parse(
                "{\"updateStrutturaId\":\"1\"," +
                        "\"updateStrutturaNome\":\"Santo Buono\"," +
                        "\"updateStrutturaInd\":\"Via Croce Verde 14, Napoli NA\"," +
                        "\"updateStrutturaNumeroCell\":\"0812205111\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(strutturaController.updateStruttura(rootObject.toString()));

        jsonElement = parser.parse(
                "{\"updateStrutturaId\":\"1\"," +
                        "\"updateStrutturaNome\":\"Errore 123\"," +
                        "\"updateStrutturaInd\":\"Via Croce Verde 14, Napoli NA\"," +
                        "\"updateStrutturaNumeroCell\":\"0812205111\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertFalse(strutturaController.updateStruttura(rootObject.toString()));

        jsonElement = parser.parse(
                "{\"updateStrutturaId\":\"789456\"," +
                        "\"updateStrutturaNome\":\"Santo Buono\"," +
                        "\"updateStrutturaInd\":\"Via Croce Verde 14, Napoli NA\"," +
                        "\"updateStrutturaNumeroCell\":\"0812205111\"}");
        rootObject = jsonElement.getAsJsonObject();
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, () -> {
            strutturaController.updateStruttura(rootObject.toString());
        });
    }
}