package org.hexlet;

public abstract class GoodEncapsulatedShape {

    protected abstract int calculateArea();
    public int getArea() {
        return calculateArea();
    }
    static class Rectangle extends GoodEncapsulatedShape {
        int x, y;

        @Override
        protected int calculateArea() {
            return x * y;
        }
    }
    static class RectangularTriangle extends GoodEncapsulatedShape {
        int legX, legY;
        @Override
        protected int calculateArea() {
            return  (legX * legY) / 2;
        }
    }
}
