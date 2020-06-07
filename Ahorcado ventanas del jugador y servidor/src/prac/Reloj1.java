package prac;


import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import prac.ModReloj;
import javax.swing.JButton;

public class Reloj1 extends javax.swing.JFrame {
    private String tiempo[];
    public static int segundero[];
    private static boolean on[];
    ModReloj mr;
    Thread  hilo[];
    int i;
    //Multicast
    public static InetAddress gpo = null;
    public static MulticastSocket s;
    /*//Arreglo para saber que reloje
    //Estan disponibles para el online
    //private static boolean reonline[];*/
    static DatagramPacket p = null;
    
    
    /*Arreglo con Todos los botones que muentran la hora
    Pa' modificarlos por bonche, mas fácil :)*/
    public static JButton BRelojes[];
    
    public static void setTime(String nvoTime, int noReloj, int seg, boolean b){
        BRelojes[noReloj].setText(nvoTime);
        
        //Reanudar el reloj
        on[noReloj] = true;
        
        //Nuevo valor del segundero
        segundero[noReloj] = seg;
        
        //Enviar el tiempo a los clientes
        //Si el servidor yá fue iniciado
        if(p!=null && b){
            p.setData((nvoTime+noReloj).getBytes());
            try {
                s.send(p);
                System.out.println("Envio "+nvoTime+noReloj );
            } catch (IOException ex) {
                Logger.getLogger(Reloj1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    //Si recibe un número de una dígito, lo regresa con un '0' a su izquierda
    public static String cadenaDig(int h){
            if(h<10) return new String("0"+h);
            else return new String(h+"");
    }
    
    public Reloj1() {
        initComponents();
        
        //reonline = new boolean[4];
        
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
            //Todos los relojes online disponibles
            //reonline[i]= false;
            
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
        setTime(hourFormat.format(date)+"", 0, 1000, false);
        
        //Asignar hora random a los demás relojes
        for(int j=1;j<4;j++)
            setTime(cadenaDig((int) (Math.random() * 23))+":"+
                    cadenaDig((int) (Math.random() * 59))+":"+
                    cadenaDig((int) (Math.random() * 59))
                    , j, 1000, false);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btne2 = new javax.swing.JButton();
        btne0 = new javax.swing.JButton();
        btne1 = new javax.swing.JButton();
        btne3 = new javax.swing.JButton();
        r1 = new javax.swing.JButton();
        r2 = new javax.swing.JButton();
        r3 = new javax.swing.JButton();
        r4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnini = new javax.swing.JButton();
        btnini1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        btne2.setText(">");
        btne2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btne2ActionPerformed(evt);
            }
        });

        btne0.setText(">");
        btne0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btne0ActionPerformed(evt);
            }
        });

        btne1.setText(">");
        btne1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btne1ActionPerformed(evt);
            }
        });

        btne3.setText(">");
        btne3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btne3ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        r1.setBackground(new java.awt.Color(255, 255, 255));
        r1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        r1.setText("00:00:00");
        r1.setToolTipText("");
        r1.setAutoscrolls(true);
        r1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        r1.setBorderPainted(false);
        r1.setContentAreaFilled(false);
        r1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        r1.setNextFocusableComponent(r2);
        r1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r1ActionPerformed(evt);
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

        jLabel2.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        jLabel2.setText("Servidor Reloj");

        btnini.setText("Iniciar Servidor");
        btnini.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btniniActionPerformed(evt);
            }
        });

        btnini1.setText("Iniciar Servidores Dependientes");
        btnini1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnini1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        jLabel3.setText("Servidor 1");

        jLabel4.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        jLabel4.setText("Servidor 3");

        jLabel5.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        jLabel5.setText("Servidor 2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(r3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(r4)
                        .addGap(57, 57, 57))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(r1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(r2)
                        .addGap(57, 57, 57))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(125, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnini1)
                        .addGap(133, 133, 133))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnini)
                        .addGap(173, 173, 173))))
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(68, 68, 68))
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(67, 67, 67))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(r1)
                    .addComponent(r2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(r3)
                    .addComponent(r4))
                .addGap(18, 18, 18)
                .addComponent(btnini)
                .addGap(12, 12, 12)
                .addComponent(btnini1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btniniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btniniActionPerformed
        btnini.setEnabled(false);
        Thread server;
        server = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                s = new MulticastSocket(4000);
                s.setReuseAddress(true);//Se hace reusable para que el mismo servidor se pueda escuchar a sí mismo
                s.setTimeToLive(2);
                gpo = InetAddress.getByName("228.1.1.1");
                s.joinGroup(gpo);//NOs unimos al canal multicast(Aunque seamos el server)
                System.out.println("Servidor iniciado ");
                
                //Recibir peticiones
                //Datagrama para recibir
                DatagramPacket rec = new DatagramPacket(new byte[20], 20);
                //Datagrama para enviar
                p = new DatagramPacket(tiempo[0].getBytes(),tiempo[0].getBytes().length,gpo,4000);
                
                while(true){
                    s.receive(rec);
                    //Si el datagrama es de un cliente
                    //Los clientes envían c cuando 
                    //Quieren unirse
                    String aux = new String(rec.getData());
                    if(aux.charAt(0)=='c'){
                        //System.out.println("Me llego " + new String(rec.getData()));
                        //De forma automática
                        /*//Si se acaban los lugares le envía al cliente NOPE
                        String aux = "NOPE";
                        for(int e=0;e<4;e++){
                            //Cuando encuentre un reloj disponible de concatena su no.
                            //Al final de la hora
                            if(!reonline[e]){
                                aux = tiempo[e]+e;
                                //Marcar como ocupado
                                reonline[e] = true;
                                break;
                            }
                        }*/
                        
                        //De forma manual
                        //Enviar la hora del número que pidió el usuario
                        p.setData(tiempo[Integer.parseInt(aux.substring(1,2))].getBytes());
                        s.send(p);
                    }
                }
                

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        server.start();
    }//GEN-LAST:event_btniniActionPerformed

    private void btne0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btne0ActionPerformed
        //Enviar el tiempo a los clientes
        //Si el servidor yá fue iniciado
        if(p!=null){
            p.setData((tiempo[0]+0).getBytes());
            try {
                s.send(p);
            } catch (IOException ex) {
                Logger.getLogger(Reloj1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btne0ActionPerformed

    private void btne2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btne2ActionPerformed
        //Enviar el tiempo a los clientes
        //Si el servidor yá fue iniciado
        if(p!=null){
            p.setData((tiempo[2]+2).getBytes());
            try {
                s.send(p);
            } catch (IOException ex) {
                Logger.getLogger(Reloj1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btne2ActionPerformed

    private void btne3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btne3ActionPerformed
        //Enviar el tiempo a los clientes
        //Si el servidor yá fue iniciado
        if(p!=null){
            p.setData((tiempo[3]+3).getBytes());
            try {
                s.send(p);
            } catch (IOException ex) {
                Logger.getLogger(Reloj1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btne3ActionPerformed

    private void btne1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btne1ActionPerformed
        //Enviar el tiempo a los clientes
        //Si el servidor yá fue iniciado
        if(p!=null){
            p.setData((tiempo[1]+1).getBytes());
            try {
                s.send(p);
            } catch (IOException ex) {
                Logger.getLogger(Reloj1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btne1ActionPerformed

    private void btnini1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnini1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnini1ActionPerformed

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("resources/rel.png"));


        return retValue;
    }
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
            java.util.logging.Logger.getLogger(Reloj1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reloj1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reloj1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reloj1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reloj1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btne0;
    private javax.swing.JButton btne1;
    private javax.swing.JButton btne2;
    private javax.swing.JButton btne3;
    private javax.swing.JButton btnini;
    private javax.swing.JButton btnini1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JButton r1;
    public static javax.swing.JButton r2;
    public static javax.swing.JButton r3;
    public static javax.swing.JButton r4;
    // End of variables declaration//GEN-END:variables


}