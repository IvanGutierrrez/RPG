
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public abstract class Esbirro implements Serializable, Cloneable{

    public String Nombre;

    public double Salud;


    public String getNombre(){return this.Nombre;}

    public double getSalud(){return this.Salud;}

    @Override
    public Esbirro clone() {
        try {
            return (Esbirro) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    protected void ModificarEsbirro(){

    }

    protected String inputNombre() {
        Scanner scanner = new Scanner(System.in);
        String nombre;
        do {
            System.out.println("Ingrese el nombre del Esbirro:");
            nombre = scanner.nextLine().trim();
        } while (nombre.isEmpty());
        return nombre;
    }

    protected double inputSalud() {
        Scanner scanner = new Scanner(System.in);
        double salud;
        do {
            System.out.println("Ingrese la cantidad de salud:");
            salud = scanner.nextDouble();
            if (salud <= 0) {
                System.out.println("La cantidad de salud debe ser mayor que 0.");
            }
        } while (salud <= 0);
        return salud;
    }

    protected int leerInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public abstract double obtenerSalud();

}