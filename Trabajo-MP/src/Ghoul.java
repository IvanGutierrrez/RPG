
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Ghoul extends Esbirro implements Serializable, Cloneable{

    private double Dependencia;

    @Override
    public Ghoul clone() {
        return (Ghoul) super.clone();
    }
}