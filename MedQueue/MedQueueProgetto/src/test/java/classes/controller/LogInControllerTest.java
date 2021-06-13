package classes.controller;

import classes.controller.exception.ErrorNewObjectException;
import classes.controller.exception.InvalidKeyException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class LogInControllerTest {
    private JsonParser parser = new JsonParser();
    private LogInController logInController = new LogInController();
    private JsonElement jsonElement;
    private JsonObject rootObject;

    @Test
    void login() throws SQLException {
        jsonElement = parser.parse("{\"username\":\"MNDCMN97R22A509S\",\"password\":\"Carmine97!\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(logInController.login(rootObject.toString()));

        jsonElement = parser.parse("{\"username\":\"ERRORE\",\"password\":\"ERRORE\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNull(logInController.login(rootObject.toString()));

        jsonElement = parser.parse("{\"username\":\"convalidaPortale\",\"password\":\"convalidaPortale1!\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(logInController.login(rootObject.toString()));
    }

    @Test
    void signup() throws SQLException, ParseException{
        //Errore Password
        jsonElement = parser.parse("{\"nomeNewUtente\":\"Antonio\"," +
                "\"cognomeNewUtente\":\"Esposito\"," +
                "\"codFiscNewUtente\":\"VLRFYV54S65A360L\"," +
                "\"passwordNewUtente\":\"ciao\"," +
                "\"numeroTelefonoNewUtente\":\"3271219447\"," +
                "\"dataDiNascitaNewUtente\":\"1929-10-31\"," +
                "\"emailNewUtente\":\"mazzate@paccari.it\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(logInController.signup(rootObject.toString()));

        //Errore altri campi
        jsonElement = parser.parse("{\"nomeNewUtente\":\"Bud\"," +
                "\"cognomeNewUtente\":\"Spencer\"," +
                "\"codFiscNewUtente\":\"ERRORECF\"," +
                "\"passwordNewUtente\":\"Fagiolih1!\"," +
                "\"numeroTelefonoNewUtente\":\"3271219447\"," +
                "\"dataDiNascitaNewUtente\":\"1929-10-31\"," +
                "\"emailNewUtente\":\"mazzate@paccari.it\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(logInController.signup(rootObject.toString()));

        //Nuovo utente
        jsonElement = parser.parse("{\"nomeNewUtente\":\"Bud\"," +
                "\"cognomeNewUtente\":\"Spencer\"," +
                "\"codFiscNewUtente\":\"DTSQJP55R30A119M\"," +
                "\"passwordNewUtente\":\"Fagiolih1!\"," +
                "\"numeroTelefonoNewUtente\":\"3271219447\"," +
                "\"dataDiNascitaNewUtente\":\"1929-10-31\"," +
                "\"emailNewUtente\":\"mazzate@paccari.it\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(logInController.signup(rootObject.toString()));


        //Utente Duplicato
        jsonElement = parser.parse("{\"nomeNewUtente\":\"Bud\"," +
                "\"cognomeNewUtente\":\"Spencer\"," +
                "\"codFiscNewUtente\":\"MNDCMN97R22A509S\"," +
                "\"passwordNewUtente\":\"Fagiolih1!\"," +
                "\"numeroTelefonoNewUtente\":\"3271219447\"," +
                "\"dataDiNascitaNewUtente\":\"1929-10-31\"," +
                "\"emailNewUtente\":\"mazzate@paccari.it\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(logInController.signup(rootObject.toString()));
    }
}