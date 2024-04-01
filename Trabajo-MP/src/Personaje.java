
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public abstract class Personaje implements Serializable, Cloneable {

    protected String Nombre;

    protected HabilidadEspecial HabilidadEspecial;

    protected ArrayList<Arma> Armas;

    protected ArrayList<Arma> ArmasActivas;

    protected ArrayList<Armadura> Armaduras;

    protected Armadura ArmaduraActiva;

    protected ArrayList<Esbirro> Esbirros;

    protected double Oro;

    protected double Salud;

    protected double Poder;

    protected ArrayList<Modificador> Debilidades;

    protected ArrayList<Modificador> Fortalezas;

    protected int Version;

    public String getNombre() {return this.Nombre;}

    public abstract double calcularPotencialAtaque();

    public abstract double calcularPotencialDefensa();

    public boolean checkApuesta(int apuesta){
        return this.Oro - apuesta > 0;
    }

    public void gestionEquipamiento() {
        int opcion;
        int intento = 0;

        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Gestionar armas");
            System.out.println("2. Gestionar armaduras");
            System.out.println("3. Salir");

            opcion = this.leerInt();
            if (opcion == 1) {
                gestionarArmas();
            } else if (opcion == 2) {
                gestionarArmaduras();
            } else if (opcion != 3){
                intento = intento + 1;
                System.out.println("Opción no válida.");
            }
        }  while (opcion != 3 && intento < 2);
    }

    private void gestionarArmas() {
        if (this.ArmasActivas.isEmpty()) {
            ArmasActivas.add(null);
            ArmasActivas.add(null);
        }
        System.out.println("Armas disponibles:");
        for (int i = 0; i < Armas.size(); i++) {
            Arma arma = Armas.get(i);
            System.out.println((i + 1) + ". " + arma.getNombre() + " (Tipo: " + (arma.getTipo() ? "Tipo 1" : "Tipo 2") + ")");
        }
        System.out.print("Seleccione un arma: ");
        int opcionArma = leerInt();
        if (opcionArma >= 1 && opcionArma <= Armas.size()) {
            Arma armaSeleccionada = Armas.get(opcionArma - 1);
            if (!armaSeleccionada.getTipo()) {
                // Tipo 1
                this.ArmasActivas.set(0, armaSeleccionada);
                System.out.println("Has seleccionado el arma: " + armaSeleccionada.getNombre());
                System.out.print("Seleccione otro arma (si no desea otra arma introduzca 0): ");
                opcionArma = leerInt();
                if (opcionArma >= 1 && opcionArma <= Armas.size()) {
                    armaSeleccionada = Armas.get(opcionArma - 1);
                    if (armaSeleccionada.getTipo()) {
                        this.ArmasActivas.set(1, armaSeleccionada);
                        System.out.println("Has seleccionado el arma: " + armaSeleccionada.getNombre());
                    } else {
                        System.out.print("Este arma es de dos manos y solo tienes una disponible");
                    }
                } else {
                    this.ArmasActivas.set(1, null);
                }
            } else {
                // Tipo 2
                this.ArmasActivas.set(0, armaSeleccionada);
                this.ArmasActivas.set(1, null);
                System.out.println("Has seleccionado el arma: " + armaSeleccionada.getNombre());
            }
        } else {
            System.out.println("Selección de arma inválida.");
        }
    }

    private void gestionarArmaduras() {
        System.out.println("Armaduras disponibles:");
        for (int i = 0; i < Armaduras.size(); i++) {
            System.out.println((i + 1) + ". " + Armaduras.get(i).getNombre());
        }
        System.out.print("Seleccione una armadura: ");
        int opcionArmadura = leerInt();

        if (opcionArmadura >= 1 && opcionArmadura <= Armaduras.size()) {
            System.out.println("Has seleccionado la armadura: " + Armaduras.get(opcionArmadura - 1).getNombre());
            this.ArmaduraActiva = Armaduras.get(opcionArmadura - 1);
        } else {
            System.out.println("Selección de armadura inválida.");
        }
    }

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

            clone.Armas = new ArrayList<>();
            for (Arma arma : this.Armas) {
                clone.Armas.add(arma.clone());
            }
            clone.HabilidadEspecial = this.HabilidadEspecial.clone();
            clone.ArmasActivas = new ArrayList<>();
            clone.Armaduras = new ArrayList<>();
            for (Armadura armadura : this.Armaduras) {
                clone.Armaduras.add(armadura.clone());
            }
            clone.Esbirros = new ArrayList<>();
            for (Esbirro esbirro : this.Esbirros) {
                clone.Esbirros.add(esbirro.clone());
            }
            clone.Debilidades = new ArrayList<>();
            for (Modificador debilidad : this.Debilidades) {
                clone.Debilidades.add(debilidad.clone());
            }
            clone.Fortalezas = new ArrayList<>();
            for (Modificador fortaleza : this.Fortalezas) {
                clone.Fortalezas.add(fortaleza.clone());
            }
            clone.ArmaduraActiva = null;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    private int leerInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public double darVidaEsbirros() {
        double suma = 0;
        for(int i = 0; i < Esbirros.size(); i++) {
            suma=suma+Esbirros.get(i).obtenerSalud();
        }
        return suma;
    }

    protected double DarValorAtqEquipo() {
        double suma=0;
        for(int i = 0; i < ArmasActivas.size(); i++) {
            suma=suma+ArmasActivas.get(i).getModificador();
        }
        suma=suma+ArmaduraActiva.getModATQ();

        return suma;
    }

    protected double DarValorDefEquipo() {
        double suma=0;
        for(int i = 0; i < ArmasActivas.size(); i++) {
            suma=suma+ArmasActivas.get(i).getModDFS();
        }
        suma=suma+ArmaduraActiva.getModificador();

        return suma;
    }

    protected void editNombre(){
        System.out.println("Nombre actual: " + this.getNombre());
        this.Nombre = inputNombre();
    }

    protected void editOro(){
        System.out.println("Oro actual: " + this.getOro());
        this.Oro = inputOro();
    }

    protected void editSalud(){
        System.out.println("Salud actual: " + this.getSalud());
        this.Salud = inputSalud();
    }

    protected void editPoder(){
        System.out.println("Poder actual: " + this.getPoder());
        this.Poder = inputPoder();
    }

    public double getPoder() {
        return Poder;
    }

    public double getSalud() {
        return Salud;
    }

    protected List<Arma> getArmas() {
        return Armas;
    }

    protected List<Arma> getArmasActivas() {
        return ArmasActivas;
    }

    protected List<Armadura> getArmaduras() {
        return Armaduras;
    }

    protected List<Modificador> getDebilidades() {
        return Debilidades;
    }

    protected List<Modificador> getFortalezas() {
        return Fortalezas;
    }

    private double leerDouble() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    protected int inputOro() {
        int oro;
        do {
            System.out.println("Ingrese cantidad de oro:");
            oro = leerInt();
            if (oro <= 0) {
                System.out.println("El valor de oro debe ser mayor que 0.");
            }
        } while (oro <= 0);
        return oro;

    }

    protected String inputNombre() {
        Scanner scanner = new Scanner(System.in);
        String nombre;
        do {
            System.out.println("Ingrese el nombre:");
            nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío. Por favor, ingrese un nombre válido.");
            }
        } while (nombre.isEmpty());
        return nombre;
    }

    protected double inputSalud() {
        double salud;
        do {
            System.out.println("Ingrese cantidad de salud:");
            salud = leerDouble();
            if (salud <= 0) {
                System.out.println("El valor de salud debe ser mayor que 0.");
            }
        } while (salud <= 0);
        return salud;
    }

    protected double inputPoder() {
        double poder;
        do {
            System.out.println("Ingrese cantidad de poder:");
            poder = leerDouble();
            if (poder <= 0) {
                System.out.println("El valor de poder debe ser mayor que 0.");
            }
        } while (poder <= 0);
        return poder;
    }

    public HabilidadEspecial getHabilidadEspecial() {
        return HabilidadEspecial;
    }

    public List<Esbirro> getEsbirros() {
        return Esbirros;
    }

    protected void setHabilidadEspecial(HabilidadEspecial habilidadEspecial) {
        HabilidadEspecial = habilidadEspecial;
    }

    protected void setOro(double oro) {
        Oro = oro;
    }

    protected void setSalud(double salud) {
        Salud = salud;
    }

    protected void setPoder(double poder) {
        Poder = poder;
    }

    public abstract void VolverAloNormal(double vidaJugador2);
}