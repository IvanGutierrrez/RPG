
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class HabilidadEspecial implements Serializable, Cloneable{

    private String Nombre;

    private double ValorATC;

    private double ValorDFS;

    private double Coste;

    public HabilidadEspecial(){
        buildHabilidadEspecialFromInput();
    }

    public HabilidadEspecial(int i, int i1, int i2) {
        this.ValorATC=i;
        this.ValorDFS=i1;
        this.Coste=i2;
    }

    @Override
    public HabilidadEspecial clone() {
        try {
            return (HabilidadEspecial) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public double DarAtq( double Valor) {
        if(Valor>=this.Coste){
            return this.ValorATC;
        }
        return 0;
    }

    public double getCoste() { return this.Coste;}

    public double DarDef(double Valor) {
        if(Valor>=this.Coste){
            return this.ValorDFS;
        }
        return 0;
    }

    public void buildHabilidadEspecialFromInput() {
        this.Nombre = inputNombre();
        this.ValorATC = inputValorATC();
        this.ValorDFS = inputValorDFS();
        this.Coste = inputCoste();
    }

    protected void EditarHabilidadEspecial(){
        int ans;
        do {
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar valor ataque");
            System.out.println("3. Modificar valor defensa");
            System.out.println("4. Modificar coste");
            System.out.println("5. Salir");
            ans = leerInt();
            if(ans == 1){
                System.out.println("Nombre actual: " + this.Nombre);
                this.Nombre = inputNombre();
            } else if (ans == 2) {
                System.out.println("Ataque actual: " + this.ValorATC);
                this.ValorATC=inputValorATC();
            } else if (ans == 3) {
                System.out.println("Defensa actual: " + this.ValorDFS);
                this.ValorDFS=inputValorDFS();
            } else if (ans == 4) {
                System.out.println("Coste actual: " + this.Coste);
                this.Coste = inputCoste();
            }

        } while (ans!=5);

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
                System.out.println("Caracter no valido, introduzca un numero real");
            }
        }
        return num;
    }

    public String getNombre() {
        return Nombre;
    }

    private String inputNombre() {
        Scanner scanner = new Scanner(System.in);
        String nombre;
        do {
            System.out.println("Ingrese nombre de la habilidad especial:");
            nombre = scanner.nextLine().trim();
        } while (nombre.isEmpty());
        return nombre;
    }

    private double inputValorATC() {
        Scanner scanner = new Scanner(System.in);
        double valorATC;
        do {
            System.out.println("Ingrese el valor de la habilidad especial para el ataque (entre 1 y 3):");
            valorATC = leerDouble();
            if (valorATC < 1 || valorATC > 3) {
                System.out.println("El valor debe estar entre 1 y 3, inclusive.");
            }
        } while (valorATC < 1 || valorATC > 3);
        return valorATC;
    }

    private double inputValorDFS() {
        double valorDFS;
        do {
            System.out.println("Ingrese el valor de la habilidad especial para la defensa (entre 1 y 3):");
            valorDFS = leerDouble();
            if (valorDFS < 1 || valorDFS > 3) {
                System.out.println("El valor debe estar entre 1 y 3, inclusive.");
            }
        } while (valorDFS < 1 || valorDFS > 3);
        return valorDFS;
    }

    private double inputCoste() {
        double coste;
        do {
            System.out.println("Ingrese el costo de la habilidad especial:");
            coste = leerDouble();
        } while (coste < 0);
        return coste;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    // Métodos getter y setter para ValorATC
    public double getValorATC() {
        return ValorATC;
    }

    public void setValorATC(double valorATC) {
        ValorATC = valorATC;
    }

    // Métodos getter y setter para ValorDFS
    public double getValorDFS() {
        return ValorDFS;
    }

    public void setValorDFS(double valorDFS) {
        ValorDFS = valorDFS;
    }

    public void setCoste(double coste) {
        Coste = coste;
    }

}
