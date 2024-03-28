
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class HabilidadEspecial implements Serializable, Cloneable{

    private String Nombre;

    private double ValorATC;

    private double ValorDFS;

    private double Coste;

    @Override
    public HabilidadEspecial clone() {
        try {
            return (HabilidadEspecial) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}