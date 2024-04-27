import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OperadorTest {

    @Test
    public void testNickUnico() throws NoSuchMethodException, IOException, InvocationTargetException, IllegalAccessException {
        Operador instancia = new Operador();
        Method metodoPrivado = instancia.getClass().getDeclaredMethod("nickUnico", String.class);
        metodoPrivado.setAccessible(true);

        GestionInicioSesion instancia2 = new GestionInicioSesion();
        Method metodoPrivado2 = instancia2.getClass().getDeclaredMethod("añadirOperador", Operador.class);
        metodoPrivado2.setAccessible(true);

        Method metodoPrivado3 = instancia2.getClass().getDeclaredMethod("asegurarArchivos");
        metodoPrivado3.setAccessible(true);

        File archivo = new File("Trabajo-MP/datos/operadores/operador");
        archivo.delete();

        metodoPrivado3.invoke(instancia2);//añade el archivo y la primera constraseña

        assertEquals(true,metodoPrivado.invoke(instancia,"Nick"));
        assertEquals(true,metodoPrivado.invoke(instancia,"nick2"));

        Operador op1 = new Operador("Name","Nick","password");
        Operador op2 = new Operador("name","nick2","password");

        metodoPrivado2.invoke(instancia2,op1);
        metodoPrivado2.invoke(instancia2,op2);

        assertEquals(false,metodoPrivado.invoke(instancia,"Nick"));
        assertEquals(false,metodoPrivado.invoke(instancia,"nick2"));
    }

    @Test
    public void testBloqueo() throws ReflectiveOperationException {
        Operador operador = new Operador();
        Jugador jugador = new Jugador();
        jugador.setBloqueado(false);
        Method metodoPrivado = operador.getClass().getDeclaredMethod("bloquarse", Jugador.class);
        metodoPrivado.setAccessible(true);
        metodoPrivado.invoke(operador, jugador);
        assertTrue(jugador.getBloqueado());
        metodoPrivado.invoke(operador, jugador);
        assertFalse(jugador.getBloqueado());
    }
    @Test
    public void removerarmadurasVacias() throws ReflectiveOperationException {
        Operador operador = new Operador();
        Personaje personaje = new Licantropo(9);
        Method metodoPrivado = operador.getClass().getDeclaredMethod("removerArmadura", Personaje.class);
        metodoPrivado.setAccessible(true);
        assertFalse((Boolean) metodoPrivado.invoke(operador, personaje));
    }

    @Test
    public void removerarmasVacias() throws ReflectiveOperationException {
        Operador operador = new Operador();
        Personaje personaje = new Licantropo(9);
        Method metodoPrivado = operador.getClass().getDeclaredMethod("removerArma", Personaje.class);
        metodoPrivado.setAccessible(true);
        assertFalse((Boolean) metodoPrivado.invoke(operador, personaje));
    }

    @Test
    public void editarArmadurasVacias() throws ReflectiveOperationException {
        Operador operador = new Operador();
        Personaje personaje = new Licantropo(9);
        Method metodoPrivado = operador.getClass().getDeclaredMethod("editarArmadura", Personaje.class);
        metodoPrivado.setAccessible(true);
        assertFalse((Boolean) metodoPrivado.invoke(operador, personaje));
    }

    @Test
    public void editarArmasVacias() throws ReflectiveOperationException {
        Operador operador = new Operador();
        Personaje personaje = new Licantropo(9);
        Method metodoPrivado = operador.getClass().getDeclaredMethod("editarArma", Personaje.class);
        metodoPrivado.setAccessible(true);
        assertFalse((Boolean) metodoPrivado.invoke(operador, personaje));
    }

    @Test
    public void testRemoverPersonaje_EliminarPersonajeExistente() throws IOException {

        Partida partida = new Partida();
        Operador operador = new Operador();

        String inputSimulado = "1\n" +
                "2\n" +
                "3\n" +
                "2\n";
        InputStream entradaSimulada = new ByteArrayInputStream(inputSimulado.getBytes());

        System.setIn(entradaSimulada);


        operador.Menu(partida);


        List<Personaje> listaPersonajes = partida.getListaPersonajes();



        operador.removerPersonaje(partida);
        assertTrue(listaPersonajes.isEmpty());


        System.setIn(System.in);
    }

}