import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonajeTest {

    @Test
    public void DarValorAtqEquipoArmas1ManoTest(){
        //creamos un personaje, el tipo es indiferente, creamos un vampiro
        Vampiro personaje = new Vampiro(0);


        //arma
        Arma arma1 = new Arma(0, false);
        arma1.setModificador(2);
        Arma arma2 = new Arma(0, false);
        arma2.setModificador(2);

        personaje.setArmasActivasPruebas(arma1,arma2);
        //armadura
        Armadura armadura = new Armadura(1);
        armadura.setModificador(3);

        personaje.setArmaduraActiva(armadura);

        double valor= personaje.DarValorAtqEquipo();
        assertEquals(5,valor);


    }
    @Test
    public void DarValorAtqEquipoArma2ManosTest(){
        //creamos un personaje, el tipo es indiferente, creamos un vampiro
        Vampiro personaje = new Vampiro(0);


        //arma
        Arma arma = new Arma(0, true);
        arma.setModificador(2);

        personaje.setArmasActivasPruebas(arma,null);
        //armadura
        Armadura armadura = new Armadura(1);
        armadura.setModificador(3);

        personaje.setArmaduraActiva(armadura);

        double valor= personaje.DarValorAtqEquipo();
        assertEquals(3,valor);


    }
    @Test
    public void DarValorDefEquipoArma2ManosTest(){
        //creamos un personaje, el tipo es indiferente, creamos un vampiro
        Vampiro personaje = new Vampiro(0);


        //arma
        Arma arma = new Arma(1, true);
        arma.setModificador(2);

        personaje.setArmasActivasPruebas(arma,null);
        //armadura
        Armadura armadura = new Armadura(1);
        armadura.setModificador(3);

        personaje.setArmaduraActiva(armadura);

        double valor= personaje.DarValorDefEquipo();
        assertEquals(4,valor);


    }
    @Test
    public void DarValorDefEquipoArmas1ManoTest(){
        //creamos un personaje, el tipo es indiferente, creamos un vampiro
        Vampiro personaje = new Vampiro(0);


        //arma
        Arma arma1 = new Arma(2, false);
        arma1.setModificador(2);
        Arma arma2 = new Arma(1, false);
        arma2.setModificador(2);

        personaje.setArmasActivasPruebas(arma1,arma2);
        //armadura
        Armadura armadura = new Armadura(1);
        armadura.setModificador(3);

        personaje.setArmaduraActiva(armadura);

        double valor= personaje.DarValorDefEquipo();
        assertEquals(6,valor);


    }
    @Test
    public void DarVidaEsbirrosTest(){
        //creamos un personaje, el tipo es indiferente, creamos un Licantropo
        Licantropo personaje = new Licantropo(0);

        //Creamos Varios esbirros y se los añadimos al personaje
        Humano esbirro1=new Humano(0);
        esbirro1.setSalud(1);
        Demonio esbirro2=new Demonio(0);
        esbirro2.setSalud(2);
        Ghoul esbirro3=new Ghoul(0);
        esbirro3.setSalud(1);

        personaje.setEsbirros(esbirro1);
        personaje.setEsbirros(esbirro2);
        personaje.setEsbirros(esbirro3);
        double valor=personaje.darVidaEsbirros();

        assertEquals(4,valor);
    }
    @Test
    public void DarVidaEsbirrosDemoniosTest(){
        //creamos un personaje, el tipo es indiferente, creamos un Licantropo
        Licantropo personaje = new Licantropo(0);

        //Creamos Varios esbirros de demonios y al alguno de ellos le añadiremos esbirros
        Demonio esbirro1=new Demonio(0);
        esbirro1.setSalud(3);
        Demonio esbirro2=new Demonio(0);
        esbirro2.setSalud(3);
        Ghoul esbirro3=new Ghoul(0);
        esbirro3.setSalud(1);
        Humano esbirro4=new Humano(0);
        esbirro4.setSalud(1);
        //Añadimos a un esbirro otro, y a ese mismo a otro
        esbirro2.setEsbirroDemonio(esbirro3);
        esbirro1.setEsbirroDemonio(esbirro2);

        personaje.setEsbirros(esbirro1);
        personaje.setEsbirros(esbirro4);
        double valor=personaje.darVidaEsbirros();

        assertEquals(8,valor);
    }

}
