package exercice.model;

public class Aire {
    private static double resultat;
    public static double calculerAire(double height, double width){
        setResultat(height*width);
        return getResultat();
    }
    public static double calculerAire(double radius){
        setResultat((radius * radius)*Math.PI);
        return getResultat();
    }

    public static double getResultat() {
        return resultat;
    }

    public static void setResultat(double resultat) {
        Aire.resultat = resultat;
    }
}
