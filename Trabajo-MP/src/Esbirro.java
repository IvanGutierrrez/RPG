
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
            System.out.println("Ingrese la cantidad de salud (entre 1 y 3):");
            salud = scanner.nextDouble();
            if (salud < 1 || salud > 3) {
                System.out.println("La cantidad de salud debe estar entre 1 y 3, inclusive.");
            }
        } while (salud < 1 || salud > 3);
        return salud;
    }

    protected int leerInt(){
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

    protected double leerDouble(){
        Scanner scanner = new Scanner(System.in);
        boolean ok = false;
        double num = 0;
        while (!ok) {
            try {
                String n = scanner.nextLine();
                num = Integer.parseInt(n);
                ok = true;
            } catch (NumberFormatException e){
                System.out.println("Caracter no valido, introduzca un numero real");
            }
        }
        return num;
    }

    public abstract double obtenerSalud();

}