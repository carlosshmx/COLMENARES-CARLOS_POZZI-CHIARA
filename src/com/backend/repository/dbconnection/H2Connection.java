//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backend.repository.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {
    public H2Connection() {
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/colmenares-pozzi", "sa", "sa");
    }

    public static void crearTablas() {
        Connection connection = null;

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/colmenares-pozzi;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception var9) {
                var9.printStackTrace();
            }

        }

    }
}
