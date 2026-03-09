package Ouistiti;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tas {
    protected List<Carte> aCartes = new ArrayList<>();

    public Tas(List<Carte> aCartes) {
        this.aCartes = aCartes;
    }

//    protected void ajouter(Carte oCarte) {
//
//    }

//    protected Carte retirerTop() {
//        Carte cartePige = null;
////       cartePige = new Carte(cartePige.getiValeur(), "",cartePige.isbFaceVisible(),false);
//        return cartePige;
//    }

    // jvais mettre les position des tas dans des record pour acces facile
    public record Zone(int posx, int posy, int width, int height) {
        public Zone {
            posx = posx - (width / 2);
            posy = posy - (height / 2);
        }


        public boolean contient(Point point) {
            return point.x >= posx && point.x <= posx + width &&
                    point.y >= posy && point.y <= posy + height;
        }
    }

    // Getters / Setters
    public List<Carte> getaCartes() {
        return aCartes;
    }


    public void setaCartes(List<Carte> aCartes) {
        this.aCartes = aCartes;
    }

    @Override
    public String toString() {
        return "aCartes" + "(" + aCartes.size() + ")" + aCartes;
    }
}
