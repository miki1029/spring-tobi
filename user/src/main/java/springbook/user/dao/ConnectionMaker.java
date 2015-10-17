package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by miki on 15. 10. 17..
 */
public interface ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
