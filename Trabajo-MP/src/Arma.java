
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

        double modDFS;
        do {
            System.out.println("Ingrese el valor de ModDFS:");
            modDFS = scanner.nextDouble();
        } while (modDFS < 0);

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

        this.Nombre = name;
        this.Modificador = modificador;
        this.ModDFS = modDFS;
        this.Tipo =tipo;
    }

    public void setModDFS(double modDFS) {
        ModDFS = modDFS;
    }
}