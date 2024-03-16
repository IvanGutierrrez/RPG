
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Partida implements Serializable {

    private transient Usuario UsuarioActivo;
    private Map<String,Jugador> MapJugadores;
    private Combate ListaDesafiosSinValidar;

    public Usuario getUsuarioActivo() {
        return UsuarioActivo;
    }

    public Map<String, Jugador> getMapJugadores() {
        return MapJugadores;
    }

    public void setMapJugadores(Map<String, Jugador> mapJugadores) {
        MapJugadores = mapJugadores;
    }

    public Combate getListaDesafiosSinValidar() {
        return ListaDesafiosSinValidar;
    }

    public void setListaDesafiosSinValidar(Combate listaDesafiosSinValidar) {
        ListaDesafiosSinValidar = listaDesafiosSinValidar;
    }

    public Personaje[] getListaPersonajes() {
        return ListaPersonajes;
    }

    public void setListaPersonajes(Personaje[] listaPersonajes) {
        ListaPersonajes = listaPersonajes;
    }

    private Personaje[] ListaPersonajes;

    public boolean noExiste(String s){
        Map<String,Jugador> mapa = this.getMapJugadores();
        boolean ok = true;
        for (Map.Entry<String, Jugador> entry : mapa.entrySet()) {
            String clave = entry.getKey();
            Jugador j = entry.getValue();
            if (j.getNRegistro().equals(s)){
                ok = false;
            }
        }
        return ok;
    }

    public Jugador coincidePass(String nick, String pass){
        Map<String,Jugador> mapa = this.getMapJugadores();
        for (Map.Entry<String, Jugador> entry : mapa.entrySet()) {
            String clave = entry.getKey();
            Jugador j = entry.getValue();
            if (j.getNick().equals(nick) && j.getPass().equals(pass)){
                return j;
            }
        }
        return null;
    }

    public boolean nickUnico(String s){
        Map<String,Jugador> mapa = this.getMapJugadores();
        boolean ok = true;
        for (Map.Entry<String, Jugador> entry : mapa.entrySet()) {
            String clave = entry.getKey();
            Jugador j = entry.getValue();
            if (j.getNick().equals(s)){
                ok = false;
            }
        }
        return ok;
    }
    public Partida(){

    }

    private void serializar(Partida p) {
        // TODO implement here
    }

    /**
     * 
     */
    public Partida deserializar() {
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
