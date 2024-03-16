
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Jugador extends Usuario implements Serializable {

    private String NRegistro;

    private Personaje PersonajeActivo;

    private boolean Bloqueado;

    private Combate Desafio;

    private Personaje[] Personajes;

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

    public Personaje[] getPersonajes() {
        return Personajes;
    }

    public void setPersonajes(Personaje[] personajes) {
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
        // TODO implement here
    }

    public void RegistrarPersonaje() {
        // TODO implement here
    }

    public void DarDeBajaPersonaje() {
        // TODO implement here
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

    public void selecPersonajeActivo() {
        // TODO implement here
    }

    private void desafiadoResuelve() {
        // TODO implement here
    }

    public void mostrarHistorial() {
        // TODO implement here
    }

    public void bloquearse() {
        // TODO implement here
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
