package classes.model.interfaces;

import classes.model.bean.entity.OperazioneBean;
import classes.model.dao.OperazioneDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class OperazioneDaoTest {
    private OperazioneDao operazioneDao = new OperazioneDao();

    @Test
    void doRetrieveByKey() throws SQLException {
        assertNotNull(operazioneDao.doRetrieveByKey(1));
    }

    @Test
    void doRetrieveByTipo() throws SQLException {
        assertNotNull(operazioneDao.doRetrieveByTipo("Pagamento Ticket"));
        assertNull(operazioneDao.doRetrieveByTipo("Tipo errato"));
    }

    @Test
    void doRetrieveAll() throws SQLException {
        assertNotNull(operazioneDao.doRetrieveAll(""));
    }

    @Test
    void doSave() throws SQLException {
        OperazioneBean operazioneBean = new OperazioneBean("Prova", "Inserita operazine di prova");
        operazioneDao.doSave(operazioneBean);
    }

    @Test
    void doUpdate() throws SQLException {
        OperazioneBean operazioneBean = operazioneDao.doRetrieveByTipo("Prova");
        operazioneBean.setTipoOperazione("ProvaModifica");
        operazioneDao.doUpdate(operazioneBean);
    }

    @Test
    void doDelete() throws SQLException {
        OperazioneBean operazioneBean = operazioneDao.doRetrieveByTipo("Da Rimuovere Dao");
        operazioneDao.doDelete(operazioneBean);
    }
}