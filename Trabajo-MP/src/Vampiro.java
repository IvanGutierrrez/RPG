
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Vampiro extends Personaje implements Serializable, Cloneable {

    private transient Scanner scanner = ScannerSingleton.getInstance();
    public Vampiro() {
        buildPersonajeFromInput();
    }


    private int Edad;

    private double Sangre;

    public Vampiro(int sangre) {
        this.Armas = new ArrayList<>();
        this.ArmasActivas = new ArrayList<>();
        this.Armaduras = new ArrayList<>();
        this.Esbirros = new ArrayList<>();
        this.Debilidades = new ArrayList<>();
        this.Fortalezas = new ArrayList<>();
        this.Version = 1;
        this.Sangre = sangre;

    }

    public double getSangre(){return this.Sangre;}

    public void setSangre(double sangre) {
        Sangre = sangre;
    }

    private void buildPersonajeFromInput() {
        this.Armas = new ArrayList<>();
        this.ArmasActivas = new ArrayList<>(2);
        this.Armaduras = new ArrayList<>();
        this.Esbirros = new ArrayList<>();
        this.Debilidades = new ArrayList<>();
        this.Fortalezas = new ArrayList<>();
        this.Version = 1;
        this.Nombre = inputNombre();
        this.Edad = inputEdad();
        this.Sangre = 0;
        this.Oro = inputOro();
        this.Salud = inputSalud();
        this.Poder = inputPoder();
    }

    public void SubirSangre(){
        this.Sangre=this.Sangre+4;
        if(this.Sangre>10){
            this.Sangre=10;
        }
    }

    @Override
    public String getNombre() {
        return this.Nombre;
    }

    @Override
    public double calcularPotencialAtaque() {
        double suma=0;
        suma=suma+super.Poder;
        suma=suma+super.DarValorAtqEquipo();
        if(this.Sangre>=5){
            suma=suma+2;
        }
        suma=suma+super.HabilidadEspecial.DarAtq(this.Sangre);

        return suma;
    }

    @Override
    public double calcularPotencialDefensa() {
        double suma=0;
        suma=suma+super.Poder;
        suma=suma+super.DarValorDefEquipo();
        if(this.Sangre>=5){
            suma=suma+2;
        }
        double aux=super.HabilidadEspecial.DarDef(this.Sangre);
        if(aux!=0){
            this.Sangre=this.Sangre-super.HabilidadEspecial.getCoste();
            suma=suma+aux;
        }
        return suma;
    }


    @Override
    public Vampiro clone() {
        return (Vampiro) super.clone();
    }

    protected int inputEdad() {
        int edad;
        do {
            System.out.println("Ingrese la edad:");
            edad = leerInt();
            if (edad < 0) {
                System.out.println("La edad debe ser un número positivo.");
            }
        } while (edad < 0);
        return edad;
    }

    @Override
    public void VolverAloNormal(double vidaJugador2) {
      super.Salud=vidaJugador2;
      this.Sangre=0;
    }

    private int leerInt() {
        boolean ok = false;
        int num = 0;
        while (!ok) {
            try {
                String n = scanner.nextLine();
                num = Integer.parseInt(n);
                ok = true;
            } catch (NumberFormatException e){
                System.out.println("Caracter no valido, introduzca un entero");
            }
        }
        return num;
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.scanner = ScannerSingleton.getInstance();
    }

}