package classes.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ViewCodaTest {

    @Test
    void getAllPrenotazioniByStruttura() throws SQLException {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse("{\"getAllPrenotazionyByStruttura\":\"1\"}");
        JsonObject rootObject = jsonElement.getAsJsonObject();
        ViewCoda viewCoda = new ViewCoda();
        assertNotNull(viewCoda.getAllPrenotazioniByStruttura(rootObject.toString()));
    }
}