package Ouistiti;

import java.util.ArrayList;
import java.util.List;

public class Tas {
    protected List<Carte> aCartes = new ArrayList<>();

    public Tas(List<Carte> aCartes) {
        this.aCartes = aCartes;
    }

    protected void ajouter(Carte oCarte){

    }
    protected Carte retirerTop(){
        Carte cartePige = null;
        cartePige = new Carte(cartePige.getiValeur(), cartePige.isbFaceVisible());
        return cartePige;
    }


    // Getters / Setters
    public List<Carte> getaCartes() {
        return aCartes;
    }

    public void setaCartes(List<Carte> aCartes) {
        this.aCartes = aCartes;
    }

    @Override
    public String toString(){
        return "aCartes"+"(" + aCartes.size() + ")" + aCartes;
    }
}
