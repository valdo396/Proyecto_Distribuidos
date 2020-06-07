package practica3distribuidos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Archivo {
    int suma = 0;
   public void leerArchivo(String name){ 
       Scanner archivo = null;
       try {
           archivo = new Scanner(new File(name));
       } catch (FileNotFoundException ex) {
         System.out.println("No se encontro el archivo");
       } 
       int num = 0;
       while(archivo.hasNext()){
           num = archivo.nextInt();
           suma = suma + num;
       }
   }

    public int getSuma() {
        return suma;
    }
   
}
