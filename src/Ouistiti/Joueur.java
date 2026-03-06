package Ouistiti;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private String sNomJoueur;
    private List<Carte> aMainJoueur = new ArrayList<>();
    private boolean bPeutJeterCarte;
    private int iScoreTotal;
    private List<Zone> aPositionCartes = new ArrayList<>();
    public Joueur(String sNomJoueur, List<Carte> aMainJoueur, boolean bPeutJeterCarte, int iScoreTotal) {
        this.sNomJoueur = sNomJoueur;
        this.aMainJoueur = aMainJoueur;
        this.bPeutJeterCarte = bPeutJeterCarte;
        this.iScoreTotal = iScoreTotal;
        this.aPositionCartes = aPositionCartes;
        aPositionCartes = List.of(new Zone[]{new Zone(1, 1, 1, 1), new Zone(1, 1, 1, 1), new Zone(1,1,1,1), new Zone(1,1,1,1)});
    }
    public record Zone(int posx, int posy, int width, int height) {
        public Zone {
            posx = posx - (width / 2);
            posy = posy - (height / 2);
        }


        public Point[] getPointsCartes() {
            return new Point[] {
                    new Point(posx + width/4, posy +height/4),                         // Haut Gauche
                    new Point(posx + ((width/4) *3), posy + height/4),              // Haut Droite
                    new Point(posx + width/4, posy + ((height/4) *3)),             // Bas Gauche
                    new Point(posx + ((width/4) *3), posy + ((height/4) *3))    // Bas Droite
            };
        }

        public boolean contient(Point point) {
            return point.x >= posx && point.x <= posx + width &&
                    point.y >= posy && point.y <= posy + height;
        }
    }



    public void regarderCarte(int iIndex) {

    }

    public Carte pigerPaquet(Paquet paquet) {
        Carte cartePige = null;
//        cartePige = new Carte(cartePige.getiValeur(), cartePige.isbFaceVisible(),false);
        return cartePige;
    }

    public Carte prendreDefausse(Defausse defausse) {
        Carte cartePige = null;
//        cartePige = new Carte(cartePige.getiValeur(), cartePige.isbFaceVisible(),false);
        return cartePige;
    }

    public Carte echangerCarte(int index, Carte nouvelle) {
        Carte cartePige = null;
//        cartePige = new Carte(cartePige.getiValeur(), cartePige.isbFaceVisible(),false);
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

    public List<Zone> getaPositionCartes() {
        return aPositionCartes;
    }

    public void setaPositionCartes(List<Zone> aPositionCartes) {
        this.aPositionCartes = aPositionCartes;
    }
}
