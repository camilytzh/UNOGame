package proyecto;

public class Jugador {
    private String nombre;
    private Mano mano;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new Mano();
    }

    public String getNombre() {
        return nombre;
    }

    public Mano getMano() {
        return mano;
    }

    public void tomarCarta(Baraja baraja) {
        mano.agregarCarta(baraja.tomarCarta());
    }

    public void jugarCarta(int indice, Baraja baraja, Carta cartaEnJuego) {
        Carta carta = mano.obtenerCarta(indice);
        mano.jugarCarta(indice);
        baraja.ponerCartaEnJuego(carta);
    }

    public boolean tieneCartas() {
        return mano.tieneCartas();
    }

    public void aplicarEfectoComodin(CartaComodin carta, Baraja baraja, Jugador oponente) {
        String tipo = carta.getTipo();

        if (carta.getColor() == 'N') {
            carta.cambioColor();
        }

        switch (tipo) {
            case "^": 
                System.out.println("Efecto Reverse: El jugador juega de nuevo.");
                break;
            case "&": 
                System.out.println("Efecto Bloqueo: El oponente pierde su turno.");
                break;
            case "+2": 
                System.out.println("Efecto +2: El oponente toma dos cartas de la baraja.");
                oponente.tomarCarta(baraja);
                oponente.tomarCarta(baraja);
                break;
            case "+4": 
                System.out.println("Efecto +4: El oponente toma cuatro cartas de la baraja.");
                oponente.tomarCarta(baraja);
                oponente.tomarCarta(baraja);
                oponente.tomarCarta(baraja);
                oponente.tomarCarta(baraja);
                break;
            case "%": 
                break;
        }
    }
}
