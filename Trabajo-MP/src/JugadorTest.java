import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JugadorTest {

    private boolean NRegistroUnico(String reg,Partida p){
        Map<String, Usuario> mapa = p.getMapUsuarios();
        if (mapa == null) {
            return true;
        } else {
            for (Map.Entry<String, Usuario> entry : mapa.entrySet()) {
                Usuario u = entry.getValue();
                if (u instanceof Jugador jugador) {
                    if (jugador.getNRegistro().equals(reg))
                        return false;
                }
            }
        }
        return true;
    }

    @Test
    public void testSetNRegistro() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Partida p = new Partida();

        Jugador j1 = new Jugador();
        Method metodoPrivado = j1.getClass().getDeclaredMethod("setNRegistro", Partida.class);
        metodoPrivado.setAccessible(true);

        j1.setNick("1");
        metodoPrivado.invoke(j1,p);
        p.getMapUsuarios().put("1",j1);

        assertEquals(true,this.NRegistroUnico("S23RR",p));

        assertEquals(false,this.NRegistroUnico(j1.getNRegistro(),p));
    }

    @Test
    public void testNickUnico() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
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