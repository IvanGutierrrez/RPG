import java.io.Serializable;

public class  Ronda implements Serializable {

    private int numero;
    private Personaje Jugador1;

    private Personaje Jugador2;

    private double Salud1;

    private  double Salud2;

    private double SaludEsbirros1;

    private double SaludEsbirros2;

    private double PuntosEsp1;

    private double PuntosEsp2;

    private double PotencialAtq1;
    private double PotencialAtq2;
    private double PotencialDfs1;
    private double PotencialDfs2;
    private double ValorAtq1;
    private double ValorAtq2;
    private double ValorDfs1;
    private double ValorDfs2;


    public Ronda(int i, Personaje personajeRetador, Personaje personajeRetado, double vidaEsbirros1, double vidaEsbirros2, double potencialAtq1, double potencialAtq2, double potencialDef1, double potencialDef2, double ataque1, double ataque2, double defensa1, double defensa2) {
        this.numero=i;
        this.Jugador1 = personajeRetador;
        this.Jugador2 = personajeRetado;
        this.Salud1 = personajeRetador.getSalud();
        this.Salud2 = personajeRetado.getSalud();
        this.SaludEsbirros1 = vidaEsbirros1;
        this.SaludEsbirros2 = vidaEsbirros2;
        if(personajeRetador instanceof Vampiro){
            this.PuntosEsp1= ((Vampiro) personajeRetador).getSangre();
        }else if(personajeRetador instanceof Licantropo){
            this.PuntosEsp1= ((Licantropo) personajeRetador).getRabia();
        } else if (personajeRetador instanceof Cazador) {
            this.PuntosEsp1=((Cazador) personajeRetador).getVoluntad();
        }
        if(personajeRetado instanceof Vampiro){
            this.PuntosEsp2= ((Vampiro) personajeRetado).getSangre();
        }else if(personajeRetado instanceof Licantropo){
            this.PuntosEsp2= ((Licantropo) personajeRetado).getRabia();
        } else if (personajeRetado instanceof Cazador) {
            this.PuntosEsp2=((Cazador) personajeRetado).getVoluntad();
        }
        this.PotencialAtq1 = potencialAtq1;
        this.PotencialAtq2 = potencialAtq2;
        this.PotencialDfs1 = potencialDef1;
        this.PotencialDfs2 = potencialDef2;
        this.ValorAtq1 = ataque1;
        this.ValorAtq2 = ataque2;
        this.ValorDfs1 = defensa1;
        this.ValorDfs2 = defensa2;
    }
    public void mostrarRonda(){
        System.out.println(" ");
        System.out.println("Ronda "+this.numero);
        System.out.printf("%-20s%-20s%n", this.Jugador1.getNombre(), this.Jugador2.getNombre());
        System.out.printf("Salud: %-20sSalud: %-20s%n", this.Salud1, this.Salud2);
        System.out.printf("SaludEsbirros: %-20sSaludEsbirros: %-20s%n", this.SaludEsbirros1, this.SaludEsbirros2);
        System.out.printf("PuntosEspeciales: %-20sPuntosEspeciales: %-20s%n", this.PuntosEsp1, this.PuntosEsp2);
        System.out.printf("PotencialAtaque: %-20sPotencialAtaque: %-20s%n", this.PotencialAtq1, this.PotencialAtq2);
        System.out.printf("PotencialDefensa: %-20sPotencialDefensa: %-20s%n", this.PotencialDfs1, this.PotencialDfs2);
        System.out.printf("ValorTotalAtaque: %-20sValorTotalAtaque: %-20s%n", this.ValorAtq1, this.ValorAtq2);
        System.out.printf("ValorTotalDefensa: %-20sValorTotalDefensa: %-20s%n", this.ValorDfs1, this.ValorDfs2);
        System.out.println("----------------------------------------------------------------------");
        if(this.ValorAtq1>=this.ValorDfs2){
            System.out.println(this.Jugador1.getNombre()+" ha tenido exito en su ataque");
        }else{
            System.out.println(this.Jugador2.getNombre()+" ha tenido exito en su defensa");
        }
        if(this.ValorAtq2>=this.ValorDfs1){
            System.out.println(this.Jugador2.getNombre()+" ha tenido exito en su ataque");
        }else{
            System.out.println(this.Jugador1.getNombre()+" ha tenido exito en su defensa");
        }

    }
}
