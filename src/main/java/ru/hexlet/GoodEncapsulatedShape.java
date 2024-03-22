package ru.hexlet;

public class GoodEncapsulatedShape {

    protected int calculateArea() {
        throw new MethodNotSupportedException();
    }

    public int getArea() {
        return calculateArea();
    }

    static class Rectangle extends GoodEncapsulatedShape {
        private int x, y;

        @Override
        protected int calculateArea() {
            return x * y;
        }
    }

    static class RectangularTriangle extends GoodEncapsulatedShape {
        private int legX, legY;

        @Override
        protected int calculateArea() {
            return (legX * legY) / 2;
        }
    }
}
