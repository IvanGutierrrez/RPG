
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * 
 */
public class Partida implements Serializable {

    private transient Usuario UsuarioActivo;
    private Map<String,Usuario> MapUsuarios;
    private Queue<Combate> combateQueue;

    public Usuario getUsuarioActivo() {
        return UsuarioActivo;
    }

    public Map<String,Usuario> getMapUsuarios() {
        return MapUsuarios;
    }

    public void setMapJugadores(Map<String,Usuario> MapUsuarios) {
        this.MapUsuarios = MapUsuarios;
    }

    public Queue<Combate> getCombateQueue() {
        return combateQueue;
    }

    public void setCombateQueue(Queue<Combate> combateQueue) {
        this.combateQueue = combateQueue;
    }

    public List<Personaje> getListaPersonajes() {
        return ListaPersonajes;
    }

    public void setListaPersonajes(List<Personaje> listaPersonajes) {
        ListaPersonajes = listaPersonajes;
    }

    private List<Personaje> ListaPersonajes;

    public boolean noExiste(String s){
        Map<String,Usuario> mapa = this.getMapUsuarios();
        boolean ok = true;
        for (Map.Entry<String, Usuario> entry : mapa.entrySet()) {
            String clave = entry.getKey();
            Usuario u = entry.getValue();
            if (u instanceof Jugador jugador) {
                if (jugador.getNRegistro().equals(s)) {
                    ok = false;
                }
            }
        }
        return ok;
    }

    public Usuario coincidePass(String nick, String pass){
        Map<String,Usuario> mapa = this.getMapUsuarios();
        for (Map.Entry<String, Usuario> entry : mapa.entrySet()) {
            Usuario u = entry.getValue();
            if (u.getNick().equals(nick) && u.getPass().equals(pass)){
                return u;
            }
        }
        return null;
    }

    public boolean nickUnico(String s){
        Map<String,Usuario> mapa = this.getMapUsuarios();
        if (mapa == null){
            return true;
        } else {
            for (Map.Entry<String, Usuario> entry : mapa.entrySet()) {
                Usuario u = entry.getValue();
                if (u.getNick().equals(s)) {
                    return false;
                }
            }
        }
        return true;
    }
    public Partida(){

    }

    public void serializar() {
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
        this.combateQueue.add(c);
    }




    public void darDeBajaUsuario(Usuario user) {
        Map<String,Usuario> mapa = this.getMapUsuarios();
        for (Map.Entry<String, Usuario> entry : mapa.entrySet()) {
            Usuario u = entry.getValue();
            if (u == user) {
                this.getMapUsuarios().remove(entry.getKey());
                break;
            }
        }

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
