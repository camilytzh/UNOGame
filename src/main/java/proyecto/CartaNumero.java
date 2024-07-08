package proyecto;

public class CartaNumero extends Carta{
    private int numero;
    public CartaNumero (char color, int numero){
        super(color);
        this.numero=numero;
    }
    public int getNumero(){
        return numero;
    }
    @Override
    public String toString(){
        return ""+numero+color;
    }
}
