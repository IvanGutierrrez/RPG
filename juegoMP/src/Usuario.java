
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public abstract class Usuario implements Serializable {

    protected String Nombre;

    protected String Contraseña;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public abstract void Menu(Partida p);

}
