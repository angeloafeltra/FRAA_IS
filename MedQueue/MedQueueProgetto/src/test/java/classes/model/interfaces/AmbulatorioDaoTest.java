package classes.model.interfaces;

import classes.model.bean.entity.AmbulatoriBean;
import classes.model.dao.AmbulatoriDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AmbulatorioDaoTest {
    private AmbulatoriDao ambulatorioDao = new AmbulatoriDao();

    @Test
    void doRetrieveByKey() throws SQLException {
        assertNotNull(ambulatorioDao.doRetrieveByKey(1));
    }

    @Test
    void doRetrieveAll() throws SQLException {
        assertNotNull(ambulatorioDao.doRetrieveAll("nome"));
    }

    @Test
    void doSave() throws SQLException {
        AmbulatoriBean ambulatoriBean = new AmbulatoriBean("Ababudoju", 1);
        ambulatorioDao.doSave(ambulatoriBean);
    }

    @Test
    void doUpdate() throws SQLException {
        AmbulatoriBean ambulatoriBean = new AmbulatoriBean("Ababudoja", 1);
        ambulatorioDao.doUpdate(ambulatoriBean);
    }

    @Test
    void doDelete() throws SQLException {
        AmbulatoriBean ambulatoriBean = new AmbulatoriBean("Ababudoja", 1);
        ambulatorioDao.doDelete(ambulatoriBean);
    }
}