import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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


}