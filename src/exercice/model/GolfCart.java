package exercice.model;

public class GolfCart extends Voiture{

    public GolfCart(String marque, String couleure, int annee) {
        super(marque, couleure, annee);
    }

    public static void allerAuGolf(){
        IO.println("je men vais au golf en golfcart");
    }
    @Override
    public void description(){
        IO.println("je suis un GolfCart " + getCouleure() + " de la marque " + getMarque() + " et de l'annee " + getAnnee());
    }

    public void description(String personalisation){
        IO.println(personalisation + " pi btw chui un golf cart");
    }

    public void conduire() {
        IO.println("proute proute");
    }
}
