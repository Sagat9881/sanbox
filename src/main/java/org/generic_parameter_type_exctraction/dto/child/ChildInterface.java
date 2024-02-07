package org.generic_parameter_type_exctraction.dto.child;

import org.generic_parameter_type_exctraction.dto.parent.ParentInterface;

public interface ChildInterface<T> extends ParentInterface<String,T> {
}
