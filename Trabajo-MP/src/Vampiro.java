
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Vampiro extends Personaje implements Serializable, Cloneable {

    public Vampiro() {
    }

    private int Edad;

    private double Sangre;

    public void SubirSangre(){
        this.Sangre=this.Sangre+4;
        if(this.Sangre>10){
            this.Sangre=10;
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
        if(this.Sangre>=5){
            suma=suma+2;
        }
        suma=suma+super.HabilidadEspecial.DarAtq(this.Sangre);

        return suma;
    }

    @Override
    public double calcularPotencialDefensa() {
        double suma=0;
        suma=suma+super.Poder;
        suma=suma+super.DarValorDefEquipo();
        if(this.Sangre>=5){
            suma=suma+2;
        }
        double aux=super.HabilidadEspecial.DarDef(this.Sangre);
        if(aux!=0){
            this.Sangre=this.Sangre-super.HabilidadEspecial.getCoste();
            suma=suma+aux;
        }
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
    public Vampiro clone() {
        return (Vampiro) super.clone();
    }




}