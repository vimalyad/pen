package Pen.src.pen;

import Pen.src.inkSource.BallPenRefill;
import Pen.src.mechanism.Mechanism;

public class BallPen extends Pen {

    public BallPen(BallPenRefill refill, Mechanism mechanism) {
        super(refill, mechanism);
    }

    @Override
    public void changeInkSource(String color) {
        this.inkSource = new BallPenRefill(color);
        System.out.println("BallPen refill changed to " + color + ".");
    }
}
