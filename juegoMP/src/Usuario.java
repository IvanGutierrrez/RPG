
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public abstract class Usuario implements Serializable {

    protected String Nombre;

    protected String Nick;

    protected String Contraseña;

    public abstract void Menu(Partida p);

}