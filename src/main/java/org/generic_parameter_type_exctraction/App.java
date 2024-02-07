package org.generic_parameter_type_exctraction;

import org.generic_parameter_type_exctraction.dto.AnotherGenericInterface;
import org.generic_parameter_type_exctraction.dto.child.ChildClass;
import org.generic_parameter_type_exctraction.dto.child.ChildInterface;
import org.generic_parameter_type_exctraction.dto.parent.ParentInterface;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws NoSuchMethodException {
        ChildClass<Integer> cInstance = new ChildClass();

        Class<? extends ChildClass> cClass = cInstance.getClass();

        Class<?> childInterface = getInterface(cClass);
        Type childInterfaceType = getGenericInterfaceType(cClass);
//        Class<?> childRawClass = (Class) childInterfaceType.getRawType();

        Class<?> parentInterface = getInterface(childInterface);
        ParameterizedType parentInterfaceType =(ParameterizedType) getGenericInterfaceType(childInterface);
        Class<?>parentRawClass = (Class) parentInterfaceType.getRawType();


        Class genericParameterParentInterfaceClass =
                ReflectionUtils.getGenericParameterClass(cInstance.getClass(), ParentInterface.class, 0);
        Class genericParameterChildInterfaceClass =
                ReflectionUtils.getGenericParameterClass(cInstance.getClass(), ChildInterface.class, 0);
        Class genericParameterAnotherInterfaceClass = ReflectionUtils.getGenericParameterClass(cInstance.getClass(), AnotherGenericInterface.class, 0);

        System.out.println(genericParameterParentInterfaceClass.equals(String.class));
        System.out.println(genericParameterChildInterfaceClass.equals(Long.class));
        System.out.println(genericParameterAnotherInterfaceClass.equals(Byte.class));

        System.out.println("1");

    }

    private static Type getGenericInterfaceType(Class<?> cClass) {
        return Arrays.stream(cClass.getGenericInterfaces())
                .findFirst().orElse(null);
    }

    private static Class<?> getInterface(Class<?> cClass) {
        return Arrays.stream(cClass.getInterfaces())
                .findFirst().orElse(null);
    }





}
