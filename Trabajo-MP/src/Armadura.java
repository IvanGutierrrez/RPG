
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Armadura extends Equipo implements Serializable, Cloneable{

    private double ModATQ;

    private transient Scanner scanner = ScannerSingleton.getInstance();

    public Armadura() {
        buildArmaduraFromInput();
    }

    public Armadura(int modatq) {
        this.ModATQ=modatq;
    }

    public void setModATQ(double modATQ) {
        ModATQ = modATQ;
    }


    public void buildArmaduraFromInput() {
        this.Nombre = inputNombre();
        this.Modificador = inputModificador();
        this.ModATQ = inputModATK();
    }

    protected void EditarArmadural(){
        int ans;
        do {
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar Modificador");
            System.out.println("3. Modificar Modificador Ataque");
            System.out.println("4. Salir");
            ans = leerInt();
            if(ans == 1){
                System.out.println("Nombre actual: " + this.Nombre);
                this.Nombre = inputNombre();
            } else if (ans == 2) {
                System.out.println("Ataque actual: " + this.Modificador);
                this.Modificador=inputModificador();
            } else if (ans == 3) {
                System.out.println("Defensa actual: " + this.ModATQ);
                this.ModATQ=inputModATK();
            }

        } while (ans!=4);

    }

    protected String inputNombre() {
        String nombre;
        do {
            System.out.println("Ingrese nombre de la armadura:");
            nombre = scanner.nextLine().trim();
        } while (nombre.isEmpty());
        return nombre;
    }

    protected double inputModificador() {
        double modif;
        do {
            System.out.println("Ingrese el valor del modificador de defensa (entre 1 y 3):");
            modif = leerDouble();
            if (modif < 1 || modif > 3) {
                System.out.println("El valor debe estar entre 1 y 3, inclusive.");
            }
        } while (modif < 1 || modif > 3);
        return modif;
    }

    protected double inputModATK() {
        double modif;
        do {
            System.out.println("Ingrese el valor del modificador de ataque (entre 0 y 3):");
            modif = leerDouble();
            if (modif < 0 || modif > 3) {
                System.out.println("El valor debe estar entre 0 y 3, inclusive.");
            }
        } while (modif < 0 || modif > 3);
        return modif;
    }

    private double leerDouble() {
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

    private int leerInt(){
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
    @Override
    public Armadura clone() {
        return (Armadura) super.clone();
    }

    public double getModATQ() { return ModATQ;}

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.scanner = ScannerSingleton.getInstance();
    }
}