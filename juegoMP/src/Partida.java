
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Partida implements Serializable {

    public transient Usuario UsuarioActivo;
    private Map<String,Usuario> MapJugadores;
    private Combate ListaDesafiosSinValidar;
    private Personaje[] ListaPersonajes;

    public Partida(){

    }

    private void Serializar(Partida p) {
        // TODO implement here
    }

    /**
     * 
     */
    public Partida Deserializar() {
        // TODO implement here
        return null;
    }

    public void Play() {
        // TODO implement here
    }

    public void setUsuarioActivo(Usuario u) {
        // TODO implement here
    }
    public void addChallenge(Combate c) {
        // TODO implement here
    }

    public Combate getCombatesNoVal() {
        // TODO implement here
        return null;
    }

    public void darDeBajaUsuario() {
        // TODO implement here
    }

    public void getPersonaje() {
        // TODO implement here
    }

    public Personaje seleccionPersonaje() {
        // TODO implement here
        return null;
    }

    public Boolean checkVersion(Personaje p) {
        // TODO implement here
        return null;
    }

}