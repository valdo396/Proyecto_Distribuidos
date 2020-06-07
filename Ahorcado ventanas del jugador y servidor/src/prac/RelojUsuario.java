//Agregar segundero
package prac;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static prac.Reloj.BRelojes;
import static prac.Reloj.cadenaDig;
import static prac.Reloj.gpo;
import static prac.Reloj.s;
import static prac.Reloj.segundero;


public class RelojUsuario extends javax.swing.JFrame {

    InetAddress gpo = null;
    MulticastSocket cl;
    private String tiempo;
    public static int segundero =1000;
    Thread  hilo, canal;
    //Número de reloj que tiene asignado
    private String nr;
    
    public RelojUsuario() {
        initComponents();
    }
    
    //CÓDIGO
    public static void setTime(String nvoTime, int seg){
        //Nuevo valor de tiempo
        lbr.setText(nvoTime);
        
        //Nuevo valor del segundero
        segundero = seg;
    }
    //CÓDIGO


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbh = new javax.swing.JLabel();
        lbr = new javax.swing.JLabel();
        btnini = new javax.swing.JButton();
        spn = new javax.swing.JSpinner();
        lb = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Interfaz del Usuario");
        setBackground(new java.awt.Color(0, 204, 204));

        lbh.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lbh.setText("Jugador");

        lbr.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbr.setText("00:00:00");

        btnini.setText("Iniciar");
        btnini.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btniniActionPerformed(evt);
            }
        });

        spn.setModel(new javax.swing.SpinnerNumberModel(0, 0, 3, 1));
        spn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lb.setText("Servidor");

        jButton1.setText("Enviar Letra");

        jButton2.setText("Enviar Palabra");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbr)
                .addGap(122, 122, 122))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbh)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lb)
                        .addGap(18, 18, 18)
                        .addComponent(spn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(btnini))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lbh)
                .addGap(50, 50, 50)
                .addComponent(lbr)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb)
                    .addComponent(spn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnini))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbr.setVisible(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btniniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btniniActionPerformed
        try{
            cl = new MulticastSocket(4000);
            System.out.println("Cliente escuchando puerto "+cl.getLocalPort());
            cl.setReuseAddress(true);
            try{
                gpo=InetAddress.getByName("228.1.1.1"); //Puede entrar dentro de un rango no válido
            }catch(UnknownHostException u){
                System.err.println("Dirección no válida");
            }
            cl.joinGroup(gpo);
            System.out.println("Unido al grupo");
        
            //Numero de cliente
            //String cli="c";
            nr = spn.getValue()+"";
            DatagramPacket envio = new DatagramPacket (("c"+nr).getBytes(), ("c"+nr).getBytes().length, gpo, 4000); 
            //Enviar una solicitud
            cl.send(envio);
            
            
            DatagramPacket p = new DatagramPacket(new byte[20], 20);
            cl.receive(p);
            
            cl.receive(p);
            
            String m = new String(p.getData()).substring(0, 8);
            /*//Obtener el número que el servidor le asignó a este usuario
            nr = new String(p.getData()).substring(8, 9);
            System.out.println("Mensaje "+ nr);*/
            lbr.setText(m);
            
            lbr.setVisible(true);
            btnini.setVisible(false);
            spn.setVisible(false);
            lb.setVisible(false);
            lbh.setText("Jugador "+(nr+2));
            
            //Cachar las actualizaciones del server
            canal = new Thread(new Runnable(){
                @Override
                public void run(){
                    while(true){
                        DatagramPacket rec = new DatagramPacket(new byte[20], 20);
                        try {
                            cl.receive(rec);
                            String aux = new String(rec.getData());
                            //Recibir mensajes solo de su numero de reloj
                            if(aux.charAt(0)!='c' && aux.charAt(8)==nr.charAt(0)){
                                setTime(aux.substring(0,8),1000);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(RelojUsuario.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            
            
            //Iniciar el reloj
            hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int seg, min, hor;
                        while(true){
                                tiempo = lbr.getText();

                                seg = Integer.parseInt(tiempo.substring(tiempo.lastIndexOf(":")+1));
                                min = Integer.parseInt(tiempo.substring(tiempo.indexOf(":")+1,tiempo.lastIndexOf(":")));
                                hor = Integer.parseInt(tiempo.substring(0,2));
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

                                lbr.setText(cadenaDig(hor)+":"+cadenaDig(min)+":"+cadenaDig(seg));
                                Thread.sleep(segundero);
                        }
                        } catch (Exception ex) {
                            System.out.println("Error en hilo: "+ex);
                        }
                }
            });
            
            hilo.start();
            canal.start();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_btniniActionPerformed

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
            java.util.logging.Logger.getLogger(RelojUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelojUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelojUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelojUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RelojUsuario().setVisible(true);
            }
        });
    }
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("resources/rel.png"));


        return retValue;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnini;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbh;
    private static javax.swing.JLabel lbr;
    private javax.swing.JSpinner spn;
    // End of variables declaration//GEN-END:variables
}
