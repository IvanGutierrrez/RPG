
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

/**
 * 
 */
public abstract class Usuario implements Serializable {

    private String Nick;
    protected String Nombre;

    protected String pass;

    public String getNick() {
        return Nick;
    }

    public void setNick(String nick) {
        Nick = nick;
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public abstract void Menu(Partida p) throws IOException;

    private int leerInt(){
        Scanner scanner = new Scanner(System.in);
        boolean ok = false;
        int num = 0;
        while (!ok) {
            try {
                String n = scanner.nextLine();
                num = Integer.parseInt(n);
                ok = true;
            } catch (NumberFormatException e){
                System.out.println("Caracter no valido, introduzca un entero");
            }
        }
        return num;
    }

    private String leerString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
