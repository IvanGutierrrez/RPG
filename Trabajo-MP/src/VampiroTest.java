import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VampiroTest {

    @Test
    public void CalculaPotencialAtaqueConHabilidadTest(){
        //En primer lugar creamos el persoanje
        Vampiro personaje=new Vampiro(0);
        //Le asiganmos los valores que toman parte en el metodo
        personaje.setSalud(4);
        personaje.setPoder(2);
        personaje.setSangre(5);

        // Habilidad especial
        HabilidadEspecial hab1 = new HabilidadEspecial(1, 1, 4);
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
        Vampiro personaje=new Vampiro(0);
        //Le asiganmos los valores que toman parte en el metodo
        personaje.setSalud(4);
        personaje.setPoder(2);
        personaje.setSangre(5);

        // Habilidad especial
        HabilidadEspecial hab1 = new HabilidadEspecial(3, 1, 7);
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

        assertEquals(5,valor);

    }
    @Test
    public void CalculaPotencialDefensaConHabilidadTest(){
        //En primer lugar creamos el persoanje
        Vampiro personaje=new Vampiro(0);
        //Le asiganmos los valores que toman parte en el metodo
        personaje.setSalud(4);
        personaje.setPoder(2);
        personaje.setSangre(4);

        // Habilidad especial
        HabilidadEspecial hab1 = new HabilidadEspecial(1, 1, 4);
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

        assertEquals(4,valor);
        assertEquals(0,personaje.getSangre());

    }
    @Test
    public void CalculaPotencialDefensaSinHabilidadTest(){
        //En primer lugar creamos el persoanje
        Vampiro personaje=new Vampiro(0);
        //Le asiganmos los valores que toman parte en el metodo
        personaje.setSalud(4);
        personaje.setPoder(2);
        personaje.setSangre(4);

        // Habilidad especial
        HabilidadEspecial hab1 = new HabilidadEspecial(1, 1, 5);
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

        assertEquals(3,valor);
        assertEquals(4,personaje.getSangre());

    }

}