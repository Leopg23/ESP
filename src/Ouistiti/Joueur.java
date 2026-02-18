package Ouistiti;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private String sNomJoueur;
    private List<Carte> aMainJoueur = new ArrayList<>();
    private boolean bPeutJeterCarte;
    private int iScoreTotal;

    public Joueur(String sNomJoueur, List<Carte> aMainJoueur, boolean bPeutJeterCarte, int iScoreTotal) {
        this.sNomJoueur = sNomJoueur;
        this.aMainJoueur = aMainJoueur;
        this.bPeutJeterCarte = bPeutJeterCarte;
        this.iScoreTotal = iScoreTotal;
    }

    public record Zone(int posx, int posy, int width, int height) {
        public Zone {
            posx = posx - (width / 2);
            posy = posy - (height / 2);
        }


        public Point[] getPointsCartes() {
            return new Point[] {
                    new Point(posx, posy),                         // Haut Gauche
                    new Point(posx + width/2, posy),              // Haut Droite
                    new Point(posx, posy + height/2),             // Bas Gauche
                    new Point(posx + width/2, posy + height/2)    // Bas Droite
            };
        }
    }



    public void regarderCarte(int iIndex) {

    }

    public Carte pigerPaquet(Paquet paquet) {
        Carte cartePige = null;
        cartePige = new Carte(cartePige.getiValeur(), cartePige.isbFaceVisible(),false);
        return cartePige;
    }

    public Carte prendreDefausse(Defausse defausse) {
        Carte cartePige = null;
        cartePige = new Carte(cartePige.getiValeur(), cartePige.isbFaceVisible(),false);
        return cartePige;
    }

    public Carte echangerCarte(int index, Carte nouvelle) {
        Carte cartePige = null;
        cartePige = new Carte(cartePige.getiValeur(), cartePige.isbFaceVisible(),false);
        return cartePige;
    }

    public void tenterJeter(int Index, Carte topDefausse) {
    }

    public int calculerSomme() {
        int sommeCalcule = 0;
        return sommeCalcule;
    }

    @Override
    public String toString() {
        return "sNomJoueur[" + sNomJoueur + "] aMainJoueur" + "(" + aMainJoueur.size() + ")" + "[" + aMainJoueur + "], bPeutJeterCarte[" + bPeutJeterCarte + "], iScoreTotal[" + iScoreTotal + "]";

    }
    //Getters/Setters

    public String getsNomJoueur() {
        return sNomJoueur;
    }

    public void setsNomJoueur(String sNomJoueur) {
        this.sNomJoueur = sNomJoueur;
    }

    public List<Carte> getaMainJoueur() {
        return aMainJoueur;
    }

    public void setaMainJoueur(List<Carte> aMainJoueur) {
        this.aMainJoueur = aMainJoueur;
    }

    public int getiScoreTotal() {
        return iScoreTotal;
    }

    public void setiScoreTotal(int iScoreTotal) {
        this.iScoreTotal = iScoreTotal;
    }

}
