package exercice.execution;

import exercice.model.*;

public class Main {
    public static void main(){
        rectangle r1 = new rectangle(20,30);
        cercle c1 = new cercle(15);
        IO.println(Aire.calculerAire(r1.height(),r1.width()));
        IO.println(Aire.calculerAire(c1.radius()));
        IO.println(Aire.calculerAire(r1.height(),r1.width()) > Aire.calculerAire(c1.radius()) ? "le rectangles est plus grand":"le cercle est plus grand" );
    }

}


