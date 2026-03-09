package Ouistiti;

import java.util.List;

public class Defausse extends Tas {

    public Defausse(List<Carte> aCartes) {
        super(aCartes);
        this.aCartes = aCartes;
    }

    public void ajouterCarte(Carte oCarte) {
        this.aCartes.add(oCarte);
    }

    public Carte regarderTop() {
        Carte oCarteTop = aCartes.size() != 0
                ? aCartes.getLast()
                : new Carte(999,"",false,false);

        return oCarteTop;
    }
}
