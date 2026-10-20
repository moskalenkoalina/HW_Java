package lab_4_HW.lab;

// Базовий клас
abstract class Quadrangle {
    protected double a, b, c, d;
    protected String type;

    public Quadrangle(String type, double a, double b, double c, double d) {
        this.type = type;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public abstract double getArea();
    public abstract double getPerimeter();

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + " [Периметр: " + getPerimeter() + ", Площа: " + getArea() + "]";
    }
}

// Квадрат
class Square extends Quadrangle {
    public Square(double side) {
        super("Квадрат", side, side, side, side);
    }
    @Override
    public double getArea() { return a * a; }
    @Override
    public double getPerimeter() { return 4 * a; }
}

// Прямокутник
class Rectangle extends Quadrangle {
    public Rectangle(double width, double height) {
        super("Прямокутник", width, height, width, height);
    }
    @Override
    public double getArea() { return a * b; }
    @Override
    public double getPerimeter() { return 2 * (a + b); }
}

// Ромб
class Rhombus extends Quadrangle {
    private double height;
    public Rhombus(double side, double height) {
        super("Ромб", side, side, side, side);
        this.height = height;
    }
    @Override
    public double getArea() { return a * height; }
    @Override
    public double getPerimeter() { return 4 * a; }
}

// Довільний чотирикутник
class CustomQuadrangle extends Quadrangle {
    private double area;
    public CustomQuadrangle(double a, double b, double c, double d, double area) {
        super("Довільний", a, b, c, d);
        this.area = area;
    }
    @Override
    public double getArea() { return area; }
    @Override
    public double getPerimeter() { return a + b + c + d; }
}