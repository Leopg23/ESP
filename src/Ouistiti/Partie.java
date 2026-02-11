package Ouistiti;

import java.util.ArrayList;
import java.util.List;

public class Partie {
    private static List<Joueur> aJoueurs = new ArrayList<>();
    private static Paquet oPaquet;
    private static Defausse oDefausse;
    private static int iTourActuel;


    public static void initialierPartie() {
        IO.println("Partie Commencée");
    }

    private static void passerTour() {
        IO.println("Tour Passé");
    }

    public static void verifierFin(){

    }

    //Getters/Setters
}
