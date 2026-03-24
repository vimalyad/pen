# 🖊️ Pen LLD — Low Level Design

A clean, extensible Java implementation of a Pen system demonstrating key OOP and SOLID principles.

---

## 📦 Package Structure

```
pen/src/
├── inksource/
│   ├── InkSource.java         ← Interface (common abstraction)
│   ├── Refill.java            ← Abstract class for cartridge-based refills
│   ├── BallPenRefill.java
│   ├── GelPenRefill.java
│   └── InkReservoir.java      ← Direct InkSource impl (not a Refill)
├── mechanism/
│   ├── Mechanism.java         ← Interface
│   ├── ClickMechanism.java
│   └── CapMechanism.java
├── pen/
│   ├── Pen.java               ← Abstract class (Template Method)
│   ├── BallPen.java
│   ├── GelPen.java
│   └── InkPen.java
├── factory/
│   └── PenFactory.java        ← Factory with Supplier + BiFunction
└── Main.java
```

---

## 🗂️ UML Class Diagram

```mermaid
classDiagram

    %% ─── InkSource hierarchy ───────────────────────────────
    class InkSource {
        <<interface>>
        +getColor() String
        +hasInk() boolean
        +write() void
    }

    class Refill {
        <<abstract>>
        #color : String
        #inkLevel : int
        +getColor() String
        +hasInk() boolean
        +write() void
    }

    class BallPenRefill {
        +BallPenRefill(color)
    }

    class GelPenRefill {
        +GelPenRefill(color)
    }

    class InkReservoir {
        -color : String
        -inkLevel : int
        +getColor() String
        +hasInk() boolean
        +write() void
        +refillInk(color) void
    }

    InkSource       <|..    Refill
    InkSource       <|..    InkReservoir
    Refill          <|--    BallPenRefill
    Refill          <|--    GelPenRefill


    %% ─── Mechanism hierarchy ────────────────────────────────
    class Mechanism {
        <<interface>>
        +open() void
        +close() void
        +isOpen() boolean
    }

    class ClickMechanism {
        -open : boolean
        +open() void
        +close() void
        +isOpen() boolean
    }

    class CapMechanism {
        -open : boolean
        +open() void
        +close() void
        +isOpen() boolean
    }

    Mechanism       <|..    ClickMechanism
    Mechanism       <|..    CapMechanism


    %% ─── Pen hierarchy ──────────────────────────────────────
    class Pen {
        <<abstract>>
        #inkSource : InkSource
        #mechanism : Mechanism
        +write(text) void
        +getColor() String
        +open() void
        +close() void
        +changeInkSource(color) void*
    }

    class BallPen {
        +BallPen(BallPenRefill, Mechanism)
        +changeInkSource(color) void
    }

    class GelPen {
        +GelPen(GelPenRefill, Mechanism)
        +changeInkSource(color) void
    }

    class InkPen {
        +InkPen(InkReservoir, Mechanism)
        +changeInkSource(color) void
    }

    Pen             <|--    BallPen
    Pen             <|--    GelPen
    Pen             <|--    InkPen

    Pen             o-->    InkSource   : inkSource
    Pen             o-->    Mechanism   : mechanism

    BallPen         ..>     BallPenRefill   : creates
    GelPen          ..>     GelPenRefill    : creates
    InkPen          ..>     InkReservoir    : creates


    %% ─── Factory ─────────────────────────────────────────────
    class PenFactory {
        <<utility>>
        -mechanismMap : Map~String, Supplier~Mechanism~~
        -penCreatorMap : Map~String, BiFunction~String,Mechanism,Pen~~
        +createPen(penType, color, mechanismType) Pen$
    }

    PenFactory      ..>     Pen             : produces
    PenFactory      ..>     Mechanism       : produces
```

---

## 🎯 Design Patterns Used

| Pattern             | Where                 | Why                                                           |
|---------------------|-----------------------|---------------------------------------------------------------|
| **Template Method** | `Pen.write(text)`     | Same write flow for all pens; only `changeInkSource()` varies |
| **Strategy**        | `Mechanism` interface | ClickMechanism / CapMechanism are interchangeable behaviors   |
| **Factory**         | `PenFactory`          | Centralized creation; client never calls `new` directly       |

---

## 🔑 Key Design Decisions

### 1. `InkSource` as a unified abstraction

Instead of forcing `InkPen` to use a `Refill`, an `InkSource` interface acts as the common contract. `Refill` and
`InkReservoir` are separate implementations — one replaceable, one refillable in place.

```
InkSource (interface)
    ├── Refill (abstract)          → BallPenRefill, GelPenRefill
    └── InkReservoir               → used by InkPen directly
```

### 2. `Refill` as intermediate abstraction

`BallPenRefill` and `GelPenRefill` share common cartridge behavior (replaceable, has ink level). Rather than duplicating
this, `Refill` abstract class captures it. `InkReservoir` doesn't extend `Refill` since it's not replaceable — it
directly implements `InkSource`.

### 3. `changeInkSource()` is abstract in `Pen`

Each pen subclass knows exactly what `InkSource` to create:

- `BallPen` → new `BallPenRefill(color)`
- `GelPen`  → new `GelPenRefill(color)`
- `InkPen`  → calls `reservoir.refillInk(color)` in place

### 4. `PenFactory` uses `Supplier<Mechanism>`

Mechanisms hold state (`open`/`closed`). A shared instance would cause all pens of the same mechanism type to share
state — a subtle but serious bug. `Supplier::get` produces a fresh instance per pen.

---

## ⚙️ How to Run

```bash
# Compile
javac -d out $(find . -name "*.java")

# Run
java -cp out src.Main
```
