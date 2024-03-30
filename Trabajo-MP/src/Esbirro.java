
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Esbirro implements Serializable, Cloneable{

    public String Nombre;

    public double Salud;


    public String getNombre(){return this.Nombre;}

    public double getSalud(){return this.Salud;}

    @Override
    public Esbirro clone() {
        try {
            return (Esbirro) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}