
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Esbirro implements Serializable, Cloneable{

    private String Nombre;

    private double Salud;

    @Override
    public Esbirro clone() {
        try {
            return (Esbirro) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}