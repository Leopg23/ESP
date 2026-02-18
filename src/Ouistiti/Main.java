package Ouistiti;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    // Variables qui ne changerons pas seront stocker dans main et en maj
    public static final int ISCREEN_WIDTH = 1920;
    public static final int ISCREEN_HEIGHT = 1080;
    public static final int ICARTE_WIDTH = 75;
    public static final int ICARTE_HEIGHT = 100;

    static void main() {

        // Il est preferable de call la fonction ainsi pour eviter de creer des bugs de syncronisation



        List<Carte> aCartePaquet = new ArrayList<>();
        List<Carte> aCarteDefausse = new ArrayList<>();
        List<Carte> aMainJoueur = new ArrayList<>();
        boolean bFaceVisible = true;
        for (int i = 0; i < 4; i++) {
            if(Math.random() < 0.5){
                aMainJoueur.add(new Carte(i, bFaceVisible, false));
            }
            else {
                aMainJoueur.add(new Carte(i, bFaceVisible, true));
            }


        }

        List<Joueur> aJoueurs = new ArrayList<>();
        String sNomJoueur = "Leo";
        boolean bPeutJeterCarte = false;
        int iScoreTotal = 0;

        for (int i = 1; i <= 2; i++) {
            aJoueurs.add(new Joueur(sNomJoueur, aMainJoueur, bPeutJeterCarte, iScoreTotal));
            sNomJoueur = "pierre";
        }

        Paquet oPaquet = new Paquet(aCartePaquet);

        for (int i = 1; i <= 52; i++) {
            oPaquet.aCartes.add(new Carte(i,false , false));
        }

        Defausse oDefausse = new Defausse(aCarteDefausse);

        Partie oPartie = new Partie(aJoueurs, oPaquet, oDefausse, 1);
        oPartie.initialierPartie();
        IO.println(oPartie);

        SwingUtilities.invokeLater(() -> OuvrirFenetre(oPartie));
    }

    public static void OuvrirFenetre(Partie oPartie) {

        DisplayJeu jeu = new DisplayJeu(oPartie);
        ImageIcon imageIcon = new ImageIcon("res/icon.png");

        JFrame frame = new JFrame("Ouistiti");
        frame.add(jeu);
        frame.pack();
        frame.setIconImage(imageIcon.getImage());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
