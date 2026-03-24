package Pen.src.mechanism;

public class CapMechanism implements Mechanism {
    private boolean open = false;

    @Override
    public void open() {
        open = true;
        System.out.println("Cap removed. Pen is ready.");
    }

    @Override
    public void close() {
        open = false;
        System.out.println("Cap placed back. Pen is closed.");
    }

    @Override
    public boolean isOpen() {
        return open;
    }
}