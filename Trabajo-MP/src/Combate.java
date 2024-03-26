
import java.util.*;

/**
 * 
 */
public class Combate {

    private Jugador JugadorRetador;

    private Jugador JugadorRetado;

    private double OroApostado;

    private int Rondas;

    private String Fecha;

    private Jugador Ganador;

    private Jugador[] JugadorConEsbirrosSinDerrotar;

    private Modificador[] Modificadores;

    private boolean Valido;

    private Personaje PersonajeRetador;

    private Personaje PersonajeRetado;

    public void calcularResutado() {
        // TODO implement here
    }

    public void mostrarResultado() {
        // TODO implement here
    }

    public void cancelarCombate() {
        // TODO implement here
    }

    public void combatir() {
        // TODO implement here
    }

    public Jugador getJugadorRetador() {
        return JugadorRetador;
    }

    public void setJugadorRetador(Jugador jugadorRetador) {
        JugadorRetador = jugadorRetador;
    }

    public Jugador getJugadorRetado() {
        return JugadorRetado;
    }

    public void setJugadorRetado(Jugador jugadorRetado) {
        JugadorRetado = jugadorRetado;
    }

    public double getOroApostado() {
        return OroApostado;
    }

    public void setOroApostado(double oroApostado) {
        OroApostado = oroApostado;
    }

    public int getRondas() {
        return Rondas;
    }

    public void setRondas(int rondas) {
        Rondas = rondas;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public Jugador getGanador() {
        return Ganador;
    }

    public void setGanador(Jugador ganador) {
        Ganador = ganador;
    }

    public Jugador[] getJugadorConEsbirrosSinDerrotar() {
        return JugadorConEsbirrosSinDerrotar;
    }

    public void setJugadorConEsbirrosSinDerrotar(Jugador[] jugadorConEsbirrosSinDerrotar) {
        JugadorConEsbirrosSinDerrotar = jugadorConEsbirrosSinDerrotar;
    }

    public Modificador[] getModificadores() {
        return Modificadores;
    }

    public void setModificadores(Modificador[] modificadores) {
        Modificadores = modificadores;
    }

    public boolean isValido() {
        return Valido;
    }

    public void setValido(boolean valido) {
        Valido = valido;
    }

    public Personaje getPersonajeRetador() {
        return PersonajeRetador;
    }

    public void setPersonajeRetador(Personaje personajeRetador) {
        PersonajeRetador = personajeRetador;
    }

    public Personaje getPersonajeRetado() {
        return PersonajeRetado;
    }

    public void setPersonajeRetado(Personaje personajeRetado) {
        PersonajeRetado = personajeRetado;
    }
}