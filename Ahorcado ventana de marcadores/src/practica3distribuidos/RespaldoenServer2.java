
package practica3distribuidos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Newton
 */
public class RespaldoenServer2 extends Thread{
    
    public RespaldoenServer2(){
    }

    @Override
    public void run() {
        System.out.println("ServerRespaldo 1");
        int puerto = 9999;
        String direccion,hora;
        int suma;
        try{
            ServerSocket servidor = new ServerSocket(puerto);
            Jugador resJugador = new Jugador();
            for(;;){
                //Creamos un socket que atravez del ServerSocket
                Socket server2resp = servidor.accept();
                ObjectInputStream respenserv2 = new ObjectInputStream(server2resp.getInputStream());
                resJugador = (Jugador) respenserv2.readObject();
                direccion = resJugador.getDireccion();
                suma = resJugador.getSuma();
                hora = resJugador.getHora();
                PreparedStatement ps = null;
                ResultSet rs = null;
                Conexion2 conn = new Conexion2();
                Connection con = conn.getConnection();
                ps = con.prepareStatement("INSERT INTO jugador(direccionIp,suma,hora)VALUES(?,?,?)");
                ps.setString(1,direccion);
                ps.setInt(2,suma);
                ps.setString(3,hora);
                int res = ps.executeUpdate();
                if(res>0){
                    System.out.println("Jugador Respandado Correctamente en Server 2");
                }else{
                    System.out.println("Ocurrio un error");
                }
            }
        
        }catch (IOException ex) {
            Logger.getLogger(RespaldoenServer2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RespaldoenServer2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RespaldoenServer2.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}