
import java.io.*;
import java.util.*;
import java.util.List;

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

    public List<Personaje> getListaPersonajes() {
        return ListaPersonajes;
    }

    public void setListaPersonajes(List<Personaje> listaPersonajes) {
        ListaPersonajes = listaPersonajes;
    }

    private List<Personaje> ListaPersonajes;

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
            Jugador j = entry.getValue();
            if (j.getNick().equals(nick) && j.getPass().equals(pass)){
                return j;
            }
        }
        return null;
    }

    public boolean nickUnico(String s){
        Map<String,Jugador> mapa = this.getMapJugadores();
        if (mapa == null){
            return true;
        } else {
            for (Map.Entry<String, Jugador> entry : mapa.entrySet()) {
                Jugador j = entry.getValue();
                if (j.getNick().equals(s)) {
                    return false;
                }
            }
        }
        return true;
    }
    public Partida(){

    }

    private void serializar() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("Trabajo-MP/src/datos/partida"); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            // Escribe la instancia actual de la clase Game en el archivo
            objectOutputStream.writeObject(this);

            System.out.println("Partida guardada correctamente");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    public Partida deserializar() {
        try (FileInputStream fileInputStream = new FileInputStream("Trabajo-MP/src/datos/partida"); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            // Leer la instancia de la clase Game desde el archivo
            Partida loadedGame = (Partida) objectInputStream.readObject();

            System.out.println("Partida cargada correctamente");
            return loadedGame;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("No se an encontrado este fichero ERROR");
            System.exit(0);
        }
        return  null;
    }

    public void Play() {
        Usuario jugador = this.getUsuarioActivo();
        jugador.Menu(this);
        this.serializar();
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

    public Personaje solveVersion(Personaje personaje) {
        // TODO implement here
        return null;
    }
}
