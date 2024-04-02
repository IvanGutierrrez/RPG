
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 
 */
public class Jugador extends Usuario implements Serializable {

    private String nRegistro;

    private Personaje personajeActivo;

    private boolean bloqueado;

    private Combate desafio;

    private double totalOroGanado;

    private ArrayList<Personaje> personajes;

    private ArrayList<Combate> historialCombates;

    private LocalDateTime ultimaDerrota;

    public String getNRegistro() {
        return nRegistro;
    }

    public Jugador(){
        this.personajes = new ArrayList<>();
        this.historialCombates = new ArrayList<>();
    }

    public final void setNRegistro(Partida p) {
        StringBuilder sb;
        do {
            sb = new StringBuilder();

            // Letra aleatoria
            char letra1 = (char) ('A' + Math.random() * ('Z' - 'A' + 1));
            char letra2 = (char) ('A' + Math.random() * ('Z' - 'A' + 1));

            // Números aleatorios
            int num1 = (int) (Math.random() * 10);
            int num2 = (int) (Math.random() * 10);

            // Construir el string
            sb.append(letra1);
            sb.append(num1);
            sb.append(num2);
            sb.append(letra2);
            sb.append(letra2);
        } while (!p.noExiste(sb.toString()));
        this.nRegistro = sb.toString();
    }

    public Personaje getPersonajeActivo() {
        return personajeActivo;
    }

    public void setPersonajeActivo(Personaje personajeActivo) {
        this.personajeActivo = personajeActivo;
    }

    public boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Combate getDesafio() {
        return desafio;
    }

    public void setDesafio(Combate desafio) {
        this.desafio = desafio;
    }

    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
    }

    public ArrayList<Combate> getHistorialCombates() {
        return historialCombates;
    }

    public void setHistorialCombates(ArrayList<Combate> historialCombates) {
        this.historialCombates = historialCombates;
    }

    public LocalDateTime getUltimaDerrota() {
        return ultimaDerrota;
    }

    public void setUltimaDerrota(LocalDateTime ultimaDerrota) {
        this.ultimaDerrota = ultimaDerrota;
    }

    private boolean nickUnico(String nick) throws IOException {
        File archivo = new File("Trabajo-MP/datos/operadores/operador");
        FileReader fileReader = new FileReader(archivo);
        BufferedReader buf = new BufferedReader(fileReader);
        String linea = buf.readLine(); // Lees la primera línea
        String[] result;
        while ((linea = buf.readLine()) != null) {
            result = linea.split(",");
            if (result[1].equals(nick)){
                buf.close(); // Importante cerrar el BufferedReader
                return false;
            }
        }
        buf.close(); // Importante cerrar el BufferedReader
        return true;
    }

    public void preguntarDetallesJugador(Partida p) throws IOException {
        boolean ok = false;
        int num = 0;
        while (!ok && num<2){
            System.out.println("Introduzca nombre");
            String nombre = this.leerString();
            this.setNombre(nombre);
            String nick = null;
            do{
                if (nick != null){
                    System.out.println("Ese nick ya esta cogido");
                }
                System.out.println("Introduzca nick");
                nick = this.leerString();
            } while(!p.nickUnico(nick) && this.nickUnico(nick));
            this.setNick(nick);

            String pass = null;
            do {
                if (pass != null){
                    System.out.println("La pass debe tener entre 8 y 12 caracteres");
                }
                System.out.println("Introduzca pass");
                pass = this.leerString();
            } while (pass.length()>12 || pass.length()<8);
            this.setPass(pass);
            System.out.println("Introduzca 1 si esta de acuerdo con los datos introducidos");
            System.out.println("Nick: " + this.getNick());
            System.out.println("Nombre: " + this.getNombre());
            System.out.println("Contraseña: " + this.getPass());
            ok = this.leerString().equals("1");
            num++;
        }
        if (num<=2) {
            this.setBloqueado(false);
            this.setDesafio(null);
            this.setNRegistro(p);
            this.setPersonajeActivo(null);
            this.setUltimaDerrota(null);
            this.setTotalOroGanado(0);
        } else{
            this.setPass(null);
        }
    }

    @Override
    public void Menu(Partida p) throws IOException {
        int opcion = 0;
        if (!this.bloqueado) {
            while (opcion != 8 && opcion != 9) {
                if (getDesafio() == null) {
                    System.out.println("Eliga la opción");
                    System.out.println("1.-Desafiar");
                    System.out.println("2.-Gestionar Equipamineto");
                    System.out.println("3.-Cambiar Personaje Activo");
                    System.out.println("4.-Registrar Personaje Nuevo");
                    System.out.println("5.-Borrar Personaje Actual");
                    System.out.println("6.-Mostrar Historial de Combates");
                    System.out.println("7.-Mostrar Ranking de Jugadores");
                    System.out.println("8.-Cerrar Sesión");
                    System.out.println("9.-Dar de Baja Usuario");
                    opcion = this.leerInt();
                    if (opcion == 1) {
                        this.desafiar(p);
                    } else if (opcion == 2) {
                        if (this.getPersonajeActivo() == null) {
                            this.selecPersonajeActivo(p);
                        }
                        if (this.personajeActivo != null) {
                            this.getPersonajeActivo().gestionEquipamiento();
                        } else {
                            System.out.println("Seleccione un personaje antes de gestionar su equipamiento");
                        }
                    } else if (opcion == 3) {
                        this.selecPersonajeActivo(p);
                    } else if (opcion == 4) {
                        this.registrarPersonaje(p);
                    } else if (opcion == 5) {
                        this.darDeBajaPersonaje();
                    } else if (opcion == 6) {
                        this.mostrarHistorial();
                    } else if (opcion == 7) {
                        this.mostrarRanking(p.getMapUsuarios());
                    } else if (opcion == 8) {
                        System.out.println("Esperamos volverte a ver pronto");
                    } else if (opcion == 9) {
                        int numBaja = 0;
                        System.out.println("¿Estas seguro?");
                        while (numBaja != 1 && numBaja != 2) {
                            System.out.println("1.-Si, quiero darme de baja");
                            System.out.println("2.-No, deseo volver");
                            numBaja = this.leerInt();
                            if (numBaja == 1) {
                                p.darDeBajaUsuario(this);
                            } else if (numBaja == 2) {
                                opcion = 0; //vuelve al menu
                            }
                        }
                    }
                } else if (this.getDesafio() != null && this.getDesafio().isValido() && this.getDesafio().getGanador() == null && this.getDesafio().getJugadorRetador() != this && (this.getDesafio().getRondas() == null || this.getDesafio().getRondas().isEmpty())) {
                    this.desafiadoResuelve();
                    this.setDesafio(null);
                } else if (this.getDesafio() != null && this.getDesafio().isValido() && this.getDesafio().getJugadorRetador() == this && ((this.getDesafio().getRondas() != null && !this.getDesafio().getRondas().isEmpty())) || this.getDesafio().getGanador() != null) {
                    this.getDesafio().mostrarResultado();
                    this.setDesafio(null);
                } else {
                    System.out.println("Se encuentra a la espera de validación de su desafio por parte de un operador, sea paciente porfavor");
                    System.out.println("Presione Enter para continuar...");
                    this.leerString();
                    p.serializar();
                    break;
                }
                p.serializar();
            }
        } else {
            System.out.println("Este jugador esta bloqueado");
        }
    }

    public void registrarPersonaje(Partida p) {
        List<Personaje> allCharacters = p.getListaPersonajes();
        boolean personajeElegido = false;

        for (int intento = 0; intento <= 2 && !personajeElegido; intento++) {
            System.out.println("Lista de personajes disponibles: ");
            for (int i = 0; i < allCharacters.size(); i++) {
                System.out.println((i + 1) + ". " + allCharacters.get(i).getNombre());
            }

            System.out.println("Seleccione un personaje:");
            int opc = this.leerInt();

            if (opc >= 1 && opc <= allCharacters.size()) {
                Personaje personajeElegidoTmp = allCharacters.get(opc - 1);
                System.out.println("Ha seleccionado a " + personajeElegidoTmp.getNombre() );
                boolean nombreRepetido = false;
                for (Personaje personajeExistente : this.personajes) {
                    if (personajeExistente.getNombre().equals(personajeElegidoTmp.getNombre())) {
                        nombreRepetido = true;
                        break;
                    }
                }
                if (nombreRepetido) {
                    System.out.println("Ya hay un personaje registrado con el mismo nombre. Por favor, seleccione otro.");
                } else {
                    personajeElegido = true;
                    Personaje personajeNuevo = personajeElegidoTmp.clone();
                    this.personajes.add(personajeNuevo);
                    this.personajeActivo = personajeNuevo;
                    this.personajeActivo.gestionEquipamiento();
                }
            } else {
                System.out.println("Opción no válida. Por favor, seleccione un número válido");
            }
        }

        if (!personajeElegido) {
            System.out.println("Se han agotado los intentos para seleccionar un personaje");
        }
    }

    public void darDeBajaPersonaje() {
        boolean personajeElegido = false;

        for (int intento = 0; intento <= 2 && !personajeElegido; intento++) {
            System.out.println("Lista de personajes disponibles: ");
            for (int i = 0; i < this.personajes.size(); i++) {
                System.out.println((i + 1) + ". " + this.personajes.get(i).getNombre());
            }

            System.out.println("Seleccione un personaje:");
            int opc = this.leerInt();

            if (opc >= 1 && opc <= this.personajes.size()) {
                Personaje personajeElegidoTmp = this.personajes.get(opc - 1);
                System.out.println("Ha seleccionado a " + personajeElegidoTmp.getNombre() );
                personajeElegido = true;

                System.out.println("¿Seguro que quiere dar de baja este personaje definitivamente? (si desea continuar escriba: 1234)");
                int conf = this.leerInt();
                if (conf == 1234){
                    this.personajes.remove(opc-1);
                }
            } else {
                System.out.println("Opción no válida. Por favor, seleccione un número válido");
            }
        }

        if (!personajeElegido) {
            System.out.println("Se han agotado los intentos para seleccionar un personaje");
        }
    }

    public void desafiar(Partida p) {
        if (this.personajeActivo != null) {
            Jugador retado = this.pedirDesafiado(p);
            if (retado != null) {
                int apuesta = this.seleccionApuesta(retado);
                if (apuesta != -1) {
                    Combate combate = new Combate(this, retado, apuesta, this.getPersonajeActivo(), retado.getPersonajeActivo(), LocalDateTime.now());
                    p.addChallenge(combate);
                    this.setDesafio(combate);
                    retado.setDesafio(combate);
                }
            }
        } else {System.out.print("Seleccione un personaje antes de retar");}
    }

    public void mostrarRanking(Map<String, Usuario> mapaJugadores) {
        List<Jugador> jugadores = new ArrayList<>();
        for (Usuario usuario : mapaJugadores.values()) {
            if (usuario instanceof Jugador jugador) {
                jugadores.add(jugador);
            }
        }

        jugadores.sort(Comparator.comparingDouble(Jugador::getTotalOroGanado).reversed());

        for (Jugador jugador : jugadores) {
            System.out.println("Nombre: " + jugador.getNombre());
            System.out.println("Número de Registro: " + jugador.getNRegistro());
            System.out.println("Indice de oro total: " + jugador.getTotalOroGanado());
            System.out.println("Personaje Activo: " + jugador.getPersonajeActivo().getNombre());
            System.out.println("Bloqueado: " + (jugador.getBloqueado() ? "Sí" : "No"));
            System.out.println("Desafío: " + (jugador.getDesafio() != null ? "Sí" : "No"));
            System.out.println("Última Derrota: " + jugador.getUltimaDerrota());
            System.out.println("---------------------------------------------");
        }
    }

    private Jugador pedirDesafiado(Partida p) {
        for (int intento = 0; intento <= 2; intento++){
            System.out.println("Introduzca el nick del jugador a retar: ");
            String nickRetado = this.leerString();
            Map<String,Usuario> mapaUsuarios = p.getMapUsuarios();

            if (mapaUsuarios.containsKey(nickRetado) && mapaUsuarios.get(nickRetado) instanceof Jugador jugador && jugador.getDesafio() == null && !jugador.getBloqueado() && jugador.getPersonajeActivo() != null && this != jugador){
                if (jugador.getUltimaDerrota() != null) {
                    long diferenciaEnHoras = ChronoUnit.HOURS.between(jugador.getUltimaDerrota(), LocalDateTime.now());
                    if (diferenciaEnHoras >= 24){
                        return (jugador);
                    } else { System.out.println("Jugador introducido no valido");}
                } else {
                    return jugador;}
            } else { System.out.println("Jugador introducido no valido");}
        }
        return null;
    }

    public int seleccionApuesta(Jugador retado) {
        for (int intento = 0; intento <= 2; intento++) {
            System.out.println("Introduzca la cantidad apostada: ");
            int apuesta = this.leerInt();
            if (retado.getPersonajeActivo().checkApuesta(apuesta) && this.getPersonajeActivo().checkApuesta(apuesta)){
                return apuesta;
            }
        }
        return -1;
    }

    public void selecPersonajeActivo(Partida p) {
        boolean personajeElegido = false;

        for (int intento = 0; intento <= 2 && !personajeElegido; intento++) {
            System.out.println("Lista de personajes disponibles:");
            for (int i = 0; i < this.personajes.size(); i++) {
                System.out.println((i + 1) + ". " + this.personajes.get(i).getNombre());
            }

            System.out.println("Seleccione un personaje:");
            int opt = this.leerInt();

            if (opt >= 1 && opt <= this.personajes.size()) {
                Personaje personajeElegidoTmp = this.personajes.get(opt - 1);

                personajeElegidoTmp = p.checkVersion(personajeElegidoTmp);

                this.personajeActivo = personajeElegidoTmp;
                System.out.println("Se ha seleccionado a " + this.personajeActivo.getNombre() + " como el personaje activo");
                personajeElegido = true;
            } else {
                System.out.println("Opción no válida. Por favor, seleccione un número válido");
            }
        }

        if (!personajeElegido) {
            System.out.println("Se han agotado los intentos para seleccionar un personaje activo");
        }
    }

    private void desafiadoResuelve() {
        System.out.println("Ha sido desafiado");
        System.out.println("¿Desea aceptar el desafio? (1 para aceptar)");
        int conf = this.leerInt();
        if (conf == 1) {
            this.desafio.combatir();
            this.desafio.mostrarResultado();

        } else {
            this.desafio.cancelarCombate();

        }
    }

    public void mostrarHistorial() {
        for (Combate elemento : this.historialCombates) {
            System.out.println("Jugador Retador: " + elemento.getJugadorRetador().getNombre());
            System.out.println("Jugador Retado: " + elemento.getJugadorRetado().getNombre());
            System.out.println("Oro Apostado: " + elemento.getOroApostado());
            System.out.println("Rondas: " + elemento.getRondas());
            System.out.println("Fecha: " + elemento.getFecha());
            System.out.println("Ganador: " + (elemento.getGanador() != null ? elemento.getGanador().getNombre() : "N/A"));
            System.out.println("Jugadores con Esbirros Sin Derrotar:");
            System.out.println(elemento.getJugadorConEsbirrosSinDerrotar().getNombre());
            System.out.println("Modificadores:");
            for (Modificador modificador : elemento.getModificadores()) {
                System.out.println("- " + modificador.getNombre());
            }
            System.out.println("Valido: " + (elemento.isValido() ? "Sí" : "No"));
            System.out.println("Personaje Retador: " + elemento.getPersonajeRetador().getNombre());
            System.out.println("Personaje Retado: " + elemento.getPersonajeRetado().getNombre());
            System.out.println("---------------------------------------------");
        }
    }

    private int leerInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private String leerString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void anadirCombate(Combate combate) {
        this.historialCombates.add(combate);
    }

    public double getTotalOroGanado() {
        return totalOroGanado;
    }

    public void setTotalOroGanado(double totalOroGanado) {
        this.totalOroGanado = totalOroGanado;
    }
}
