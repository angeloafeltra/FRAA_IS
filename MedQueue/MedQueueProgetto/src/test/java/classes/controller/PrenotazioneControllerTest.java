package classes.controller;

import classes.controller.exception.ErrorNewObjectException;
import classes.controller.exception.InvalidKeyException;
import classes.controller.exception.ObjectNotFoundException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PrenotazioneControllerTest {
    private JsonParser parser = new JsonParser();
    private PrenotazioneController prenotazioneController = new PrenotazioneController();
    private JsonElement jsonElement;
    private JsonObject rootObject;
    private JsonObject finalRootObject;

    @Test
    void getPrenotazioneById() throws SQLException, InvalidKeyException {
        jsonElement = parser.parse("{\"idPrenotazioneGet\":\"1\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(prenotazioneController.getPrenotazioneById(rootObject.toString()));

        jsonElement = parser.parse("{\"idPrenotazioneGet\":\"0\"}");
        rootObject = jsonElement.getAsJsonObject();
        finalRootObject = rootObject;
        InvalidKeyException invalidKeyException = assertThrows(InvalidKeyException.class, () -> {
            prenotazioneController.getPrenotazioneById(finalRootObject.toString());
        });

        jsonElement = parser.parse("{\"idPrenotazioneGet\":\"79813\"}");
        rootObject = jsonElement.getAsJsonObject();
        finalRootObject = rootObject;
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, () -> {
            prenotazioneController.getPrenotazioneById(finalRootObject.toString());
        });
    }

    @Test
    void getAllPrenotazioni() throws SQLException {
        jsonElement = parser.parse("{\"ordinePrenotazioni\":\"data\"}");
        rootObject = jsonElement.getAsJsonObject();
        prenotazioneController.getAllPrenotazioni(rootObject.toString());

        jsonElement = parser.parse("{\"ordinePrenotazioni\":\"\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(prenotazioneController.getAllPrenotazioni(rootObject.toString()));
    }

    @Test
    void newPrenotazione() throws SQLException, ParseException {
        jsonElement = parser.parse(
                "{\"newPrenotazioniCf\":\"MNDCMN97R22A509S\"," +
                        "\"newPrenotazioniOra\":\"15:30:00\"," +
                        "\"newPrenotazioniIdOp\":\"1\"," +
                        "\"newPrenotazioniIdS\":\"1\"," +
                        "\"newPrenotazioneData\":\"2021-02-13\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertFalse(prenotazioneController.newPrenotazione(rootObject.toString()));

        LocalDateTime dataPerOra = LocalDateTime.now();
        String data = dataPerOra.getYear() + "-" + dataPerOra.getMonthValue()
                + "-" + dataPerOra.getDayOfMonth();
        jsonElement = parser.parse(
                "{\"newPrenotazioniCf\":\"CNDVKM62S23B586F\"," +
                        "\"newPrenotazioniOra\":\"15:30:00\"," +
                        "\"newPrenotazioniIdOp\":\"1\"," +
                        "\"newPrenotazioniIdS\":\"1\"," +
                        "\"newPrenotazioneData\":\""+data+"\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(prenotazioneController.newPrenotazione(rootObject.toString()));

        jsonElement = parser.parse(
                "{\"newPrenotazioniCf\":\"ERRORE 1294571507\"," +
                        "\"newPrenotazioniOra\":\"15:30:00\"," +
                        "\"newPrenotazioniIdOp\":\"1\"," +
                        "\"newPrenotazioniIdS\":\"1\"," +
                        "\"newPrenotazioneData\":\""+data+"\"}");
        rootObject = jsonElement.getAsJsonObject();
        ErrorNewObjectException errorNewObjectException = assertThrows(ErrorNewObjectException.class, () -> {
            prenotazioneController.newPrenotazione(rootObject.toString());
        });
    }

    @Test
    void deletePrenotazione() throws SQLException {
        jsonElement = parser.parse("{\"deletePrenotazioniId\":\"9\"}");
        rootObject = jsonElement.getAsJsonObject();
        prenotazioneController.deletePrenotazione(rootObject.toString());
    }

    @Test
    void updatePrenotazione() throws SQLException, ParseException {
        jsonElement = parser.parse(
                "{\"updatePrenotazioniId\":\"7\"," +
                        "\"updatePrenotazioniCf\":\"SQLRFL97R10F839D\"," +
                        "\"updatePrenotazioniOra\":\"09:30:00\"," +
                        "\"updatePrenotazioniIdOp\":\"1\"," +
                        "\"updatePrenotazioniIdS\":\"1\"," +
                        "\"updatePrenotazioneData\":\"2021-03-11\","+
                        "\"updatePrenotazioneConvalida\":\"false\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(prenotazioneController.updatePrenotazione(rootObject.toString()));

        jsonElement = parser.parse(
                "{\"updatePrenotazioniId\":\"7\"," +
                        "\"updatePrenotazioniCf\":\"SQLRFL97R10F839D\"," +
                        "\"updatePrenotazioniOra\":\"9:30:00\"," +
                        "\"updatePrenotazioniIdOp\":\"1\"," +
                        "\"updatePrenotazioniIdS\":\"1\"," +
                        "\"updatePrenotazioneData\":\"2021-03-11\","+
                        "\"updatePrenotazioneConvalida\":\"false\"}");
        rootObject = jsonElement.getAsJsonObject();
        ErrorNewObjectException errorNewObjectException = assertThrows(ErrorNewObjectException.class, () -> {
            prenotazioneController.updatePrenotazione(rootObject.toString());
        });

        jsonElement = parser.parse(
                "{\"updatePrenotazioniId\":\"789456\"," +
                        "\"updatePrenotazioniCf\":\"DRGMRA99D09A509V\"," +
                        "\"updatePrenotazioniOra\":\"12:00:00\"," +
                        "\"updatePrenotazioniIdOp\":\"1\"," +
                        "\"updatePrenotazioniIdS\":\"1\"," +
                        "\"updatePrenotazioneData\":\"13-02-2021\","+
                        "\"updatePrenotazioneConvalida\":\"false\"}");
        rootObject = jsonElement.getAsJsonObject();
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, () -> {
            prenotazioneController.updatePrenotazione(rootObject.toString());
        });
    }

    @Test
    void getPrenotazioniByCodFisc() throws SQLException {
        jsonElement = parser.parse("{\"getPrenotazioniByCf\":\"MNDCMN97R22A509S\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(prenotazioneController.getPrenotazioniByCodFisc(rootObject.toString()));
    }

    @Test
    void convalidaPrenotazione() throws SQLException, ParseException {
        //Convalida possibile
        LocalDateTime dataPerOra = LocalDateTime.now();
        String data = dataPerOra.getYear() + "-" + dataPerOra.getMonthValue()
                + "-" + dataPerOra.getDayOfMonth();
        String now = dataPerOra.getHour() + ":" + dataPerOra.getMinute() + ":" + dataPerOra.getSecond();
        jsonElement = parser.parse(
                "{\"newPrenotazioniCf\":\"DRGMRA99D09A509V\"," +
                        "\"newPrenotazioniOra\":\""+now+"\"," +
                        "\"newPrenotazioniIdOp\":\"1\"," +
                        "\"newPrenotazioniIdS\":\"1\"," +
                        "\"newPrenotazioneData\":\""+data+"\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(prenotazioneController.newPrenotazione(rootObject.toString()));

        jsonElement = parser.parse("{\"convalidaPrenotazione\":\"DRGMRA99D09A509V\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(prenotazioneController.convalidaPrenotazione(rootObject.toString()));

        //Convalida già eseguita
        assertNotNull(prenotazioneController.convalidaPrenotazione(rootObject.toString()));

        //Convalida non possibile
        jsonElement = parser.parse("{\"convalidaPrenotazione\":\"CCCNTN98H02F839V\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(prenotazioneController.convalidaPrenotazione(rootObject.toString()));

        // Convalida troppo presto
        LocalDateTime dataScadutaLocal = LocalDateTime.now().plusMinutes(40);
        String presto = dataScadutaLocal.getHour() + ":" + dataScadutaLocal.getMinute() + ":" + dataScadutaLocal.getSecond();
        jsonElement = parser.parse(
                "{\"newPrenotazioniCf\":\"CRLNTN92S15H703Z\"," +
                        "\"newPrenotazioniOra\":\""+presto+"\"," +
                        "\"newPrenotazioniIdOp\":\"1\"," +
                        "\"newPrenotazioniIdS\":\"1\"," +
                        "\"newPrenotazioneData\":\""+data+"\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(prenotazioneController.newPrenotazione(rootObject.toString()));

        jsonElement = parser.parse("{\"convalidaPrenotazione\":\"CRLNTN92S15H703Z\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(prenotazioneController.convalidaPrenotazione(rootObject.toString()));

        // Convalida troppo tardi
        LocalDateTime dataTardi = LocalDateTime.now().minusMinutes(25);
        String troppoTardi = dataTardi.getHour() + ":" + dataTardi.getMinute() + ":" + dataTardi.getSecond();
        jsonElement = parser.parse(
                "{\"newPrenotazioniCf\":\"MNDCMN97R22A509Y\"," +
                        "\"newPrenotazioniOra\":\""+troppoTardi+"\"," +
                        "\"newPrenotazioniIdOp\":\"1\"," +
                        "\"newPrenotazioniIdS\":\"1\"," +
                        "\"newPrenotazioneData\":\""+data+"\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(prenotazioneController.newPrenotazione(rootObject.toString()));

        jsonElement = parser.parse("{\"convalidaPrenotazione\":\"MNDCMN97R22A509Y\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(prenotazioneController.convalidaPrenotazione(rootObject.toString()));

        //Codice Fiscale falso
        jsonElement = parser.parse("{\"convalidaPrenotazione\":\"FALSOHAHA\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(prenotazioneController.convalidaPrenotazione(rootObject.toString()));
    }

    @Test
    void getOrariDisponibili() throws SQLException, ParseException {
        jsonElement = parser.parse("{\"idStruttura\":\"1\","
                + "\"idOperazione\":\"1\","
                + "\"PrenotazioneData\":\"2021-01-22\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(prenotazioneController.getOrariDisponibili(rootObject.toString()));
    }
}