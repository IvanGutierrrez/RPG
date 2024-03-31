
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Humano extends Esbirro implements Serializable, Cloneable {

    private String Lealtad;


    @Override
    public Humano clone() {
        return (Humano) super.clone();
    }

    public Humano(){
        HumanoFromInput();
    }

    private void HumanoFromInput(){
        this.Nombre = inputNombre();
        this.Salud = inputSalud();
        this.Lealtad = inputLealtad();
    }

    private String inputLealtad() {
        Scanner scanner = new Scanner(System.in);
        String lealtad;
        do {
            System.out.println("Ingrese la lealtad:");
            lealtad = scanner.nextLine().trim();
        } while (lealtad.isEmpty());
        return lealtad;
    }

    @Override
    protected void ModificarEsbirro(){
        int ans;
        do {
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar salud");
            System.out.println("3. Modificar lealtad");
            System.out.println("4. Salir");
            ans = leerInt();
            if(ans == 1){
                System.out.println("Nombre actual: " + this.Nombre);
                this.Nombre = inputNombre();
            } else if (ans == 2) {
                System.out.println("Salud actual: " + this.Salud);
                this.Salud=inputSalud();
            } else if (ans == 3) {
                System.out.println("Lealtad actual: " + this.Lealtad);
                this.Lealtad=inputLealtad();
            }
        } while (ans!=4);
    }

}