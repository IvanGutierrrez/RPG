
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 
 */
public class Combate implements Serializable {

    private Jugador JugadorRetador;

    private Jugador JugadorRetado;

    private double OroApostado;

    private ArrayList<Ronda> Rondas;

    private LocalDateTime Fecha;

    private Jugador Ganador;

    private Jugador JugadorConEsbirrosSinDerrotar;

    private ArrayList<Modificador> Modificadores;

    private boolean Valido;

    private Personaje PersonajeRetador;

    private Personaje PersonajeRetado;

    public Combate (Jugador retador, Jugador retado,int apuesta, Personaje personajeRetador, Personaje personajeRetado, LocalDateTime fecha) {
        this.JugadorRetador = retador;
        this.JugadorRetado = retado;
        this.OroApostado = apuesta;
        this.PersonajeRetador = personajeRetador;
        this.PersonajeRetado = personajeRetado;
        this.Fecha = fecha;
        this.Ganador = null;
        this.JugadorConEsbirrosSinDerrotar = null;
        this.Modificadores = new ArrayList<>();
        this.Rondas = new ArrayList<>();
        this.Valido = false;
    }

    public void ResolverCombate(double vidaJugador1, double vidaJugador2) {
        if(this.Ganador==this.JugadorRetado){
            double oro=this.PersonajeRetado.getOro()+this.OroApostado;
            this.PersonajeRetado.setOro(oro);
            oro=this.PersonajeRetador.getOro()-this.OroApostado;
            this.PersonajeRetador.setOro(oro);
            this.JugadorRetador.setUltimaDerrota(this.Fecha);
        }else if(this.Ganador==this.JugadorRetador){
            double oro=this.PersonajeRetador.getOro()+this.OroApostado;
            this.PersonajeRetador.setOro(oro);
            oro=this.PersonajeRetado.getOro()-this.OroApostado;
            this.PersonajeRetado.setOro(oro);
            this.JugadorRetado.setUltimaDerrota(this.Fecha);
        }
        this.PersonajeRetador.VolverAloNormal(vidaJugador1);
        this.PersonajeRetado.VolverAloNormal(vidaJugador2);
        this.JugadorRetador.anadirCombate(this);
        this.JugadorRetado.anadirCombate(this);

    }

    public void mostrarResultado() {
        System.out.println("Combate");
        System.out.printf("%-20s%-20s%n", this.getJugadorRetador().getNombre(), this.getJugadorRetado().getNombre());
        System.out.printf("%-20s%-20s%n", this.PersonajeRetador.getNombre(), this.PersonajeRetado.getNombre());
        System.out.println("DineroApostado: "+ this.OroApostado);
        System.out.println("Fecha: "+ this.Fecha);
        System.out.println("Las modificaciones presentes en el combate son: ");
        for (int i = 0; i < Modificadores.size(); i++) {
            System.out.print(Modificadores.get(i).getNombre()+" ");
        }
        waitForEnter();
        System.out.println("A continuacion mostramos las rondas del combate:");
        waitForEnter();
        for (int i = 0; i < Rondas.size(); i++) {
            Ronda ronda = Rondas.get(i);
            ronda.mostrarRonda();
            waitForEnter();
        }
        System.out.println(" ");
        if(this.Ganador!=null){
            System.out.println("El ganador del combate es: "+ this.Ganador.getNombre());
        }else{
            System.out.println("El combate ha quedado en empate");
        }
        waitForEnter();
    }
    private void waitForEnter() {
        System.out.println(" ");
        System.out.println("Presiona Enter para continuar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public void cancelarCombate() {
        double penalizacion=this.OroApostado*0.1;
        penalizacion=this.PersonajeRetado.getOro()-penalizacion;
        this.PersonajeRetado.setOro(penalizacion);
        this.JugadorRetador.setDesafio(null);
        this.JugadorRetado.setDesafio(null);

    }

    public void combatir() {
        double vidaJugador1=this.PersonajeRetador.getSalud();
        double vidaJugador2=this.PersonajeRetado.getSalud();
        double Modificadores1=CalcularModificadores(this.Modificadores,this.PersonajeRetador);
        double Modificadores2=CalcularModificadores(this.Modificadores,this.PersonajeRetado);
        double VidaEsbirros1=this.PersonajeRetador.darVidaEsbirros();
        double VidaEsbirros2=this.PersonajeRetado.darVidaEsbirros();
        int i=1;
        while(this.PersonajeRetador.getSalud()!=0 && this.PersonajeRetado.getSalud()!=0 ){
            double PotencialAtq1=this.PersonajeRetador.calcularPotencialAtaque()+Modificadores1;
            double PotencialAtq2=this.PersonajeRetado.calcularPotencialAtaque()+Modificadores2;
            double PotencialDef1=this.PersonajeRetador.calcularPotencialDefensa()+Modificadores1;
            double PotencialDef2=this.PersonajeRetado.calcularPotencialDefensa()+Modificadores2;
            double ataque1=calcularSuerte(PotencialAtq1);
            double ataque2=calcularSuerte(PotencialAtq2);
            double defensa1=calcularSuerte(PotencialDef1);
            double defensa2=calcularSuerte(PotencialDef2);
            VidaEsbirros2=CalcularRonda(ataque1,defensa2,VidaEsbirros2,this.getPersonajeRetador(),this.getPersonajeRetado());
            VidaEsbirros1=CalcularRonda(ataque2,defensa1,VidaEsbirros1,this.getPersonajeRetado(),this.getPersonajeRetador());
            Ronda ronda= new Ronda(i,this.PersonajeRetador,this.PersonajeRetado,VidaEsbirros1,VidaEsbirros2,PotencialAtq1,PotencialAtq2,PotencialDef1,PotencialDef2,ataque1,ataque2,defensa1,defensa2);
            this.Rondas.add(ronda);
            i=i+1;
        }
        if(this.PersonajeRetador.getSalud()==0 && this.PersonajeRetado.getSalud()==0){
            this.Ganador=null;
        }else if(this.PersonajeRetador.getSalud()==0){
            this.Ganador=this.JugadorRetado;
            this.getJugadorRetado().setTotalOroGanado((this.getJugadorRetado().getTotalOroGanado()+this.OroApostado));
            this.getJugadorRetador().setTotalOroGanado((this.getJugadorRetador().getTotalOroGanado()-this.OroApostado));
            if (VidaEsbirros2>0){
                this.JugadorConEsbirrosSinDerrotar=this.JugadorRetado;
            }
        }else if(this.PersonajeRetado.getSalud()==0){
            this.Ganador=this.JugadorRetador;
            this.getJugadorRetado().setTotalOroGanado((this.getJugadorRetado().getTotalOroGanado()-this.OroApostado));
            this.getJugadorRetador().setTotalOroGanado((this.getJugadorRetador().getTotalOroGanado()+this.OroApostado));
            if (VidaEsbirros1>0){
                this.JugadorConEsbirrosSinDerrotar=this.JugadorRetador;
            }
        }
        ResolverCombate(vidaJugador1,vidaJugador2);
    }

    private double CalcularModificadores(List<Modificador> modificadores, Personaje jugadorRetado) {
        double suma=0;
        for (int i = 0; i < jugadorRetado.Debilidades.size(); i++) {
            for (int j = 0; j < modificadores.size(); j++) {
                if (jugadorRetado.Debilidades.get(i) == modificadores.get(j)) {
                    suma=suma-jugadorRetado.Debilidades.get(i).getValor();
                }
            }
        }
        for (int i = 0; i < jugadorRetado.Fortalezas.size(); i++) {
            for (int j = 0; j < modificadores.size(); j++) {
                if (jugadorRetado.Fortalezas.get(i) == modificadores.get(j)) {
                    suma=suma+jugadorRetado.Fortalezas.get(i).getValor();
                }
            }
        }
        System.out.println("modificadores presente jugador: "+suma);
        return suma;
    }

    private double CalcularRonda( double ataque, double defensa, double VidaEsbirros, Personaje personajeRetador, Personaje personajeRetado) {
        if(ataque>=defensa){
            if(VidaEsbirros>0){
                VidaEsbirros=VidaEsbirros-1;
            }else{
                double salud=personajeRetado.getSalud()-1;
                personajeRetado.setSalud(salud);
            }
            if(personajeRetador instanceof Vampiro){
                ((Vampiro) personajeRetador).SubirSangre();
            }
            if(personajeRetado instanceof Licantropo){
                ((Licantropo) personajeRetado).SubirRabia();

            } else if (personajeRetado instanceof Cazador) {
                ((Cazador) personajeRetado).BajarVoluntad();
            }
        }
        return VidaEsbirros;
    }


    private double calcularSuerte(double potencial) {
        double suma=0;
        Random random = new Random();
        while(potencial!=0) {
            int num = random.nextInt(6) + 1;
            if (num >= 5) {
                suma = suma + 1;
            }
            potencial=potencial-1;
        }
        return suma;
    }

    public Jugador getJugadorRetador() {
        return JugadorRetador;
    }

    public void setJugadorRetador(Jugador jugadorRetador) {
        JugadorRetador = jugadorRetador;
    }

    public Jugador getJugadorRetado() {
        return JugadorRetado;
    }

    public void setJugadorRetado(Jugador jugadorRetado) {
        JugadorRetado = jugadorRetado;
    }

    public double getOroApostado() {
        return OroApostado;
    }

    public void setOroApostado(double oroApostado) {
        OroApostado = oroApostado;
    }

    public ArrayList<Ronda> getRondas() {
        return Rondas;
    }

    public void setRondas(ArrayList<Ronda> rondas) {
        Rondas = rondas;
    }

    public LocalDateTime getFecha() {
        return Fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        Fecha = fecha;
    }

    public Jugador getGanador() {
        return Ganador;
    }

    public void setGanador(Jugador ganador) {
        Ganador = ganador;
    }

    public Jugador getJugadorConEsbirrosSinDerrotar() {
        return JugadorConEsbirrosSinDerrotar;
    }

    public void setJugadorConEsbirrosSinDerrotar(Jugador jugadorConEsbirrosSinDerrotar) {
        JugadorConEsbirrosSinDerrotar = jugadorConEsbirrosSinDerrotar;
    }

    public ArrayList<Modificador> getModificadores() {
        return Modificadores;
    }

    public void setModificadores(ArrayList<Modificador> modificadores) {
        Modificadores = modificadores;
    }

    public boolean isValido() {
        return Valido;
    }

    public void setValido(boolean valido) {
        Valido = valido;
    }

    public Personaje getPersonajeRetador() {
        return PersonajeRetador;
    }

    public void setPersonajeRetador(Personaje personajeRetador) {
        PersonajeRetador = personajeRetador;
    }

    public Personaje getPersonajeRetado() {
        return PersonajeRetado;
    }

    public void setPersonajeRetado(Personaje personajeRetado) {
        PersonajeRetado = personajeRetado;
    }

    @Override
    public String toString() {
        return "Desafio Actual:\n" +
                "Jugador Retador: " + JugadorRetador + "\n" +
                "Jugador Retado: " + JugadorRetado + "\n" +
                "Oro Apostado: " + OroApostado + "\n" +
                "Fecha: '" + Fecha + '\'';
    }
}