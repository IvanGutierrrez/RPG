
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Operador extends Usuario implements Serializable {
    private transient Scanner scanner = ScannerSingleton.getInstance();

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

    private boolean nickUnico(String nick) throws IOException {
        File archivo = new File("Trabajo-MP/datos/operadores/operador");
        FileReader fileReader = new FileReader(archivo);
        BufferedReader buf = new BufferedReader(fileReader);
        String linea = buf.readLine(); // Lees la primera línea
        String[] result;
        while ((linea = buf.readLine()) != null) {
            result = linea.split(",");
            if (result[1].equals(nick)){
                buf.close(); // Importante cerrar el BufferedReader
                return false;
            }
        }
        buf.close(); // Importante cerrar el BufferedReader
        return true;
    }

    public void preguntarDetallesOperador(Partida p) throws IOException {
        boolean ok = false;
        int num = 0;
        while (!ok && num<2){
            System.out.println("Introduzca nombre");
            String nombre = this.leerString();
            this.setNombre(nombre);
            String nick = null;
            do{
                if (nick != null){
                    System.out.println("Ese nick ya esta cogido");
                }
                System.out.println("Introduzca nick");
                nick = this.leerString();
            } while(!p.nickUnico(nick) && this.nickUnico(nick));
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
    public void Menu(Partida p) throws IOException {
        int opcion = 0;
        while(opcion != 6 && opcion != 7) {
            System.out.println("Eliga la opción");
            System.out.println("1.-Revisar Combates no validos");
            System.out.println("2.-Bloquear/Desbloquear Jugador");
            System.out.println("3.-Crear Personaje");
            System.out.println("4.-Editar Personaje");
            System.out.println("5.-Remover Personaje");
            System.out.println("6.-Cerrar Sesión");
            System.out.println("7.-Dar de Baja Usuario");
            opcion = this.leerInt();
            if (opcion == 1) {
                this.revisarCombatesNoVal(p);
            } else if (opcion == 2) {
                this.bloquearJugador(p.getMapUsuarios());
            } else if (opcion == 3){
                this.crearPersonaje(p);
            } else if (opcion == 4) {
                this.editarPersonaje(p);
            } else if (opcion == 5) {
                this.removerPersonaje(p);
            } else if (opcion == 6) {
                System.out.println("Esperamos volverte a ver pronto");
            } else if (opcion == 7) {
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
            p.serializar();
        }
    }

    public void revisarCombatesNoVal(Partida partida) {
        Queue<Combate> listaDesafiosSinValidar =  partida.getCombateQueue();
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
                ValidarRetador(desafioActual.getJugadorRetador(), desafioActual);
                ValidarRetado(desafioActual.getJugadorRetado(), desafioActual);
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

    public void ValidarRetador(Jugador jugador, Combate desafio){
        System.out.println("Personaje del Jugador Retador:");
        System.out.println("Debilidades:");
        ValidarDebilidades(jugador.getPersonajeActivo(), desafio);
        System.out.println("Fortalezas:");
        ValidarFortalezas(jugador.getPersonajeActivo(), desafio);
        System.out.println("Jugador retador validado");
    }

    public void ValidarRetado(Jugador jugador, Combate desafio){
        System.out.println("Personaje del Jugador Retado:");
        System.out.println("Debilidades:");
        ValidarDebilidades(jugador.getPersonajeActivo(), desafio);
        System.out.println("Fortalezas:");
        ValidarFortalezas(jugador.getPersonajeActivo(), desafio);
        System.out.println("Jugador retado validado");
    }

    public void ValidarFortalezas(Personaje personaje, Combate desafioActual){
        List<Modificador> fortalezas = personaje.getFortalezas();

            int input;
            do {
                if (fortalezas.isEmpty()) {
                    System.out.println("No hay fortalezas disponibles, cree nuevas");}
                System.out.println("¿Qué fortaleza añadiras?");
                for (int i = 0; i < fortalezas.size(); i++) {
                    System.out.println((i + 1) + ". " + fortalezas.get(i).getNombre());
                }
                System.out.println(fortalezas.size() + 1 + ". Crear Fortaleza Nueva");
                System.out.println(fortalezas.size() + 2 + ". Listo!");
                input = leerInt();
                if (input > 0 && input <= fortalezas.size()) {
                    Modificador newfortaleza = fortalezas.get(input -1);
                    if (desafioActual.getModificadores().contains(newfortaleza)){
                        System.out.println("Fortaleza ya en el desafio, escoja otra");
                    } else {
                        desafioActual.getModificadores().add(newfortaleza);
                    }
                } else if (input == fortalezas.size() + 1) {
                    Modificador newfortaleza = new Modificador();
                    fortalezas.add(newfortaleza);
                }
            } while (!(input == fortalezas.size()+2) && fortalezas.isEmpty());

    }

    public void ValidarDebilidades(Personaje personaje, Combate desafioActual){
        List<Modificador> debilidades = personaje.getDebilidades();

        int input;
        do {
            if (debilidades.isEmpty()) {
                System.out.println("No hay debilidades disponibles, cree nuevas");}
            System.out.println("¿Qué debilidades añadiras?");
            for (int i = 0; i < debilidades.size(); i++) {
                System.out.println((i + 1) + ". " + debilidades.get(i).getNombre());
            }
            System.out.println(debilidades.size() + 1 + ". Crear Debilidad Nueva");
            System.out.println(debilidades.size() + 2 + ". Listo!");
            input = leerInt();
            if (input > 0 && input <= debilidades.size()) {
                Modificador newdebiliad = debilidades.get(input -1);
                if (desafioActual.getModificadores().contains(newdebiliad)){
                    System.out.println("Debilidad ya en el desafio, escoja otra");
                } else {
                    desafioActual.getModificadores().add(newdebiliad);
                }

            } else if (input == debilidades.size() + 1) {
                Modificador newdebilidad = new Modificador();
                debilidades.add(newdebilidad);
            }
        } while (!(input == debilidades.size()+2) && debilidades.isEmpty());

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

    protected void bloquarse(Jugador jugador){
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
                añadirHabilidadEspecial(nuevoPersonaje);
                partida.getListaPersonajes().add(nuevoPersonaje);
            } else if (ans == 2) {
                Personaje nuevoPersonaje = new Licantropo();
                añadirHabilidadEspecial(nuevoPersonaje);
                partida.getListaPersonajes().add(nuevoPersonaje);
            } else if (ans == 3) {
                Personaje nuevoPersonaje = new Cazador();
                añadirHabilidadEspecial(nuevoPersonaje);
                partida.getListaPersonajes().add(nuevoPersonaje);
            }

        } while (ans<=0 || ans >3);
    }

    public void removerPersonaje(Partida p) {
        List<Personaje> listaPersonajes = p.getListaPersonajes();
        System.out.println("Personaje a remover:");

        int ans;
        if (!listaPersonajes.isEmpty()){
            do {
                for (int i = 0; i < listaPersonajes.size(); i++){
                    System.out.println(i+1 + ". " + listaPersonajes.get(i).getNombre());
                }
                System.out.println(listaPersonajes.size()+1 +". Salir");
                ans = leerInt();
                if (ans >= 1 && ans<=listaPersonajes.size()){
                    listaPersonajes.remove(ans -1);
                }
            } while (ans!=listaPersonajes.size()+1);
        } else {
            System.out.println("Aun no hay personajes");
        }

    }

    public void editarPersonaje(Partida p) {
        List<Personaje> listaPersonajes = p.getListaPersonajes();
        System.out.println("Personaje a editar:");

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
        boolean aux = false;
        int opcion = 0;
        while(opcion != 11) {
            System.out.println("Eliga la opción");
            System.out.println("1.-Editar nombre");
            System.out.println("2.-Editar habilidad especial");
            System.out.println("3.-Editar armas");
            System.out.println("4.-Editar armadura");
            System.out.println("5.-Editar esbirros");
            System.out.println("6.-Editar oro");
            System.out.println("7.-Editar salud");
            System.out.println("8.-Editar poder");
            System.out.println("9.-Editar fortalezas");
            System.out.println("10.-Editar debilidades");
            System.out.println("11.-Salir");
            opcion = this.leerInt();
            if (opcion == 1) {
                personaje.editNombre();
                edited = true;
            } else if (opcion == 2) {
                edited = gestionHabilidadEspecial(personaje);
            } else if (opcion == 3) {
                edited = gestionArmas(personaje);
            } else if (opcion == 4) {
                edited = gestionArmadura(personaje);
            } else if (opcion == 5) {
                edited = gestionEsbirros(personaje);
            } else if (opcion == 6) {
                personaje.editOro();
                edited = true;
            } else if (opcion == 7) {
                personaje.editSalud();
                edited = true;
            } else if (opcion == 8) {
                personaje.editPoder();
                edited = true;
            } else if (opcion == 9) {
                edited = ModificarFortalezas(personaje);
            } else if (opcion == 10) {
                edited = ModificarDebilidades(personaje);
            }
        }
        if (edited){
            aux = edited;
        }
        if (aux){
            personaje.setVersion(personaje.getVersion()+1);
        }

    }

    private boolean gestionEsbirros(Personaje personaje) {
        int ans;
        boolean aux = false;
        boolean changed = false;
        do {
            System.out.println("1. Añadir esbirro");
            System.out.println("2. Eliminar esbirro");
            System.out.println("3. Editar esbirro");
            System.out.println("4. Salir");
            ans = leerInt();
            if(ans == 1){
                changed = crearEsbirro(personaje);
            } else if (ans == 2) {
                changed = removerEsbirro(personaje);
            } else if (ans == 3) {
                changed = ModificarEsbirros(personaje);
            }

            if (changed){
                aux = changed;
            }

        } while (ans!=4);

        return aux;
    }



    public boolean crearEsbirro(Personaje personaje){
        int ans;
        do {
            System.out.println("1. Crear ghoul");
            System.out.println("2. Crear demonio");
            System.out.println("3. Crear humano");
            System.out.println("Recuerde que los vampiros no pueden tener esbirros humanos");
            ans = leerInt();

            if(ans == 1){
                Esbirro esbirro = new Ghoul();
                personaje.getEsbirros().add(esbirro);
                return true;
            } else if (ans == 2) {
                Esbirro esbirro = new Demonio();
                personaje.getEsbirros().add(esbirro);
                return true;
            } else if (ans == 3) {
                if (personaje instanceof Vampiro) {
                    System.out.println("¡Un vampiro no puede crear un esbirro humano!");
                    ans=-1;
                } else {
                    Esbirro esbirro = new Humano();
                    personaje.getEsbirros().add(esbirro);
                    return true;
                }
            }

        } while (ans<=0 || ans >3);

        return false;
    }

    private boolean ModificarDebilidades(Personaje personaje) {
        int ans;
        boolean aux = false;
        boolean changed = false;
        do {
            System.out.println("1. Añadir Debilidad");
            System.out.println("2. Remover Debilidad");
            System.out.println("3. Editar Debilidad");
            System.out.println("4. Salir");
            ans = leerInt();
            if(ans == 1){
                changed = añadirDebilidad(personaje);
            } else if (ans == 2) {
                changed = removerDebilidad(personaje);
            } else if (ans == 3){
                changed = editarDebilidad(personaje);
            }

            if (changed){
                aux = changed;
            }

        } while (ans!=4);

        return aux;
    }

    private boolean ModificarFortalezas(Personaje personaje) {
        int ans;
        boolean aux = false;
        boolean changed = false;
        do {
            System.out.println("1. Añadir Fortaleza");
            System.out.println("2. Remover Fortaleza");
            System.out.println("3. Editar Fortaleza");
            System.out.println("4. Salir");
            ans = leerInt();
            if(ans == 1){
                changed = añadirFortaleza(personaje);
            } else if (ans == 2) {
                changed = removerFortaleza(personaje);
            } else if (ans == 3){
                changed = editarFortaleza(personaje);
            }

            if (changed){
                aux = changed;
            }

        } while (ans!=4);

        return aux;
    }

    private boolean añadirFortaleza(Personaje personaje) {
        Modificador fortaleza = new Modificador();
        personaje.getFortalezas().add(fortaleza);
        return true;
    }

    private boolean ModificarEsbirros(Personaje personaje) {
        List<Esbirro> esbirros = personaje.getEsbirros();
        if (esbirros.isEmpty()) {
            System.out.println("No hay esbirros por editar");
            return false;
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
                    Esbirro esbirro = esbirros.get(input - 1);
                    if (esbirro instanceof Demonio demonio) {
                        System.out.println("El esbirro seleccionado es un demonio.");
                        demonio.ModificarEsbirro();
                    } else if (esbirro instanceof Humano humano) {
                        System.out.println("El esbirro seleccionado es un humano.");
                        humano.ModificarEsbirro();
                    } else if (esbirro instanceof Ghoul ghoul) {
                        System.out.println("El esbirro seleccionado es un ghoul.");
                        ghoul.ModificarEsbirro();
                    }
                    return true;
                } else if (input == esbirros.size() + 1) {
                    return false;
                } else {
                    System.out.println("Valor no válido, por favor introduzca uno nuevo");
                }
            } while (true);
        }
    }

    private boolean removerEsbirro(Personaje personaje) {
        List<Esbirro> esbirros = personaje.getEsbirros();
        if (esbirros.isEmpty()) {
            System.out.println("No hay esbirros por eliminar");
            return false;
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
                    return true;
                } else if (input == esbirros.size() + 1) {
                    return false;
                } else {
                    System.out.println("Valor no válido, por favor introduzca uno nuevo");
                }
            } while (true);
        }
    }



    private boolean removerFortaleza(Personaje personaje) {
        List<Modificador> fortalezas = personaje.getFortalezas();
        if (fortalezas.isEmpty()) {
            System.out.println("No hay fortalezas por eliminar");
            return false;
        } else {
            int input;
            do {
                System.out.println("¿Qué fortaleza vas a eliminar?");
                for (int i = 0; i < fortalezas.size(); i++) {
                    System.out.println((i + 1) + ". " + fortalezas.get(i).getNombre());
                }
                System.out.println(fortalezas.size() + 1 + ". Salir");
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

    private boolean editarDebilidad(Personaje personaje) {
        List<Modificador> debilidades = personaje.getDebilidades();
        if (debilidades.isEmpty()) {
            System.out.println("No hay debilidades por editar");
            return false;
        } else {
            int input;
            do {
                System.out.println("¿Qué debilidad vas a editar?");
                for (int i = 0; i < debilidades.size(); i++) {
                    System.out.println((i + 1) + ". " + debilidades.get(i).getNombre());
                }
                System.out.println(debilidades.size() + 1 + ". Salir");
                input = leerInt();
                if (input > 0 && input <= debilidades.size()) {
                    debilidades.get(input - 1).EditarModificadorActual();
                    return true;
                } else if (input == debilidades.size() + 1) {
                    return false;
                } else {
                    System.out.println("Valor no válido, por favor introduzca uno nuevo");
                }
            } while (true);
        }
    }

    private boolean editarFortaleza(Personaje personaje) {
        List<Modificador> fortalezas = personaje.getFortalezas();
        if (fortalezas.isEmpty()) {
            System.out.println("No hay fortalezas por editar");
            return false;
        } else {
            int input;
            do {
                System.out.println("¿Qué fortaleza vas a editar?");
                for (int i = 0; i < fortalezas.size(); i++) {
                    System.out.println((i + 1) + ". " + fortalezas.get(i).getNombre());
                }
                System.out.println(fortalezas.size() + 1 + ". Salir");
                input = leerInt();
                if (input > 0 && input <= fortalezas.size()) {
                    fortalezas.get(input - 1).EditarModificadorActual();
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
            System.out.println(debilidades.size() + 1 + ". Salir");
            int input;
            do {
                System.out.println("¿Qué debilidad vas a eliminar?");
                for (int i = 0; i < debilidades.size(); i++) {
                    System.out.println((i + 1) + ". " + debilidades.get(i).getNombre());
                }
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
        boolean aux = false;
        boolean changed = false;
        do {
            System.out.println("1. Añadir Habilidad Especial");
            System.out.println("2. Editar Habilidad Especial");
            System.out.println("3. Salir");
            ans = leerInt();
            if(ans == 1){
                changed = añadirHabilidadEspecial(personaje);
            } else if (ans == 2) {
                if (personaje.getHabilidadEspecial() == null){
                    changed = false;
                    System.out.println("Personaje sin habilidad Especial");
                } else {
                    changed = true;
                    personaje.getHabilidadEspecial().EditarHabilidadEspecial();
                }
            }
            if (changed){
                aux = changed;
            }

        } while (ans!=3);

        return aux;
    }


    private boolean añadirHabilidadEspecial(Personaje personaje) {
        HabilidadEspecial habilidadEspecial = new HabilidadEspecial();
        personaje.setHabilidadEspecial(habilidadEspecial);
        return true;
    }


    private boolean gestionArmas(Personaje personaje) {
        int ans;
        boolean aux = false;
        boolean changed = false;
        do {
            System.out.println("1. Añadir Arma");
            System.out.println("2. Remover Arma");
            System.out.println("3. Editar Arma");
            System.out.println("4. Salir");
            ans = leerInt();
            if(ans == 1){
                changed = añadirArma(personaje);
            } else if (ans == 2) {
                changed = removerArma(personaje);
            } else if (ans == 3) {
                changed = editarArma(personaje);
            }

            if (changed){
                aux = changed;
            }

        } while (ans!=4);

        return aux;
    }

    private boolean gestionArmadura(Personaje personaje) {
        int ans;
        boolean aux = false;
        boolean changed = false;
        do {
            System.out.println("1. Añadir Armadura");
            System.out.println("2. Remover Armadura");
            System.out.println("3. Editar Armadura");
            System.out.println("4. Salir");
            ans = leerInt();
            if(ans == 1){
                changed = añadirArmadura(personaje);
            } else if (ans == 2) {
                changed = removerArmadura(personaje);
            } else if (ans == 3) {
                changed = editarArmadura(personaje);
            }

            if (changed){
                aux = changed;
            }

        } while (ans!=4);

        return aux;
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
            System.out.println(listaArmas.size() + 1 + ". Salir");
            int input;
            do {
                input = leerInt();
                if (input>0 && input <=listaArmas.size()){
                    listaArmas.remove(input-1);
                    System.out.println("Listo!");
                    return true;
                } else if (input == listaArmas.size() + 1) {
                    return false;
                } else {
                    System.out.println("Valor no valido, introduzca uno nuevo");
                }
            } while(true);
        }
    }

    private boolean editarArma(Personaje personaje) {
        List<Arma> listaArmas = personaje.getArmas();
        if (listaArmas.isEmpty()){
            System.out.println("No hay armas por editar");
            return false;
        } else{
            System.out.println("¿Que arma vas a editar?");
            for (int i = 0; i < listaArmas.size(); i++) {
                System.out.println((i + 1) + ". " + listaArmas.get(i).getNombre());
            }
            System.out.println(listaArmas.size() + 1 + ". Salir");
            int input;
            do {
                input = leerInt();
                if (input>0 && input <=listaArmas.size()){
                    listaArmas.get(input-1).EditarArma();
                    return true;
                } else if (input == listaArmas.size() + 1) {
                    return false;
                } else {
                    System.out.println("Valor no valido, introduzca uno nuevo");
                }
            } while(true);
        }
    }

    private boolean editarArmadura(Personaje personaje) {
        List<Armadura> listaArmaduras = personaje.getArmaduras();
        if (listaArmaduras.isEmpty()) {
            System.out.println("No hay armaduras por editar");
            return false;
        } else {
            System.out.println("¿Qué armadura vas a editar?");
            for (int i = 0; i < listaArmaduras.size(); i++) {
                System.out.println((i + 1) + ". " + listaArmaduras.get(i).getNombre());
            }
            System.out.println(listaArmaduras.size() + 1 + ". Salir");
            int input;
            do {
                input = leerInt();
                if (input > 0 && input <= listaArmaduras.size()) {
                    listaArmaduras.get(input - 1).EditarArmadural();
                    return true;
                } else if (input == listaArmaduras.size() + 1) {
                    return false;
                } else {
                    System.out.println("Valor no válido, introduzca uno nuevo");
                }
            } while (true);
        }
    }

    private boolean removerArmadura(Personaje personaje) {
        List<Armadura> listaArmaduras = personaje.getArmaduras();
        if (listaArmaduras.isEmpty()) {
            System.out.println("No hay armaduras por eliminar");
            return false;
        } else {
            System.out.println("¿Qué armadura vas a eliminar?");
            for (int i = 0; i < listaArmaduras.size(); i++) {
                System.out.println((i + 1) + ". " + listaArmaduras.get(i).getNombre());
            }
            System.out.println(listaArmaduras.size() + 1 + ". Salir");
            int input;
            do {
                input = leerInt();
                if (input > 0 && input <= listaArmaduras.size()) {
                    listaArmaduras.remove(input - 1);
                    System.out.println("¡Armadura eliminada exitosamente!");
                    return true;
                } else if (input == listaArmaduras.size() + 1) {
                    return false;
                } else {
                    System.out.println("Valor no válido, introduzca uno nuevo");
                }
            } while (true);
        }
    }

    private boolean añadirArma(Personaje personaje) {
        Arma arma = new Arma();
        personaje.getArmas().add(arma);
        return true;
    }

    private boolean añadirArmadura(Personaje personaje) {
        Armadura armadura = new Armadura();
        personaje.getArmaduras().add(armadura);
        return true;
    }


    private int leerInt(){
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

    private String leerString(){
        return scanner.nextLine();
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.scanner = ScannerSingleton.getInstance();
    }
}
