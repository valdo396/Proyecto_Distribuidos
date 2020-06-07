package practica3distribuidos;

import java.io.Serializable;

/**
 *
 * @author Newton
 */
public class Jugador implements Serializable{
    String direccion,hora;
    int suma;
    public Jugador(){
        
    }

    public String getDireccion() {
        return direccion;
    }

    public String getHora() {
        return hora;
    }

    public int getSuma() {
        return suma;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }
    
    
    
}
