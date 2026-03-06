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
    private Image imgZoneJoueur;
    private Image imgBackground;
    private Image imgHighlightCartes;
    private Image imgHighlightDeffause;
    private Image imgHighlightPaquet;
    private Image imgHighlightPlayer1;
    private Image imgHighlightPlayer2;
    private Image imgHighlightTas;

    private String sCarteActive = "pigez une carte";
    boolean bPigerEnCours = false;
    boolean bRevelerCartePaquet = false;
    boolean bTourJ1 = true;

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


        for (int i = 0; i < Main.INOMBRE_JOUEURS; i++) {
            int px = centerX + dx[i];
            int py = centerY + dy[i];

            aZonesJoueurs.add(new Joueur.Zone(px, py, Main.IZONEJOUEUR[0], Main.IZONEJOUEUR[1]));
            List<Joueur> aJoueurs = oPartie.getaJoueurs();
            Joueur oJoueurActuel = oPartie.getaJoueurs().get(i);
            Joueur.Zone rZoneJoueur = aZonesJoueurs.get(i);
            Point[] points = rZoneJoueur.getPointsCartes();
            List<Joueur.Zone> aPositionCartesJoueurActuel = new ArrayList<>();
            for (int j = 0; j < Main.ITAILLE_MAIN; j++) {
                Point p = points[j];
                aPositionCartesJoueurActuel.add(new Joueur.Zone(p.x, p.y, Main.ICARTE_WIDTH, Main.ICARTE_HEIGHT));
            }
            oJoueurActuel.setaPositionCartes(aPositionCartesJoueurActuel);
            IO.println("test1-------" + oJoueurActuel.getaPositionCartes());
        }

        try {
            imgBackground = ImageIO.read(new File("res/fond_du_jeu.png"));
            imgCarte = ImageIO.read(new File("res/carte.png"));
            imgCarteFaceDown = ImageIO.read(new File("res/carte_verso.png"));
            imgCarteNull = ImageIO.read(new File("res/null.png"));
            imgZoneJoueur = ImageIO.read(new File("res/zoneJoueur.png"));


            imgHighlightCartes = ImageIO.read(new File("res/highlightCartes.png"));
            imgHighlightDeffause = ImageIO.read(new File("res/highlightDeffause.png"));
            imgHighlightPaquet = ImageIO.read(new File("res/highlightPaquet.png"));
            imgHighlightPlayer1 = ImageIO.read(new File("res/highlightPlayer1.png"));
            imgHighlightPlayer2 = ImageIO.read(new File("res/highlightPlayer2.png"));
            imgHighlightTas = ImageIO.read(new File("res/highlightTas.png"));

        } catch (IOException e) {
            IO.println(e.getMessage());
        }


        this.addMouseListener(new MouseAdapter() {
            Joueur oJoueurActuel = null;
            Carte oCarteActive = null;
            Defausse oDefausse = oPartie.getoDefausse();

            @Override
            public void mousePressed(MouseEvent e) {

                List<Carte> oMainJoueurActuel;
                List<Joueur.Zone> aPositionCartesJoueurActuel;
                if (bPigerEnCours) {

                    if (rZoneDefausse.contient(e.getPoint())) {
                        oDefausse.ajouterCarte(oCarteActive);
                        bPigerEnCours = false;
                        bRevelerCartePaquet = false;
                        repaint();
                    }

                    oMainJoueurActuel = oJoueurActuel.getaMainJoueur();
                    aPositionCartesJoueurActuel = oJoueurActuel.getaPositionCartes();

                    for (int j = 0; j < oMainJoueurActuel.size(); j++) {
                        Carte carteActuelle = oMainJoueurActuel.get(j);
                        Joueur.Zone posCarteActuelle = aPositionCartesJoueurActuel.get(j);

                        if (posCarteActuelle.contient(e.getPoint())) {
                            // Mettre la carte actuelle dans la defausse
                            oDefausse.ajouterCarte(carteActuelle);
                            // Remplacer par la carte active
                            oMainJoueurActuel.remove(j);
                            oMainJoueurActuel.add(j, oCarteActive);


                            bPigerEnCours = false;
                            bRevelerCartePaquet = false;
//                          IO.println(posCarteActuelle + " ICI B");
                            IO.println(carteActuelle);
                            IO.println(oMainJoueurActuel);
                            repaint();
                        }
                    }//FIN for
                }

                // Si on clique sur le paquet ou la deffause pour piger
                if ((rZonePaquet.contient(e.getPoint()) || rZoneDefausse.contient(e.getPoint())) && !bPigerEnCours) {

                    // Code specifique a si on clique sur le paquet
                    if (rZonePaquet.contient(e.getPoint())) {
                        bRevelerCartePaquet = true;
                        oCarteActive = oPartie.getoPaquet().piger();
                        IO.println(bPigerEnCours);
                    }

                    // Code specifique a si on clique sur la defausse
                    if (rZoneDefausse.contient(e.getPoint()) && !oDefausse.aCartes.isEmpty()) {
                        oCarteActive = oPartie.getoDefausse().regarderTop();
                        IO.println(oCarteActive);
                    }

                    // Code pour les deux
                    bPigerEnCours = true;

                    if (bTourJ1) {
                        bTourJ1 = false;
                        oJoueurActuel = oPartie.getaJoueurs().get(0);
//                            IO.println(oJoueurActuel + " if");
                    } else {
                        oJoueurActuel = oPartie.getaJoueurs().get(1);
                        bTourJ1 = true;
//                            IO.println(oJoueurActuel + " else");
                    }


                    sCarteActive = (oCarteActive != null)
                            ? oCarteActive.getiValeurString() + " " + oCarteActive.getsSigne()
                            : "paquet vide";


                    repaint();
                }


            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {

        /* rectangles a fill pour les highlights de differentes zones :

        zone paquet ---->g.fillRect(
                            800,
                            466,
                            118,
                            147
                        );


         */

        Color highlightColor = Color.decode("#61e6ec");
        super.paintComponent(g);
        g.setColor(highlightColor);

        if (!bPigerEnCours) {
            g.drawImage(imgBackground,
                    0,
                    0,
                    Main.ISCREEN_WIDTH,
                    Main.ISCREEN_HEIGHT,
                    null
            );
        }
        else{
            g.fillRect(
                    1001,
                    466,
                    118,
                    147
            );
            g.drawImage(imgBackground,
                    0,
                    0,
                    Main.ISCREEN_WIDTH,
                    Main.ISCREEN_HEIGHT,
                    null
            );

        }

        g.drawImage(
                bRevelerCartePaquet ? imgCarte : imgCarteFaceDown,
                rZonePaquet.posx(),
                rZonePaquet.posy(),
                rZonePaquet.width(),
                rZonePaquet.height(),
                null
        );

        if (bRevelerCartePaquet) {
            g.drawString(
                    sCarteActive,
                    rZonePaquet.posx() + (rZonePaquet.width() / 2),
                    rZonePaquet.posy() + (rZonePaquet.height() / 2)
            );
        }

        g.drawImage(
                imgCarte,
                rZoneDefausse.posx(),
                rZoneDefausse.posy(),
                rZoneDefausse.width(),
                rZoneDefausse.height(),
                null
        );

        g.drawString(
                !oPartie.getoDefausse().getaCartes().isEmpty()
                        ? oPartie.getoDefausse().regarderTop().getiValeurString()
                        : "",
                rZoneDefausse.posx() + (rZoneDefausse.width() / 2),
                rZoneDefausse.posy() + (rZoneDefausse.height() / 2)
        );

        g.drawString(oPartie.getoDefausse().regarderTop().getsSigne(),
                rZoneDefausse.posx() + (rZoneDefausse.width() / 2),
                rZoneDefausse.posy() + (rZoneDefausse.height() / 2) + 10
        );

        for (int i = 0; i < aZonesJoueurs.size(); i++) {
            Joueur.Zone rZoneJoueur = aZonesJoueurs.get(i);
            Joueur oJoueurActuel = oPartie.getaJoueurs().get(i);
            List<Joueur.Zone> aPositionCartesJoueurActuel = oJoueurActuel.getaPositionCartes();
            g.setColor(Color.RED);
            g.drawImage(
                    imgZoneJoueur,
                    rZoneJoueur.posx(),
                    rZoneJoueur.posy(),
                    rZoneJoueur.width(),
                    rZoneJoueur.height(), null

            );
//            g.setColor(Color.black);
//            Point[] points = rZoneJoueur.getPointsCartes();
            List<Carte> oMainJoueurActuel = oJoueurActuel.getaMainJoueur();

            for (int j = 0; j < oMainJoueurActuel.size(); j++) {
                Joueur.Zone p = aPositionCartesJoueurActuel.get(j);

                Carte carteActuelle = oMainJoueurActuel.get(j);
                if (!carteActuelle.bEstNull) {
                    g.drawImage(
                            imgCarteFaceDown,
                            p.posx(),
                            p.posy(),
                            p.width(),
                            p.height(),
                            null
                    );

                    g.drawString(
                            carteActuelle.getiValeurString() + " " + carteActuelle.getsSigne(),
                            p.posx() + (p.width() / 2),
                            p.posy() + (p.height() / 2)
                    );

                } else {
                    g.drawImage(
                            imgCarteNull,
                            p.posx(),
                            p.posy(),
                            p.width(),
                            p.height(),
                            null
                    );
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
