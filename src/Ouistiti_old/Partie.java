package Ouistiti;

import java.util.ArrayList;
import java.util.List;

public class Partie {
    private List<Joueur> aJoueurs;
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
        oPaquet.melanger();

        for(int i = 0; i < aJoueurs.size();i++){
            Joueur oJoueurActuel = aJoueurs.get(i);
            List<Carte> aMainDepart = new ArrayList<>();

            for(int j = 0; j < Main.ITAILLE_MAIN;j++){
                aMainDepart.add(oPaquet.piger());
                oJoueurActuel.setaMainJoueur(aMainDepart);
            }
        }
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
