package Pen.src.pen;

import Pen.src.inkSource.InkReservoir;
import Pen.src.mechanism.Mechanism;

public class InkPen extends Pen {

    public InkPen(InkReservoir reservoir, Mechanism mechanism) {
        super(reservoir, mechanism);
    }

    @Override
    public void changeInkSource(String color) {
        ((InkReservoir) inkSource).refillInk(color);
    }
}
