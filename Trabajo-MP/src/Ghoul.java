
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Ghoul extends Esbirro implements Serializable, Cloneable{

    private double Dependencia;

    @Override
    public Ghoul clone() {
        return (Ghoul) super.clone();
    }

    public Ghoul(){
        ghoulFromInput();
    }
    private double inputDependencia() {
        Scanner scanner = new Scanner(System.in);
        double dependencia;
        do {
            System.out.println("Ingrese la cantidad de dependencia:");
            dependencia = scanner.nextDouble();
            if (dependencia <= 0) {
                System.out.println("La cantidad de dependencia debe ser mayor que 0.");
            }
        } while (dependencia <= 0);
        return dependencia;
    }

    @Override
    protected void ModificarEsbirro(){
        int ans;
        do {
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar salud");
            System.out.println("3. Modificar dependencia");
            System.out.println("4. Salir");
            ans = leerInt();
            if(ans == 1){
                System.out.println("Nombre actual: " + this.Nombre);
                this.Nombre = inputNombre();
            } else if (ans == 2) {
                System.out.println("Salud actual: " + this.Salud);
                this.Salud=inputSalud();
            } else if (ans == 3) {
                System.out.println("Dependencia actual: " + this.Dependencia);
                this.Dependencia=inputDependencia();
            }

        } while (ans!=4);
    }

    private void ghoulFromInput(){
        this.Nombre = inputNombre();
        this.Salud = inputSalud();
        this.Dependencia = inputDependencia();
    }
}