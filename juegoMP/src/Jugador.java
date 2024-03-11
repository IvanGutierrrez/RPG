
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Jugador extends Usuario implements Serializable {

    private String NºRegistro;

    public Personaje PersonajeActivo;

    private boolean Bloqueado;

    private Combate Desafio;

    private Personaje[] Personajes;

    private Combate[] RegistrosCombates;

    private Combate[] HistorialCombates;

    private String ultimaDerrota;

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
}