import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CazadorTest {
    @Test
    public void CalculaPotencialAtaqueConHabilidadTest(){
        //En primer lugar creamos el persoanje
        Cazador personaje=new Cazador(0);
        //Le asiganmos los valores que toman parte en el metodo
        personaje.setSalud(4);
        personaje.setPoder(2);
        personaje.setVoluntad(2);

        // Habilidad especial
        HabilidadEspecial hab1 = new HabilidadEspecial(1, 1, 2);
        personaje.setHabilidadEspecial(hab1);

        // Arma
        Arma arma = new Arma(0, true);
        arma.setModificador(1);
        personaje.setArmasActivasPruebas(arma,null);

        // Armadura
        Armadura armadura = new Armadura(0);
        armadura.setModificador(1);
        personaje.setArmaduraActiva(armadura);

        double valor=personaje.calcularPotencialAtaque();

        assertEquals(6,valor);

    }
    @Test
    public void CalculaPotencialAtaqueSinHabilidadTest(){
        //En primer lugar creamos el persoanje
        Cazador personaje=new Cazador(0);
        //Le asiganmos los valores que toman parte en el metodo
        personaje.setSalud(4);
        personaje.setPoder(2);
        personaje.setVoluntad(1);

        // Habilidad especial
        HabilidadEspecial hab1 = new HabilidadEspecial(1, 1, 2);
        personaje.setHabilidadEspecial(hab1);

        // Arma
        Arma arma = new Arma(0, true);
        arma.setModificador(1);
        personaje.setArmasActivasPruebas(arma,null);

        // Armadura
        Armadura armadura = new Armadura(0);
        armadura.setModificador(1);
        personaje.setArmaduraActiva(armadura);

        double valor=personaje.calcularPotencialAtaque();

        assertEquals(4,valor);

    }
    @Test
    public void CalculaPotencialDefensaConHabilidadTest(){
        //En primer lugar creamos el persoanje
        Cazador personaje=new Cazador(0);
        //Le asiganmos los valores que toman parte en el metodo
        personaje.setSalud(4);
        personaje.setPoder(2);
        personaje.setVoluntad(3);

        // Habilidad especial
        HabilidadEspecial hab1 = new HabilidadEspecial(1, 1, 2);
        personaje.setHabilidadEspecial(hab1);

        // Arma
        Arma arma = new Arma(0, true);
        arma.setModificador(1);
        personaje.setArmasActivasPruebas(arma,null);

        // Armadura
        Armadura armadura = new Armadura(0);
        armadura.setModificador(1);
        personaje.setArmaduraActiva(armadura);

        double valor=personaje.calcularPotencialDefensa();

        assertEquals(7,valor);

    }
    @Test
    public void CalculaPotencialDefensaSinHabilidadTest(){
        //En primer lugar creamos el persoanje
        Cazador personaje=new Cazador(0);
        //Le asiganmos los valores que toman parte en el metodo
        personaje.setSalud(4);
        personaje.setPoder(2);
        personaje.setVoluntad(2);

        // Habilidad especial
        HabilidadEspecial hab1 = new HabilidadEspecial(1, 1, 3);
        personaje.setHabilidadEspecial(hab1);

        // Arma
        Arma arma = new Arma(0, true);
        arma.setModificador(1);
        personaje.setArmasActivasPruebas(arma,null);

        // Armadura
        Armadura armadura = new Armadura(0);
        armadura.setModificador(1);
        personaje.setArmaduraActiva(armadura);

        double valor=personaje.calcularPotencialDefensa();

        assertEquals(5,valor);

    }

}