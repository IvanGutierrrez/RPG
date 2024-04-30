import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CombateTest {
    @Test
    public void testCombateResultadoCorrecto() {
        //creamos los Jugadores
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();

        jugador1.setNombre("J1");
        jugador2.setNombre("J2");
        //creamos los personajes, en este caso un vampiro y un licantropo
        Vampiro personaje1 = new Vampiro(0);
        Licantropo personaje2 = new Licantropo(0);

        //Vampiro
        personaje1.setSalud(4);
        personaje1.setPoder(2);
        personaje1.setOro(1000);
        //Licantropo
        personaje2.setSalud(4);
        personaje2.setPoder(2);
        personaje2.setOro(1000);
        //habilidad especial
        HabilidadEspecial hab1 = new HabilidadEspecial(1, 1, 3);
        personaje1.setHabilidadEspecial(hab1);
        personaje2.setHabilidadEspecial(hab1);
        //arma
        Arma arma = new Arma(0, true);

        arma.setModificador(1);
        personaje1.setArmasActivas(arma);
        personaje2.setArmasActivas(arma);
        //armadura
        Armadura armadura = new Armadura(0);
        armadura.setModificador(1);
        armadura.setModATQ(0);
        personaje1.setArmaduraActiva(armadura);
        personaje2.setArmaduraActiva(armadura);

        //Creamos el combate
        Combate combate = new Combate(jugador1, jugador2, 100, personaje1, personaje2, LocalDateTime.now());
        combate.setValido(true);
        combate.combatir();
        if (combate.getPersonajeRetador().getSalud() == 0 || combate.getPersonajeRetado().getSalud() == 0) {
            assertTrue(combate.getGanador() == jugador1 || combate.getGanador() == jugador2);

        } else if (combate.getPersonajeRetador().getSalud() == 0 && combate.getPersonajeRetado().getSalud() == 0) {
            assertNull(combate.getGanador());
        }
    }

    @Test
    public void testCombateResultadoIncorrectoPersonajeNull() {
        //creamos los Jugadores
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();

        jugador1.setNombre("J1");
        jugador2.setNombre("J2");
        //creamos los personajes, en este caso un vampiro y el otro personaje lo ponemos a null
        Vampiro personaje1 = new Vampiro(0);
        Licantropo personaje2 = null;

        //Vampiro
        personaje1.setSalud(4);
        personaje1.setPoder(2);
        personaje1.setOro(1000);

        //habilidad especial
        HabilidadEspecial hab1 = new HabilidadEspecial(1, 1, 3);
        personaje1.setHabilidadEspecial(hab1);

        //arma
        Arma arma = new Arma(0, true);

        arma.setModificador(1);
        personaje1.setArmasActivas(arma);
        //armadura
        Armadura armadura = new Armadura(0);
        armadura.setModificador(1);
        armadura.setModATQ(0);
        personaje1.setArmaduraActiva(armadura);


        //Creamos el combate
        Combate combate = new Combate(jugador1, jugador2, 100, personaje1, personaje2, LocalDateTime.now());
        combate.setValido(true);
        // Comprobar que se lanza una excepción debido al personaje null
        Exception exception = assertThrows(NullPointerException.class, () -> {
            combate.combatir();
        });

        assertNotNull(exception);
    }

    @Test
    public void testCombateResultadoIncorrectoJugadorNull() {
        //creamos los Jugadores
        Jugador jugador1 = null;
        Jugador jugador2 = new Jugador();


        jugador2.setNombre("J2");
        //creamos los personajes, en este caso un vampiro y un licantropo
        Vampiro personaje1 = new Vampiro(0);
        Licantropo personaje2 = new Licantropo(0);

        //Vampiro
        personaje1.setSalud(4);
        personaje1.setPoder(2);
        personaje1.setOro(1000);
        //Licantropo
        personaje2.setSalud(4);
        personaje2.setPoder(2);
        personaje2.setOro(1000);
        //habilidad especial
        HabilidadEspecial hab1 = new HabilidadEspecial(1, 1, 3);
        personaje1.setHabilidadEspecial(hab1);
        personaje2.setHabilidadEspecial(hab1);
        //arma
        Arma arma = new Arma(0, true);

        arma.setModificador(1);
        personaje1.setArmasActivas(arma);
        personaje2.setArmasActivas(arma);
        //armadura
        Armadura armadura = new Armadura(0);
        armadura.setModificador(1);
        armadura.setModATQ(0);
        personaje1.setArmaduraActiva(armadura);
        personaje2.setArmaduraActiva(armadura);

        //Creamos el combate
        Combate combate = new Combate(jugador1, jugador2, 100, personaje1, personaje2, LocalDateTime.now());
        combate.setValido(true);

        // Comprobar que se lanza una excepción debido al Jugador null
        assertThrows(NullPointerException.class, () -> {
            combate.combatir();
        });
    }
    @Test
    public void testResolverCombateGanador(){
        // Crear los jugadores
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();

        jugador1.setNombre("J1");
        jugador2.setNombre("J2");

        // Crear los personajes, un vampiro y un cazador
        Vampiro personaje1 = new Vampiro(0);
        Cazador personaje2 = new Cazador(3);

        // Configurar Vampiro
        personaje1.setSalud(4);
        double vidaInicial1 = 4;
        personaje1.setPoder(2);
        personaje1.setOro(1000);

        // Configurar cazador
        personaje2.setSalud(4);
        double vidaInicial2 = 4;
        personaje2.setPoder(2);
        personaje2.setOro(1000);

        // Habilidad especial
        HabilidadEspecial hab1 = new HabilidadEspecial(1, 1, 3);
        personaje1.setHabilidadEspecial(hab1);
        personaje2.setHabilidadEspecial(hab1);

        // Arma
        Arma arma = new Arma(0, true);
        arma.setModificador(1);
        personaje1.setArmasActivas(arma);
        personaje2.setArmasActivas(arma);

        // Armadura
        Armadura armadura = new Armadura(0);
        armadura.setModificador(1);
        armadura.setModATQ(0);
        personaje1.setArmaduraActiva(armadura);
        personaje2.setArmaduraActiva(armadura);

        // Crear el combate
        int apuesta = 100;
        Combate combate = new Combate(jugador1, jugador2, apuesta, personaje1, personaje2, LocalDateTime.now());
        combate.setValido(true);
        combate.setGanador(jugador1);
        combate.ResolverCombate(vidaInicial1, vidaInicial2);

        // Verificar el oro de los personajes después del combate
        assertEquals(1100, personaje1.getOro(), "El oro de personaje 1 debe incrementarse por la apuesta");
        assertEquals(900, personaje2.getOro(), "El oro de personaje 2 debe disminuir por la apuesta");

        // Verificar la vida y puntos especiales de los personajes después del combate
        assertEquals(vidaInicial1, personaje1.getSalud(), "La salud de personaje 1 debe ser igual a la inicial");
        assertEquals(0, personaje1.getSangre(), "Los puntos especiales de personaje 1 deben ser 0");

        assertEquals(vidaInicial2, personaje2.getSalud(), "La salud de personaje 2 debe ser igual a la inicial");
        assertEquals(3, personaje2.getVoluntad(), "Los puntos especiales de personaje 2 deben ser 3");

        // Verificar que se añaden los combates al historial de cada jugador
        assertNotNull(jugador1.getHistorialCombates(), "El historial de combates de jugador 1 no debe ser null");
        assertTrue(jugador1.getHistorialCombates().contains(combate), "El historial de combates de jugador 1 debe contener el combate resuelto");

        assertNotNull(jugador2.getHistorialCombates(), "El historial de combates de jugador 2 no debe ser null");
        assertTrue(jugador2.getHistorialCombates().contains(combate), "El historial de combates de jugador 2 debe contener el combate resuelto");
    }
    @Test
    public void TestResolverCombateEmpate(){
        // Crear los jugadores
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();

        jugador1.setNombre("J1");
        jugador2.setNombre("J2");

        // Crear los personajes, un vampiro y un cazador
        Vampiro personaje1 = new Vampiro(0);
        Cazador personaje2 = new Cazador(3);

        // Configurar Vampiro
        personaje1.setSalud(4);
        double vidaInicial1 = 4;
        personaje1.setPoder(2);
        personaje1.setOro(1000);

        // Configurar cazador
        personaje2.setSalud(4);
        double vidaInicial2 = 4;
        personaje2.setPoder(2);
        personaje2.setOro(1000);

        // Habilidad especial
        HabilidadEspecial hab1 = new HabilidadEspecial(1, 1, 3);
        personaje1.setHabilidadEspecial(hab1);
        personaje2.setHabilidadEspecial(hab1);

        // Arma
        Arma arma = new Arma(0, true);
        arma.setModificador(1);
        personaje1.setArmasActivas(arma);
        personaje2.setArmasActivas(arma);

        // Armadura
        Armadura armadura = new Armadura(0);
        armadura.setModificador(1);
        armadura.setModATQ(0);
        personaje1.setArmaduraActiva(armadura);
        personaje2.setArmaduraActiva(armadura);

        // Crear el combate
        int apuesta = 100;
        Combate combate = new Combate(jugador1, jugador2, apuesta, personaje1, personaje2, LocalDateTime.now());
        combate.setValido(true);
        combate.setGanador(null);//Esto es que hubo un empate.
        combate.ResolverCombate(vidaInicial1, vidaInicial2);

        // Verificar el oro de los personajes después del combate
        assertEquals(1000, personaje1.getOro(), "El oro de personaje 1 debe ser el mismo que al iniciar el combate");
        assertEquals(1000, personaje2.getOro(), "El oro de personaje 2 debe ser el mismo que al iniciar el combate");


    }
    @Test
    public void TestCancelarCombate(){
        // Crear los jugadores
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();

        jugador1.setNombre("J1");
        jugador2.setNombre("J2");

        // Crear los personajes, un vampiro y un cazador
        Vampiro personaje1 = new Vampiro(0);
        Cazador personaje2 = new Cazador(3);

        // Configurar Vampiro
        personaje1.setSalud(4);
        personaje1.setPoder(2);
        personaje1.setOro(1000);

        // Configurar cazador
        personaje2.setSalud(4);
        personaje2.setPoder(2);
        personaje2.setOro(1000);

        // Habilidad especial
        HabilidadEspecial hab1 = new HabilidadEspecial(1, 1, 3);
        personaje1.setHabilidadEspecial(hab1);
        personaje2.setHabilidadEspecial(hab1);

        // Arma
        Arma arma = new Arma(0, true);
        arma.setModificador(1);
        personaje1.setArmasActivas(arma);
        personaje2.setArmasActivas(arma);

        // Armadura
        Armadura armadura = new Armadura(0);
        armadura.setModificador(1);
        armadura.setModATQ(0);
        personaje1.setArmaduraActiva(armadura);
        personaje2.setArmaduraActiva(armadura);

        // Crear el combate
        int apuesta = 100;
        double penalizacion=apuesta*0.1;
        Combate combate = new Combate(jugador1, jugador2, apuesta, personaje1, personaje2, LocalDateTime.now());
        combate.setValido(true);
        combate.cancelarCombate();

        // Verificar el oro de los personajes después del combate, el jugador 2 es el que rechaza el combate.
        assertEquals(1000-penalizacion, personaje2.getOro(), "El oro de personaje 2 debe ser el mismo que al iniciar el combate");
    }
    @Test
    public void TestModificadores(){
        // Crear los jugadores
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();

        jugador1.setNombre("J1");
        jugador2.setNombre("J2");

        // Crear los personajes, un vampiro y un cazador
        Vampiro personaje1 = new Vampiro(0);
        Cazador personaje2 = new Cazador(3);

        // Configurar Vampiro
        personaje1.setSalud(4);
        double vidaInicial1 = 4;
        personaje1.setPoder(2);
        personaje1.setOro(1000);

        // Configurar cazador
        personaje2.setSalud(4);
        double vidaInicial2 = 4;
        personaje2.setPoder(2);
        personaje2.setOro(1000);

        // Habilidad especial
        HabilidadEspecial hab1 = new HabilidadEspecial(1, 1, 3);
        personaje1.setHabilidadEspecial(hab1);
        personaje2.setHabilidadEspecial(hab1);

        // Arma
        Arma arma = new Arma(0, true);
        arma.setModificador(1);
        personaje1.setArmasActivas(arma);
        personaje2.setArmasActivas(arma);

        // Armadura
        Armadura armadura = new Armadura(0);
        armadura.setModificador(1);
        armadura.setModATQ(0);
        personaje1.setArmaduraActiva(armadura);
        personaje2.setArmaduraActiva(armadura);
        //Creamos los modificadores
        Modificador mod1=new Modificador(4);
        Modificador mod2=new Modificador(2);
        Modificador mod3=new Modificador(1);
        Modificador mod4=new Modificador(3);
        Modificador mod5=new Modificador(2);
        Modificador mod6=new Modificador(1);
        //modificadores personaje1
        personaje1.setFortalezas(mod1);
        personaje1.setDebilidades(mod6);
        //Modificadores personaje2
        personaje2.setFortalezas(mod4);
        personaje2.setFortalezas(mod5);
        personaje2.setDebilidades(mod3);
        personaje2.setDebilidades(mod2);

        // Crear el combate
        int apuesta = 100;
        ArrayList<Modificador> ModificadoresCombate = new ArrayList<>();
        ModificadoresCombate.add(mod1);
        ModificadoresCombate.add(mod6);
        ModificadoresCombate.add(mod4);
        Combate combate = new Combate(jugador1, jugador2, apuesta, personaje1, personaje2, LocalDateTime.now());
        combate.setModificadores(ModificadoresCombate);
        combate.setValido(true);
        double ModPers1=combate.CalcularModificadores(combate.getModificadores(),personaje1);
        double ModPers2=combate.CalcularModificadores(combate.getModificadores(),personaje2);

        assertEquals(3,ModPers1);
        assertEquals(3,ModPers2);


    }
    @Test
    public void TestModificadoresSinModificadores(){
        // Crear los jugadores
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();

        jugador1.setNombre("J1");
        jugador2.setNombre("J2");

        // Crear los personajes, un vampiro y un cazador
        Vampiro personaje1 = new Vampiro(0);
        Cazador personaje2 = new Cazador(3);

        // Configurar Vampiro
        personaje1.setSalud(4);
        double vidaInicial1 = 4;
        personaje1.setPoder(2);
        personaje1.setOro(1000);

        // Configurar cazador
        personaje2.setSalud(4);
        double vidaInicial2 = 4;
        personaje2.setPoder(2);
        personaje2.setOro(1000);

        // Habilidad especial
        HabilidadEspecial hab1 = new HabilidadEspecial(1, 1, 3);
        personaje1.setHabilidadEspecial(hab1);
        personaje2.setHabilidadEspecial(hab1);

        // Arma
        Arma arma = new Arma(0, true);
        arma.setModificador(1);
        personaje1.setArmasActivas(arma);
        personaje2.setArmasActivas(arma);

        // Armadura
        Armadura armadura = new Armadura(0);
        armadura.setModificador(1);
        armadura.setModATQ(0);
        personaje1.setArmaduraActiva(armadura);
        personaje2.setArmaduraActiva(armadura);
        //Creamos los modificadores
        Modificador mod1=new Modificador(4);
        Modificador mod2=new Modificador(2);
        Modificador mod3=new Modificador(1);
        Modificador mod4=new Modificador(3);
        Modificador mod5=new Modificador(2);
        Modificador mod6=new Modificador(1);
        //modificadores personaje1
        personaje1.setFortalezas(mod1);
        personaje1.setDebilidades(mod6);
        //Modificadores personaje2
        personaje2.setFortalezas(mod4);
        personaje2.setFortalezas(mod5);
        personaje2.setDebilidades(mod3);
        personaje2.setDebilidades(mod2);

        // Crear el combate
        int apuesta = 100;
        ArrayList<Modificador> ModificadoresCombate = new ArrayList<>();
        Combate combate = new Combate(jugador1, jugador2, apuesta, personaje1, personaje2, LocalDateTime.now());
        combate.setModificadores(ModificadoresCombate);
        combate.setValido(true);
        double ModPers1=combate.CalcularModificadores(combate.getModificadores(),personaje1);
        double ModPers2=combate.CalcularModificadores(combate.getModificadores(),personaje2);

        assertEquals(0,ModPers1);
        assertEquals(0,ModPers2);


    }
}



