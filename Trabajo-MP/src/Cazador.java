
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
    private transient Scanner scanner = ScannerSingleton.getInstance();

    public void setVoluntad(int voluntad){
        this.Voluntad=voluntad;
    }

    public Cazador(int voluntad) {
        this.Armas = new ArrayList<>();
        this.ArmasActivas = new ArrayList<>(2);
        this.Armaduras = new ArrayList<>();
        this.Esbirros = new ArrayList<>();
        this.Debilidades = new ArrayList<>();
        this.Fortalezas = new ArrayList<>();
        this.Version = 1;
        this.Voluntad=voluntad;
    }

    private void buildPersonajeFromInput() {
        this.Armas = new ArrayList<>();
        this.ArmasActivas = new ArrayList<>();
        this.Armaduras = new ArrayList<>();
        this.Esbirros = new ArrayList<>();
        this.Debilidades = new ArrayList<>();
        this.Fortalezas = new ArrayList<>();
        this.Version = 1;
        this.Nombre = inputNombre();
        this.Voluntad = 3;
        this.Oro = inputOro();
        this.Salud = inputSalud();
        this.Poder = inputPoder();
    }

    public void BajarVoluntad(){
        this.Voluntad=this.Voluntad-1;
        if(this.Voluntad<0){
            this.Voluntad=0;
        }
    }
    public double getVoluntad(){return this.Voluntad;}

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

    @Override
    public void VolverAloNormal(double vidaJugador2) {
       super.Salud=vidaJugador2;
       this.Voluntad=3;
    }


    @Override
    public Cazador clone() {
        return (Cazador) super.clone();
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.scanner = ScannerSingleton.getInstance();
    }

}