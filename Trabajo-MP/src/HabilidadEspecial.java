
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
        Scanner scanner = new Scanner(System.in);
        String nombre;
        do {
            System.out.println("Ingrese nombre de la habilidad especial:");
            nombre = scanner.nextLine();
        } while (nombre.isEmpty());

        double valorATC;
        do {
            System.out.println("Ingrese el valor de la habilidad especial para el ataque:");
            valorATC = scanner.nextDouble();
        } while (valorATC < 0);

        double valorDFS;
        do {
            System.out.println("Ingrese el valor de la habilidad especial para la defensa:");
            valorDFS = scanner.nextDouble();
        } while (valorDFS < 0);

        double coste;
        do {
            System.out.println("Ingrese el costo de la habilidad especial:");
            coste = scanner.nextDouble();
        } while (coste < 0);

        this.Nombre = nombre;
        this.ValorATC = valorATC;
        this.ValorDFS = valorDFS;
        this.Coste = coste;
    }
}