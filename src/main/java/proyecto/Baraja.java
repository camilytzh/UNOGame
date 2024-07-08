package proyecto;
import java.util.ArrayList;
import java.util.Random;

public class Baraja {
    private ArrayList<Carta> cartas;
    public Baraja(){
        cartas = new ArrayList<>();
        char[] colores={'A','R','V','Z'};
        for (char c:colores){
            for (int i=0;i<=9;i++){
                cartas.add(new CartaNumero(c, i));
            }
            cartas.add(new CartaComodin(c, "^"));
            cartas.add(new CartaComodin(c, "^"));
            cartas.add(new CartaComodin(c, "&"));
            cartas.add(new CartaComodin(c, "&"));
            cartas.add(new CartaComodin(c, "+2"));
            cartas.add(new CartaComodin(c, "+2"));
            cartas.add(new CartaComodin(c, "+4"));
            cartas.add(new CartaComodin(c, "+4"));
        }
        cartas.add(new CartaComodin('N', "+2"));
        cartas.add(new CartaComodin('N', "+2"));
        cartas.add(new CartaComodin('N', "+4"));
        cartas.add(new CartaComodin('N', "+4"));
        cartas.add(new CartaComodin('N', "%"));
        cartas.add(new CartaComodin('N', "%"));
        this.barajar();
    }
    public ArrayList<Carta> getCartas() {
        return cartas;
    }


    public void barajar() {
        Random random = new Random();
        for (int i = cartas.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Carta temp = cartas.get(i);
            cartas.set(i, cartas.get(j));
            cartas.set(j, temp);
        }
    }

    public Carta tomarCarta(){
        Carta carta=cartas.remove(0);
        return carta;
    }

    public void ponerCartaEnJuego(Carta carta) {
        cartas.add(0, carta); 
    }
}
