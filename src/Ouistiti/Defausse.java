package Ouistiti;

import java.util.List;

public class Defausse extends Tas{

    public Defausse(List<Carte> aCartes){
        super(aCartes);
        this.aCartes = aCartes;
    }

    public Carte regarderTop(){
        Carte cartePige = null;
        cartePige = new Carte(cartePige.getiValeur(), cartePige.isbFaceVisible(),false);
        return cartePige;
    }
}
