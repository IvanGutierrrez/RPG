
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Demonio extends Esbirro implements Serializable, Cloneable{

    private boolean Pacto;

    private String DescrPact;

    private ArrayList<Esbirro> Esbirros;

    private transient Scanner scanner = ScannerSingleton.getInstance();

    public void setEsbirroDemonio(Esbirro e){
        this.Esbirros.add(e);
    }

    public Demonio(int i) {
        super();
        this.Esbirros=new ArrayList<>();
    }


    @Override
    public Demonio clone() {
        Demonio clone = (Demonio) super.clone();
        clone.Esbirros = new ArrayList<>(this.Esbirros);
        return clone;
    }

    public Demonio(){
        demonioFromInput();
    }

    private void demonioFromInput(){
        this.Nombre = inputNombre();
        this.Salud = inputSalud();
        this.Pacto = inputPacto();
        this.DescrPact= inputDescrPacto();
        this.Esbirros = new ArrayList<>();
    }

    protected boolean inputPacto() {
        int opcion;
        boolean pacto;
        do {
            System.out.println("¿El personaje tiene pacto?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            opcion = leerInt();
            if (opcion != 1 && opcion != 2) {
                System.out.println("Opción incorrecta. Por favor, ingrese 1 o 2.");
            }
        } while (opcion != 1 && opcion != 2);

        pacto = (opcion == 1);
        return pacto;
    }

    @Override
    protected void ModificarEsbirro(){
        int ans;
        do {
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar salud");
            System.out.println("3. Modificar pacto");
            System.out.println("4. Modificar descripcion del pacto");
            System.out.println("5. Modificar esbirros del demonio");
            System.out.println("6. Salir");
            ans = leerInt();
            if(ans == 1){
                System.out.println("Nombre actual: " + this.Nombre);
                this.Nombre = inputNombre();
            } else if (ans == 2) {
                System.out.println("Salud actual: " + this.Salud);
                this.Salud=inputSalud();
            } else if (ans == 3) {
                System.out.println("Dependencia actual: " + this.Pacto);
                this.Pacto=inputPacto();
            } else if (ans == 4) {
                System.out.println("Descripcion actual: " + this.DescrPact);
                this.DescrPact=inputDescrPacto();
            } else if (ans == 5) {
                gestionEsbirros(this);
            }

        } while (ans!=6);
    }

    @Override
    public double obtenerSalud() {
        double suma=0;
        for(int i=0;i<this.Esbirros.size();i++){
           suma=suma+this.Esbirros.get(i).obtenerSalud();
        }
        suma=suma+ super.Salud;
        return suma;
    }

    private void gestionEsbirros(Demonio demonio) {
        int ans;
        do {
            System.out.println("1. Añadir esbirro al demonio");
            System.out.println("2. Eliminar esbirro del demonio");
            System.out.println("3. Editar esbirro del demonio");
            System.out.println("4. Salir");
            ans = leerInt();
            if(ans == 1){
                crearEsbirro(demonio);
            } else if (ans == 2) {
                removerEsbirro(demonio);
            } else if (ans == 3) {
                ModificarEsbirrosDem(demonio);
            }
        } while (ans!=4);
    }

    public void crearEsbirro(Demonio esbirro){
        int ans;
        do {
            System.out.println("1. Crear ghoul");
            System.out.println("2. Crear demonio");
            System.out.println("3. Crear humano");
            ans = leerInt();
            if(ans == 1){
                Esbirro esbirro2 = new Ghoul();
                esbirro.getEsbirros().add(esbirro2);
            } else if (ans == 2) {
                Esbirro esbirro2 = new Demonio();
                esbirro.getEsbirros().add(esbirro2);
            } else if (ans == 3) {
                Esbirro esbirro2 = new Humano();
                esbirro.getEsbirros().add(esbirro2);
            }

        } while (ans<=0 || ans >3);

    }



    private void ModificarEsbirrosDem(Demonio esbirro) {
        List<Esbirro> esbirros = esbirro.getEsbirros();
        if (esbirros.isEmpty()) {
            System.out.println("No hay esbirros por editar");
        } else {

            int input;
            do {
                System.out.println("¿Qué esbirro vas a editar?");
                for (int i = 0; i < esbirros.size(); i++) {
                    System.out.println((i + 1) + ". " + esbirros.get(i).getNombre());
                }
                System.out.println(esbirros.size() + 1 + ". Salir");
                input = leerInt();
                if (input > 0 && input <= esbirros.size()) {
                    Esbirro esbirro2 = esbirros.get(input - 1);
                    if (esbirro2 instanceof Demonio demonio) {
                        System.out.println("El esbirro seleccionado es un demonio.");
                        demonio.ModificarEsbirro();
                    } else if (esbirro2 instanceof Humano humano) {
                        System.out.println("El esbirro seleccionado es un humano.");
                        humano.ModificarEsbirro();
                    } else if (esbirro2 instanceof Ghoul ghoul) {
                        System.out.println("El esbirro seleccionado es un ghoul.");
                        ghoul.ModificarEsbirro();
                    }
                } else {
                    System.out.println("Valor no válido, por favor introduzca uno nuevo");
                }
            } while (!(input == esbirros.size() + 1));
        }
    }

    private void removerEsbirro(Demonio esbirro) {
        List<Esbirro> esbirros = esbirro.getEsbirros();
        if (esbirros.isEmpty()) {
            System.out.println("No hay esbirros por eliminar");
        } else {
            int input;
            do {
                System.out.println("¿Qué esbirro vas a eliminar?");
                for (int i = 0; i < esbirros.size(); i++) {
                    System.out.println((i + 1) + ". " + esbirros.get(i).getNombre());
                }
                System.out.println(esbirros.size() + 1 + ". Salir");
                input = leerInt();
                if (input > 0 && input <= esbirros.size()) {
                    esbirros.remove(input - 1);
                    System.out.println("¡Esbirro eliminado exitosamente!");
                } else {
                    System.out.println("Valor no válido, por favor introduzca uno nuevo");
                }
            } while (input == esbirros.size() + 1);
        }
    }

    private String inputDescrPacto() {
        String descrPacto;
        do {
            System.out.println("Ingrese la descripción del pacto:");
            descrPacto = scanner.nextLine();
        } while (descrPacto.isEmpty());
        return descrPacto;
    }

    public List<Esbirro> getEsbirros() {
        return Esbirros;
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.scanner = ScannerSingleton.getInstance();
    }
}