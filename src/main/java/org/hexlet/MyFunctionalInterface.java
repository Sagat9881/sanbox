package org.hexlet;

import java.lang.FunctionalInterface;

@FunctionalInterface
public interface MyFunctionalInterface {

    // один абстрактный метод
    double getValue();

    class ClassWithLambda {

        private static double getValueFromLambda(MyFunctionalInterface lambdaMethod) {
            return lambdaMethod.getValue();
        }

        public static void main(String[] args) {
            double valueFromLambda = getValueFromLambda(() -> 10.0);
            System.out.println(valueFromLambda);
        }
    }
}

