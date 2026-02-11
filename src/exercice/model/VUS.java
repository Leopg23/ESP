package exercice.model;

public class VUS extends Voiture{

    public VUS(String marque, String couleure, int annee) {
        super(marque, couleure, annee);
    }
    public static void allerEnMontagne(){
        IO.println("je men vais en montagne en vus");
    }

    @Override
    public void description(){
     IO.println("je suis un VUS " + getCouleure() + " de la marque " + getMarque() + " et de l'annee " + getAnnee());
    }
    public void conduire() {
        IO.println("Vroum Vroum");
    }

}
