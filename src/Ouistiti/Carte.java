package Ouistiti;

public class Carte {
    int iValeur;
    boolean bFaceVisible;
    boolean bEstNull;
    public Carte(int iValeur, boolean bFaceVisible, boolean bEstNull) {
        this.iValeur = iValeur;
        this.bFaceVisible = bFaceVisible;
        this.bEstNull = bEstNull;
    }

    public record Zone(int posx, int posy, int width, int height) {
        public Zone {
            posx = posx - (width / 2);
            posy = posy - (height / 2);
        }
    }
    // Getters / Setters

    public boolean isbFaceVisible() {
        return bFaceVisible;
    }

    public void setbFaceVisible(boolean bFaceVisible) {
        this.bFaceVisible = bFaceVisible;
    }

    public int getiValeur() {
        return iValeur;
    }

    public void setiValeur(int iValeur) {
        this.iValeur = iValeur;
    }

    public boolean isbEstNull() {
        return bEstNull;
    }

    public void setbEstNull(boolean bEstNull) {
        this.bEstNull = bEstNull;
    }

    @Override
    public String toString() {
        return "Carte{" + getiValeur() + ", " + isbFaceVisible() + ", " + isbEstNull() + "}";
    }
}
