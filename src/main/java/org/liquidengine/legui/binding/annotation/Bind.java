package org.liquidengine.legui.binding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.liquidengine.legui.binding.model.AbstractClassBinding;

/**
 * @author Aliaksandr_Shcherbin.
 */
@Target(ElementType.FIELD)
public @interface Bind {

    String to() default "";

    Class<? extends AbstractClassBinding>[] usingBinding() default {};
}
