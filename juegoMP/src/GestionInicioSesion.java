
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * 
 */
public class GestionInicioSesion {

    public void menuOptions() {
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

    public void inicioSesion() {
        // TODO implement here
    }

    public void registrarse() {
        boolean ok = false;
        // Crear un objeto File con la ruta de la carpeta
        File directorio = new File("datos/partida");
        if (directorio.exists() && directorio.isDirectory()) {//si la carpeta existe
            // Obtener la lista de archivos en el directorio
            File[] archivos = directorio.listFiles();
            Partida p = new Partida();
            if (archivos != null){ //si existen archivos en la carpeta
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
            } else{//falta comprobar si el nick no esta ya en algun jugador
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
        int num = scanner.nextInt();
        scanner.close();
        return num;
    }

    private String leerString(){
        Scanner scanner = new Scanner(System.in);
        String valor = scanner.nextLine();
        scanner.close();
        return valor;
    }

}
