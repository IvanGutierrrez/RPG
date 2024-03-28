
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public abstract class Personaje implements Serializable, Cloneable {

    protected String Nombre;

    protected HabilidadEspecial HabilidadEspecial;

    protected Arma[] Armas;

    protected Arma[] ArmasActivas;

    protected Armadura[] Armaduras;

    protected Armadura ArmaduraActiva;

    protected Esbirro[] Esbirros;

    protected double Oro;

    protected double Salud;

    protected double Poder;

    protected Modificador[] Debilidades;

    protected Modificador[] Fortalezas;

    protected int Version;

    public String getNombre() {return this.Nombre;}

    public abstract double calcularPotencialAtaque();

    public abstract double calcularPotencialDefensa();

    public boolean checkApuesta(int apuesta){
        return this.Oro - apuesta > 0;
    }

    public void gestionEquipamiento() {
        int opcion = 0;
        int intento = 0;
        while (opcion != 3 || intento > 1) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Gestionar armas");
            System.out.println("2. Gestionar armaduras");
            System.out.println("3. Salir");

            opcion = this.leerInt();
            if (opcion == 1) {
                gestionarArmas();
            } else if (opcion == 2) {
                gestionarArmaduras();
            } else if (opcion == 3) {
                return;
            } else {
                intento = intento + 1;
                System.out.println("Opción no válida.");
            }
        }
    }

    private void gestionarArmas() {
        System.out.println("Armas disponibles:");
        for (int i = 0; i < Armas.length; i++) {
            Arma arma = Armas[i];
            System.out.println((i + 1) + ". " + arma.getNombre() + " (Tipo: " + (arma.getTipo() ? "Tipo 1" : "Tipo 2") + ")");
        }
        System.out.print("Seleccione un arma: ");
        int opcionArma = leerInt();
        if (opcionArma >= 1 && opcionArma <= Armas.length) {
            Arma armaSeleccionada = Armas[opcionArma - 1];
            if (armaSeleccionada.getTipo()) {
                // Tipo 1
                this.ArmasActivas[0] = armaSeleccionada;
                System.out.println("Has seleccionado el arma: " + armaSeleccionada.getNombre());
                opcionArma = leerInt();
                if (opcionArma >= 1 && opcionArma <= Armas.length) {
                    armaSeleccionada = Armas[opcionArma - 1];
                    if (armaSeleccionada.getTipo()) {
                        this.ArmasActivas[1] = armaSeleccionada;
                        System.out.println("Has seleccionado el arma: " + armaSeleccionada.getNombre());
                    } else {
                        System.out.print("Este arma es de dos manos y solo tienes una disponible");
                    }
                }
            } else {
                // Tipo 2
                this.ArmasActivas[0] = armaSeleccionada;
                this.ArmasActivas[1] = null;
                System.out.println("Has seleccionado el arma: " + armaSeleccionada.getNombre());
            }
        } else {
            System.out.println("Selección de arma inválida.");
        }
    }

    private void gestionarArmaduras() {
        System.out.println("Armaduras disponibles:");
        for (int i = 0; i < Armaduras.length; i++) {
            System.out.println((i + 1) + ". " + Armaduras[i].getNombre());
        }
        System.out.print("Seleccione una armadura: ");
        int opcionArmadura = leerInt();

        if (opcionArmadura >= 1 && opcionArmadura <= Armaduras.length) {
            System.out.println("Has seleccionado la armadura: " + Armaduras[opcionArmadura - 1].getNombre());
            this.ArmaduraActiva = Armaduras[opcionArmadura - 1];
        } else {
            System.out.println("Selección de armadura inválida.");
        }
    }

    public abstract void gestionarApuesta(int oro);

    public abstract void editarAtributos();

    public int getVersion(){
        return this.Version;
    }

    public void setVersion(int version){
        this.Version = version;
    }

    public Double getOro(){
        return this.Oro;
    }

    public void setOro(Double oro) {
        this.Oro = oro;
    }

    @Override
    public Personaje clone() {
        try {
            Personaje clone = (Personaje) super.clone();
            clone.Armas = this.Armas.clone();
            clone.HabilidadEspecial = this.HabilidadEspecial.clone();
            clone.ArmasActivas = this.ArmasActivas.clone();
            clone.Armaduras = this.Armaduras.clone();
            clone.Esbirros = this.Esbirros.clone();
            clone.Debilidades = this.Debilidades.clone();
            clone.Fortalezas = this.Fortalezas.clone();
            clone.ArmaduraActiva = this.ArmaduraActiva.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    private int leerInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}