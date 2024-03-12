package org.hexlet;

public class BadEncapsulatedShape {
    public int calculateArea() {
        throw new MethodNotSupportedException();
    }
    public int getArea() {
        return calculateArea();
    }
    static class Rectangle extends BadEncapsulatedShape {
        int x, y;
        @Override
        public int calculateArea() {
            return x * y;
        }
    }
    static class RectangularTriangle extends BadEncapsulatedShape {
        int legX, legY;

        @Override
        public int calculateArea() {
            return (legX * legY) / 2;
        }
    }
}


