
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Equipo implements Serializable, Cloneable{

    protected String Nombre;

    protected double Modificador;

    public Equipo(){}


    public Equipo(String nombre, double modificador) {
        this.Nombre = nombre;
        this.Modificador = modificador;
    }

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