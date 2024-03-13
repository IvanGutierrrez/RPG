
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Operador extends Usuario implements Serializable {

    public void preguntarDetallesOperador() {
        boolean ok = false;
        int num = 0;
        while (!ok || num>2){
            System.out.println("Introduzca nombre");
            String nombre = this.leerString();
            this.setNombre(nombre);
            String contraseña;
            do {
                System.out.println("Introduzca contraseña");
                contraseña = this.leerString();
            } while (contraseña.length()>12 || contraseña.length()<8);
            this.setContraseña(contraseña);
            System.out.println("Introduzca 1 si esta de acuerdo con los datos introducidos");
            System.out.println("Nombre: " + this.getNombre());
            System.out.println("Contraseña: " + this.getContraseña());
            ok = this.leerString().equals("1");
            num++;
        }
        if (num>2){
            this.setContraseña(null);
        }
    }
    @Override
    public void Menu(Partida p) {
        // TODO implement here
    }

    public void revisarCombatesNoVal() {
        // TODO implement here
    }

    public void bloquearJugador(Map<String,Jugador> jugador) {
        // TODO implement here
    }

    public void desbloquearJugador(Map<String,Jugador> jugador) {
        // TODO implement here
    }

    public void editarPersonaje(Partida p) {
        // TODO implement here
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
