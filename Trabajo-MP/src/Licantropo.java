
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Licantropo extends Personaje implements Serializable, Cloneable {

    private double Rabia;
    private transient Scanner scanner = ScannerSingleton.getInstance();

    public Licantropo(int rabia) {
        this.Armas = new ArrayList<>();
        this.ArmasActivas = new ArrayList<>(2);
        this.Armaduras = new ArrayList<>();
        this.Esbirros = new ArrayList<>();
        this.Debilidades = new ArrayList<>();
        this.Fortalezas = new ArrayList<>();
        this.Version = 1;
        this.Rabia=rabia;
    }

    public void SubirRabia(){
        this.Rabia=this.Rabia+1;
        if(this.Rabia>3){
            this.Rabia=3;
        }
    }
    public double getRabia(){return this.Rabia;}

    public void setRabia(double rabia) {
        Rabia = rabia;
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
        this.Rabia = 0;
        this.Oro = inputOro();
        this.Salud = inputSalud();
        this.Poder = inputPoder();
    }

    @Override
    public Licantropo clone() {
        return (Licantropo) super.clone();
    }


    @Override
    public void VolverAloNormal(double vidaJugador2) {
        super.Salud=vidaJugador2;
        this.Rabia=0;
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.scanner = ScannerSingleton.getInstance();
    }
}