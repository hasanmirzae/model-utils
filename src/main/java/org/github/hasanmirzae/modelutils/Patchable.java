package org.github.hasanmirzae.modelutils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Collection;
import java.util.Collections;

public abstract class Patchable {
    protected Collection<String> overridingFields = Collections.EMPTY_SET;

    public Collection<String> getOverridingFields() {
        return overridingFields;
    }

    public void setOverridingFields(Collection<String> overridingFields) {
        this.overridingFields = overridingFields;
    }

    public void patchBy(Patchable obj) {
        obj.getOverridingFields().forEach(field -> updateField(field,obj));
    }

    private void updateField(String name, Patchable obj){
        try {
            BeanUtils.setProperty(this,name,BeanUtils.getProperty(obj,name));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
