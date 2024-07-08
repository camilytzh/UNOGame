package proyecto;

import java.util.Scanner;

public class CartaComodin extends Carta {
    private String tipo;

    public CartaComodin(char color, String tipo) {
        super(color);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void cambioColor() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        int intento = 0;
        while (!input.equals("A") && !input.equals("Z") && !input.equals("V") && !input.equals("R")) {
            if (intento > 0) System.out.println("Caracter no válido (!)");    
            System.out.println("Elige un color para cambiar la linea de juego (A/R/V/Z): ");
            input = scanner.nextLine();
            intento++;
        }
        this.setColor(input.charAt(0));
    }

    @Override
    public String toString() {
        return tipo + color;
    }
}