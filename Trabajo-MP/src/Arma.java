
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

    protected double inputModDFS() {
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

    protected boolean inputTipo() {
        Scanner scanner = new Scanner(System.in);
        String tipoStr;
        boolean tipo;
        do {
            System.out.println("Ingrese el valor de Tipo (true/false):");
            tipoStr = scanner.next();
            if (!tipoStr.equalsIgnoreCase("true") && !tipoStr.equalsIgnoreCase("false")) {
                System.out.println("Valor incorrecto. Por favor, ingrese 'true' o 'false'.");
            }
        } while (!tipoStr.equalsIgnoreCase("true") && !tipoStr.equalsIgnoreCase("false"));
        tipo = Boolean.parseBoolean(tipoStr);
        return tipo;
    }

    public void setModDFS(double modDFS) {
        ModDFS = modDFS;
    }

    private double leerDouble() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }
}