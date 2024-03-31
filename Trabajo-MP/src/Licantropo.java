
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Licantropo extends Personaje implements Serializable, Cloneable {

    private double Rabia;

    public void SubirRabia(){
        this.Rabia=this.Rabia+1;
        if(this.Rabia>3){
            this.Rabia=3;
        }
    }

    public Licantropo(){
        buildPersonajeFromInput();
    }

    @Override
    public String getNombre() {
        return this.Nombre;
    }

    @Override
    public double calcularPotencialAtaque() {
        double suma=0;
        suma=suma+super.Poder;
        suma=suma+super.DarValorAtqEquipo();
        suma=suma+this.Rabia;
        suma=suma+super.HabilidadEspecial.DarAtq(this.Rabia);

        return suma;
    }

    @Override
    public double calcularPotencialDefensa() {
        double suma=0;
        suma=suma+super.Poder;
        suma=suma+super.DarValorDefEquipo();
        suma=suma+this.Rabia;
        suma=suma+super.HabilidadEspecial.DarDef(this.Rabia);

        return suma;
    }

    private void buildPersonajeFromInput() {
        this.Armas = new ArrayList<>();
        this.Esbirros = new ArrayList<>();
        this.ArmasActivas = new ArrayList<>();
        this.Armaduras = new ArrayList<>();
        this.Debilidades = new ArrayList<>();
        this.Fortalezas = new ArrayList<>();
        this.Version = 1;
        this.Nombre = inputNombre();
        this.Rabia = inputRabia();
        this.Oro = inputOro();
        this.Salud = inputSalud();
        this.Poder = inputPoder();
    }

    protected double inputRabia() {
        double rabia;
        do {
            System.out.println("Ingrese cantidad de rabia:");
            rabia = leerDouble();
            if (rabia < 0) {
                System.out.println("El valor de la rabia debe ser mayor o igual a 0.");
            }
        } while (rabia < 0);
        return rabia;
    }

    @Override
    public void gestionEquipamiento() {
        // TODO implement here
    }

    @Override
    public void gestionarApuesta(int oro) {
        // TODO implement here
    }

    @Override
    public void editarAtributos() {
        // TODO implement here
    }

    @Override
    public Licantropo clone() {
        return (Licantropo) super.clone();
    }

    private double leerDouble() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

}