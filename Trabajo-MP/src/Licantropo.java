
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Licantropo extends Personaje implements Serializable, Cloneable {

    private double Rabia;

    @Override
    public double calcularPotencialAtaque() {
        // TODO implement here
        return 0.0d;
    }

    @Override
    public double calcularPotencialDefensa() {
        // TODO implement here
        return 0.0;
    }

    @Override
    public boolean checkApuesta() {
        // TODO implement here
        return false;
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

    public void Clone() {
        // TODO implement here
    }
}