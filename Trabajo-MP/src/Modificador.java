
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Modificador implements Serializable, Cloneable {

    private String Nombre;

    private double Valor;

    public Modificador(){
        buildModificadorFromInput();
    }

    public String getNombre() {
        return Nombre;
    }

    public double getValor() {
        return Valor;
    }

    @Override
    public Modificador clone() {
        try {
            return (Modificador) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void buildModificadorFromInput() {
        Scanner scanner = new Scanner(System.in);
        String nombre;
        do {
            System.out.println("Ingrese nombre del modificador:");
            nombre = scanner.nextLine();
        } while (nombre.isEmpty());

        double valor;
        do {
            System.out.println("Ingrese el valor del modificador:");
            valor = scanner.nextDouble();
        } while (valor < 0);

        this.Nombre = nombre;
        this.Valor = valor;
    }
}