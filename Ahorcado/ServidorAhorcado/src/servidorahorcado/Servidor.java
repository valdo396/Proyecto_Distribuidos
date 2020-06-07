/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorahorcado;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Thread{
    
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
   
    private String[] diccionario = {/*"ABEJA", "AEROPUERTO", "COMPUTADOR", "OSO",
        "JAVA", "NEVERA", "PROGRAMA", "INFORMATICA", "COMPUTACION", "COMPUTADOR","CORAZON","BANANO","PLATANO",
        "AUTOMOVIL", "PERRO", "COLOMBIA", "LONDRES", "CEPILLO", "BRAZO", "CABEZA", "CUERPO","DEPORTE","SALUD",
        "ANONYMOUS", "CUADERNO", "PANTALLA", "UBUNTU", "SEMAFORO", "LINUX", "LOBO","AMOR","MOSCA","ZANAHORIA",
        "PINGUINO", "HACKER", "SISTEMA", "ELEFANTE", "CASCADA", "JUEGOS","LARGO","BONITO","IMPOSIBLE","UNIDOS","ZOMBIE",
        "MATEMATICAS", "CALCULO", "ALGEBRA", "DICCIONARIO", "BIBLIOTECA", "COCINA","PELICULA","COMERCIAL","GRANDE","PEQUEÑO",
        "GATO", "SAPO", "JIRAFA", "FERROCARRIL", "FACEBOOK", "PERSONA","BICICLETA","CONTROL","PANTALON","AEROSOL",
        "ERROR", "COPA", "COPA", "PROGRAMADOR", "LICENCIA", "NUEVE", "PROCESADOR","GARAJE","MODERNO","TABLA","DISCOTECA",
        "LENGUAJE", "PROGRAMACION", "HERRAMIENTAS", "INTERNET", "EJECUTAR", "PROYECTO","PERIODICO","COCODRILO","TORTUGA","CABALLO",
        "APLICACION", "PERA", "SOFTWARE", "ADMINISTRACION", "VENTANA", "MANTENIMIENTO","INFORMACION","PRESIDENTE","PERSONA","GENTE",
        "NARANJA", "PRUEBA", "MANZANA", "JARRA", "CELULAR", "TELEFONO","CONTAMINACION","COLOR","ROMANO","ADIVINAR","MARCADOR",
        "INSTRUCCION", "CUADERNO", "CASA", "PALA", "ARBOL", "PUENTE", "PAPEL", "HOJA","HELICOPTERO","BARCO","GOLF","CARRERA",
        "TUBERIA", "PLOMERO", "FUTBOL", "BALONCESTO", "ESTADIO", "JEAN", "FUENTE", "LEOPARDO","REGLA","PRIMERO","SEGUNDO",
        "BLUSA", "CAMISA", "AGUA", "FUEGO", "INDUSTRIA", "AIRE","TIERRA","NATURALEZA","MIERCOLES","FOTOGRAFIA","LEON",
        "TIGRE",*/"THE","OF","AND","A","TO","IN","HE","HAVE","IT","THAT","FOR","THEY","I","WITH","AS","NOT","ON","SHE",
"AT","BY","THIS","WE","YOU","DO","BUT", "FROM","OR","WHICH","ONE","WOULD","ALL","WILL","THERE","SAY","WHO","MAKE","WHEN",
"CAN","MORE","IF","NO","MAN","OUT","OTHER","SO","WHAT","TIME","UP","GO","ABOUT","THAN","INTO","COULD","STATE","ONLY"
,"NEW","YEAR","SOME","TAKE","COME","THESE","KNOW","SEE","USE","GET", "LIKE","THEN","FIRST","ANY","WORK","NOW","MAY",
"SUCH","GIVE","OVER","THINK","MOST","EVEN","FIND","DAY","ALSO","AFTER","WAY","MANY","MUST","LOOK","BEFORE","GREAT"
            ,"BACK","THROUGH","LONG","WHERE","MUCH","SHOULD","WELL","PEOPLE","DOWN","OWN","JUST","BECAUSE","GOOD"
            ,"EACH","THOSE","FEEL","SEEM","HOW", "HIGH","TOO","PLACE","LITTLE","WORLD","VERY","STILL","NATION","HAND",
"OLD","LIFE","TELL","WRITE","BECOME","HERE","SHOW","HOUSE","BOTH","BETWEEN","NEED","MEAN","CALL","DEVELOP","UNDER"
,"WHILE","NUMBER","PART","TURN","REAL","LEAVE","MIGHT","WANT","POINT","FORM","OFF","CHILD","FEW","SMALL","SINCE","AGAINST",
"ASK","LATE","HOME","INTEREST","LARGE","PERSON","END","OPEN","PUBLIC","FOLLOW","DURING","PRESENT","WITHOUT",
"AGAIN","HOLD","GOVERN","AROUND","POSSIBLE","HEAD","CONSIDER","WORD","PROGRAM","PROBLEM","HOWEVER","LEAD","SYSTEM"
            ,"SET","ORDER","EYE","PLAN","RUN","KEEP","FACE","FACT","GROUP","PLAY","STAND","INCREASE","EARLY"
            ,"COURSE","CHANGE","HELP","LINE"}; 
    
    private char[] palabra_secreta;
    
    public Servidor(Socket socket, int idSession)  {
        
        this.socket = socket;
        
        this.palabra_secreta = Random().toCharArray();
        
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            System.out.println(palabra_secreta);
            dos.writeUTF(String.valueOf(palabra_secreta));
           
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        
    }
    
    private String Random(){   
        int num = (int)(Math.random()*(diccionario.length));   
        return diccionario[num];
    }
    
    
    
}
