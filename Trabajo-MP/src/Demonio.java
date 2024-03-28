
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Demonio extends Esbirro implements Serializable, Cloneable{

    private boolean Pacto;

    private String DescrPact;

    private Esbirro Esbirros;

    @Override
    public Demonio clone() {
        Demonio clone = (Demonio) super.clone();
        clone.Esbirros = this.Esbirros.clone();
        return clone;
    }
}