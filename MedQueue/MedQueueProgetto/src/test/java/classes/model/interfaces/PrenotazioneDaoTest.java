package classes.model.interfaces;

import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.PrenotazioneBean;
import classes.model.dao.PrenotazioneDao;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class PrenotazioneDaoTest {
    private PrenotazioneDao prenotazioneDao = new PrenotazioneDao();
    private PrenotazioneBean prenotazioneBean = new PrenotazioneBean();

    @Test
    void doRetrieveByKey() throws SQLException, ObjectNotFoundException {
        assertNotNull(prenotazioneDao.doRetrieveByKey(1));
        assertNull(prenotazioneDao.doRetrieveByKey(48389572));
    }

    @Test
    void doRetrieveAll() throws SQLException {
        assertNotNull(prenotazioneDao.doRetrieveAll(""));
    }

    @Test
    void doSave() throws SQLException {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date date = null;
        try {
            date = new Date(df.parse("05-04-2022").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        prenotazioneBean.setDataPrenotazione(date);
        prenotazioneBean.setOra("11:00:00");
        prenotazioneBean.setCodiceFiscale("MNDCMN97R22A509S");
        prenotazioneBean.setConvalida(false);
        prenotazioneBean.setIdStruttura(1);
        prenotazioneBean.setIdOperazione(1);
        prenotazioneDao.doSave(prenotazioneBean);
    }

    @Test
    void doUpdate() throws SQLException {
        prenotazioneBean = prenotazioneDao.doRetrieveByKey(2);
        prenotazioneBean.setIdStruttura(2);
        prenotazioneDao.doUpdate(prenotazioneBean);
    }

    @Test
    void doDelete() throws SQLException {
        prenotazioneBean = prenotazioneDao.doRetrieveByKey(8);
        prenotazioneDao.doDelete(prenotazioneBean);
    }

    @Test
    void getUtentePrenotazioni() throws SQLException {
        assertNotNull(prenotazioneDao.getUtentePrenotazioni("CCCNTN98H02F839V"));
        assertNull(prenotazioneDao.getUtentePrenotazioni("CCCNTN98H02F839Z"));
    }

    @Test
    void getCodaStruttura() throws SQLException {
        assertNotNull(prenotazioneDao.getCodaStruttura(1));
    }

    @Test
    void getOrariPrenotazione() throws SQLException {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date date = null;
        try {
            date = new Date(df.parse("03-22-2021").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertNotNull(prenotazioneDao.getOrariPrenotazione(1, 2, date));

        date = null;
        try {
            date = new Date(df.parse("03-22-2021").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertNotNull(prenotazioneDao.getOrariPrenotazione(5, 2, date));
    }
}