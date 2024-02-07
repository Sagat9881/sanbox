package org.generic_parameter_type_exctraction.dto.child;

import org.generic_parameter_type_exctraction.dto.AnotherGenericInterface;
import org.generic_parameter_type_exctraction.dto.NotGenericInterface;
import org.generic_parameter_type_exctraction.dto.parent.ParentClass;

public class ChildClass<CHILD_CLASS_GENERIC_TYPE> extends ParentClass<Integer> implements ChildInterface<Long,CHILD_CLASS_GENERIC_TYPE>, NotGenericInterface, AnotherGenericInterface<Byte> {
}
