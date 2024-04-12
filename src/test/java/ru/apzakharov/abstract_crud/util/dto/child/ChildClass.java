package ru.apzakharov.abstract_crud.util.dto.child;

import ru.apzakharov.abstract_crud.util.dto.AnotherGenericInterface;
import ru.apzakharov.abstract_crud.util.dto.NotGenericInterface;
import ru.apzakharov.abstract_crud.util.dto.parent.ParentClass;

public class ChildClass<CHILD_CLASS_GENERIC_TYPE> extends ParentClass<Integer>
        implements ChildInterface<Long,CHILD_CLASS_GENERIC_TYPE>, NotGenericInterface, AnotherGenericInterface<Byte> {
}
