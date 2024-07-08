package proyecto;

import java.util.ArrayList;

public class Mano {
    private ArrayList<Carta> cartas;

    public Mano() {
        cartas = new ArrayList<>();
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    public Carta jugarCarta(int indice) {
        Carta carta = cartas.remove(indice);
        if (cartas.size() == 1) {
            System.out.println("¡UNO!");
        }
        return carta;
    }

    public Carta obtenerCarta(int indice) {
        return cartas.get(indice);
    }

    public boolean tieneCartas() {
        return !cartas.isEmpty();
    }

    public int contarCartas() {
        return cartas.size();
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    @Override
    public String toString() {
        return cartas.toString();
    }
}
