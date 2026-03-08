package Ouistiti;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class Paquet extends Tas{

    public Paquet(List<Carte> aCartes){
        super(aCartes);
        this.aCartes = aCartes;

    }

    public List<Carte> melanger(){
        Collections.shuffle(aCartes);
        return aCartes;
    }

    public Carte piger(){
            if(aCartes.isEmpty()){
                IO.println("il ne reste plus de carte");
                return null;
            }

            Carte cartePige = aCartes.getFirst();
            aCartes.remove(cartePige);
            return cartePige;
    }

    public Carte regarderTop() {
        Carte oCarteTop = aCartes.size() != 0
                ? aCartes.getFirst()
                : new Carte(999,"",false,false);

        return oCarteTop;
    }
}
