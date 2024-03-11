
import java.util.*;

/**
 * 
 */
public class Vampiro extends Personaje {

    /**
     * Default constructor
     */
    public Vampiro() {
    }

    /**
     * 
     */
    private int Edad;

    /**
     * 
     */
    private double Sangre;

    /**
     * @return
     */
    public double calcularPotencialAtaque() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @return
     */
    public double calcularPotencialDefensa() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @return
     */
    public boolean checkApuesta() {
        // TODO implement here
        return false;
    }

    /**
     * 
     */
    public void gestionEquipamiento() {
        // TODO implement here
    }

    /**
     * @param int
     */
    public void gestionarApuesta(void int) {
        // TODO implement here
    }

    /**
     * 
     */
    public void editarAtributos() {
        // TODO implement here
    }

    /**
     * 
     */
    public void Clone() {
        // TODO implement here
    }

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

}