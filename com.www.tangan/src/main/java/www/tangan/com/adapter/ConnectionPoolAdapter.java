package www.tangan.com.adapter;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Logger;

/**
 * @author tangan
 */
public class ConnectionPoolAdapter implements DataSource {
    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) {

        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) {

        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) {

        return false;
    }

    @Override
    public PrintWriter getLogWriter() {

        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) {

    }

    @Override
    public void setLoginTimeout(int seconds) {

    }

    @Override
    public int getLoginTimeout() {

        return 0;
    }

    @Override
    public Logger getParentLogger() {

        return null;
    }
}
