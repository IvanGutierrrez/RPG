
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
            System.out.println("Ingrese la lealtad (1 para ALTA, 2 para NORMAL, 3 para BAJA):");
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    lealtad = "ALTA";
                    break;
                case 2:
                    lealtad = "NORMAL";
                    break;
                case 3:
                    lealtad = "BAJA";
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione 1, 2 o 3.");
                    lealtad = "";
            }
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

    @Override
    public double obtenerSalud() {
        return super.Salud;
    }

}