
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Armadura extends Equipo implements Serializable, Cloneable{

    private double ModATQ;


    @Override
    public Armadura clone() {
        return (Armadura) super.clone();
    }

    public double getModATQ() { return ModATQ;}
}