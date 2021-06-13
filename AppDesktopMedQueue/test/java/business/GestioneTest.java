package business;

import bean.OperazioneBean;
import bean.PrenotazioneBean;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GestioneTest {
  private static Gestione test;


  @BeforeAll
  public static void setUp() {
    test = new Gestione();
  }

  @Test
  void accettazionePrenotazione_CodaNonSelezionata_1() {
    assertNull(test.accettaPrenotazione(null,null));
  }

  @Test
  void accettazionePrenotazione_PrenotazioneNonAccettata_2() {
    assertNull(test.accettaPrenotazione(1,null));
  }

  @Test
  void accettazionePrenotazione_PrenotazioneAccettata_3() {
    String str = "2021-03-22";
    String str1 = "12:00:00";
    Date date = Date.valueOf(str);
    Time time = Time.valueOf(str1);
    PrenotazioneBean p = new PrenotazioneBean(3, date, time, true, "CRLNTN92S15H703Q", 2, 1);
    assertEquals(p,test.accettaPrenotazione(2,1));
  }

  @Test
  void accettaPrenotazione_idNonValido(){
    assertNull(test.accettaPrenotazione(-1,-1));
  }

  @Test
  void getNumeroPrenotazioni_idNonValido(){
    assertEquals(0,test.getNumPrenotazioni(0,0));
  }

  @Test
  void getNumeroPrenotazioni_idValidoNonTrovato(){
    assertEquals(0,test.getNumPrenotazioni(99,99));
  }

  @Test
  void getNumeroPrenotazioni_idValido(){
    assertNotNull(test.getNumPrenotazioni(1,1));
  }

  @Test
  void getListaOperazioni(){
    ArrayList<OperazioneBean> result=new ArrayList<OperazioneBean>();
    result.add(new OperazioneBean(1, "Pagamento Ticket", "Pagamento Ticket per visita medica"));
    result.add(new OperazioneBean(2, "Prenotazione Ambulatorio", "Richiesta prenotazione ambulatorio"));
    result.add(new OperazioneBean(3, "Da Rimuovere Dao", "Da rimuovere dao"));
    result.add(new OperazioneBean(4, "Da Rimuovere Controller", "Da rimuovere Controller"));
    assertEquals(result,test.getListaOperazioni());
  }

  @Test
  void getOperazione_idNonValido(){
    assertNull(test.getOperazione(0));
  }

  @Test
  void getOperazione_idValidoNonTrovato(){
    assertNull(test.getOperazione(99));
  }

  @Test
  void getOperazione_idValido(){
    OperazioneBean o =
            new OperazioneBean(1, "Pagamento Ticket", "Pagamento Ticket per visita medica");
    assertEquals(o, test.getOperazione(1));
  }

    @Test
    void deletePrenotazioniScadute() {
    assertEquals(1,test.deletePrenotazioniScadute());
    }
}
