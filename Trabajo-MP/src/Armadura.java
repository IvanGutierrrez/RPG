
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Armadura extends Equipo implements Serializable, Cloneable{

    private double ModATQ;



    public Armadura() {
        buildArmaduraFromInput();
    }


    public void buildArmaduraFromInput() {
        this.Nombre = inputNombre();
        this.Modificador = inputModificador();
        this.ModATQ = inputModATK();
    }

    protected String inputNombre() {
        Scanner scanner = new Scanner(System.in);
        String nombre;
        do {
            System.out.println("Ingrese nombre del arma:");
            nombre = scanner.nextLine().trim();
        } while (nombre.isEmpty());
        return nombre;
    }

    protected double inputModificador() {
        double modif;
        do {
            System.out.println("Ingrese el valor del modificador:");
            modif = leerDouble();
            if (modif <= 0) {
                System.out.println("El valor debe ser mayor que 0.");
            }
        } while (modif <= 0);
        return modif;
    }

    protected double inputModATK() {
        double modif;
        do {
            System.out.println("Ingrese el valor del ModDFS:");
            modif = leerDouble();
            if (modif <= 0) {
                System.out.println("El valor debe ser mayor que 0.");
            }
        } while (modif <= 0);
        return modif;
    }

    private double leerDouble() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }
    @Override
    public Armadura clone() {
        return (Armadura) super.clone();
    }

    public double getModATQ() { return ModATQ;}
}