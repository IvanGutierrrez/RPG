
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

    protected void EditarHabilidadEspecial() {
        int ans;
        do {
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar valor");
            System.out.println("3. Salir");
            ans = leerInt();
            if (ans == 1) {
                System.out.println("Nombre actual: " + this.Nombre);
                this.Nombre = inputNombre();
            } else if (ans == 2) {
                System.out.println("Valor del modificador actual: " + this.Valor);
                this.Valor = inputValor();
            }
        } while (ans != 3) ;
    }
    public void buildModificadorFromInput() {
        this.Nombre = inputNombre();
        this.Valor = inputValor();
    }

    private String inputNombre() {
        Scanner scanner = new Scanner(System.in);
        String nombre;
        do {
            System.out.println("Ingrese nombre del modificador:");
            nombre = scanner.nextLine().trim();
        } while (nombre.isEmpty());
        return nombre;
    }

    private double inputValor() {
        Scanner scanner = new Scanner(System.in);
        double valor;
        do {
            System.out.println("Ingrese el valor del modificador:");
            valor = scanner.nextDouble();
        } while (valor < 0);
        return valor;
    }

    private int leerInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}