package ru.apzakharov.abstract_crud.util.dto.child;

import org.generic_parameter_type_exctraction.dto.parent.ParentInterface;

public interface ChildInterface<CHILD_INTERFACE_GENERIC_TYPE1,CHILD_INTERFACE_GENERIC_TYPE2>
        extends ParentInterface<String, CHILD_INTERFACE_GENERIC_TYPE1> {
}
