package Pen.src.inkSource;

public abstract class Refill implements InkSource {
    protected String color;
    protected int inkLevel;

    public Refill(String color) {
        this.color = color;
        this.inkLevel = 100;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean noInk() {
        return inkLevel <= 0;
    }

    @Override
    public void write() {
        if (noInk()) {
            throw new IllegalStateException("Refill is empty! Please replace the refill.");
        }
        inkLevel -= 10;
        System.out.println("Writing with " + getColor() + " ink using " + getClass().getSimpleName());
    }
}
