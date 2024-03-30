
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

    private String[] Rondas;

    private LocalDateTime Fecha;

    private Jugador Ganador;

    private Jugador JugadorConEsbirrosSinDerrotar;

    private Modificador[] Modificadores;

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
        this.Modificadores = null;
        this.Rondas = null;
        this.Valido = false;
    }

    public void calcularResutado() {
        // TODO implement here
    }

    public void mostrarResultado() {
        // TODO implement here
    }

    public void cancelarCombate() {
        // TODO implement here
    }

    public void combatir() {
        System.out.println("Combate");
        System.out.printf("%-20s%-20s%n", this.getJugadorRetador(), this.getJugadorRetado());
        System.out.printf("%-20s%-20s%n", this.PersonajeRetador.getNombre(), this.PersonajeRetado.getNombre());
        System.out.println("DineroApostado: "+ this.OroApostado);
        System.out.println("Fecha: "+ this.Fecha);
        double Modificadores1=CalcularModificadores(this.Modificadores,this.PersonajeRetador);
        double Modificadores2=CalcularModificadores(this.Modificadores,this.PersonajeRetado);
        double VidaEsbirros1=this.PersonajeRetador.darVidaEsbirros();
        double VidaEsbirros2=this.PersonajeRetado.darVidaEsbirros();
        while(this.PersonajeRetador.Salud==0 || this.PersonajeRetado.Salud==0 ){
            double PotencialAtq1=this.PersonajeRetador.calcularPotencialAtaque()+Modificadores1;
            double PotencialAtq2=this.PersonajeRetado.calcularPotencialAtaque()+Modificadores2;
            double PotencialDef1=this.PersonajeRetador.calcularPotencialDefensa()+Modificadores1;
            double PotencialDef2=this.PersonajeRetado.calcularPotencialDefensa()+Modificadores2;
            double ataque1=calcularSuerte(PotencialAtq1);
            double ataque2=calcularSuerte(PotencialAtq2);
            double defensa1=calcularSuerte(PotencialDef1);
            double defensa2=calcularSuerte(PotencialDef2);
            CalcularRonda(ataque1,defensa2,VidaEsbirros2,this.getPersonajeRetador(),this.getPersonajeRetado());
            CalcularRonda(ataque2,defensa1,VidaEsbirros1,this.getPersonajeRetado(),this.getPersonajeRetador());
        }
        if(this.PersonajeRetador.Salud==0 && this.PersonajeRetado.Salud==0){
            System.out.println("Empate");
        }else if(this.PersonajeRetador.Salud==0){
            this.Ganador=this.JugadorRetado;
            if (VidaEsbirros2>0){
                this.JugadorConEsbirrosSinDerrotar=this.JugadorRetado;
            }
        }else if(this.PersonajeRetado.Salud==0){
            this.Ganador=this.JugadorRetador;
            if (VidaEsbirros1>0){
                this.JugadorConEsbirrosSinDerrotar=this.JugadorRetador;
            }
        }

    }

    private double CalcularModificadores(Modificador[] modificadores, Personaje jugadorRetado) {
        double suma=0;
        for (int i = 0; i < jugadorRetado.Debilidades.size(); i++) {
            for (int j = 0; j < modificadores.length; j++) {
                if (jugadorRetado.Debilidades.get(i) == modificadores[j]) {
                    suma=suma+jugadorRetado.Debilidades.get(i).getValor();
                }
            }
        }
        for (int i = 0; i < jugadorRetado.Fortalezas.size(); i++) {
            for (int j = 0; j < modificadores.length; j++) {
                if (jugadorRetado.Fortalezas.get(i) == modificadores[j]) {
                    suma=suma+jugadorRetado.Fortalezas.get(i).getValor();
                }
            }
        }
        System.out.println("modificadores presente jugador: "+suma);
        return suma;
    }

    private void CalcularRonda(double ataque1, double defensa2, double VidaEsbirros2, Personaje personajeRetador, Personaje personajeRetado) {
        if(ataque1>=defensa2){
            if(VidaEsbirros2>0){
                VidaEsbirros2=VidaEsbirros2-1;
            }else{
                personajeRetado.Salud=personajeRetado.Salud-1;
                if(personajeRetador instanceof Vampiro){
                    ((Vampiro) personajeRetador).SubirSangre();
                }
                if(personajeRetado instanceof Licantropo){
                    ((Licantropo) personajeRetado).SubirRabia();

                } else if (personajeRetado instanceof Cazador) {
                    ((Cazador) personajeRetado).BajarVoluntad();
                }

            }
        }
    }


    private double calcularSuerte(double potencialAtq1) {
        double suma=0;
        Random random = new Random();
        while(potencialAtq1!=0) {
            int num = random.nextInt(6) + 1;
            if (num >= 5) {
                suma = suma + 1;
            }
            potencialAtq1=potencialAtq1-1;
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

    public String[] getRondas() {
        return Rondas;
    }

    public void setRondas(String[] rondas) {
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

    public Modificador[] getModificadores() {
        return Modificadores;
    }

    public void setModificadores(Modificador[] modificadores) {
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
        return "Combate{" +
                "JugadorRetador=" + JugadorRetador +
                ", JugadorRetado=" + JugadorRetado +
                ", OroApostado=" + OroApostado +
                ", Fecha='" + Fecha + '\'' +
                '}';
    }
}