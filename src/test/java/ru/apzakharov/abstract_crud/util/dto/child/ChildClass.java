package ru.apzakharov.abstract_crud.util.dto.child;

import org.generic_parameter_type_exctraction.dto.parent.ParentClass;
import ru.apzakharov.abstract_crud.util.dto.AnotherGenericInterface;
import ru.apzakharov.abstract_crud.util.dto.NotGenericInterface;

public class ChildClass<CHILD_CLASS_GENERIC_TYPE> extends ParentClass<Integer>
        implements ChildInterface<Long,CHILD_CLASS_GENERIC_TYPE>, NotGenericInterface, AnotherGenericInterface<Byte> {
}
