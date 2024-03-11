
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public abstract class Personaje implements Serializable {

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

    public abstract double calcularPotencialAtaque();

    public abstract double calcularPotencialDefensa();

    public abstract boolean checkApuesta();

    public abstract void gestionEquipamiento();

    public abstract void gestionarApuesta(int oro);

    public abstract void editarAtributos();

    public void Clone() {
        // TODO implement here
    }

}