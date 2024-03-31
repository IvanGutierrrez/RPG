
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Cazador extends Personaje implements Serializable, Cloneable {

    public Cazador(){
        buildPersonajeFromInput();
    }

    private double Voluntad;

    private void buildPersonajeFromInput() {
        this.Armas = new ArrayList<>();
        this.ArmasActivas = new ArrayList<>();
        this.Armaduras = new ArrayList<>();
        this.Esbirros = new ArrayList<>();
        this.Debilidades = new ArrayList<>();
        this.Fortalezas = new ArrayList<>();
        this.Version = 1;
        this.Nombre = inputNombre();
        this.Voluntad = inputVoluntad();
        this.Oro = inputOro();
        this.Salud = inputSalud();
        this.Poder = inputPoder();
    }


    protected double inputVoluntad() {
        double voluntad;
        do {
            System.out.println("Ingrese cantidad de voluntad:");
            voluntad = leerDouble();
            if (voluntad <= 0) {
                System.out.println("El valor de voluntad debe ser mayor que 0.");
            }
        } while (voluntad <= 0);
        return voluntad;
    }

    public void BajarVoluntad(){
        this.Voluntad=this.Voluntad-1;
        if(this.Voluntad<0){
            this.Voluntad=0;
        }
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
        suma=suma+this.Voluntad;
        suma=suma+super.HabilidadEspecial.DarAtq(this.Voluntad);

        return suma;
    }
    @Override
    public double calcularPotencialDefensa() {
        double suma=0;
        suma=suma+super.Poder;
        suma=suma+super.DarValorDefEquipo();
        suma=suma+this.Voluntad;
        suma=suma+super.HabilidadEspecial.DarDef(this.Voluntad);

        return suma;
    }

    private double leerDouble() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
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
    public Cazador clone() {
        return (Cazador) super.clone();
    }

}