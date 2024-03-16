
import java.io.*;
import java.util.*;

/**
 * 
 */
public class GestionInicioSesion {

    public void menuOptions() throws IOException {
        int opcion = 0;
        while (opcion != 3){
            System.out.println("Eliga la opción");
            System.out.println("1.-Registrarse");
            System.out.println("2.-Iniciar sesión");
            System.out.println("3.-Salir");
            opcion = this.leerInt();
            if (opcion == 1){
                this.registrarse();
            } else if (opcion == 2){
                this.inicioSesion();
            } else{
                System.out.println("Hasta la proxima");
            }
        }
    }

    private boolean partidaExits(){
        // Crear un objeto File con la ruta de la carpeta
        File directorio = new File("datos/partida");
        if (directorio.exists() && directorio.isDirectory()) {//si la carpeta existe
            // Obtener la lista de archivos en el directorio
            File[] archivos = directorio.listFiles();
            if (archivos != null){ //si existen archivos en la carpeta
                return true;
            }
        }
        return false;
    }

    public void inicioSesion() throws IOException {
        if (this.partidaExits()){
            Partida p = new Partida();
            p.deserializar();
            int ok = 0;
            while (ok<2){
                System.out.println("Introduzca su nick");
                String nick = this.leerString();
                if (!p.nickUnico(nick)){//si el nick no es unico significa que ya existe un jugador con ese nick
                    System.out.println("Introduzca su pass");
                    String pass = this.leerString();
                    Jugador j = p.coincidePass(nick,pass);
                    if (j != null) {
                        p.setUsuarioActivo(j);
                    } else{
                        System.out.println("No coincide la pass");
                    }
                } else if(this.existeOperador(nick)){
                    System.out.println("Introduzca su pass");
                    String pass = this.leerString();
                    String name = this.coincidePass(nick,pass);
                    if (name != null) {
                        Operador op = new Operador(name, nick, pass);
                        p.setUsuarioActivo(op);
                    } else{
                        System.out.println("No coincide la pass");
                    }
                } else{
                    System.out.println("Ese nombre no existe, vuelva a introducirlo o registrese");
                }
                ok++;
            }
        } else{
            System.out.println("Es nuestro primer usuario, registrese");
        }
    }

    public void registrarse() {
        boolean ok = false;
        Partida p = new Partida();
        if (this.partidaExits()){ //si existen archivos en la carpeta
            p = p.deserializar();
        }//Si esta vacia la carpeta no deserializamos y dejamos la partida a null
        System.out.println("Introduzca contraseña especial");
        String passEspecial = this.leerString();
        if (this.coincide(passEspecial)){
            Operador op1 = new Operador();
            op1.preguntarDetallesOperador();
            if (op1.getPass() != null){
                p.setUsuarioActivo(op1);
                ok = true;
            }
        } else{
            Jugador j1 = new Jugador();
            j1.preguntarDetallesJugador(p);
            if (j1.getPass() != null) {
                p.setUsuarioActivo(j1);
                p.getMapJugadores().put(j1.getNick(), j1);
                ok = true;
            }
        }
        if (ok) {
            p.Play();
        }
    }

    private String coincidePass(String nick, String pass) throws IOException {
        Reader in = new FileReader("datos/operadores");
        BufferedReader buf = new BufferedReader(in);
        String linea = buf.readLine();//lees la primera linea
        String[] result = linea.split(",");
        while ((linea = buf.readLine()) != null) {
            result = linea.split(",");
            if (result[1] == nick && result[2] == pass){
                return result[0];
            }
        }
        return null;
    }

    private boolean existeOperador(String nick) throws IOException {
        Reader in = new FileReader("datos/operadores");
        BufferedReader buf = new BufferedReader(in);
        String linea = buf.readLine();//lees la primera linea
        String[] result = linea.split(",");
        while ((linea = buf.readLine()) != null) {
            result = linea.split(",");
            if (result[1] == nick){
                return true;
            }
        }
        return false;
    }

    private boolean coincide(String nike){
        // Crear un objeto File con la ruta de la carpeta
        File directorio = new File("datos/operadores");
        if (directorio.exists() && directorio.isDirectory()) {
            // Obtener el primer archivo del directorio
            // Obtener la lista de archivos en el directorio
            File[] archivos = directorio.listFiles();

            // Leer el contenido del primer archivo de texto (si hay alguno)
            if (archivos != null && archivos.length > 0) {
                File primerArchivo = archivos[0];
                if (primerArchivo.isFile() && primerArchivo.getName().endsWith(".txt")) {
                    try (BufferedReader br = new BufferedReader(new FileReader(primerArchivo))) {
                        String primeraLinea = br.readLine();
                        if (primeraLinea != null && primeraLinea.equals(nike)) {
                            return true;
                        }
                    } catch (IOException e) {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public void salir() {
        // TODO implement here
    }

    public Usuario preguntarDetallesIS() {
        // TODO implement here
        return null;
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
