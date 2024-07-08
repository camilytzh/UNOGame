package proyecto;

import java.util.Scanner;

public class Juego {
    private Baraja baraja;
    private Jugador jugador;
    private Jugador maquina;
    private Carta cartaEnJuego;
    private Scanner scanner;
    private boolean turnoJugador = true;

    public Juego() {
        baraja = new Baraja();
        jugador = new Jugador("Jugador01");
        maquina = new Jugador("Maquina");
        scanner = new Scanner(System.in);
        inicializarJuego();
    }

    private void inicializarJuego() {
        for (int i = 0; i < 7; i++) {
            jugador.tomarCarta(baraja);
            maquina.tomarCarta(baraja);
        }
        cartaEnJuego = baraja.tomarCarta();
        while (cartaEnJuego instanceof CartaComodin) {
            cartaEnJuego = baraja.tomarCarta();
        }
        mostrarEstado();
    }

    private void mostrarEstado() {
        System.out.println("-".repeat(50));
        System.out.println("              Ultima carta en juego: " + cartaEnJuego + "             ");
        System.out.println("-".repeat(50));
        System.out.println("Mano del jugador: " + jugador.getMano());
        System.out.println();
        System.out.println("Mano de la máquina: " + maquina.getMano());
        System.out.println("-".repeat(50));
    }

    public void jugar() {
        while (jugador.tieneCartas() && maquina.tieneCartas()) {
            if (turnoJugador) {
                turnoJugador();
            } else {
                turnoMaquina();
            }
        }
        if (!jugador.tieneCartas()) {
            System.out.println("¡El Jugador ha ganado!");
        } else if (!maquina.tieneCartas()) {
            System.out.println("¡La Máquina ha ganado!");
        }
    }

    private void turnoJugador() {
        boolean jugadaValida = false;
        while (!jugadaValida) {
            if (!tieneCartaValida(jugador)) {
                System.out.println("No tienes cartas válidas para jugar. Robando una carta...");
                jugador.tomarCarta(baraja);
                mostrarEstado();
                turnoJugador = false;
                return;
            }

            System.out.println("Turno del jugador. Selecciona una carta (índice): ");
            int indice = scanner.nextInt();
            Carta carta = jugador.getMano().obtenerCarta(indice);
            if (esJugadaValida(carta, cartaEnJuego)) {
                jugador.jugarCarta(indice, baraja, cartaEnJuego);
                ponerCartaEnJuego(carta);
                jugadaValida = true;
                if (carta instanceof CartaComodin) {
                    jugador.aplicarEfectoComodin((CartaComodin) carta, baraja, maquina);
                    if (carta.getColor() == 'N') {
                        ((CartaComodin) carta).cambioColor();
                    }
                    mostrarEstado(); 
                    if (((CartaComodin) carta).getTipo().equals("^") || ((CartaComodin) carta).getTipo().equals("&")) {
                        turnoJugador = true;
                    } else {
                        turnoJugador = false;
                    }
                } else {
                    mostrarEstado(); 
                    turnoJugador = false;
                }
            } else {
                System.out.println("Jugada inválida. Intenta de nuevo.");
            }
        }
    }

    private void turnoMaquina() {
        System.out.println("Turno de la máquina.");
        boolean jugadaValida = false;
        for (int i = 0; i < maquina.getMano().contarCartas(); i++) {
            Carta carta = maquina.getMano().obtenerCarta(i);
            if (esJugadaValida(carta, cartaEnJuego)) {
                maquina.jugarCarta(i, baraja, cartaEnJuego);
                ponerCartaEnJuego(carta); 
                System.out.println("Máquina jugó: " + carta);
                jugadaValida = true;
                if (carta instanceof CartaComodin) {
                    maquina.aplicarEfectoComodin((CartaComodin) carta, baraja, jugador);
                    if (carta.getColor() == 'N') {
                        ((CartaComodin) carta).cambioColor();
                    }
                    mostrarEstado(); 
                    if (((CartaComodin) carta).getTipo().equals("^") || ((CartaComodin) carta).getTipo().equals("&")) {
                        turnoJugador = false;
                    } else {
                        turnoJugador = true;
                    }
                } else {
                    mostrarEstado(); 
                    turnoJugador = true;
                }
                break;
            }
        }
        if (!jugadaValida) {
            System.out.println("Máquina no tiene cartas válidas para jugar. Tomando una carta de la baraja...");
            maquina.tomarCarta(baraja);
            mostrarEstado();
            turnoJugador = true;
        }
    }

    private boolean esJugadaValida(Carta carta, Carta cartaEnJuego) {
        if (carta instanceof CartaNumero && cartaEnJuego instanceof CartaNumero) {
            CartaNumero cartaNumero = (CartaNumero) carta;
            CartaNumero cartaNumeroEnJuego = (CartaNumero) cartaEnJuego;
            return cartaNumero.getColor() == cartaNumeroEnJuego.getColor() ||
                   cartaNumero.getNumero() == cartaNumeroEnJuego.getNumero();
        } else if (carta instanceof CartaComodin && cartaEnJuego instanceof CartaComodin) {
            CartaComodin cartaComodin = (CartaComodin) carta;
            CartaComodin cartaComodinEnJuego = (CartaComodin) cartaEnJuego;
            return cartaComodin.getColor() == cartaComodinEnJuego.getColor() ||
                   cartaComodin.getColor() == 'N' ||
                   cartaComodin.getTipo().equals(cartaComodinEnJuego.getTipo());
        } else if (carta instanceof CartaComodin) {
            CartaComodin cartaComodin = (CartaComodin) carta;
            return cartaComodin.getColor() == cartaEnJuego.getColor() || cartaComodin.getColor() == 'N';
        } else if (cartaEnJuego instanceof CartaComodin) {
            return carta.getColor() == cartaEnJuego.getColor();
        }
        return false;
    }

    private boolean tieneCartaValida(Jugador jugador) {
        for (int i = 0; i < jugador.getMano().contarCartas(); i++) {
            if (esJugadaValida(jugador.getMano().obtenerCarta(i), cartaEnJuego)) {
                return true;
            }
        }
        return false;
    }

    private void ponerCartaEnJuego(Carta carta) {
        cartaEnJuego = carta;
    }
}