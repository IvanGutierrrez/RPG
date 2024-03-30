
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Equipo implements Serializable, Cloneable{

    private String Nombre;

    private double Modificador;

    @Override
    public Equipo clone() {
        try {
            return (Equipo) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public String getNombre() {
        return Nombre;
    }

    public double getModificador() {
        return Modificador;
    }


}