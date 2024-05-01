import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LicantropoTest {
    @Test
    public void CalculaPotencialAtaqueConHabilidadTest(){
        //En primer lugar creamos el persoanje
        Licantropo personaje=new Licantropo(0);
        //Le asiganmos los valores que toman parte en el metodo
        personaje.setSalud(4);
        personaje.setPoder(2);
        personaje.setRabia(3);

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

        assertEquals(7,valor);

    }
    @Test
    public void CalculaPotencialAtaqueSinHabilidadTest(){
        //En primer lugar creamos el persoanje
        Licantropo personaje=new Licantropo(0);
        //Le asiganmos los valores que toman parte en el metodo
        personaje.setSalud(4);
        personaje.setPoder(1);
        personaje.setRabia(1);

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

        assertEquals(3,valor);

    }
    @Test
    public void CalculaPotencialDefensaConHabilidadTest(){
        //En primer lugar creamos el persoanje
        Licantropo personaje=new Licantropo(0);
        //Le asiganmos los valores que toman parte en el metodo
        personaje.setSalud(4);
        personaje.setPoder(2);
        personaje.setRabia(3);

        // Habilidad especial
        HabilidadEspecial hab1 = new HabilidadEspecial(1, 2, 2);
        personaje.setHabilidadEspecial(hab1);

        // Arma
        Arma arma = new Arma(0, true);
        arma.setModificador(1);
        personaje.setArmasActivasPruebas(arma,null);

        // Armadura
        Armadura armadura = new Armadura(0);
        armadura.setModificador(2);
        personaje.setArmaduraActiva(armadura);

        double valor=personaje.calcularPotencialDefensa();

        assertEquals(9,valor);

    }
    @Test
    public void CalculaPotencialDefensaSinHabilidadTest(){
        //En primer lugar creamos el persoanje
        Licantropo personaje=new Licantropo(0);
        //Le asiganmos los valores que toman parte en el metodo
        personaje.setSalud(4);
        personaje.setPoder(2);
        personaje.setRabia(1);

        // Habilidad especial
        HabilidadEspecial hab1 = new HabilidadEspecial(1, 2, 2);
        personaje.setHabilidadEspecial(hab1);

        // Arma
        Arma arma = new Arma(0, true);
        arma.setModificador(1);
        personaje.setArmasActivasPruebas(arma,null);

        // Armadura
        Armadura armadura = new Armadura(0);
        armadura.setModificador(2);
        personaje.setArmaduraActiva(armadura);

        double valor=personaje.calcularPotencialDefensa();

        assertEquals(5,valor);

    }

}