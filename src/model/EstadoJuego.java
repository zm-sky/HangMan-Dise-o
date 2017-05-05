/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import command.Context;
import static command.Context.setContext;
import commands.C_IniciarJuego;
import commands.C_MovimientoCorrecto;
import commands.C_MovimientoIncorrecto;
import commands.C_RegistrarTurno;
import commands.C_TerminarJuego;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import network.Peer;
import patron.Command;
import patron.CommandManager;
import view.EstadoMonito;

/**
 *
 * @author zippy
 */
public class EstadoJuego extends Context{

    private EstadoJuego(){
        
    }
    
    public static EstadoJuego getInstance(){
        return EstadoJuegoHolder.instance;
    }
    
    ///METODOS DE EL ESTADO DE JUEGO
    /**
     * Inicializa el peer en la direccion dada por los parametros. Tambien se
     * conecta hacia el servidor destinatario.
     *
     * @param IP La IP de este peer.
     * @param puerto El puerto de este peer.
     * @return
     */
    public boolean inicializar(String IP, int puerto, String IPServer, int puertoServer) {
        return EstadoJuegoHolder.instance.inicializar(IP, puerto, IPServer, puertoServer);
    }
    
    /**
     * Inicia el juego dentro del cliente. Esto se hace aqui para mandarle el
     * comando a todos los demas jugadores que ya se inicio el juego.
     *
     * Si mandaramos el comando Iniciar juego dentro del otro metodo, el comando
     * se seguiria llamando indefinidamente.
     */
    public void iniciarJuegoCliente() throws Exception {
        EstadoJuegoHolder.instance.iniciarJuegoCliente();
    }
    
    /**
     * Verifica la letra que se quiere agregar.
     * 
     * @param letra 
     */
    public void verificarLetra(Character letra){
        EstadoJuegoHolder.instance.verificarLetra(letra);
    }
    
    /**
     * Agrega un listener dedicado a escuchar por cambios en el modelo.
     *
     * @param listener
     */
    public void addModelListener(ModelListener listener) {
        EstadoJuegoHolder.instance.addModelListener(listener);
    }
    
    /**
     * Regresa la data del modelo actual.
     * 
     * @return 
     */
    public Map<String, Object> getModelData(){
        return EstadoJuegoHolder.instance.getModelData();
    }
    
    ///METODOS DEL COMMAND
    @Override
    public void registrarTurno(int turno) {
        EstadoJuegoHolder.instance.registrarTurno(turno);
    }

    @Override
    public void iniciarJuego(String frase) {
        EstadoJuegoHolder.instance.iniciarJuego(frase);
    }

    @Override
    public void avanzarTurno() {
        EstadoJuegoHolder.instance.avanzarTurno();
    }

    @Override
    public void resetearTimer() {
        EstadoJuegoHolder.instance.resetearTimer();
    }

    @Override
    public void agregarLetraIncorrecta(Character letra) {
        EstadoJuegoHolder.instance.agregarLetraIncorrecta(letra);
    }

    @Override
    public void agregarLetraCorrecta(Character letra) {
        EstadoJuegoHolder.instance.agregarLetraCorrecta(letra);
    }

    @Override
    public void finalizarJuego(String ganador) {
        EstadoJuegoHolder.instance.finalizarJuego(ganador);
    }
    
    
    
    
    
    
    
    
    
    private static class EstadoJuegoHolder extends EstadoJuego{
                /**
         * El numero de errores que se han cometido.
         */
        private int errores = 0;
        
        /**
         * Contiene el turno que nos toco.
         */
        private int turno;

        /**
         * Contiene el turno actual del juego.
         */
        private int turnoActual = 0;

        /**
         * Contiene el indice dentro de la lista para determinar
         * el turno actual. La lista tiene todos los turnos registrados
         * en orden de menor a mayor.
         */
        private int turnoIndex = -1;
        
        /**
         * Indica si el juego ha sido ganado.
         * Esto solo se usa para estar contando el tiempo
         * mientras no se haya ganado.
         */
        private boolean juegoGanado;
        
        /**
         * Contiene la lista de turnos asignados a todos los jugadores.
         */
        private List<Integer> turnosRegistrados;

        /**
         * Contiene la lista de letras que se han tratado
         * de adivinar. 
         * Aqui se indica si una letra es correcta mediante
         * el boolean especificado.
         */
        private static Map<Character, Boolean> letrasAñadidas;
        
        /**
         * Contiene la lista de listeners escuchando
         * cambios en el modelo.
         */
        private List<ModelListener> listeners;
        
        /**
         * Contiene la frase a adivinar.
         */
        private String frase;

        /**
         * Contiene lo que se ha adivinado de la frase que se tiene que
         * adivinar.
         */
        private String fraseActual = "";

        /**
         * Contiene el contador para adivinar.
         * Cuando quede en 0, ya no puede adivinar
         * el jugador.
         */
        private TimeWatch watch;
        
        /**
         * Contiene el estado actual del monito.
         */
        private EstadoMonito estadoMonito;

        /**
         * Contiene parte del modelo que maneja conexiones.
         */
        private CommandManager manager;

        /**
         * Esto simplemente sirve para guardar todos los datos
         * mencionados arriba, por si se quiere hacer uso de ellos
         * en algun otro lado.
         */
        private static Map<String, Object> datosConcretos;
        
        /**
         * La instancia de este singleton.
         */
        private static final EstadoJuego.EstadoJuegoHolder instance = new EstadoJuego.EstadoJuegoHolder();

        /**
         * Constructor privado.
         */
        private EstadoJuegoHolder(){
            listeners = new ArrayList();
            datosConcretos = new HashMap();
            turnosRegistrados = new ArrayList();
            letrasAñadidas = new HashMap();
            
            watch = new TimeWatch();
            inicializarDatos();
            setContext(this);
        }
        
        /**
         * Agrega toda la informacion relevante al
         * hashmap por si se ocupan datos.
         */
        private void inicializarDatos(){
            estadoMonito = EstadoMonito.VACIO;
            
            datosConcretos.put("Juego Iniciado:", false);
            datosConcretos.put("Turno Jugador:", null);
            datosConcretos.put("Segundos:", -1);
            datosConcretos.put("EstadoMono:", estadoMonito);
            datosConcretos.put("FraseActual:", null);
            datosConcretos.put("Conectados:", 0);
            datosConcretos.put("Letras:", letrasAñadidas);
        }
        
        /**
         * Inicializa el peer en la direccion dada por los parametros. Tambien
         * se conecta hacia el servidor destinatario.
         *
         * @param IP La IP de este peer.
         * @param puerto El puerto de este peer.
         * @return
         */
        @Override
        public boolean inicializar(String IP, int puerto, String IPServer, int puertoServer) {
            try {
                Peer peer = new Peer();
                peer.inicializar(IP, puerto);
                turno = peer.conectarServidor(IPServer, puertoServer);
                turnosRegistrados.add(turno);
               
                /**
                * Si nos pudimos conectar al servidor, entonces tenemos que mandarle
                * a los que esten conectados nuestro turno.
                */
                manager = new CommandManager(peer);
                
                /**
                 * Mandamos nuestro turno a los que esten conectados.
                 */
                Command registrarTurno = new C_RegistrarTurno(turno);
                manager.mandarComando(registrarTurno);
                
                /**
                 * Indicamos que si pudimos conectarnos al servidor.
                 */
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }
        
         /**
         * Avanza un turno hacia adelante.
         *
         * @return
         */
        @Override
        public void avanzarTurno() {
            /**
             * Antes de seguir, tenemos que checar si el mono esta muerto o no.
             */
            if(estadoMonito != EstadoMonito.MUERTO){
                turnoIndex = turnoIndex == turnosRegistrados.size() - 1 ? 0 : turnoIndex + 1;

                /**
                 * Obtenemos el turno actual..
                 */
                turnoActual = turnosRegistrados.get(turnoIndex);

                /**
                 * Esto simplemente sirve para decirle a la interfaz que texto poner en
                 * la parte de "Le toca a: JUGADOR". No se hace nada con esta variable.
                 */
                String nombreJugador = turno == turnoActual ? "(Te toca a ti adivinar!)" : "Jugador " + (turnoIndex+1);

                System.out.println("Poniendo nombre jugador " + nombreJugador);
                datosConcretos.put("Turno:", turno == turnoActual);
                datosConcretos.put("Turno Jugador:", nombreJugador);
                
                /**
                 * Notificamos a nuestros listeners que se cambio el turno.
                 */
                notifyListeners();
                
                /**
                 * Reiniciamos el contador a 0, ya que un nuevo turno empezo.
                 */
                watch.reset();

                /**
                 * Vamos a verificar si el contador ya ha sido activado.
                 * Suele estar desactivado solamente cuando apenas
                 * se inicia el juego. En este caso, tenemos que hacer un
                 * nuevo hilo donde el contador se va a estar actualizando,
                 * y la interfaz grafica vaya mostrando el tiempo restante.
                 */
                if(!watch.isStarted()){
                    watch.start();

                    new Thread(new Runnable(){
                        public void run(){
                            try{
                                /**
                                 * Mientras el juego no ha sido ganado,
                                 * vamos a estar contando el tiempo.
                                 */
                                while(!juegoGanado){
                                    //30 segundos - Tiempo que ha pasado.
                                    int segundosRestantes = (int) (30 - watch.time(TimeUnit.SECONDS));

                                    //Esto se hace ya que si hay lag, el contador no se
                                    //va a reiniciar hasta que reciba el comando de siguiente
                                    //turno del jugador.
                                    if(segundosRestantes <= 0){
                                        //Logica de movimiento incorrecto.
                                        segundosRestantes = 30;
                                        
                                        Command comando = new C_MovimientoIncorrecto(null);
                                        manager.mandarComando(comando);
                                        agregarError(null);
                                        avanzarTurno();
                                    }

                                    //Notificamos a el observer que avanzamos un segundo.
                                    datosConcretos.put("Segundos:", segundosRestantes);
                                    
                                    //notifyObservers(segundosRestantes);
                                    //Notificamos a los listener que hubo un cambio en los datos.
                                    notifyListeners();
                                    
                                    //Dormimos 100 millisegundos para volver a checar el tiempo
                                    //y que no se sature de instrucciones el processador.
                                    Thread.sleep(100);
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
            else
                watch.started = false;
        }
        
        /**
         * Registra el turno de un jugador dentro
         * de la lista de turnos.
         * 
         * @param turno 
         */
        @Override
        public void registrarTurno(int turno) {
            try{
                /**
                 * Determinamos si el turno que recibimos esta registrado.
                 * En caso de que no, tenemos que registrarlo, y mandarle a
                 * la fuente que tambien nos registre a nosotros.
                 */
                if(!estaTurnoRegistrado(turno)){
                    turnosRegistrados.add(turno);
                    Collections.sort(turnosRegistrados);

                    for(Integer i : turnosRegistrados){
                        System.out.println(i);
                    }

                    datosConcretos.put("Conectados:", turnosRegistrados.size());
                    notifyListeners();

                    /**
                     * Enviamos un comando a todos los que esten conectados
                     * que nuestro turno tiene que ser registrado, si es que
                     * no lo han hecho aun.
                     */
                    Command registrarTurno = new C_RegistrarTurno(this.turno);
                    manager.mandarComando(registrarTurno);
                }
                else
                    System.out.println("Este turno ya esta registrado.");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        /**
         * 
         * @param letra 
         */
        public void verificarLetra(Character letra){
            /**
             * Primero checaremos si la letra no haya sido
             * escrita. Si ya lo fue, no tenemos porque hacer
             * algo.
             */
            if(!letraYaEscrita(letra)){
                /**
                 * Si la letra si se encontro dentro de la
                 * frase..
                 */
                if(llenarFrase(letra)){
                    //Verificamos lo que se lleva completado de la frase.
                    verificarFrase();
                    
                    try{
                        //Tenemos que mandar el comando MovimientoCorrecto
                        //a los que esten conectados.
                        Command correcto = new C_MovimientoCorrecto(letra);
                        manager.mandarComando(correcto);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    
                    //Tenemos que reiniciar nuestro tiempo.
                    watch.reset();
                    
                    //Tambien tenemos que notificar el cambio en
                    //los que esten escuchando.
                    datosConcretos.put("FraseActual:", fraseActual);
                    notifyListeners();
                }
                /**
                 * Si fue incorrecta la letra..
                 */
                else{
                    try{
                        Command incorrecto = new C_MovimientoIncorrecto(letra);
                        manager.mandarComando(incorrecto);
                        
                        agregarError(letra);
                        avanzarTurno();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        
        /**
         * Llena la frase con la letra especificada, y
         * en las posiciones determinadas.
         * 
         * Si no se agrego ningun caracter, se regresara
         * falso ya que la frase no contiene ninguna de esas
         * letras.
         * 
         * @param letra 
         */
        private boolean llenarFrase(Character letra){
            /**
             * Esto servira para determinar si la letra existe en la palabra que
             * se indico.
             */
            boolean letraEncontrada = false;

            /**
             * Por cada caracter de la frase concreta, vamos a verificar que la
             * letra que se indico si esta.
             */
            for (int i = 0; i < frase.length(); i++) {
                if (frase.charAt(i) == letra) {
                    letraEncontrada = true;

                    StringBuilder builder = new StringBuilder(fraseActual);
                    builder.setCharAt(i, frase.charAt(i));
                    fraseActual = builder.toString();
                }
            }
            
            return letraEncontrada;
        }
        
        /**
         * Setea la frase.
         *
         * @param frase
         */
        public void setFrase(String frase) {
            this.frase = frase;
            
            /**
             * Para cada caracter, vamos a cambiarlo por "_" y
             * agregarselo a la frase actual, que es la que indica
             * que tanto de la frase ha sido adivinada.
             */
            for(int i = 0; i < frase.length(); i++){
                if(frase.charAt(i) == ' ')
                    fraseActual+= " ";
                
                fraseActual+= "_";
            }
            
            /**
             * Registramos el dato en los datos concretos,
             * y le avisamos a los que esten escuchando por cambios
             * que hubo una actualizacion.
             */
            datosConcretos.put("Frase:", frase);
            datosConcretos.put("FraseActual:", fraseActual);
            notifyListeners();
        }
        
        /**
         * Agrega uno al contador de error y actualiza el estado del 
         * monito.
         */
        public void agregarError(Character letra){
            //Añadimos 1 al contador..
            errores+=1;
            
            //Actualizamos el estado del monito..
            estadoMonito = EstadoMonito.getEstadoPorNumeroErrores(errores);
            datosConcretos.put("EstadoMono:", estadoMonito);
            
            if(letra != null){
                //Actualizamos las letras erroneas..
                letrasAñadidas.put(letra, false);
                datosConcretos.put("Letras:", letrasAñadidas);
            }
            
            notifyListeners();
            
            //Ahora verificamos si el juego ha sido perdido..
            if(estadoMonito == EstadoMonito.MUERTO)
                verificarJuego();
        }
        
        /**
         * Termina el juego, y verifica si se ha perdido, ademas de quien fue el ganador.
         */
        public void verificarJuego(){
            try{
                //Verificamos si la frase fue completada y nosotros fuimos el que ganamos.
                if(!fraseActual.contains("_") && turnoActual == turno){
                    Command terminar = new C_TerminarJuego(String.valueOf(turnoActual));
                    manager.mandarComando(terminar);
                    
                    finalizarJuego(String.valueOf(turnoActual));
                }
                else if(turnoActual == turno && estadoMonito == EstadoMonito.MUERTO){
                    Command terminar = new C_TerminarJuego(null);
                    manager.mandarComando(terminar);
                    
                    finalizarJuego(null);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        /**
         * Termina el juego, y verifica la razon por la cual se termino.
         * @param ganador Indica si hubo un ganador. Aqui viene el turno del jugador.
         */
        @Override
        public void finalizarJuego(String ganador){
            //Si no hubo ganador, significa que el juego fue perdido
            //o se termino prematuramente.
            if(ganador == null)
                JOptionPane.showMessageDialog(null, "Lastima! La palabra era " + frase + ".\nEl juego se ha perdido.");
            else if(Integer.parseInt(ganador) == turno){
                String jugadorGanador = "Jugador " + (turnosRegistrados.indexOf(Integer.parseInt(ganador)) + 1);
                JOptionPane.showMessageDialog(null, jugadorGanador + " ha ganado el juego!");
            }
            //Por otro lado, si hay un ganador entonces verificamos
            //quien fue el que gano.
            else
                JOptionPane.showMessageDialog(null, "Usted ha ganado el juego! Felicidades!");
            
            System.exit(0);
        }
        
        /**
        * Inicia el juego dentro del cliente.
        * Esto se hace aqui para mandarle el comando a todos
        * los demas jugadores que ya se inicio el juego.
        * 
        * Si mandaramos el comando Iniciar juego dentro del
        * otro metodo, el comando se seguiria llamando indefinidamente.
        */
        @Override
       public void iniciarJuegoCliente() throws Exception{
           /**
            * Generamos la frase.
            */
           String frase = Frases.generarFrase();
           System.out.println("Frase escojida: " + frase);
           
           /**
            * Construimos los comandos para que los 
            * demas clientes inicien el juego con la
            * frase que se dio, y que avanzen al primer turno.
            */
           Command comando = new C_IniciarJuego(frase);

           manager.mandarComando(comando);
           iniciarJuego(frase);
       }
                
        /**
         * Verifica si la frase ha sido completada.
         * En dado caso que si, avisaremos que el juego ha sido ganado.
         */
        public void verificarFrase(){
             //Esta variable servira para ver cuantas letras faltan.
            int letrasFaltantes = fraseActual.length() - fraseActual.replace("_", "").length();
            
            //Verificamos si ya no hay letras faltantes.
            if(letrasFaltantes == 0)
                verificarJuego();
        }
        
        /**
         * Determina si la letra ya esta escrita.
         * 
         * @param letra
         * @return 
         */
        public boolean letraYaEscrita(Character letra){
            for(Map.Entry<Character, Boolean> key : letrasAñadidas.entrySet()){
                if(key.getKey().compareTo(letra) == 0)
                    return true;
            }
            
            return false;
        }
              
        /**
         * Agrega una letra incorrecta a la lista de letras.
         * 
         * @param letra 
         */
        @Override
        public void agregarLetraCorrecta(Character letra){
            //letrasAñadidas.put(letra, true);
            
            llenarFrase(letra);
            verificarFrase();
            datosConcretos.put("FraseActual:", fraseActual);
            notifyListeners();
        }
        
       /**
         * Inicia el juego y marca en los datos que se ha arrancado.
         * 
         * @param frase 
         */
        @Override
        public void iniciarJuego(String frase){
            setFrase(frase);
            
            avanzarTurno();
            datosConcretos.put("Juego Iniciado:", true);
            notifyListeners();
        }
       
        /**
         * Regresa si el turno ya esta registrado.
         * 
         * @return 
         */
        public boolean estaTurnoRegistrado(int turno){
            for(Integer i : turnosRegistrados){
                System.out.println("    Turno: " + i);
                if(i == turno)
                    return true;
            }
            
            return false;
        }
               
        /**
         * Agrega una letra correcta a la lista de letras.
         * 
         * @param letra 
         */
        @Override
        public void agregarLetraIncorrecta(Character letra){
            if(letra != null){
                letrasAñadidas.put(letra, false);
                datosConcretos.put("Letras:", letrasAñadidas);
                notifyListeners();
            }
            
            agregarError(letra);
        }
        
         /**
         * Sirve para notificar a todos los que esten escuchando
         * por cambios en el modelo, que hubo una actualizacion.
         */
        private void notifyListeners(){
            for(ModelListener listener : listeners)
                listener.update();
        }
        
        /**
         * Agrega un listener dedicado a escuchar por cambios
         * en el modelo.
         * 
         * @param listener 
         */
        public void addModelListener(ModelListener listener){
            listeners.add(listener);
        }
        
        /**
         * Regresa la data del modelo actual.
         *
         * @return
         */
        public Map<String, Object> getModelData() {
            return datosConcretos;
        }
        
        /**
         * Resetea el contador de segundos.
         */
        public void resetearTimer(){
            watch.reset();
        }
    }
}
