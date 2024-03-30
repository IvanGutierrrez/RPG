
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Cazador extends Personaje implements Serializable, Cloneable {

    private double Voluntad;

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