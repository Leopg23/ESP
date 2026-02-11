package Ouistiti;

import java.util.ArrayList;
import java.util.List;

public class Partie {
    private List<Joueur> aJoueurs = new ArrayList<>();
    private Paquet oPaquet;
    private Defausse oDefausse;
    private int iTourActuel;

    public Partie(List<Joueur> aJoueurs, Paquet oPaquet, Defausse oDefausse, int iTourActuel){
        this.setaJoueurs(aJoueurs);
        this.setoPaquet(oPaquet);
        this.setoDefausse(oDefausse);
        this.setiTourActuel(iTourActuel);
    }
    public void initialierPartie() {
        IO.println("Partie Commencée");

    }

    private void passerTour() {
        IO.println("Tour Passé");
    }

    public void verifierFin(){

    }

    @Override
    public String toString(){
        return "aJoueurs" +  "(" + aJoueurs.size() + ")" + "["+ aJoueurs + "]\n" +
                "oPaquet{" + oPaquet + "}\n" +
                "oDefausse{" + oDefausse + "}\n" +
                "iTourActuel = " + iTourActuel + ";" ;
    }


    //Getters/Setters

    public List<Joueur> getaJoueurs() {
        return aJoueurs;
    }

    public void setaJoueurs(List<Joueur> aJoueurs) {
        this.aJoueurs = aJoueurs;
    }

    public Paquet getoPaquet() {
        return oPaquet;
    }

    public void setoPaquet(Paquet oPaquet) {
        this.oPaquet = oPaquet;
    }

    public Defausse getoDefausse() {
        return oDefausse;
    }

    public void setoDefausse(Defausse oDefausse) {
        this.oDefausse = oDefausse;
    }

    public int getiTourActuel() {
        return iTourActuel;
    }

    public void setiTourActuel(int iTourActuel) {
        this.iTourActuel = iTourActuel;
    }
}
