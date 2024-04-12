package ru.apzakharov.abstract_crud.util;

import org.junit.jupiter.api.Test;
import ru.apzakharov.abstract_crud.util.dto.AnotherGenericInterface;
import ru.apzakharov.abstract_crud.util.dto.NoParentClass;
import ru.apzakharov.abstract_crud.util.dto.NotGenericInterface;
import ru.apzakharov.abstract_crud.util.dto.child.ChildClass;
import ru.apzakharov.abstract_crud.util.dto.child.ChildInterface;
import ru.apzakharov.abstract_crud.util.dto.parent.ParentClass;
import ru.apzakharov.abstract_crud.util.dto.parent.ParentClassInterface;
import ru.apzakharov.abstract_crud.util.dto.parent.ParentInterface;

import java.lang.reflect.Type;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionUtilsTest {

    @Test
    void getGenericParameterClass() {
        ChildClass<Integer> cInstance = new ChildClass();
        Class genericParameterChildInterface =  ReflectionUtils.getGenericParameterClass(cInstance.getClass(), ChildInterface.class, 0);
        Class genericParameterParentClass =
                ReflectionUtils.getGenericParameterClass(cInstance.getClass(), ParentClass.class, 0);
        Class genericParameterParentInterface =
                ReflectionUtils.getGenericParameterClass(cInstance.getClass(), ParentInterface.class, 1);
        Class genericParameterParentClassInterface =
                ReflectionUtils.getGenericParameterClass(cInstance.getClass(), ParentClassInterface.class, 1);
        Class anotherGenericParameterInterface =
                ReflectionUtils.getGenericParameterClass(cInstance.getClass(), AnotherGenericInterface.class, 0);

        assertEquals(anotherGenericParameterInterface,Byte.class);
        assertEquals(genericParameterChildInterface,Long.class);
        assertEquals(genericParameterParentInterface,Long.class);
        assertEquals(genericParameterParentClass,Integer.class);
        assertEquals(genericParameterParentClassInterface, Short.class);

        assertThrows(IllegalArgumentException.class,()->ReflectionUtils.getGenericParameterClass(cInstance.getClass(), NoParentClass.class,0));
        assertThrows(IllegalArgumentException.class,()->ReflectionUtils.getGenericParameterClass(cInstance.getClass(), NotGenericInterface.class,0));
        assertThrows(IllegalStateException.class,()-> ReflectionUtils.getGenericParameterClass(cInstance.getClass(), ChildInterface.class, 1));
        assertThrows(IllegalStateException.class,()->  ReflectionUtils.getGenericParameterClass(cInstance.getClass(), ParentClassInterface.class, 0));

    }

    @Test
    void getParameterTypeDeclarationIndex() {
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