
import java.io.*;
import java.util.*;

/**
 * 
 */
public class GestionInicioSesion {

    public void menuOptions() throws IOException {
        int opcion = 0;
        asegurarArchivos(); // Asegurar que los archivos y directorios existen
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
            } else if (opcion == 3){
                System.out.println("Hasta la proxima");
            } else{
                System.out.println("Opcion no valida");
            }
        }
    }

    public void inicioSesion() throws IOException {
        if (this.partidaExits()){
            Partida p = new Partida();
            p = p.deserializar();
            int ok = 0;
            while (ok<2){
                System.out.println("Introduzca su nick");
                String nick = this.leerString();
                if (!p.nickUnico(nick)){//si el nick no es unico significa que ya existe un jugador con ese nick
                    System.out.println("Introduzca su pass");
                    String pass = this.leerString();
                    Usuario u = p.coincidePass(nick,pass);
                    if (u != null) {
                        p.setUsuarioActivo(u);
                        p.Play();
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
                        p.Play();
                    } else{
                        System.out.println("No coincide la pass");
                    }
                } else{
                    System.out.println("Ese nick no existe, vuelva a introducirlo o registrese");
                }
                ok++;
            }
            if (ok >= 2) {
                System.out.println("Se ha equivocado dos veces, va a volver al menu principal");
            }
        } else{
            System.out.println("Es nuestro primer usuario, registrese");
        }
    }

    public void registrarse() throws IOException {
        boolean ok = false;
        Partida p = new Partida();
        if (this.partidaExits()){ //si existen archivos en la carpeta
            p = p.deserializar();
        }//Si esta vacia la carpeta no deserializamos y dejamos la partida a null
        System.out.println("Introduzca contraseña especial");
        String passEspecial = this.leerString();
        if (this.coincide(passEspecial)){
            System.out.println("Bienvenido operador");
            Operador op1 = new Operador();
            op1.preguntarDetallesOperador();
            if (op1.getPass() != null){
                p.setUsuarioActivo(op1);
                this.añadirOperador(op1);
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

    private void asegurarArchivos() {
        File partidaDirectorio = new File("Trabajo-MP/datos/partida");
        File operadoresDirectorio = new File("Trabajo-MP/datos/operadores");
        File operadorArchivo = new File("Trabajo-MP/datos/operadores/operador");

        // Si los directorios o archivos no existen, se crean
        if (!partidaDirectorio.exists()) {
            partidaDirectorio.mkdirs(); // Crea el directorio y cualquier directorio padre necesario
        }
        if (!operadoresDirectorio.exists()) {
            operadoresDirectorio.mkdirs(); // Crea el directorio y cualquier directorio padre necesario
        }
        if (!operadorArchivo.exists()) {
            try {
                operadorArchivo.createNewFile(); // Crea el archivo
            } catch (IOException e) {
                e.printStackTrace(); // Manejo de excepciones en caso de error
            }
        }
    }

    private boolean partidaExits() {
        File directorio = new File("Trabajo-MP/datos/partida");
        if (directorio.exists() && directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();
            if (archivos != null && archivos.length > 0) {
                return true;
            }
        }
        return false;
    }

    private void añadirOperador(Operador op) throws IOException {
        File file = new File("Trabajo-MP/datos/operadores/operador");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)); // true para permitir la escritura al final del archivo
        writer.write(op.getNombre() + "," + op.getNick() + "," + op.getPass()); // Escribir el nuevo operador al final del archivo
        writer.newLine(); // Nueva línea para el próximo operador
        writer.close(); // Cerrar el escritor
    }

    private String coincidePass(String nick, String pass) throws IOException {
        File archivo = new File("Trabajo-MP/datos/operadores/operador");
        FileReader fileReader = new FileReader(archivo);
        BufferedReader buf = new BufferedReader(fileReader);
        String linea = buf.readLine(); // Lees la primera línea
        String[] result;
        while (linea != null) {
            result = linea.split(",");
            if (result[1].equals(nick) && result[2].equals(pass)) {
                buf.close(); // Importante cerrar el BufferedReader
                return result[0];
            }
            linea = buf.readLine(); // Lees la siguiente línea
        }
        buf.close(); // Importante cerrar el BufferedReader
        return null;
    }

    private boolean existeOperador(String nick) throws IOException {
        File archivo = new File("Trabajo-MP/datos/operadores/operador");
        FileReader fileReader = new FileReader(archivo);
        BufferedReader buf = new BufferedReader(fileReader);
        String linea = buf.readLine(); // Lees la primera línea
        String[] result;
        while ((linea = buf.readLine()) != null) {
            result = linea.split(",");
            if (result[1] == nick){
                buf.close(); // Importante cerrar el BufferedReader
                return true;
            }
        }
        buf.close(); // Importante cerrar el BufferedReader
        return false;
    }

    private boolean coincide(String passEspecial) throws IOException {
        File archivo = new File("Trabajo-MP/datos/operadores/operador");
        FileReader fileReader = new FileReader(archivo);
        BufferedReader buf = new BufferedReader(fileReader);
        String linea = buf.readLine(); // Lees la primera línea
        if (linea == null){
            return false;
        }
        buf.close(); // Importante cerrar el BufferedReader
        return linea.equals(passEspecial);
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
