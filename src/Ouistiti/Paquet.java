package Ouistiti;

import java.util.List;

public class Paquet extends Tas{

    public Paquet(List<Carte> aCartes){
        super(aCartes);
        this.aCartes = aCartes;
    }

    public void melanger(){

    }

    public Carte piger(){
        Carte cartePige = null;
        cartePige = new Carte(cartePige.getiValeur(), cartePige.isbFaceVisible());
        return cartePige;
    }
}
