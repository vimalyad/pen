package Pen.src.mechanism;

public class ClickMechanism implements Mechanism {
    private boolean open = false;

    @Override
    public void open() {
        open = true;
        System.out.println("Click! Pen tip extended.");
    }

    @Override
    public void close() {
        open = false;
        System.out.println("Click! Pen tip retracted.");
    }

    @Override
    public boolean isOpen() {
        return open;
    }
}
