package persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DriverManagerConnectionPoolTest {
    private static DriverManagerConnectionPool test;

    @BeforeAll
    public static void startUp(){
        test=new DriverManagerConnectionPool();
    }

    @Test
    void createDbConnection() {
        assertNotNull(DriverManagerConnectionPool.createDbConnection());
    }

    @Test
    void getConnection() throws SQLException {
        assertNotNull(DriverManagerConnectionPool.getConnection());
    }

    @Test
    void releaseConnection() throws SQLException {
        Connection con=DriverManagerConnectionPool.createDbConnection();
        DriverManagerConnectionPool.releaseConnection(con);
    }
}