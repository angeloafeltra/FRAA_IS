package business;

import bean.ImpiegatoBean;
import bean.PrenotazioneBean;
import eccezioni.InvalidKeyException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class AccessoTest {
  private static Accesso test=new Accesso();

  @BeforeAll
  public static void setUp(){

  }


  @Test
  void AutenticazioneImpiegato_CodiceFiscale_NonValido_1()  {
    assertNull(test.verificaCredenziali("mario", null));
  }

  @Test
  void AutenticazioneImpiegato_CodiceFiscale_NonEsistenteNelDatabase_2() {
    assertNull(test.verificaCredenziali("MNDCMN97R22A509S", null));
  }

  @Test
  void AutenticazioneImpiegato_Password_NonRispettaIlFormato_3() {
    assertNull(test.verificaCredenziali("FLTNGL99A14L845J", "a"));
  }

  @Test
  void AutenticazioneImpiegato_Password_Errata_4() {
    assertNull(test.verificaCredenziali("FLTNGL99A14L845J", "angelo9"));
  }

  @Test
  void AutenticazioneImpiegato_Successo_5() {
    String strdate="1999-01-14";
    Date data=Date.valueOf(strdate);
    ImpiegatoBean impiegato=new ImpiegatoBean("FLTNGL99A14L845J","angelo99","angelo","afeltra",data,"a.afeltra12@studenti.unisa.it","3394487295",1);
    assertEquals(impiegato,test.verificaCredenziali("FLTNGL99A14L845J", "angelo99"));
  }

}
