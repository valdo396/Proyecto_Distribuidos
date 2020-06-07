package prac;


import java.awt.Graphics;
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

public class Reloj extends javax.swing.JFrame {
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
                System.out.println("Servidor 2"+" Envio "+nvoTime+noReloj + " Direccion: " + gpo);
            } catch (IOException ex) {
                Logger.getLogger(Reloj.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    //Si recibe un número de una dígito, lo regresa con un '0' a su izquierda
    public static String cadenaDig(int h){
            if(h<10) return new String("0"+h);
            else return new String(h+"");
    }
    
    public Reloj() {
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


    /**]*¨\~
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btne0 = new javax.swing.JButton();
        r1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btne1 = new javax.swing.JButton();
        btne3 = new javax.swing.JButton();
        btne2 = new javax.swing.JButton();
        r2 = new javax.swing.JButton();
        r3 = new javax.swing.JButton();
        r4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnini = new javax.swing.JButton();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        label5 = new java.awt.Label();
        btnini1 = new javax.swing.JButton();
        r5 = new javax.swing.JButton();
        label6 = new java.awt.Label();

        btne0.setText(">");
        btne0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btne0ActionPerformed(evt);
            }
        });

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

        jLabel2.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        jLabel2.setText("Local");

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

        btne2.setText(">");
        btne2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btne2ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Servidores");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

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

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setToolTipText("");

        btnini.setText("Iniciar Servidor Reloj");
        btnini.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btniniActionPerformed(evt);
            }
        });

        label3.setText("Servidor 2");

        label4.setText("Servidor 1");

        label5.setText("Servidor Reloj");

        btnini1.setText("Iniciar Servidores Dependientes");
        btnini1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnini1ActionPerformed(evt);
            }
        });

        r5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        r5.setText("00:00:00");
        r5.setBorderPainted(false);
        r5.setContentAreaFilled(false);
        r5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        r5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r5ActionPerformed(evt);
            }
        });

        label6.setText("Servidor 3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(btnini))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(btnini1)))
                .addGap(0, 150, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(r5)
                            .addComponent(r2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(r4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(102, 102, 102))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(71, 71, 71))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(r3)
                                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(56, 56, 56)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(r2)
                    .addComponent(r3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(r4)
                    .addComponent(r5))
                .addGap(12, 12, 12)
                .addComponent(btnini)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnini1)
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void paint(Graphics g){
       
    }
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
                System.out.println("Servidor 0 iniciado ");
                System.out.println("El coordinador es: 0 ");
                System.out.println("Los siguientes son:");
                System.out.println(" 1 2 ");/*
                System.out.println("Servidor 1 iniciado ");
                System.out.println("El coordinador es: 2 ");
                System.out.println("Los siguientes son:");
                System.out.println(" 2  ");
                System.out.println(" Id del servidor: 0 no recibida, promoviendo a coordinador con el id mas alto");
                System.out.println("Servidor 2 iniciado ");
                System.out.println("El coordinador es: 2 ");
                System.out.println("Los siguientes son:");
                System.out.println("  1 ");*/
                
                //System.out.println("Servidor 2"+" Envio "+nvoTime+noReloj + " Direccion: " + gpo);
                
                
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
                Logger.getLogger(Reloj.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Reloj.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Reloj.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Reloj.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btne1ActionPerformed

    private void btnini1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnini1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnini1ActionPerformed

    private void r5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r5ActionPerformed

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
            java.util.logging.Logger.getLogger(Reloj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reloj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reloj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reloj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reloj().setVisible(true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    public static javax.swing.JButton r1;
    public static javax.swing.JButton r2;
    public static javax.swing.JButton r3;
    public static javax.swing.JButton r4;
    public static javax.swing.JButton r5;
    // End of variables declaration//GEN-END:variables


}