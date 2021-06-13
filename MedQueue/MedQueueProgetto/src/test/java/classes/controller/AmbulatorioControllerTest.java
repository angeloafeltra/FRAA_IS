package classes.controller;

import classes.controller.exception.ErrorNewObjectException;
import classes.controller.exception.InvalidKeyException;
import classes.controller.exception.ObjectNotFoundException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AmbulatorioControllerTest {
    private JsonParser parser = new JsonParser();
    private AmbulatorioController ambulatorioController = new AmbulatorioController();
    private JsonElement jsonElement;
    private JsonObject rootObject;

    @Test
    void getById() throws SQLException, InvalidKeyException {
        jsonElement = parser.parse("{\"id\":\"1\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(ambulatorioController.getById(rootObject.toString()));

        jsonElement = parser.parse("{\"id\":\"0\"}");
        rootObject = jsonElement.getAsJsonObject();
        rootObject = rootObject;
        InvalidKeyException invalidKeyException = assertThrows(InvalidKeyException.class, () -> {
            ambulatorioController.getById(rootObject.toString());
        });

        jsonElement = parser.parse("{\"id\":\"54564163\"}");
        rootObject = jsonElement.getAsJsonObject();
        rootObject = rootObject;
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, () -> {
            ambulatorioController.getById(rootObject.toString());
        });
    }

    @Test
    void getAllAmbulatori() throws SQLException {
        jsonElement = parser.parse("{\"ordineAmbulatori\":\"idStruttura\"}");
        rootObject = jsonElement.getAsJsonObject();
        ambulatorioController.getAllAmbulatori(rootObject.toString());

        jsonElement = parser.parse("{\"ordineAmbulatori\":\"\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(ambulatorioController.getAllAmbulatori(rootObject.toString()));
    }

    @Test
    void newAmbulatorio() throws SQLException {
        jsonElement = parser.parse(
                "{\"newAmbulatorioNome\":\"NewAmbulatorio\",\"newAmbulatorioIdS\":\"1\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(ambulatorioController.newAmbulatorio(rootObject.toString()));

        jsonElement = parser.parse(
                "{\"newAmbulatorioNome\":\"NomeErrato1245\",\"newAmbulatorioIdS\":\"1\"}");
        rootObject = jsonElement.getAsJsonObject();
        ErrorNewObjectException errorNewObjectException = assertThrows(ErrorNewObjectException.class, () -> {
            ambulatorioController.newAmbulatorio(rootObject.toString());
        });
    }

    @Test
    void updateAmbulatorio() throws SQLException {
        jsonElement = parser.parse(
                "{\"idAmbulatorioUpdate\":\"1\",\""
                        + "AmbulatoriUpdateName\":\"Ortopedia Infantile\",\""
                        + "AmbulatoriUpdateIdStruttura\":\"1\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(ambulatorioController.updateAmbulatorio(rootObject.toString()));

        jsonElement = parser.parse(
                "{\"idAmbulatorioUpdate\":\"1\",\""
                        + "AmbulatoriUpdateName\":\"Errore 12\",\""
                        + "AmbulatoriUpdateIdStruttura\":\"21341\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertFalse(ambulatorioController.updateAmbulatorio(rootObject.toString()));

        jsonElement = parser.parse(
                "{\"idAmbulatorioUpdate\":\"784524\",\""
                        + "AmbulatoriUpdateName\":\"Ortopedia Infantile\",\""
                        + "AmbulatoriUpdateIdStruttura\":\"1\"}");
        rootObject = jsonElement.getAsJsonObject();
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, () -> {
            ambulatorioController.updateAmbulatorio(rootObject.toString());
        });
    }

    @Test
    void deleteAmbulatorio() throws SQLException {
        jsonElement = parser.parse("{\"idAmbulatorioRemove\":\"11\"}");
        rootObject = jsonElement.getAsJsonObject();
        ambulatorioController.deleteAmbulatorio(rootObject.toString());
    }
}