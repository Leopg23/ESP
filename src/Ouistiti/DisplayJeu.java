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
import java.util.Objects;


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
    private Carte oCarteActive = null;
    private boolean bPigerEnCours = true;
    private boolean bRevelerCartePaquet = false;
    private boolean bTourJ1 = true;
    private int iNbrTour = 0;
    private Point pointDernierClic;
    private ArrayList<int[]> aPosHighlightCartes = new ArrayList<>();
    private Font fMonospaced = new Font("Monospaced", Font.BOLD, 16);
    private int centerX = Main.ISCREEN_WIDTH / 2;
    private int centerY = Main.ISCREEN_HEIGHT / 2;
    private int distanceH = Main.ISCREEN_HEIGHT / 3;
    private int distanceW = Main.ISCREEN_WIDTH / 3;

    private List<Joueur.Zone> aZonesJoueurs = new ArrayList<>();
    private int[] dx = {0, 0, distanceW, -distanceW};
    private int[] dy = {-distanceH, distanceH, 0, 0};

    Paquet.Zone rZonePaquet = new Tas.Zone(centerX - 100, centerY, Main.ICARTE_WIDTH, Main.ICARTE_HEIGHT);
    Defausse.Zone rZoneDefausse = new Tas.Zone(centerX + 100, centerY, Main.ICARTE_WIDTH, Main.ICARTE_HEIGHT);


    public DisplayJeu(Partie oPartie) {
        this.setoPartie(oPartie);
        this.setPreferredSize(new Dimension(Main.ISCREEN_WIDTH, Main.ISCREEN_HEIGHT));
        //askip faut une couleure opaque pour eviter des bugs daffichage i guess
        this.setBackground(Color.BLACK);
        this.aPosHighlightCartes.add(new int[]{55, 15});  // Haut Gauche
        this.aPosHighlightCartes.add(new int[]{255, 15}); // Haut Droite
        this.aPosHighlightCartes.add(new int[]{55, 165}); // Bas Gauche
        this.aPosHighlightCartes.add(new int[]{255, 165}); // Bas Droite





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


        } catch (IOException e) {
            IO.println(e.getMessage());
        }


        this.addMouseListener(new MouseAdapter() {
            Joueur oJoueurActuel = null;

            Defausse oDefausse = oPartie.getoDefausse();

            @Override
            public void mousePressed(MouseEvent e) {
//                IO.println(oCarteActive);
                List<Carte> oMainJoueurActuel;
                List<Joueur.Zone> aPositionCartesJoueurActuel;
                if (!bPigerEnCours) {

                    if (rZoneDefausse.contient(e.getPoint()) && !(rZoneDefausse.contient(pointDernierClic))) {
                        pointDernierClic = e.getPoint();
                        oDefausse.ajouterCarte(oCarteActive);
                        bPigerEnCours = true;
                        bRevelerCartePaquet = false;
                        repaint();
                        return;
                    }

                    oMainJoueurActuel = oJoueurActuel.getaMainJoueur();
                    aPositionCartesJoueurActuel = oJoueurActuel.getaPositionCartes();

                    for (int j = 0; j < oMainJoueurActuel.size(); j++) {
                        Carte carteActuelle = oMainJoueurActuel.get(j);
                        Joueur.Zone posCarteActuelle = aPositionCartesJoueurActuel.get(j);

                        if (posCarteActuelle.contient(e.getPoint())) {
                            pointDernierClic = e.getPoint();
                            // Mettre la carte actuelle dans la defausse
                            oDefausse.ajouterCarte(carteActuelle);
                            // Remplacer par la carte active
                            oMainJoueurActuel.remove(j);
                            oMainJoueurActuel.add(j, oCarteActive);


                            bPigerEnCours = true;
                            bRevelerCartePaquet = false;


                            repaint();
                            return;
                        }
                    }//FIN for

                }
                // Si on clique sur le paquet ou la deffause pour piger
                else if ((rZonePaquet.contient(e.getPoint()) || rZoneDefausse.contient(e.getPoint())) && bPigerEnCours) {

                    // Code specifique a si on clique sur le paquet
                    if (rZonePaquet.contient(e.getPoint()) && bPigerEnCours) {
                        pointDernierClic = e.getPoint();
                        bPigerEnCours = false;
                        bRevelerCartePaquet = true;
                        oCarteActive = oPartie.getoPaquet().piger();

                    }

                    // Code specifique a si on clique sur la defausse
                    if (rZoneDefausse.contient(e.getPoint()) && !oDefausse.aCartes.isEmpty() && bPigerEnCours) {
                        pointDernierClic = e.getPoint();
                        bPigerEnCours = false;
                        oCarteActive = oPartie.getoDefausse().regarderTop();
                        oDefausse.aCartes.remove(oCarteActive);
                        IO.println(oCarteActive);

                    }

                    // Code pour les deux
//                    bPigerEnCours = false;
                    iNbrTour++;
                    if (iNbrTour % 2 == 0) {
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

        // highlight sur le paquet
        g.fillRect(
                    800,
                    466,
                    118,
                    147
                );

        ----

        // highlight sur la defausse
        g.fillRect(
                    1001,
                    466,
                    118,
                    147
            );

        ----
         */


        super.paintComponent(g);



        Color highlightColor = Color.decode("#61e6ec");
        Color couleurVert = Color.decode("#41662a");
        Color couleurMauve = Color.decode("#8b1786");

        g.setColor(highlightColor);

        g.setFont(fMonospaced);
        FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(fMonospaced);

        if (iNbrTour == 0) {

            // highlight sur le paquet
            g.fillRect(
                    800,
                    466,
                    118,
                    147
            );

            g.drawImage(
                    imgBackground,
                    0,
                    0,
                    Main.ISCREEN_WIDTH,
                    Main.ISCREEN_HEIGHT,
                    null
            );

            g.drawImage(
                    imgCarteNull,
                    rZoneDefausse.posx(),
                    rZoneDefausse.posy(),
                    rZoneDefausse.width(),
                    rZoneDefausse.height(),
                    null
            );

        } else if (!bPigerEnCours) {
            g.drawImage(
                    imgBackground,
                    0,
                    0,
                    Main.ISCREEN_WIDTH,
                    Main.ISCREEN_HEIGHT,
                    null
            );

            if(iNbrTour !=1){
                g.drawImage(
                        imgCarte,
                        rZoneDefausse.posx(),
                        rZoneDefausse.posy(),
                        rZoneDefausse.width(),
                        rZoneDefausse.height(),
                        null
                );
            } else {
                g.drawImage(
                        imgCarteNull,
                        rZoneDefausse.posx(),
                        rZoneDefausse.posy(),
                        rZoneDefausse.width(),
                        rZoneDefausse.height(),
                        null
                );
            }

        } else {
            // highlight sur le paquet
            g.fillRect(
                    800,
                    466,
                    118,
                    147
            );

            // highlight sur la defausse
            g.fillRect(
                    1001,
                    466,
                    118,
                    147
            );

            // image de fond
            g.drawImage(
                    imgBackground,
                    0,
                    0,
                    Main.ISCREEN_WIDTH,
                    Main.ISCREEN_HEIGHT,
                    null
            );

            g.drawImage(
                    imgCarte,
                    rZoneDefausse.posx(),
                    rZoneDefausse.posy(),
                    rZoneDefausse.width(),
                    rZoneDefausse.height(),
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
            Carte oCarte = oPartie.getoPaquet().aCartes.getFirst();
            dessinerTextCarte(g,metrics,highlightColor,oCarte,rZonePaquet);
        }





        if (!oPartie.getoDefausse().getaCartes().isEmpty()) {
            Carte oTopCarte = oPartie.getoDefausse().regarderTop();
            dessinerTextCarte(g,metrics,highlightColor,oTopCarte,rZoneDefausse);
        }

        for (int i = 0; i < aZonesJoueurs.size(); i++) {
            Joueur.Zone rZoneJoueur = aZonesJoueurs.get(i);
            Joueur oJoueurActuel = oPartie.getaJoueurs().get(i);
            List<Joueur.Zone> aPositionCartesJoueurActuel = oJoueurActuel.getaPositionCartes();
            List<Carte> oMainJoueurActuel = oJoueurActuel.getaMainJoueur();

            for (int j = 0; j < oMainJoueurActuel.size(); j++) {
                Joueur.Zone p = aPositionCartesJoueurActuel.get(j);

                Carte carteActuelle = oMainJoueurActuel.get(j);
                if (!carteActuelle.bEstNull) {

                    if (!bPigerEnCours) {
                        if (iNbrTour % 2 == i) {
                            g.fillRect(
                                    rZoneJoueur.posx() + aPosHighlightCartes.get(j)[0],
                                    rZoneJoueur.posy() + aPosHighlightCartes.get(j)[1] ,
                                    90,
                                    120
                            );
                        }
                    }
                    g.drawImage(
                            imgCarteFaceDown,
                            p.posx(),
                            p.posy(),
                            p.width(),
                            p.height(),
                            null
                    );


                    //dessinerTextCarte(g,metrics,highlightColor,carteActuelle,p);



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


            g.drawImage(
                    imgZoneJoueur,
                    rZoneJoueur.posx(),
                    rZoneJoueur.posy(),
                    rZoneJoueur.width(),
                    rZoneJoueur.height(), null

            );


//            g.setColor(Color.black);
//            Point[] points = rZoneJoueur.getPointsCartes();
//            g.drawString("Zone Jeu", rZoneJoueur.posx(), rZoneJoueur.posy() - 10);
        }


    }

    public void dessinerTextCarte(Graphics g, FontMetrics metrics, Color highlightColor, Carte oCarte, Tas.Zone zone){
        int iValeur = oCarte.getiValeur();
        String sValeur = oCarte.getiValeurString();
        String sSigne = oCarte.getsSigne();

        if (Objects.equals(sSigne, "♠️") || Objects.equals(sSigne, "♣️")) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.decode("#a6009f"));
        }

        if(iValeur == 11){
            sValeur = "J";
        } else if (iValeur == 12) {
            sValeur = "Q";
        } else if (iValeur == 13) {
            sValeur = "K";
        }
        int valLargeur = metrics.stringWidth(sValeur);
        int sigLargeur = metrics.stringWidth(sSigne);
        int hauteurTotale = metrics.getHeight();

        int cX = zone.posx() + (zone.width() / 2);
        int cY = zone.posy() + (zone.height() / 2);

        g.drawString(
                sValeur,
                cX - (valLargeur / 2),
                cY + (hauteurTotale / 4)
        );

        g.drawString(
                sSigne,
                cX - (sigLargeur / 2),
                cY - (hauteurTotale / 2)
        );

        g.drawString(sSigne,
                cX - (sigLargeur / 2),
                cY + (hauteurTotale)
        );

        g.setColor(highlightColor);
    }

    public void dessinerTextCarte(Graphics g, FontMetrics metrics, Color highlightColor, Carte oCarte, Joueur.Zone zone){
        int iValeur = oCarte.getiValeur();
        String sValeur = oCarte.getiValeurString();
        String sSigne = oCarte.getsSigne();

        if (Objects.equals(sSigne, "♠️") || Objects.equals(sSigne, "♣️")) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.decode("#a6009f"));
        }

        if(iValeur == 11){
            sValeur = "J";
        } else if (iValeur == 12) {
            sValeur = "Q";
        } else if (iValeur == 13) {
            sValeur = "K";
        }
        int valLargeur = metrics.stringWidth(sValeur);
        int sigLargeur = metrics.stringWidth(sSigne);
        int hauteurTotale = metrics.getHeight();

        int cX = zone.posx() + (zone.width() / 2);
        int cY = zone.posy() + (zone.height() / 2);

        g.drawString(
                sValeur,
                cX - (valLargeur / 2),
                cY + (hauteurTotale / 4)
        );

        g.drawString(
                sSigne,
                cX - (sigLargeur / 2),
                cY - (hauteurTotale / 2)
        );

        g.drawString(sSigne,
                cX - (sigLargeur / 2),
                cY + (hauteurTotale)
        );

        g.setColor(highlightColor);
    }
//Getters/Setters

    public Partie getoPartie() {
        return oPartie;
    }

    public void setoPartie(Partie oPartie) {
        this.oPartie = oPartie;
    }
}
