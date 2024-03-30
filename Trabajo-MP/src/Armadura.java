
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

    private void buildArmaduraFromInput() {
        Scanner scanner = new Scanner(System.in);
        String name;
        do {
            System.out.println("Ingrese nombre del arma:");
            name = scanner.nextLine();
        } while (!name.isEmpty());

        Double modificador;
        do {
            System.out.println("Ingrese el valor del modificador:");
            modificador = scanner.nextDouble();
        } while (modificador < 0);

        double modATQ;
        do {
            System.out.println("Ingrese el valor de modATQ:");
            modATQ = scanner.nextDouble();
        } while (modATQ < 0);


        this.Nombre = name;
        this.Modificador = modificador;
        this.ModATQ = modATQ;

    }


    @Override
    public Armadura clone() {
        return (Armadura) super.clone();
    }

    public double getModATQ() { return ModATQ;}
}