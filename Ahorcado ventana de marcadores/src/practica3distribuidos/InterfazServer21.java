/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3distribuidos;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Newton
 */
public class InterfazServer21 extends javax.swing.JFrame implements Runnable{
    
    Thread h1;
     private String tiempo[];
    public String hora;
    public static int segundero[];
    private static boolean on[];
    ModReloj mr;
    Thread  hilo[];
    int i;

    public String getHora() {
        return hora;
    }
    
    /*Arreglo con Todos los botones que muentran la hora
    Pa' modificarlos por bonche, mas fácil :)*/
    public static JButton BRelojes[];
    
    public static void setTime(String nvoTime, int noReloj, int s){
        BRelojes[noReloj].setText(nvoTime);
        
        //Nuevo valor del segundero
        segundero[noReloj] = s;
        
          //Reanudar el reloj
        on[noReloj] = true;
    }
    
    
    //Si recibe un número de una dígito, lo regresa con un '0' a su izquierda
    public static String cadenaDig(int h){
            if(h<10) return new String("0"+h);
            else return new String(h+"");
    }
    /**
     * Creates new form InterfazServer
     */
    public InterfazServer21(){
        initComponents();
        //Llenar el arreglo de los botones
        Thread mihiloserver = new Thread(this);
        mihiloserver.start();
        Thread rserver2;
        rserver2 = new RespaldoenServer2();
        rserver2.start();
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBtnCargar = new javax.swing.JButton();
        r2 = new javax.swing.JButton();
        r4 = new javax.swing.JButton();
        r3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableJugadores = new javax.swing.JTable();
        r1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextServidor = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableJugadores1 = new javax.swing.JTable();

        jBtnCargar.setText("Cargar");
        jBtnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCargarActionPerformed(evt);
            }
        });

        r2.setBackground(new java.awt.Color(255, 255, 255));
        r2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        r2.setText("00:00:00");
        r2.setBorderPainted(false);
        r2.setContentAreaFilled(false);
        r2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        r2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r2ActionPerformed(evt);
            }
        });

        r4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        r4.setText("00:00:00");
        r4.setBorderPainted(false);
        r4.setContentAreaFilled(false);
        r4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        r4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r4ActionPerformed(evt);
            }
        });

        r3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        r3.setText("00:00:00");
        r3.setBorderPainted(false);
        r3.setContentAreaFilled(false);
        r3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        r3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r3ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Marcadores");

        jTableJugadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "228.1.1.1", "REAL", "18:23:582", "5/06/2020"},
                {"2", "228.1.1.2", "PROBLEM", "18:25:322", "5/06/2020"},
                {"3", "228.1.1.3", "HOWEVER", "18:26:192", "5/06/2020"},
                {"1", "228.1.1.2", "AROUND", "18:35:319", "5/06/2020"},
                {"2", "228.1.1.3", "POSSIBLE", "18:36:292", "5/06/2020"},
                {"3", "228.1.1.1", "CONSIDER", "18:36:512", "5/06/2020"},
                {"1", "228.1.1.2", "NUMBER", "18:44:112", "5/06/2020"},
                {"2", "228.1.1.1", "WHILE", "18:45:552", "5/06/2020"},
                {"3", "228.1.1.3", "PART", "18:46:117", "5/06/2020"},
                {"1", "228.1.1.3", "FACT", "18:56:117", "5/06/2020"}
            },
            new String [] {
                "Id", "IpOrigen", "Frase Jugada", "Hora de Inicio", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableJugadores);

        r1.setBackground(new java.awt.Color(255, 90, 209));
        r1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        r1.setText("19:08:04");
        r1.setToolTipText("");
        r1.setAutoscrolls(true);
        r1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        r1.setBorderPainted(false);
        r1.setContentAreaFilled(false);
        r1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        r1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Hora:");

        jTextServidor.setColumns(20);
        jTextServidor.setRows(5);
        jScrollPane2.setViewportView(jTextServidor);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Usuario");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Ganadores");

        jTableJugadores1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), "18:30:150", "5/06/2020"},
                { new Integer(1), "18:39:334", "5/06/2020"},
                { new Integer(3), "17:47:596", "5/06/2020"},
                { new Integer(3), "17:58:012", "5/06/2020"},
                { new Integer(3), "18:06:592", "5/06/2020"},
                { new Integer(2), "18:09:592", "5/06/2020"},
                { new Integer(1), "18:13:237", "5/06/2020"},
                { new Integer(1), "18:16:192", "5/06/2020"},
                { new Integer(2), "18:20:296", "5/06/2020"}
            },
            new String [] {
                "Id", "Hora", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableJugadores1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(r1))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(r1)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCargarActionPerformed
        // TODO add your handling code here:
        try{
            DefaultTableModel modelo = new DefaultTableModel();
            jTableJugadores.setModel(modelo);
            PreparedStatement ps = null; 
            ResultSet rs = null;
            Conexion2 conn = new Conexion2();
            Connection con = conn.getConnection();
            String sql = "SELECT idJugador,direccionIp,suma,hora FROM JUGADOR";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadcolumnas = rsMd.getColumnCount();
            modelo.addColumn("Id");
            modelo.addColumn("IpOrigen");
            modelo.addColumn("Suma");
            modelo.addColumn("Hora");
            while(rs.next()){
                Object[] filas;
                filas = new Object[cantidadcolumnas];
                for(int i=0;i<cantidadcolumnas;i++){
                    filas[i] = rs.getObject(i+1);
                }
                modelo.addRow(filas);
                
            }
        }catch (SQLException e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_jBtnCargarActionPerformed

    private void r1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r1ActionPerformed
        //Pausar el hilo
        //El método suspend esta depreciado xD
        on[0] = false;
        //Mandar tiempo y no de reloj al otr frame
        mr.setTime(tiempo[0], 0, segundero[0]);
        mr.setVisible(true);
    }//GEN-LAST:event_r1ActionPerformed

    private void r2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r2ActionPerformed
        on[1] = false;
        mr.setTime(tiempo[1], 1, segundero[1]);
        mr.setVisible(true);
    }//GEN-LAST:event_r2ActionPerformed

    private void r3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r3ActionPerformed
        on[2] = false;
        mr.setTime(tiempo[2], 2, segundero[2]);
        mr.setVisible(true);
    }//GEN-LAST:event_r3ActionPerformed

    private void r4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r4ActionPerformed
        on[3] = false;
        mr.setTime(tiempo[3], 3, segundero[3]);
        mr.setVisible(true);
    }//GEN-LAST:event_r4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazServer21.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazServer21.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazServer21.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazServer21.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazServer21().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCargar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableJugadores;
    private javax.swing.JTable jTableJugadores1;
    private javax.swing.JTextArea jTextServidor;
    public static javax.swing.JButton r1;
    public static javax.swing.JButton r2;
    public static javax.swing.JButton r3;
    public static javax.swing.JButton r4;
    // End of variables declaration//GEN-END:variables
    
    
    @Override
    public void run() {
        //Llenar el arreglo de los botones
        BRelojes = new JButton[4];
        BRelojes[0] = r1;
        BRelojes[1] = r2;
        BRelojes[2] = r3;
        BRelojes[3] = r4;
        
        //Instanciar la ventana de modificar
        mr = new ModReloj();
        tiempo = new String[4];
        segundero = new int[4];
        on = new boolean[4];
        hilo = new Thread[4];
        

        for(i=0;i<4;i++){
            //Tener la variable e evita que los hilos se declaren con el mismo valor de i = 3
            int e=i;
            
            //Encender todos los relojes
            on[i] = true;
            
            segundero[i] = 1000;
            
            //Código de cada reloj (Hilos)
            hilo[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int seg=0, min, hor;
                        while(true){
                            while(on[e]){

                                tiempo[e] = BRelojes[e].getText();
                                hora = BRelojes[0].getText();
                                seg = Integer.parseInt(tiempo[e].substring(tiempo[e].lastIndexOf(":")+1));
                                min = Integer.parseInt(tiempo[e].substring(tiempo[e].indexOf(":")+1,tiempo[e].lastIndexOf(":")));
                                hor = Integer.parseInt(tiempo[e].substring(0,2));
                                if(seg <59) seg++;
                                else{
                                    seg=0;
                                    if(min<59) min++;
                                    else{
                                        min=0;
                                        if(hor<23) hor++;
                                        else hor=0;
                                    }
                                }

                                BRelojes[e].setText(cadenaDig(hor)+":"+cadenaDig(min)+":"+cadenaDig(seg));
                                Thread.sleep(segundero[e]);
                            }
                            //NPI de por que no se reanuda el botón si no tiene código
                            System.out.println();
                        }
                        } catch (Exception ex) {
                            System.out.println("Error en hilo "+e+": "+ex);
                        }
                }
            });
            
            
            //Iniciar los hilos
            hilo[i].start();
        }
        
        //Obtener la hora local para el primer reloj
        Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        setTime(hourFormat.format(date)+"", 0, 1000);
        
        //Asignar hora random a los demás relojes
        for(int j=1;j<4;j++)
            setTime(cadenaDig((int) (Math.random() * 23))+":"+
                    cadenaDig((int) (Math.random() * 59))+":"+
                    cadenaDig((int) (Math.random() * 59))
                    , j, 1000);
        int puerto = 1234;
        //Conexion con1 = new Conexion();
        //Connection con2;
        
        try{
            //con2 = con1.getConnection();
            //Iniciar el servidor
            jTextServidor.setText("");
            System.out.println("Servidor Inicado...");
            ServerSocket servidor = new ServerSocket(puerto);
            for(;;){
                //Creamos un socket que atravez del ServerSocket
                Socket server = servidor.accept();
                //Guardamos la direccion donde proviene en Clente
                String direccion = server.getInetAddress().toString();
                //Aceptamos el flujo de datos enviados
                DataInputStream archivo = new DataInputStream(server.getInputStream());
                //Guadamos nombre y tamaño en variables Locales
                String nombre = archivo.readUTF();
                long tam = archivo.readLong();
                //Obtenemos la hora
                 String hora1;
                 //Reloj r = new Reloj();
                 //String hora2[] = r.getTiempo();
                 //System.out.println(hora2[0]);
                 
                //Declaramos la ruta a donde mover el archivo
                String path = "/Users/Newton/Desktop/Repositorio/"+direccion+"-"+nombre;
                FileOutputStream destino = new FileOutputStream(path);
                
                byte[] buffer = new byte[1024];
                int len;
                
                while((len=archivo.read(buffer))>0){
                    destino.write(buffer,0,len);
                }
                Archivo numeros = new Archivo();
                numeros.leerArchivo(path);
                int suma = numeros.getSuma();
              
                PreparedStatement ps = null;
                ResultSet rs = null;
                Conexion conn = new Conexion();
                Connection con = conn.getConnection();
                ps = con.prepareStatement("INSERT INTO jugador(direccionIp,suma,hora)VALUES(?,?,?)");
                ps.setString(1,direccion);
                ps.setInt(2,suma);
                String hora2 = hora;
                ps.setString(3,hora2);
                
                int res = ps.executeUpdate();
                
                if(res>0){
                    System.out.println("Jugador Almacenado Correctamente");
                }else{
                    System.out.println("Ocurrio un error");
                }
                
                System.out.println("------------------------------------");
                System.out.println("Archivo Recibido Correctamente");
                System.out.println("Archivo Recibido desde: "+ direccion);
                System.out.println("La suma es: "+ suma);
                System.out.println("La Hora es: "+hora2);
                jTextServidor.setText("Recibido Correctamente\n"+
                                      "Desde: "+ direccion+"\n"+
                                      "Suma: "+ suma+"\n"+
                                      "Hora: "+ hora2+"\n");
                server.close();
            }
        }catch(IOException ex){
            System.out.println(ex.toString());
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } 
    }
    
    
}
