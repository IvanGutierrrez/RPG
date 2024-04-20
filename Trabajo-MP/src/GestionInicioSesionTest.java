import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class GestionInicioSesionTest {

    @Test
    public void testExistenOperadores() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        GestionInicioSesion instancia = new GestionInicioSesion();
        Method metodoPrivado = instancia.getClass().getDeclaredMethod("existenOperadores");
        metodoPrivado.setAccessible(true);

        assertEquals(false,metodoPrivado.invoke(instancia));

        Operador op = new Operador("Nombre","Nick","password");
        Method metodoPrivado2 = instancia.getClass().getDeclaredMethod("añadirOperador", Operador.class);
        metodoPrivado2.setAccessible(true);
        metodoPrivado2.invoke(instancia,op);

        assertEquals(true,metodoPrivado.invoke(instancia));

    }

    public boolean existenDatos(){
        File datos = new File("Trabajo-MP/datos");
        if (datos.exists() && datos.isDirectory()) {
            File[] archivos = datos.listFiles();
            if (archivos != null && archivos.length > 0) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testAsegurarArchivos() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        File datos = new File("Trabajo-MP/datos");
        datos.delete();
        //para que funcione debes eliminar manualmente la carpeta datos
        assertEquals(false,this.existenDatos());

        GestionInicioSesion instancia = new GestionInicioSesion();
        Method metodoPrivado = instancia.getClass().getDeclaredMethod("asegurarArchivos");
        metodoPrivado.setAccessible(true);
        metodoPrivado.invoke(instancia);

        assertEquals(true,this.existenDatos());
    }

    @Test
    public void testPartidaExits() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        GestionInicioSesion instancia = new GestionInicioSesion();
        Method metodoPrivado = instancia.getClass().getDeclaredMethod("partidaExits");
        metodoPrivado.setAccessible(true);

        assertEquals(false,metodoPrivado.invoke(instancia));

        Partida p = new Partida();
        p.serializar();

        assertEquals(true,metodoPrivado.invoke(instancia));

    }

    @Test
    public void testAñadirOperadorYExisteOperador() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Operador op = new Operador("name","nike2","password");

        GestionInicioSesion instancia = new GestionInicioSesion();
        Method metodoPrivado2 = instancia.getClass().getDeclaredMethod("existeOperador", String.class);
        metodoPrivado2.setAccessible(true);

        assertEquals(false,metodoPrivado2.invoke(instancia,"nike2"));

        Method metodoPrivado = instancia.getClass().getDeclaredMethod("añadirOperador", Operador.class);
        metodoPrivado.setAccessible(true);
        metodoPrivado.invoke(instancia,op);

        assertEquals(true,metodoPrivado2.invoke(instancia,"nike2"));



    }

    @Test
    public void testCoincidePass() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Operador op = new Operador("name","nike","password");

        GestionInicioSesion instancia = new GestionInicioSesion();
        Method metodoPrivado2 = instancia.getClass().getDeclaredMethod("coincidePass", String.class, String.class);
        metodoPrivado2.setAccessible(true);

        assertEquals(null,metodoPrivado2.invoke(instancia,"nike","password"));

        Method metodoPrivado = instancia.getClass().getDeclaredMethod("añadirOperador", Operador.class);
        metodoPrivado.setAccessible(true);
        metodoPrivado.invoke(instancia,op);

        assertEquals("name",metodoPrivado2.invoke(instancia,"nike","password"));
    }

    @Test
    public void testCoincide() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        GestionInicioSesion instancia = new GestionInicioSesion();
        Method metodoPrivado = instancia.getClass().getDeclaredMethod("coincide", String.class);
        metodoPrivado.setAccessible(true);

        assertEquals(false,metodoPrivado.invoke(instancia,"n"));
        assertEquals(true,metodoPrivado.invoke(instancia,"1password"));
    }

}