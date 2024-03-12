package org.hexlet;

public interface RectangleInterface {

    default int calculateRectangleArea(int x, int y) {
        return x * y;
    }

    class Rectangle extends AbstractShape implements RectangleInterface {
        private int x, y;

        @Override
        protected int calculateArea() {
            return calculateRectangleArea(x, y);
        }


    }
}
