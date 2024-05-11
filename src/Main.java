import org.apache.log4j.Logger;
import java.sql.*;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        String create = "DROP TABLE IF EXISTS ANIMALES; CREATE TABLE ANIMALES(ID INT AUTO_INCREMENT PRIMARY KEY, NOMBRE VARCHAR(20) NOT NULL, TIPO VARCHAR(20) NOT NULL)";
        String insert = "INSERT INTO ANIMALES(NOMBRE, TIPO) VALUES ('Firulais', 'Perro'), ('Lola', 'Vaca'), ('Homero', 'Perro'), ('Pepe', 'Sapo'), ('Tuki', 'Loro')";

        String select = "SELECT * FROM ANIMALES";

        Connection connection = null;

        try{
            connection = getConnection();

            //crear la tabla
            Statement statement = connection.createStatement();
            statement.execute(create);

            //insertar los registros
            statement.execute(insert);

            //select all
            ResultSet resultSet = statement.executeQuery(select);

            //recorrer el result set
            while (resultSet.next()){
                logger.info("Animal: " + resultSet.getString("id") + " - " + resultSet.getString(2) + " - " + resultSet.getString("tipo"));
            }

            logger.info("-----------------------------------------------------");
            //eliminar un registro
            statement.execute("DELETE FROM ANIMALES WHERE ID = 1");

            //select all
            resultSet = statement.executeQuery(select);
            //recorrer el result set
            while (resultSet.next()){
                logger.info("Animal: " + resultSet.getString("id") + " - " + resultSet.getString(2) + " - " + resultSet.getString("tipo"));
            }



        }catch (Exception exception){
            exception.printStackTrace();
            logger.error(exception.getClass() + ": " + exception.getMessage());
        }

        finally {
            try{
                connection.close();
            } catch (Exception exception){
                logger.error(exception.getMessage());
            }
        }

    }
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        //indicar que driver vamos a usar
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/clase8c1b2", "sa", "sa");

    }
}
