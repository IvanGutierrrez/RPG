import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class PartidaTest {

    @Test
    public void testCoincidePass() {
        Partida p = new Partida();
        Jugador j = new Jugador();
        j.setNick("J1");
        j.setNombre("NJ1");
        j.setPass("password");
        p.getMapUsuarios().put("J1",j);

        assertEquals(null,p.coincidePass("J2","password"));
        assertEquals(null,p.coincidePass("J1","nopassword"));
        assertEquals(j,p.coincidePass("J1","password"));
    }

    @Test
    public void testNickUnico() {
        Partida p = new Partida();
        Jugador j = new Jugador();
        j.setNick("J1");
        j.setNombre("NJ1");
        j.setPass("password");
        p.getMapUsuarios().put("J1",j);

        assertEquals(true,p.nickUnico("J2"));
        assertEquals(false,p.nickUnico("J1"));
    }

    @Test
    public void testSerializar() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        File datos = new File("Trabajo-MP/datos");
        datos.delete();

        GestionInicioSesion instancia = new GestionInicioSesion();
        Method metodoPrivado = instancia.getClass().getDeclaredMethod("partidaExits");
        metodoPrivado.setAccessible(true);

        //para que funcione hace falta borrar manualmente la carpeta de datos
        assertEquals(false,metodoPrivado.invoke(instancia));

        Partida p = new Partida();
        p.serializar();

        assertEquals(true,metodoPrivado.invoke(instancia));
    }

    @Test
    public void testDeserializar() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Partida p = new Partida();
        Operador op = new Operador("nombre","nick","password");
        p.getMapUsuarios().put("nick",op);
        p.serializar();

        Partida p2 = p.deserializar();

        assertEquals(true,p.getMapUsuarios().get("nick").getNick().equals(p2.getMapUsuarios().get("nick").getNick()));
    }

    @Test
    public void testDarDeBajaUsuario() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Partida p = new Partida();
        Jugador j = new Jugador();
        j.setNick("J1");
        j.setNombre("NJ1");
        j.setPass("password");
        p.getMapUsuarios().put("J1",j);
        p.darDeBajaUsuario(j);

        assertEquals(false,p.getMapUsuarios().containsValue(j));

        Operador op = new Operador("nombre","nick","password");
        GestionInicioSesion instancia = new GestionInicioSesion();
        Method metodoPrivado = instancia.getClass().getDeclaredMethod("añadirOperador", Operador.class);
        metodoPrivado.setAccessible(true);
        metodoPrivado.invoke(instancia, op);

        Method metodoPrivado2 = instancia.getClass().getDeclaredMethod("existeOperador", String.class);
        metodoPrivado2.setAccessible(true);

        p.darDeBajaUsuario(op);
        assertEquals(false,metodoPrivado2.invoke(instancia, op.getNick()));
    }

    @Test
    public void testCheckVersion() {
    }

    @Test
    public void testExistenArmasArmaduras() {
    }
}