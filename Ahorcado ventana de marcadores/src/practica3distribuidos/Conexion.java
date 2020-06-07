package practica3distribuidos;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.sql.DriverManager;

/**
 *
 * @author Isaac
 */
public class Conexion {
    private static Connection conn;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "root";
    private static final String url ="jdbc:mysql://localhost:3306/bdcordinador";
    
    public Conexion(){
        conn = null;
        try{
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url, user, password);
            if(conn!=null){
                System.out.println("Conexion Establecida...");
            }
        }catch(Exception e){
            System.out.println("Error al conectar"+e);
        }
    }
    
    public Connection getConnection(){
        return conn;
    }
    
    public void desconectar(){
        conn = null;
        if(conn == null){
            System.out.println("Conexion Terminada");
        }
    }
}
