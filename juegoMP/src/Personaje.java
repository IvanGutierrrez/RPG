
import java.util.*;

/**
 * 
 */
public abstract class Personaje {

    /**
     * Default constructor
     */
    public Personaje() {
    }

    /**
     * 
     */
    protected String Nombre;

    /**
     * 
     */
    protected HabilidadesEspeciale HabilidadEspecial;

    /**
     * 
     */
    protected Arma[] Armas;

    /**
     * 
     */
    protected Array[] ArmasActivas;

    /**
     * 
     */
    protected Armadura[] Armaduras;

    /**
     * 
     */
    protected Armadura ArmaduraActiva;

    /**
     * 
     */
    protected Esbirro[] Esbirros;

    /**
     * 
     */
    protected double Oro;

    /**
     * 
     */
    protected double Salud;

    /**
     * 
     */
    protected double Poder;

    /**
     * 
     */
    protected Modificador[] Debilidades;

    /**
     * 
     */
    protected Modificador[] Fortalezas;

    /**
     * 
     */
    protected int Version;

    /**
     * @return
     */
    public abstract double calcularPotencialAtaque();

    /**
     * @return
     */
    public abstract double calcularPotencialDefensa();

    /**
     * @return
     */
    public abstract Boolean checkApuesta();

    /**
     * 
     */
    public abstract void gestionEquipamiento();

    /**
     * @param int
     */
    public abstract void gestionarApuesta(void int);

    /**
     * 
     */
    public abstract void editarAtributos();

    /**
     * 
     */
    public void Clone() {
        // TODO implement here
    }

}