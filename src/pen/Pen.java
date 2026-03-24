package Pen.src.pen;

import Pen.src.inkSource.InkSource;
import Pen.src.mechanism.Mechanism;

public abstract class Pen {
    protected InkSource inkSource;
    protected Mechanism mechanism;

    public Pen(InkSource inkSource, Mechanism mechanism) {
        this.inkSource = inkSource;
        this.mechanism = mechanism;
    }

    public void write(String text) {
        if (!mechanism.isOpen()) {
            throw new IllegalStateException("Pen is closed! Open the pen before writing.");
        }
        if (inkSource.hasInk()) {
            throw new IllegalStateException("No ink left! Please replace or refill the ink source.");
        }
        inkSource.write();
        System.out.println("TEXT: " + text);
    }

    public String getColor() {
        return inkSource.getColor();
    }

    public void open() {
        if (mechanism.isOpen()) {
            throw new IllegalStateException("Pen is already open! Close the pen before opening.");
        }
        mechanism.open();
    }

    public void close() {
        if (!mechanism.isOpen()) {
            throw new IllegalStateException("Pen is already close! Open the pen before closing.");
        }
        mechanism.close();
    }

    public abstract void changeInkSource(String color);
}
