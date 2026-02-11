package Ouistiti;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private String sNomJoueur;
    private List<Carte> aMainJoueur = new ArrayList<>();
    private boolean bPeutJeterCarte;
    private int iScoreTotal;

    public Joueur (String sNomJoueur, List<Carte> aMainJoueur, boolean bPeutJeterCarte, int iScoreTotal){
        this.sNomJoueur = sNomJoueur;
        this.aMainJoueur = aMainJoueur;
        this.bPeutJeterCarte = bPeutJeterCarte;
        this.iScoreTotal = iScoreTotal;
    }
    public void regarderCarte(int iIndex){

    }

    public Carte pigerPaquet(Paquet paquet){
        Carte cartePige = null;
        cartePige = new Carte(cartePige.getiValeur(), cartePige.isbFaceVisible());
        return cartePige;
    }

    public Carte prendreDefausse(Defausse defausse){
        Carte cartePige = null;
        cartePige = new Carte(cartePige.getiValeur(), cartePige.isbFaceVisible());
        return cartePige;
    }

    public Carte echangerCarte(int index, Carte nouvelle){
        Carte cartePige = null;
        cartePige = new Carte(cartePige.getiValeur(), cartePige.isbFaceVisible());
        return cartePige;
    }

    public void tenterJeter(int Index, Carte topDefausse){
    }

    public int calculerSomme(){
        int sommeCalcule = 0;
        return sommeCalcule;
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
