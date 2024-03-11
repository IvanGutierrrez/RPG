
import java.util.*;

/**
 * 
 */
public abstract class Usuario {

    /**
     * Default constructor
     */
    public Usuario() {
    }

    /**
     * 
     */
    protected String Nombre;

    /**
     * 
     */
    protected String Nick;

    /**
     * 
     */
    protected String Contraseña;

    /**
     * @param Partida
     */
    public abstract void Menu(void Partida);

}