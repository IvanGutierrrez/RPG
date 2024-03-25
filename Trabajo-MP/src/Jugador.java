
import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.Scanner;

/**
 * 
 */
public class Jugador extends Usuario implements Serializable {

    private String NRegistro;

    private Personaje PersonajeActivo;

    private boolean Bloqueado;

    private Combate Desafio;

    private List<Personaje> Personajes;

    private Combate[] RegistrosCombates;

    private Combate[] HistorialCombates;

    private String ultimaDerrota;

    public String getNRegistro() {
        return NRegistro;
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
        this.NRegistro = sb.toString();
    }

    public Personaje getPersonajeActivo() {
        return PersonajeActivo;
    }

    public void setPersonajeActivo(Personaje personajeActivo) {
        PersonajeActivo = personajeActivo;
    }

    public boolean getBloqueado() {
        return Bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        Bloqueado = bloqueado;
    }

    public Combate getDesafio() {
        return Desafio;
    }

    public void setDesafio(Combate desafio) {
        Desafio = desafio;
    }

    public List<Personaje> getPersonajes() {
        return Personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        Personajes = personajes;
    }

    public Combate[] getRegistrosCombates() {
        return RegistrosCombates;
    }

    public void setRegistrosCombates(Combate[] registrosCombates) {
        RegistrosCombates = registrosCombates;
    }

    public Combate[] getHistorialCombates() {
        return HistorialCombates;
    }

    public void setHistorialCombates(Combate[] historialCombates) {
        HistorialCombates = historialCombates;
    }

    public String getUltimaDerrota() {
        return ultimaDerrota;
    }

    public void setUltimaDerrota(String ultimaDerrota) {
        this.ultimaDerrota = ultimaDerrota;
    }

    public void preguntarDetallesJugador(Partida p) {
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
            } while(!p.nickUnico(nick));
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
            this.setHistorialCombates(null);
            this.setNRegistro(p);
            this.setPersonajeActivo(null);
            this.setPersonajes(null);
            this.setUltimaDerrota(null);
            this.setRegistrosCombates((null));
        } else{
            this.setPass(null);
        }
    }

    @Override
    public void Menu(Partida p) {
        int opcion = 0;

        while(opcion != 8 && opcion != 9) {
            if (getDesafio() != null) {
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
                    this.Desafiar();
                } else if (opcion == 2) {
                    if (this.getPersonajeActivo() == null) {
                        this.selecPersonajeActivo(p);
                    }
                    Personaje personaje = this.getPersonajeActivo();
                    personaje.gestionEquipamiento();
                } else if (opcion == 3) {
                    this.selecPersonajeActivo(p);
                } else if (opcion == 4) {
                    this.RegistrarPersonaje(p);
                } else if (opcion == 5) {
                    this.DarDeBajaPersonaje();
                } else if (opcion == 6) {
                    this.mostrarHistorial();
                } else if (opcion == 7) {
                    this.mostrarRanking(p.getMapJugadores());
                } else if (opcion == 8 || opcion == 9) {
                    System.out.println("Esperamos volverte a ver pronto");
                }
            } else {
                this.desafiadoResuelve();
            }

        }
    }

    public void RegistrarPersonaje(Partida p) {
        List<Personaje> allCharacters = p.getListaPersonajes();
        Scanner scanner = new Scanner(System.in);
        boolean personajeElegido = false;

        for (int intento = 0; intento <= 2 && !personajeElegido; intento++) {
            System.out.println("Lista de personajes disponibles: ");
            for (int i = 0; i < allCharacters.size(); i++) {
                System.out.println((i + 1) + ". " + allCharacters.get(i).getNombre());
            }

            System.out.println("Seleccione un personaje:");
            int opc = scanner.nextInt();

            if (opc >= 1 && opc <= allCharacters.size()) {
                Personaje personajeElegidoTmp = allCharacters.get(opc - 1);
                System.out.println("Ha seleccionado a " + personajeElegidoTmp.getNombre() );
                personajeElegido = true;
                Personaje personajeNuevo = personajeElegidoTmp.clone();
                this.Personajes.add(personajeNuevo);
                this.PersonajeActivo = personajeNuevo;
            } else {
                System.out.println("Opción no válida. Por favor, seleccione un número válido");
            }
        }

        if (!personajeElegido) {
            System.out.println("Se han agotado los intentos para seleccionar un personaje");
        }
    }

    public void DarDeBajaPersonaje() {
        Scanner scanner = new Scanner(System.in);
        boolean personajeElegido = false;

        for (int intento = 0; intento <= 2 && !personajeElegido; intento++) {
            System.out.println("Lista de personajes disponibles: ");
            for (int i = 0; i < this.Personajes.size(); i++) {
                System.out.println((i + 1) + ". " + this.Personajes.get(i).getNombre());
            }

            System.out.println("Seleccione un personaje:");
            int opc = scanner.nextInt();

            if (opc >= 1 && opc <= this.Personajes.size()) {
                Personaje personajeElegidoTmp = this.Personajes.get(opc - 1);
                System.out.println("Ha seleccionado a " + personajeElegidoTmp.getNombre() );
                personajeElegido = true;

                System.out.println("¿Seguro que quiere dar de baja este personaje definitivamente? (si desea continuar escriba: 1234)");
                int conf = scanner.nextInt();
                if (conf == 1234){
                    this.Personajes.remove(opc-1);
                }
            } else {
                System.out.println("Opción no válida. Por favor, seleccione un número válido");
            }
        }

        if (!personajeElegido) {
            System.out.println("Se han agotado los intentos para seleccionar un personaje");
        }
    }

    public void Desafiar() {
        // TODO implement here
    }

    public void mostrarRanking(Map<String,Jugador> mapaJugadores) {
        // TODO implement here
    }

    private void pedirDesafiado() {
        // TODO implement here
    }

    public void seleccionApuesta() {
        // TODO implement here
    }

    public void selecPersonajeActivo(Partida p) {
        Scanner scanner = new Scanner(System.in);
        boolean personajeElegido = false;

        for (int intento = 0; intento <= 2 && !personajeElegido; intento++) {
            System.out.println("Lista de personajes disponibles:");
            for (int i = 0; i < this.Personajes.size(); i++) {
                System.out.println((i + 1) + ". " + this.Personajes.get(i).getNombre());
            }

            System.out.println("Seleccione un personaje:");
            int opt = scanner.nextInt();

            if (opt >= 1 && opt <= this.Personajes.size()) {
                Personaje personajeElegidoTmp = this.Personajes.get(opt - 1);

                boolean control = p.checkVersion(personajeElegidoTmp);

                if (!control) {
                    personajeElegidoTmp = p.solveVersion(personajeElegidoTmp);
                }

                this.PersonajeActivo = personajeElegidoTmp;
                System.out.println("Se ha seleccionado a " + this.PersonajeActivo.getNombre() + " como el personaje activo");
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
        // TODO implement here
    }

    public void mostrarHistorial() {
        // TODO implement here
    }

    public void bloquearse() {
        this.Bloqueado = !this.Bloqueado;
    }

    private int leerInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private String leerString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
