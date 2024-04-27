
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public abstract class Esbirro implements Serializable, Cloneable{

    public String Nombre;

    public double Salud;

    private transient Scanner scanner = ScannerSingleton.getInstance();


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
        String nombre;
        do {
            System.out.println("Ingrese el nombre del Esbirro:");
            nombre = scanner.nextLine().trim();
        } while (nombre.isEmpty());
        return nombre;
    }

    protected double inputSalud() {
        double salud;
        do {
            System.out.println("Ingrese la cantidad de salud (entre 1 y 3):");
            salud = leerDouble();
            if (salud < 1 || salud > 3) {
                System.out.println("La cantidad de salud debe estar entre 1 y 3, inclusive.");
            }
        } while (salud < 1 || salud > 3);
        return salud;
    }

    protected int leerInt(){
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

    protected double leerDouble(){
        boolean ok = false;
        double num = 0;
        while (!ok) {
            try {
                String n = scanner.nextLine();
                num = Double.parseDouble(n);
                ok = true;
            } catch (NumberFormatException e){
                System.out.println("Caracter no valido, introduzca un numero real");
            }
        }
        return num;
    }

    public abstract double obtenerSalud();

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.scanner = ScannerSingleton.getInstance();
    }

}