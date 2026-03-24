package Pen.src.factory;

import Pen.src.inkSource.BallPenRefill;
import Pen.src.inkSource.GelPenRefill;
import Pen.src.inkSource.InkReservoir;
import Pen.src.mechanism.CapMechanism;
import Pen.src.mechanism.ClickMechanism;
import Pen.src.mechanism.Mechanism;
import Pen.src.pen.BallPen;
import Pen.src.pen.GelPen;
import Pen.src.pen.InkPen;
import Pen.src.pen.Pen;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class PenFactory {

    private PenFactory() {
    }

    private static final Map<String, Supplier<Mechanism>> mechanismMap = new HashMap<>();

    private static final Map<String, BiFunction<String, Mechanism, Pen>> penCreatorMap = new HashMap<>();

    static {
        mechanismMap.put("click", ClickMechanism::new);
        mechanismMap.put("cap", CapMechanism::new);

        penCreatorMap.put("ball-pen", (color, mech) -> new BallPen(new BallPenRefill(color), mech));
        penCreatorMap.put("gel-pen", (color, mech) -> new GelPen(new GelPenRefill(color), mech));
        penCreatorMap.put("ink-pen", (color, mech) -> new InkPen(new InkReservoir(color), mech));
    }

    public static Pen createPen(String penType, String color, String mechanismType) {
        if (!penCreatorMap.containsKey(penType)) {
            throw new IllegalArgumentException("Unknown pen type: " + penType);
        }
        if (!mechanismMap.containsKey(mechanismType)) {
            throw new IllegalArgumentException("Unknown mechanism: " + mechanismType);
        }

        Mechanism mechanism = mechanismMap.get(mechanismType).get();
        return penCreatorMap.get(penType).apply(color, mechanism);
    }
}

