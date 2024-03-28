
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * 
 */
public class Partida implements Serializable {


    public Partida(){
        Map<String,Usuario> m = new HashMap<>();
        this.setMapJugadores(m);
        Queue<Combate> queue = new LinkedList<>();
        this.setCombateQueue(queue);
        this.setUsuarioActivo(null);
        List<Personaje> lista = new ArrayList<>();
        this.setListaPersonajes(lista);
    }
    private transient Usuario UsuarioActivo;
    private Map<String,Usuario> MapUsuarios;
    private Queue<Combate> combateQueue;
    private List<Personaje> ListaPersonajes;

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
                System.out.println("ayuda");
                return u;
            }
        }
        return null;
    }

    public boolean nickUnico(String s) {
        Map<String, Usuario> mapa = this.getMapUsuarios();
        if (mapa == null) {
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

    public void serializar() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Trabajo-MP/datos/partida/partida.dat"));
            oos.writeObject(this);
            oos.close();
            System.out.println("Partida guardada correctamente");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al intentar serializar la partida");
        }
    }

    /**
     * 
     */
    public Partida deserializar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Trabajo-MP/datos/partida/partida.dat"))) {

            // Leer la instancia de la clase Game desde el archivo
            Partida loadedGame = (Partida) ois.readObject();

            System.out.println("Partida cargada correctamente");
            return loadedGame;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Creación de fichero nuevo");
            return null;
        }
    }

    public void Play() {
        this.getUsuarioActivo().Menu(this);
        this.serializar();
    }

    public void setUsuarioActivo(Usuario u) {
        this.UsuarioActivo = u;
    }
    public void addChallenge(Combate c) {
        this.combateQueue.add(c);
    }

    public void darDeBajaUsuario(Usuario user) {
        int conf = this.leerInt();
        System.out.println("¿Esta seguro de que quiere dar de baja este usuario? (1234 para confirmar)");
        if (conf == 1234) {
            Map<String, Usuario> mapa = getMapUsuarios();
            for (Map.Entry<String, Usuario> entry : mapa.entrySet()) {
                Usuario u = entry.getValue();
                if (u == user) {
                    this.getMapUsuarios().remove(entry.getKey());
                    break;
                }
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

    public Personaje checkVersion(Personaje p) {
        String nombre = p.getNombre();
        for (Personaje personje: this.ListaPersonajes){
            if (Objects.equals(personje.getNombre(), nombre)){
                if (personje.getVersion() == p.getVersion()){
                    return p;
                } else {
                    return this.solveVersion(p,personje);
                }
            }
        }
        return null;
    }

    private Personaje solveVersion(Personaje pOutdated, Personaje pNew) {
        Double oro = pOutdated.getOro();
        pOutdated = pNew.clone();
        pOutdated.setOro(oro);
        System.out.println("El personaje ha sido actualizado por un operador");
        pOutdated.gestionEquipamiento();
        return pOutdated;
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
