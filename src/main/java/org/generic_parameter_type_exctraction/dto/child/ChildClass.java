package org.generic_parameter_type_exctraction.dto.child;

import org.generic_parameter_type_exctraction.dto.AnotherGenericInterface;
import org.generic_parameter_type_exctraction.dto.NotGenericInterface;
import org.generic_parameter_type_exctraction.dto.parent.ParentClass;

public class ChildClass<T> extends ParentClass<T> implements ChildInterface<Long>, NotGenericInterface, AnotherGenericInterface<Byte> {
}
