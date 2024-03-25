
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public abstract class Personaje implements Serializable, Cloneable {

    protected String Nombre;

    protected HabilidadEspecial HabilidadEspecial;

    protected Arma[] Armas;

    protected Arma[] ArmasActivas;

    protected Armadura[] Armaduras;

    protected Armadura ArmaduraActiva;

    protected Esbirro[] Esbirros;

    protected double Oro;

    protected double Salud;

    protected double Poder;

    protected Modificador[] Debilidades;

    protected Modificador[] Fortalezas;

    protected int Version;

    public abstract String getNombre();

    public abstract double calcularPotencialAtaque();

    public abstract double calcularPotencialDefensa();

    public abstract boolean checkApuesta();

    public abstract void gestionEquipamiento();

    public abstract void gestionarApuesta(int oro);

    public abstract void editarAtributos();

    public void Clone() {
        // TODO implement here
    }

    @Override
    public Personaje clone() {
        try {
            Personaje clone = (Personaje) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}