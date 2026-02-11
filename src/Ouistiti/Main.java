package Ouistiti;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main(){
        // 1. Create the window (The Frame)
        JFrame frame = new JFrame("Ma Partie de Cartes");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 2. Create a component to show your text
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // Use your existing toString() here!
        // MaPartie partie = new MaPartie();
        // textArea.setText(partie.toString());

        textArea.setText("Bienvenue Léo!\nVotre partie s'affichera ici.");

        // 3. Add to frame and show
        frame.add(new JScrollPane(textArea)); // Adds a scrollbar if text is long
        frame.setVisible(true);


        List<Carte> aCartePaquet = new ArrayList<>();
        List<Carte> aCarteDefausse = new ArrayList<>();
        List<Carte> aMainJoueur = new ArrayList<>();
        boolean bFaceVisible = true;
        for(int i=1; i<2; i++){
            aMainJoueur.add(new Carte(i, bFaceVisible));
        }

        List<Joueur> aJoueurs = new ArrayList<>();
        String sNomJoueur = "Leo";
        boolean bPeutJeterCarte = false;
        int iScoreTotal = 0;

        for(int i=1; i<=2; i++){
            aJoueurs.add(new Joueur(sNomJoueur,aMainJoueur,bPeutJeterCarte,iScoreTotal));
            sNomJoueur = "pierre";
        }

        Paquet oPaquet = new Paquet(aCartePaquet);

        for(int i=1; i<=52; i++){
            oPaquet.aCartes.add(new Carte(i,false));
        }

        Defausse oDefausse = new Defausse(aCarteDefausse);
        int iTourActuel = 1;
        Partie oPartie = new Partie(aJoueurs,oPaquet,oDefausse,iTourActuel);
        oPartie.initialierPartie();
        IO.println(oPartie);
    }
}
