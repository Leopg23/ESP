package Ouistiti;

public class Carte {
    int iValeur;
    boolean bFaceVisible;

    public Carte (int iValeur, boolean bFaceVisible){
        this.iValeur = iValeur;
        this.bFaceVisible = bFaceVisible;
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


    @Override
    public String toString(){
        return "Carte{" + getiValeur() + ", " + isbFaceVisible() + "}";
    }
}
