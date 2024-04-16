package es.cesur.progprojectpok.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexión con la base de datos.
 */
public class DBConnection {

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(ConfigDB.URL, ConfigDB.USERNAME, ConfigDB.PASSWORD);
        }catch (SQLException e){
            return null;
        }
    }
}

