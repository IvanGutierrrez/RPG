
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Operador extends Usuario implements Serializable {

    public Operador(){
        this.setNick(null);
        this.setPass(null);
        this.setNombre(null);
    }
    public Operador(String name, String nick, String pass){
        this.setNick(nick);
        this.setPass(pass);
        this.setNombre(name);
    }

    public void preguntarDetallesOperador() {
        boolean ok = false;
        int num = 0;
        while (!ok || num<2){
            System.out.println("Introduzca nombre");
            String nombre = this.leerString();
            this.setNombre(nombre);
            System.out.println("Introduzca nick");
            String nick = this.leerString();
            this.setNick(nick);
            String pass;
            do {
                System.out.println("Introduzca pass");
                pass = this.leerString();
            } while (pass.length()>12 || pass.length()<8);
            this.setPass(pass);
            System.out.println("Introduzca 1 si esta de acuerdo con los datos introducidos");
            System.out.println("Nick: " + this.getNick());
            System.out.println("Nombre: " + this.getNombre());
            System.out.println("Contraseña: " + this.getPass());
            ok = this.leerString().equals("1");
            num++;
        }
        if (num>2){
            this.setPass(null);
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
