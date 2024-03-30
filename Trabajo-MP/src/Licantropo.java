
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

}