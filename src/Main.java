package Pen.src;

import Pen.src.factory.PenFactory;
import Pen.src.pen.Pen;

public class Main {
    public static void main(String[] args) {

        Pen ballPen = PenFactory.createPen("ball-pen", "blue", "click");
        ballPen.open();
        System.out.println("Current color: " + ballPen.getColor());
        ballPen.write("Hi I am Ball Pen!");
        ballPen.changeInkSource("red");
        System.out.println("Current color: " + ballPen.getColor());
        ballPen.write("Hi I am Ball Pen!");
        ballPen.close();

        System.out.println();

        Pen gelPen = PenFactory.createPen("gel-pen", "black", "cap");
        gelPen.open();
        System.out.println("Current color: " + gelPen.getColor());
        gelPen.write("HI I am Gel Pen!");
        gelPen.close();

        System.out.println();

        Pen inkPen = PenFactory.createPen("ink-pen", "green", "cap");
        inkPen.open();
        System.out.println("Current color: " + inkPen.getColor());
        inkPen.write("HI I am Ink Pen!");
        inkPen.changeInkSource("purple");
        System.out.println("Current color: " + inkPen.getColor());
        inkPen.write("HI I am Ink Pen!");
        inkPen.close();

        System.out.println();

        try {
            Pen closedPen = PenFactory.createPen("ball-pen", "blue", "click");
            closedPen.write("HI I am Ball Pen!");
        } catch (IllegalStateException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
    }
}

