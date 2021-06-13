package business;

import bean.ImpiegatoBean;
import bean.OperazioneBean;
import bean.PrenotazioneBean;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FacadeClassBusinessTest {
    private static FacadeClassBusiness test;
    private static int idValido;
    private static int idNonValido;

    @BeforeAll
    public static void startUp(){
        test=new FacadeClassBusiness();
        idValido=1;
        idNonValido=0;
    }

    @Test
    void autenticazione() {
        String strdate="1999-01-14";
        Date data=Date.valueOf(strdate);
        ImpiegatoBean impiegato=new ImpiegatoBean("FLTNGL99A14L845J","angelo99","angelo","afeltra",data,"a.afeltra12@studenti.unisa.it","3394487295",1);
        assertEquals(impiegato,test.autenticazione("FLTNGL99A14L845J", "angelo99"));
    }

    @Test
    void autenticazione_codiceFiscaleNonValido() {
        assertNull(test.autenticazione("FLTNGL99A14L84J","angelo99"));
    }

    @Test
    void autenticazione_passwordNonValida() {
        assertNull(test.autenticazione("FLTNGL99A14L84J",null));
    }

    @Test
    void accettaPrenotazione_idNonValido() {
        assertNull(test.accettaPrenotazione(idNonValido,idNonValido));
    }

    @Test
    void accettaPrenotazione_idValido() {
        String str = "2021-03-22";
        String str1 = "11:45:00";
        Date date = Date.valueOf(str);
        Time time = Time.valueOf(str1);
        PrenotazioneBean p = new PrenotazioneBean(4, date, time, true, "CRLNTN92S15H703Q", 2, 2);
        assertEquals(p,test.accettaPrenotazione(2,2));
    }

    @Test
    void getCode() {
        ArrayList<OperazioneBean> result=new ArrayList<OperazioneBean>();
        result.add(new OperazioneBean(1, "Pagamento Ticket", "Pagamento Ticket per visita medica"));
        result.add(new OperazioneBean(2, "Prenotazione Ambulatorio", "Richiesta prenotazione ambulatorio"));
        result.add(new OperazioneBean(3, "Da Rimuovere Dao", "Da rimuovere dao"));
        result.add(new OperazioneBean(4, "Da Rimuovere Controller", "Da rimuovere Controller"));
        assertEquals(result,test.getCode());
    }

    @Test
    void getCoda_idNonValido() {
        assertNull(test.getCoda(idNonValido));
    }

    @Test
    void getCoda_idValido() {
        OperazioneBean o =
                new OperazioneBean(1, "Pagamento Ticket", "Pagamento Ticket per visita medica");
        assertEquals(o, test.getCoda(idValido));
    }

    @Test
    void getSizeCoda_idNonValido() {
        assertEquals(0,test.getSizeCoda(idNonValido,idNonValido));
    }

    @Test
    void getSizeCoda_idValido() {
        assertNotNull( test.getSizeCoda(idValido, idValido));
    }

}