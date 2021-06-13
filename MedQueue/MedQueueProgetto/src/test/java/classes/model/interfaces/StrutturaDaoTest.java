package classes.model.interfaces;

import classes.model.bean.entity.StrutturaBean;
import classes.model.dao.StrutturaDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class StrutturaDaoTest {
    private StrutturaDao strutturaDao = new StrutturaDao();
    private StrutturaBean strutturaBean = new StrutturaBean();

    @Test
    void doRetrieveByKey() throws SQLException {
        assertNotNull(strutturaDao.doRetrieveByKey(1));
        assertNull(strutturaDao.doRetrieveByKey(1900428));
    }

    @Test
    void doRetrieveByName() throws SQLException {
        assertNotNull(strutturaDao.doRetrieveByName("San Leonardo"));
    }

    @Test
    void doRetrieveAll() throws SQLException {
        assertNotNull(strutturaDao.doRetrieveAll(""));
    }

    @Test
    void doSave() throws SQLException {
        strutturaBean.setIndirizzo("Via Ababudoju");
        strutturaBean.setNome("Ababudoju Center");
        strutturaBean.setNumeroDiTelefono("0874878584");
        strutturaDao.doSave(strutturaBean);
    }

    @Test
    void doUpdate() throws SQLException {
        strutturaBean = strutturaDao.doRetrieveByName("Ababudoju Center");
        strutturaBean.setNome("Rehab Center");
        strutturaBean.setIndirizzo("Via Shady Slim 12");
        strutturaDao.doUpdate(strutturaBean);
    }

    @Test
    void doDelete() throws SQLException {
        strutturaBean = strutturaDao.doRetrieveByName("Da Rimuovere Dao");
        strutturaDao.doDelete(strutturaBean);
    }
}