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
    public static final int ITAILLE_MAIN = 4;
    public static final int INOMBRE_JOUEURS = 2;
    public static final int INOMBRE_CARTES_TOTAL = 52;
    public static final int[] IZONEJOUEUR = {400, 300}; // [0] = x, [1] = y
    static void main() {

        // Il est preferable de call la fonction ainsi pour eviter de creer des bugs de syncronisation

        List<Carte> aCartePaquet = new ArrayList<>();
        List<Carte> aCarteDefausse = new ArrayList<>();
        List<Carte> aMainJoueur = new ArrayList<>();
        boolean bFaceVisible = true;
        for (int i = 0; i < ITAILLE_MAIN; i++) {
                aMainJoueur.add(new Carte(i, "",bFaceVisible, false));
        }

        List<Joueur> aJoueurs = new ArrayList<>();
        String sNomJoueur = "Leo";
        boolean bPeutJeterCarte = false;
        int iScoreTotal = 0;

        for (int i = 1; i <= INOMBRE_JOUEURS; i++) {
            aJoueurs.add(new Joueur(sNomJoueur, aMainJoueur, bPeutJeterCarte, iScoreTotal));
            sNomJoueur = "pierre";
        }

        Paquet oPaquet = new Paquet(aCartePaquet);

        for (int i = 1; i <= INOMBRE_CARTES_TOTAL; i++) {
            String signe = "";
            int valeure = 0;
            if(i<=13){
                signe = "❤️";
                valeure = i;
            } else if (i<=26) {
                signe = "♠️";
                valeure = i - 13;
            } else if (i<=39) {
                signe = "♦️";
                valeure = i - 26;
            } else {
                signe = "♣️";
                valeure = i - 39;
            }
            oPaquet.aCartes.add(new Carte(valeure,signe,false , false));
        }

        Defausse oDefausse = new Defausse(aCarteDefausse);

        Partie oPartie = new Partie(aJoueurs, oPaquet, oDefausse, 1);
        oPartie.initialierPartie();
        IO.println(oPartie);

        SwingUtilities.invokeLater(() -> GererFenetre(oPartie));
    }

    public static void GererFenetre(Partie oPartie) {

        DisplayJeu jeu = new DisplayJeu(oPartie);
        ImageIcon imageIcon = new ImageIcon("res/icon.png");

        JFrame frame = new JFrame("Ouistiti");
        frame.setUndecorated(true);
        frame.add(jeu);
        frame.pack();
        frame.setFocusable(true);
        frame.setIconImage(imageIcon.getImage());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });


    }

}
