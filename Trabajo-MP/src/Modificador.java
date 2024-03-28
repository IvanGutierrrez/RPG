
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Modificador implements Serializable, Cloneable {

    private String Nombre;

    private double Valor;



    public String getNombre() {
        return Nombre;
    }

    @Override
    public Modificador clone() {
        try {
            return (Modificador) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}