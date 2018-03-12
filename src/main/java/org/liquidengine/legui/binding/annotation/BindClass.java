package org.liquidengine.legui.binding.annotation;

/**
 * @author Aliaksandr_Shcherbin.
 */
public @interface BindClass {

    String to();

    boolean defaultBinding() default false;

    Binding[] unbind() default {};
}
