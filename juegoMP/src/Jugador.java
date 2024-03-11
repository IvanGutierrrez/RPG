
import java.util.*;

/**
 * 
 */
public class Jugador extends Usuario {

    /**
     * Default constructor
     */
    public Jugador() {
    }

    /**
     * 
     */
    private String NºRegistro;

    /**
     * 
     */
    public Personaje PersonajeActivo;

    /**
     * 
     */
    private boolean Bloqueado;

    /**
     * 
     */
    private Combate Desafio;

    /**
     * 
     */
    private Personaje[] Personajes;

    /**
     * 
     */
    private Combate[] RegistrosCombates;

    /**
     * 
     */
    private Combate[] HistorialCombates;

    /**
     * 
     */
    private String ultimaDerrota;

    /**
     * @param Partida
     */
    public void Menu(void Partida) {
        // TODO implement here
    }

    /**
     * 
     */
    public void RegistrarPersonaje() {
        // TODO implement here
    }

    /**
     * 
     */
    public void DarDeBajaPersonaje() {
        // TODO implement here
    }

    /**
     * 
     */
    public void Desafiar() {
        // TODO implement here
    }

    /**
     * 
     */
    public void mostrarRanking( Map<Nick,Jugador>)() {
        // TODO implement here
    }

    /**
     * 
     */
    private void pedirDesafiado() {
        // TODO implement here
    }

    /**
     * 
     */
    public void seleccionApuesta() {
        // TODO implement here
    }

    /**
     * 
     */
    public void selecPersonajeActivo() {
        // TODO implement here
    }

    /**
     * 
     */
    private void desafiadoResuelve() {
        // TODO implement here
    }

    /**
     * 
     */
    public void mostrarHistorial() {
        // TODO implement here
    }

    /**
     * 
     */
    public void bloquearse() {
        // TODO implement here
    }

    /**
     * @param Partida
     */
    public abstract void Menu(void Partida);

}