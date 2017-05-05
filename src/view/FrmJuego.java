/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControlUI;
import java.awt.Color;
import java.awt.Component;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.EstadoJuego;
import model.ModelListener;

/**
 *
 * @author Roberto Pedraza Coello
 */
public class FrmJuego extends JFrame implements ModelListener {

    /**
     * Contiene el control del juego.
     */
    private ControlUI control;
    
    /**
     * Creates new form FrmJuego
     */
    public FrmJuego() {
        initComponents();
        setLocationRelativeTo(null);

        //Inicializamos cosas basicas..
        control = new ControlUI();
        EstadoJuego.getInstance().addModelListener(this);
        
        //Conectamos al servidor para que nos de nuestro turno..
        conectar();
    }

    /**
     * Intenta conectarse a el servidor de entrada, y muestra
     * la pantalla.
     */
    private void conectar() {
        if (control.conectar("localhost", 5039)) {
            setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar al servidor.");
            System.exit(0);
        }
    }

    /**
     * Este metodo es llamado cada vez que el modelo
     * haya sido actualizado.
     */
    @Override
    public void update(){
        EstadoJuego model = EstadoJuego.getInstance();
        Map<String, Object> data = model.getModelData();
        
        /**
         * Checamos si el juego ha sido iniciado.
         * Si este valor es verdadero, entonces todos los
         * demas datos no tienen porque ser nulos ya que se 
         * le deberian haber asignado un valor por medio del modelo.
         */
        if((Boolean) data.get("Juego Iniciado:")){
            activarCampos();
            /**
             * Vamos a obtener todos los datos posibles de el modelo y ponerlo
             * en variables.
             */
            String fraseActual = (String) data.get("FraseActual:");
            String turnoJugador = (String) data.get("Turno Jugador:");
            String segundosRestantes = String.valueOf(data.get("Segundos:"));
            EstadoMonito estado = (EstadoMonito) data.get("EstadoMono:");

            /**
             * Ahora vamos a actualizar la interfaz con los datos. No todos los
             * datos pueden haber cambiado, pero si uno fue cambiado se
             * reflejara en la pantalla.
             */
            labelTiempo.setText(segundosRestantes + " segundos.");
            /**
             * Seteamos quien sigue.
             */
            jlabelTurno.setText(turnoJugador);
            
            /**
             * Mostramos el panel de letras incorrectas.
             */
            mostrarLetras((Map<Character, Boolean>) data.get("Letras:"));
            /**
             * Cambiamos el color del panel dependiendo si es nuestro turno o
             * no.
             */
            cambiarColorPanel(turnoJugador);
            /**
             * Cambiamos la imagen del monito.
             */
            setImagenMonito(estado);
            /**
             * Cambiamos lo que se lleva adivinado de la frase.
             */
            cambiarFraseActual(fraseActual);
        }
        /**
         * Si todavia no ha sido iniciado, entonces checamos 
         * cuanta gente hay conectada para agregarla a la tabla
         * de personas conectadas.
         */
        else{
            int conectados = (Integer) data.get("Conectados:");
            
            /**
             * Checamos si tenemos que agregar un jugador a la tabla.
             */
            if(tablaJugadores.getRowCount() != conectados)
                cambiarTablaJugadoresConectados();
        }
    }
    
    /**
     * Metodo update del observer.
     * 
     * @param o
     * @param obj 
     */
//    @Override
//    public void update(Observable o, Object obj) {
//        /**
//         * Usualmente solo se recibira una string si un jugador
//         * se conecto.
//         */
//        if (obj instanceof String) {
//            String mensaje = (String) obj;
//
//            if(mensaje.contains("conectado"))
//                cambiarTablaJugadoresConectados();
//            else if(mensaje.contains("LetraAñadida:"))
//                panelLetras.add(new JLabel(mensaje.substring(13)));
//            else if(mensaje.contains("iniciado:")){
//                activarCampos();
//                
//                labelPalabra.setText(mensaje.substring(9));
//            }
//            else if(mensaje.contains("FraseActual:")){
//                String frase = mensaje.substring(12);
//                String texto = "";
//                
//                for(int i = 0; i < frase.length(); i++){
//                    Character letra = frase.charAt(i);
//                    
//                    texto+= letra + " ";
//                    
//                    if(letra == ' ')
//                        texto+= "   ";
//                }
//                
//                labelPalabra.setText(texto);
//            }
//            else if(mensaje.contains("cambio turno")){
//                jlabelTurno.setText(mensaje.substring(13));
//                
//                if(mensaje.substring(13).equalsIgnoreCase("(Te toca a ti adivinar!)"))
//                    jPanel4.setBackground(new Color(255, 204, 204));
//                else
//                    jPanel4.setBackground(new Color(240, 240, 240));
//            }
//            else if(mensaje.equalsIgnoreCase("Mono muerto")){
//                control.perderJuego();
//            }
//            else if(mensaje.equalsIgnoreCase("Gane")){
//                control.ganarJuego();
//            }
//        }
//        /**
//         * Indica si se ha avanzado un segundo
//         * en el contador.
//         */
//        else if(obj instanceof Integer){
//            int segundos = (Integer) obj;
//            
//            if(segundos <= 0){
//                /**
//                 * Si es nuestro turno y se nos acabo el tiempo,
//                 * tenemos que mandar el comando que notifica a los demas que hay un nuevo turno.
//                 */
//                if(EstadoJuego.getInstance().getTurno() == EstadoJuego.getInstance().getTurnoActual()){
//                    EstadoJuego.getInstance().enviarComando(new C_SiguienteTurno());
//                    EstadoJuego.getInstance().avanzarTurno();
//                }
//            }
//            else
//                labelTiempo.setText(segundos + " segundos");
//        }
//        /**
//         * Aqui se da si se tiene que cambiar el estado del
//         * monito en el tablero.
//         */
//        else if (obj instanceof EstadoMonito) {
//            setImagenMonito((EstadoMonito) obj);
//        }
//        /**
//         * Indica si es nuestro turno de poder
//         * adivinar una letra.
//         */
//        else if(obj instanceof Boolean){
//            setActivadoEnviar((Boolean) obj);
//        }
//    }

    /**
     * Llena la tabla de jugadores.
     * 
     * @param jugadoresConectados 
     */
    private void cambiarTablaJugadoresConectados(){
        String[] data = new String[]{"Jugador " + (tablaJugadores.getRowCount()+1)};
        ((DefaultTableModel)tablaJugadores.getModel()).addRow(data);
        
        botonIniciarJuego.setEnabled(true);
    }
    
    /**
     * Activa todos los campos.
     */
    private void activarCampos(){
        jLabel2.setEnabled(true);
        jLabel13.setEnabled(true);
        jLabel14.setEnabled(true);
        jlabelTurno.setEnabled(true);
        jLabel7.setEnabled(true);
        panelLetras.setEnabled(true);
        jlabel.setEnabled(true);
        botonMonito.setEnabled(true);
        jPanel1.setEnabled(true);
        labelPalabra.setEnabled(true);
        
        botonIniciarJuego.setEnabled(false);
    }
    
    /**
     * Muestra las letras incorrectas en la pantalla.
     * El metodo esta sincronizado para evitar que una letra
     * se agrege dos veces en caso de que se haya invocado al mismo tiempo
     * en distintos lugares.
     * 
     * @param letras 
     */
    private synchronized void mostrarLetras(Map<Character, Boolean> letras){
        for (Map.Entry<Character, Boolean> letra : letras.entrySet()) {
            /**
             * Si la letra añadida no fue encontrada en la palabra,
             * el valor es falso.
             */
            if (!letra.getValue() && !contieneLetra(letra.getKey())) 
                panelLetras.add(new JLabel(String.valueOf(letra.getKey())));
        }
    }
    
    /**
     * Verifica si la letra ya esta dentro del panel de letras incorrectas.
     * 
     * @param letra
     * @return 
     */
    private boolean contieneLetra(Character letra){
        for(Component c : panelLetras.getComponents()){
            JLabel label = (JLabel) c;
            
            if(label.getText().equalsIgnoreCase(String.valueOf(letra)))
                return true;
        }
        
        return false;
    }
    
    /**
     * Muestra lo que se lleva de la frase actual
     * en la pantalla.
     * 
     * @param frase 
     */
    private void cambiarFraseActual(String frase){
        String texto = "";
        
        for (int i = 0; i < frase.length(); i++) {
            Character letra = frase.charAt(i);

            texto += letra + " ";

            if (letra == ' ') {
                texto += "   ";
            }
        }

        labelPalabra.setText(texto);
    }
    
    /**
     * Verifica si el panel tiene que cambiar de color acorde si
     * es el turno del jugador o no.
     * 
     * @param jugador 
     */
    private void cambiarColorPanel(String mensaje){
        if (mensaje.equalsIgnoreCase("(Te toca a ti adivinar!)")) {
            jPanel4.setBackground(new Color(255, 204, 204));
            campoTextoLetra.setEnabled(true);
            botonEnviarLetra.setEnabled(true);
        } else {
            jPanel4.setBackground(new Color(240, 240, 240));
            campoTextoLetra.setEnabled(false);
            botonEnviarLetra.setEnabled(false);
        }
    }
    
    /**
     * Cambia el estado del monito en pantalla.
     *
     * @param estado
     */
    public void setImagenMonito(EstadoMonito estado) {
        ImageIcon image = new ImageIcon(estado.getImagen());
        botonMonito.setIcon(image);
    }

    /**
     * Habilita o deshabilita el boton de enviar.
     * 
     * @param value Verdadero o falso.
     */
    public void setActivadoEnviar(boolean value){
        botonEnviarLetra.setEnabled(value);
        campoTextoLetra.setEnabled(value);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        botonEnviarLetra = new javax.swing.JButton();
        labelTiempo = new javax.swing.JLabel();
        jlabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        campoTextoLetra = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        botonIniciarJuego = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaJugadores = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        panelLetras = new javax.swing.JPanel();
        panelLetras.setLayout(new WrapLayout());
        jlabelTurno = new javax.swing.JLabel();
        labelPalabra = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        botonMonito = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 51));

        botonEnviarLetra.setText("Enviar");
        botonEnviarLetra.setEnabled(false);
        botonEnviarLetra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEnviarLetraActionPerformed(evt);
            }
        });

        jlabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlabel.setText("Le toca a: ");
        jlabel.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tiempo Restante:");
        jLabel3.setEnabled(false);

        campoTextoLetra.setEnabled(false);

        jSeparator2.setEnabled(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        botonIniciarJuego.setText("Iniciar Juego");
        botonIniciarJuego.setEnabled(false);
        botonIniciarJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIniciarJuegoActionPerformed(evt);
            }
        });

        tablaJugadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Jugadores"
            }
        ));
        jScrollPane1.setViewportView(tablaJugadores);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Esperando a jugadores..");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(botonIniciarJuego)))
                    .addComponent(jLabel14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonIniciarJuego)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator1.setEnabled(false);

        jLabel7.setText("Letras Erroneas");
        jLabel7.setEnabled(false);

        jLabel13.setText("Indique la letra:");
        jLabel13.setEnabled(false);

        panelLetras.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelPalabra.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelPalabra.setText("(Palabra sin escojer)");

        jLabel2.setText("Palabra por Adivinar");
        jLabel2.setEnabled(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        botonMonito.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botonMonito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botonMonito, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTiempo))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jlabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlabelTurno))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(labelPalabra)
                            .addComponent(jLabel7)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(campoTextoLetra, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(botonEnviarLetra))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelLetras, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelPalabra)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(labelTiempo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlabel)
                            .addComponent(jlabelTurno)
                            .addComponent(jLabel13)
                            .addComponent(campoTextoLetra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonEnviarLetra))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonIniciarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIniciarJuegoActionPerformed
        control.iniciarJuego();
        activarCampos();
    }//GEN-LAST:event_botonIniciarJuegoActionPerformed

    private void botonEnviarLetraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEnviarLetraActionPerformed
       control.verificarLetra(campoTextoLetra.getText().charAt(0));
       campoTextoLetra.setText("");
    }//GEN-LAST:event_botonEnviarLetraActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEnviarLetra;
    private javax.swing.JButton botonIniciarJuego;
    private javax.swing.JButton botonMonito;
    private javax.swing.JTextField campoTextoLetra;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel jlabel;
    private javax.swing.JLabel jlabelTurno;
    private javax.swing.JLabel labelPalabra;
    private javax.swing.JLabel labelTiempo;
    private javax.swing.JPanel panelLetras;
    private javax.swing.JTable tablaJugadores;
    // End of variables declaration//GEN-END:variables

}
