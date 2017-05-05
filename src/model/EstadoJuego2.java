/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author zippy
 */
public class EstadoJuego2 {
//
//    /**
//     * Acceso privado del constructor para que no se pueda isntanciar esta clase
//     * en ninguna parte mas que aqui mismo.
//     */
//    private EstadoJuego2() {
//        
//    }
//
//    /**
//     * Registra un turno dentro de la lista de turnos. Esto se usara para
//     * determinar quien sigue.
//     *
//     * @param turno El turno a registrarse.
//     */
//    public void registrarTurno(int turno) {
//        EstadoJuegoHolder.instance.registrarTurno(turno);
//    }
//
//    /**
//     * Inicia el juego dentro del cliente.
//     * Esto se hace aqui para mandarle el comando a todos
//     * los demas jugadores que ya se inicio el juego.
//     * 
//     * Si mandaramos el comando Iniciar juego dentro del
//     * otro metodo, el comando se seguiria llamando indefinidamente.
//     */
//    public void iniciarJuegoCliente() throws Exception{
//        EstadoJuegoHolder.instance.iniciarJuegoCliente();
//    }
//    
//    /**
//     * Regresa la isntancia del estado de juego.
//     *
//     * @return La instancia del estado de juego.
//     */
//    public static EstadoJuego2 getInstance() {
//        return EstadoJuegoHolder.instance;
//    }
//
//    /**
//     * Agrega un listener al modelo del juego.
//     * 
//     * @param listener 
//     */
//    public void addModelListener(ModelListener listener){
//        EstadoJuegoHolder.instance.addModelListener(listener);
//    }
//    
//    /**
//     * Inicializa el peer en la direccion dada por los parametros. Tambien se
//     * conecta hacia el servidor destinatario.
//     *
//     * @param IP La IP de este peer.
//     * @param puerto El puerto de este peer.
//     * @return
//     */
//    public boolean inicializar(String IP, int puerto, String IPServer, int puertoServer) {
//        return EstadoJuegoHolder.instance.inicializar(IP, puerto, IPServer, puertoServer);
//    }
//
//    /**
//     * Manda un comando a todos los demas jugadores que esten conectados en la
//     * misma red.
//     *
//     * @param command El comando a mandarse.
//     */
//    //public void enviarComando(Command command) {
//    //    EstadoJuegoHolder.instance.enviarComando(command);
//    //}
//
//    /**
//     * Avanza un turno hacia adelante.
//     */
//    public void avanzarTurno() {
//        EstadoJuegoHolder.instance.avanzarTurno();
//    }
//
//    /**
//     * Cambia la frase a adivinarse.
//     *
//     * @param frase
//     */
//    //public void setFrase(String frase) {
//    //    EstadoJuegoHolder.instance.setFrase(frase);
//    //}
//
//    /**
//     * Regresa lo que se lleva adivinado de la frase.
//     *
//     * @return La frase que se lleva adivinada.
//     */
//    public String getFraseActual() {
//        return EstadoJuegoHolder.instance.getFraseActual();
//    }
//
//    /**
//     * Cambia lo que se lleva adivinado de la frase.
//     *
//     * @param fraseActual
//     */
//    public void setFraseActual(String fraseActual) {
//        EstadoJuegoHolder.instance.setFraseActual(fraseActual);
//    }
//
//    /**
//     * Regresa la frase a adivinarse.
//     *
//     * @return
//     */
//    public String getFrase() {
//        return EstadoJuegoHolder.instance.getFrase();
//    }
//
//    /**
//     * Regresa el turno actual.
//     * 
//     * @return 
//     */
//    public int getTurnoActual(){
//        return EstadoJuegoHolder.instance.getTurnoActual();
//    }
//    
//    /**
//     * Regresa el turno que se nos asigno.
//     * @return 
//     */
//    public int getTurno(){
//        return EstadoJuegoHolder.instance.getTurno();
//    }
//    
//    /**
//     * Regresa el indice del turno.
//     * @return 
//     */
//    public int getTurnoIndex(){
//        return EstadoJuegoHolder.instance.getTurnoIndex();
//    }
//    
//    /**
//     * Determina si el turno ya esta registrado.
//     * 
//     * @return 
//     */
//    public boolean estaTurnoRegistrado(int turno){
//        return EstadoJuegoHolder.instance.estaTurnoRegistrado(turno);
//    }
//    
//        /**
//     * Agrega una letra a la lista de letras que se han intentado adivinar.
//     *
//     * @param letra
//     */
//    public void agregarLetra(Character letra) {
//        EstadoJuegoHolder.instance.checarLetra(letra);
//        //EstadoJuegoHolder.instance.verificarLetra(letra);
//    }
//
//    /**
//     * Verifica si la letra indicada es la correcta.
//     * 
//     * @return 
//     */
//    public boolean verificarLetra(Character letra){
//        return EstadoJuegoHolder.instance.verificarLetra(letra);
//    }
//    
//    /**
//     * Indica si la letra ya esta escrita.
//     * 
//     * @param letra
//     * @return 
//     */
//    public boolean letraYaEscrita(Character letra){
//        return EstadoJuegoHolder.instance.letraYaEscrita(letra);
//    }
//
//    @Override
//    public void iniciarJuego(String frase) {
//        EstadoJuegoHolder.instance.iniciarJuego(frase);
//    }
//
//    @Override
//    public void setGanador(String ganador) {
//        
//    }
//    
//    /**
//     * Regresa la informacion registrada en el modelo.
//     * 
//     * @return 
//     */
//    public Map<String, Object> getModelData(){
//        return EstadoJuegoHolder.instance.datosConcretos;
//    }
//
//    /**
//     * Pierde el juego.
//     */
//    public void perderJuego() {
//        EstadoJuegoHolder.instance.perderJuego();
//    }
//
//    /**
//     * Gana el juego.
//     */
//    public void ganarJuego() {
//        EstadoJuegoHolder.instance.ganarJuego();
//    }
//
//    @Override
//    public void resetearTimer() {
//        EstadoJuegoHolder.instance.resetearTimer();
//    }
//    
//    
//    
//    
//    
//    
//    
//    /**
//     * Contiene toda la informacion relacionada a el juego y lo que va navegando
//     * atraves de la red.
//     *
//     * El modelo del juego.
//     */
//    private static class EstadoJuegoHolder extends EstadoJuego2{
//
//        /**
//         * El numero de errores que se han cometido.
//         */
//        private int errores = 0;
//        
//        /**
//         * Contiene el turno que nos toco.
//         */
//        private int turno;
//
//        /**
//         * Contiene el turno actual del juego.
//         */
//        private int turnoActual = 0;
//
//        /**
//         * Contiene el indice dentro de la lista para determinar
//         * el turno actual. La lista tiene todos los turnos registrados
//         * en orden de menor a mayor.
//         */
//        private int turnoIndex = -1;
//        
//        /**
//         * Indica si el juego ha sido ganado.
//         * Esto solo se usa para estar contando el tiempo
//         * mientras no se haya ganado.
//         */
//        private boolean juegoGanado;
//        
//        /**
//         * Contiene la lista de turnos asignados a todos los jugadores.
//         */
//        private List<Integer> turnosRegistrados;
//
//        /**
//         * Contiene la lista de letras que se han tratado
//         * de adivinar. 
//         * Aqui se indica si una letra es correcta mediante
//         * el boolean especificado.
//         */
//        private Map<Character, Boolean> letrasAñadidas;
//        
//        /**
//         * Contiene la lista de listeners escuchando
//         * cambios en el modelo.
//         */
//        private List<ModelListener> listeners;
//        
//        /**
//         * Contiene la frase a adivinar.
//         */
//        private String frase;
//
//        /**
//         * Contiene lo que se ha adivinado de la frase que se tiene que
//         * adivinar.
//         */
//        private String fraseActual = "";
//
//        /**
//         * Contiene el contador para adivinar.
//         * Cuando quede en 0, ya no puede adivinar
//         * el jugador.
//         */
//        private TimeWatch watch;
//        
//        /**
//         * Contiene el estado actual del monito.
//         */
//        private EstadoMonito estadoMonito;
//
//        /**
//         * Contiene parte del modelo que maneja conexiones.
//         */
//        private CommandManager manager;
//
//        /**
//         * Esto simplemente sirve para guardar todos los datos
//         * mencionados arriba, por si se quiere hacer uso de ellos
//         * en algun otro lado.
//         */
//        private Map<String, Object> datosConcretos;
//        
//        /**
//         * La instancia de este singleton.
//         */
//        private static final EstadoJuegoHolder instance = new EstadoJuegoHolder();
//
//        /**
//         * Constructor privado.
//         */
//        private EstadoJuegoHolder(){
//            listeners = new ArrayList();
//            datosConcretos = new HashMap();
//            turnosRegistrados = new ArrayList();
//            letrasAñadidas = new HashMap();
//            
//            watch = new TimeWatch();
//            inicializarDatos();
//            setContext(this);
//        }
//        
//        /**
//         * Agrega toda la informacion relevante al
//         * hashmap por si se ocupan datos.
//         */
//        private void inicializarDatos(){
//            estadoMonito = EstadoMonito.VACIO;
//            
//            datosConcretos.put("Juego Iniciado:", false);
//            //datosConcretos.put("Turno:", -1);
//            datosConcretos.put("Turno Jugador:", null);
//            datosConcretos.put("Segundos:", -1);
//            datosConcretos.put("EstadoMono:", estadoMonito);
//            //datosConcretos.put("Letras:", null);
//            //datosConcretos.put("Gane:", null);
//            //datosConcretos.put("JuegoPerdido:", false);
//            datosConcretos.put("FraseActual:", null);
//            datosConcretos.put("Conectados:", 0);
//            datosConcretos.put("Letras:", letrasAñadidas);
//        }
//        
//        /**
//        * Inicia el juego dentro del cliente.
//        * Esto se hace aqui para mandarle el comando a todos
//        * los demas jugadores que ya se inicio el juego.
//        * 
//        * Si mandaramos el comando Iniciar juego dentro del
//        * otro metodo, el comando se seguiria llamando indefinidamente.
//        */
//        @Override
//       public void iniciarJuegoCliente() throws Exception{
//           /**
//            * Generamos la frase.
//            */
//           String frase = Frases.generarFrase();
//           System.out.println("Frase escojida: " + frase);
//           
//           /**
//            * Construimos los comandos para que los 
//            * demas clientes inicien el juego con la
//            * frase que se dio, y que avanzen al primer turno.
//            */
//           Command comando = new C_IniciarJuego(frase);
//
//           manager.mandarComando(comando);
//           iniciarJuego(frase);
//       }
//        
//        /**
//         * Inicializa el peer en la direccion dada por los parametros. Tambien
//         * se conecta hacia el servidor destinatario.
//         *
//         * @param IP La IP de este peer.
//         * @param puerto El puerto de este peer.
//         * @return
//         */
//        @Override
//        public boolean inicializar(String IP, int puerto, String IPServer, int puertoServer) {
//            try {
//                Peer peer = new Peer();
//                peer.inicializar(IP, puerto);
//                turno = peer.conectarServidor(IPServer, puertoServer);
//                turnosRegistrados.add(turno);
//                
//                /**
//                * Si nos pudimos conectar al servidor, entonces tenemos que mandarle
//                * a los que esten conectados nuestro turno.
//                */
//                manager = new CommandManager(peer);
//                
//                /**
//                 * Mandamos nuestro turno a los que esten conectados.
//                 */
//                Command registrarTurno = new C_RegistrarTurno(getTurno());
//                manager.mandarComando(registrarTurno);
//                
//                /**
//                 * Indicamos que si pudimos conectarnos al servidor.
//                 */
//                return true;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return false;
//        }
//
//        /**
//         * Manda un comando a todos los demas jugadores que esten conectados en
//         * la misma red.
//         *
//         * @param command El comando a mandarse.
//         */
//        public void enviarComando(Command command) {
//            try {
//                manager.mandarComando(command);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        /**
//         * Avanza un turno hacia adelante.
//         *
//         * @return
//         */
//        @Override
//        public void avanzarTurno() {
//            /**
//             * Antes de seguir, tenemos que checar si el mono esta muerto o no.
//             */
//            if(estadoMonito != EstadoMonito.MUERTO){
//                turnoIndex = turnoIndex == turnosRegistrados.size() - 1 ? 0 : turnoIndex + 1;
//
//                /**
//                 * Obtenemos el turno actual..
//                 */
//                turnoActual = turnosRegistrados.get(turnoIndex);
//
//                /**
//                 * Esto simplemente sirve para decirle a la interfaz que texto poner en
//                 * la parte de "Le toca a: JUGADOR". No se hace nada con esta variable.
//                 */
//                String nombreJugador = turno == turnoActual ? "(Te toca a ti adivinar!)" : "Jugador " + (turnoIndex+1);
//
//                System.out.println("Poniendo nombre jugador " + nombreJugador);
//                datosConcretos.put("Turno:", turno == turnoActual);
//                datosConcretos.put("Turno Jugador:", nombreJugador);
//                
//                /**
//                 * Notificamos a nuestros listeners que se cambio el turno.
//                 */
//                notifyListeners();
//                
//                /**
//                 * Reiniciamos el contador a 0, ya que un nuevo turno empezo.
//                 */
//                watch.reset();
//
//                /**
//                 * Vamos a verificar si el contador ya ha sido activado.
//                 * Suele estar desactivado solamente cuando apenas
//                 * se inicia el juego. En este caso, tenemos que hacer un
//                 * nuevo hilo donde el contador se va a estar actualizando,
//                 * y la interfaz grafica vaya mostrando el tiempo restante.
//                 */
//                if(!watch.isStarted()){
//                    watch.start();
//
//                    new Thread(new Runnable(){
//                        public void run(){
//                            try{
//                                /**
//                                 * Mientras el juego no ha sido ganado,
//                                 * vamos a estar contando el tiempo.
//                                 */
//                                while(!juegoGanado){
//                                    //30 segundos - Tiempo que ha pasado.
//                                    int segundosRestantes = (int) (30 - watch.time(TimeUnit.SECONDS));
//
//                                    //Esto se hace ya que si hay lag, el contador no se
//                                    //va a reiniciar hasta que reciba el comando de siguiente
//                                    //turno del jugador.
//                                    if(segundosRestantes <= 0){
//                                        //Logica de movimiento incorrecto.
//                                        segundosRestantes = 30;
//                                        
//                                        Command comando = new C_MovimientoIncorrecto(null);
//                                        manager.mandarComando(comando);
//                                        avanzarTurno();
//                                    }
//
//                                    //Notificamos a el observer que avanzamos un segundo.
//                                    datosConcretos.put("Segundos:", segundosRestantes);
//                                    
//                                    //notifyObservers(segundosRestantes);
//                                    //Notificamos a los listener que hubo un cambio en los datos.
//                                    notifyListeners();
//                                    
//                                    //Dormimos 100 millisegundos para volver a checar el tiempo
//                                    //y que no se sature de instrucciones el processador.
//                                    Thread.sleep(100);
//                                }
//                            }catch(Exception e){
//                                e.printStackTrace();
//                            }
//                        }
//                    }).start();
//                }
//            }
//            else
//                watch.started = false;
//        }
//
//        /**
//         * Verifica si la letra es la correcta.
//         * 
//         * @param letra
//         * @return 
//         */
//        public boolean verificarLetra(Character letra){
//            try{
//                Command comando;
//                System.out.println("Verificando letra: " + letra);
//
//                /**
//                 * Primero vamos a verificar que no hayamos ya 
//                 * escrito la letra. En dado caso que si, no hacemos
//                 * nada hasta que se ecoja otra.
//                 */
//                if(!letraYaEscrita(letra)){
//                    System.out.println("La letra: " + letra + " | no ha sido escrita. Verificando..");
//                    /**
//                     * Si la letra es la correcta..
//                     */
//                    if(checarLetra(letra)){
//                        System.out.println("La letra si es correcta. Mandando movimiento correcto.");
//                        
//                        agregarLetra(letra);
//                        
//                        comando = new C_MovimientoCorrecto(letra);
//                        System.out.println("Movimiento correcto.");
//                    }
//                    /**
//                     * Si no es correcta, tenemos que pasar al siguiente turno.
//                     */
//                    else{
//                        comando = new C_MovimientoIncorrecto(letra);
//                        avanzarTurno();
//
//                        System.out.println("Movimiento Incorrecto.");
//                    }
//
//                    manager.mandarComando(comando);
//                    
//                    //Ahora verificamos si el monito ha muerto.
//                    verificarMono();
//                    
//                    return true;
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//            
//            return false;
//        }
//        
//        /**
//         * Verifica si el monito ha muerto, y si se tiene
//         * que perder el juego.
//         */
//        public void verificarMono(){
//            if(estadoMonito == EstadoMonito.MUERTO){
//                perderJuego();
//            }
//        }
//        
//        /**
//         * Checa si la letra es correcta o no.
//         * 
//         * @param letra
//         * @return 
//         */
//        private boolean checarLetra(Character letra){
//            if(letra == null){
//                   /**
//                 * Añadimos 1 a la cantidad de errores que se tienen.
//                 */
//                errores++;
//                /**
//                 * Cambiamos el estado del monito dependiendo de los errores que
//                 * se tienen.
//                 */
//                estadoMonito = EstadoMonito.getEstadoPorNumeroErrores(errores);
//
//                datosConcretos.put("FraseActual:", fraseActual);
//                datosConcretos.put("EstadoMono:", estadoMonito);
//                datosConcretos.put("Letras:", letrasAñadidas);
//                return false;
//            }
//            
//            /**
//             * Esto servira para determinar si la letra existe en la palabra que
//             * se indico.
//             */
//            boolean letraEncontrada = false;
//
//            /**
//             * Por cada caracter de la frase concreta, vamos a verificar que la
//             * letra que se indico si esta.
//             */
//            for (int i = 0; i < frase.length(); i++) {
//                if (frase.charAt(i) == letra) {
//                    letraEncontrada = true;
//
//                    StringBuilder builder = new StringBuilder(fraseActual);
//                    builder.setCharAt(i, frase.charAt(i));
//                    fraseActual = builder.toString();
//                }
//            }
//
//            /**
//             * Esta variable servira para ver cuantas letras faltan.
//             */
//            int letrasFaltantes = fraseActual.length() - fraseActual.replace("_", "").length();
//
//            /**
//             * Verifica si la letra que se ingreso no fue la correcta.
//             */
//            if (!letraEncontrada) {
//                /**
//                 * Añadimos 1 a la cantidad de errores que se tienen.
//                 */
//                errores++;
//                /**
//                 * Cambiamos el estado del monito dependiendo de los errores que
//                 * se tienen.
//                 */
//                estadoMonito = EstadoMonito.getEstadoPorNumeroErrores(errores);
//
//                /**
//                 * Agregamos
//                 */
//                letrasAñadidas.put(letra, false);
//
//                datosConcretos.put("EstadoMono:", estadoMonito);
//                datosConcretos.put("Letras:", letrasAñadidas);
//            } else {
//                if (letrasFaltantes == 0) {
//                    datosConcretos.put("Gane:", "Ganaste");
//                    ganarJuego();
//                }
//
//                letrasAñadidas.put(letra, true);
//                watch.reset();
//            }
//
//            
//            datosConcretos.put("FraseActual:", fraseActual);
//            notifyListeners();
//
//            return letraEncontrada;
//        }
//        
//        /**
//         * Regresa si el turno ya esta registrado.
//         * 
//         * @return 
//         */
//        public boolean estaTurnoRegistrado(int turno){
//            for(Integer i : turnosRegistrados)
//                if(i == turno)
//                    return true;
//            
//            return false;
//        }
//        
//        /**
//         * Setea la frase.
//         *
//         * @param frase
//         */
//        public void setFrase(String frase) {
//            this.frase = frase;
//            
//            /**
//             * Para cada caracter, vamos a cambiarlo por "_" y
//             * agregarselo a la frase actual, que es la que indica
//             * que tanto de la frase ha sido adivinada.
//             */
//            for(int i = 0; i < frase.length(); i++){
//                if(frase.charAt(i) == ' ')
//                    fraseActual+= " ";
//                
//                fraseActual+= "_";
//            }
//            
//            /**
//             * Registramos el dato en los datos concretos,
//             * y le avisamos a los que esten escuchando por cambios
//             * que hubo una actualizacion.
//             */
//            datosConcretos.put("Frase:", frase);
//            datosConcretos.put("FraseActual:", fraseActual);
//            notifyListeners();
//        }
//
//        
//        /**
//         * Registra el turno de un jugador dentro
//         * de la lista de turnos.
//         * 
//         * @param turno 
//         */
//        @Override
//        public void registrarTurno(int turno) {
//            /**
//             * Determinamos si el turno que recibimos esta registrado.
//             * En caso de que no, tenemos que registrarlo, y mandarle a
//             * la fuente que tambien nos registre a nosotros.
//             */
//            if(!estaTurnoRegistrado(turno)){
//                turnosRegistrados.add(turno);
//                Collections.sort(turnosRegistrados);
//                System.out.println("El turno ha sido registrado.");
//
//                for(Integer i : turnosRegistrados){
//                    System.out.println(i);
//                }
//
//                datosConcretos.put("Conectados:", turnosRegistrados.size());
//                notifyListeners();
//                
//                /**
//                 * Enviamos un comando a todos los que esten conectados
//                 * que nuestro turno tiene que ser registrado, si es que
//                 * no lo han hecho aun.
//                 */
//                Command registrarTurno = new C_RegistrarTurno(getTurno());
//                enviarComando(registrarTurno);
//            }
//        }
//        
//        /**
//         * Determina si la letra ya esta escrita.
//         * 
//         * @param letra
//         * @return 
//         */
//        public boolean letraYaEscrita(Character letra){
//            for(Map.Entry<Character, Boolean> key : letrasAñadidas.entrySet()){
//                if(key.getKey() == letra)
//                    return true;
//            }
//            
//            return false;
//        }
//        
//        /**
//        * Gana el juego.
//        */
//       public void ganarJuego(){
//           try{
//                Command perder = new C_Pierde("Jugador " + (getTurnoIndex() + 1));
//                manager.mandarComando(perder);
//
//                JOptionPane.showMessageDialog(null, "Usted ha ganado el juego, felicidades!");
//                System.exit(0);
//           }catch(Exception e){
//               e.printStackTrace();
//           }
//       }
//        
//        /**
//         * Indica que el juego se ha perdido.
//         */
//        public void perderJuego(){
//            try{
//                Command perder = new C_Pierde(null);
//                manager.mandarComando(perder);
//
//                JOptionPane.showMessageDialog(null, "El juego ha sido perdido!\nLa palabra era: " + getFrase());
//                System.exit(0);
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//        
//        /**
//         * Inicia el juego y marca en los datos que se ha arrancado.
//         * 
//         * @param frase 
//         */
//        public void iniciarJuego(String frase){
//            setFrase(frase);
//            
//            avanzarTurno();
//            datosConcretos.put("Juego Iniciado:", true);
//            notifyListeners();
//        }
//        
//        /**
//         * Sirve para notificar a todos los que esten escuchando
//         * por cambios en el modelo, que hubo una actualizacion.
//         */
//        private void notifyListeners(){
//            for(ModelListener listener : listeners)
//                listener.update();
//        }
//        
//        /**
//         * Agrega una letra a la lista de letras que se
//         * han intentado adivinar. Todas estas letras
//         * son letras incorrectas.
//         * 
//         * @param letra 
//         */
//        public void agregarLetra(Character letra){
//            letrasAñadidas.put(letra, false);
//        }
//        
//        /**
//         * Agrega un listener al modelo.
//         * 
//         * @param listener 
//         */
//        public void addModelListener(ModelListener listener){
//            listeners.add(listener);
//        }
//        
//        /**
//         * Regresa lo que se lleva adivinado de la frase.
//         *
//         * @return La frase que se lleva adivinada.
//         */
//        @Override
//        public String getFraseActual() {
//            return fraseActual;
//        }
//
//        /**
//         * Cambia lo que se lleva adivinado de la frase.
//         *
//         * @param fraseActual
//         */
//        @Override
//        public void setFraseActual(String fraseActual) {
//            this.fraseActual = fraseActual;
//        }
//        
//        /**
//         * Regresa la frase que se esta descifrando.
//         *
//         * @return
//         */
//        @Override
//        public String getFrase() {
//            return frase;
//        }
//
//        /**
//         * Regresa el turno actual.
//         * 
//         * @return 
//         */
//        @Override
//        public int getTurnoActual(){
//            return turnoActual;
//        }
//        
//        /**
//         * Regresa el indice del turno.
//         * 
//         * @return 
//         */
//        public int getTurnoIndex(){
//            return turnoIndex;
//        }
//        
//        /**
//         * Regresa el turno que se nos asigno.
//         *
//         * @return
//         */
//        @Override
//        public int getTurno() {
//            return turno;
//        }
//        
//        /**
//         * Resetea el timer.
//         */
//        @Override
//        public void resetearTimer() {
//            watch.reset();
//        }
//    }
}
