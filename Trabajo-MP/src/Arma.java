
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Arma extends Equipo implements Serializable, Cloneable {

    private double ModDFS;

    private boolean Tipo;


    @Override
    public Arma clone() {
        return (Arma) super.clone();
    }

    public boolean getTipo() {
        return Tipo;
    }

    public double getModDFS() { return ModDFS;}

    public void setTipo(boolean tipo) {
        Tipo = tipo;
    }
}