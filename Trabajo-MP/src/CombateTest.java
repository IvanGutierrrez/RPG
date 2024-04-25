import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CombateTest {
    @Test
    public void testCombateResultadoCorrecto(){
        //creamos los Jugadores
        Jugador jugador1= new Jugador();
        Jugador jugador2= new Jugador();

        jugador1.setNombre("J1");
        jugador2.setNombre("J2");
        //creamos los personajes, en este caso un vampiro y un licantropo
        Vampiro personaje1=new Vampiro();
        Licantropo personaje2=new Licantropo();

        //Vampiro
        personaje1.setSalud(4);
        personaje1.setPoder(2);
        personaje1.setOro(1000);
        personaje1.setSangre(0);
        //Licantropo
        personaje2.setSalud(4);
        personaje2.setPoder(2);
        personaje2.setOro(1000);
        personaje2.setRabia(0);
        //habilidad especial
        HabilidadEspecial hab1=new HabilidadEspecial();
        hab1.setValorATC(1);
        hab1.setValorDFS(1);
        hab1.setCoste(3);
        personaje1.setHabilidadEspecial(hab1);
        personaje2.setHabilidadEspecial(hab1);
        //arma
        Arma arma=new Arma();
        arma.setTipo(true);
        arma.setModDFS(0);
        arma.setModificador(1);
        personaje1.setArmasActivas(arma);
        personaje2.setArmasActivas(arma);
        //armadura
        Armadura armadura=new Armadura();
        armadura.setModificador(1);
        armadura.setModATQ(0);
        personaje1.setArmaduraActiva(armadura);
        personaje2.setArmaduraActiva(armadura);

        //Creamos el combate
        Combate combate=new Combate(jugador1,jugador2,100,personaje1,personaje2, LocalDateTime.now());
        combate.setValido(true);
        combate.combatir();
        if(combate.getPersonajeRetador().getSalud() == 0 || combate.getPersonajeRetado().getSalud() == 0){
            assertTrue(combate.getGanador() == jugador1 || combate.getGanador() == jugador2);

        } else if (combate.getPersonajeRetador().getSalud() == 0 && combate.getPersonajeRetado().getSalud() == 0) {
            assertNull(combate.getGanador());
        }


    }

}