package Pen.src.pen;

import Pen.src.inkSource.GelPenRefill;
import Pen.src.mechanism.Mechanism;

public class GelPen extends Pen {

    public GelPen(GelPenRefill refill, Mechanism mechanism) {
        super(refill, mechanism);
    }

    @Override
    public void changeInkSource(String color) {
        this.inkSource = new GelPenRefill(color);
        System.out.println("GelPen refill changed to " + color + ".");
    }
}

