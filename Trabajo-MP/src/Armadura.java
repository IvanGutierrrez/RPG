
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
        Scanner scanner = new Scanner(System.in);
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
            System.out.println("Ingrese el valor del modificador de ataque (entre 1 y 3):");
            modif = leerDouble();
            if (modif < 1 || modif > 3) {
                System.out.println("El valor debe estar entre 1 y 3, inclusive.");
            }
        } while (modif < 1 || modif > 3);
        return modif;
    }

    private double leerDouble() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    private int leerInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    @Override
    public Armadura clone() {
        return (Armadura) super.clone();
    }

    public double getModATQ() { return ModATQ;}
}