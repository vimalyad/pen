package Pen.src.inkSource;

public class InkReservoir implements InkSource {
    private String color;
    private int inkLevel;

    public InkReservoir(String color) {
        this.color = color;
        this.inkLevel = 100;
    }

    public void refillInk(String newColor) {
        this.color = newColor;
        this.inkLevel = 100;
        System.out.println("Reservoir refilled with " + newColor + " ink.");
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean hasInk() {
        return inkLevel <= 0;
    }

    @Override
    public void write() {
        if (hasInk()) {
            throw new IllegalStateException("Reservoir is empty! Please refill the ink.");
        }
        inkLevel -= 10;
        System.out.println("Writing with " + color + " ink using InkReservoir");
    }
}
