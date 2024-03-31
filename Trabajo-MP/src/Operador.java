
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
        while(opcion != 5 && opcion != 6) {
            System.out.println("Eliga la opción");
            System.out.println("1.-Revisar Combates no validos");
            System.out.println("2.-Bloquear/Desbloquear Jugador");
            System.out.println("3.-Crear Personaje");
            System.out.println("4.-Editar Personaje");
            System.out.println("5.-Cerrar Sesión");
            System.out.println("6.-Dar de Baja Usuario");
            opcion = this.leerInt();
            if (opcion == 1) {
                this.revisarCombatesNoVal(p.getCombateQueue());
            } else if (opcion == 2) {
                this.bloquearJugador(p.getMapUsuarios());
            } else if (opcion == 3){
                this.crearPersonaje(p);
            } else if (opcion == 4) {
                this.editarPersonaje(p);
            } else if (opcion == 5) {
                System.out.println("Esperamos volverte a ver pronto");
            } else if (opcion == 6) {
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
        int opcion;

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

    public void bloquearJugador(Map<String, Usuario> mapaJugadores) {
        int contador = 0;
        boolean done = false;
        while (contador < 2 && !done) {
            System.out.println("Introduzca el nick del usuario a bloquear/desbloquar");
            String nick = leerString();
            for (Map.Entry<String, Usuario> entry : mapaJugadores.entrySet()) {
                Usuario u = entry.getValue();
                if (u instanceof Jugador jugador){
                    if (jugador.getNick().equals(nick)) {
                        bloquarse(jugador);
                        done = true;
                        break;
                    }
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

    public void crearPersonaje(Partida partida){
        int ans;
        do {
            System.out.println("1. Crear vampiro");
            System.out.println("2. Crear licantropo");
            System.out.println("3. Crear cazador");
            ans = leerInt();
            if(ans == 1){
                Personaje nuevoPersonaje = new Vampiro();
                partida.getListaPersonajes().add(nuevoPersonaje);
            } else if (ans == 2) {
                Personaje nuevoPersonaje = new Licantropo();
                partida.getListaPersonajes().add(nuevoPersonaje);
            } else if (ans == 3) {
                Personaje nuevoPersonaje = new Cazador();
                partida.getListaPersonajes().add(nuevoPersonaje);
            }

        } while (ans<=0 || ans >3);
    }

    public void editarPersonaje(Partida p) {
        List<Personaje> listaPersonajes = p.getListaPersonajes();
        System.out.println("Personaje a editar:");
        System.out.println();
        int ans;
        if (!listaPersonajes.isEmpty()){
            do {
                for (int i = 0; i < listaPersonajes.size(); i++){
                    System.out.println(i+1 + ". " + listaPersonajes.get(i).getNombre());
                }
                System.out.println(listaPersonajes.size()+1 +". Salir");
                ans = leerInt();
                if (ans >= 1 && ans<=listaPersonajes.size()){
                    Personaje personajeActual = listaPersonajes.get(ans -1);
                    menuCambioPersonaje(personajeActual);
                }
            } while (ans!=listaPersonajes.size()+1);
        } else {
            System.out.println("Aun no hay personajes");
        }

    }

    private void menuCambioPersonaje(Personaje personaje) {
        boolean edited = false;
        int opcion = 0;
        while(opcion != 4) {
            System.out.println("Eliga la opción");
            System.out.println("1.-Editar Armas");
            System.out.println("2.-Editar Armadura");
            System.out.println("3.-Editar Personaje");
            System.out.println("4.-Salir");
            opcion = this.leerInt();
            if (opcion == 1) {
                edited = gestionArmas(personaje);
            } else if (opcion == 2) {
                edited = gestionArmadura(personaje);
            } else if (opcion == 3) {
                edited = gestionHabilidadEspecial(personaje);
            } else if (opcion == 4) {
                edited = false;
            }
            if (edited){
                personaje.setVersion(personaje.getVersion()+1);
            }
        }
    }

    private boolean gestionModificadores(Personaje personaje) {
        int ans;
        boolean changed;
        do {
            System.out.println("1. Modificar Fortalezas");
            System.out.println("2. Modificar Debilidades");
            System.out.println("3. Salir");
            ans = leerInt();
            if(ans == 1){
                changed = ModificarFortalezas(personaje);
            } else if (ans == 2) {
                changed = removerHabilidadEspecial(personaje);
            } else {
                changed = false;
            }

        } while (ans!=3);

        return changed;
    }

    private boolean ModificarDebilidades(Personaje personaje) {
        int ans;
        boolean changed;
        do {
            System.out.println("1. Añadir Debilidad");
            System.out.println("2. Remover Debilidad");
            System.out.println("3. Salir");
            ans = leerInt();
            if(ans == 1){
                changed = añadirDebilidad(personaje);
            } else if (ans == 2) {
                changed = removerDebilidad(personaje);
            } else {
                changed = false;
            }

        } while (ans!=3);

        return changed;
    }

    private boolean ModificarFortalezas(Personaje personaje) {
        int ans;
        boolean changed;
        do {
            System.out.println("1. Añadir Fortaleza");
            System.out.println("2. Remover Fortaleza");
            System.out.println("3. Salir");
            ans = leerInt();
            if(ans == 1){
                changed = añadirFortaleza(personaje);
            } else if (ans == 2) {
                changed = removerFortaleza(personaje);
            } else {
                changed = false;
            }

        } while (ans!=3);

        return changed;
    }

    private boolean añadirFortaleza(Personaje personaje) {
        Modificador fortaleza = new Modificador();
        personaje.getFortalezas().add(fortaleza);
        return true;
    }

    private boolean removerFortaleza(Personaje personaje) {
        List<Modificador> fortalezas = personaje.getFortalezas();
        if (fortalezas.isEmpty()) {
            System.out.println("No hay fortalezas por eliminar");
            return false;
        } else {
            System.out.println("¿Qué fortaleza vas a eliminar?");
            for (int i = 0; i < fortalezas.size(); i++) {
                System.out.println((i + 1) + ". " + fortalezas.get(i).getNombre());
            }
            System.out.println(fortalezas.size() + 1 + ". Salir");
            int input;
            do {
                input = leerInt();
                if (input > 0 && input <= fortalezas.size()) {
                    fortalezas.remove(input - 1);
                    System.out.println("¡Fortaleza eliminada exitosamente!");
                    return true;
                } else if (input == fortalezas.size() + 1) {
                    return false;
                } else {
                    System.out.println("Valor no válido, por favor introduzca uno nuevo");
                }
            } while (true);
        }
    }

    private boolean removerDebilidad(Personaje personaje) {
        List<Modificador> debilidades = personaje.getDebilidades();
        if (debilidades.isEmpty()) {
            System.out.println("No hay debilidades por eliminar");
            return false;
        } else {
            System.out.println("¿Qué debilidad vas a eliminar?");
            for (int i = 0; i < debilidades.size(); i++) {
                System.out.println((i + 1) + ". " + debilidades.get(i).getNombre());
            }
            System.out.println(debilidades.size() + 1 + ". Salir");
            int input;
            do {
                input = leerInt();
                if (input > 0 && input <= debilidades.size()) {
                    debilidades.remove(input - 1);
                    System.out.println("¡Debilidad eliminada exitosamente!");
                    return true;
                } else if (input == debilidades.size() + 1) {
                    return false;
                } else {
                    System.out.println("Valor no válido, por favor introduzca uno nuevo");
                }
            } while (true);
        }
    }

    private boolean añadirDebilidad(Personaje personaje) {
        Modificador debilidad = new Modificador();
        personaje.getDebilidades().add(debilidad);
        return true;
    }


    private boolean gestionHabilidadEspecial(Personaje personaje) {
        int ans;
        boolean changed;
        do {
            System.out.println("1. Añadir Habilidad Especial");
            System.out.println("2. Remover Habilidad Especial");
            System.out.println("3. Salir");
            ans = leerInt();
            if(ans == 1){
                changed = añadirHabilidadEspecial(personaje);
            } else if (ans == 2) {
                changed = removerHabilidadEspecial(personaje);
            } else {
                changed = false;
            }

        } while (ans!=3);

        return changed;
    }

    private boolean removerHabilidadEspecial(Personaje personaje) {
        personaje.setHabilidadEspecial(null);
        return true;
    }

    private boolean añadirHabilidadEspecial(Personaje personaje) {
        HabilidadEspecial habilidadEspecial = new HabilidadEspecial();
        personaje.setHabilidadEspecial(habilidadEspecial);
        return true;
    }


    private boolean gestionArmas(Personaje personaje) {
        int ans;
        boolean changed;
        do {
            System.out.println("1. Añadir Arma");
            System.out.println("2. Remover Arma");
            System.out.println("3. Salir");
            ans = leerInt();
            if(ans == 1){
                changed = añadirArma(personaje);
            } else if (ans == 2) {
                changed = removerArma(personaje);
            } else {
                changed = false;
            }

        } while (ans!=3);

        return changed;
    }

    private boolean gestionArmadura(Personaje personaje) {
        int ans;
        boolean changed;
        do {
            System.out.println("1. Añadir Armadura");
            System.out.println("2. Remover Armadura");
            System.out.println("3. Salir");
            ans = leerInt();
            if(ans == 1){
                changed = añadirArmadura(personaje);
            } else if (ans == 2) {
                changed = removerArmadura(personaje);
            } else {
                changed = false;
            }

        } while (ans!=3);

        return changed;
    }

    private boolean removerArma(Personaje personaje) {
        List<Arma> listaArmas = personaje.getArmas();
        if (listaArmas.isEmpty()){
            System.out.println("No hay armas por eliminar");
            return false;
        } else{
            System.out.println("¿Que arma vas a eliminar?");
            for (int i = 0; i < listaArmas.size(); i++) {
                System.out.println((i + 1) + ". " + listaArmas.get(i).getNombre());
            }
            int input;
            do {
                input = leerInt();
                if (input>0 && input <=listaArmas.size()){
                    listaArmas.remove(input-1);
                    System.out.println("Listo!");
                    return true;
                } else {
                    System.out.println("Valor no valido, introduzca uno nuevo");
                }
            } while(!(input>0 && input <=listaArmas.size()));
        }
        return false;
    }

    private boolean removerArmadura(Personaje personaje) {
        List<Arma> listaArnaduras = personaje.getArmas();
        if (listaArnaduras.isEmpty()){
            System.out.println("No hay armaduras por eliminar");
            return false;
        } else{
            System.out.println("¿Que armadura vas a eliminar?");
            for (int i = 0; i < listaArnaduras.size(); i++) {
                System.out.println((i + 1) + ". " + listaArnaduras.get(i).getNombre());
            }
            int input;
            do {
                input = leerInt();
                listaArnaduras.remove(input -1);
                if (input>0 && input <=listaArnaduras.size()){
                    System.out.println("Listo!");
                    return true;
                } else {
                    System.out.println("Valor no valido, introduzca uno nuevo");
                }
            } while(!(input>0 && input <=listaArnaduras.size()));
        }
        return false;
    }

    private boolean añadirArma(Personaje personaje) {
        Arma arma = new Arma();
        personaje.getArmas().add(arma);
        return true;
    }

    private boolean añadirArmadura(Personaje personaje) {
        Armadura armarmadura = new Armadura();
        personaje.getArmaduras().add(armarmadura);
        return true;
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
