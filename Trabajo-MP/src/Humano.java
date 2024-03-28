
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Humano extends Esbirro implements Serializable, Cloneable {

    private String Lealtad;


    @Override
    public Humano clone() {
        return (Humano) super.clone();
    }
}