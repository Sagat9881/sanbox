package org.hexlet;

public abstract class AbstractShape {
    protected abstract int calculateArea();

    public int getArea() {
        return calculateArea();
    }

    static class Rectangle extends AbstractShape {
        private int x, y;
        @Override
        protected int calculateArea() {
            return x * y;
        }
    }

    static class RectangularTriangle extends AbstractShape {
        int legX, legY;
        @Override
        protected int calculateArea() {
            return (legX * legY) / 2;
        }
    }
}
