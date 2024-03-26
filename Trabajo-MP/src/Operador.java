
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Operador extends Usuario implements Serializable {

    public Operador(){
        this.setNick(null);
        this.setPass(null);
        this.setNombre(null);
    }
    public Operador(String name, String nick, String pass){
        this.setNick(nick);
        this.setPass(pass);
        this.setNombre(name);
    }

    public void preguntarDetallesOperador() {
        boolean ok = false;
        int num = 0;
        while (!ok && num<2){
            System.out.println("Introduzca nombre");
            String nombre = this.leerString();
            this.setNombre(nombre);
            System.out.println("Introduzca nick");
            String nick = this.leerString();
            this.setNick(nick);
            String pass = null;
            do {
                if (pass != null){
                    System.out.println("La pass debe tener entre 8 y 12 caracteres");
                }
                System.out.println("Introduzca pass");
                pass = this.leerString();
            } while (pass.length()>12 || pass.length()<8);
            this.setPass(pass);
            System.out.println("Introduzca 1 si esta de acuerdo con los datos introducidos");
            System.out.println("Nick: " + this.getNick());
            System.out.println("Nombre: " + this.getNombre());
            System.out.println("Contraseña: " + this.getPass());
            ok = this.leerString().equals("1");
            num++;
        }
        if (num>2){
            this.setPass(null);
        }
    }
    @Override
    public void Menu(Partida p) {
        int opcion = 0;
        while(opcion != 4 && opcion != 5) {
            System.out.println("Eliga la opción");
            System.out.println("1.-Revisar Combates no validos");
            System.out.println("2.-Bloquear/Desbloquear Jugador");
            System.out.println("3.-Editar Personaje");
            System.out.println("4.-Cerrar Sesión");
            System.out.println("5.-Dar de Baja Usuario");
            opcion = this.leerInt();
            if (opcion == 1) {
                this.revisarCombatesNoVal(p.getCombateQueue());
            } else if (opcion == 2) {
                this.bloquearJugador(p.getMapUsuarios());
            } else if (opcion == 3) {
                this.editarPersonaje(p);
            } else if (opcion == 4) {
                System.out.println("Esperamos volverte a ver pronto");
            } else if (opcion == 5) {
                int numBaja = 0;
                System.out.println("¿Estas seguro?");
                while (numBaja != 1 && numBaja != 2){
                    System.out.println("1.-Si, quiero darme de baja");
                    System.out.println("2.-No, deseo volver");
                    numBaja = this.leerInt();
                    if (numBaja == 1){
                        p.darDeBajaUsuario(this);
                    } else if (numBaja == 2) {
                        opcion=0; //vuelve al menu
                    }

                }

            }
        }
    }

    public void revisarCombatesNoVal(Queue<Combate> listaDesafiosSinValidar) {
        boolean done = false;
        int opcion = 0;

        if (listaDesafiosSinValidar.isEmpty()){
            System.out.println("No hay desafios que validar.");
            return;
        }

        while (!done && !(listaDesafiosSinValidar.isEmpty())){
            System.out.println("1.-Validar siguiente Combate");
            System.out.println("2.-Salir");
            opcion = this.leerInt();
            if (opcion ==1){
                validarSiguienteCombate(listaDesafiosSinValidar);
            } else if (opcion == 2) {
                done = true;
                System.out.println("Listo!");
            }

        }
    }

    private void validarSiguienteCombate(Queue<Combate> listaDesafiosSinValidar){
        Combate desafioActual = listaDesafiosSinValidar.remove();
        System.out.println(desafioActual.toString());
        System.out.println("1.-Validar");
        System.out.println("2.-No Validar");
        int opcionvalidar = 0;
        while (opcionvalidar != 1 && opcionvalidar != 2){
            opcionvalidar = this.leerInt();
            if (opcionvalidar == 1){
                desafioActual.setValido(true);
                System.out.println("Desafio Valido");
            } else if (opcionvalidar ==2) {
                System.out.println("Desafio rechazado");
            }
        }

        if (listaDesafiosSinValidar.isEmpty()){
            System.out.println("No hay desafios que validar.");
        }
    }

    public void bloquearJugador(Map<String,Usuario> mapaJugadores) {
        int contador = 0;
        boolean done = false;
        while (contador < 2 && !done) {
            System.out.println("Introduzca el nick del usuario a bloquear/desbloquar");
            String nick = leerString();
            for (Map.Entry<String, Usuario> entry : mapaJugadores.entrySet()) {
                Usuario u = entry.getValue();
                if (u.getNick().equals(nick) && u instanceof Jugador ) {
                    Jugador jugador = (Jugador) u;
                    bloquarse(jugador);
                    done = true;
                    break;
                }
            }
            if(!done){
                System.out.println("No se ha encontrado, intente de nuevo");
                System.out.println();
                contador++;
            }

        }
    }

    private void bloquarse(Jugador jugador){
        if (jugador.getBloqueado()){
            jugador.setBloqueado(false);
            System.out.println("Jugador " + jugador.getNick() + " exitosamente desbloqueado");
        } else {
            jugador.setBloqueado(true);
            System.out.println("Jugador " + jugador.getNick() + " exitosamente bloqueado");
        }
    }

    public void editarPersonaje(Partida p) {
        // TODO implement here
    }

    private int leerInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private String leerString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
