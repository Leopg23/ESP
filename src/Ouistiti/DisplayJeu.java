package Ouistiti;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DisplayJeu extends JPanel {
    private Partie oPartie;

    private Image imgCarte;
    private Image imgCarteFaceDown;
    private Image imgCarteNull;
    boolean test = false;

    int centerX = Main.ISCREEN_WIDTH / 2;
    int centerY = Main.ISCREEN_HEIGHT / 2;
    int distanceH = Main.ISCREEN_HEIGHT / 3;
    int distanceW = Main.ISCREEN_WIDTH / 3;

    private List<Joueur.Zone> aZonesJoueurs = new ArrayList<>();
    int[] dx = {0, 0, distanceW, -distanceW};
    int[] dy = {-distanceH, distanceH, 0, 0};

    Paquet.Zone rZonePaquet = new Tas.Zone(centerX - 100, centerY, Main.ICARTE_WIDTH, Main.ICARTE_HEIGHT);
    Defausse.Zone rZoneDefausse = new Tas.Zone(centerX + 100, centerY, Main.ICARTE_WIDTH, Main.ICARTE_HEIGHT);


    public DisplayJeu(Partie oPartie) {
        this.setoPartie(oPartie);
        this.setPreferredSize(new Dimension(Main.ISCREEN_WIDTH, Main.ISCREEN_HEIGHT));
        //askip faut une couleure opaque pour eviter des bugs daffichage i guess
        this.setBackground(Color.BLACK);


        for (int i = 0; i < 2; i++) {
            int px = centerX + dx[i];
            int py = centerY + dy[i];

            aZonesJoueurs.add(new Joueur.Zone(px, py, 400, 300));

        }

        try {
            imgCarte = ImageIO.read(new File("res/carte.png"));
        } catch (IOException e) {
            IO.println(e.getMessage());
        }

        try {
            imgCarteFaceDown = ImageIO.read(new File("res/icon.png"));
        } catch (IOException e) {
            IO.println(e.getMessage());
        }

        try {
            imgCarteFaceDown = ImageIO.read(new File("res/null.png"));
        } catch (IOException e) {
            IO.println(e.getMessage());
        }


//        for (Joueur.Zone z : aZonesJoueurs){
//            this.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mousePressed(MouseEvent e) {
//                    if (z.contient(e.getPoint())) {
//
//                        test = !test;
//                        repaint();
//                    }
//                }
//            });
//        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);

        List<Joueur> listeJoueurs = oPartie.getaJoueurs();

        g.drawImage(imgCarteFaceDown, rZonePaquet.posx(), rZonePaquet.posy(), rZonePaquet.width(), rZonePaquet.height(), null);
        g.drawImage(imgCarte, rZoneDefausse.posx(), rZoneDefausse.posy(), rZoneDefausse.width(), rZoneDefausse.height(), null);

        for (int i = 0; i < aZonesJoueurs.size(); i++) {
            Joueur.Zone rZoneJoueur = aZonesJoueurs.get(i);
            Joueur oJoueurActuel = listeJoueurs.get(i);


            g.fillRect(rZoneJoueur.posx(), rZoneJoueur.posy(), rZoneJoueur.width(), rZoneJoueur.height());

            Point[] points = rZoneJoueur.getPointsCartes();
            List<Carte> oMainJoueurActuel = oJoueurActuel.getaMainJoueur();

            for (int i2 = 0; i2 < oMainJoueurActuel.size(); i2++) {
                Point p = points[i2];

                if (!oMainJoueurActuel.get(i2).bEstNull) {
                    g.drawImage(imgCarte, p.x + 66, p.y + 25, 75, 100, null);
                }
                else{
                    g.drawImage(imgCarteNull, p.x + 66, p.y + 25, 75, 100, null);
                }

            }

            g.drawString("Zone Jeu", rZoneJoueur.posx(), rZoneJoueur.posy() - 10);
        }


    }


    //Getters/Setters


    public Partie getoPartie() {
        return oPartie;
    }

    public void setoPartie(Partie oPartie) {
        this.oPartie = oPartie;
    }
}
