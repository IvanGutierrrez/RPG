
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Arma extends Equipo implements Serializable, Cloneable {

    private double ModDFS;

    private boolean Tipo;


    @Override
    public Arma clone() {
        return (Arma) super.clone();
    }

    public boolean getTipo() {
        return Tipo;
    }

    public double getModDFS() { return ModDFS;}

    public void setTipo(boolean tipo) {
        Tipo = tipo;
    }

    public Arma(){
        buildArmaFromInput();
    }


    public void buildArmaFromInput() {
        this.Nombre = inputNombre();
        this.Modificador = inputModificador();
        this.Tipo = inputTipo();
        this.ModDFS = inputModDFS();
    }

    protected void EditarArma(){
        int ans;
        do {
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar Modificador");
            System.out.println("3. Modificar Modificador Defensa");
            System.out.println("4. Modificar Tipo");
            System.out.println("5. Salir");
            ans = leerInt();
            if(ans == 1){
                System.out.println("Nombre actual: " + this.Nombre);
                this.Nombre = inputNombre();
            } else if (ans == 2) {
                System.out.println("Ataque actual: " + this.Modificador);
                this.Modificador=inputModificador();
            } else if (ans == 3) {
                System.out.println("Defensa actual: " + this.ModDFS);
                this.ModDFS=inputModDFS();
            } else if (ans == 4) {
                System.out.println("Tipo actual: " + (this.Tipo ? "Arma de dos manos" : "Arma de una mano"));
                this.Tipo=inputTipo();
            }

        } while (ans!=5);

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
            System.out.println("Ingrese el valor del modificador de ayaque:");
            modif = leerDouble();
            if (modif <= 0) {
                System.out.println("El valor debe ser mayor que 0.");
            }
        } while (modif <= 0);
        return modif;
    }

    protected double inputModDFS() {
        double modif;
        do {
            System.out.println("Ingrese el valor del modificador de defensa:");
            modif = leerDouble();
            if (modif < 0) {
                System.out.println("El valor debe ser mayor o igual a 0.");
            }
        } while (modif < 0);
        return modif;
    }

    protected boolean inputTipo() {
        Scanner scanner = new Scanner(System.in);
        int tipoInput;
        boolean tipo = false;
        do {
            System.out.println("Ingrese 1 para 'Arma de una mano' o 2 para 'Arma de dos manos':");
            tipoInput = scanner.nextInt();
            if (tipoInput == 1) {
                tipo = false;  // Arma de una mano
            } else if (tipoInput == 2) {
                tipo = true;   // Arma de dos manos
            } else {
                System.out.println("Valor incorrecto. Por favor, ingrese 1 o 2.");
            }
        } while (tipoInput != 1 && tipoInput != 2);

        return tipo;
    }

    public void setModDFS(double modDFS) {
        ModDFS = modDFS;
    }

    private double leerDouble() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }
    private int leerInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}