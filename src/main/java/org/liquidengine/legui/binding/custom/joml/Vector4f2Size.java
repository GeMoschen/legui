package org.liquidengine.legui.binding.custom.joml;

import org.joml.Vector4f;
import org.liquidengine.legui.binding.model.AbstractClassBinding;
import org.liquidengine.legui.binding.model.ClassBinding;

/**
 * @author Aliaksandr_Shcherbin.
 */
public class Vector4f2Size extends AbstractClassBinding<Vector4f> {

    /**
     * Constructs class binding.
     *
     * @param bindingForType type for which binding is created.
     * @param toName name which should be used as default element name.
     * @param byDefault should this binding used as default or not.
     */
    public Vector4f2Size(Class<? extends Vector4f> bindingForType, String toName, boolean byDefault) {
        super(bindingForType, toName, byDefault);
    }
}
