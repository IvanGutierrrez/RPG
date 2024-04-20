
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

    protected void EditarModificadorActual() {
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
        double valor;
        do {
            System.out.println("Ingrese el valor del modificador (entre 1 y 5):");
            valor = leerDouble();
            if (valor < 1 || valor > 5) {
                System.out.println("El valor debe estar entre 1 y 5, inclusive.");
            }
        } while (valor < 1 || valor > 5);
        return valor;
    }


    private int leerInt(){
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

    private double leerDouble(){
        Scanner scanner = new Scanner(System.in);
        boolean ok = false;
        double num = 0;
        while (!ok) {
            try {
                String n = scanner.nextLine();
                num = Integer.parseInt(n);
                ok = true;
            } catch (NumberFormatException e){
                System.out.println("Caracter no valido, introduzca un nnumero real");
            }
        }
        return num;
    }
}