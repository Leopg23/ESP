package exercice.model;

public abstract class Voiture {
    private String  marque,
                    couleure;
    private int annee;
    private static int compteur;

    public Voiture(String marque, String couleure, int annee){
        this.marque = marque;
        this.couleure = couleure;
        this.annee = annee;
        compteur++;
        IO.println("construction de votre voiture chef");
    }
    public void description(){
        IO.println("je suis une voiture " + couleure + " de la marque " + marque + " et de l'annee " + annee);
    }

    public abstract void conduire();

    // Getter/Setters
    public String getMarque() {
        return marque;
    }
    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCouleure() {
        return couleure;
    }
    public void setCouleure(String couleure) {
        this.couleure = couleure;
    }

    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public static int getCompteur() {
        return compteur;
    }
}
