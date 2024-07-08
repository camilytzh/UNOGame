package proyecto;

public class Carta {
    protected char color;

    public Carta(char color){
        this.color=color;
    }
    public char getColor(){
        return color;
    }
    public void setColor(char newColor){
        color=newColor;
    }
}
